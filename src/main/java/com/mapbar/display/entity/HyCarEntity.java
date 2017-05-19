package com.mapbar.display.entity;// default package

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * jiangcm 2016/11/18
 */
@Entity
@Table(name = "hy_car",  uniqueConstraints = @UniqueConstraint(columnNames = "CHASSIS_NUM"))
public class HyCarEntity implements java.io.Serializable {

	// Fields

	private Long carId;
	private Integer districtId;
	private String carCph;
	private Integer carColor;
	private Long carTerminal;
	private Long carTeamId;
	private Integer carState;
	private String carPw;
	private String carAccountName;
	private String carAutoNumber;
	private Long carDate;
	private String carPlace;
	private String carCompany;
	private Integer delFlag;
	private Integer carType;
	private Integer carTrade;
	private Integer carServiceStop;
	private Long serviceBegin;
	private Long serviceEnd;
	private Long nettingTime;
	private Long nettingLog;
	private Long nettingLat;
	private String chassisNum;
	private String structureNum;
	private String oilCapacity;
	private Integer lockStauts;
	private Long carTerminalId;
	private Integer autoFlag;
	private Integer tamperStatue;
	private String operateCommon;
	private String operateUser;
	private Long operateDate;
	private Integer tamperNoticeStatus;
	private Long offlineTime;
	private Long removalTime;
	private Long registerTime;
	private String operateIp;
	private Long carFkdate;
	private Integer batteryType;
	private Integer batteryBatches;
	private Long warehouseLog;
	private Long warehouseLat;
	private Long warehouseTime;
	private String carModelCode;
	private Long onlineTime;
	private String carModel;
	private String orderNumber;
	private Timestamp syncTime;

	private String vfactory;

	// Constructors

	/** default constructor */
	public HyCarEntity() {
	}

	/** minimal constructor */
	public HyCarEntity(Integer districtId) {
		this.districtId = districtId;
	}

	/** full constructor */
	public HyCarEntity(Integer districtId, String carCph, Integer carColor,
                       Long carTerminal, Long carTeamId, Integer carState, String carPw,
                       String carAccountName, String carAutoNumber, Long carDate,
                       String carPlace, String carCompany, Integer delFlag,
                       Integer carType, Integer carTrade, Integer carServiceStop,
                       Long serviceBegin, Long serviceEnd, Long nettingTime,
                       Long nettingLog, Long nettingLat, String chassisNum,
                       String structureNum, String oilCapacity, Integer lockStauts,
                       Long carTerminalId, Integer autoFlag, Integer tamperStatue,
                       String operateCommon, String operateUser, Long operateDate,
                       Integer tamperNoticeStatus, Long offlineTime, Long removalTime,
                       Long registerTime, String operateIp, Long carFkdate,
                       Integer batteryType, Integer batteryBatches, Long warehouseLog,
                       Long warehouseLat, Long warehouseTime, String carModelCode,
                       Long onlineTime, String carModel, String orderNumber, Timestamp syncTime,
                       String vfactory) {
		this.districtId = districtId;
		this.carCph = carCph;
		this.carColor = carColor;
		this.carTerminal = carTerminal;
		this.carTeamId = carTeamId;
		this.carState = carState;
		this.carPw = carPw;
		this.carAccountName = carAccountName;
		this.carAutoNumber = carAutoNumber;
		this.carDate = carDate;
		this.carPlace = carPlace;
		this.carCompany = carCompany;
		this.delFlag = delFlag;
		this.carType = carType;
		this.carTrade = carTrade;
		this.carServiceStop = carServiceStop;
		this.serviceBegin = serviceBegin;
		this.serviceEnd = serviceEnd;
		this.nettingTime = nettingTime;
		this.nettingLog = nettingLog;
		this.nettingLat = nettingLat;
		this.chassisNum = chassisNum;
		this.structureNum = structureNum;
		this.oilCapacity = oilCapacity;
		this.lockStauts = lockStauts;
		this.carTerminalId = carTerminalId;
		this.autoFlag = autoFlag;
		this.tamperStatue = tamperStatue;
		this.operateCommon = operateCommon;
		this.operateUser = operateUser;
		this.operateDate = operateDate;
		this.tamperNoticeStatus = tamperNoticeStatus;
		this.offlineTime = offlineTime;
		this.removalTime = removalTime;
		this.registerTime = registerTime;
		this.operateIp = operateIp;
		this.carFkdate = carFkdate;
		this.batteryType = batteryType;
		this.batteryBatches = batteryBatches;
		this.warehouseLog = warehouseLog;
		this.warehouseLat = warehouseLat;
		this.warehouseTime = warehouseTime;
		this.carModelCode = carModelCode;
		this.onlineTime = onlineTime;
		this.carModel = carModel;
		this.orderNumber = orderNumber;
		this.syncTime = syncTime;
		this.vfactory = vfactory;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CAR_ID", unique = true, nullable = false)
	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	@Column(name = "DISTRICT_ID", nullable = false)
	public Integer getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	@Column(name = "CAR_CPH", length = 50)
	public String getCarCph() {
		return this.carCph;
	}

	public void setCarCph(String carCph) {
		this.carCph = carCph;
	}

	@Column(name = "CAR_COLOR")
	public Integer getCarColor() {
		return this.carColor;
	}

	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	@Column(name = "CAR_TERMINAL")
	public Long getCarTerminal() {
		return this.carTerminal;
	}

	public void setCarTerminal(Long carTerminal) {
		this.carTerminal = carTerminal;
	}

	@Column(name = "CAR_TEAM_ID")
	public Long getCarTeamId() {
		return this.carTeamId;
	}

	public void setCarTeamId(Long carTeamId) {
		this.carTeamId = carTeamId;
	}

	@Column(name = "CAR_STATE")
	public Integer getCarState() {
		return this.carState;
	}

	public void setCarState(Integer carState) {
		this.carState = carState;
	}

	@Column(name = "CAR_PW", length = 50)
	public String getCarPw() {
		return this.carPw;
	}

	public void setCarPw(String carPw) {
		this.carPw = carPw;
	}

	@Column(name = "CAR_ACCOUNT_NAME", length = 50)
	public String getCarAccountName() {
		return this.carAccountName;
	}

	public void setCarAccountName(String carAccountName) {
		this.carAccountName = carAccountName;
	}

	@Column(name = "CAR_AUTO_NUMBER", length = 50)
	public String getCarAutoNumber() {
		return this.carAutoNumber;
	}

	public void setCarAutoNumber(String carAutoNumber) {
		this.carAutoNumber = carAutoNumber;
	}

	@Column(name = "CAR_DATE")
	public Long getCarDate() {
		return this.carDate;
	}

	public void setCarDate(Long carDate) {
		this.carDate = carDate;
	}

	@Column(name = "CAR_PLACE", length = 200)
	public String getCarPlace() {
		return this.carPlace;
	}

	public void setCarPlace(String carPlace) {
		this.carPlace = carPlace;
	}

	@Column(name = "CAR_COMPANY", length = 200)
	public String getCarCompany() {
		return this.carCompany;
	}

	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}

