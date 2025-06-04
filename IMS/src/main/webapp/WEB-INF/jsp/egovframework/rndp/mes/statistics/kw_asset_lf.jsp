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
		$('#mloader').hide();
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

<form id="frm" name="frm" method="post" action="/mes/statistics/kw_asset_lf.do">
	
	<div class="content_top">
		<div class="content_tit">
			<h2>대상장비(자산)관리 통계</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
<!-- 				<li> -->
<!-- 			          		<span>조회 구분</span> -->
<!-- 							<select name="ePageGubun" id="ePageGubun" onchange="eNowPage()"> -->
<%-- 								<option value="1" <c:if test="${mesStatisticsVO.ePageGubun eq '1'}">selected</c:if>>제조사기준</option> --%>
<%-- 								<option value="2" <c:if test="${mesStatisticsVO.ePageGubun eq '2'}">selected</c:if>>유형별기준</option> --%>
<%-- 								<option value="3" <c:if test="${mesStatisticsVO.ePageGubun eq '3'}">selected</c:if>>기간별</option> --%>
<!-- 					      	</select> -->
<!-- 					    </li> -->
<!-- 				<li> -->
					<li>
						<span>자산상태</span>
						<select id='searchType' name='searchType'>
							<option value=''>전체</option>
							<c:forEach var='list' items='${gubun37List}'>
								<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'   <c:if test="${mesStatisticsVO.searchType eq list.sGubunKey  }">selected="selected"</c:if> >${list.sGubunName}</option>						
							</c:forEach>
						</select> 
					</li>
					<li>
		           	 	<span>도입일 선택 </span>
		           	 	<div class="date">
		           	 		<input type="text" name="topStartDate"  id="topStartDate" value="${mesStatisticsVO.topStartDate}" readonly/>
				       		-
				           	<input type="text" name="topEndDate" id="topEndDate"  value="${mesStatisticsVO.topEndDate}" readonly/>
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
	   		  		<th>제조사명</th>
	   		  		<th>등록 자산 수</th>
	  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="assetmcList" items="${assetmcList}" varStatus="status1">
	  		  			<tr> 
			   		  		<td>
								<c:out value="${assetmcList.sAssetMaker}" />
							</td>
							<td>
			   		  			 ${assetmcList.sAssetCount} 
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
	   		  		<th>자산유형</th>
	   		  		<th>등록 자산 수</th>
	  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="ieList" items="${ieList}" varStatus="status1">
	  		  			<tr> 
			   		  		<td>
								${ieList.sAssetMaker}
								<input type="hidden" id="eValueA_${status1.index}" name="eValueA" value="${ieList.sAssetCount}">
								<input type="hidden" id="eWordA_${status1.index}" name="eWordA" value="${ieList.sAssetMaker}">
							</td>
							<td>
			   		  			 ${ieList.sAssetCount} 
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
	   		  		<th>자산유형</th>
	   		  		<th>등록 자산 수</th>
	  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="ieList" items="${ieList}" varStatus="status1">
	  		  			<tr> 
			   		  		<td>
								<c:out value="${ieList.sAssetMaker}" />
							</td>
							<td>
			   		  			 ${ieList.sAssetCount} 
							</td>
						</tr>
	  		  		</c:forEach>
	   		  </tbody>
			</table>
		</div>
	</div>
	
	<c:forEach var="assetList" items="${assetList}" varStatus="i">
		<c:if test="${assetList.sAssetCount > 0}">
		<input type="hidden" id="eDate_${i.index}" name="eDate" value="${assetList.eDate}">
		<input type="hidden" id="sAssetCount_${i.index}" name="sAssetCount" value="${assetList.sAssetCount}">
		</c:if>
	</c:forEach>

	<c:forEach var="assetmcList" items="${assetmcList}" varStatus="j">
		<c:if test="${assetmcList.sAssetCount > 0}">
		<input type="hidden" id="sAssetMaker_${j.index}" name="sAssetMaker" value="${assetmcList.sAssetMaker}">
		<input type="hidden" id="sAssetmcCount_${j.index}" name="sAssetmcCount" value="${assetmcList.sAssetCount}">
		</c:if>
	</c:forEach>

	<c:forEach var="environmentList" items="${environmentList}" varStatus="k">
		<input type="hidden" id="maxtemperature_${k.index}" name="maxtemperature" value="${environmentList.maxtemperature}">
		<input type="hidden" id="mintemperature_${k.index}" name="mintemperature" value="${environmentList.mintemperature}">
		<input type="hidden" id="environmentName_${k.index}" name="environmentName" value="${environmentList.environmentName}">
	</c:forEach>
