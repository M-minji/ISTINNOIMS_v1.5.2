<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript">

// 저장
function update_go(){
	 if(chkIns()){
		if(confirm("저장하시겠습니까?")){
			$("#mloader").show();
			document.getElementsByName("sItemPrice")[0].value = uncomma(document.getElementsByName("sItemPrice")[0].value);
			document.frm.action = "/mes/item/sub/kw_sub_u.do";
			document.frm.submit();
		}
	}  
}

// validation
function chkIns(){
	if($("#sItemCateKey").val() == ""){
		alert("자재분류를 선택하세요.");
		$("#sItemCatePath").focus();
		return false;
	}
	if($("#codeTxt").text() == ""){
		alert("코드생성을 눌러주세요.");
		return false;
	} 
	
	if($("#specialCode").val() == ""){
		alert("특수코드를 입력해주세요.");
		return false;
	} 
	if($("#sItemName").val() == ""){
		alert("자재명을 입력하세요.");
		$("#sItemName").focus();
		return false;
	}
	if($("#sItemStandard").val() == ""){
		alert("규격을 입력하세요.");
		$("#sItemStandard").focus();
		return false;
	}
	if($("#sItemPrice").val() == "" || $("#sItemPrice").val() == "0"){
		alert("단가를 입력해주세요.");
		return false;
	}
	if($("#sItemMoneyUnit").val() == ""){
		alert("화폐단위를 선택하세요.");
		return false;
	}
	
	if($("#sItemUnit").val() == ""){
		alert("자재단위를 선택하세요.");
		$("#sItemUnit").focus();
		return false;
	}
	//코드번호
	$("#sItemCode").val($("#codeTxt").text());
	
	return true;
}

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/item/sub/kw_sub_lf.do";
	document.frm.submit();
}

