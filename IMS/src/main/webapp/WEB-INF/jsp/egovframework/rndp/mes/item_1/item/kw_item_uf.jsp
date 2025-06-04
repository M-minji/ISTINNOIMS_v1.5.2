<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">

// 저장
function update_go(){
	 if(chkIns()){
		if(confirm("저장하시겠습니까?")){
			document.getElementsByName("eItemPrice")[0].value = uncomma(document.getElementsByName("eItemPrice")[0].value);
			$("#mloader").show();
			document.frm.action = "/mes/item/item/kw_item_u.do";
			document.frm.submit();
		}
	}  
}

// validation
function chkIns(){
	if($("#eItemCateKey").val() == ""){
		alert("제품분류를 선택하세요.");
		$("#eItemCatePath").focus();
		return false;
	}
	if($("#eItemName").val() == ""){
		alert("모델명을 입력하세요.");
		$("#eItemName").focus();
		return false;
	}
	if($("#eItemStandard").val() == ""){
		alert("규격을 입력하세요.");
		$("#eItemStandard").focus();
		return false;
	}
	if($("#eItemUnit").val() == ""){
		alert("제품단위를 선택하세요.");
		$("#eItemUnit").focus();
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
	//코드세팅
	$("#eItemCode").val($("#codeTxt").text());
	
	var lns = document.getElementsByName("eItemOutPriceProcessKey").length;
	if(lns > 0){
		for(var k =0; k<lns; k++){
			if(document.getElementsByName("eItemOutPriceProcessKey")[k].value=="" || document.getElementsByName("eItemOutProcessPrice")[k].value==""){
				alert ("공정을 선택하지 않았거나 가격을 입력하지 않았습니다.");
				return false;
			}
		}
	}
	return true;
}

// 목록
function cancel(){
	$("#mloader").show();
	document.frm.action = "/mes/item/item/kw_item_lf.do";
	document.frm.submit();
}

// 제품분류 팝업
function itemPopup(){
	var url = "/mes/item/popup/kw_item_lf.do";
	window.open(url ,"itemList","width=850,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

// 제품분류 반환
function setItemPop(eItemCateKey, eItemCateName, eItemCatePath, middleCate, logoCode){
	
	//코드생성여부 체크
	codeReSet();
	
	$("#eItemCateKey").val(eItemCateKey);
	$("#eItemCateName").val(eItemCateName);
	$("#eItemCatePath").val(eItemCatePath);
	$("#logoCode").val(logoCode);
	$("#middleCate").val(middleCate);

}

function gubunSelect(){
	var target = document.getElementById("eItemUnit");
	if(target.options[target.selectedIndex].text == "선택"){
		$("#eItemUnitTxt").val("");
	}else{
		$("#eItemUnitTxt").val(target.options[target.selectedIndex].text);
	}
}
//중복체크
function codeCheck(){
	var target = document.getElementById("mainCate");
	var subTarget = document.getElementById("specialCode");
	var itemCate = "eItem";
	
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
			if(target.options[target.selectedIndex].value == "${itemInfo.mainCate}" && $("#middleCate").val() == "${itemInfo.middleCate}" && $("#logoCode").val() == "${itemInfo.logoCode}"){
				$("#codeTxt").text("${itemInfo.codeTxt}");
			}else{
				//$("#codeTxt").text(target.options[target.selectedIndex].value+"-"+$("#middleCate").val()+"-"+$("#logoCode").val()+"-"+count+"-");
				$("#codeTxt").text(mainCate+middleCate+logoCode+count+specialCode);
				$("#newCodeYN").val("Y");
			}
		}
	});
}
// 행삭제
function delRow(obj){
	var tr = $(obj).parent().parent();
	tr.remove();

	chkSum();
}
// 선택박스 key값+name값 세팅
function setSelect(obj){
	var temp1 = $(obj).attr("name");
	var temp2 = $(obj).attr("name").split("_")[0];
	var ln = document.getElementsByName(temp2).length;

	if(temp1 != temp2 && ln > 0){
		var target = document.getElementsByName(temp2+"_All")[0];
		var gubunVal = target.options[target.selectedIndex].value;
		var gubunText = target.options[target.selectedIndex].innerText;

		for(var i = 0; i < ln; i++){
			document.getElementsByName(temp2)[i].value = gubunVal;
			if(document.getElementsByName(temp2)[i].previousElementSibling != null){
				document.getElementsByName(temp2)[i].previousElementSibling.value = gubunText;
			}
		}
	}else if(temp1 == temp2 && ln > 0){
		for(var i = 0; i < ln; i++){
			var target = document.getElementsByName(temp2)[i];
			var value = target.options[target.selectedIndex].value;
			var text = target.options[target.selectedIndex].innerText;

			document.getElementsByName(temp2)[i].value = value;
			if(document.getElementsByName(temp2)[i].previousElementSibling != null){
				document.getElementsByName(temp2)[i].previousElementSibling.value = text;
			}

			var k = 0;
			var cnt = 0;
			for (var j = 0; j < ln; j++) {
				if ($("select[name=eItemOutPriceProcessKey]").eq(i).val() == $("select[name=eItemOutPriceProcessKey]").eq(j).val()) {
					if($("select[name=eItemOutPriceProcessKey]").eq(i).val() != ""){
						k = j;
						cnt += 1;
					}
				}
			}
			if (cnt > 1) {
				alert("중복되는 공정이 있습니다.");
				document.getElementsByName("eItemOutPriceProcessKey")[k].value = "";
				return false;
			}

		}
	}
}
var rowIndex=document.getElementsByName("eItemOutPriceProcessKey").length;

