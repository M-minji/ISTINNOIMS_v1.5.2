package egovframework.rndp.mes.machine.service;

import egovframework.rndp.com.cmm.service.DefultVO;

public class MesMachineVO extends DefultVO{

	private String eMachineTime     = "";
	private String eMaKey     = "";
	private String eMachineTmp1     = "";
	private String eMachineNo		= "";  
	private String keyArray         = "";
	private String sumCount			="";
	private String cnt			="";   
	private String topStartDate = "";
	private String topEndDate = "";
	private String eMachineTmp2        = "";
	private String eMachineTmp3		= "";

	
	private String mDate            = "";
	private String devCnt1          = "";
	private String devCnt2          = "";
	private String devCnt3			= "";
	
	
	private int recordCountPerPage1 = 10;
	private String topStartDate1   					= ""; 
	private String topEndDate1    					= ""; 	
	
	
	private int recordCountPerPage2 = 10;
	private String topStartDate2   					= ""; 
	private String topEndDate2     					= ""; 	
	
	
	private String eGubunPage						= "";

	private String minCount;
	private String maxCount;
	private String finCount;
	private String processDate;

	//그래프용 필드명
	private String h09Cnt;
	private String h10Cnt;
	private String h11Cnt;
	private String h12Cnt;
	private String h13Cnt;
	private String h14Cnt;
	private String h15Cnt;
	private String h16Cnt;
	private String h17Cnt;
	private String h18Cnt;
	private String h19Cnt;
	private String h20nt;
	private String total;
	private String processHour;
	private String thour;
	private String yy;
	private String rpm;
	private String id;
	private String onoff;
	private String ecol;
	private String adGubun;
	private String ad1;
	private String ad2;
	private String year;
	private String month;
	private String day;
	private String mindate;
	private String maxdate;
	private String worktime;

	public String getEcol() {
		return ecol;
	}

	public String getAdGubun() {
		return adGubun;
	}

	public void setAdGubun(String adGubun) {
		this.adGubun = adGubun;
	}

	public void setEcol(String ecol) {
		this.ecol = ecol;
	}

	public String getMindate() {
		return mindate;
	}

	public void setMindate(String mindate) {
		this.mindate = mindate;
	}

	public String getMaxdate() {
		return maxdate;
	}

