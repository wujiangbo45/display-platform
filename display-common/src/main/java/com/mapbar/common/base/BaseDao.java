package com.mapbar.common.base;

import com.mapbar.common.utils.sqlLaber.SqlLaberUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDao
{
    @PersistenceContext
    protected EntityManager em;
    
    @Autowired
    public SqlLaberUtil sqlLaberUtil;
}