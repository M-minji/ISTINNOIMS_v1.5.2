function insert_go()
{
	with (document.writeform)
	{
		var sm = startm.value, sd = startd.value, em = endm.value, ed = endd.value;

		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return;
		}

		
		if (sm < 10)
		{
			sm = "0" + sm;
		}

		if (sd < 10)
		{
			sd = "0" + sd;
		}

		if (em < 10)
		{
			em = "0" + em;
		}

		if (ed < 10)
		{
			ed = "0" + ed;
		}

		startdate.value = starty.value + "-" + sm + "-" + sd;
		enddate.value = endy.value + "-" + em + "-" + ed;

		submit();
	}
}


function insert_reset()
{
	with (document.writeform)
	{
		reset();
	}
}