// 자재분류 팝업
function catePopup(){
	var url = "/mes/item/popup/kw_cate_lf.do";
	window.open(url ,"cateList","width=850,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

// 자재분류 반환
function setCatePop(sItemCateKey, sItemCateName, sItemCatePath, middleCate, logoCode){
	//코드생성여부 체크
	codeReSet();
	
	$("#sItemCateKey").val(sItemCateKey);
	$("#sItemCateName").val(sItemCateName);
	$("#sItemCatePath").val(sItemCatePath);
	$("#logoCode").val(logoCode);
	$("#middleCate").val(middleCate);

}

function gubunSelect(){
	var target = document.getElementById("sItemUnit");
	if(target.options[target.selectedIndex].text == "선택"){
		$("#sItemUnitTxt").val("");
	}else{
		$("#sItemUnitTxt").val(target.options[target.selectedIndex].text);
	}
}

function moneySelect(){
	var target = document.getElementById("sItemMoneyUnit");
	if(target.options[target.selectedIndex].text == "선택"){
		$("#sItemMoneyUnitTxt").val("");
	}else{
		$("#sItemMoneyUnitTxt").val(target.options[target.selectedIndex].text);
	}
}
//중복체크
function codeCheck(){
	var target = document.getElementById("mainCate");
	var subTarget = document.getElementById("specialCode");
	var itemCate = "sItem";
	
	if($("#mainCate").val() == ""){
		alert("대분류을 확인해주세요.");
		return false;
	}
	if($("#middleCate").val() == ""){
		alert("품목분류명을 확인해주세요.");
		return false;
	}
	if($("#logoCode").val() == ""){
		alert("LOGO CODE를 등록해주세요.");
		return false;
	}
	if($("#specialCode").val() == ""){
		alert("특수코드를 등록해주세요.");
		return false;
	}
	
	var mainCate = $("#mainCate").val();
	var middleCate = $("#middleCate").val();
	var logoCode = $("#logoCode").val();
	var specialCode = $("#specialCode").val();
	
	
	$.ajax({
		url : "/mes/item/kw_codeSelectAjax.do"
	,	data :  {"mainCate": mainCate,"itemCate": itemCate, "middleCate":middleCate , "logoCode" : logoCode}
	,	dataType : "json"
	,	async : false
	,	cache : false
	,	success : function(data){
			var count = data.result.count;
			if(target.options[target.selectedIndex].value == "${subInfo.mainCate}" && $("#middleCate").val() == "${subInfo.middleCate}" && $("#logoCode").val() == "${subInfo.logoCode}"){
				$("#codeTxt").text("${subInfo.codeTxt}");
			}else{
				//$("#codeTxt").text(target.options[target.selectedIndex].value+"-"+$("#middleCate").val()+"-"+$("#logoCode").val()+"-"+count+"-");
				$("#codeTxt").text(mainCate+middleCate+logoCode+count+specialCode);
				$("#newCodeYN").val("Y");
			}
		}
	});
}
function setComma(){
	document.getElementsByName("sItemPrice")[0].value = comma(document.getElementsByName("sItemPrice")[0].value);
}

function codeReSet(){
	var gbn = $("#newCodeYN").val();
	if(gbn != 'N'){
		alert("코드생성내역이 변경되었습니다. 코드생성을 다시 해주세요");
		$("#codeTxt").text("");
		return false;
	}
}

</script>

<form id="frm" name="frm" method="post" action="/mes/item/kw_sub_item_i.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesSubVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesSubVO.recordCountPerPage}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesSubVO.searchType}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesSubVO.searchWord}" />
	
	<input type="hidden" id="sItemKey" name="sItemKey" value="${subInfo.sItemKey}" />
	
	<input type="hidden" id="logoCode" name="logoCode" value="${subInfo.logoCode}" />
	<input type="hidden" id="sItemCode" name="sItemCode" value="${subInfo.sItemCode}" />
	<input type="hidden" id="middleCate" name="middleCate" value="${subInfo.middleCate}" />
	<!-- 생성되어있으므로  -->
	<input type="hidden" id="newCodeYN" name="newCodeYN" value="Y" />
	
	<div class="content">
		<div class="content_tit">
			<h2>자재관리 수정</h2>
		</div>
	</div>
		
	<div class="tbl_write_f">
		<table>
	  		<tbody>
	  			<tr>
		    		<th>*대분류</th>
		    		<td>
		    			<select id="mainCate" name="mainCate" onchange="codeReSet();">
		    				<option value="" <c:if test="${subInfo.mainCate eq ''}">selected</c:if>>선택</option>
		    				<option value="P" <c:if test="${subInfo.mainCate eq 'P'}">selected</c:if>>P:Plastic Magnet</option>
		    				<option value="N" <c:if test="${subInfo.mainCate eq 'N'}">selected</c:if>>N:Nd Magnet</option>
		    				<option value="R" <c:if test="${subInfo.mainCate eq 'R'}">selected</c:if>>R:Rubber Magnet</option>
		    				<option value="O" <c:if test="${subInfo.mainCate eq 'O'}">selected</c:if>>O:기타 아이템</option>
		    			</select>
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>*자재분류</th>
		    		<td>
		    			<input type="hidden" id="sItemCateKey"  name="sItemCateKey" value="${subInfo.sItemCateKey}" />
		    			<input type="hidden" id="sItemCateName" name="sItemCateName" value="${subInfo.sItemCateName}" />
		    			<input type="text"  class="inp_color" id="sItemCatePath" name="sItemCatePath" value="${subInfo.sItemCatePath}" readonly />
		    			<a class="mes_btn" onclick="catePopup();">분류선택</a>
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>*특수코드</th>
		    		<td>
		    			<select id="specialCode" name="specialCode" onchange="codeReSet();">
		    				<option value="" <c:if test="${subInfo.specialCode eq ''}">selected</c:if>>선택</option>
		    				<option value="A" <c:if test="${subInfo.specialCode eq 'A'}">selected</c:if>>A:사급</option>
		    				<option value="B" <c:if test="${subInfo.specialCode eq 'B'}">selected</c:if>>B:도급</option>
		    				<option value="C" <c:if test="${subInfo.specialCode eq 'C'}">selected</c:if>>C:해당없음</option>
		    			</select>
		    			<a class="mes_btn" onclick="codeCheck();">코드생성</a>
		    		</TD>
		    	</TR>
		  		<tr>
		    		<th>*자재코드</th>
		    		<td>
		    			<span id="codeTxt">${subInfo.codeTxt}</span>
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>*자재명</th>
		    		<td>
		    			<input type="text" id="sItemName" name="sItemName" value="${subInfo.sItemName}" maxlength="50" />
		    		</td>
		  		</tr>
	  		
		  		<tr>
		    		<th>*규격</th>
		    		<td>
		    			<input type="text" id="sItemStandard" name="sItemStandard" value="${subInfo.sItemStandard}" maxlength="50" />
		    		</td>
		  		</tr>
				<tr>
					<th>*단가</th>
					<td>
						<input type="text" style="text-align: right" id="sItemPrice" name="sItemPrice" value="<fmt:formatNumber value="${subInfo.sItemPrice}" pattern="#,##0.0000" />" maxlength="20"  onkeypress='return isNumberKey(event);' onblur="setComma()"/>
						<input type="hidden" id="sItemMoneyUnitTxt" name="sItemMoneyUnitTxt" value="${subInfo.sItemMoneyUnitTxt}" />
						<select id="sItemMoneyUnit" name="sItemMoneyUnit" onchange="moneySelect();">
		    				<option value="">선택</option>
		    				<c:forEach var="moneyList" items="${moneyList}" varStatus="i">
		    					<option value="${moneyList.sGubunKey}" <c:if test="${moneyList.sGubunKey eq subInfo.sItemMoneyUnit}">selected</c:if>>${moneyList.sGubunName}</option>
		    				</c:forEach>
		    			</select>
					</td>
				</tr>
		  		<tr>
		    		<th>*자재단위</th>
		    		<td>
		    			<input type="hidden" id="sItemUnitTxt" name="sItemUnitTxt" value="${subInfo.sItemUnitTxt}"/>
		    			<select id="sItemUnit" name="sItemUnit" onchange="gubunSelect();">
		    				<option value="">선택</option>
		    				<c:forEach var="gubunList" items="${gubunList}" varStatus="i">
		    					<option value="${gubunList.sGubunKey}" <c:if test="${gubunList.sGubunKey eq subInfo.sItemUnit}">selected</c:if>>${gubunList.sGubunName}</option>
		    				</c:forEach>
		    			</select>
		    		</td>
		  		</tr>		  		
		  		<tr>
		    		<th>적정재고</th>
		    		<td>
		    			<input type="text" class="ssp" id="sItemInven" name="sItemInven" value="${subInfo.sItemInven}" maxlength="10" onkeypress="return isNumberKey(event);" onkeyup="return delHangle(event);" />
		    		</td>
		  		</tr>		  		
		  		<tr>
		    		<th>비고</th>
		    		<td>
		    			<input type="text" id="sItemEtc" name="sItemEtc" value="${subInfo.sItemEtc}" maxlength="100" />
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>업체명</th>
		    		<td>
		    			<input type="text" id="sItemCompanyName" name="sItemCompanyName" value="${subInfo.sItemCompanyName}" maxlength="100" />
		    		</td>
		  		</tr>
		  	</tbody>
		</table>
	</div>
	
	<div class="tbl_btn_right">
		<ul>
			<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="update_go();">저장</a>
	         	</li>
         	</c:if>
			<li>
				<a style="cursor: pointer;" onclick="cancel();">목록</a>	
       		</li>
		</ul>
	</div>
	
</form>
