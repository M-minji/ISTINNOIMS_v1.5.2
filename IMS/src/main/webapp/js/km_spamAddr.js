function check()
{
	with (writeform)
	{
		return true;
	}
}
function insert_go()
{
	with (document.writeform)
	{
		if (email.value == "")
		{
			alert("주소를 입력하세요.");
			email.focus();
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
		email.focus();
	}
}

