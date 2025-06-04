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

		submit();
	}
}


function insert_reset()
{
	with (document.writeform)
	{
		reset();
		filename1.focus();
	}
}


function move_go()
{
	with (document.searchform)
	{
		if (step2.value == "")
		{
			alert("카탈로그를 선택하세요.");
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


function change_type()
{
	with (document.searchform)
	{
		group.value = "";
		submit();
	}
}