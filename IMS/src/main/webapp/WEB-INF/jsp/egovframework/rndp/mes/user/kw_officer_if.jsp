<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">

$(function() {

	var gubun = $('#eAllChk').val();
	if(gubun == "Y"){
		$('input:checkbox[id="eAllChk"]').prop("checked",true);
	}else{
		$('input:checkbox[id="eAllChk"]').prop("checked",false);
	}
})
	
//등록
function insert_go(){
	InCheck();
	$('#mloader').show();
	document.frm.submit();
}

function cancle(){
	$('#mloader').show();
	document.frm.action = "/mes/user/kw_user_lf.do";
	document.frm.submit(); 
}

function InCheck(){
	var chk = $("input:checkbox[id='eAllChk']").is(":checked");
	
	if(chk == true){
		$("#eAllChk").val("Y");
	}else{
		$("#eAllChk").val("N");
	}
}

var itemRowIndex = "${fn:length(officerItem)}";

//행추가
function addRow(obj) {	
	var innerStr = "";
	innerStr += "	<tr>";
	innerStr += "		<td>";
	innerStr += "			<a onclick='delRow(this,"+itemRowIndex+");' class='del'>X</a>";
	innerStr += "		</td>";	
	innerStr +=	"		<td>";
	/* 품목분류 */
	innerStr +=	"			<span id='sItemCateNameSpan_"+itemRowIndex+"'>"+obj.path+"</span>";	
	innerStr +=	"			<input name='sItemCateName' type='hidden' id='sItemCateName_"+itemRowIndex+"'value='"+obj.path+"'/>";	
	innerStr +=	"		</td>";
	/* 품명 */
	innerStr +=	"		<td>";
	innerStr +=	"			<span id='eItemNameSpan_"+itemRowIndex+"'>"+obj.eItemName+"</span>";	
	innerStr +=	"			<input name='eItemName' type='hidden' id='eItemName_"+itemRowIndex+"'value='"+obj.eItemName+"'/>";	
	innerStr +=	"			<input name='eItemKey' type='hidden' id='eItemKey_"+itemRowIndex+"'value='"+obj.eItemKey+"'/>";	
	innerStr +=	"		</td>";
	innerStr +=	"	</tr>";
										
	var row =innerStr;
	$(row).appendTo("#lineRow");
	
	itemRowIndex++;
}

//행삭제
function delRow(obj, rowIndex){
	var tr = $(obj).parent().parent();
	
	tr.remove();	//라인삭제
}
 
function selectItem(){
	//자재 팝업
	var url = "/mes/buy/ipgo/popup/kw_itemChkList.do"; 
	window.open(url ,"AddrAdd" ,"width=1100,height=840,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

function setItemList2(obj){
	if(obj != null){
		addRow(obj);
	}
}
	
	
</script>

<%-- <jsp:include page="../kw_topmenu_depth2.jsp" flush="true" /> --%>

<form id="frm" name="frm" method="post" action="/mes/user/kw_officer_i.do">
<!-- 	<input type="hidden" id="fileIndex" name="fileIndex" value=""> -->
	
	<div class="content">
		<div class="content_tit">
			<h2>품목관리</h2>
		</div>
	</div>
	
	<div class="tbl_write_f">	
		<table>
			<tbody>
		   		<tr>
		     		<th>직원 명</th>
		     		<td>
		     			<span>${mesUserInfo.mesUserName}</span>
		     			<input type="hidden" id="kStaffName" name="kStaffName" value="${mesUserInfo.mesUserName}" />
		     			<input type="hidden" id="kStaffKey" name="kStaffKey" value="${mesUserInfo.mesUserKey}" />
		     		</td>
		     	</tr>
		     	<tr>	
		     		<th>ID</th>
		     		<td>
		     			<span>${mesUserInfo.mesUserId}</span>
		     		</td>
		     	</tr>
		     	<tr>
		     		<th>품목전체</th>
		     		<td>
		     			<input type="checkbox" id="eAllChk" name="eAllChk" value="${mesUserInfo.kStaffItemGubun}"/>
		     		</td>
		   		</tr>
	   		</tbody>
	   	</table>
	 </div>
	   	
	
	<div class="tbl_top">
		<ul class="tbl_top_left" >
			<li>
				<a onclick="selectItem();" style="cursor: pointer;">품목선택</a>
			</li>
		</ul>
	</div>
	
	<div class="tbl_list">	
      	<table >
        	<thead>
          		<tr>
            		<th style='width: 10%'>구분</th>
            		<th style='width: 20%'>품목분류</th>
            		<th>품명</th>
          		</tr>
        	</thead>
        	<tbody id="lineRow">
        		<c:forEach var="item" items="${officerItem}" varStatus="k">
        			<tr>
        				<td>
        					<a onclick='delRow(this,"+itemRowIndex+");' class='del'>X</a>
        					<input name='eOfficerKey' type='hidden' id='eOfficerKey_${k.index}'value='${item.eOfficerKey}'/>
        				</td>
        				<td>
        					<span>${item.eItemCateName}</span>
        					<input name='sItemCateName' type='hidden' id='sItemCateName_${k.index}'value='${item.eItemCateName}'/>
        				</td>
        				<td>
        					<span>${item.eItemName}</span>
        					<input name='eItemName' type='hidden' id='eItemName_${k.index}'value='${item.eItemName}'/>
        					<input name='eItemKey' type='hidden' id='eItemKey_${k.index}'value='${item.eItemKey}'/>
        				</td>
        			</tr>
        		</c:forEach>
        	</tbody>
		</table>
	</div>
	
	<div class="tbl_btn_right">
		<ul>
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">	
			<li>
         			<a style="cursor: pointer;" onclick="insert_go();">저장</a>
         	</li>
         	</c:if>
			<li>
         		    <a style="cursor: pointer;" onclick="cancle();">취소</a>	
         		</li>
         	</ul>
	</div>
</form>
