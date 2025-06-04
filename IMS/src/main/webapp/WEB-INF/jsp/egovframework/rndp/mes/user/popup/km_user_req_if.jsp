<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/css/mes/common.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/popup.css" rel="stylesheet"	type="text/css" />

<script src="/js/egovframework/com/cmm/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="/js/km_staff_req.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
var idCheck = "F";
function checmesUserId() {
	var mesUserId = $.trim(document.getElementById("mesUserId").value);
	document.subfrm.mesUserId.value = mesUserId;
	if (mesUserId == "") {		/* 아이디 널값 체크 */
		alert("아이디를 입력하세요.");
		document.writeform.mesUserId.focus();
	} else {
		$.ajax({			/* 아이디 중복체크 */
			type : "post",
			dataType : "json",
			url : "<c:url value='/popup/kw_mesCheckId.do'/>",
			data : $('#subfrm').serialize(),
			success : function(msg) { //응답이 성공 상태 코드를 반환하면 호출
				var resultFlag = msg.result.resultFlag;
				if (resultFlag == "T") {
					alert("사용 가능한 아이디 입니다.");
				} else {
					alert("중복된 아이디입니다.");
				}
				idCheck = resultFlag;
			},
			error : function(e) {
				alert('에러발생');
			}
		});
	}
}

function againChecmesUserId() {		/* 아이디 중복 체크 후 아이디를 변경하였을경우 다시 기본 값 셋팅 */
	idCheck = 'F';
}

function onlyNumber() {		/* 문자입력 제한 */
	if ((event.keyCode < 48) || (event.keyCode > 57))
		event.returnValue = false;
}

function insert_go() {		/* 전제적인 입력창 널값 체크 */
	with (document.writeform) {
		if (kSectorKey.value == "") {
			alert("사업부문을 선택하세요.");
			kSectorKey.focus();
			return;
		}
		if (kClassKey.value == "") {
			alert("직급을 선택하세요.");
			kClassKey.focus();
			return;
		}
		if (kPositionKey.value == "") {
			alert("부서를 선택하세요.");
			kPositionKey.focus();
			return;
		}
		if (mesUserId.value == "") {
			alert("아이디를 입력하세요.");
			mesUserId.focus();
			return;
		}
		if (idCheck == "F") {
			alert("아이디를 확인 하세요. 중복된 아이디는 사용 할 수 없습니다.");
			mesUserId.focus();
			return;
		}
		if (mesUserName.value == "") {
			alert("이름을 입력하세요.");
			mesUserName.focus();
			return;
		}
		if (mesUserPassword1.value == "") {
			alert("비밀번호를 입력하세요.");
			mesUserPassword1.focus();
			return;
		}
		if (mesUserPassword1.value != mesUserPassword2.value) {
			alert("비밀번호를 맞게 입력하세요.");
			mesUserPassword2.focus();
			return;
		}
		if(checkPassword(mesUserPassword1.value, mesUserId.value) == false){
			mesUserPassword1.focus();
			return;	
		}
		if (idCheck == 'F') {
			alert(resultFlag);
			alert("아이디를 확인 하세요. 중복된 아이디는 사용 할 수 없습니다.");
			mesUserId.focus();
			return;
		}
		if (confirm("등록 하시겠습니까?")){		/* 등록여부 최종확인 */
			submit();
		}
	}
}
function insert_close() {		/* 등록 후 팝업창 닫기 */
	var aa = "${closeValue}";
	if (aa == '1') {
		alert("신청이 등록되었습니다.");
		window.close();
	}
}
function nextclose() {		/* 아이디 중복 체크 후 아이디를 변경하였을경우 다시 기본 값 셋팅 */
	window.close();
}
/* 다음 주소검색 팝업 사용  */
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('mesUserPost1').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('mesUserAddress1').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('mesUserAddress2').focus();
        }
    }).open();
}
function checkPassword(password,id){	
    if(!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{9,25}$/.test(password)){            
        alert('숫자+영문자+특수문자 조합으로 9자리 이상 사용해야 합니다.');
        $('#password').val('').focus();
        return false;
    }    
    var checkNumber = password.search(/[0-9]/g);
    var checkEnglish = password.search(/[a-z]/ig);
    if(checkNumber <0 || checkEnglish <0){
        alert("숫자와 영문자를 혼용하여야 합니다.");
        $('#password').val('').focus();
        return false;
    }
    if(/(\w)\1\1\1/.test(password)){
        alert('같은 문자를 4번 이상 사용하실 수 없습니다.');
        $('#password').val('').focus();
        return false;
    }
    return true;
}
function passwordResultCheck(){
	var pw = $("#mesUserPassword1").val();
	
	if(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/.test(pw)){
		$("#passwordResult").text("안전");
	}else if(/^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$/.test(pw)){
		$("#passwordResult").text("약함");
	}else{
		$("#passwordResult").text("사용할 수 없는 비밀번호");
	}
	
	var check = $("#mesUserPassword2").val();
	if(check != ""){
		samePassword();
	}
}

// 비밀번호 확인
function samePassword(){
	var pw = $("#mesUserPassword1").val();
	var check = $("#mesUserPassword2").val();
	
	if(pw == check){
		$("#passwordCheck").text("일치");
	}else{
		$("#passwordCheck").text("일치하지 않습니다.");
	}
}

</script>
</head>

<body onLoad="javascript:insert_close(); ">
	<!-- document.writeform.id.focus(); -->
<form name="writeform" method="post" action="/popup/checmesUserId.do" class="popup_wrap">
	<!-- 무조건 재직 -->
