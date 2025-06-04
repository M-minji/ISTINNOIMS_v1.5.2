function insert_go()
{
	with (document.writeform)
	{
		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return;
		}

		if (password.value.length < 4)
		{
			alert("패스워드는 4자 이상으로 입력하세요.");
			password.focus();
			return;
		}

		if (memo.value.length >= 1000)
		{
			alert("아이디 설명은 1000자 이내로 입력하세요.");
			memo.focus();
			return;
		}

		submit();
	}
}

function insert_ugo()
{
	with (document.writeform)
	{
		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return;
		}

		if (memo.value.length >= 1000)
		{
			alert("아이디 설명은 1000자 이내로 입력하세요.");
			memo.focus();
			return;
		}

		submit();
	}
}

function insert_pgo()
{
	with (document.writeform)
	{
		if (password.value == "")
		{
			alert("현재 패스워드를 입력하세요.");
			password.focus();
			return;
		}
		if (newpassword.value == "")
		{
			alert("변결할 패스워드를 입력하세요.");
			newpassword.focus();
			return;
		}
		if (nppassword.value == "")
		{
			alert("확인 패스워드를 입력하세요.");
			nppassword.focus();
			return;
		}
		
		if (newpassword.value.length < 4)
		{
			alert("패스워드는 4자 이상으로 입력하세요.");
			newpassword.focus();
			return;
		}
		
		if (nppassword.value != newpassword.value)
		{
			alert("변경 패스워드와 확인패스워드가 다릅니다. 확인바랍니다. ");
			newpassword.focus();
			return;
		}		

		
		submit();
	}
}
function winOpen(num){
	
	window.open("kw_staff.jsp?gubun=admin&num=0", "service", "width=500,height=200");
	
}


function insert_reset()
{
	with (document.writeform)
	{
		reset();
		id.focus();
	}
}
