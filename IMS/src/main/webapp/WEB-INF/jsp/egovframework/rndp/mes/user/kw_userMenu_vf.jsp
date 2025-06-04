<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
   
<script>

function fcheck(idx,ref,root){
		var reff = $('#flagref'+idx).val();
		
		
		var chkArr= document.getElementsByName("flagroot"+root).length;
		for(var i =0 ; i < chkArr; i ++ ){
			
			var a = document.getElementsByName("flagroot"+root)[i].value; 
			var b = document.getElementsByName("flagkey"+root)[i].value; 
	
		if(a == root){
			$('input[name=flagCheak' + b + ']').attr('checked',true);	
			$('input[name=flag'+b+']').val('T');
		}
	}
}

function funcheck(idx,ref,root){
var reff = $('#flagref'+idx).val();
	
	
	var chkArr= document.getElementsByName("flagroot"+root).length;
	for(var i =0 ; i < chkArr; i ++ ){
		
		var a = document.getElementsByName("flagroot"+root)[i].value; 
		var b = document.getElementsByName("flagkey"+root)[i].value; 

	if(a == root){
		$('input[name=flagCheak' + b + ']').attr('checked',false);	
		$('input[name=flag'+b+']').val('F');
	}
	}
}
function ccheck(idx,ref,root){
		var reff = $('#cref'+idx).val();
		
		
		var chkArr= document.getElementsByName("croot"+root).length;
		for(var i =0 ; i < chkArr; i ++ ){
			
			var a = document.getElementsByName("croot"+root)[i].value; 
			var b = document.getElementsByName("ckey"+root)[i].value; 
	
		if(a == root){
			$('input[name=cCheak' + b + ']').attr('checked',true);	
			$('input[name=c'+b+']').val('T');
		}
	}
}

function cuncheck(idx,ref,root){
var reff = $('#cref'+idx).val();
	
	
	var chkArr= document.getElementsByName("croot"+root).length;
	for(var i =0 ; i < chkArr; i ++ ){
		
		var a = document.getElementsByName("croot"+root)[i].value; 
		var b = document.getElementsByName("ckey"+root)[i].value; 

	if(a == root){
		$('input[name=cCheak' + b + ']').attr('checked',false);	
		$('input[name=c'+b+']').val('F');
	}
	}
}
function wcheck(idx,ref,root){
		var reff = $('#wref'+idx).val();
		
		
		var chkArr= document.getElementsByName("wroot"+root).length;
		for(var i =0 ; i < chkArr; i ++ ){
			
			var a = document.getElementsByName("wroot"+root)[i].value; 
			var b = document.getElementsByName("wkey"+root)[i].value; 
	
		if(a == root){
			$('input[name=wCheak' + b + ']').attr('checked',true);	
			$('input[name=w'+b+']').val('T');
		}
	}
}

function wuncheck(idx,ref,root){
var reff = $('#wref'+idx).val();
	
	
	var chkArr= document.getElementsByName("wroot"+root).length;
	for(var i =0 ; i < chkArr; i ++ ){
		
		var a = document.getElementsByName("wroot"+root)[i].value; 
		var b = document.getElementsByName("wkey"+root)[i].value; 

	if(a == root){
		$('input[name=wCheak' + b + ']').attr('checked',false);	
		$('input[name=w'+b+']').val('F');
	}
	}
}
function mcheck(idx,ref,root){
		var reff = $('#mref'+idx).val();
		
		
		var chkArr= document.getElementsByName("mroot"+root).length;
		for(var i =0 ; i < chkArr; i ++ ){
			
			var a = document.getElementsByName("mroot"+root)[i].value; 
			var b = document.getElementsByName("mkey"+root)[i].value; 
	
		if(a == root){
			$('input[name=mCheak' + b + ']').attr('checked',true);	
			$('input[name=m'+b+']').val('T');
		}
	}
}

function muncheck(idx,ref,root){
var reff = $('#mref'+idx).val();
	
	
	var chkArr= document.getElementsByName("mroot"+root).length;
	for(var i =0 ; i < chkArr; i ++ ){
		
		var a = document.getElementsByName("mroot"+root)[i].value; 
		var b = document.getElementsByName("mkey"+root)[i].value; 

	if(a == root){
		$('input[name=mCheak' + b + ']').attr('checked',false);	
		$('input[name=m'+b+']').val('F');
	}
	}
}
function dcheck(idx,ref,root){
		var reff = $('#dref'+idx).val();
		
		
		var chkArr= document.getElementsByName("droot"+root).length;
		for(var i =0 ; i < chkArr; i ++ ){
			
			var a = document.getElementsByName("droot"+root)[i].value; 
			var b = document.getElementsByName("dkey"+root)[i].value; 
	
		if(a == root){
			$('input[name=dCheak' + b + ']').attr('checked',true);	
			$('input[name=d'+b+']').val('T');
		}
	}
}

