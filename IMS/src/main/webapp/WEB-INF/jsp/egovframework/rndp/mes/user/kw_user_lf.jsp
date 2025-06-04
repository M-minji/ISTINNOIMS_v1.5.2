<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>

<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />

<script type="text/javascript">
/* 직원등록신청 팝업 */
function staffReq() {
	var url = "/popup/km_user_req_if.do";
	window.open(url, "test", "toolbar=no, location=no, directories=no, "
			+ "status=no, menubar=no, scrollbars=yes, "
			+ "resizable=yes, top=100, left=150, "
			+ "width=1100, height=950");
}

viewService.prototype.go_view = function(obj) {
	$("#mesUserKey").val(obj.childNodes[0].querySelectorAll("input")[0].value);
	document.listForm.action = "/mes/user/kw_user_uf.do";
	document.listForm.submit();
}

function mesUserMenu(key){
	document.listForm.mesUserKey.value = key;
	document.listForm.action = "/mes/user/kw_userMenu_vf.do";
	document.listForm.submit();
}

function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "/mes/user/kw_user_lf.do";
	document.listForm.submit();
}

function mesOffiMenu(key) {
	document.listForm.mesUserKey.value = key;
	document.listForm.action = "/mes/user/kw_officer_if.do";
	document.listForm.submit();
}

$(function(){
	tdBlock(5);
	$('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	$('table[role="grid"].gridjs-table td:nth-child(6)').each(function() {
	 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
	    $(this).css({
	    	 "cursor": "default"
	    });
	});
})

function mesSetCount(key){
	$.ajax({
		type : "post",
		url : "/mes/user/setCount.do",
		data : {"mesUserKey": key},
		dataType : "json",
		async : false,
		cache : false,
		success : function(){
			location.reload();
		}
	});
}
function generatePassword(length) {
    // 비밀번호에 사용할 문자들 정의
    const lowerCase = 'abcdefghijklmnopqrstuvwxyz';
    const upperCase = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const numbers = '0123456789';
    const specialChars = '!@#$';

    // 모든 문자 조합
    const allChars = lowerCase + upperCase + numbers + specialChars;

    // 결과를 저장할 변수
    let password = '';

    // 각 문자 유형을 최소 1개씩 추가 (안전한 비밀번호 보장을 위해)
    password += lowerCase[Math.floor(Math.random() * lowerCase.length)];
    password += upperCase[Math.floor(Math.random() * upperCase.length)];
    password += numbers[Math.floor(Math.random() * numbers.length)];
    password += specialChars[Math.floor(Math.random() * specialChars.length)];

    // 나머지 길이만큼 랜덤으로 채우기
    for (let i = password.length; i < length; i++) {
        password += allChars[Math.floor(Math.random() * allChars.length)];
    }

    // 비밀번호를 무작위로 섞기
    password = password.split('').sort(() => Math.random() - 0.5).join('');

    return password;
}


function mesSetPass(key){
	var str = generatePassword(8);
	if(confirm('비밀번호를 초기화 하시겠습니까? \n변경될 비밀번호는 '+str+' 입니다.\n')){
		navigator.clipboard.writeText(str)
        .then(() => {
          alert("비밀번호가 복사되었습니다.");
          
	          $.ajax({
	  			type : "post",
	  			url : "/mes/user/setPass.do",
	  			data : {"mesUserKey": key , "mesUserPassword" : str},
	  			dataType : "json",
	  			async : false,
	  			cache : false,
	  			success : function(){
	  				window.location.reload();
	  			}
	  		});
        });
	}
}
</script>
<style>
td.gridjs-td:last-child{text-align:center !important;} 
</style>

<form name="listForm" id="listForm" action="/mes/user/kw_user_lf.do"  method="post">		
	<input type="hidden" name="mesUserKey" id="mesUserKey"  value="" />
	<input type="hidden" name="pageIndex" id="pageIndex" value ="${mesUserVO.pageIndex}"/>

	<div class="content_top">
		<div class="content_tit">
			<h2>시스템 사용자 계정관리</h2>
		</div>
		
		<div class="filter_wrap"> 
			<div class="search_filter nogrid">
				<ul> 
					<li>
						<select name="searchType" class="select_search" id="searchType"  >
							<option value="1"
								<c:if test="${mesUserVO.searchType eq 1}">selected="selected"</c:if>>전체</option>
							<option value="2"
								<c:if test="${mesUserVO.searchType eq 2}">selected="selected"</c:if>>이름</option>
							<option value="3"
								<c:if test="${mesUserVO.searchType eq 3}">selected="selected"</c:if>>ID</option>
						</select>
					</li>
					<li> 
						<input name="searchWord" type="text" class="searchWord" style="width:150px;" id="searchWord" value="${mesUserVO.searchWord}"  onkeypress="if(event.keyCode == 13 ){fn_guestList(1);}" maxlength="20"/>  
					</li>
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div>	

	<div class="data_table">
		<table id="myTable" >
			<colgroup>
				<col width="7%" />
				<col width="*" />
				<col width="*" />
				<col width="*" />
				<col width="*" />
				<col width="*" />
			</colgroup>
			<thead>
				<tr>
					<th>No.</th>
					<th>ID</th>
					<th>이름</th>
					<th>E-Mail</th>
					<th>휴대전화</th>
					<th >관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="mesUserList" items="${mesUserList}" varStatus="i">
					<tr style="cursor: pointer;"  onclick="mesUserInfo(${mesUserList.mesUserKey})">
						<td>
							${paginationInfo.totalRecordCount - (mesUserVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${mesUserList.mesUserKey}" />
						</td>
						<td>
							${mesUserList.mesUserId}
							<c:if test="${mesUserList.count >= 5}">
							<span style="color: red;">(계정 잠금)</span>
							</c:if>
						</td>
						<td>${mesUserList.mesUserName}</td>
						<td>${mesUserList.mesUserEmail}</td>
						<td>${mesUserList.mesUserTelephone1}</td>
						<td  onclick="event.cancelBubble = true;">
							<div class="table_btn">
								<%-- <a class="list_btn" onclick="mesOffiMenu(${mesUserList.mesUserKey})">
									품목관리
								</a> --%>
								<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
									<a class="form_btn sm" onclick="mesUserMenu(${mesUserList.mesUserKey})">
										직원별메뉴
									</a>	
									<c:if test="${mesUserList.count >= 5}">
										<a class="form_btn sm" onclick="mesSetCount(${mesUserList.mesUserKey})">
											잠금 풀기
										</a>	
									</c:if>
									<a class="form_btn sm" onclick="mesSetPass(${mesUserList.mesUserKey})">
										비밀번호초기화
									</a>	
								</c:if>
							</div>
						</td>									
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="list_btm">
		<div class="options">
			<select name="recordCountPerPage" class="selectbox select_recordCountPerPage" id="recordCountPerPage"   onchange="fn_guestList(1)">
          		<option value="10" <c:if test="${mesUserVO.recordCountPerPage eq 10}">selected="selected"</c:if>>10개씩 보기</option>
          		<option value="20" <c:if test="${mesUserVO.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
          		<option value="50" <c:if test="${mesUserVO.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
          		<option value="100" <c:if test="${mesUserVO.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
   			</select> 
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
		</div>
		<div class="btns">
			<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
			<button type="button" class="form_btn active" onclick="staffReq()">사용자 추가</button>
			</c:if>
		</div>
	</div>
</form>

