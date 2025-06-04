<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script src="/js/highchart/code/highcharts.js"></script>
<script type="text/javascript">
/* 페이징 */
function fn_guestList(pageNo) {
	$('#mloader').show();
	document.frm.submit();
}

function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}


$(document).ready(function(){	
	datepickerSet('topStartDate', 'topEndDate');
	
	$('.tab_button').click(function () {
		const target = $(this).data('tab');
		$('.tab_button').removeClass('active');
		$(this).addClass('active');
		$('.tab_content').hide();
		$('#' + target).show();
	});
	$('.tab_button.active').trigger('click');
});






function setStartDate(d) {
	var settingDate = new Date();
	if (d === '1d') settingDate.setDate(settingDate.getDate() - 1);
	else if (d === '2d') settingDate.setDate(settingDate.getDate() - 2);
	else if (d === '3d') settingDate.setDate(settingDate.getDate() - 3);
	else if (d === 7) settingDate.setDate(settingDate.getDate() - 7);
	else if (d === 1) settingDate.setMonth(settingDate.getMonth() - 1);
	else settingDate.setMonth(settingDate.getMonth() - 3);

	// 날짜 입력
	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));

	fn_guestList(1);
}





function openTab(evt, tabName) {
    evt.preventDefault(); 
    
    var i, tabcontent, tablinks;
    // Hide all tab contents
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    
    // Remove the active class from all tabs
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    
    // Show the current tab and add an "active" class to the tab button
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}


function moveDetail(url){
	$("#topStartDate").val("");
	$("#topEndDate").val("");
	if(url != ''){
		document.frm.action = url;
	}else{
		document.frm.action = "/mes/main.do";
	}
	document.frm.submit();
}



