<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<meta http-equiv="refresh" content="900">
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<!-- <script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script> -->

<script src="/js/highchart/code/highcharts.js"></script>
<script src="/js/highchart/code/modules/exporting.js"></script>
<script src="/js/highchart/code/modules/export-data.js"></script>

<script type="text/javascript">
	$(document).ready(function(){	
		eDivCheck();
		datepickerSet('topStartDate', 'topEndDate');
	});	
	
	function eDivCheck(){
		var eNowPageNum = Number("${mesStatisticsVO.ePageGubun}");
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
	/* 페이징 */
	function fn_guestList(pageNo) {
		$('#mloader').show();
		document.frm.submit();
	}

	function showDiv(divId) {
	    // Hide all divs
	    document.getElementById('div1').style.display = 'none';
	    document.getElementById('div2').style.display = 'none';
	    document.getElementById('div3').style.display = 'none';
	    
	    // Show the selected div
	    document.getElementById(divId).style.display = 'block';
	}
</script>

<style>
	td{
		text-align:center;
	}
	
	.highcharts-figure,
	.highcharts-data-table table {
	    min-width: 310px;
	    max-width: 800px;
	    margin: 1em auto;
	}
	
	#container {
	    height: 400px;
	}
	
	.highcharts-data-table table {
	    font-family: Verdana, sans-serif;
	    border-collapse: collapse;
	    border: 1px solid #ebebeb;
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
	
	.highcharts-data-table td,
	.highcharts-data-table th,
	.highcharts-data-table caption {
	    padding: 0.5em;
	}
	
	.highcharts-data-table thead tr,
	.highcharts-data-table tr:nth-child(even) {
	    background: #f8f8f8;
	}
	
	.highcharts-data-table tr:hover {
	    background: #f1f7ff;
	}
	
	.graph-container {
	    display: flex;
	    justify-content: space-between;
	}
	
	.graph {
	    width: 45%; /* 그래프의 너비 조정 */
	    margin: 10px; /* 그래프 간격 조정 */
	}
</style>

<form id="frm" name="frm" method="post" action="/mes/statistics/kw_outputs_lf.do">
	
	<div class="content_top">
		<div class="content_tit">
			<h2>산출물 통계</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
		           		<span>사업기간기간 </span>
		           		<div class="date">
		           			<input type="text" class='inp_color' name="topStartDate" style="width:120px; text-align:center;" id="topStartDate" value="${mesStatisticsVO.topStartDate}" readonly/>
				       		-
				           	<input type="text" class='inp_color' name="topEndDate" style="width:120px; text-align:center;"  id="topEndDate"  value="${mesStatisticsVO.topEndDate}" readonly/>
		           		</div>
					</li>
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>
	
	<div class="graph-container">
	    <div id="containerGraph1" class="graph" style="cursor:pointer;" onclick="showDiv('div1')"></div>
<!--     	<div id="containerGraph3" class="graph" style="cursor:pointer;" onclick="showDiv('div3')"></div> -->
	    <div id="containerGraph2" class="graph" style="cursor:pointer;" onclick="showDiv('div2')"></div>
	</div>
	
	<div id="div1"  style="display: block;" >
		<div class="normal_table">
	 		<table>
	   		   <thead>
   		  		<tr> 
	   		  		<th rowspan="2">프로젝트명</th>
	   		  		<th colspan="4"> 산출물 </th>
	   		  		
  		  		</tr>
  		  		<tr>
  		  			<th class="bl">착수</th>
	   		  		<th>중간</th>
	   		  		<th>완료</th>
	   		  		<th>합계</th>
  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="outputList" items="${outputList}" varStatus="status1">
	  		  			<tr> 
			   		  		<td>
								<c:out value="${outputList.eWordA}" />
								<input type="hidden" id="eWordA_${status1.index}" name="eWordA" value="${outputList.eWordA}">
								<input type="hidden" id="eValueD_${status1.index}" name="eValueD" value="${outputList.eValueD}">
							</td>
							<td>
			   		  			 ${outputList.eValueA} 
							</td>
							<td>
			   		  			 ${outputList.eValueB} 
							</td>
							<td>
			   		  			 ${outputList.eValueC} 
							</td>
							<td>
			   		  			 ${outputList.eValueD} 
							</td>
						</tr>
				</c:forEach>
	   		  </tbody>
			</table>
		</div>
	</div>

	<div id="div2"  style="display: none;" >
		<div class="normal_table">
	 		<table>
	   		  <thead>
	   		  	<tr> 
	   		  		<th rowspan="2">프로젝트명</th>
	   		  		<th colspan="4"> 보고서 </th>
	   		  		
  		  		</tr>
  		  		<tr>
  		  			<th>착수</th>
	   		  		<th>중간</th>
	   		  		<th>완료</th>
	   		  		<th>합계</th>
  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="projectList" items="${projectList}" varStatus="status2">
	  		  			<tr> 
			   		  		<td>
								<c:out value="${projectList.eWordA}" />
								<input type="hidden" id="eWordB_${status2.index}" name="eWordB" value="${projectList.eWordA}">
								<input type="hidden" id="eValueB_${status2.index}" name="eValueB" value="${projectList.eValueD}">
							</td>
							<td>
			   		  			 ${projectList.eValueA} 
							</td>
							<td>
			   		  			 ${projectList.eValueB} 
							</td>
							<td>
			   		  			 ${projectList.eValueC} 
							</td>
							<td>
			   		  			 ${projectList.eValueD} 
							</td>
						</tr>
				</c:forEach>
	   		  </tbody>
			</table>
		</div>
	</div>

	<div id="div3"  style="display: none;" >
		<div class="normal_table">
	 		<table>
	   		  <thead>
	   		  	<tr> 
	   		  		<th>사용자명</th>
	   		  		<th>건수</th>
  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="outputsmemList" items="${outputsmemList}" varStatus="status1">
	   		 		<c:if test="${outputsmemList.kStaffName ne ''}"> 
	  		  			<tr> 
			   		  		<td>
								<c:out value="${outputsmemList.kStaffName}" />
							</td>
							<td>
			   		  			 ${outputsmemList.sOutputCount} 
							</td>
						</tr>
						</c:if>
	  		  		</c:forEach>
	   		  </tbody>
			</table>
		</div>
	</div>
	
	<c:forEach var="outputsyList" items="${outputsyList}" varStatus="i">
		<c:if test="${outputsyList.sOutputCount > 0}">
		<input type="hidden" id="sYear_${i.index}" name="sYear" value="${outputsyList.sYear}">
		<input type="hidden" id="sOutputYCount_${i.index}" name="sOutputYCount" value="${outputsyList.sOutputCount}">
		</c:if>
	</c:forEach>

	<c:forEach var="outputsmList" items="${outputsmList}" varStatus="i">
		<c:if test="${outputsmList.sOutputCount > 0}">
		<input type="hidden" id="eDate_${i.index}" name="eDate" value="${outputsmList.eDate}">
		<input type="hidden" id="sOutputmCount_${i.index}" name="sOutputmCount" value="${outputsmList.sOutputCount}">
		</c:if>
	</c:forEach>

	<c:forEach var="outputsmemList" items="${outputsmemList}" varStatus="i">
		<c:if test="${outputsmemList.sOutputCount > 0}">
		<input type="hidden" id="kStaffName_${i.index}" name="kStaffName" value="${outputsmemList.kStaffName}">
		<input type="hidden" id="sOutputmemCount_${i.index}" name="sOutputmemCount" value="${outputsmemList.sOutputCount}">
		</c:if>
	</c:forEach>
	
	<c:forEach var="environmentList" items="${environmentList}" varStatus="i">
		<input type="hidden" id="maxtemperature_${i.index}" name="maxtemperature" value="${environmentList.maxtemperature}">
		<input type="hidden" id="mintemperature_${i.index}" name="mintemperature" value="${environmentList.mintemperature}">
		<input type="hidden" id="environmentName_${i.index}" name="environmentName" value="${environmentList.environmentName}">
	</c:forEach>
</form>



<script>
	const eMachineDate = document.getElementsByName('eDate');
	const eMachineDateValue = Array.from(eMachineDate).map(eMachineDate => eMachineDate.value);

	const sYear = document.getElementsByName('sYear');
	const sYearValue = Array.from(sYear).map(sYear => sYear.value);
	
	const sOutputYCount = document.getElementsByName('sOutputYCount');
	const sOutputYCountValue = Array.from(sOutputYCount).map(sOutputYCount => parseInt(sOutputYCount.value));
	
	const sOutputmCount = document.getElementsByName('sOutputmCount');
	const sOutputmCountValue = Array.from(sOutputmCount).map(sOutputmCount => parseInt(sOutputmCount.value));
	
	const kStaffName = document.getElementsByName('kStaffName');
	const kStaffNameValue = Array.from(kStaffName).map(kStaffName => kStaffName.value);

	const sOutputmemCount = document.getElementsByName('sOutputmemCount');
	const sOutputmemCountValue = Array.from(sOutputmemCount).map(sOutputmemCount => parseInt(sOutputmemCount.value));
	
	const eWordA = document.getElementsByName('eWordA');
	const eWordAValue = Array.from(eWordA).map(eWordA => eWordA.value);

	const eValueD = document.getElementsByName('eValueD');
	const eValueDCountValue = Array.from(eValueD).map(eValueD => parseInt(eValueD.value));

	function uncomma(str) {
	    str = String(str);
	    return isNullChk(str.replace(/(,)/g, ''));
	}
	
	Highcharts.setOptions({
	    lang: {
	        thousandsSep: ','
	    }
	});
	
	const chart1 = Highcharts.chart('containerGraph1', {
	    chart: {
	        type: 'column',
	        //width: 650
	    },
	    title: {
	        text: '프로젝트당 산출물 등록 건',
	        style: {
	 	        fontSize: '16px',
	 	        fontWeight: 600,
		        fontFamily: 'Pretendard',
	 	        color: '#171D2F',
	 	  	}
	    },
	    xAxis: {
	        categories: eWordAValue, // 원형 그래프와 같은 날짜를 사용합니다.
	        crosshair: true,
	        labels: {
	            formatter: function () {
	              const maxLen = 12;
	              const label = this.value;
	              return label.length > maxLen ? label.substring(0, maxLen) + '...' : label;
	            },
	          },
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '-'
	        }
	    },
	    tooltip: {
	        valueSuffix: '건'
	    },
	    plotOptions: {
	        column: {
	            dataLabels: {
	                enabled: true,
	                format: '{point.y}', // 막대 그래프의 데이터 라벨 포맷 설정
	                inside: true,
	                style: {
	         	        fontSize: '13px',
	        	        fontFamily: 'Pretendard',
	         	        color: '#6A6D75',
	         	  	}
	            }
	        }
	    },
	    series: [
	    	 {
	                id: 'Countst',
		            name: '건',
		            data: eWordAValue.map((name, index) => ({ name, y: eValueDCountValue[index] })),
	                visible: true
		        }
	    	]
	});
	const eWordB = document.getElementsByName('eWordB');
	const eWordBValue = Array.from(eWordB).map(eWordB => eWordB.value);

	const eValueB = document.getElementsByName('eValueB');
	const eValueBCountValue = Array.from(eValueB).map(eValueB => parseInt(eValueB.value));
	const chart2 = Highcharts.chart('containerGraph2', {
		 chart: {
		        type: 'pie',
		        //width: 650
		    },
		    title: {
		        text: '프로젝트 보고서 등록 건',
		        style: {
		 	        fontSize: '16px',
		 	        fontWeight: 600,
			        fontFamily: 'Pretendard',
		 	        color: '#171D2F',
		 	  	}
		    },
		    xAxis: {
		        categories: eWordBValue,
		        crosshair: true
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: '-'
		        }
		    },
		    tooltip: {
		        valueSuffix: '건'
		    },
		    plotOptions: {
		    	 pie: {
		    		 borderWidth: 2,
		             allowPointSelect: true,
		             cursor: 'pointer',
		             dataLabels: {
		                 enabled: true,
		                 formatter: function () {
	                	    const maxLen = 12;
	                	    const name = this.point.name;
	                	    const shortName = name.length > maxLen ? name.substring(0, maxLen) + '...' : name;
	                	    return '<b>' + shortName + '</b> : ' + this.y + '건';
	                	  },
		                 style: {
		          	        fontSize: '13px',
		         	        fontFamily: 'Pretendard',
		          	        color: '#6A6D75',
		          	  	}
		             }
		    	 }
		    },
		    series: [
		    	{
		        	name: '요청 개수',
		            data: eWordBValue.map((name, index) => ({ name, y: eValueBCountValue[index] })),
		            visible: true
		        }
		    ]
		});

	const barChart = Highcharts.chart('containerGraph3', {
	    chart: {
	        type: 'column' ,
	        //width: 650
	    },
	    title: {
	        text: '월별',
	        style: {
	 	        fontSize: '16px',
	 	        fontWeight: 600,
		        fontFamily: 'Pretendard',
	 	        color: '#171D2F',
	 	  	}
	    },
	    xAxis: {
	        categories: eMachineDateValue, // 원형 그래프와 같은 날짜를 사용합니다.
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '수량'
	        }
	    },
	    tooltip: {
	        valueSuffix: '개'
	    },
	    plotOptions: {
	        column: {
	            dataLabels: {
	                enabled: true,
	                format: '{point.y}', // 막대 그래프의 데이터 라벨 포맷 설정
	                inside: true,
	                style: {
	         	        fontSize: '13px',
	        	        fontFamily: 'Pretendard',
	         	        color: '#6A6D75',
	         	  	}
	            }
	        }
	    },
	    series: [
	    	 {
	                id: 'Countst',
		            name: '요청 수',
		            data: sOutputmCountValue,
	                visible: true
		        }
	    	]
	});
</script>