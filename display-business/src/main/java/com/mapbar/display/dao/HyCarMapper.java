package com.mapbar.display.dao;

import com.mapbar.display.domain.HyCar;
import com.mapbar.display.dto.HyAccountDto;
import com.mapbar.display.dto.ServerStationInfo;
import com.mapbar.display.dto.ServerStationReq;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface HyCarMapper {
    int deleteByPrimaryKey(Long carId);

    int insert(HyCar record);

    int insertSelective(HyCar record);

    HyCar selectByPrimaryKey(Long carId);

    int updateByPrimaryKeySelective(HyCar record);

    int updateByPrimaryKey(HyCar record);

    HyAccountDto queryLoginInfoSql(String username);

    int carTotal();

    ServerStationInfo getFirstServiceStationInfo(String id);

    ServerStationInfo getSecondServiceStationInfo(String id);
}