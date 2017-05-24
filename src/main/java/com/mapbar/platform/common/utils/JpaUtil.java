package com.mapbar.platform.common.utils;

import javax.persistence.Query;

/**
 * Created by zhangy on 2016/04/11.
 */
public class JpaUtil
{
    /**
     * 设置分页
     *
     * @param pageIndex 页数
     * @param pageSize 每页显示条数
     */
    public static void setQueryPage(Query querySql, int pageIndex, int pageSize)
    {
        if (querySql != null)
        {
            // 设置每页开始数据
            querySql.setFirstResult(pageIndex * pageSize);
            
            // 每页条数
            querySql.setMaxResults(pageSize);
        }
    }
    
    /**
     * 取得记录数的sql
     *
     * @param querySql
     * @return
     */
    public static String getStrCountSql(String querySql)
    {
        StringBuilder countSql = new StringBuilder();
        
        if (StringUtil.isNotEmpty(querySql))
        {

            countSql.append("select count(1) from ( ");
            countSql.append(querySql);
            countSql.append(") t");
//            if (querySql.toLowerCase().contains(JpaConstants.SQL_FROM_STR))
//            {
//                // 开始截取的位置
//                int begin = querySql.toLowerCase().indexOf(JpaConstants.SQL_FROM_STR);
//                // 结束的位置
//                int end = querySql.length();
//
//                countSql = JpaConstants.SQL_FROM_COUNT_STR + querySql.substring(begin, end);
//
//            }
        }
        return countSql.toString();
    }
}
