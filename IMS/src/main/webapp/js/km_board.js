function insert_go()
{
	with (document.writeform)
	{
		if (name.value == "")
		{
			alert("게시판명을 입력하세요.");
			name.focus();
			return;
		}
		
		if(calendarFlag[0].checked == true){
			if($('#calendarmngKey').val() == 0){
				alert("일정테이블을 선택하세요.");
				return;
			}
		}

		if (isNaN(fileNumber.value))
		{
			alert("업로드파일 개수를 숫자로 입력하세요.");
			fileNumber.select();
			return;
		}

		if (isNaN(fileSize.value))
		{
			alert("업로드파일 크기를 숫자로 입력하세요.");
			fileSize.select();
			return;
		}
/*
		if (isNaN(linkNumber.value))
		{
			alert("링크 개수를 숫자로 입력하세요.");
			maxNumber.select();
			return;
		}
*/
		if (isNaN(pageLength.value))
		{
			alert("페이지당 게시물 개수를 숫자로 입력하세요.");
			pageLength.select();
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


function file_check()
{
	with (document.writeform)
	{
		if (fileFlag[0].checked == true)
		{
			fileNumber.disabled = false;
			fileSize.disabled = false;
		}
		else
		{
			fileNumber.disabled = true;
			fileSize.disabled = true;
		}
	}
}

function calendar_check()
{
	with (document.writeform)
	{
		if (calendarFlag[0].checked == true)
		{
			calendarmngKey.disabled = false;
		}
		else
		{
			calendarmngKey.disabled = true;
			$('#calendarmngKey').val('0');
		}
	}
}

function read_check()
{
	with (document.writeform)
	{
		if (readPermit[1].checked == true)
		{
			readLevel.disabled = false;
		}
		else
		{
			readLevel.disabled = true;
		}
	}
}


function write_check()
{
	with (document.writeform)
	{
		if (writePermit[1].checked == true)
		{
			writeLevel.disabled = false;
		}
		else
		{
			writeLevel.disabled = true;
		}
	}
}


function reply_check()
{
	with (document.writeform)
	{
		if (replyPermit[1].checked == true)
		{
			replyLevel.disabled = false;
		}
		else
		{
			replyLevel.disabled = true;
		}
	}
}


function comment_check()
{
	with (document.writeform)
	{
		if (commentPermit[1].checked == true)
		{
			commentLevel.disabled = false;
		}
		else
		{
			commentLevel.disabled = true;
		}
	}
}


function file_click(vfile)
{
	with (document.writeform)
	{
		if (vfile == 'T')
		{
			fileFlag[0].click();
		}
		else
		{
			fileFlag[1].click();
		}
	}
}


function read_click(vread)
{
	with (document.writeform)
	{
		if (vread == 'A')
		{
			readPermit[0].click();
		}
		else if (vread == 'M')
		{
			readPermit[1].click();
		}
		else if (vread == 'E')
		{
			readPermit[3].click();
		}
		else
		{
			readPermit[2].click();
		}
	}
}


function write_click(vwrite)
{
	with (document.writeform)
	{
		if (vwrite == 'A')
		{
			writePermit[0].click();
		}
		else if (vwrite == 'M')
		{
			writePermit[1].click();
		}
		else
		{
			writePermit[2].click();
		}
	}
}


function reply_click(vreply)
{
	with (document.writeform)
	{
		if (vreply == 'A')
		{
			replyPermit[0].click();
		}
		else if (vreply == 'M')
		{
			replyPermit[1].click();
		}
		else
		{
			replyPermit[2].click();
		}
	}
}


function comment_click(vcomment)
{
	with (document.writeform)
	{
		if (vcomment == 'A')
		{
			commentPermit[0].click();
		}
		else if (vcomment == 'M')
		{
			commentPermit[1].click();
		}
		else
		{
			commentPermit[2].click();
		}
	}
}