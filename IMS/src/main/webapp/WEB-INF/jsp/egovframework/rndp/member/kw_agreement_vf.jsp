
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">
function chkAgree(){
	//var chkCnt = $("input:checkbox").length;
	var chkCnt = 0; 
	$("input:checkbox").each(function(){
		if(!this.checked){
			chkCnt += 1;
		}
	});
	
	if(chkCnt == 0){
		location.href = "./webMenu.do?key=22";
	}else{
		alert("약관에 동의 하셔야 합니다. ");
		return;
	}
	
}

</script>

<div class="sub_container">
	<table width="1000" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20">&nbsp;</td>
			<td width="160" valign="top">
				<!-- left 메뉴 --> <c:out value="${subMenu }" escapeXml="false" /> <!-- left 메뉴 -->
			</td>
			<td width="30">&nbsp;</td>
			<td width="30" class="contents_bg">&nbsp;</td>
			<td width="760" valign="top">
				<!--컨텐츠 시작-->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="25" class="navigate"><img
							src="./images_smart/sub/navi_icon.gif" align="absmiddle" /> <span
							class="navigate_s"><c:out value="${menuCategory}"
									escapeXml="false" /></span></td>
					</tr>
					<tr>
						<!-- 타이틀 -->
						<td height="50" class="contents_title">${menuNm}</td>
					</tr>
					<tr>
						<td height="10">&nbsp;</td>
					</tr>
					<tr>
						<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
						<input type="hidden" id="key" name="key" value="${key }" />
								<tr>
									<td class="in_stit">이용약관</td>
								</tr>
								<tr>
									<td><textarea name="content" readonly="readonly" class="terms_area">${agree.content }</textarea></td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td class="t_chk">
										<input type="checkbox" id="agreeOne" name="agreechk" >&nbsp;회원약관에 동의합니다.
									</td>
								</tr>
								<tr>
									<td height="20">&nbsp;</td>
								</tr>
								<tr>
									<td class="in_stit">개인정보보호정책</td>
								</tr>
								<tr>
									<td><textarea name="content2" readonly="readonly" class="terms_area">${agree.content2 }</textarea></td>
								</tr>
								<tr>
									<td class="t_chk">
										<input type="checkbox" id="agreeTwo" name="agreechk" >&nbsp;개인정보취급방침에 동의합니다.
									</td>
								</tr>
								<tr>
									<td height="40">&nbsp;</td>
								</tr>
								<tr>
									<td class="join_btn">
										<!--확인버튼-->
										<img src="./images_smart/sub/btn_ok2.gif" width="122" height="52"  onclick="chkAgree();" />
									</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td height="100">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<!--컨텐츠 끝-->
</div>