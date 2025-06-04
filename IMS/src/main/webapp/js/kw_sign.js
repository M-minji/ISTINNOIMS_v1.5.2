function insert()
{
	with (document.writeform)
	{
		if (kSignPathKey.value == "0")
		{
			alert("결재경로를 선택하세요.");
			pathKey.focus();
			return false;
		}

		if (kSignDocCode.value == "")
		{
			alert("문서번호를 선택하세요.");
			docCode.focus();
			return false;
		}

		if (kSignSubject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return false;
		}

/*		if (filename.value == "")
		{
			alert("파일명을 입력하세요.");
			filename.focus();
			return false;
		}
		*/
		return true;
	}
}



function insert2()
{
	with (document.writeform)
	{
		var tmpLen = staffKeys.length;
		var tmpStaff = "false";
				
		for(var i=0; i<tmpLen; i++){
						
			if (staffKeys[i].value != "0")
			{
				tmpStaff = "true";
			}
		}
		
		if (tmpStaff == "false")
		{
			alert("결재자를 선택하세요.");
			return false;
		}

		
		if (trim(subject.value) == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return false;
		}

		return true;	
	}
}


function addRow()
{
	var tableObj = document.getElementById("fileLine"), _tr, _td;
    var len=(tableObj.rows.length)+1;
    
    
	if (len > 3)
	{
		return;
	}

	_tr = tableObj.insertRow(-1);  
	_td = _tr.insertCell(-1);
    _td.innerHTML = "<input type='file' name='filename" + len + "' size='54' />";

}

function addRowRefer()
{
	var tableObj = document.getElementById("referLine"), _tr, _td;
    var len=(tableObj.rows.length)+1;

	if (len > 4)
	{
		return;
	}

	_tr = tableObj.insertRow(-1);  
	_td = _tr.insertCell(-1);
	_td.bgColor = "#DDDDDD";
	_td.innerHTML = "* 협조자";
	_td = _tr.insertCell(-1);
	_td.colSpan = 3;
    _td.innerHTML = "<input type='hidden' id='referSign" + len + "' name='referSign' value='' />";
    _td.innerHTML += "<input id='referSignName" + len + "' name='referSignName' type='text' size='14' value='' readonly />";
    _td.innerHTML += "<input type='button' value='선택하기' style='width:85px' onclick='javascript:winOpen("+len+");'/>";

}

function winOpen(num){
	
	window.open("kw_staff.jsp?gubun=refer&num="+num, "service", "width=425,height=225");
	
}

 
function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}


function tmpStatus(status){
	document.writeform.kSignStatus.value = status;
	if(!insert()){
		return;
	}
	document.writeform.submit();
}



function pubChk()
{
	with (document.writeform)
	{
		
		if (kSignPubSignflag.checked == true)
		{
			kSignPubSignflag.value="T";
		}else{
			kSignPubSignflag.value="F";
		}
		
		if (kSignPubPubflag.checked == true)
		{
			kSignPubPubflag.value="T";
		}else{
			kSignPubPubflag.value="F";
		}
		
		
		return true;
	}
}

function winPop(pubKey,signKey ){
	var width = 800;
    var height = 900;
    var ediWindow =  
	window.open("kw_sign_pub_print.jsp?pubKey="+pubKey+"&signKey="+signKey,"시행문","toolbar=0,menubar=0,location=0,directories=0,status=1,scrollbars=1,resizable=0,width="+width+",height="+height+", top=0, left=0");
    ediWindow.focus();								
}

function winPr(){
	document.body.innerHTML = document.getElementById('pre').innerHTML;
	window.print();
	
}

function selPathKey(sel){

	//document.writeform.tmpPathKey.value=sel;	
	var url = "kw_sign_select.jsp?pathKey=" + sel;
	var signTmp = "", cirTmp=""; 
	$.ajaxSetup({ async: false }); //ajax 사용 동기화 시키기
	$.ajax({
		type : "get",
		url : url,
		dataType : "xml",
		success : function(xml) {
			$(xml).find("items").each(function() {
				signTmp = $(this).find("signTmp").text();
				//cirTmp = $(this).find("cirTmp").text();
				
			});
			
		}
	});
	// && cirTmp == ""
	if(signTmp == ""){
		document.getElementById("selPath").style.display = "none";
		
	}else{
		document.getElementById("selPath").style.display = "";
		document.writeform.tmpSign.value = signTmp;
		//document.writeform.tmpCir.value = cirTmp;
	}
}