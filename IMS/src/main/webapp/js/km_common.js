// 텍스트 박스에서 엔터를 입력했을 경우
function is_enter()
{
    if (window.event.keyCode == 13)
    {
		return false;
    }
}


function delete_list()
{
	var msg = "하위분류까지 모두 삭제됩니다.\n삭제하시겠습니까?";
	return confirm(msg);
}


function delete_list2()
{
	var msg = "정말로 삭제하시겠습니까?";
	return confirm(msg);
}


function delete_list3()
{
	var msg = "홈페이지의 모든 파일이 교체 됩니다.\n정말로 적용하시겠습니까?";
	return confirm(msg);
}

//시작 ~ 끝 년 월 일 출력 
function showDate()
{
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();

	document.open();

	document.writeln("<select name='starty'>");
	for (var i = year - 1; i <= year + 1; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == year)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>년");

	document.writeln("<select name='startm'>");
	for (var i = 1; i <= 12; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == month)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>월");

	document.writeln("<select name='startd'>");
	for (var i = 1; i <= 31; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == day)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>일 ~ ");

	document.writeln("<select name='endy'>");
	for (var i = year - 1; i <= year + 1; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == year)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>년");

	document.writeln("<select name='endm'>");
	for (var i = 1; i <= 12; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == month)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>월");

	document.writeln("<select name='endd'>");
	for (var i = 1; i <= 31; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == day)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>일");

	document.close();
}

// 
function showDateUpdate()
{
	var today = new Date();
	var year = today.getFullYear();

	var sy, ey;
	var sm, em;
	var sd, ed;

	with (document.writeform)
	{
		sy = startdate.value.substring(0, 4);
		sm = startdate.value.substring(5, 7);
		sd = startdate.value.substring(8, 10);

		ey = enddate.value.substring(0, 4);
		em = enddate.value.substring(5, 7);
		ed = enddate.value.substring(8, 10);
	}

	document.open();

	document.writeln("<select name='starty'>");
	for (var i = year - 1; i <= year + 1; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == sy)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>년");

	document.writeln("<select name='startm'>");
	for (var i = 1; i <= 12; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == sm)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>월");

	document.writeln("<select name='startd'>");
	for (var i = 1; i <= 31; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == sd)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>일 ~ ");

	document.writeln("<select name='endy'>");
	for (var i = year - 1; i <= year + 1; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == ey)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>년");

	document.writeln("<select name='endm'>");
	for (var i = 1; i <= 12; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == em)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>월");

	document.writeln("<select name='endd'>");
	for (var i = 1; i <= 31; i++)
	{
		document.write("<option value='" + i + "'");
		if (i == ed)
		{
			document.write(" selected");
		}
		document.writeln(">" + i + "</option>");
	}
	document.write("</select>일");

	document.close();
}

//팝업 설정하는 함수 (일정한 스타일의 팝업 ) 
function win_open(url, title, width, height)
{
	var option = "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, top=150, left=250, ";
	option += "width=" + width + ", height=" + height;

	window.open(url, title, option);
}

function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}

function nowDateDot(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "." + month + "." + day;
	
    return nowDate;
}

function _CurrencyClear(s){
	var cntArray = new Array();

	for( var i = 0 ; i < s.length ; i++)
	{
		cntArray[i] = s.indexOf(",");
		s = s.replace(",","");

	}
	return s;
}

function cashReturn(numValue){
	var cashReturn = ""; 
	var v = numValue.toString();
	for (var i = v.length-1; i >= 0; i--){ 
		cashReturn = v.charAt(i) + cashReturn; 
		if ( i != 0 && v.charAt(i-1) != "-" && i%3 == v.length%3) cashReturn = "," + cashReturn; 
	} 
	return cashReturn; 
}


function numOnly(obj,isCash) { 
	// <input type="text" name="text" onKeyUp="javascript:numOnly(this,true);"> 

	if (event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39) return;

	var returnValue = "";
        var temp = "";
        temp = obj.value.charAt(0);
        if (temp == "-")
        {
            returnValue = "-"; 
        }
        
	for (var i = 0; i < obj.value.length; i++){
		if (obj.value.charAt(i) >= "0" && obj.value.charAt(i) <= "9") returnValue += obj.value.charAt(i); 
		else returnValue += ""; 
	} 
	
//	returnValue =  returnValue.substr(i);
	
	if (isCash){ 
		obj.value = cashReturn(returnValue); 
		return; 
	} 
	obj.focus(); 
	obj.value = returnValue; 
}


function trim(s)
{
    s = s.replace(/^\s*/,'').replace(/\s*$/, '');
    if(s == "") s="0";
    return s;
} 


function hangul(srcNumber){
	

	if(srcNumber != ""){
	    var i, j=0, k=0;
	    var han1 = new Array("","일","이","삼","사","오","육","칠","팔","구");
	    var han2 = new Array("","만","억","조","경","해","시","양","구","간");
	    var han3 = new Array("","십","백","천");
	    var result="", hangul = srcNumber + "", pm = "";
	    var str = new Array(), str2="";
	    var strTmp = new Array();
	
	    if(parseInt(srcNumber)==0) return "영원 정"; //입력된 숫자가 0일 경우 처리
	    
	    if(hangul.substring(0,1) == "-"){ //음수 처리
            pm = "마이너스 ";
            hangul = hangul.substring(1, hangul.length);
	    }
	    
	
	    for(i=hangul.length; i > 0; i=i-4){
	        str[j] = hangul.substring(i-4,i); //4자리씩 끊는다.
	        for(k=str[j].length;k>0;k--){
	            strTmp[k] = (str[j].substring(k-1,k))?str[j].substring(k-1,k):"";
	            strTmp[k] = han1[parseInt(strTmp[k])];
	            if(strTmp[k]) strTmp[k] += han3[str[j].length-k];
	            str2 = strTmp[k] + str2;
	        }
	
	        str[j] = str2;
	        if(str[j]) result = str[j]+han2[j]+result;
	        j++; str2 = "";
	    }
	
	    return pm + result + "원 정 "; //부호 + 숫자값	
	}else{
		 return "영원 정"; //입력된 숫자가 0일 경우 처리
	}
}