function fnc_addProcess(){
	var innerStr = "";
	// 구분(행삭제)
	innerStr += "	<tr>";
	innerStr += "		<th style='text-align: center'>";
	innerStr += "			<a class='del' onclick='delRow(this);'>X</a>";
	innerStr += "		</th>";
	// 공정선택
	innerStr += "		<td style='width: 8%'>";
	innerStr += "		<input type='hidden' id='eItemOutProcessName_"+rowIndex+"' name='eItemOutProcessName' value=''>";
	innerStr += "			<select id='eItemOutPriceProcessKey_"+rowIndex+"' name='eItemOutPriceProcessKey' onchange='setSelect(this)'>";
	innerStr += "			<option value='' >공정선택</option>";
	<c:forEach items="${gubunList7}" var="gubunList7">
	innerStr += "			<option value='${gubunList7.sGubunKey}' >${gubunList7.sGubunName}</option>";
	</c:forEach>
	innerStr += "			</select>";
	innerStr += "			</td>";
	innerStr += "			<td colspan='2'>";
	// 왼주단가
	innerStr += "			<input type='text' style='text-align: right' id='eItemOutProcessPrice_"+rowIndex+"' name='eItemOutProcessPrice' value='' style='width:10%;' maxlength='100' />";
	innerStr += "		</td>";

	innerStr += "</tr>";

	$(innerStr).appendTo("#lineRow1");

	rowIndex++;
}

function setComma(){
	document.getElementsByName("eItemPrice")[0].value = comma(document.getElementsByName("eItemPrice")[0].value);
}

