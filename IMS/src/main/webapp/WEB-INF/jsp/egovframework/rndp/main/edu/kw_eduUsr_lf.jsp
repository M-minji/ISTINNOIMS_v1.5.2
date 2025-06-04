<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){	
	
	datepickerSet('topStartDate', 'topEndDate');
	
	$('#topStartDate, #topEndDate').val(nowDate());
	$('#mloader').show();
}); 


function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}	
	
function fn_guestList(pageNo) {
	document.writeform.pageIndex.value = pageNo;
	document.writeform.action = "/webMenu.do";
	document.writeform.submit();
}
	
	
function go_insert(){
	//$('#mloader').show();
	document.frm.action = "/jsp/homeEdu/kw_eduUsr_if.do";
	document.frm.submit();
}

function viewBom(key){
	//$('#mloader').show();
	document.frm.eBomKey.value = key;
	document.frm.action = "/jsp/homeEdu/kw_eduUsr_vf.do";
	document.frm.submit();
}

</script>

<!--컨텐츠 시작-->
<form id="frm" name="frm" method="post" >
	<input type="hidden" id="pageIndex" name="pageIndex" value="${homeEduVO.pageIndex}" />
	
	
	<table class="board_top">
		<tr>
	    	<th width="70%">  
	    		BOM :
	     		<input name="searchWord" type="text" class="searchWord" id="searchWord" value="${mesBomVO.searchWord}" style="width:150px;" />
	     		 &nbsp;&nbsp;
           		등록일 :
	       		<input type="text" name="topStartDate" id="topStartDate" value="${mesBomVO.topStartDate }"  style="width:70px;"/>
	           	~<input type="text" name="topEndDate" id="topEndDate" value="${mesBomVO.topEndDate }"  style="width:70px;"/>
	     		<a href="#" onclick="fn_guestList(1)" style="cursor: pointer;" >
	     			<img src="/images_mes/btn/btn_search.gif" alt="검색버튼" />
	     		</a>
	    	</th>
	    	<td width="30%">
	    		<a onclick="go_insert()" style="cursor: pointer;" >
	    			<img src="/images_sales/btn/btn_add.gif" alt="추가버튼" /></a>
	    	</td>
	  	</tr>
	</table>
	<table  class="board_list">
		<thead>
			<tr>
				<th width="40%" >신청명</th>
				<th width="20%" >신청회사</th>
				<th width="15%" >신청자명</th>
				<th width="15%" >신청자주소</th>
				<th width="15%" >신청자전화</th>				
				<th width="15%" >신청자일</th>
			</tr>
		</thead>
		<TBODY>
			<c:forEach var="item" items="${eduList}">
				<tr>
					<td class="subject">${item.kEdulistSubject}</td>
					<td>${item.kEdulistCompany}</td>
					<td>${item.kEdulistName}</td>
					<td>${item.kEdulistAddr}</td>
					<td>${item.kEdulistTel}</td>					
					<td>${item.kEdulistWritedate}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty eduList}">
				<tr height="22" bgcolor="#F3F3EE">
					<td colspan="6" align="center">등록된 내용이 없습니다.</td>
				</tr>
			</c:if>
		</TBODY>
	</table>
	<!--컨텐츠 끝-->
	
	<table class="board_page">
	   <tr>
	     <td><ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" /></td>
	   </tr>
	</table>
</form>
 
					