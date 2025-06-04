<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script type="text/javascript" src="https://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function updateField(){
	
	    const eFieldName = document.getElementById("eFieldName").value.trim();
	    const eField1 = document.getElementById("eField1").value.trim();
	    const eField2 = document.getElementById("eField2").value.trim();
	    const eField3 = document.getElementById("eField3").value.trim();
	    const eField4 = document.getElementById("eField4").value.trim();
	    const eField5 = document.getElementById("eField5").value.trim();

	    if (eFieldName === "") {
	        alert("이름을 입력하세요.");
	        document.getElementById("eFieldName").focus();
	        return false;
	    }

	    if (eField1 === "") {
	        alert("모든 필드를 입력하세요.");
	        document.getElementById("eField1").focus();
	        return false;
	    }

	    if (eField2 === "") {
	        alert("모든 필드를 입력하세요.");
	        document.getElementById("eField2").focus();
	        return false;
	    }
	    
	    if (eField3 === "") {
	        alert("모든 필드를 입력하세요.");
	        document.getElementById("eField3").focus();
	        return false;
	    }
	    
	    if (eField4 === "") {
	        alert("모든 필드를 입력하세요.");
	        document.getElementById("eField4").focus();
	        return false;
	    }
	    
	    if (eField5 === "") {
	        alert("모든 필드를 입력하세요.");
	        document.getElementById("eField5").focus();
	        return false;
	    }
	    

	document.writeForm.action = "/mes/inspection/kw_inspection_field_u.do";
	document.writeForm.submit();
}

function fieldDel() {
	
	$("#eFieldStatus").val("삭제");
	if(confirm("해당 정보를 삭제하시겠습니까?")){
		$("#mloader").show();
		document.writeForm.action = "/mes/inspection/kw_inspection_field_u.do";
		document.writeForm.submit();
	}
	
}

</script>
<form name="writeForm" id="writeForm" method="post">		
	
		<div class="content_top">
			<div class="content_tit">
				<h2>점검결과 필드</h2>
			</div>
		</div>
		
			<div class="normal_table row">
				<table>
					<tbody>
						<tr>
							<th>이름</th>
							<td>
								<input type="text" name="eFieldName" id="eFieldName" maxlength="50" value="${info.eFieldName}" />
								<input type="hidden" name="eFieldKey" id="eFieldKey" value="${info.eFieldKey}" />
								<input type="hidden" name="eFieldStatus" id="eFieldStatus" value="${info.eFieldStatus}" />
							</td>
						</tr>
						<tr>
							<th>필드1</th>
							<td>
								<input type="text" name="eField1" id="eField1"   maxlength="50" value="${info.eField1}" />
							</td>
						</tr>
						<tr>
							<th>필드2</th>
							<td>
								<input type="text" name="eField2" id="eField2"   maxlength="50" value="${info.eField2}" />
							</td>
						</tr>
						<tr>
							<th>필드3</th>
							<td>
								<input type="text" name="eField3" id="eField3"   maxlength="50" value="${info.eField3}" />
							</td>
						</tr>
						<tr>
							<th>필드4</th>
							<td>
								<input type="text" name="eField4" id="eField4"   maxlength="50" value="${info.eField4}" />
							</td>
						</tr>
						<tr>
							<th>필드5</th>
							<td>
								<input type="text" name="eField5" id="eField5"   maxlength="50" value="${info.eField5}" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="bottom_btn">
				<button type="button" class="form_btn active" onclick="updateField();">저장</button>
				<button type="button" class="form_btn bg" onclick="fieldDel();">삭제</button>
				<a class="form_btn" href="/mes/inspection/kw_inspection_field_lf.do" >목록</a>
			</div>
		
</form>