package com.mapbar.display.common;

import com.mapbar.display.result.PagingInfo;
import com.mapbar.display.util.JpaUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.*;

/**
 * 通用dao
 *
 * Created by zhangy on 2016/04/08.
 */
@Repository
@Transactional
public class CommonDao<T> extends BaseDao
{
    /**
     * 删除
     */
    public void delete(T t)
        throws RuntimeException
    {
        if (null == t)
        {
            throw new RuntimeException("请求删除的对象为空!");
        }
        try
        {
            if (em.contains(t))
            {
                em.remove(t);
            }
            else
            {
                Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t);
                em.remove(em.find(t.getClass(), id));
            }

        }
        catch (Exception e)
        {
            throw new RuntimeException("删除对象时出错!");
        }
    }

    /**
     * 批量删除 传入集合
     */
    public void batchDelete(Collection<T> ts)
    {
        for (T t : ts)
        {
            delete(t);
        }
    }

    /**
     * sql 取得总数
     *
     * @param sql
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public long getSqlCount(String sql)
    {
        Object O = em.createNativeQuery(JpaUtil.getStrCountSql(sql)).getSingleResult();
        return Long.valueOf(O.toString());
    }

    /**
     * hql 取得总数
     *
     * @param hql
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public long getHqlCount(String hql)
    {
        Object O = em.createQuery(JpaUtil.getStrCountSql(hql)).getSingleResult();
        return Long.valueOf(O.toString());
    }

    /**
     * 保存
     */
    public T save(T t)
    {
        try
        {
            em.persist(t);

            return t;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("更新新建失败：" + e.getMessage());
        }
    }

    /**
     * 批量保存
     */
    @Transactional
    public void batchSave(Collection<T> ts, int count)
    {

        Iterator<T> list = ts.iterator();
        for (int index = 0; list.hasNext(); index++)
        {
            em.persist(list.next());
            if (index % count == 0) {
                em.flush();
                em.clear();
            }
        }
    }

    /**
     * 若数据库中已有此记录，置为托管状态并刷新实体信息
     */
    public T update(T t)
        throws RuntimeException
    {
        try
        {
            t = em.contains(t) ? t : em.merge(t);

        }
        catch (Exception e)
        {
            throw new RuntimeException("更新失败！");
        }
        return t;
    }

    /**
     * 批次更新
     */
    public Collection<T> batchUpdate(Collection<T> ts)
    {
        Collection<T> collection = new HashSet<T>();
        for (T t : ts)
        {
            collection.add(update(t));
        }
        return collection;
    }

    /**
     * 查询一条数
     *
     * @param t
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public T findOne(T t)
    {
        Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t);
        return (T)em.find(t.getClass(), id);

    }

    /**
     * sql查询不带分页
     *
     * @param sqlId
     * @param entityClass
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<T> sqlFind(String sqlId, T entityClass)
    {
        String sql = sqlLaberUtil.getSqlLabel(sqlId, entityClass);
        Query querySql = em.createNativeQuery(sql, entityClass.getClass());

        return querySql.getResultList();
    }


    /**
     * sql查询对象（自定义返回实体，用于多表查询）
     *
     * @param sqlId
     * @param paramEntity
     * @param pojoClass  返回类型
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public Object sqlFindObject(String sqlId, T paramEntity, Class<?> pojoClass)
    {
        String sql = sqlLaberUtil.getSqlLabel(sqlId, paramEntity);
        Query query = em.createNativeQuery(sql);
        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(pojoClass));
        return sqlQuery.uniqueResult();
    }

    /**
     * sql查询字段（返回单个字段）
     *
     * @param sqlId sqlId
     * @param paramEntity paramEntity
     * @return List  返回类型
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List sqlFindField(String sqlId, T paramEntity)
    {
        String sql = sqlLaberUtil.getSqlLabel(sqlId, paramEntity);
        Query query = em.createNativeQuery(sql);
        List rows = query.getResultList();
        return rows;
    }

    /**
     * sql查询不带分页（自定义返回实体，用于多表查询）
     *
     * @param sqlId
     * @param paramEntity
     * @param pojoClass  返回类型
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<T> sqlFind(String sqlId, T paramEntity, Class<?> pojoClass)
    {
        String sql = sqlLaberUtil.getSqlLabel(sqlId, paramEntity);
        Query query = em.createNativeQuery(sql);
        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(pojoClass));
        return sqlQuery.list();
    }

    /**
     * sql查询不带分页（自定义返回实体，用于多表查询）
     *
     * @param pojoClass  返回类型
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<T> bySqlFind(String sql,Class<?> pojoClass)
    {
        Query query = em.createNativeQuery(sql);
        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(pojoClass));
        return sqlQuery.list();
    }


    /**
     * sql查询不带分页（自定义返回实体，用于多表查询）
     *
     * @param sql  返回类型
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<Map> bySqlFind(String sql)
    {
        List<Map> resultMapList = new ArrayList<Map>();
        Query query = em.createNativeQuery(sql);
        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List rows = query.getResultList();
        for (Object obj : rows) {
            Map row = (Map) obj;
            resultMapList.add(row);
        }
        return resultMapList;
    }

    /**
     * 更新
     * @param sqlId
     * @param paramEntity
     * @return
     */
    @Transactional
    public int updateBySql(String sqlId, T paramEntity)
    {
        String sql = sqlLaberUtil.getSqlLabel(sqlId, paramEntity);
        Query query = em.createNativeQuery(sql);
//        query.executeUpdate();
        return  Integer.valueOf(query.getSingleResult().toString());
    }

    /**
     * 语句更新
     *
     * @param sql  入参SQL
     * @return
     */
    @Transactional
    public int updateBySql(String sql)
    {
        Query query = em.createNativeQuery(sql);
        return query.executeUpdate();
    }

    /**
     * sql查询不带分页（自定义返回实体，用于多表查询）
     *
     * @param sqlId
     * @param pojoClass 返回类型
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<T> sqlFind(String sqlId, Class<?> pojoClass) {
        String sql = sqlLaberUtil.getSqlLabel(sqlId);
        Query query = em.createNativeQuery(sql);
        SQLQuery sqlQuery = query.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(pojoClass));
        return sqlQuery.list();
    }

    /**
     * hql查询不带分页
     *
     * @param hqlId
     * @param entityClass
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<T> hqlFind(String hqlId, T entityClass)
    {
        String sql = sqlLaberUtil.getSqlLabel(hqlId, entityClass);
        Query querySql = em.createQuery(sql, entityClass.getClass());
        return querySql.getResultList();
    }

    /**
     * sql查询带分页
     *
     * @param sqlId
     * @param entityClass
     * @param pageIndex 页数
     * @param pageSize 每页显示条数
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingInfo<T> sqlPagelFind(String sqlId, T entityClass, int pageIndex, int pageSize)
    {
        PagingInfo<T> pageList = new PagingInfo<T>();

        String sql = sqlLaberUtil.getSqlLabel(sqlId, entityClass);
        Query querySql = em.createNativeQuery(sql, entityClass.getClass());

        // 设置分页
        JpaUtil.setQueryPage(querySql, pageIndex, pageSize);

        List<T> list = querySql.getResultList();
        pageList.setList(list);

        // 总页数
        long count = getSqlCount(sql);

        // 总条数
        pageList.setTotal(count);

        // 总页数
        pageList.setPage_total((count + pageSize - 1) / pageSize);
        return pageList;
    }

    /**
     * sql查询带分页（自定义返回实体，用于多表查询）
     *
     * @param sqlId
     * @param paramEntity
     * @param pageIndex 页数
     * @param pageSize 每页显示条数
     * @param pojoClass 返回类型
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingInfo<T> sqlPagelFind(String sqlId, T paramEntity, int pageIndex, int pageSize, Class<?> pojoClass)
    {
        PagingInfo<T> pageList = new PagingInfo<T>();

        String sql = sqlLaberUtil.getSqlLabel(sqlId, paramEntity);
        Query querySql = em.createNativeQuery(sql);
        // 设置分页
        JpaUtil.setQueryPage(querySql, pageIndex, pageSize);

        SQLQuery sqlQuery = querySql.unwrap(SQLQuery.class);
        sqlQuery.setResultTransformer(Transformers.aliasToBean(pojoClass));

        List<T> list = sqlQuery.list();
        pageList.setList(list);

        // 总页数
        long count = getSqlCount(sql);

        // 总条数
        pageList.setTotal(count);

        // 总页数
        pageList.setPage_total((count + pageSize - 1) / pageSize);
        return pageList;
    }

    /**
     * hql查询带分页
     *
     * @param hqlId
     * @param entityClass
     * @param pageIndex 页数
     * @param pageSize 每页显示条数
     * @return
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingInfo<T> hqlPagelFind(String hqlId, T entityClass, int pageIndex, int pageSize)
    {
        PagingInfo<T> pageList = new PagingInfo<T>();

        String sql = sqlLaberUtil.getSqlLabel(hqlId, entityClass);
        Query queryHql = em.createQuery(sql, entityClass.getClass());

        // 设置分页
        JpaUtil.setQueryPage(queryHql, pageIndex, pageSize);

        List<T> list = queryHql.getResultList();
        pageList.setList(list);

        // 总页数
        long count = getHqlCount(sql);

        // 总条数
        pageList.setTotal(count);

        // 总页数
        pageList.setPage_total((count + pageSize - 1) / pageSize);
        return pageList;
    }

    /**
     * 同步jpa容器和数据库
     */
    public void flush()
    {
        em.flush();
    }

    /**
     * 刷新缓存
     */
    public void clear()
    {
        em.clear();
    }

    /**
     * 同步jpa容器和数据库
     */
    public void commit()
    {
        em.getTransaction().commit();
    }

}
