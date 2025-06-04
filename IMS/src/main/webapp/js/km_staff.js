function insert_go()
{
	with (document.writeform)
	{
		if (classKey.value == "")
		{
			alert("직급을 선택하세요.");
			classKey.focus();
			return;
		}

		if (positionKey.value == "")
		{
			alert("부서를 선택하세요.");
			positionKey.focus();
			return;
		}
/*
		if (num.value == "")
		{
			alert("사원번호를 입력하세요.");
			num.focus();
			return;
		}*/

		if (name.value == "")
		{
			alert("이름을 입력하세요.");
			name.focus();
			return;
		}

		/*if (jumin1.value == "")
		{
			alert("주민등록번호를 입력하세요.");
			jumin1.focus();
			return;
		}

		if (jumin2.value == "")
		{
			alert("주민등록번호를 입력하세요.");
			jumin2.focus();
			return;
		}*/

		/*if (password.value != password2.value)
		{
			alert("비밀번호를 맞게 입력하세요.");
			password2.focus();
			return;
		}*/

		submit();
	}
}


function insert_reset()
{
	with (document.writeform)
	{
		reset();
		id.focus();
	}
}


// 아이디 체크
function checkId()
{
    var id = document.writeform.id.value;
    newWin = window.open("/intranet/staff/kw_checkid.jsp?id=" + id, "", "top=65, left=250, width=510, height=250");
}


function searchPost()
{
    newWin = window.open("/jsp/member/kw_post.jsp", "", "top=65, left=250, width=520, height=290, scrollbars=yes");
}


function setBirth()
{
	with (document.writeform)
	{
		birth.value = "19" + jumin1.value;
	}
}
