<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

$(document).ready(function(){
	addRow();
});

function insert_go(){
	if(chkIns()){		
		if(confirm("저장하시겠습니까?")){
			$.ajax({
				method : "post",
				url : "/mes/gubun/kw_gubunCheckAjax.do" ,			
				dataType : "json",
				data : $("#frm").serialize(),
				success : function(msg){
					var cnt = msg.result.resultCnt;
					if(cnt != 0){
						alert("이미 등록된 구분명입니다.");
						return false;
					}
					else{
						$("#mloader").show();
						document.frm.submit();
					}		
				}
			});
		}
	}
}

// validation
function chkIns(){
	var ln = document.getElementsByName("sGubunCateName").length;
	if(ln > 0){
		for(var i = 0; i < ln; i++){
			if(document.getElementsByName("sGubunCateName")[i].value == ""){
				alert((i+1)+"번째 구분을 입력하세요.");
				document.getElementsByName("sGubunCateName")[i].focus();
				return false;
			}			
		}
	}else{
		alert("등록할 행이 없습니다.");
		return false;
	}
	return true;
}

// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();		
}

var itemRowIndex = 0;

function addRow() {	
	
	var innerStr = "";
	
	innerStr += "	<tr>";
	innerStr += "		<td>";
	innerStr += "			<a class='del' onclick='delRow(this);'>X</a>";
	innerStr += "		</td>";
	// 구분
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='sGubunCateName_"+itemRowIndex+"' name='sGubunCateName' maxlength='20'  />";
	innerStr += "		</td>";
	// 비고
	innerStr += "		<td>";
	innerStr += "			<input type='text' id='sGubunCateEtc_"+itemRowIndex+"' name='sGubunCateEtc' maxlength='20'  />";
	innerStr += "		</td>";
	innerStr += "	</tr>"; 
	
	$(innerStr).appendTo("#lineRow");
	
	itemRowIndex++;
}

// 구분 목록
function cancel(){
	$("#mloader").show();		
	document.frm.action = "/mes/gubun/gubunCate/kw_gubunCate_lf.do";
	document.frm.submit();
}

</script>

<form id="frm" name="frm" method="post" action="/mes/gubun/gubunCate/kw_gubunCate_i.do">
<input type="hidden" name="pageIndex" id="pageIndex" value="${mesGubunCateVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesGubunCateVO.recordCountPerPage}" />
	<input type="hidden" name="searchWord" id="searchWord" value="${mesGubunCateVO.searchWord}" />

	<div class="if_content">
		<div class="content_tit">
			<h2>구분관리 등록</h2>
		</div>
		
		<div class="tbl_top">
	    	<ul class="tbl_top_right">						
	    		<li>
	    			<a style="cursor: pointer;" onclick="addRow();" >
	    				행추가
	    			</a>
	    		</li>
			</ul>	    
	    </div>
	</div>
	
	<div class="tbl_list">
		<table style="width:100%">
			<thead>
       			<tr>
        	 		<th style="width:6%;"></th>
        	 		<th>구분</th>
        	 		<th>비고</th>
       			</tr>
     		</thead>
			<tbody id="lineRow">
			   
	  		</tbody>
		</table>
	</div>
	
	<div class="tbl_btn_right">
		<ul>
			<li>
				<a style="cursor: pointer;" onclick="insert_go();">등록</a> 
			</li>
			<li>
				 <a style="cursor: pointer;" onclick="cancel();">목록</a> 
			</li>
		</ul>
	</div>		
</form>
