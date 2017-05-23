package com.mapbar.display.service;

import com.mapbar.display.aspect.LoggerAspect;
import com.mapbar.display.common.CommonDao;
import com.mapbar.display.dto.GetTotalMileageAndPackage;
import com.mapbar.display.service.base.BaseService;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wangjc on 2017/5/22.
 */
@Service
public class QueryVehicleTotalInfoService extends BaseService{
    private static final Logger logger = LoggerFactory.getLogger(QueryVehicleTotalInfoService.class);
    @Autowired
    public CommonDao dao;
    LoggerAspect loggerAspect;
    @Transactional
    public int getTotalVehicle(){
        int intTotal = 0;

        String countSql = sqlLaberUtil.getSqlLabel("carTotal");
        int count = Integer.parseInt(entityManager.createNativeQuery(countSql).getSingleResult().toString());
        intTotal = count;

        return intTotal;
    }
}
