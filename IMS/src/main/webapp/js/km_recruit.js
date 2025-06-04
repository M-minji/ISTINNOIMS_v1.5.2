function insert_go()
{
	
	
	with (document.writeform)
	{

		if (recruit1.value == "")
		{
			alert("채용분야를  입력하세요.");
			recruit1.focus();
			return;
		}
		if (recruit2.value == "")
		{
			alert("지원자격를  입력하세요.");
			recruit2.focus();
			return;
		}
		if (recruit3.value == "")
		{
			alert("제출서류를  입력하세요.");
			recruit3.focus();
			return;
		}
		if (handlingInfo.value == "")
		{
			alert("채용 담당자 를  입력하세요.");
			handlingInfo.focus();
			return;
		}
		if (companyEmail.value == "")
		{
			alert("담당자 메일를  입력하세요.");
			companyEmail.focus();
			return;
		}

		if (eveyFlag.checked == true)
		{
			eveyFlag.value="T";
		}else{
			eveyFlag.value="F";
		}
		
		submit();
	}
}



function insert_reset()
{
	with (document.writeform)
	{
		reset();
		recruit1.focus();
	}
}
