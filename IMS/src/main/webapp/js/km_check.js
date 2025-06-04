function check()
{
	with (writeform)
	{
		var cm = checkm.value;

		if (checkm.value.length < 2)
		{
			cm = "0" + checkm.value;
		}

		checkdate.value = checky.value + "-" + cm;

		return true;
	}
}


function showMonth()
{
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;

	var y, m;

	with (document.writeform)
	{
		if (checkdate.value.length == 7)
		{
			y = checkdate.value.substring(0, 4);
			m = checkdate.value.substring(5, 7);
		}
		else
		{
			y = year;
			m = today.getMonth() + 1;
		}
	}

	document.open();

	document.writeln("<select name='checky'>");
	for (var i = year - 1; i <= year + 1; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == y)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>년");

	document.writeln("<select name='checkm'>");
	for (var i = 1; i <= 12; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == m)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>월");

	document.close();
}
