function insert_go()
{
	with (document.frm)
	{
		var pass = password.value;
		var same = true, inc = true;

		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}

		if (id.value.length < 5)
		{
			alert("아이디는 5글자 이상 입력하세요.");
			id.focus();
			return false;
		}

		if (name.value == "")
		{
			alert("이름를 입력하세요.");
			name.focus();
			return false;
		}

		if (pass == "")
		{
			alert("비밀번호를 입력하세요.");
			password.focus();
			return false;
		}

		if (pass != password2.value)
		{
			alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인하세요.");
			password2.select();
			return false;
		}

		if (pass.length < 4)
		{
			alert("비밀번호는 4글자 이상 입력하세요.");
			password.focus();
			return false;
		}

		for (var i = 0; i < pass.length - 1; i++)
		{
			if (pass.charAt(i) != pass.charAt(i + 1))
			{
				same = false;
			}
		}

		if (same)
		{
			alert("비밀번호는 같은 단어를 연속으로 사용할 수 없습니다.");
			password.select();
			return false;
		}

		for (var i = 0; i < pass.length - 1; i++)
		{
			if (pass.charCodeAt(i) != pass.charCodeAt(i + 1) - 1)
			{
				inc = false;
			}
		}

		if (inc)
		{
			alert("비밀번호는 증가값을 사용할 수 없습니다.");
			password.select();
			return false;
		}

		if (pass.indexOf(id.value) != -1)
		{
			alert("비밀번호에는 아이디와 같은 단어가 포함될 수 없습니다.");
			password.select();
			return false;
		}

		if (email.value == "")
		{
			alert("이메일을 입력하세요.\n비밀번호 분실시 사용됩니다.");
			email.focus();
			return false;
		}

		if (address1.value == "")
		{
			alert("주소를 입력하세요.");
			address1.focus();
			return false;
		}

		if (address2.value == "")
		{
			alert("주소를 입력하세요.");
			address2.focus();
			return false;
		}

		if (isNaN(telephone1.value))
		{
			alert("전화번호는 숫자로 입력하세요.");
			telephone1.select();
			return false;
		}

		if (isNaN(telephone2.value))
		{
			alert("전화번호는 숫자로 입력하세요.");
			telephone2.select();
			return false;
		}

		if (isNaN(telephone3.value))
		{
			alert("전화번호는 숫자로 입력하세요.");
			telephone3.select();
			return false;
		}

		if (isNaN(mobile1.value))
		{
			alert("핸드폰번호는 숫자로 입력하세요.");
			mobile1.select();
			return false;
		}

		if (isNaN(mobile2.value))
		{
			alert("핸드폰번호는 숫자로 입력하세요.");
			mobile2.select();
			return false;
		}

		if (isNaN(mobile3.value))
		{
			alert("핸드폰번호는 숫자로 입력하세요.");
			mobile3.select();
			return false;
		}

		return true;
	}
}


function insert_reset()
{
	with (document.frm)
	{
		reset();
	}
}


// 아이디 체크
function checkId()
{
    var id = document.frm.id.value;
    newWin = window.open("/jsp/member/kw_checkid.jsp?id=" + id, "", "top=65, left=250, width=520, height=270");
}


// 우편번호 찾기
function searchPost()
{
    newWin = window.open("/jsp/member/kw_post.jsp", "", "top=65, left=250, width=520, height=270, scrollbars=yes");
}