package com.mapbar.platform.display.repository;

import com.mapbar.platform.display.entity.HyCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by jiangcm on 2016-10-25 14:56:05
 */
@Repository
public interface HyCarRepository extends JpaRepository<HyCarEntity, String>, JpaSpecificationExecutor<HyCarEntity> {

    public List<HyCarEntity> findByStructureNumAndCarTeamId(String vin, String carTeamId);

    public List<HyCarEntity> findByChassisNum(String chassisNum);

    public HyCarEntity findByCarId(Long carId);

}
