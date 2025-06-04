
function chkUpdateIns(){
	with (document.frm)
	{	
		if (sgyeonjeonkUname.value == "")
		{
			alert("견적서명을 입력하세요.");
			sgyeonjeonkName.focus();
			return false;
		}
	}
	return true;
}


function chkIns2(){
	
	with (document.frm)
	{
		if (sgyeonjeonkCateName.value == "")
		{
			alert("견적서분류명을 입력하세요.");
			sgyeonjeonkName.focus();
			return false;
		} 
		if (sgyeonjeonkName.value == "")
		{
			alert("견적서명을 입력하세요.");
			sgyeonjeonkEngName.focus();
			return false;
		}
	}
	return true;
}
// 견적서 등록
function gyeonjeokInsert(){
	document.frm.action = "kw_gyeonjeok_if.jsp";
	document.frm.submit();
}

//견적서 등록
function insert_go(){
	if(chkIns2()){
		document.frm.submit();	
	}
}

//견적서등록
function insert(){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	
	document.frm.action = "kw_gyeonjeok_i.jsp";
	document.frm.submit();
}

//견적서 수정
function gyeonjeonkUpdate(key){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.sgyeonjeonkKey.value = key;
	document.frm.action = "kw_gyeonjeok_uf.jsp";
	document.frm.submit();
}

//견적서 수정화면
function gyeonjeonkUpdateMove(){
	document.frm.action = "kw_gyeonjeok_uf.jsp";
	document.frm.submit();
}

//견적서수정실행
function update(){
	if(chkIns2()){
		document.frm.submit();	
	}
}

//견적서 삭제
function gyeonjeonkDelete(key){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.sgyeonjeonkKey.value = key;
	document.frm.action = "kw_gyeonjeok_d.jsp";
	document.frm.submit();
}

//목록으로
function cancle(){
	document.frm.action = "kw_gyeonjeok_lf.jsp";
	document.frm.submit();
}

//견적서추가 팝업
function gyeonjeonkPop(){ 
	var url = "popup/kw_gyeonjeok_lf.jsp";
	//window.open(url ,"AddrAdd" ,"width=500 height=600 menubar=no status=no0");
	window.open(url ,"AddrAdd" ,"width=500,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}
 
