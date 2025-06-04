function insert_go()
{
	with (document.writeform)
	{
		if (subject.value == "")
		{
			alert("등급명을 입력하세요.");
			subject.focus();
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
		subject.focus();
	}
}


function move_go()
{
	with (document.searchform)
	{
		if (rank2.value == "")
		{
			alert("등급을 선택하세요.");
			return;
		}

		submit();
	}
}


function move_reset()
{
	with (document.searchform)
	{
		reset();
	}
}