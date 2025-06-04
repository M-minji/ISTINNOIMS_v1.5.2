<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<script type="text/javascript" src="https://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
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
            document.getElementById('kMemberPost').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('kMemberAddress1').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('kMemberAddress2').focus();
        }
    }).open();
}	

function mesUserResInsert(key){
	document.writeForm.action = "/mes/user/kw_user_req_i.do";
	document.writeForm.submit();
}

</script>
<form name="writeForm" id="writeForm">		
	<input type="hidden" name="mesUserRequestKey" id="mesUserRequestKey" value="${mesUserInfo.mesUserRequestKey}" />
	
	
		<div class="content">
			<div class="content_tit">
				<h2>직원가입신청 정보수정</h2>
			</div>
		</div>
		
	
			<div class="tbl_write">
				<table>
					<tbody>
						<tr>
							<th>I D</th>
							<td>${mesUserInfo.mesUserId}</td>
							<th>이름</th>
							<td>${mesUserInfo.mesUserName }<input type="hidden" name="mesUserName" maxLength="50" style="width: 150px;" value="${mesUserInfo.mesUserName }" /></td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td>${mesUserInfo.mesUserBirthday}<input type="hidden" name="mesUserBirthday" maxLength="8" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width: 150px;" value="${mesUserInfo.mesUserBirthday}"/> (Ex:19220125) 
								<input type="radio" name="mesUserBirthdayFlag" value="T" <c:if test="${mesUserInfo.mesUserBirthdayFlag eq '양력'}">checked</c:if>><span>양력</span>
								<input type="radio" name="mesUserBirthdayFlag" value="F" value="T" <c:if test="${mesUserInfo.mesUserBirthdayFlag eq '음력'}">checked</c:if>><span>음력</span>
							</td>
							<th>E-Mail</th>
							<td>${mesUserInfo.mesUserEmail}<input type="hidden" name="mesUserEmail" maxLength="100" style="width: 300px;" value="${mesUserInfo.mesUserEmail}"/></td>
						</tr>
						<tr>
							<th>주소</th>
		            		<td colspan="3">
		            		
		            		(${mesUserInfo.mesUserPost1}) ${mesUserInfo.mesUserAddress1} ${mesUserInfo.mesUserAddress2}
								<input type="hidden" name="mesUserPost1" id="mesUserPost1" style="width:30% !important;" placeholder="우편번호" maxlength="5" value="${mesUserInfo.mesUserPost1}" readonly="readonly">
								<!-- <a class="list_btn" href="#" onclick="sample6_execDaumPostcode()"> 
		              				우편번호 찾기
		              			</a> --><br>
								<input type="hidden" name="mesUserAddress1" id="mesUserAddress1" placeholder="주소" style="width:40% !important; margin-top: 5px;" value="${mesUserInfo.mesUserAddress1}" readonly="readonly">
								<input type="hidden" name="mesUserAddress2" id="mesUserAddress2" placeholder="상세주소" style="width:30% !important; margin-top: 5px;" value="${mesUserInfo.mesUserAddress2}" readonly="readonly">
		              		</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td colspan="3">
							
							${mesUserInfo.mesUserTelephone1}-${mesUserInfo.mesUserTelephone2}-${mesUserInfo.mesUserTelephone3}
							<input type="hidden" name="mesUserTelephone1" maxLength="3" onkeypress="onlyNumber();" 
									style="ime-mode: disabled; width: 15% !important;" value="${mesUserInfo.mesUserTelephone1}" readonly="readonly"/>
								<input type="hidden" name="mesUserTelephone2" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width: 15% !important;" value="${mesUserInfo.mesUserTelephone2}" readonly="readonly" />
								<input type="hidden" name="mesUserTelephone3" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:15% !important;" value="${mesUserInfo.mesUserTelephone3}" readonly="readonly" /></td>
						</tr>
						<tr>
							<th scope="row">핸 드 폰</th>
							<td colspan="3">
							${mesUserInfo.mesUserMobile1}-${mesUserInfo.mesUserMobile2}-${mesUserInfo.mesUserMobile3}
							
							<input type="hidden" name="mesUserMobile1" maxLength="3" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:15% !important;" value="${mesUserInfo.mesUserMobile1}" readonly="readonly" />
								<input type="hidden" name="mesUserMobile2" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width:15% !important;" value="${mesUserInfo.mesUserMobile2}" readonly="readonly" /> 
								<input type="hidden" name="mesUserMobile3" maxLength="4" onkeypress="onlyNumber();"
									style="ime-mode: disabled; width: 15% !important;" value="${mesUserInfo.mesUserMobile3}" readonly="readonly" />
							</td>
						</tr>					
					</tbody>
				</table>
			</div>	
			
			<div class="tbl_write">
			<%-- 	<table>
					<tbody>
						<tr>
							<th>확정권한</th>
							<td>
								<input type="radio" name="kMemberConfirmFlag" value="T" <c:if test="${mesUserInfo.mesUserAuthConfirmFlag eq 'T'}">checked</c:if>>있음
								<input type="radio" name="kMemberConfirmFlag" value="F" <c:if test="${mesUserInfo.mesUserAuthConfirmFlag ne 'T'}">checked</c:if>>없음
							</td>
							<th>쓰기권한</th>
							<td>
								<input type="radio" name="kMemberWriteFlag" value="T" <c:if test="${mesUserInfo.mesUserAuthWriteFlag eq 'T'}">checked</c:if>>있음
								<input type="radio" name="kMemberWriteFlag" value="F" <c:if test="${mesUserInfo.mesUserAuthWriteFlag ne 'T'}">checked</c:if>>없음
							</td>
						</tr>
						<tr>
							<th>수정권한</th>
							<td>
								<input type="radio" name="kMemberUpdateFlag" value="T" <c:if test="${mesUserInfo.mesUserAuthModifyFlag eq 'T'}">checked</c:if>>있음
								<input type="radio" name="kMemberUpdateFlag" value="F" <c:if test="${mesUserInfo.mesUserAuthModifyFlag ne 'T'}">checked</c:if>>없음
							</td>
							<th>삭제권한</th>
							<td>
								<input type="radio" name="kMemberDeleteFlag" value="T" <c:if test="${mesUserInfo.mesUserAuthDelFlag eq 'T'}">checked</c:if>>있음
								<input type="radio" name="kMemberDeleteFlag" value="F" <c:if test="${mesUserInfo.mesUserAuthDelFlag ne 'T'}">checked</c:if>>없음
							</td>
						</tr>
					</tbody>
				</table> --%>		
			</div>
			
			<div class="tbl_btn_right">
				<ul>
					<li>
						<a href="javascript:mesUserResInsert();">승인</a>
					</li>
					<li>
						<a href="/mes/user/kw_user_req_lf.do">목록</a>
					</li>
				</ul>
			</div>
		
</form>