<!-- 	<input type="hidden" name="stateFlag" value="1"> -->
	<input type="hidden" name="mesUserStateFlag" value="1">
	<input type="hidden" name="kSectorKey" value="1">

	<div class="pop_head">
		<div id="pop_head">
			<div class="tit">
				<h3>임직원등록 신청</h3>
			</div>
		</div>
	</div>
		
	<div class="popup_content">	
		<div class="pop_con">
			<div class="normal_table row">
				<table>
					<tbody>
						<%-- <tr>
							<th scope="row">사업 부문</th>
							<td><select name="kSectorKey" >
									<option value="" selected>사업부문선택</option>
									<c:forEach var="item" items="${sectorList}">
										<option value="<c:out value='${item.kSectorKey}' />"><c:out value="${item.kSectorName}" /></option>
									</c:forEach>
							</select></td>
						</tr> --%>
						<tr>
							<th scope="row"><span style="color: red">* </span>직급</th>
							<td><select name="kClassKey">
									<option value="" selected>직급선택</option>
									<c:forEach var="item" items="${classList}">
										<option value="<c:out value='${item.kClassKey}' />">
											<c:out value="${item.kClassName}" />
										</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th scope="row"><span style="color: red">* </span>부서</th>
							<td>
								<select name="kPositionKey" >
									<option value="" selected>부서선택</option>
									<c:forEach var="item" items="${positionList}">
										<option value="<c:out value='${item.kPositionKey}' />">
											<c:out value="${item.kPositionName}" />
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row"><span style="color: red">* </span>아이디</th>
							<td>
								<p>
									<input type="text" id="mesUserId" name="mesUserId" maxLength="20" onkeydown="againChecmesUserId()"  style="width:200px" />
									<a class="form_btn bg" onclick="javascript:checmesUserId()">확인</a>
								</p>
							</td>
						</tr>
						<tr>
							<th scope="row"><span style="color: red">* </span>이름</th>
							<td>
								<input type="text" name="mesUserName" maxLength="50"  style="width:200px" />
							</td>
						</tr>
						<tr>
							<th scope="row"><span style="color: red">* </span>비밀번호</th>
							<td>
	<!-- 							<input type="password" name="mesUserPassword1" maxLength="20" /> -->
								<input type="password" id="mesUserPassword1" name="mesUserPassword1" maxLength="20" onblur="passwordResultCheck();" class="mr5" style="width:200px; vertical-align:middle" />
								비밀번호안전도 : <span id="passwordResult"></span>(숫자, 영문, 특수문자조합 9자리이상 가능)
							</td>
						</tr>
						<tr>
							<th scope="row"><span style="color: red">* </span>비밀번호 확인</th>
							<td>
								<input type="password" id="mesUserPassword2" name="mesUserPassword2" maxLength="20" onblur="samePassword();" style="width:200px"  />
								<span id="passwordCheck"></span>
							</td>
						</tr>
						<tr>
							<th scope="row">생년월일</th>
							<td><input type="text" name="mesUserBirthday" maxLength="8" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width: 200px; vertical-align:middle" /> <span class="ml5 mr20">(Ex:19220125) </span> 
									
								<label class="inp_radio">
									<input type="radio" name="mesUserBirthdayFlag" value="T" checked>
									<i></i>
									<span>양력</span>
								</label>
								<label class="inp_radio">
									<input type="radio" name="mesUserBirthdayFlag" value="F">
									<i></i>
									<span>음력</span>
								</label>
						</tr>
						<tr>
							<th scope="row">이 메 일</th>
							<td><input type="text" name="mesUserEmail" maxLength="100" style="width:200px" /></td>
						</tr>
		          		<tr>
		            		<th scope="row">주소</th>
		            		<td>
		            			<div class="tbl_group">
		            				<ul>
		            					<li>
											<input type="text" name="mesUserPost1" id="mesUserPost1" placeholder="우편번호" maxlength="5" style="width:200px" >
											<a class="form_btn bg" onclick="sample6_execDaumPostcode()"> 
					              				우편번호찾기
					              			</a>
										</li>
										<li>	
											<input type="text" name="mesUserAddress1" id="mesUserAddress1" placeholder="주소" style="width:300px;" readonly />
											<input type="text" name="mesUserAddress2" id="mesUserAddress2" placeholder="상세주소" style="width:250px;" maxlength="100" />
		              					</li>
		              				</ul>
		              			</div>
		              		</td>
		          		</tr>						
						<tr>
							<th scope="row">전화번호</th>
							<td><input type="text" name="mesUserTelephone1" maxLength="3" onkeypress="onlyNumber();" 
									style="ime-mode: disabled; width:10%; vertical-align: middle" /> <span>-</span> 
								<input type="text" name="mesUserTelephone2" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:10%;" /> <span>-</span> 
								<input type="text" name="mesUserTelephone3" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:10%;" /></td>
						</tr>
						<tr>
							<th scope="row">핸드폰</th>
							<td><input type="text" name="mesUserMobile1" maxLength="3" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:10%; vertical-align: middle" /> <span>-</span> 
								<input type="text" name="mesUserMobile2" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:10%; vertical-align: middle" /> <span>-</span> 
								<input type="text" name="mesUserMobile3" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:10%;" />
							</td>
						</tr>
					</tbody>	
				</table>
			</div>
			
			<div class="bottom_btn">
				<button type="button" class="form_btn active" onclick="insert_go();">저장</button>
				<button type="button" class="form_btn ico_refresh" onclick="insert_reset();">다시</button>
				<button type="button" class="form_btn" onclick="nextclose();">닫기</button>
			</div>
		</div>	
	</div>	
</form>	
</body>
<form name="subfrm" id="subfrm">
	<input type="hidden" id="mesUserId" name="mesUserId">
</form>
</html>
