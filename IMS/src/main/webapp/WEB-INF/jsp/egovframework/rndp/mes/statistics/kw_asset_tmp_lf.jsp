<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<meta http-equiv="refresh" content="900">
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />

<!-- 그리드 S -->
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />

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

<form id="frm" name="frm" method="post" action="/mes/statistics/kw_asset_tmp_lf.do">
	
	<div class="content_top">
		<div class="content_tit">
			<h2>임시자산 반입출 통계</h2>
		</div>
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
<!-- 				<li> -->
<!-- 	          		<span>조회 구분</span> -->
<!-- 					<select name="ePageGubun" id="ePageGubun" onchange="eNowPage()"> -->
<%-- 						<option value="1" <c:if test="${mesStatisticsVO.ePageGubun eq '1'}">selected</c:if>>사용자기준</option> --%>
<%-- 						<option value="2" <c:if test="${mesStatisticsVO.ePageGubun eq '2'}">selected</c:if>>유형별기준</option> --%>
<%-- 						<option value="3" <c:if test="${mesStatisticsVO.ePageGubun eq '3'}">selected</c:if>>기간별</option> --%>
<!-- 			      	</select> -->
<!-- 			    </li> -->
					<li>
						<span>기간 </span>
						<div class="date">
							<input type="text" name="topStartDate" id="topStartDate" value="${mesStatisticsVO.topStartDate}" readonly/>
				       		-
				           	<input type="text" name="topEndDate"  id="topEndDate"  value="${mesStatisticsVO.topEndDate}" readonly/>
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
	   		  		<th>제조사</th>
	   		  		<th>반입</th>
	   		  		<th>미반출</th>
  		  		</tr>
	   		  </thead>
	   		   <tbody>
	   		 	<c:forEach var="iMakerList" items="${iMakerList}" varStatus="a">
	  		  			<tr>
				   		 	<td>${iMakerList.eWordA}
								<input type="hidden" id="eWordA_${a.index}" name="eWordA" value="${iMakerList.eWordA}">
								<input type="hidden" id="eValueA_${a.index}" name="eValueA" value="${iMakerList.eValueA}">
								<input type="hidden" id="eWordB_${a.index}" name="eValueB" value="${iMakerList.eValueB}">
							</td>
				   		 	<td>${iMakerList.eValueA}</td>
				   		 	<td>${iMakerList.eValueB}</td>
								
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
	   		  		<th>반입</th>
	   		  		<th>미반출</th>
  		  		</tr>
	   		  </thead>
	   		   <tbody>
	   		 	<c:forEach var="iTypeList" items="${iTypeList}" varStatus="b">
	  		  			<tr> 
			   		  		<td>${iTypeList.eWordB}
								<input type="hidden" id="eWordB_${b.index}" name="eWordB" value="${iTypeList.eWordB}">
								<input type="hidden" id="eValueC_${b.index}" name="eValueC" value="${iTypeList.eValueC}">
								<input type="hidden" id="eValueD_${b.index}" name="eValueD" value="${iTypeList.eValueD}">
							</td>
				   		 	<td>${iTypeList.eValueC}</td>
				   		 	<td>${iTypeList.eValueD}</td>
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
	   		  		<th>기간</th>
	   		  		<th>건수</th>
	  		  		</tr>
	   		  </thead>
	   		  <tbody>
	   		 	<c:forEach var="statisticsList" items="${statisticsList}" varStatus="status1">
	   		 		<c:if test="${statisticsList.mMaintanceCount > 0}"> 
	  		  			<tr> 
			   		  		<td>
								<c:out value="${statisticsList.eDate}" />
							</td>
							<td>
			   		  			 ${statisticsList.mMaintanceCount} 
							</td>
						</tr>
						</c:if>
	  		  		</c:forEach>
	   		  </tbody>
			</table>
		</div>
	</div>
</form>



<script>
	const eMachineDate = document.getElementsByName('eDate');
	const eMachineDateValue = Array.from(eMachineDate).map(eMachineDate => eMachineDate.value);
 
	const Countst = document.getElementsByName('Countst');
	const CountstValue = Array.from(Countst).map(Countst => parseInt(Countst.value));
 
	
	
// 	--
	const eValueA = document.getElementsByName('eValueA');
	const eValueAValue = Array.from(eValueA).map(eValueA => parseInt(eValueA.value));

	const eWordAa = document.getElementsByName('eWordA');
	const eWordAValuea = Array.from(eWordAa).map(eWordAa => eWordAa.value);
	 
	
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
	        //width: 650
	    },
	    title: {
	        text: '제조사별 반입 등록 건',
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
	        	name: '등록 개수',
	            data: eWordAValuea.map((name, index) => ({ name, y: eValueAValue[index] })),
	            visible: true
	        }
	    ]
	});
// 	--
	const eValueB = document.getElementsByName('eValueC');
	const eValueBValue = Array.from(eValueB).map(eValueB => parseInt(eValueB.value));

	const eWordBa = document.getElementsByName('eWordB');
	const eWordBValuea = Array.from(eWordBa).map(eWordBa => eWordBa.value);
	
	const chart2 = Highcharts.chart('containerGraph2', {
		 chart: {
		        type: 'column',
		        //width: 650
		    },
		    title: {
		        text: '유형별 반입 등록 건',
		        style: {
		 	        fontSize: '16px',
		 	        fontWeight: 600,
			        fontFamily: 'Pretendard',
		 	        color: '#171D2F',
		 	  	}
		    },
		    xAxis: {
		        categories: eWordBValuea,
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
		    	  column: {
		    	        dataLabels: {
		    	            enabled: true,  // 막대에 수량 표시
		    	            format: '{point.y}건',  // 수량 포맷 설정
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
		        	name: '자산 유형',
		            data: eWordBValuea.map((name, index) => ({ name, y: eValueBValue[index] })),
		            visible: true
		        }
		    ]
		});

	const barChart = Highcharts.chart('containerGraph3', {
	    chart: {
	        type: 'column', // 막대 그래프로 설정
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
		            data: CountstValue,
	                visible: true
		        }
	    	]
	});
</script>