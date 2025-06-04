<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script>
// --------쿠키를 저장한다.
function setCookie( name, value, expiredays ){
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}
//--------오늘 보이지 않음 클릭
function closeWin(objDiv,type){
	setCookie(type,"1",1);                    //마지막 파라미터 1을 2로 고치면 2일 동안 안보임.
	window.close();
}
//--------팝업창 닫기
function Close_PopPanel(objDiv){
	window.close();
}


//--------오늘 보이지 않음 클릭
//function closeWin(objDiv,type){
//	setCookie(type,"1",1);                    //마지막 파라미터 1을 2로 고치면 2일 동안 안보임.
//	Close_PopPanel(objDiv);
//}
//--------팝업창 닫기
// function Close_PopPanel(objDiv){
// 	objDiv.style.display="none";
// }

</script>

<%-- <form name="popfrom"> --%>
	<div id="POPSMART${popMainInfo.key}" style="position:absolute; width:${popMainInfo.width-5}px; height:${popMainInfo.height-20}px; z-index:9000; visibility: visible; "><!-- background:#646464; -->

		<div style="width:${popMainInfo.width-5}; height:${popMainInfo.height-27}px; padding: 0px 0px 0px 0px">
			<c:if test="${not empty popMainInfo.url }">
				<a href='<c:out value="${popMainInfo.url}"/>' target="_blank">
			</c:if>		
				${popMainInfo.content}
			<c:if test="${not empty popMainInfo.url }">
				</a>	
			</c:if>	
		</div>
		<div style="text-align:center; width:${popMainInfo.width-5}; height:20px; padding: 5px 0px 0px 0px;"><!--  background: #646464; -->
			<input type="checkbox" name="Notice" onclick="javascript:closeWin(document.all.POPSMART${popMainInfo.key},'POPSMART${popMainInfo.key}');">오늘하루 이창을 다시 열지않음
			<a href="javascript:void(0);" onclick="javascript:Close_PopPanel(document.all.POPSMART${popMainInfo.key});"><b>[닫기]</b></a>
		</div>
	</div>

<%-- </form>	 --%>
	
	
<script language="Javascript">
	cookiedata = document.cookie; 
	
	if ( cookiedata.indexOf("POPSMART${popMainInfo.key}=1") < 0 ){ 
		document.getElementById("POPSMART${popMainInfo.key}").style.display = "block";
	} else {
		document.getElementById("POPSMART${popMainInfo.key}").style.display = "none"; 
	}
</script>
