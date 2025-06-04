function insert_go()
{
	with (document.writeform)
	{
		if (staffKey.value == "")
		{
			alert("받는사람을 선택하세요.");
			staffKey.focus();
			return;
		}

		if (noteSub.value == "")
		{
			alert("제목을 입력하세요.");
			noteSub.focus();
			return;
		}

		if (noteCon.value == "")
		{
			alert("제목을 입력하세요.");
			noteCon.focus();
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
		noteSub.focus();
	}
}
