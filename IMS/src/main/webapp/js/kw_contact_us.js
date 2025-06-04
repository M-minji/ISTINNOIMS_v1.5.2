function insert_go()
{
	with (document.writeform)
	{
		if (name.value == "")
		{
			alert("성명을 입력하세요.");
			return;
		}
		
		if (email.value == "")
		{
			alert("이메일을 입력하세요.");
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

function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
	if ( tMonth < 10) tMonth = "0"+tMonth;
	if ( tDate < 10) tDate = "0"+tDate;
	document.getElementById("inDate").value = tDay.getFullYear()+"년"+tMonth+"월"+tDate+"일";
 }
