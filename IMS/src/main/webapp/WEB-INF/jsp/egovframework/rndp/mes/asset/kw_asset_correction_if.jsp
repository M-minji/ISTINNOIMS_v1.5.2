<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  addRow();
});

var itemRowIndex = 0;
//행추가
function addRow() {	

	var innerStr = "";
	
	innerStr += "<tr>";
	innerStr += "	<td>";
	innerStr += "		<a class='del' onclick='delRow(this)'>";
	innerStr += "		x";
	innerStr += "		</a>"; 
	innerStr += "		<input name='idx' type='hidden' id='idx_" + itemRowIndex + "' value='"+itemRowIndex+"' />";
	innerStr += "	</td>"; 
	innerStr += "	<td>";
	innerStr += "		<input name='eMachineCorrectionDate' type='text' id='eMachineCorrectionDate_" + itemRowIndex + "' />";
	innerStr += "	</td>";
	innerStr += "	<td>";
	innerStr += "		<input name='eMachineCorrectionInterval' type='text' id='eMachineCorrectionInterval_" + itemRowIndex + "'/>";
	innerStr += "	</td>";	
	innerStr += "	<td>";
	innerStr += "		<input name='eMachineCorrectionNextDate' type='text' id='eMachineCorrectionNextDate_" + itemRowIndex + "'/>";
	innerStr += "	</td>";
	innerStr += "	<td>";
	innerStr += "		<input name='eMachineCorrectionStudyDate' type='text' id='eMachineCorrectionStudyDate_" + itemRowIndex + "'/>";
	innerStr += "	</td>";	
	innerStr += "	<td>";
	innerStr += "		<input name='eMachineCorrectionAgency' type='text' id='eMachineCorrectionAgency_" + itemRowIndex + "'/>";
	innerStr += "	</td>";	
	innerStr += "	<td>";
	innerStr += "		<input name='eMachineCorrectionEtc' type='text' id='eMachineCorrectionEtc_" + itemRowIndex + "'/>";
	innerStr += "	</td>";	
	innerStr += "</tr>"; 
									
	var row =innerStr;
	$(row).appendTo("#lineRow");
	var clareCalendar = {
			   monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			   dayNamesMin: ['일','월','화','수','목','금','토'],
			   weekHeader: 'Wk',
			   dateFormat: 'yy-mm-dd', //형식(20120303)
			   autoSize: false, //오토리사이즈(body등 상위태그의 설정에 따른다)
			   changeMonth: true, //월변경가능
			   changeYear: true, //년변경가능
			   showMonthAfterYear: true, //년 뒤에 월 표시
			   buttonImageOnly: true, //이미지표시
			   buttonText: '달력선택', //버튼 텍스트 표시
			   buttonImage: '/images/btn/cale_bg.jpg', //이미지주소
			   showOn: "button", //엘리먼트와 이미지 동시 사용(both,button)
			  };

			  $("#eMachineCorrectionDate_" + itemRowIndex).datepicker(clareCalendar);
			  $("#eMachineCorrectionNextDate_" + itemRowIndex).datepicker(clareCalendar);
			  $("#eMachineCorrectionStudyDate_" + itemRowIndex).datepicker(clareCalendar);
			  $("img.ui-datepicker-trigger").attr("style","margin-left:5px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
			  $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김  
	itemRowIndex++;
	
}
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();	//라인삭제	
}
function insert_go(){
	$.ajax({
		method : "post",
		url : "/mes/sulbi/kw_sulbi_correction_i.do" ,
		dataType : "json" ,
		data : $('#repairForm').serialize(),
		success:function(msg){
			var message = msg.result.message;
			alert(message);
			window.close();
		}		
	});
}
</script>



<form id="repairForm" name="repairForm" method="post" enctype="multipart/form-data">
		<input  type="hidden" id="eMachineKey" name="eMachineKey" value="${vov.esulbiKey}" />

		<div class="if_content">
			 <div class="content_tit">
				<h2>[장비명]수리내역 관리</h2>
			</div>
			 <div class="add_row">
				<ul>
					<li><a onclick="addRow();">행추가</a></li>
				</ul>
			</div>
		</div>
	

		<div class="tbl_write_f">
			<table>
				<tbody>
					<tr>
						<th>설비명</th>
						<td>${vov.eMachineName}</td>
					</tr>
					<tr>
						<th>설비번호</th>
						<td>${vov.eMachineNumber}</td>
					</tr>
					<tr>
						<th>제조번호(No)</th>
						<td>${vov.eMachineNo}</td>
					</tr>
					<tr>
						<th>제작회사</th>
						<td>${vov.eMachineProdCompany}</td>
					</tr>
				</tbody>
			</table>
		</div>	
		
		<div class="tbl_top">
			<ul class="tbl_top_left">						
				<li>
					<a onclick='addRow();'>행추가</a>
				</li>
			</ul>	
		</div>	
		
		<div class="tbl_list">
			<table>
				<thead>
					<tr>
						<th colspan="7" style="text-align:center;">계측기 검/교정 계획서 </th>
					</tr>
					<tr>
						<th>구분</th>
						<th>교정일자</th>
						<th>교정주기</th>
						<th>차기 교정일</th>
						<th>교정 계획일</th>
						<th>교정기관</th>
						<th>비고</th>
					</tr>
				</thead>			
				<tbody id="lineRow">		
				</tbody>
			</table>
		</div>
	

	
</form>	

