function insert_go()
{
	with (document.writeform)
	{
		if (group.value == "")
		{
			alert("그룹명을 입력하세요.");
			group.focus();
			return;
		}

		if (subject.value == "")
		{
			alert("배너명을 입력하세요.");
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
		if (step2.value == "")
		{
			alert("배너를 선택하세요.");
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


function change_group()
{
	with (document.writeform)
	{
		group.value = group2.value;
	}
}