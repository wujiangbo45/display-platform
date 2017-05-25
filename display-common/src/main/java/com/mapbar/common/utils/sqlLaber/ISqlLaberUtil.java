package com.mapbar.common.utils.sqlLaber;

import java.io.IOException;

/**
 * 动态sql/hql语句组装器
 * 
 * @author zhangy
 *
 */
public interface ISqlLaberUtil
{
    
    /**
     * 初始化
     * 
     * @throws IOException
     */
    public void init()
        throws Exception;
}