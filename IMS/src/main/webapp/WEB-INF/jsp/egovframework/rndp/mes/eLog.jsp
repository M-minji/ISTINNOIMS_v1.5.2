<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />

<style>
lf_tbl_list{background:#fff; display:inline-block; width:100%;margin:0.2em 0em; table-layout: auto;}
.lf_tbl_list>table{width:100%; border-top:2px solid #666;border-bottom: 1px solid #c7c4c4; table-layout: fixed !important; }
.lf_tbl_list>table>thead>tr>th {color:#000; border-right: 1px solid #c7c4c4;font-size:11pt;height:40px;text-align: center;background-color:#e5ecef;}
.lf_tbl_list>table>thead>tr {border-bottom: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;border-right: 1px solid #c7c4c4;}
.lf_tbl_list>table>tbody>tr {border-bottom: 1px solid #c7c4c4;border-left:1px solid #c7c4c4;border-right: 1px solid #c7c4c4;}
.lf_tbl_list>table>tbody>tr:hover {background-color:#f1f1f1; box-shadow:0px 7px 20px 0px #d0d0d0; border-left:2px solid #383636;}

.lf_tbl_list>table>tbody>tr>td{padding:0em 0.2em;font-size:11pt;text-align:center;height:40px;border-right: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;}
.lf_tbl_list>table>tbody>tr>td>a{text-decoration:underline;}
.lf_tbl_list>table>tfoot>tr>th {color:#000; border-right: 1px solid #c7c4c4;font-size:10;height:40px;text-align: center;background-color:#e5ecef;}
.lf_tbl_list>table>tfoot>tr {border-bottom: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;border-right: 1px solid #c7c4c4;}
.lf_tbl_list>table>tfoot>tr>td{padding:0em 0.2em;font-size:11pt;text-align:center;height:40px;border-right: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;}

.lf_tbl_list>div{background:#fff; display:inline-block; width:100%;margin:0.2em 0em;  }
.lf_tbl_list>div>table{width:100%; border-top:2px solid #666;border-bottom: 1px solid #c7c4c4; table-layout: fixed;}
.lf_tbl_list>div>table>thead>tr>th {color:#000; border-right: 1px solid #c7c4c4;font-size:11pt;width:160px !important; height:40px;text-align: center;background-color:#e5ecef;}
.lf_tbl_list>div>table>thead>tr {border-bottom: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;border-right: 1px solid #c7c4c4;}
.lf_tbl_list>div>table>tbody>tr {border-bottom: 1px solid #c7c4c4;border-left:1px solid #c7c4c4;border-right: 1px solid #c7c4c4;}
.lf_tbl_list>div>table>tbody>tr:hover {background-color:#f1f1f1; box-shadow:0px 7px 20px 0px #d0d0d0; border-left:2px solid #383636;}

.lf_tbl_list>div>table>tbody>tr>td{padding:0em 0.2em;font-size:11pt;text-align:center;height:40px;border-right: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;}
.lf_tbl_list>div>table>tbody>tr>td>a{text-decoration:underline;}
.lf_tbl_list>div>table>tfoot>tr>th {color:#000; border-right: 1px solid #c7c4c4;font-size:10;height:40px;text-align: center;background-color:#e5ecef;}
.lf_tbl_list>div>table>tfoot>tr {border-bottom: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;border-right: 1px solid #c7c4c4;}
.lf_tbl_list>div>table>tfoot>tr>td{padding:0em 0.2em;font-size:11pt;text-align:center;height:40px;border-right: 1px solid #c7c4c4;border-left: 1px solid #c7c4c4;}

.left-box {
/*   background: red; */
  float: left;
  width: 25%;
}
.right-box {
/*   background: blue; */
  float: right;
  width: 75%;
  max-width: 1150px; /* 최대 높이 설정 */
}

.highcharts-figure, .highcharts-data-table table {
    min-width: 320px; 
    max-width: 800px;
    margin: 1em auto;
}

.highcharts-data-table table {
    font-family: Verdana, sans-serif;
    border-collapse: collapse;
    border: 1px solid #EBEBEB;
    margin: 10px auto;
    text-align: center;
    width: 100%;
    max-width: 500px;
}
.highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;
}
.highcharts-data-table th {
    font-weight: 600;
    padding: 0.5em;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
    padding: 0.5em;
}
.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
}
.highcharts-data-table tr:hover {
    background: #f1f7ff;
}


input[type="number"] {
    min-width: 50px;
}
</style>

<script type="text/javascript">
$(document).ready(function(){  
	eDivCheck();
	datepickerSet('eTopStartDate','eTopEndDate');
});
 
function eDivCheck(){
	var eNowPageNum = Number("${staffVo.ePageGubun}");
	if(eNowPageNum == 1){
		document.getElementById("div1").style.display = "block";
		document.getElementById("div2").style.display = "none";
		document.getElementById("div3").style.display = "none";
	}
	if(eNowPageNum == 2){
		document.getElementById("div1").style.display = "none";
		document.getElementById("div2").style.display = "block";
		document.getElementById("div3").style.display = "none";
	}
	if(eNowPageNum == 3){
		document.getElementById("div1").style.display = "none";
		document.getElementById("div2").style.display = "none";
		document.getElementById("div3").style.display = "block";
	}
	 
}
function eNowPage(){
	document.frm.submit();
}

function setStartDate(d) {
    var settingDate = new Date();
    if(d == '7'){
        settingDate.setDate(settingDate.getDate()-7);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else if(d == '1'){
        settingDate.setMonth(settingDate.getMonth()-1);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else{
        settingDate.setMonth(settingDate.getMonth()-3);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }
}

// 현재날짜
function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}

// $(function() { 
// 	var cntStaffSumTmp = new Array();
// 	var eLogMenuList = new Array(); 
// 	var eLogMenuCntList  = [];
// 	var eLogMenuNameList = []; 


// 			<c:forEach items="${eMenuLog_StaffSum}" var="eMenuLog">
// 			cntStaffSumTmp.push({
// 			  "name": "${eMenuLog.kStaffName}"
// 			 ,"y": Number(${eMenuLog.eLogSum})
// 			        });
// 			</c:forEach>
// 	        Highcharts.chart('pieContainer', {
// 	            chart: {
// 	                plotBackgroundColor: null,
// 	                plotBorderWidth: null,
// 	                plotShadow: false,
// 	                type: 'pie'
// 	            },
// 	            title: {
// 	                text: '사용자  사용현황(메뉴전체)'
// 	            },
// 	            tooltip: {
// 	                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
// 	            },
// 	            accessibility: {
// 	                point: {
// 	                    valueSuffix: '%'
// 	                }
// 	            },
// 	            plotOptions: {
// 	                pie: {
// 	                    allowPointSelect: true,
// 	                    cursor: 'pointer',
// 	                    dataLabels: {
// 	                        enabled: false
// 	                    },
// 	                    showInLegend: true
// 	                }
// 	            },
// 	            series:[{
	            	
// 	           	 name: '사용률',
// 	             colorByPoint: true,
// 	             data: cntStaffSumTmp
// 	            }]

// 	        }); 
// 	});
</script>

<form id="frm" name="frm" method="post" action="/mes/eLog.do"  >
<input   type="hidden"      id="kStaffId"   name="kStaffId" value="${staffVo.kStaffId}">
	<div class="content_top">
		<div class="content_tit">
			<h2>접속 통계</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
		          		<span>조회 구분</span>
						<select name="ePageGubun" id="ePageGubun" onchange="eNowPage()">
							<option value="1" <c:if test="${staffVo.ePageGubun eq '1'}">selected</c:if>>사용자기준</option>
							<option value="2" <c:if test="${staffVo.ePageGubun eq '2'}">selected</c:if>>메뉴기준</option>
				      	</select>
				    </li>
				    <li>
				    	<span>기간</span>
				    	<div class="date">
				    		<input type="text" name="eTopStartDate" id="eTopStartDate" value="${staffVo.eTopStartDate}"  readonly/>
	         			 	-<input type="text" name="eTopEndDate" id="eTopEndDate" value="${staffVo.eTopEndDate}"  readonly/>
				    	</div>
	    			</li>
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn bg" onclick="eNowPage()">검색</button>
			</div>
		</div>
	</div>

    <!--  
    <div class='left-box' id="pieContainer"> </div>
	<div class='right-box' id="containerGraph"></div>
	 -->
	
	<div id="div1" style="display: none;" >
		<div class="normal_table">
			<table>
	   		  <thead>
	   		  	<tr> 
	   		  		<th >접속일</th>
	   		  		<c:forEach var="idList" items="${wIdText}" varStatus="st">
	   		  		<th >${idList.kStaffId}(${idList.kStaffName})</th>
	   		  		</c:forEach>
	   		  		<th>합계</th>
	  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="dateList" items="${dateList}" varStatus="status1">
	  		  			<tr> 
		   		  		<td>
							<c:out value="${dateList}" />
						</td>
						<c:set var="tTotalSum" value="0"/>
						<c:set var="tTotalCheck" value="0"/>
						<c:forEach var="idtext" items="${wIdText}" varStatus="nts">
						<td>
							<c:forEach var="idList2" items="${wIdList}" varStatus="nt">
								<c:if test="${dateList eq idList2.eLogData}">
										<c:if test="${idtext.kStaffKey eq idList2.kStaffKey}">
							   		  			 ${idList2.eLogCnt} 
								   		  		<c:set var="tTotalSum" value="${idList2.eLogSum}"/>
								   		  		<c:set var="tTotalCheck" value="${tTotalCheck+1}"/>
					   		  			</c:if>
					   		  		 
				   		  		</c:if>
			   		  		</c:forEach>
						
						</td>
			   		  			
		   		  		</c:forEach>
		   		  		<td>${tTotalSum}</td>
		   		  		<c:if test="${tTotalCheck ne 0}">
		   		  			<c:set var="tTotalSum" value="0"/>
		   		  		</c:if>
					</tr>
	  		  		</c:forEach>
	   		  </tbody>
	<!-- 			   		  <tfoot> -->
	<!-- 				   		  <tr> -->
	<!-- 					   		  <th> -->
	<!-- 					   			  합계 -->
	<!-- 					   		  </th> -->
	<!-- 				   		  </tr> -->
	<!-- 			   		  </tfoot> -->
	  			</table>
		</div>
	</div>
	<div id="div2"  style="display: none;">
		<div class="normal_table">
			<table>
	   		  <thead>
	   		  	<tr> 
	   		  		<th>접속일</th>
	   		  		<c:forEach var="idList" items="${wMenuList}" varStatus="st">
	   		  		<th>${idList.kMenuName}</th>
	   		  		</c:forEach>
	   		  		<th>합계</th>
	  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="dateList" items="${dateList}" varStatus="status1">
	  		  		<tr> 
		   		  		<td>
							<c:out value="${dateList}" />
						</td>
						<c:set var="tTotalSum2" value="0"/>
						<c:set var="tTotalCheck2" value="0"/>
						
							<c:forEach var="idList3" items="${wMenuList}" varStatus="nt">
								
									<c:if test="${dateList eq idList3.eLogData}">
					   		  			<td>${idList3.eLogCnt}</td>
					   		  		<c:set var="tTotalSum2" value="${idList3.eLogSum}"/>
					   		  		<c:set var="tTotalCheck2" value="${tTotalCheck2+1}"/>
					   		  		</c:if>
					   		  		<c:if test="${dateList ne idList3.eLogData}">
					   		  		<td></td>
					   		  		</c:if>
		   		  		</c:forEach>
		   		  		<td>${tTotalSum2}</td>
		   		  		<c:if test="${tTotalCheck2 ne 0}">
		   		  			<c:set var="tTotalSum" value="0"/>
		   		  		</c:if>
					</tr>
	  		  	</c:forEach>
	   		  </tbody>
	  		</table>
		</div>
	</div>
	<div id="div3" class="normal_table"  style="display: none;">
   		<table>
   		</table>
	</div>
	<c:forEach var="menulist" items="${eALLofMenuSumList}" varStatus="k">
		<input type="hidden" id="eLogSum_${k.index}" name="eLogSum" value="${menulist.eLogSum}">
		<input type="hidden" id="kMenuName_${k.index}" name="kMenuName" value="${menulist.kMenuName}">
	</c:forEach>
</form>

