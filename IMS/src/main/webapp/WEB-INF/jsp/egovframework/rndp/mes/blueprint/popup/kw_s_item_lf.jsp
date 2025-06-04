<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script type="text/javascript">

function selectItem() {
	var ln = document.getElementsByName("sItemKey").length;
	var cln = $("input[name=chk]:checked").length;
	
	if(cln > 0){
		for(var i = 0; i < ln; i++){
			if($("#chk_"+i).is(":checked")){
				
				var sItemKey					=  $("#sItemKey_"+i).val();
				var sItemCode					=  $("#sItemCode_"+i).val();
				var sItemName					=  $("#sItemName_"+i).val();
				var sItemStandard				=  $("#sItemStandard_"+i).val();
				var sItemCateKey				=  $("#sItemCateKey_"+i).val();
				var sItemCatePath				=  $("#sItemCatePath_"+i).val();
				var sItemUnit					=  $("#sItemUnit_"+i).val();
				var sItemUnitTxt				=  $("#sItemUnitTxt_"+i).val();

				if(opnerValueChk(sItemKey, i) == true){
					var obj = {
							sItemKey 			:	sItemKey,
							sItemCode			:	sItemCode,
							sItemName			:	sItemName,
							sItemStandard		:	sItemStandard,
							sItemCateKey 		:	sItemCateKey,
							sItemCatePath		:	sItemCatePath,
							sItemUnit		:	sItemUnit,
							sItemUnitTxt		:	sItemUnitTxt,
					};
					  if(typeof(opener.setsItemPop) != "undefined"){
						window.opener.setsItemPop(obj);
						window.close();
					}   
				}
			} 
		}
	}else{
		alert("자재를 먼저 선택하세요");
	}
}
	
function opnerValueChk(sItemKeyTmp, idx) {
	var ln = opener.document.getElementsByName("sItemKey").length;
	
	if(ln > 0){
		for(var i = 0; i < ln; i++){
			var openersItemKey = opener.document.getElementsByName("sItemKey")[i].value;
			if(sItemKeyTmp == openersItemKey){
				alert($("#cnt_"+idx).val()+"번째  품목은  이미 선택된 품목입니다.");										
				return false;
			}
		}
	}
	return true;
}	
	
//전체선택
function check(){
	if($("input[name=chkAll]").is(":checked")){
		$("input[name=chk]").attr("checked", true);
	}else{
		$("input[name=chk]").attr("checked", false);
	}
}

// 검색
function fn_guestList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "/mes/bom/popup/kw_s_item_lf.do";
	document.frm.submit();
}

//행선택
function checkItem(idx){
	if($("input[id=chk_"+idx+"]").is(":checked")){
		$("input[id=chk_"+idx+"]").attr("checked", false);
	}else{
		$("input[id=chk_"+idx+"]").attr("checked", true);
	}
}

//검색 ENTER
function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

</script>

