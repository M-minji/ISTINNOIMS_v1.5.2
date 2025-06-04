function check()
{
	with (document.writeform)
	{
		if (password.value == "")
		{
			alert("비밀번호를 입력하세요.");
			password.focus();
			return false;
		}

		if (password2.value == "")
		{
			alert("비밀번호확인을 입력하세요.");
			password2.focus();
			return false;
		}

		if (password.value != password2.value)
		{
			alert("비밀번호를 맞게 입력하세요.");
			password2.select();
			return false;
		}

		return true;
	}
}

function insert_gop()
{
	with (document.writeform)
	{
		if (kStaffPassword.value == "")
		{
			alert("현재 패스워드를 입력하세요.");
			kStaffPassword.focus();
			return;
		}
		if (newPassword.value == "")
		{
			alert("변경할 패스워드를 입력하세요.");
			newPassword.focus();
			return;
		}
		if (nppassword.value == "")
		{
			alert("확인 패스워드를 입력하세요.");
			nppassword.focus();
			return;
		}
		
		if (newPassword.value.length < 4)
		{
			alert("패스워드는 4자 이상으로 입력하세요.");
			newPassword.focus();
			return;
		}
		
		if (nppassword.value != newPassword.value)
		{
			alert("변경 패스워드와 확인패스워드가 다릅니다. 확인바랍니다. ");
			newPassword.focus();
			return;
		}		

		
		submit();
	}
}

function reset()
{
	with (document.writeform)
	{
		reset();
		id.focus();
	}
}

function reset_s()
{
	with (document.writeform)
	{
		reset();
		birth.focus();
	}
}
function reset_p()
{
	with (document.writeform)
	{
		reset();
		password.focus();
	}
}


// 아이디 체크
function checkId()
{
    var staffKey = document.writeform.staffKey.value;
    var id = document.writeform.id.value;
    newWin = window.open("/intranet/staff/kw_checkid.jsp?staffKey=" + staffKey + "&id=" + id, "", "top=65, left=250, width=450, height=300");
}


function searchPost()
{
    newWin = window.open("/jsp/member/kw_post.jsp", "", "top=65, left=250, width=452, height=300, scrollbars=yes");
}


function setBirth()
{
	with (document.writeform)
	{
		birth.value = "19" + jumin1.value;
	}
}
