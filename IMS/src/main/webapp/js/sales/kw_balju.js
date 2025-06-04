function chkUpdateIns(){
	with (document.frm)
	{	
		if (sbaljuUname.value == "")
		{
			alert("품목명을 입력하세요.");
			sbaljuName.focus();
			return false;
		}
	}
	return true;
}


function chkIns2(){
	
	with (document.frm)
	{
		if (sbaljuCateName.value == "")
		{
			alert("품목분류명을 입력하세요.");
			sbaljuName.focus();
			return false;
		} 
		if (sbaljuName.value == "")
		{
			alert("품목명을 입력하세요.");
			sbaljuEngName.focus();
			return false;
		}
	}
	return true;
}
// 품목 등록
function baljuInsert(){
	document.frm.action = "kw_balju_if.jsp";
	document.frm.submit();
}

//품목 등록
function insert_go(){
	if(chkIns2()){
		document.frm.submit();	
	}
}

//품목등록
function insert(){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	
	document.frm.action = "kw_balju_i.jsp";
	document.frm.submit();
}

//품목 수정
function baljuUpdate(key){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.sbaljuKey.value = key;
	document.frm.action = "kw_balju_uf.jsp";
	document.frm.submit();
}

//품목수정
function update(){
	if(chkIns2()){
		document.frm.submit();	
	}
}


//품목 삭제
function baljuDelete(key){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.sbaljuKey.value = key;
	document.frm.action = "kw_balju_d.jsp";
	document.frm.submit();
}

//목록으로
function cancle(){
	document.frm.action = "kw_balju_lf.jsp";
	document.frm.submit();
}

//품목추가 팝업
function baljuPop(){ 
	var url = "popup/kw_balju_lf.jsp";
	//window.open(url ,"AddrAdd" ,"width=500 height=600 menubar=no status=no0");
	window.open(url ,"AddrAdd" ,"width=500,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

//품목분류추가 팝업
function catebaljuPop(){ 
	var url = "popup/kw_cate_balju_lf.jsp";
	window.open(url ,"catebaljuAdd" ,"width=500,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

