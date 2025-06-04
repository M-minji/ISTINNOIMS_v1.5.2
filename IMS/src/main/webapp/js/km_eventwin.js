function insert_go()
{
	with (document.writeform)
	{
		//var sm = startm.value, sd = startd.value, em = endm.value, ed = endd.value;

		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return;
		}

		if (width.value == "")
		{
			alert("넓이를 입력하세요.");
			width.focus();
			return;
		}

		if (isNaN(width.value))
		{
			alert("넓이를 숫자로 입력하세요.");
			width.select();
			return;
		}

		if (height.value == "")
		{
			alert("높이를 입력하세요.");
			height.focus();
			return;
		}

		if (isNaN(height.value))
		{
			alert("높이를 숫자로 입력하세요.");
			height.select();
			return;
		}

		if(dateChk() == false){
			return;
		}

		submit();
	}
}

function dateChk(){
	var result = true;
	var startdate = chkDate(document.getElementsByName("startdate")[0].value);
	var enddate = chkDate(document.getElementsByName("enddate")[0].value);
		
	if(startdate > enddate){
		alert("기간을 확인하세요.");
		result = false;
	}	
	
	return result;
}



function insert_reset()
{
	with (document.writeform)
	{
		reset();
	}
}

function chkDate(chkDate){
    var str = chkDate;
    var strArray = str.split("-");
    var result = "";
    for(var i=0; i<strArray.length; i++){
        result = result + strArray[i];
    }
    //var result = strArray[0] + strArray[1] + strArray[2]
    return result;
}
