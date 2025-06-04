function change_ans(flag)
{
	with (document.writeform)
	{
		if (flag == "F")
		{
			document.all.ans.style.display = "none";
		}
		else
		{
			document.all.ans.style.display = "block";
		}
	}
}

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

		if (question.value == "")
		{
			alert("질문내용을 입력하세요.");
			question.focus();
			return;
		}

		if (objectFlag[0].checked && answer[0].value == "")
		{
			alert("예시를 2개이상 입력하세요.");
			answer[0].focus();
			return;
		}

		if (objectFlag[0].checked && answer[1].value == "")
		{
			alert("예시를 2개이상 입력하세요.");
			answer[1].focus();
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


function object_click(vobject)
{
	with (document.writeform)
	{
		if (vobject == 'T')
		{
			objectFlag[0].click();
		}
		else if (vobject == 'F')
		{
			objectFlag[1].click();
		}

		objectFlag[0].disabled = true;
		objectFlag[1].disabled = true;
	}
}
