
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/js/jBox/jBox.all.min.css" rel="stylesheet">
<script src="/js/jBox/jBox.all.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	addRow();
}); 

function insert_go(){
	if(chkIns()){
		modal3("등록하시겠습니까?", function() {
			$("#mloader").show();
			document.frm.submit();	
		});
	}
}

// validation
function chkIns(){
	var ln = document.getElementsByName("sGubunCateKey").length;
		
	if(ln > 0){
		for(var i = 0; i < ln; i++){
			if(document.getElementsByName("sGubunCateKey")[i].value == ""){
				var text = "";
				if(i+1 > 1) {
					text = (i+1) + "번째 "
				}
				modal1(text + "구분을 선택하세요.", "#sGubunCateKey_"+i);
				return false;
			}
			if(document.getElementsByName("sGubunName")[i].value == ""){
				var text = "";
				if(i+1 > 1) {
					text = (i+1) + "번째 "
				}
				modal1(text + "세부항목을 입력하세요.", "#sGubunName_"+i);
				return false;
			}
		}
	}else{
		modal1("등록할 세부항목이 없습니다.");
		return false;
	} 
	return true;
}

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/gubun/kw_gubun_lf.do";
	document.frm.submit(); 
}

// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();		
}

//행추가
var itemRowIndex = 0;

function addRow() {	

	var innerStr = "";
	
	innerStr += "	<tr>";
	innerStr += "		<td>";
	innerStr += "			<a class='del' onclick='delRow(this)'>X</a>";
	innerStr += "		</td>";
	// 구분
	innerStr += "		<td>";
	innerStr += "			<select id='sGubunCateKey_"+itemRowIndex+"' name='sGubunCateKey'>";
	innerStr += "				<option value=''>미구분</option>";
	<c:forEach var="gubunCateList" items="${gubunCateList}" varStatus="i">
	innerStr += "				<option value='${gubunCateList.sGubunCateKey}'>${gubunCateList.sGubunCateName}</option>";
	</c:forEach>
	innerStr += "			</select>"; 
	innerStr += "		</td>";
	// 구분명
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='sGubunName_"+itemRowIndex+"' name='sGubunName' style='width:100%;'  maxlength='20' />";
	innerStr += "		</td>";
	// 비고
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='sGubunEtc_"+itemRowIndex+"' name='sGubunEtc' style='width:100%;' maxlength='20' />";
	innerStr += "		</td>";	
	innerStr += "	</tr>"; 
									
	$(innerStr).appendTo("#lineRow");
	
	itemRowIndex++;
}

</script>

<form id="frm" name="frm" method="post" action="/mes/gubun/kw_gubun_i.do">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesGubunVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesGubunVO.recordCountPerPage}" />
	<input type="hidden" name="searchWord" id="searchWord" value="${mesGubunVO.searchWord}" />
	<input type="hidden" name="searchGubun" id="searchGubun" value="${mesGubunVO.searchGubun}"  />


	<div class="content_top with_btn">
		<div class="content_tit">
			<h2>코드정보 등록</h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addRow();">세부항목 추가</button>
		</div>
	</div>
	<div class="normal_table">
		<table style="width:100%">
			<thead>
		       <tr>
      				<th style="width:6%;">행 삭제</th>
       				<th style="width: 200px;"><span style="color: red">* </span>구분</th>
       				<th><span style="color: red">* </span>세부항목</th>
       				<th>영문표기(약어)</th>
      			</tr>
       		</thead>
  	 		<tbody id="lineRow">
  	 		
			</tbody>
		</table>
	</div>
	
	<div class="bottom_btn">
		<button type="button" class="form_btn active" onclick="insert_go();">등록</button>
		<button type="button" class="form_btn" onclick="cancel();">취소</button>
	</div>
</form>
