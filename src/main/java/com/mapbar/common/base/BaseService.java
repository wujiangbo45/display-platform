package com.mapbar.common.base;


import com.mapbar.common.utils.sqlLaber.SqlLaberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by zhangy on 2015/12/31.
 */
@Service
public class BaseService
{
    protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    @PersistenceContext
    public EntityManager entityManager;
    
    @Autowired
    public SqlLaberUtil sqlLaberUtil;

    @Autowired
    public CommonDao dao;

}
