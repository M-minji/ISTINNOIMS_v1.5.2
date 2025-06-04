function insert_go()
{
	with (document.writeform)
	{
		if (content.value == "")
		{
			alert("내용을 입력하세요.");
			content.focus();
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
		content.focus();
	}
}
