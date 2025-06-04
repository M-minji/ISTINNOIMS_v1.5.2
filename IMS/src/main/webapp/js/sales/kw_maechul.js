function chkIns(){
	with (document.frm)
	{
		
		if (sDealMaechulCode.selectedIndex == 0)
		{
			alert("매출상태를 선택하세요.");
			sDealMaechulCode.focus();
			return  ;
		}
		
		if (sComName.value == "")
		{
//			alert(sComName.value + sComKey.value +"++++++++sComName.value + sComKey.value ");
			alert("기업명을 입력하세요.");
			sComName.focus();
			return false;
		}
		
		if (sBaljuNumber.value == "")
		{
			alert("발주번호를 입력하세요.");
			sBaljuNumber.focus();
			return false;
		}
		
		if (sDealBaljuDate.value == "")
		{
			alert("발주일을 입력하세요.");
			sDealBaljuDate.focus();
			return false;
			
		}
			
		if (sDealMaechulDate.value == "")
		{
			alert("매출일을 입력하세요.");
			sDealMaechulDate.focus();
			return false;
			
		}
		
		if (sDealMaechulTotalMoney.value == "")
		{
			alert("발주총액을 입력하세요.");
			sDealMaechulTotalMoney.focus();
			return false;
		}
		
		if (sDealMaechulRealMoney.value == "")
		{
			alert("실납품액을 입력하세요.");
			sDealMaechulRealMoney.focus();
			return false;
		}
		
		
		
	}
	return true;
}
//매출 수정
function chkUp(){
	with (document.frm)
	{
		
		if (sDealMaechulCode.selectedIndex == 0)
		{
			alert("매출상태를 선택하세요.");
			sDealMaechulCode.focus();
			return  ;
		}
	}
	return true;
}


//품목 처리
var itemIndex = 0;
function setItemPopList(objList){ 
//	alert("objList = "+objList.length);
	for(var i = 0 ; i < objList.length ; i++){
		var obj = objList[i];
//		alert("sItemKey = "+obj.sItemKey
//				+"\n sItemName = "+obj.sItemName
//				);
		
		var ulobj = document.getElementById("itemUL");
		var liobj = document.createElement('LI');
		var idx = ulobj.childNodes.length;
		
		
		itemIndex++;
		liobj.id = "item_" + itemIndex;
		liobj.style.display = "inline";
		liobj.style.padding = "0";
		ulobj.appendChild(liobj);
		
		
		var innerStr = "";
		innerStr+="<input type='hidden' id='itemIndex' name='itemIndex' value='"+itemIndex+"' />";
		innerStr+="<input type='text'   id='receiveName' 		name='receiveName' 		style='width:120px' value='"+obj.sItemName+"'/>";
		innerStr+="<input type='hidden' id='maechulItemKey' 	name='maechulItemKey' 		value='0'/>";
		innerStr+="<input type='hidden' id='itemKey' 		    name='itemKey' 			value='"+obj.sItemKey+"'/>";
		innerStr+="<input type='hidden' id='itemName' 			name='itemName' 		value='"+obj.sItemName+"'/>";
		innerStr+="<img src=\"/intranet/image/btn/btn_del_s2.gif\" style=\"padding:0 5px 0 5px; vertical-align:middle; cursor:pointer;\" onclick=\"itemDel('item_" + itemIndex+"');\"> ";
		liobj.innerHTML = innerStr;	
	}
	
}


function itemDel(idx){
	document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));
}

//파일 추가
var fileIndex = 0;
function fileAdd(){
	var ulobj = document.getElementById("fileUL");
	var liobj = document.createElement('LI');
	var idx = ulobj.childNodes.length;
	
	fileIndex++;
	liobj.id = "filename_" + fileIndex;
	liobj.style.display = "inline";
	liobj.style.padding = "0";
	ulobj.appendChild(liobj);
//	document.getElementById("fileIndex").value = fileIndex;
	
	var innerStr = "";
	innerStr+="<b>"+fileIndex+".</b>";
	innerStr+="<input type='hidden' id='fileKey' name='fileKey' value='0' />";
	innerStr+="<input type='hidden' id='fileIndex' name='fileIndex' value='"+fileIndex+"' />";
	innerStr+="<input type='file' id='filename' 	name='filename"+fileIndex+"'  style='width:300px'/>";
	innerStr+="<img src=\"/intranet/image/btn/btn_del_s2.gif\" style=\"padding:0 5px 0 5px; vertical-align:middle; cursor:pointer;\" onclick=\"fileDel('filename_" + fileIndex+"');\"><br>";
	liobj.innerHTML = innerStr;	
	
}

function fileDel(idx){
	document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));
}


var win = null;
//품목 팝업
function itemOpen(){
	var sUrl = "../item/popup/kw_item_lf.jsp";
	var sName = "item_add";
	if(!win||win.closed){
		win = window.open(sUrl,sName,"width=500, height=600, scrollbars=yes, resizeable=no, left=150, top=150");
		win.opener.self;
	}else{
		win.location = sUrl;
		win.focus();
	}
}
var win = null;
//거래처  팝업
function comPop(){ 
		var sUrl = "../company/popup/kw_company.jsp";
		var sName = "AddrAdd";
		if(!win||win.closed){
			win = window.open(sUrl,sName,"width=320, height=500, scrollbars=yes, resizeable=no, left=150, top=150");
			win.opener.self;
		}else{
			win.location = sUrl;
			win.focus();
		}
}

