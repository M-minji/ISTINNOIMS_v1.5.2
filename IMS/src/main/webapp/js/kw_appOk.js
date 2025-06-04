function insert_go()
{
	with (document.writeform)
	{

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