function duncheck(idx,ref,root){
var reff = $('#dref'+idx).val();
	
	
	var chkArr= document.getElementsByName("droot"+root).length;
	for(var i =0 ; i < chkArr; i ++ ){
		
		var a = document.getElementsByName("droot"+root)[i].value; 
		var b = document.getElementsByName("dkey"+root)[i].value; 

	if(a == root){
		$('input[name=dCheak' + b + ']').attr('checked',false);	
		$('input[name=d'+b+']').val('F');
	}
	}
}

function flagCheak(idx,ref,root){
		if ($('input[name=flagCheak'+idx+']').is(":checked")) {
		    $('input[name=flag'+idx+']').val('T');
			if(ref == 0){
				fcheck(idx,ref,root)
			}
		} else {
		    $('input[name=flag'+idx+']').val('F');
			if(ref == 0){
				funcheck(idx,ref,root)
			}
		} 
		
	
		
}
function cCheak(idx,ref,root){
		if ($('input[name=cCheak'+idx+']').is(":checked")) {
		    $('input[name=c'+idx+']').val('T');
		    if(ref == 0){
				ccheck(idx,ref,root)
			}
		} else {
		    $('input[name=c'+idx+']').val('F');
		    if(ref == 0){
				cuncheck(idx,ref,root)
			}
		} 
}
function wCheak(idx,ref,root){
		if ($('input[name=wCheak'+idx+']').is(":checked")) {
		    $('input[name=w'+idx+']').val('T');
		    if(ref == 0){
				wcheck(idx,ref,root)
			}
		} else {
		    $('input[name=w'+idx+']').val('F');
		    if(ref == 0){
				wuncheck(idx,ref,root)
			}
		} 
}
function mCheak(idx,ref,root){
		if ($('input[name=mCheak'+idx+']').is(":checked")) {
		    $('input[name=m'+idx+']').val('T');
		    if(ref == 0){
				mcheck(idx,ref,root)
			}
		} else {
		    $('input[name=m'+idx+']').val('F');
		    if(ref == 0){
				muncheck(idx,ref,root)
			}
		} 
}
function dCheak(idx,ref,root){
		if ($('input[name=dCheak'+idx+']').is(":checked")) {
		    $('input[name=d'+idx+']').val('T');
		    if(ref == 0){
				dcheck(idx,ref,root)
			}
		} else {
		    $('input[name=d'+idx+']').val('F');
		    if(ref == 0){
				duncheck(idx,ref,root)
			}
		} 
}
var staffKey = "";
function mesUserMenuUp(){
	if(confirm("저장하시겠습니까?")){
		document.listForm.action = "/mes/kw_userMenu_u.do";
		document.listForm.submit();
		$('#mloader').show();
	} 
}

function cancle(){
	$('#mloader').show();
	document.listForm.action = "/mes/user/kw_user_lf.do";
	document.listForm.submit();
}

function chkAll(flag, flagTag,flag2){
	var menuList = "${mesUserMenuList}";
	if($("#"+flag).is(':checked')){	
		for(var idx=0; idx < menuList.length ; idx++){
			$('input[name='+ flagTag + idx + ']').attr('checked',true);	
			$('input[name='+ flag2 + idx + ']').val('T');
		}
		
	}else{
		
		for(var idx=0; idx < menuList.length ; idx++){
			$('input[name='+ flagTag + idx + ']').attr('checked',false);	
			$('input[name='+ flag2 + idx + ']').val('F');
		}
	}			
}


</script>  
    
