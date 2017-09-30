package com.mapbar.display.dao;

import com.mapbar.display.dto.WorkOrderGroupByStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderMapper {

    List<WorkOrderGroupByStatus> groupByWorkOrderByStatus();

}