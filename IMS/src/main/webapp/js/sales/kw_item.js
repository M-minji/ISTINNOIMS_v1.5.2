function chkUpdateIns(){
	with (document.frm)
	{	
		if (sItemUname.value == "")
		{
			alert("품목명을 입력하세요.");
			sItemName.focus();
			return false;
		}
	}
	return true;
}


function chkIns2(){
	
	with (document.frm)
	{
		if (sItemCateName.value == "")
		{
			alert("품목분류명을 입력하세요.");
			sItemName.focus();
			return false;
		} 
		if (sItemName.value == "")
		{
			alert("품목명을 입력하세요.");
			sItemEngName.focus();
			return false;
		}
	}
	return true;
}
// 품목 등록
function itemInsert(){
	document.frm.action = "kw_item_if.jsp";
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
	
	document.frm.action = "kw_item_i.jsp";
	document.frm.submit();
}

//품목 수정
function itemUpdate(key){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.sItemKey.value = key;
	document.frm.action = "kw_item_uf.jsp";
	document.frm.submit();
}

//품목수정
function update(){
	if(chkIns2()){
		document.frm.submit();	
	}
}


//품목 삭제
function itemDelete(key){
	if (isNaN(key))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.sItemKey.value = key;
	document.frm.action = "kw_item_d.jsp";
	document.frm.submit();
}

//목록으로
function cancle(){
	document.frm.action = "kw_item_lf.jsp";
	document.frm.submit();
}

//품목추가 팝업
function itemPop(){ 
	var url = "popup/kw_item_lf.jsp";
	//window.open(url ,"AddrAdd" ,"width=500 height=600 menubar=no status=no0");
	window.open(url ,"AddrAdd" ,"width=500,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}


//품목추가 팝업
function itemOnePop(){ 
	var url = "popup/kw_item_one_lf.jsp";
	//window.open(url ,"AddrAdd" ,"width=500 height=600 menubar=no status=no0");
	window.open(url ,"AddrAdd" ,"width=500,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}

//품목분류추가 팝업
function cateitemPop(){ 
	var url = "popup/kw_cate_item_lf.jsp";
	window.open(url ,"cateItemAdd" ,"width=500,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}
