function insert_go()
{
	with (document.writeform)
	{

		if (name.value == "")
		{
			alert("이름를 입력하세요.");
			name.focus();
			return;
		}

		if (email1.value == "")
		{
			alert("이메일을 입력하세요.");
			email1.focus();
			return;
		}

		if (email2.value == "")
		{
			alert("이메일을 입력하세요.");
			email2.focus();
			return;
		}
		
		if (address1.value == "")
		{
			alert("주소를 입력하세요.");
			address1.focus();
			return;
		}

		if (address2.value == "")
		{
			alert("주소를 입력하세요.");
			address2.focus();
			return;
		}

		if (company.value == "")
		{
			alert("회사명을 입력하세요.");
			company.focus();
		}
		
		if (part.value == "")
		{
			alert("부서를 입력하세요.");
			part.focus();
			return;
		}

		if (position.value == "")
		{
			alert("직위를 입력하세요.");
			position.focus();
			return;
		}

		if (isNaN(tel1.value))
		{
			alert("전화번호는 숫자로 입력하세요.");
			tel1.select();
			return;
		}

		if (isNaN(tel2.value))
		{
			alert("전화번호는 숫자로 입력하세요.");
			tel2.select();
			return;
		}

		if (isNaN(tel3.value))
		{
			alert("전화번호는 숫자로 입력하세요.");
			tel3.select();
			return;
		}

		if (isNaN(hp1.value))
		{
			alert("핸드폰번호는 숫자로 입력하세요.");
			hp1.select();
			return;
		}

		if (isNaN(hp2.value))
		{
			alert("핸드폰번호는 숫자로 입력하세요.");
			hp2.select();
			return;
		}

		if (isNaN(hp3.value))
		{
			alert("핸드폰번호는 숫자로 입력하세요.");
			hp3.select();	
			return;
		}

		submit();
	}
}


function insert_reset()
{
	with (document.writeform)
	{
		reset();
	}
}


// 우편번호 찾기
function searchPost()
{
    newWin = window.open("/jsp/member/kw_post.jsp", "", "top=65, left=250, width=452, height=300, scrollbars=yes");
}