	@Column(name = "DEL_FLAG")
	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "CAR_TYPE")
	public Integer getCarType() {
		return this.carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	@Column(name = "CAR_TRADE")
	public Integer getCarTrade() {
		return this.carTrade;
	}

	public void setCarTrade(Integer carTrade) {
		this.carTrade = carTrade;
	}

	@Column(name = "CAR_SERVICE_STOP")
	public Integer getCarServiceStop() {
		return this.carServiceStop;
	}

	public void setCarServiceStop(Integer carServiceStop) {
		this.carServiceStop = carServiceStop;
	}

	@Column(name = "SERVICE_BEGIN")
	public Long getServiceBegin() {
		return this.serviceBegin;
	}

	public void setServiceBegin(Long serviceBegin) {
		this.serviceBegin = serviceBegin;
	}

	@Column(name = "SERVICE_END")
	public Long getServiceEnd() {
		return this.serviceEnd;
	}

	public void setServiceEnd(Long serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	@Column(name = "NETTING_TIME")
	public Long getNettingTime() {
		return this.nettingTime;
	}

	public void setNettingTime(Long nettingTime) {
		this.nettingTime = nettingTime;
	}

	@Column(name = "NETTING_LOG")
	public Long getNettingLog() {
		return this.nettingLog;
	}

	public void setNettingLog(Long nettingLog) {
		this.nettingLog = nettingLog;
	}

	@Column(name = "NETTING_LAT")
	public Long getNettingLat() {
		return this.nettingLat;
	}

	public void setNettingLat(Long nettingLat) {
		this.nettingLat = nettingLat;
	}

	@Column(name = "CHASSIS_NUM", unique = true, length = 100)
	public String getChassisNum() {
		return this.chassisNum;
	}

	public void setChassisNum(String chassisNum) {
		this.chassisNum = chassisNum;
	}

	@Column(name = "STRUCTURE_NUM", length = 100)
	public String getStructureNum() {
		return this.structureNum;
	}

	public void setStructureNum(String structureNum) {
		this.structureNum = structureNum;
	}

	@Column(name = "OIL_CAPACITY", length = 100)
	public String getOilCapacity() {
		return this.oilCapacity;
	}

	public void setOilCapacity(String oilCapacity) {
		this.oilCapacity = oilCapacity;
	}

	@Column(name = "LOCK_STAUTS")
	public Integer getLockStauts() {
		return this.lockStauts;
	}

	public void setLockStauts(Integer lockStauts) {
		this.lockStauts = lockStauts;
	}

	@Column(name = "CAR_TERMINAL_ID")
	public Long getCarTerminalId() {
		return this.carTerminalId;
	}

	public void setCarTerminalId(Long carTerminalId) {
		this.carTerminalId = carTerminalId;
	}

	@Column(name = "AUTO_FLAG")
	public Integer getAutoFlag() {
		return this.autoFlag;
	}

	public void setAutoFlag(Integer autoFlag) {
		this.autoFlag = autoFlag;
	}

	@Column(name = "TAMPER_STATUE")
	public Integer getTamperStatue() {
		return this.tamperStatue;
	}

	public void setTamperStatue(Integer tamperStatue) {
		this.tamperStatue = tamperStatue;
	}

	@Column(name = "OPERATE_COMMON", length = 200)
	public String getOperateCommon() {
		return this.operateCommon;
	}

	public void setOperateCommon(String operateCommon) {
		this.operateCommon = operateCommon;
	}

	@Column(name = "OPERATE_USER", length = 200)
	public String getOperateUser() {
		return this.operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	@Column(name = "OPERATE_DATE")
	public Long getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(Long operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "tamper_notice_status")
	public Integer getTamperNoticeStatus() {
		return this.tamperNoticeStatus;
	}

	public void setTamperNoticeStatus(Integer tamperNoticeStatus) {
		this.tamperNoticeStatus = tamperNoticeStatus;
	}

	@Column(name = "OFFLINE_TIME")
	public Long getOfflineTime() {
		return this.offlineTime;
	}

	public void setOfflineTime(Long offlineTime) {
		this.offlineTime = offlineTime;
	}

	@Column(name = "REMOVAL_TIME")
	public Long getRemovalTime() {
		return this.removalTime;
	}

	public void setRemovalTime(Long removalTime) {
		this.removalTime = removalTime;
	}

	@Column(name = "REGISTER_TIME")
	public Long getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "OPERATE_IP", length = 100)
	public String getOperateIp() {
		return this.operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	@Column(name = "CAR_FKDATE")
	public Long getCarFkdate() {
		return this.carFkdate;
	}

	public void setCarFkdate(Long carFkdate) {
		this.carFkdate = carFkdate;
	}

	@Column(name = "BATTERY_TYPE")
	public Integer getBatteryType() {
		return this.batteryType;
	}

	public void setBatteryType(Integer batteryType) {
		this.batteryType = batteryType;
	}

	@Column(name = "BATTERY_BATCHES")
	public Integer getBatteryBatches() {
		return this.batteryBatches;
	}

	public void setBatteryBatches(Integer batteryBatches) {
		this.batteryBatches = batteryBatches;
	}

	@Column(name = "WAREHOUSE_LOG")
	public Long getWarehouseLog() {
		return this.warehouseLog;
	}

	public void setWarehouseLog(Long warehouseLog) {
		this.warehouseLog = warehouseLog;
	}

	@Column(name = "WAREHOUSE_LAT")
	public Long getWarehouseLat() {
		return this.warehouseLat;
	}

	public void setWarehouseLat(Long warehouseLat) {
		this.warehouseLat = warehouseLat;
	}

	@Column(name = "WAREHOUSE_TIME")
	public Long getWarehouseTime() {
		return this.warehouseTime;
	}

	public void setWarehouseTime(Long warehouseTime) {
		this.warehouseTime = warehouseTime;
	}

	@Column(name = "car_model_code", length = 20)
	public String getCarModelCode() {
		return this.carModelCode;
	}

	public void setCarModelCode(String carModelCode) {
		this.carModelCode = carModelCode;
	}

	@Column(name = "online_time")
	public Long getOnlineTime() {
		return this.onlineTime;
	}

	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}

	@Column(name = "CAR_MODEL", length = 100)
	public String getCarModel() {
		return this.carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	@Column(name = "order_number", length = 60)
	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name = "sync_time")
	public Timestamp getSyncTime () {
		return this.syncTime;
	}

	public void setSyncTime(Timestamp syncTime) {
		this.syncTime = syncTime;
	}

	@Column(name = "vfactory", nullable = false)
	public String getVfactory() {
		return vfactory;
	}

	public void setVfactory(String vfactory) {
		this.vfactory = vfactory;
	}
}