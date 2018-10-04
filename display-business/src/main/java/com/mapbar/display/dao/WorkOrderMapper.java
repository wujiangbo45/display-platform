package com.mapbar.display.dao;

import com.mapbar.display.dto.SelectWorkOrderStatus;
import com.mapbar.display.dto.WorkOrderGroupByStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderMapper {

    List<WorkOrderGroupByStatus> groupByWorkOrderByStatus(@Param("userId")List<String> userId);

    List<SelectWorkOrderStatus> selectGroupByWoType(@Param("userId") List<String> userId, @Param("type") int type);

    List<WorkOrderGroupByStatus> groupByWorkOrderByStatusByDay(@Param("userId")List<String> userId, @Param("sDate")String sDay, @Param("eDate")String eDay);

    List<SelectWorkOrderStatus> selectGroupByWoTypeByDay(@Param("userId") List<String> userId, @Param("type") int type, @Param("sDate")String sDay, @Param("eDate")String eDay);

}