function chkIns(){
	
	with (document.frm)
	{		
		
		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return false;
		}

		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}

		if (name.value == "")
		{
			alert("이름을 입력하세요.");
			name.focus();
			return false;
		}

		if (password.value == "")
		{
			alert("비밀번호를 입력하세요.");
			password.focus();
			return false;
		}
	}
}


function chkIns2(){
	
	with (document.writeform)
	{
		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return  ;
		}

		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}

		if (name.value == "")
		{
			alert("이름을 입력하세요.");
			name.focus();
			return false;
		}

		if (telephone1.value == "")
		{
			alert("연락처를 입력하세요.");
			telephone1.focus();
			return false;
		}

		if (telephone2.value == "")
		{
			alert("연락처를 입력하세요.");
			telephone2.focus();
			return false;
		}

		if (telephone3.value == "")
		{
			alert("연락처를 입력하세요.");
			telephone3.focus();
			return false;
		}

		if (isNaN(telephone1.value))
		{
			alert("숫자를 입력하세요.");
			telephone1.select();
			return false;
		}

		if (isNaN(telephone2.value))
		{
			alert("숫자를 입력하세요.");
			telephone2.select();
			return false;
		}

		if (isNaN(telephone3.value))
		{
			alert("숫자를 입력하세요.");
			telephone3.select();
			return false;
		}
	}
}

function insert_tgo(){
	
	with (document.frm)
	{
		if (pubflag.checked == true)
		{
			pubflag.value="T";
		}else{
			pubflag.value="F";
		}
	}
	
	if(chkIns() == false)	return;
	document.frm.submit();
}

function insert_go()
{

	if(chkIns() == false)	return;
	
	document.frm.submit();
}


function insert_go2()
{
	if(chkIns2() == false)	return;
	document.frm.submit();
}


function insert_reset()
{
	with (document.frm)
	{
		reset();
		subject.focus();
	}
}


function showLink(num)
{
	if (num == 0)
	{
		return;
	}

	document.open();

	for (var i = 0; i < num; i++)
	{
		document.writeln("<tr>");
		document.writeln("<TH class=line_right scope=row>링크" + (i + 1) + "</th>");
		document.writeln("<TD class=td_view><input type='text' name='filename" + i + "' maxLength='200' size='80' class=''></td>");
		document.writeln("</tr>");
	}

	document.close();

}


function showFile(num, size, flag)
{
		
	if (flag == "F")
	{
		return;
	}

		document.open();
	
		for (var i = 0; i < num; i++)
		{
			document.writeln("<tr>");
			document.writeln("<TH class=line_right scope=row>첨부파일" + (i + 1) + "</th>");
			document.writeln("<TD class=td_view colspan='3'><input type='file' name='filename" + i + "' maxLength='100' size='45' class=''>(용량 : " + size + " MB)</td>");
			document.writeln("</tr>");
		}
	
	/*	document.writeln("<tr>");
		document.writeln("<TH class=line_right scope=row>용량제한</th>");
		document.writeln("<TD class=td_view>" + size + " MB</td>");
		document.writeln("</tr>");*/
		document.close();

}


function showIcon(filename)
{
	var imgname = "/jsp/board/image/";
	var temp = filename.toLowerCase().split(".");
	var ext = temp[temp.length - 1];

	if (filename == "")
	{
		return "";
	}

	if (ext == "txt")
	{
		imgname += "icn_txt.gif";
	}
	else if (ext == "zip" || ext == "rar" || ext == "tar")
	{
		imgname += "icn_als.gif";
	}
	else if (ext == "hwp")
	{
		imgname += "icn_hwp.gif";
	}
	else if (ext == "ppt")
	{
		imgname += "icn_ppt.gif";
	}
	else if (ext == "doc")
	{
		imgname += "icn_word.gif";
	}
	else if (ext == "pdf")
	{
		imgname += "icn_pdf.gif";
	}
	else if (ext == "xls")
	{
		imgname += "icn_excel.gif";
	}
	else if (ext == "gif" || ext == "jpg")
	{
		imgname += "icn_img.gif";
	}
	else if (ext == "bmp" || ext == "psd")
	{
		imgname += "icn_photo.gif";
	}
	else if (ext == "mp3" || ext == "asx" || ext == "mov" || ext == "mpg" || ext == "avi")
	{
		imgname += "icn_speaker.gif";
	}
	else
	{
		imgname += "icn_m.gif";
	}

	document.open();
	document.writeln("<img src='" + imgname + "' align='absmiddle'>");
	document.close();
}

function MoveEditor(){
	src = "<iframe id = 'editorFrm' src='/jsp/webeditor/elnsEdit.jsp' frameborder=0 width=670px height=570px style='border:0 solid'></iframe>";
	document.getElementById("editor_write").innerHTML = src;
	document.getElementById("editor_write").style.display = "inline";
}


function note_save(){
	
	var str = document.getElementById("editorFrm").contentWindow.saveAs();
	//document.getElementById("editorHtml").innerHTML = str;
	
	alert(str);
	with (document.writeform)
	{
		
		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return false;
		}

		if (id.value == "")
		{
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}

		if (name.value == "")
		{
			alert("이름을 입력하세요.");
			name.focus();
			return false;
		}

		if (password.value == "")
		{
			alert("비밀번호를 입력하세요.");
			password.focus();
			return false;
		}

		//return true;
	}

}
