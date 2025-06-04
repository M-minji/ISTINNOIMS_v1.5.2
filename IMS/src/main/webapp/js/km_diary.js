function insert_go()
{
	
	
	with (document.writeform)
	{

		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
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


function check()
{
	with (document.writeform)
	{
		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return false;
		}

		if (content.value == "")
		{
			alert("내용을 입력하세요.");
			content.focus();
			return false;
		}
		var date1 = new Date($("#startdate").datepicker("getDate"));
	    var date2 = new Date($("#enddate").datepicker("getDate"));
	     
		if(date2 - date1 < 0){
			alert("종료 날짜가 시작날짜보다 이전일수 없습니다"); 
			return false;
		}
		return true;
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

//현재날짜 구하기 
function nowDate() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1)
			: date.getMonth() + 1;
	var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
	var nowDate = year + "-" + month + "-" + day;
	return nowDate;
}