</script>
<form id="frm" name="frm" method="post"	action="/mes/main.do">
		 <div id="mes_container">
		 	<div class="main_top">
		 		<div class="main_title" >
					시스템 관리 현황
				</div>
				<div class="seoeun_top">
					<ul class="select_date">
						<li>
							<button type="button" class="date_btn" onclick="setStartDate('1d');">
								1일전
							</button>
						</li>
						<li>
							<button type="button" class="date_btn" onclick="setStartDate('2d');">
								2일전
							</button>
						</li>
						<li>
							<button type="button" class="date_btn" onclick="setStartDate('3d');">
								3일전
							</button>
						</li>
						<li>
							<button type="button" class="date_btn" onclick="setStartDate(7);">
								1주일
							</button>
						</li>
						<li>
							<button type="button" class="date_btn" onclick="setStartDate(1);">
								1개월
							</button>
						</li>
						<li>
							<button type="button" class="date_btn" onclick="setStartDate(3);">
								3개월
							</button>
						</li>
					</ul>
					<div class="range_select">
						<input type="text" name="topStartDate" id="topStartDate" value="${vo.topStartDate}" readonly class="inp_color" />
						- <input type="text" name="topEndDate" id="topEndDate" value="${vo.topEndDate}" readonly class="inp_color" />
					</div>
					<button type="submit" class="basic_btn dark" onclick="fn_guestList(1)">검색</button>
				</div>
		 	</div>
		 	<div class="dashboard_wrap">
		 		<div class="top">
		 			<div class="error">
		 				<div <c:if test="${ass766 eq 'T' }">onclick="moveDetail('/mes/issue/kw_issue_lf.do');" </c:if> style="cursor:pointer;">
		 					<h4>장애 처리</h4>
		 					<ul class="error_statu">
		 						<li class="regist">
		 							<strong>등록</strong>
		 							<p><i>${mainIssueInfo.eSearchWordA}</i>건</p>
		 						</li>
		 						<li class="ing">
		 							<strong>처리  중</strong>
		 							<p><i>${mainIssueInfo.eSearchWordB}</i>건</p>
		 						</li>
		 						<li class="complete">
		 							<strong>완료</strong>
		 							<p><i>${mainIssueInfo.eSearchWordC}</i>건</p>
		 						</li>
		 					</ul>
							<table class="dash_table">
								<thead>
									<tr>
										<th>No.</th>
										<th>자산유형 </th>
										<th>처리유형 </th>
										<th>상세구분</th>
										<th>요청일자</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="list" items="${mainIssueList}" varStatus="i" end="7">
										<tr>
												<td>${i.count}</td>
												<td>${list.eAssetTypeName}</td>
												<td>${list.eIssueTypeName}</td>
												<td>${list.eProcessingTypeName}</td>
												<td>${list.eRequestDate}</td>
										</tr>
									</c:forEach>
									 <c:forEach var="i" begin="${fn:length(mainIssueList) + 1}" end="8">
									      <tr>
									        <td>${i}</td>
									        <td>-</td>
									        <td>-</td>
									        <td>-</td>
									        <td>-</td>
									      </tr>
								    </c:forEach>
								</tbody>
							</table>
						</div>
		 			</div>
		 			<div class="device">
		 				<h4>대상장비 등록</h4>
		 				<div class="tabs">
		 					<div class="tab">
		 						<button type="button" class="tab_button active" data-tab="all_content">자산유형별</button>
		 						<button type="button" class="tab_button" data-tab="programming_content">제조사별</button>
		 					</div>
		 					<div class="tab_contents">
			 					<div class="tab_content" id="all_content">
							        <div id="containerGraph1" onclick="moveDetail('/mes/asset/kw_asset_lf.do');"></div>
							    </div> 
								<div class="tab_content" id="programming_content">
							        <div id="containerGraph2" onclick="moveDetail('/mes/asset/kw_asset_lf.do');"></div>
							    </div>
							</div>
		 				</div>
		 			</div>
		 			<div class="statu">
		 				<div class="main">
		 					<h4>유지관리</h4>
		 					<ul class="main_statu">
		 						<li class="error">
		 							<em <c:if test="${ass766 eq 'T' }">onclick="moveDetail('/mes/issue/kw_issue_lf.do');" </c:if>>장애</em>
		 							<p><strong>${mainIssueTotal.eSearchWordA }건</strong></p>
		 						</li>
		 						<li class="problem">
		 							<em <c:if test="${ass763 eq 'T' }">onclick="moveDetail('/mes/blueprint/kw_issue_lf.do');" </c:if>>문제</em>
		 							<p><strong>${mainIssueTotal.eSearchWordC }건</strong></p>
		 						</li>
		 						<li class="change">
		 							<em <c:if test="${ass746 eq 'T' }">onclick="moveDetail('/mes/blueprint/kw_blueprint_lf.do');"</c:if>>변경</em>
		 							<p><strong>${mainIssueTotal.eSearchWordB }건</strong></p>
		 						</li>
		 						<li class="sr">
		 							<em <c:if test="${ass764 eq 'T' }">onclick="moveDetail('/mes/blueprint/kw_sr_lf.do');" </c:if>>SR</em>
		 							<p><strong>${mainIssueTotal.eSearchWordD }건</strong></p>
		 						</li>
		 					</ul>
		 					<div class="num_box" <c:if test="${ass758 eq 'T' }"> onclick="moveDetail('/mes/inspection/kw_inspection_lf.do');"</c:if>>
		 						<p>정기점검</p>
		 						<ul>
		 							<li>
		 								<em>일일</em>
		 								<strong>${mainIssueTotal.eSearchWordF }건</strong>
		 							</li>
		 							<li>
		 								<em>주간</em>
		 								<strong>${mainIssueTotal.eSearchWordG }건</strong>
		 							</li>
		 							<li>
		 								<em>월간</em>
		 								<strong>${mainIssueTotal.eSearchWordH }건</strong>
		 							</li>
		 						</ul>
		 					</div>
		 				</div>
	 					<div class="data">
	 						<h4>자산변동</h4>
	 						<div class="num_box" <c:if test="${ass756 eq 'T' }"> onclick="moveDetail('/mes/asset/kw_eCondition_lf.do');"</c:if>>
		 						<p>보유자산</p>
		 						<ul>
		 							<li>
		 								<em>반출</em>
		 								<strong>${mainIssueTotal.eSearchWordI }건</strong>
		 							</li>
		 							<li>
		 								<em>반입</em>
		 								<strong>${mainIssueTotal.eSearchWordJ }건</strong>
		 							</li>
		 							<li>
		 								<em>미반입</em>
		 								<strong>${mainIssueTotal.eSearchWordK }건</strong>
		 							</li>
		 						</ul>
		 					</div>
		 					<div class="num_box" <c:if test="${ass767 eq 'T' }"> onclick="moveDetail('/mes/equipment/kw_equipment_in_lf.do');"</c:if>>
		 						<p>임시자산</p>
		 						<ul>
		 							<li>
		 								<em>반입</em>
		 								<strong>${mainIssueTotal.eSearchWordL }건</strong>
		 							</li>
		 							<li>
		 								<em>반출</em>
		 								<strong>${mainIssueTotal.eSearchWordM }건</strong>
		 							</li>
		 							<li>
		 								<em>미반출</em>
		 								<strong>${mainIssueTotal.eSearchWordN }건</strong>
		 							</li>
		 						</ul>
		 					</div>
	 					</div>
		 			</div>
		 		</div>
		 		<div class="btm">
		 			<div>
		 				<h4>EoS</h4>
		 				<table  class="dash_table" <c:if test="${ass755 eq 'T' }">  onclick="moveDetail('/mes/asset/kw_Hardware_lf.do');" </c:if>>
							<thead>
								<tr>
									<th>No.</th>
									<th>대상</th>
									<th>유효기간</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="alist" items="${assetEosList}" varStatus="a" end="7">
									<tr>
										<td colspan="1">${a.count }</td>
										<td colspan="1">${alist.eAssetModel }</td>
										<td colspan="1">${alist.eEosDate } : ${alist.searchTypeSet1 } </td>
									</tr>
								</c:forEach>
								 <c:forEach var="i" begin="${fn:length(assetEosList) + 1}" end="8">
								      <tr>
								        <td>${i}</td>
								        <td>-</td>
								        <td>-</td>
								      </tr>
							    </c:forEach>
							</tbody>
						</table>
		 			</div>
		 			<div>
		 				<h4>EoL</h4>
		 				<table class="dash_table" <c:if test="${ass755 eq 'T' }"> onclick="moveDetail('/mes/asset/kw_Hardware_lf.do');" </c:if>>
							<thead>
								<tr>
									<th>No.</th>
									<th>대상</th>
									<th>유효기간</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="blist" items="${assetEolList}" varStatus="b" end="7">
									<tr>
										<td colspan="1">${b.count }</td>
										<td colspan="1">${blist.eAssetModel }</td>
										<td colspan="1">${blist.eEolDate } : ${blist.searchTypeSet2 } </td>
									</tr>
								</c:forEach>
								 <c:forEach var="i" begin="${fn:length(assetEolList) + 1}" end="8">
								      <tr>
								        <td>${i}</td>
								        <td>-</td>
								        <td>-</td>
								      </tr>
							    </c:forEach>
							</tbody>
						</table>
		 			</div>
		 			<div>
		 				<h4>라이선스</h4>
		 				<table class="dash_table" <c:if test="${ass754 eq 'T' }">  onclick="moveDetail('/mes/asset/kw_Software_Register_lf.do');" </c:if>>
							<thead>
								<tr>
									<th>No.</th>
									<th>대상</th>
									<th>유효기간</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="clist" items="${mainSoftwareList}" varStatus="c" end="7">
									<tr>
										<td colspan="1">${c.count }</td>
										<td colspan="1">${clist.eProductName }</td>
										<td colspan="1">${clist.eEndDate } : ${clist.searchTypeSet1 } </td>
									</tr>
								</c:forEach>
								 <c:forEach var="i" begin="${fn:length(mainSoftwareList) + 1}" end="8">
								      <tr>
								        <td>${i}</td>
								        <td>-</td>
								        <td>-</td>
								      </tr>
							    </c:forEach>
							</tbody>
						</table>
		 			</div>
		 			<div>
		 				<h4>노후장비</h4>
		 				<table class="dash_table" <c:if test="${ass742 eq 'T' }"> onclick="moveDetail('/mes/asset/kw_asset_lf.do');" </c:if>>
							<thead>
								<tr>
									<th>No.</th>
									<th>대상</th>
									<th>도입일자(사용년수)</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="dlist" items="${mainLifeStatusList}" varStatus="d" end="7">
									<tr>
										<td colspan="1">${d.count }</td>
										<%--	<td colspan="1">${dlist.aAssetName }</td>   --%>
										<td colspan="1">${dlist.aAssetModel }</td>
										<td colspan="1">${dlist.eAssetDate }(${dlist.eLifespan }) </td>
									</tr>
								</c:forEach>
								 <c:forEach var="i" begin="${fn:length(mainLifeStatusList) + 1}" end="8">
								      <tr>
								        <td>${i}</td>
								        <td>-</td>
								        <td>-</td>
								      </tr>
							    </c:forEach>
							</tbody>
						</table>
		 			</div>
		 		</div>
		 	</div>
		</div> 
	</form> 
 

	<c:forEach var="assetMakerList" items="${assetMakerList}" varStatus="j">
		<input type="hidden" id="aAssetMaker_${j.index}" name="aAssetMaker" value="${assetMakerList.aAssetMaker}">
		<input type="hidden" id="aAssetCost_${j.index}" name="aAssetCost" value="${assetMakerList.aAssetCost}">
	</c:forEach>

	<c:forEach var="assetTypeList" items="${assetTypeList}" varStatus="j">
		<input type="hidden" id="aAssetType_${j.index}" name="aAssetType" value="${assetTypeList.aAssetType}">
		<input type="hidden" id="aAssetTypeCost_${j.index}" name="aAssetTypeCost" value="${assetTypeList.aAssetCost}">
	</c:forEach>