<form name="listForm" id="listForm"  method="post">
	<input type="hidden" name="mesUserKey" value="<c:out value='${mesUserVO.mesUserKey}' />">
	<input type="hidden" name="kClassKey" value="<c:out value='${mesUserVO.kClassKey}' />">
	<input type="hidden" name="recordCountPerPage" value="<c:out value='${mesUserVO.recordCountPerPage}' />">
	<input type="hidden" name="pageIndex" id="pageIndex" value ="${mesUserVO.pageIndex }"/>
	<input type="hidden" name="searchType" id="searchType"   value="<c:out value='${mesUserVO.searchType}' />"/>
	<input type="hidden" name="searchWord" id="searchWord"   value="<c:out value='${mesUserVO.searchWord}' />"/>
	
			<div class="content_top">
				<div class="content_tit">
					<h2>직원 메뉴 관리</h2>
				</div>
			</div>
		
		
			<div class="normal_table">
				<table>
					<colgroup>	
						<col width="5%" />
						<col width="40%" />
						<col width="*" />
					</colgroup>
					<thead>
						<tr>
							<th rowspan="2">No.</th>
							<th rowspan="2">메뉴명</th>
							<th colspan="5">권한</th>
						</tr>
						<tr>
							<th class="bl">
								<label class="inp_radio end">
									<input type="checkbox" name="flagCheakAll"  id="flagCheakAll" onclick="chkAll('flagCheakAll', 'flagCheak','flag')">	
									<i></i>
									<span>읽기</span>
								</label>
							</th>
							<th>
								<label class="inp_radio end">
									<input type="checkbox" name="cCheakAll" id="cCheakAll" onclick="chkAll('cCheakAll', 'cCheak','c')">					
									<i></i>
									<span>확정</span>				
								</label> 
							</th>
							<th>
								<label class="inp_radio end">
									<input type="checkbox" name="wCheakAll"  id="wCheakAll" onclick="chkAll('wCheakAll', 'wCheak','w')">	
									<i></i>
									<span>쓰기</span>												
								</label>
							</th>
							<th>
								<label class="inp_radio end">
									<input type="checkbox" name="mCheakAll" id="mCheakAll" onclick="chkAll('mCheakAll', 'mCheak','m')">			
									<i></i>
									<span>수정</span>					
								 </label>
							</th>
							<th>
								<label class="inp_radio end">
									<input type="checkbox" name="dCheakAll"  id="dCheakAll" onclick="chkAll('dCheakAll', 'dCheak','d')">			
									<i></i>
									<span>삭제</span>									
								 </label>		
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="menuList" items="${mesUserMenuList}" varStatus="i">
							<tr <%-- onclick="memberReqInfo(${memberList.kMemberKey});" --%> style="cursor:pointer;">
								<td>
									${i.index + 1}
									<input type="hidden" name="kMenuKey"  id="kMenuKey" value="${menuList.mesMenuKey}">
								</td>
								<td style="text-align: left;">
									<c:if test="${menuList.mesMenuRef ne '0'}">&nbsp;&nbsp;&nbsp;&nbsp;└${menuList.mesMenuName}	</c:if>
									<c:if test="${menuList.mesMenuRef eq '0'}">&nbsp;<b>${menuList.mesMenuName}</b></c:if>							
								</td>
								<td>
									<label for="flagCheak${menuList.mesMenuKey}" class="inp_radio">
										<input type="checkbox" name="flagCheak${menuList.mesMenuKey}"  id="flagCheak${menuList.mesMenuKey}" onclick="flagCheak(${menuList.mesMenuKey},${menuList.mesMenuRef},${menuList.mesMenuRoot})" <c:if test="${menuList.mesMenuAuthFlag eq 'T'}">checked="checked"</c:if>>
										<i></i>	
										<input type="hidden" name="flag${menuList.mesMenuKey}" value="<c:if test="${menuList.mesMenuAuthFlag eq 'T'}">T</c:if><c:if test="${menuList.mesMenuAuthFlag ne 'T'}">F</c:if>">
										<input type="hidden" id="flagref${menuList.mesMenuKey}" name="flagref${menuList.mesMenuKey}" value="${menuList.mesMenuRef}">
										<input type="hidden" id="flagroot${menuList.mesMenuKey}" name="flagroot${menuList.mesMenuRoot}" value="${menuList.mesMenuRoot}">
										<input type="hidden" id="flagkey${menuList.mesMenuKey}" name="flagkey${menuList.mesMenuRoot}" value="${menuList.mesMenuKey}">
									  </label> 
								</td>
								<td>
									<label for="cCheak${menuList.mesMenuKey}" class="inp_radio">
										<input type="checkbox" name="cCheak${menuList.mesMenuKey}" id="cCheak${menuList.mesMenuKey}" onclick="cCheak(${menuList.mesMenuKey},${menuList.mesMenuRef},${menuList.mesMenuRoot})" <c:if test="${menuList.mesMenuAuthC eq 'T'}">checked="checked"</c:if>>
										<i></i>	
										<input type="hidden" name="c${menuList.mesMenuKey}" value="<c:if test="${menuList.mesMenuAuthC eq 'T'}">T</c:if><c:if test="${menuList.mesMenuAuthC ne 'T'}">F</c:if>">
										<input type="hidden" id="cref${menuList.mesMenuKey}" name="cref${menuList.mesMenuKey}" value="${menuList.mesMenuRef}">
										<input type="hidden" id="croot${menuList.mesMenuKey}" name="croot${menuList.mesMenuRoot}" value="${menuList.mesMenuRoot}">
										<input type="hidden" id="ckey${menuList.mesMenuKey}" name="ckey${menuList.mesMenuRoot}" value="${menuList.mesMenuKey}">
									</label>
								</td>
								<td>
									<label for="wCheak${menuList.mesMenuKey}" class="inp_radio">
										<input type="checkbox" name="wCheak${menuList.mesMenuKey}"  id="wCheak${menuList.mesMenuKey}" onclick="wCheak(${menuList.mesMenuKey},${menuList.mesMenuRef},${menuList.mesMenuRoot})" <c:if test="${menuList.mesMenuAuthW eq 'T'}">checked="checked"</c:if>>
										<i></i>	
										<input type="hidden" name="w${menuList.mesMenuKey}" value="<c:if test="${menuList.mesMenuAuthW eq 'T'}">T</c:if><c:if test="${menuList.mesMenuAuthW ne 'T'}">F</c:if>">
										<input type="hidden" id="wref${menuList.mesMenuKey}" name="wref${menuList.mesMenuKey}" value="${menuList.mesMenuRef}">
										<input type="hidden" id="wroot${menuList.mesMenuKey}" name="wroot${menuList.mesMenuRoot}" value="${menuList.mesMenuRoot}">
										<input type="hidden" id="wkey${menuList.mesMenuKey}" name="wkey${menuList.mesMenuRoot}" value="${menuList.mesMenuKey}">
									 </label>
								</td>
								<td>
									<label for="mCheak${menuList.mesMenuKey}" class="inp_radio">
										<input type="checkbox" name="mCheak${menuList.mesMenuKey}" id="mCheak${menuList.mesMenuKey}" onclick="mCheak(${menuList.mesMenuKey},${menuList.mesMenuRef},${menuList.mesMenuRoot})" <c:if test="${menuList.mesMenuAuthM eq 'T'}">checked="checked"</c:if>>
										<i></i>
										<input type="hidden" name="m${menuList.mesMenuKey}" value="<c:if test="${menuList.mesMenuAuthM eq 'T'}">T</c:if><c:if test="${menuList.mesMenuAuthM ne 'T'}">F</c:if>">
										<input type="hidden" id="mref${menuList.mesMenuKey}" name="mref${menuList.mesMenuKey}" value="${menuList.mesMenuRef}">
										<input type="hidden" id="mroot${menuList.mesMenuKey}" name="mroot${menuList.mesMenuRoot}" value="${menuList.mesMenuRoot}">
										<input type="hidden" id="mkey${menuList.mesMenuKey}" name="mkey${menuList.mesMenuRoot}" value="${menuList.mesMenuKey}">
									 </label>
								 </td>
								 <td>
									<label for="dCheak${menuList.mesMenuKey}" class="inp_radio">
										<input type="checkbox" name="dCheak${menuList.mesMenuKey}"  id="dCheak${menuList.mesMenuKey}" onclick="dCheak(${menuList.mesMenuKey},${menuList.mesMenuRef},${menuList.mesMenuRoot})" <c:if test="${menuList.mesMenuAuthD eq 'T'}">checked="checked"</c:if>>
										<i></i>
										<input type="hidden" name="d${menuList.mesMenuKey}" value="<c:if test="${menuList.mesMenuAuthD eq 'T'}">T</c:if><c:if test="${menuList.mesMenuAuthD ne 'T'}">F</c:if>">
										<input type="hidden" id="dref${menuList.mesMenuKey}" name="dref${menuList.mesMenuKey}" value="${menuList.mesMenuRef}">
										<input type="hidden" id="droot${menuList.mesMenuKey}" name="droot${menuList.mesMenuRoot}" value="${menuList.mesMenuRoot}">
										<input type="hidden" id="dkey${menuList.mesMenuKey}" name="dkey${menuList.mesMenuRoot}" value="${menuList.mesMenuKey}">
									 </label>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty mesUserMenuList }">
							<tr>
								<td colspan="7">메뉴 목록이 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			
			<div class="bottom_btn">
				<span class="mr20">* 권한 부여 후 재 로그인하셔야 반영됩니다. </span>
				<button type="button" class="form_btn active" onclick="mesUserMenuUp();">등록</button>
				<button type="button" class="form_btn" onclick="cancle();">목록</button>
			</div>
		
</form>