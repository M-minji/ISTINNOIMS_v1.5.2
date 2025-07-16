
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	var ln = document.getElementsByName("eFieldName").length;
		
	if(ln > 0){
		for(var i = 0; i < ln; i++){
			const eFieldName = document.getElementsByName("eFieldName")[i].value.trim();
		    const eField1 = document.getElementsByName("eField1")[i].value.trim();
		    const eField2 = document.getElementsByName("eField2")[i].value.trim();
		    const eField3 = document.getElementsByName("eField3")[i].value.trim();
		    const eField4 = document.getElementsByName("eField4")[i].value.trim();
		    const eField5 = document.getElementsByName("eField5")[i].value.trim();
		    var text = "";
		    if(i+1 > 1){
		    	text = (i+1) + "번째 ";
		    }
			if(eFieldName == ""){
				modal1(text + "이름을 입력하세요.", "#eFieldName_"+i);
				return false;
			}
			if(eField1 == ""){
				modal1("모든 필드를 입력하세요.", "#eField1_"+i);
				return false;
			}
			if(eField2 == ""){
				modal1("모든 필드를 입력하세요.", "#eField2_"+i);
				return false;
			}
			if(eField3 == ""){
				modal1("모든 필드를 입력하세요.", "#eField3_"+i);
				return false;
			}
			if(eField4 == ""){
				modal1("모든 필드를 입력하세요.", "#eField4_"+i);
				return false;
			}
			if(eField5 == ""){
				modal1("모든 필드를 입력하세요.", "#eField5_"+i);
				return false;
			}
		}
	}else{
		modal1("등록할 필드가 없습니다.");
		return false;
	} 
	return true;
}

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/inspection/kw_inspection_field_lf.do";
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
	// 이름
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eFieldName_"+itemRowIndex+"' name='eFieldName' style='width:100%;'  maxlength='20' />";
	innerStr += "		</td>";
	// 필드1
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eField1_"+itemRowIndex+"' name='eField1' style='width:100%;'  maxlength='20' />";
	innerStr += "		</td>";
	// 필드2
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eField2_"+itemRowIndex+"' name='eField2' style='width:100%;' maxlength='20' />";
	innerStr += "		</td>";	
	// 필드3
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eField3_"+itemRowIndex+"' name='eField3' style='width:100%;' maxlength='20' />";
	innerStr += "		</td>";	
	// 필드4
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eField4_"+itemRowIndex+"' name='eField4' style='width:100%;' maxlength='20' />";
	innerStr += "		</td>";	
	// 필드5
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='eField5_"+itemRowIndex+"' name='eField5' style='width:100%;' maxlength='20' />";
	innerStr += "		</td>";	
	innerStr += "	</tr>"; 
							
							
									
	$(innerStr).appendTo("#lineRow");
	
	itemRowIndex++;
}

</script>

<form id="frm" name="frm" method="post" action="/mes/inspection/kw_inspection_field_i.do">
	<input type="hidden" name="searchWord" id="searchWord" value="${mesInspectionVO.searchWord}" />


	<div class="content_top with_btn">
		<div class="content_tit">
			<h2>점검필드 등록</h2>
		</div>
		<div class="btns">
			 <button type="button" class="form_btn md" onclick="addRow();">필드 추가</button>
		</div>
	</div>
	<div class="normal_table">
		<table style="width:100%">
			<thead>
		       <tr>
      				<th style="width:6%;">행 삭제</th>
       				<th style="width: 200px;">이름</th>
       				<th>필드1</th>
       				<th>필드2</th>
       				<th>필드3</th>
       				<th>필드4</th>
       				<th>필드5</th>
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