</form>



<script>
	const eMachineDate = document.getElementsByName('eDate');
	const eMachineDateValue = Array.from(eMachineDate).map(eMachineDate => eMachineDate.value);
	
	const sAssetCount = document.getElementsByName('sAssetCount');
	const sAssetCountValue = Array.from(sAssetCount).map(sAssetCount => parseInt(sAssetCount.value));
	
	const sAssetmcCount = document.getElementsByName('sAssetmcCount');
	const sAssetmcCountValue = Array.from(sAssetmcCount).map(sAssetmcCount => parseInt(sAssetmcCount.value));

	const sAssetieCount = document.getElementsByName('sAssetieCount');
	const sAssetieCounttValue = Array.from(sAssetieCount).map(sAssetieCount => parseInt(sAssetieCount.value));
	
	const sAssetMaker = document.getElementsByName('sAssetMaker');
	const sAssetMakerValue = Array.from(sAssetMaker).map(sAssetMaker => sAssetMaker.value);

	const sAssetUesType = document.getElementsByName('sAssetUesType');
	const sAssetUesTypeValue = Array.from(sAssetUesType).map(sAssetUesType => sAssetUesType.value);

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
	        type: 'pie',
	       // width: 650
	    },
	    title: {
	        text: '제조사별 통계',
	        style: {
	 	        fontSize: '16px',
	 	        fontWeight: 600,
		        fontFamily: 'Pretendard',
	 	        color: '#171D2F',
	 	  	}
	    },
	    xAxis: {
	        categories: sAssetMakerValue,
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '온도'
	        }
	    },
	    tooltip: {
	        valueSuffix: '개'
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
	                     return '<b>' + shortName + '</b> : ' + this.y + '개';
	                   }, // 각 섹션의 데이터 라벨 포맷 설정
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
	        	name: '건',
	            data: sAssetMakerValue.map((name, index) => ({ name, y: sAssetmcCountValue[index] })),
	            visible: true
	        }
	    ]
	});
	
	const eValueA = document.getElementsByName('eValueA');
	const eValueAValue = Array.from(eValueA).map(eValueA => parseInt(eValueA.value));

	const eWordAa = document.getElementsByName('eWordA');
	const eWordAValuea = Array.from(eWordAa).map(eWordAa => eWordAa.value);
	
	const chart2 = Highcharts.chart('containerGraph2', {
		 chart: {
		        type: 'pie',
		        //width: 650
		    },
		    title: {
		        text: '유형별 통계',
		        style: {
		 	        fontSize: '16px',
		 	        fontWeight: 600,
			        fontFamily: 'Pretendard',
		 	        color: '#171D2F',
		 	  	}
		    },
		    xAxis: {
		        categories: eWordAValuea,
		        crosshair: true
		    },
		    yAxis: {
		        min: 0,
		        title: {
		            text: '온도'
		        }
		    },
		    tooltip: {
		        valueSuffix: '개'
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
		                     return '<b>' + shortName + '</b> : ' + this.y + '개';
		                   }, // 각 섹션의 데이터 라벨 포맷 설정
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
		        	name: '건',
		            data: eWordAValuea.map((name, index) => ({ name, y: eValueAValue[index] })),
		            visible: true
		        }
		    ]
		});

	const barChart = Highcharts.chart('containerGraph3', {
	    chart: {
	        type: 'column',
	        //width: 650
	    },
	    title: {
	        text: '기간별',
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
		            data: sAssetCountValue,
	                visible: true
		        }
	    	]
	});
</script>