function chkUpdateIns(){
	with (document.frm)
	{	
		if (sItemCateUname.value == "")
		{
			alert("분류명을 입력하세요.");
			sItemCateUname.focus();
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
			alert("분류명을 입력하세요.");
			sItemCateName.focus();
			return  ;
		}

		if (sItemCateKey.value == "")
		{ 
			return false;
		}

		if (sItemCateUkey.value == "")
		{ 
			return false;
		}

		if (sItemCateUname.value == "")
		{ 
			return false;
		} 
	}
}
// 물품분류 수정
function subInsert(ckey){
	if (isNaN(ckey))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.action = "/sales/item/kw_cate_item_if.do?sItemCateKey="+ckey;
	document.frm.submit();
}


//물품분류 수정
function cateUpdate(){
	document.frm.action = "/sales/item/kw_cate_item_uf.do";
	document.frm.submit();
}

//물품분류 수정
function update(){
	if (chkIns2())
	{ 
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.action = "/sales/item/kw_cate_item_u.do";
	document.frm.submit();
}


// 물품분류 삭제
function cateDelete(ckey){
	if (isNaN(ckey))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.action = "/sales/item/kw_cate_item_d.do?sItemCateKey="+ckey;
	document.frm.submit();
}

//목록으로
function cancle(){
	document.frm.action = "/sales/item/kw_cate_item_lf.do";
	document.frm.submit();
}

//품목분류추가 팝업
function cateitemPop(){ 
	var url = "/sales/item/popup/kw_cate_item_lf.do";
	window.open(url ,"cateItemAdd" ,"width=500,height=600,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
}
