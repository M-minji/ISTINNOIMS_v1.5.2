function insert_go()
{
	with (document.frm)
	{
		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}

		return true;
	}
}


function insert_go2()
{
	with (document.writeform)
	{

		if (checkEmail() == false)
		{
			email.focus();
			return false;
		}


		if (name.value == "")
		{
			alert("이름을 입력하세요.");
			name.focus();
			return false;
		}

		return true;
	}
}


function checkEmail() 
{
	var result = true;
	var mailObj = document.writeform.email;
    var txt = document.writeform.email.value;    
    
    if(txt.length == 0)
    {
    	result = false;
    }
    for (i=0; i<txt.length; i++) 
    {   
        if (txt.charCodeAt(i)>127) 
        {  
            alert("한글을 사용할수 없습니다.") ;
            mailObj.focus(); // 포커스 지정
            result = false; 
        } 
    }
    if (txt.indexOf("@")<3)
    {  
        alert("이메일 형식이 잘못 되었거나 @가 빠졌습니다..") ;
        mailObj.focus(); // 포커스 지정
        result = false;
    }
    
    return result;
}   




function insert_reset()
{
	with (document.writeform)
	{
		reset();
		id.focus();
	}
}


function findPass()
{
    newWin = window.open("/jsp/login/kw_lostpass_f.jsp", "", "top=100, left=200, width=500, height=280");
}
