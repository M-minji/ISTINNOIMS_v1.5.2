
function chkIns(){
	with (document.frm)
	{
		if (sGubunCateKey.selectedIndex == 0)
		{
			alert("구분을 입력하세요.");
			sGubunCateKey.focus();
			return  ;
		}

		if (sGubunName.value == "")
		{
			alert("구분명을 입력하세요.");
			sGubunName.focus();
			return false;
		}

		if (sGubunMemo.value == "")
		{
			alert("구분약어를 입력하세요.");
			sGubunMemo.focus();
			return false;
		}
	}
	return true;
}



//코드 수정화면
function gubunUpdate(){
	document.frm.action = "/sales/gubun/kw_gubun_uf.do";
	document.frm.submit();
}

//코드 수정실행
function update(){
	document.frm.action = "/sales/gubun/kw_gubun_u.do";
	document.frm.submit();
}


// 코드 삭제
function gubunDelete(ckey){
	if(!confirm("삭제하시겠습니까?")){
		return false;
	}
	if (isNaN(ckey))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.sGubunKey.value = ckey;
	document.frm.action = "/sales/gubun/kw_gubun_d.do";
	document.frm.submit();
}

//목록으로
function cancle(){
	document.frm.action = "/sales/gubun/kw_gubun_lf.do";
	document.frm.submit(); 
}

