function insert_go()
{
	with (document.writeform)
	{
		if (name.value == "")
		{
			alert("일정테이블명을 입력하세요.");
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


function permit_check()
{
	with (document.writeform)
	{
		if (permit[0].checked)
		{
			level.disabled = true;
		}
		else
		{
			level.disabled = false;
		}
	}
}


function permit_click(vpermit)
{
	with (document.writeform)
	{
		if (vpermit == 'A')
		{
			permit[0].click();
		}
		else
		{
			permit[1].click();
		}
	}
}