<form id="frm" name="frm" method="post" action="/mes/bom/popup/kw_s_item_lf.do">
	<input type="hidden" name="pageIndex"  id="pageIndex" value="${mesBomVO.pageIndex}">

		<div class="pop_head">
			<div id="pop_head">
				<div class="tit">
					<h3>자재 조회</h3>
				</div>
				<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
			</div>
		</div> 

		<div class="popup_content">	
			<div id="itemCateZone" class="tbl_top"> 
				<ul class="tbl_top_left">
					<li>
		          		<select name="recordCountPerPage" class="select_recordCountPerPage" id="recordCountPerPage"  onchange="fn_guestList(1)">
		          			<option value="10" <c:if test="${mesBomVO.recordCountPerPage eq 10}">selected="selected"</c:if>>Page/10</option>
		              		<option value="20" <c:if test="${mesBomVO.recordCountPerPage eq 20}">selected="selected"</c:if>>Page/20</option>
		              		<option value="50" <c:if test="${mesBomVO.recordCountPerPage eq 50}">selected="selected"</c:if>>Page/50</option>
		              		<option value="100" <c:if test="${mesBomVO.recordCountPerPage eq 100}">selected="selected"</c:if>>Page/100</option>
	       				</select> 
			   		</li>
					<li>
						<select name="searchType" class="select_search" id="searchType" >
		       				<option value="1" <c:if test="${mesBomVO.searchType eq 1}">selected="selected"</c:if>>전체조회</option>
		       				<option value="2" <c:if test="${mesBomVO.searchType eq 2}">selected="selected"</c:if>>자재코드</option>
		       				<option value="3" <c:if test="${mesBomVO.searchType eq 3}">selected="selected"</c:if>>자재분류</option>
		       				<option value="4" <c:if test="${mesBomVO.searchType eq 4}">selected="selected"</c:if>>자재명</option>
		     			</select>
					</li>
					<li>
						<input name="searchWord" type="text" class="searchWord" id="searchWord" value="${mesBomVO.searchWord}" onKeydown="fn_keyDown()" />
					</li>
					<li>
						<a onclick="fn_guestList(1)">검색</a>
					</li>
				</ul>
				<ul class="tbl_top_right">
					<li>
						<a onclick="selectItem();">선택</a>
					</li>
				</ul>
			</div>	
		
			<div class="lf_tbl_list" id="pop_result_list">
				<table>
			        <thead>
						<tr style="width:100%">
							<th style="width:3%">
								<input type="checkbox" id="chkAll" name="chkAll" onclick="check();">
							</th>
			      			<th style="width: 12%;">자재분류경로</th>
			      			<th style="width: 10%;">자재코드</th>
			      			<th style="width: 12%;">자재명</th>
			      			<th style="width: 10%;">규격</th>
			      			<th style="width: 6%;">단위</th>
						</tr>
			        </thead>
			        <tbody>
	 					<c:forEach var="sItemList" items="${sItemList}" varStatus="i">
							<tr style="cursor: pointer;" onclick="checkItem('${i.index}');">
								<td onclick="event.cancelBubble = true;">
				        			<input type="checkbox" id="chk_${i.index}" name="chk">
 									<input type="hidden" id="cnt_${i.index}" name="cnt" value="${i.index + 1}" />
									
									<input type="hidden" id="sItemKey_${i.index}" name="sItemKey" value="${sItemList.sItemKey}" />
									<input type="hidden" id="sItemCateKey_${i.index}" name="sItemCateKey" value="${sItemList.sItemCateKey}" />
									<input type="hidden" id="sItemCode_${i.index}" name="sItemCode" value="${sItemList.sItemCode}" />
									<input type="hidden" id="sItemName_${i.index}" name="sItemName" value="${sItemList.sItemName}" />
									<input type="hidden" id="sItemUnit_${i.index}" name="sItemUnit" value="${sItemList.sItemUnit}" />
									<input type="hidden" id="sItemUnitTxt_${i.index}" name="sItemUnitTxt" value="${sItemList.sItemUnitTxt}" />
									<input type="hidden" id="sItemStandard_${i.index}" name="sItemStandard" value="${sItemList.sItemStandard}" />
									<input type="hidden" id="sItemCatePath_${i.index}" name="sItemCatePath" value="${sItemList.sItemCatePath}" />
								</td>
					            <td>
					            	<c:out value="${sItemList.sItemCatePath}" />
					            </td>
					            <td>
					            	<c:out value="${sItemList.sItemCode}" />
					            </td>
					             <td>
					            	<c:out value="${sItemList.sItemName}" />
					            </td>
					            <td>
					            	<c:out value="${sItemList.sItemStandard}" />
					            </td>
					            <td>
					            	<c:out value="${sItemList.sItemUnitTxt}" />
					            </td>
							</tr>
						</c:forEach>
						<c:if test="${empty sItemList}">
							<tr>
								<td colspan="7">조회 정보가 없습니다.</td>
							</tr>
						</c:if>
			        </tbody>
			     </table>
			</div>

			<div class="page">
				<span>
					<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fn_guestList" />
				</span>
			</div>
		</div>	
</form>

