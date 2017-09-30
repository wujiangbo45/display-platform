package com.mapbar.display.dao;

import com.mapbar.display.dto.SelectWorkOrderStatus;
import com.mapbar.display.dto.WorkOrderGroupByStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderMapper {

    List<WorkOrderGroupByStatus> groupByWorkOrderByStatus();

    List<SelectWorkOrderStatus> selectGroupByWoType(@Param("type") int type);
}