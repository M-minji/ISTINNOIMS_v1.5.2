function insert_go()
{
	with (document.writeform)
	{
		if (name.value == "")
		{
			alert("분류명을 입력하세요.");
			name.focus();
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
		name.focus();
	}
}


function move_go()
{
	with (document.searchform)
	{
		if (root2.value == "")
		{
			alert("분류를 선택하세요.");
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