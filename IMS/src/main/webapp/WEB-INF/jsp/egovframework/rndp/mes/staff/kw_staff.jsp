<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<title>선택하기</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script language="JavaScript">
	var staffList = new Array();
	var kStaffKey;
	var kStaffName;
	var kClassName;

	$(document).ready(function(){
		
		<c:forEach var="info" items="${staffListByPos}">
			var obj = new Object();
			obj.kStaffKey		= "${info.kStaffKey}";
			obj.kClassName		= "${info.kClassName}";
			obj.kStaffName		= "${info.kStaffName}";
			staffList.push(obj);
		</c:forEach>
	});
function check()
{
	with (document.searchform)
	{
		if (positionKey.value == "")
		{
			alert("부서를 선택하세요.");
			return false;
		}
		else
		{
			submit();
		}
	}
}

function winClose(){
	window.close();	
}

//부모창에 부여
function selecter(){	
	
	var index = $("#index").val();
	var obj = {
 			index			: index,
 			kStaffKey 		: kStaffKey,
 			kStaffName 		: kStaffName
		  };
	
	if(typeof(opener.setStaffPop) != "undefined"){
 		window.opener.setStaffPop(obj);
 		window.close();
 	}
	
}

function selectStaffKey(obj){
	if(obj.value != ''){
		for(var i = 0; i < staffList.length; i++){
			if(staffList[i].kStaffKey == obj.value){
				$('#kClassName').text(staffList[i].kClassName);
				kStaffKey		= staffList[i].kStaffKey;
				kStaffName		= staffList[i].kStaffName;
				kClassName		= staffList[i].kClassName;
				break;
			}
		}
	}else{
		$('#kClassName').text('');
		kStaffKey		= '';
		kStaffName		= '';
		kClassName		= '';
	}
}

</script>
</head>

<body>
<form name="searchform" method="post" action="/mes/staff/kw_staff.do">
	<input type="hidden" name="index" id="index" value="<c:out value='${mesStaffVO.index}' />">
	
	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>결재자선택</h3>
			</div>
		 	<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
		</div>
	</div>
		
		
	<div class="tbl_write">
			<table>
				<tr>
		            <th>부 서</th>
		            <td>
		            	<select name="kPositionKey" onChange="searchform.submit();">
							<option value="">부서선택</option>
								<c:forEach var="item" items="${positionList}">
									<option value="<c:out value='${item.kPositionKey}' />" <c:if test="${mesStaffVO.kPositionKey eq item.kPositionKey}"> selected='selected'</c:if>        ><c:out value="${item.kPositionName}" /></option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>결재자</th>
		            <td>
		            	<select name="kStaffKey" id="kStaffKey" onChange="javascript:selectStaffKey(this);">
							<option value="">결재자선택</option>
								<c:forEach var="item" items="${staffListByPos}">
									<option value="<c:out value='${item.kStaffKey}' />" ><c:out value="${item.kStaffName}" /></option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
				    <th>직 급</th>
				    <td><label id='kClassName'></label></td>
				</tr>
			</table>	
		</div>
		
		
	   	<div class="tbl_btn_right">
			<ul>
				<li>
					<a onclick="selecter();"  style="cursor: pointer;">확인</a>
				</li>
				<li>
					<a onclick="winClose();" style="cursor: pointer;">닫기</a>					 
				</li>
			</ul>
		</div>
	

</form>
</body>