	public void setMaxdate(String maxdate) {
		this.maxdate = maxdate;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public String getAd1() {
		return ad1;
	}

	public void setAd1(String ad1) {
		this.ad1 = ad1;
	}

	public String getAd2() {
		return ad2;
	}

	public void setAd2(String ad2) {
		this.ad2 = ad2;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String geteMaKey() {
		return eMaKey;
	}

	public void seteMaKey(String eMaKey) {
		this.eMaKey = eMaKey;
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm;
	}

	public String getYy() {
		return yy;
	}

	public void setYy(String yy) {
		this.yy = yy;
	}

	public String getProcessHour() {
		return processHour;
	}

	public void setProcessHour(String processHour) {
		this.processHour = processHour;
	}

	public String getThour() {
		return thour;
	}

	public void setThour(String thour) {
		this.thour = thour;
	}

	public String getH09Cnt() {
		return h09Cnt;
	}

	public void setH09Cnt(String h09Cnt) {
		this.h09Cnt = h09Cnt;
	}

	public String getH10Cnt() {
		return h10Cnt;
	}

	public void setH10Cnt(String h10Cnt) {
		this.h10Cnt = h10Cnt;
	}

	public String getH11Cnt() {
		return h11Cnt;
	}

	public void setH11Cnt(String h11Cnt) {
		this.h11Cnt = h11Cnt;
	}

	public String getH12Cnt() {
		return h12Cnt;
	}

	public void setH12Cnt(String h12Cnt) {
		this.h12Cnt = h12Cnt;
	}

	public String getH13Cnt() {
		return h13Cnt;
	}

	public void setH13Cnt(String h13Cnt) {
		this.h13Cnt = h13Cnt;
	}

	public String getH14Cnt() {
		return h14Cnt;
	}

	public void setH14Cnt(String h14Cnt) {
		this.h14Cnt = h14Cnt;
	}

	public String getH15Cnt() {
		return h15Cnt;
	}

	public void setH15Cnt(String h15Cnt) {
		this.h15Cnt = h15Cnt;
	}

	public String getH16Cnt() {
		return h16Cnt;
	}

	public void setH16Cnt(String h16Cnt) {
		this.h16Cnt = h16Cnt;
	}

	public String getH17Cnt() {
		return h17Cnt;
	}

	public void setH17Cnt(String h17Cnt) {
		this.h17Cnt = h17Cnt;
	}

	public String getH18Cnt() {
		return h18Cnt;
	}

	public void setH18Cnt(String h18Cnt) {
		this.h18Cnt = h18Cnt;
	}

	public String getH19Cnt() {
		return h19Cnt;
	}

	public void setH19Cnt(String h19Cnt) {
		this.h19Cnt = h19Cnt;
	}

	public String getH20nt() {
		return h20nt;
	}

	public void setH20nt(String h20nt) {
		this.h20nt = h20nt;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getMinCount() {
		return minCount;
	}

	public void setMinCount(String minCount) {
		this.minCount = minCount;
	}

	public String getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}

	public String getFinCount() {
		return finCount;
	}

	public void setFinCount(String finCount) {
		this.finCount = finCount;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getTopEndDate() {
		return topEndDate;
	}
	public void setTopEndDate(String topEndDate) {
		this.topEndDate = topEndDate;
	}
	public String geteGubunPage() {
		return eGubunPage;
	}
	public void seteGubunPage(String eGubunPage) {
		this.eGubunPage = eGubunPage;
	}
	public int getRecordCountPerPage1() {
		return recordCountPerPage1;
	}
	public void setRecordCountPerPage1(int recordCountPerPage1) {
		this.recordCountPerPage1 = recordCountPerPage1;
	}
	public String getTopStartDate1() {
		return topStartDate1;
	}
	public void setTopStartDate1(String topStartDate1) {
		this.topStartDate1 = topStartDate1;
	}
	public String getTopEndDate1() {
		return topEndDate1;
	}
	public void setTopEndDate1(String topEndDate1) {
		this.topEndDate1 = topEndDate1;
	}
	public int getRecordCountPerPage2() {
		return recordCountPerPage2;
	}
	public void setRecordCountPerPage2(int recordCountPerPage2) {
		this.recordCountPerPage2 = recordCountPerPage2;
	}
	public String getTopStartDate2() {
		return topStartDate2;
	}
	public void setTopStartDate2(String topStartDate2) {
		this.topStartDate2 = topStartDate2;
	}
	public String getTopEndDate2() {
		return topEndDate2;
	}
	public void setTopEndDate2(String topEndDate2) {
		this.topEndDate2 = topEndDate2;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getDevCnt1() {
		return devCnt1;
	}
	public void setDevCnt1(String devCnt1) {
		this.devCnt1 = devCnt1;
	}
	public String getDevCnt2() {
		return devCnt2;
	}
	public void setDevCnt2(String devCnt2) {
		this.devCnt2 = devCnt2;
	}
	public String getDevCnt3() {
		return devCnt3;
	}
	public void setDevCnt3(String devCnt3) {
		this.devCnt3 = devCnt3;
	}
	public String geteMachineTmp2() {
		return eMachineTmp2;
	}
	public void seteMachineTmp2(String eMachineTmp2) {
		this.eMachineTmp2 = eMachineTmp2;
	}
	public String geteMachineTmp3() {
		return eMachineTmp3;
	}
	public void seteMachineTmp3(String eMachineTmp3) {
		this.eMachineTmp3 = eMachineTmp3;
	}
	public String getTopStartDate() {
		return topStartDate;
	}
	public void setTopStartDate(String topStartDate) {
		this.topStartDate = topStartDate;
	}
	public String geteMachineTime() {
		return eMachineTime;
	}
	public void seteMachineTime(String eMachineTime) {
		this.eMachineTime = eMachineTime;
	}
	public String geteMachineTmp1() {
		return eMachineTmp1;
	}
	public void seteMachineTmp1(String eMachineTmp1) {
		this.eMachineTmp1 = eMachineTmp1;
	}
	public String geteMachineNo() {
		return eMachineNo;
	}
	public void seteMachineNo(String eMachineNo) {
		this.eMachineNo = eMachineNo;
	}
	public String getKeyArray() {
		return keyArray;
	}
	public void setKeyArray(String keyArray) {
		this.keyArray = keyArray;
	}
	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	
}
