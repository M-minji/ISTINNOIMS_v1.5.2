function insert_go()
{
	with (document.writeform)
	{
		if (yy.value == "" || mm.value == "")
		{
			alert("기간을 입력하세요.");
			return;
		}

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
		subject.focus();
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
		if (yy.value != "" )
		{
			y = yy.value;
		}
		else
		{
			y = year;
		}
		
		if (mm.value != "" )
		{
			m = mm.value;
		}
		else
		{
			m = today.getMonth() + 1;
		}

	}

	document.open();

	document.writeln("<select name='checky' onChange='setCheckyYear(this.value);'>");
	for (var i = year - 10; i <= year + 1; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == y)
		{
			document.write(" selected");
			
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>년&nbsp;&nbsp;");

	document.writeln("<select name='checkm' onChange='setCheckyMm(this.value);'>");
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

function setCheckyYear(obj){
	document.writeform.yy.value=obj;	
}

function setCheckyMm(obj){
	document.writeform.mm.value=obj;		
}
