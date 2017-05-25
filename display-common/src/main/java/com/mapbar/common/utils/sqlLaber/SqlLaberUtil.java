package com.mapbar.common.utils.sqlLaber;

import com.mapbar.common.utils.ObjUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.hibernate.internal.util.xml.MappingReader;
import org.hibernate.internal.util.xml.OriginImpl;
import org.hibernate.internal.util.xml.XmlDocument;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author zhangy
 *
 */
@Component
public class SqlLaberUtil implements ISqlLaberUtil, ResourceLoaderAware, InitializingBean
{
    private static final Logger LOGGER = Logger.getLogger(SqlLaberUtil.class);
    
    // 利用静态变量来将所有的sql保存到内存中，方便下次读取，增加读取效率
    private static Map<String, String> namedHQLQueries = new HashMap<String, String>();
    
    private static Map<String, String> namedSQLQueries = new HashMap<String, String>();
    
    private static Map<String, Template> tempLateMap = new HashMap<String, Template>();;
    
    private ResourceLoader resourceLoader;
    @Value("${sql.xml.files.path}")
    private String xmlpath;
    
    private EntityResolver entityResolver = new DtdParse();
    
    /**
     * 查询语句名称缓存，不允许重复
     */
    private Set<String> nameCache = new HashSet<String>();



    public SqlLaberUtil()
    {
        
    }
    
    // @Override
    // public Map<String, String> getNamedHQLQueries() {
    // return namedHQLQueries;
    // }
    //
    // @Override
    // public Map<String, String> getNamedSQLQueries() {
    // return namedSQLQueries;
    // }
    
    @Override
    public void init()
        throws Exception
    {
        boolean flag = this.resourceLoader instanceof ResourcePatternResolver;
        if (flag)
        {
            Resource[] resources = ((ResourcePatternResolver)this.resourceLoader).getResources(xmlpath);
            buildMap(resources);
        }
        else
        {
            Resource resource = resourceLoader.getResource(xmlpath);
            buildMap(resource);
        }
        // clear name cache
        // 加载所有模板
        initTemplate();
        nameCache.clear();
    }
    
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader)
    {
        this.resourceLoader = resourceLoader;
    }
    
    private void buildMap(Resource[] resources)
        throws Exception
    {
        if (resources == null)
        {
            return;
        }
        for (Resource resource : resources)
        {
            buildMap(resource);
        }
    }
    
    @SuppressWarnings({"rawtypes"})
    private void buildMap(Resource resource)
        throws Exception
    {
        InputSource inputSource = null;
        try
        {
            inputSource = new InputSource(resource.getInputStream());
            XmlDocument metadataXml =
                MappingReader.INSTANCE.readMappingDocument(entityResolver,
                    inputSource,
                    new OriginImpl("file", resource.getFilename()));
            if (isDynamicStatementXml(metadataXml))
            {
                final Document doc = metadataXml.getDocumentTree();
                final Element dynamicHibernateStatement = doc.getRootElement();
                Iterator rootChildren = dynamicHibernateStatement.elementIterator();
                while (rootChildren.hasNext())
                {
                    final Element element = (Element)rootChildren.next();
                    final String elementName = element.getName();
                    if ("sql-query".equals(elementName))
                    {
                        putStatementToCacheMap(resource, element, namedSQLQueries);
                    }
                    else if ("hql-query".equals(elementName))
                    {
                        putStatementToCacheMap(resource, element, namedHQLQueries);
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.toString());
            throw e;
        }
        finally
        {
            if (inputSource != null && inputSource.getByteStream() != null)
            {
                try
                {
                    inputSource.getByteStream().close();
                }
                catch (IOException e)
                {
                    LOGGER.error(e.toString());
                    
                }
            }
        }
        
    }
    
    private void putStatementToCacheMap(Resource resource, final Element element, Map<String, String> statementMap)
        throws Exception
    {
        String sqlQueryName = element.attribute("name").getText();
        Validate.notEmpty(sqlQueryName);
        if (nameCache.contains(sqlQueryName))
        {
            throw new Exception("重复的sql-query/hql-query语句定义在文件:" + resource.getURI() + "中，必须保证name的唯一."
                + ",重复的sqlQueryName为：" + sqlQueryName);
        }
        nameCache.add(sqlQueryName);
        String queryText = element.getText();
        statementMap.put(sqlQueryName, queryText);
    }
    
    private static boolean isDynamicStatementXml(XmlDocument xmlDocument)
    {
        return "dynamic-hibernate-statement".equals(xmlDocument.getDocumentTree().getRootElement().getName());
    }
    
    /**
     * 加载所有sql模板
     *
     * @throws IOException
     */
    private void initTemplate()
    {
        Configuration configuration = new Configuration();
        configuration.setNumberFormat("#");
        for (Entry<String, String> entry : namedHQLQueries.entrySet())
        {
            try
            {
                tempLateMap.put(entry.getKey(), new Template(entry.getKey(), new StringReader(entry.getValue()),
                    configuration));
            }
            catch (IOException e)
            {
                LOGGER.error("HQL配置文件加载异常：sqlname:" + entry.getKey() + "--加载异常，请检查语法");
                e.printStackTrace();
            }
        }
        for (Entry<String, String> entry : namedSQLQueries.entrySet())
        {
            try
            {
                tempLateMap.put(entry.getKey(), new Template(entry.getKey(), new StringReader(entry.getValue()),
                    configuration));
            }
            catch (IOException e)
            {
                LOGGER.error("SQL配置文件加载异常：sqlname:" + entry.getKey() + "--加载异常，请检查语法");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 获取指定sql
     *
     * @param <T>
     * @throws IOException
     * @throws TemplateException
     */
    public <T> String getSqlLabel(String label, T params)
    {
        // 将bean转换为map
        Map<String, Object> paramMap = ObjUtils.beanToMap(params);
        StringWriter stringWriter = new StringWriter();
        Template template = tempLateMap.get(label);
        try
        {
            template.process(paramMap, stringWriter);
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

    /**
     * 获取指定sql
     *
     * @param <T>
     * @throws IOException
     * @throws TemplateException
     */
    public <T> String getSqlLabel(String label)
    {
        // 将bean转换为map
        StringWriter stringWriter = new StringWriter();
        Template template = tempLateMap.get(label);
        try
        {
            template.process(null, stringWriter);
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }


    @Override
    public void afterPropertiesSet()
        throws Exception
    {
        init();
    }
    
}