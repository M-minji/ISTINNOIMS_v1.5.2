<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="/js/jquery/jquery-3.7.1.min.js"></script>
<script type="text/javascript">var $j = jQuery.noConflict();</script>
<script type="text/javascript" src="<c:url value='/js/km_common.js'/>"></script>
<script type="text/javascript">
	function goSubList(key, auth, root, groupKey, rootKey){
		var url = "";
		document.subfrm.key.value = key;
		$j.ajax({
			type: "post",                                           //포스트 방식
		 	dataType : "json",                                      //dataType
		  	url: "<c:url value='/admin/contentSubList.do'/>",         //url
		  	data: $j('#subfrm').serialize(),												//요청에 전달되는 프로퍼티를 가진 객체
		  	success: function(msg){								//응답이 성공 상태 코드를 반환하면 호출	
  		  		var $subId = $j('#subId'+key);
		  		$j('.sub').empty();
		  		$j.each(msg.result.voList, function(i, item){
   		  			if(auth){
   		  				url = "<a href=\"<c:url value='/admin/contentUpdatefrm.do'/>?menuKey="+item.menuKey+"&root="+root+"&groupKey="+groupKey+"&rootKey="+rootKey+"\" title=\"컨텐츠수정\"><img src=\"<c:url value='/images/admin/icon_s_E.gif'/>\"  align=\"absmiddle\"></a>";
   		  				url += "&nbsp;<a href=\"<c:url value='/admin/contentView.do'/>?menuKey="+item.menuKey+"&root="+root+"&groupKey="+groupKey+"&rootKey="+rootKey+"\" title=\"컨텐츠미리보기\"><img src=\"<c:url value='/images/admin/icon_s_V.gif'/>\"  align=\"absmiddle\"></a>";
   		  				url += "&nbsp;<a href=\"<c:url value='/admin/contentDelete.do'/>?menuKey="+item.menuKey+"&root="+root+"&groupKey="+groupKey+"&rootKey="+rootKey+"\" title=\"컨텐츠삭제\" onClick=\"return delete_list2();\"><img src=\"<c:url value='/images/admin/icon_s_D.gif'/>\" align=\"absmiddle\"></a>";
//    		  				$subId.after('<tr class="sub" ><td width="50">&nbsp;</td><td width="*" style="text-align:left;padding:0px 0 0 20;"><a href="javascript:goSubList2('+item.key+','+item.auth+','+item.root+','+item.groupKey+','+rootKey+');">'+item.name+'</a></td><td width="100">'+item.visibleForm+'</td><td width="100">'+item.adminId+'</td><td width="80" >'+url+'</td></tr>');
   		  				$subId.after('<tr class="sub" id="subId'+item.menuKey+'"><td width="50">&nbsp;</td><td width="*" style="text-align:left;padding:0px 0 0 20;"><a href="javascript:goSubList2(' + item.menuKey + ',' + auth + ',' + key + ',' + item.groupKey + ',' + rootKey + ');">'+item.name+'</a></td><td width="100">'+item.visibleForm+'</td><td width="100">'+item.adminId+'</td><td width="80" >'+url+'</td></tr>');
   		  			}else{
   		  				$subId.after('<tr class="sub" ><td width="50">&nbsp;</td><td width="*" style="text-align:left;padding:0px 0 0 20;">'+item.name+'</td><td width="100">'+item.visibleForm+'</td><td width="100">'+item.adminId+'</td><td width="80">&nbsp;</td></tr>');
   		  			}
		  			
   		  		});
			},
			error: function (e) { 
				alert('에러발생'); 
			}                         
			
		});
	}	
	
	function goSubList2(key, auth, root, groupKey, rootKey){
		var url = "";
		document.subfrm.key.value = key;
		$j.ajax({
			type: "post",                                           //포스트 방식
		 	dataType : "json",                                      //dataType
		  	url: "<c:url value='/admin/contentSubList.do'/>",         //url
		  	data: $j('#subfrm').serialize(),												//요청에 전달되는 프로퍼티를 가진 객체
		  	success: function(msg){								//응답이 성공 상태 코드를 반환하면 호출	
  		  		var $subId = $j('#subId'+key);
		  		$j('.sub1').empty();
		  		$j.each(msg.result.voList, function(i, item){
   		  			if(auth){
   		  				url = "<a href=\"<c:url value='/admin/contentUpdatefrm.do'/>?menuKey="+item.menuKey+"&root="+root+"&groupKey="+groupKey+"&rootKey="+rootKey+"\" title=\"컨텐츠수정\"><img src=\"<c:url value='/images/admin/icon_s_E.gif'/>\"  align=\"absmiddle\"></a>";
   		  				url += "&nbsp;<a href=\"<c:url value='/admin/contentView.do'/>?menuKey="+item.menuKey+"&root="+root+"&groupKey="+groupKey+"&rootKey="+rootKey+"\" title=\"컨텐츠미리보기\"><img src=\"<c:url value='/images/admin/icon_s_V.gif'/>\"  align=\"absmiddle\"></a>";
   		  				url += "&nbsp;<a href=\"<c:url value='/admin/contentDelete.do'/>?menuKey="+item.menuKey+"&root="+root+"&groupKey="+groupKey+"&rootKey="+rootKey+"\" title=\"컨텐츠삭제\" onClick=\"return delete_list2();\"><img src=\"<c:url value='/images/admin/icon_s_D.gif'/>\" align=\"absmiddle\"></a>";
   		  				$subId.after('<tr class="sub1" id="subId'+item.menuKey+'"><td width="50">&nbsp;</td><td width="*" style="text-align:left;margin-left:20px;padding:0px 0 0 20px;">'+item.name+'</td><td width="100">'+item.visibleForm+'</td><td width="100">'+item.adminId+'</td><td width="80" >'+url+'</td></tr>');
   		  			}else{
   		  				$subId.after('<tr class="sub1" ><td width="50">&nbsp;</td><td width="*" style="text-align:left;margin-left:20px;padding:0px 0 0 40px;">'+item.name+'</td><td width="100">'+item.visibleForm+'</td><td width="100">'+item.adminId+'</td><td width="80">&nbsp;</td></tr>');
   		  			}
		  			
   		  		});
			},
			error: function (e) { 
				alert('에러발생'); 
			}                         
			
		});
	}
