package com.mapbar.display.common.base;

import com.mapbar.display.util.sqlLaber.SqlLaberUtil;
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