function moneySelect(){
	var target = document.getElementById("eItemMoneyUnit");
	if(target.options[target.selectedIndex].text == "선택"){
		$("#eItemMoneyUnitTxt").val("");
	}else{
		$("#eItemMoneyUnitTxt").val(target.options[target.selectedIndex].text);
	}
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

<form id="frm" name="frm" method="post" action="/mes/item/item/kw_item_lf.do">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesItemVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesItemVO.recordCountPerPage}" />
	<input type="hidden" id="searchType" name="searchType" value="${mesItemVO.searchType}" />
	<input type="hidden" id="searchWord" name="searchWord" value="${mesItemVO.searchWord}" />
	
	<input type="hidden" id="eItemKey" name="eItemKey" value="${itemInfo.eItemKey}" />
	
	<input type="hidden" id="logoCode" name="logoCode" value="${itemInfo.logoCode}" />
	<input type="hidden" id="eItemCode" name="eItemCode" value="${itemInfo.eItemCode}" />
	<input type="hidden" id="middleCate" name="middleCate" value="${itemInfo.middleCate}" />
	<!-- 생성되어있으므로  -->
	<input type="hidden" id="newCodeYN" name="newCodeYN" value="Y" />
	
	<div class="content">
		<div class="content_tit">
			<h2>제품관리 수정</h2>
		</div>
	</div>
		
	<div class="tbl_write_f">
		<table>
	  		<tbody>
	  			<tr>
		    		<th>*대분류</th>
		    		<td colspan="3">
		    			<select id="mainCate" name="mainCate" onchange="codeReSet();">
		    				<option value="" <c:if test="${itemInfo.mainCate eq ''}">selected</c:if>>선택</option>
		    				<option value="P" <c:if test="${itemInfo.mainCate eq 'P'}">selected</c:if>>P:Plastic Magnet</option>
		    				<option value="N" <c:if test="${itemInfo.mainCate eq 'N'}">selected</c:if>>N:Nd Magnet</option>
		    				<option value="R" <c:if test="${itemInfo.mainCate eq 'R'}">selected</c:if>>R:Rubber Magnet</option>
		    				<option value="O" <c:if test="${itemInfo.mainCate eq 'O'}">selected</c:if>>O:기타 아이템</option>
		    			</select>
		    		</td>
		  		</tr>
		  		<tr>
		    		<th>*제품분류</th>
					<td colspan="3">
		    			<input type="hidden" id="eItemCateKey"  name="eItemCateKey" value="${itemInfo.eItemCateKey}" />
		    			<input type="hidden" id="eItemCateName" name="eItemCateName" value="${itemInfo.eItemCateName}" />
		    			<input type="text"  class="inp_color" id="eItemCatePath" name="eItemCatePath" value="${itemInfo.eItemCatePath}" readonly />
		    			<a class="mes_btn" onclick="itemPopup();">분류선택</a>
		    		</td>
		  		</tr>		
		  		<tr>
		    		<th>*특수코드</th>
		    		<td>
		    			<select id="specialCode" name="specialCode" onchange="codeReSet();">
		    				<option value="" <c:if test="${itemInfo.specialCode eq ''}">selected</c:if>>선택</option>
		    				<option value="A" <c:if test="${itemInfo.specialCode eq 'A'}">selected</c:if>>A:사급</option>
		    				<option value="B" <c:if test="${itemInfo.specialCode eq 'B'}">selected</c:if>>B:도급</option>
		    				<option value="C" <c:if test="${itemInfo.specialCode eq 'C'}">selected</c:if>>C:해당없음</option>
		    			</select>
		    			<a class="mes_btn" onclick="codeCheck();">코드생성</a>
		    		</TD>
		    	</TR>  		
		  		<tr>
		    		<th>*제품코드</th>
					<td colspan="3">
		    			<span id="codeTxt">${itemInfo.codeTxt}</span>
		    		</td>
		  		</tr>	  
		  		<tr>
		    		<th>*모델명</th>
					<td colspan="3">
		    			<input type="text" id="eItemName" name="eItemName" value="${itemInfo.eItemName}" maxlength="50" />
		    		</td>
		  		</tr>		
		  		<tr>
		    		<th>*규격</th>
					<td colspan="3">
		    			<input type="text" id="eItemStandard" name="eItemStandard" value="${itemInfo.eItemStandard}" maxlength="50" />
		    		</td>
		  		</tr>
				<tr>
					<th>기본 단가</th>
					<td>
						<input type="text" id="eItemPrice" style="text-align: right" name="eItemPrice" value="${itemInfo.eItemPrice}" maxlength="20"   onkeypress='return isNumberKey(event);' onblur="setComma()"/>
						<input type="hidden" id="eItemMoneyUnitTxt" name="eItemMoneyUnitTxt" value="${itemInfo.eItemMoneyUnitTxt}" />
		    			<select id="eItemMoneyUnit" name="eItemMoneyUnit" onchange="moneySelect();">
		    				<option value="">선택</option>
		    				<c:forEach var="moneyList" items="${moneyList}" varStatus="i">
		    					<option value="${moneyList.sGubunKey}" <c:if test="${moneyList.sGubunKey eq itemInfo.eItemMoneyUnit}">selected</c:if>>${moneyList.sGubunName}</option>
		    				</c:forEach>
		    			</select>
					</td>
					<%-- <th>원가</th>
					<td>
						<fmt:formatNumber value="${itemInfo.eItemCost}" pattern="#,###" /> <c:out value="${itemList.eItemMoneyUnitTxt}" />
					</td> --%>
				</tr>
		  		<tr>
		    		<th>*제품단위</th>
					<td colspan="3">
		    			<input type="hidden" id="eItemUnitTxt" name="eItemUnitTxt" value="${itemInfo.eItemUnitTxt}" />
		    			<select id="eItemUnit" name="eItemUnit" onchange="gubunSelect();">
		    				<option value="">선택</option>
		    				<c:forEach var="gubunList" items="${gubunList}" varStatus="i">
		    					<option value="${gubunList.sGubunKey}" <c:if test="${gubunList.sGubunKey eq itemInfo.eItemUnit}">selected</c:if>>${gubunList.sGubunName}</option>
		    				</c:forEach>
		    			</select>
		    		</td>
		  		</tr>		  		
		  		<tr>
		    		<th>적정재고</th>
					<td colspan="3">
		    			<input type="text" id="eItemInven" name="eItemInven" value="${itemInfo.eItemInven}" maxlength="10" onkeypress="return isNumberKey(event);" onkeyup="return delHangle(event);" />
		    		</td>
		  		</tr>		  		
		  		<tr>
		    		<th>비고</th>
					<td colspan="3">
		    			<input type="text" id="eItemEtc" name="eItemEtc" value="${itemInfo.eItemEtc}" maxlength="100" />
		    		</td>
		  		</tr>
		  		<!-- 
				<tr>
					<th>외주 단가</th>
					<td COLSPAN="3">
						<a class="mes_btn" onclick="fnc_addProcess()">공정추가</a>
					</td>
				</tr>
				<tr>
		    		<th>업체명</th>
					<td colspan="3">
		    			<input type="text" id="eItemCompanyName" name="eItemCompanyName" value="${itemInfo.eItemCompanyName}" maxlength="100" />
		    		</td>
		  		</tr>
		  	</tbody>
			<tbody id="lineRow1">
			<c:forEach items="${itemInfo.outPriceList}" var="outPriceList" varStatus="i">
				<tr>
					<th style="text-align: center">
						<a class='del' onclick='delRow(this);'>X</a>
					</th>
					<td style="width: 8%">
						<input type='hidden' id='eItemOutProcessName_${i.index}' name='eItemOutProcessName' value='${outPriceList.eItemOutProcessName}'>
						<select id="eItemOutPriceProcessKey_${i.index}"  name="eItemOutPriceProcessKey"  onchange='setSelect(this)'>
							<option value='' >공정선택</option>
							<c:forEach items="${gubunList7}" var="gubunList7" varStatus="j">
								<option value="${gubunList7.sGubunKey}" <c:if test="${gubunList7.sGubunKey eq outPriceList.eItemOutPriceProcessKey}">selected</c:if>> ${gubunList7.sGubunName}</option></option>
							</c:forEach>
							${outPriceList.eItemOutProcessName}
						</select>
					</td>
					<td  colspan="2">
						<input  style="text-align: right" type='text' id='eItemOutProcessPrice_${i.index}' name='eItemOutProcessPrice' value='${outPriceList.eItemOutProcessPrice}' style='width:10%;' maxlength='100' />
							 <c:out value="${itemList.eItemMoneyUnitTxt}" />
					</td>
				</tr>
			</c:forEach>
			</tbody>
			 -->
			<tbody>
				<tr>
		    		<th>포장사양서</th>
					<td colspan="3">
		    			<textarea rows="15" style="width: 99%; resize: none;" id="eItemPacking" name="eItemPacking">${itemInfo.eItemPacking}</textarea>
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
