
function insert_go()
{
	with (document.writeform)
	{
		if (title.value == "")
		{
			alert("TITLE OF THE INVENTION을 입력하세요.");
			title.focus();
			return;
		}
		if(number.value == "" && nb.value == ""){
			alert("PATENT NUMBER를 입력하세요.");
			number.focus();
			return;
		}
		if(date.value == "" && nb.value == ""){
			alert("REGISTRATION DATE를 입력하세요.");
			date.focus();
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
		if (rank2.value == "")
		{
			alert("특허명을 선택하세요.");
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