</script>
</head>

<body class="content_bg">
	<div id="contents">
		<div class="title">
			<h2>컨텐츠관리</h2>
			<div class="path" >
				<p>HOME&nbsp;>&nbsp;컨텐츠관리</p>
			</div>
		</div>
		<div class="tbl_top">
				<ul class="tbl_top_left">
					<li>
					<form name="searchform" method="post" action="<c:url value='/admin/adminContentList.do'/>">
						<input type="hidden" name="rootKey" value="${rootKey}">
						<select name="groupKey" onchange="searchform.submit();">
								<c:forEach var="item" items="${groupList}">
									<option value="<c:out value='${item.groupKey}' />" <c:if test="${item.groupKey == groupKey}">selected</c:if>>
										<c:out value="${item.name}" />
									</option>
								</c:forEach>
						</select>
					</form>
				</li>
			</ul>
			<ul class="tbl_top_right">
				<li>
					<img src="<c:url value='/images/admin/admin_icon_E.gif'/>">
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_V.gif'/>"> 
				</li>
				<li>
					<img src="<c:url value='/images/admin/admin_icon_D.gif'/>">
				</li>
			</ul>
		</div>
		<table class="tbl_list" border='0'>
			<thead>
				<tr>
					<th width="50" scope=col>번 호</th>
					<th width="*" scope=col>메 뉴 명</th>
					<th width="100" scope=col>화면표시</th>
					<th width="100" scope=col>관리자</th>
					<th width="80" scope=col>관 리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${contentList}" varStatus="i">
					<tr onMouseOver="style.backgroundColor='#EAEAEA'" onMouseOut="style.backgroundColor=''" id="subId${item.key}" name="subId${item.key}">
						<td><c:out value="${i.count}" /></td>
						<td class="tit">
							<a href="javascript:goSubList('${item.key}','${item.auth}','${item.root}','${item.groupKey}','${rootKey}');"><c:out value='${item.name}' escapeXml="false" /></a>
							
						</td>
						<td><c:out value="${item.visibleForm}" escapeXml="false" /></td>
						<td><c:out value="${item.adminId}" /></td>
 						<td>&nbsp; 
 							<c:set target="${item}" property="adminKey" value="${groupKey}" /> 
 							<c:if test="${item.auth eq true && item.exist eq 'T'}" >
								<c:set var="link" value="menuKey=${item.key}&root=${item.root}&groupKey=${item.groupKey}&rootKey=${rootKey}" /> 
								<a href="<c:url value='/admin/contentUpdatefrm.do'/>?<c:out value='${link}' />" title="컨텐츠수정"><img src="<c:url value='/images/admin/icon_s_E.gif'/>"  align="absmiddle"></a>
								<a href="<c:url value='/admin/contentView.do'/>?<c:out value='${link}' />" title="컨텐츠미리보기"><img src="<c:url value='/images/admin/icon_s_V.gif'/>"  align="absmiddle"></a>
								<a href="<c:url value='/admin/contentDelete.do'/>?<c:out value='${link}' />" title="컨텐츠삭제" onClick="return delete_list2();"><img src="<c:url value='/images/admin/icon_s_D.gif'/>" align="absmiddle"></a> 
							</c:if> 

						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty contentList}">
					<tr>
						<td colspan="5" align="center">등록된 메뉴 목록이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<form name="subfrm" id="subfrm">
		<input type="hidden" name="key" id="key" value=""/>
	</form>
	<c:if test="${err eq 'error'}">
		<script type="text/javascript">
			alert('파일이 존재하지 않습니다.');
		</script>
	</c:if>
</body>
</html>
