function type_check()
{
	with (document.writeform)
	{
		if (type[0].checked == true)
		{
			boardKey.disabled = true;
			program.disabled = true;
			link.disabled = true;
		}else if (type[1].checked == true)
		{
			boardKey.disabled = false;
			program.disabled = true;
			link.disabled = true;
			boardKey.focus();
		}
		else if (type[2].checked == true)
		{
			boardKey.disabled = true;
			program.disabled = false;
			link.disabled = true;
			program.focus();
		}
		else if (type[3].checked == true)
		{
			boardKey.disabled = true;
			program.disabled = true;
			link.disabled = false;
			link.focus();
		}
	}
}


function permit_check()
{
	with (document.writeform)
	{
		if (permit[0].checked == true)
		{
			level.disabled = false;
		}
		else
		{
			level.disabled = true;
		}
	}
}


function insert_go()
{
	with (document.writeform)
	{
		if (name.value == "")
		{
			alert("메뉴명을 입력하세요.");
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
			alert("메뉴를 선택하세요.");
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


function type_click(vtype)
{
	with (document.writeform)
	{
		if (vtype == 'C')
		{
			type[0].click();
		}
		else if (vtype == 'B')
		{
			type[1].click();
		}
		else if (vtype == 'P')
		{
			type[2].click();
		}
		else if (vtype == 'L')
		{
			type[3].click();
		}
	}
}


function permit_click(vpermit)
{
	with (document.writeform)
	{
		if (vpermit == 'T')
		{
			permit[0].click();
		}
		else if (vpermit == 'F')
		{
			permit[1].click();
		}
	}
}
