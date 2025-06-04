
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

<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
	</table>