package com.mapbar.display.service.base;


import com.mapbar.display.common.CommonDao;
import com.mapbar.display.util.sqlLaber.SqlLaberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