<script>

const sAssetTypeCost = document.getElementsByName('aAssetTypeCost'); 
 const sAssetTypeCostValue = Array.from(sAssetTypeCost).map(sAssetTypeCost => parseInt(sAssetTypeCost.value)); 

 const sAssetType = document.getElementsByName('aAssetType'); 
 const sAssetTypeValue = Array.from(sAssetType).map(sAssetType => sAssetType.value); 

 const sAssetmcCount = document.getElementsByName('aAssetCost'); 
 const sAssetmcCountValue = Array.from(sAssetmcCount).map(sAssetmcCount => parseInt(sAssetmcCount.value)); 

 const sAssetMaker = document.getElementsByName('aAssetMaker'); 
 const sAssetMakerValue = Array.from(sAssetMaker).map(sAssetMaker => sAssetMaker.value); 

 const chart1 = Highcharts.chart('containerGraph2', { 
     chart: { 
         type: 'pie', height: 286  
     }, 
     title: false, 
     xAxis: { 
         categories: sAssetMakerValue, 
         crosshair: true 
     }, 
     yAxis: { 
         min: 0, 
         title: { 
             text: '-' 
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
                      return shortName + ' ' + this.y + '개';
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

 const chart2 = Highcharts.chart('containerGraph1', { 
     chart: { 
         type: 'pie', height: 286  
     }, 
     title: false, 
     xAxis: { 
         categories: sAssetTypeValue, 
         crosshair: true 
     }, 
     yAxis: { 
         min: 0, 
         title: { 
             text: '-' 
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
                      return shortName + ' ' + this.y + '개';
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
             data: sAssetTypeValue.map((name, index) => ({ name, y: sAssetTypeCostValue[index] })), 
             visible: true 
         } 
     ] 
 }); 
 </script> 



