
function chkIns(){
	with (document.frm)
	{
		
		if (sComGubun.selectedIndex == 0)
		{
			alert("구분을 선택하세요.");
			sComGubun.focus();
			return  ;
		}
		if (sComName.value == "")
		{
			alert("기업명을 입력하세요.");
			sComName.focus();
			return false;
		}
		
		if (sComBrn1.value == "" || sComBrn2.value == "" || sComBrn3.value == "")
		{
			alert("사업자번호을 입력하세요.");
			sComBrn1.focus();
			return false;
			
		}
		if(isNaN(sComBrn1.value)||isNaN(sComBrn2.value)||isNaN(sComBrn3.value)){
			alert("사업자 번호는 숫자만 입력하세요.");
			sComBrn1.focus();
			return false;
		}
			
		if (sComPhone1.value == "" || sComPhone2.value == "" || sComPhone3.value == "")
		{
			alert("전화번호을 입력하세요.");
			sComPhone1.focus();
			return false;
		}
		
		if(isNaN(sComPhone1.value)||isNaN(sComPhone2.value)||isNaN(sComPhone3.value)){
			alert("전화번호는 숫자만 입력하세요.");
			sComPhone1.focus();
			return false;
		}
		
		if (sComFax1.value == "" || sComFax2.value == "" || sComFax3.value == "")
		{
			alert("팩스번호을 입력하세요.");
			sComFax1.focus();
			return false;
		}
		
		if(isNaN(sComFax1.value)||isNaN(sComFax2.value)||isNaN(sComFax3.value)){
			alert("팩스번호는 숫자만 입력하세요.");
			sComFax1.focus();
			return false;
		}
		var damgdangArr = document.getElementsByName("referIndex").length;
		if(damgdangArr != 0){
			for(var i=0; i < damgdangArr ; i++){
				if(document.getElementsByName("damdangName")[i].value ==""){
					alert(document.getElementsByName("referIndex")[i].value+"번째 성함을 입력해주세요.");
					document.getElementsByName("damdangName")[i].focus();
					return false;
				}
				if(document.getElementsByName("damdangPhone1")[i].value =="" && document.getElementsByName("damdangPhone2")[i].value =="" && document.getElementsByName("damdangPhone1")[i].value ==""){
					alert(document.getElementsByName("referIndex")[i].value+"번째 전화번호를 입력해주세요.");
					document.getElementsByName("damdangPhone1")[i].focus();
					return false;
				}
				if(document.getElementsByName("damdangEmail")[i].value ==""){
					alert(document.getElementsByName("referIndex")[i].value+"번째 이메일을 입력해주세요.");
					document.getElementsByName("damdangEmail")[i].focus();
					return false;
				}
			}
		}else{
			alert("담당자를 입력해 주세요.");
			return false;
		}
		var itemArr = document.getElementsByName("itemIndex").length;
		if(itemArr ==0){
			alert("관련품목을 선택해 주세요.");
			return false;
		}
		var fileArr = document.getElementsByName("fileIndex").length;
		if (fileArr ==0){
			alert("파일명을 입력하세요.");
			return false;
		}
	}
	return true;
}
function chkUp(){
	with (document.frm)
	{
		if (sComGubun.selectedIndex == 0)
		{
			alert("구분을 선택하세요.");
			sComGubun.focus();
			return  ;
		}
		if (sComName.value == "")
		{
			alert("기업명을 입력하세요.");
			sComName.focus();
			return false;
		}
		
		if (sComBrn1.value == "" || sComBrn2.value == "" || sComBrn3.value == "")
		{
			alert("사업자번호을 입력하세요.");
			sComBrn1.focus();
			return false;
		}
		
		if(isNaN(sComBrn1.value)||isNaN(sComBrn2.value)||isNaN(sComBrn3.value)){
			alert("사업자 번호는 숫자만 입력하세요.");
			sComBrn1.focus();
			return false;
		}
			
		if (sComPhone1.value == "" || sComPhone2.value == "" || sComPhone3.value == "")
		{
			alert("전화번호을 입력하세요.");
			sComPhone1.focus();
			return false;
		}
		
		if(isNaN(sComPhone1.value)||isNaN(sComPhone2.value)||isNaN(sComPhone3.value)){
			alert("전화번호는 숫자만 입력하세요.");
			sComPhone1.focus();
			return false;
		}
		
		if (sComFax1.value == "" || sComFax2.value == "" || sComFax3.value == "")
		{
			alert("팩스번호을 입력하세요.");
			sComFax1.focus();
			return false;
		}
		
		if(isNaN(sComFax1.value)||isNaN(sComFax2.value)||isNaN(sComFax3.value)){
			alert("팩스번호는 숫자만 입력하세요.");
			sComFax1.focus();
			return false;
		}
		var damgdangArr1 = document.getElementsByName("indexKey").length;
		var damgdangArr2 = document.getElementsByName("referIndex").length;
		if(damgdangArr1 != 0 || damgdangArr2 != 0){
			for(var i=0; i < damgdangArr1 ; i++){
				if(document.getElementsByName("damdangName")[i].value ==""){
					alert(document.getElementsByName("indexKey")[i].value+"번째 담당자 성함을 입력해주세요.");
					document.getElementsByName("damdangName")[i].focus();
					return false;
				}
				if(document.getElementsByName("damdangPhone1")[i].value =="" && document.getElementsByName("damdangPhone2")[i].value =="" && document.getElementsByName("damdangPhone1")[i].value ==""){
					alert(document.getElementsByName("indexKey")[i].value+"번째 담당자 전화번호를 입력해주세요.");
					document.getElementsByName("damdangPhone1")[i].focus();
					return false;
				}
				if(document.getElementsByName("damdangEmail")[i].value ==""){
					alert(document.getElementsByName("indexKey")[i].value+"번째 담당자  이메일을 입력해주세요.");
					document.getElementsByName("damdangEmail")[i].focus();
					return false;
				}
			}
			for(var i=0; i < damgdangArr2 ; i++){
				if(document.getElementsByName("damdangName")[i].value ==""){
					alert(document.getElementsByName("referIndex")[i].value+"번째 성함을 입력해주세요.");
					document.getElementsByName("damdangName")[i].focus();
					return false;
				}
				if(document.getElementsByName("damdangPhone1")[i].value =="" && document.getElementsByName("damdangPhone2")[i].value =="" && document.getElementsByName("damdangPhone1")[i].value ==""){
					alert(document.getElementsByName("referIndex")[i].value+"번째 전화번호를 입력해주세요.");
					document.getElementsByName("damdangPhone1")[i].focus();
					return false;
				}
				if(document.getElementsByName("damdangEmail")[i].value ==""){
					alert(document.getElementsByName("referIndex")[i].value+"번째 이메일을 입력해주세요.");
					document.getElementsByName("damdangEmail")[i].focus();
					return false;
				}
			}
		}else if(damgdangArr1 != 0 && damgdangArr2 != 0){
//			alert(document.getElementsByName("indexKey").length+"getElementsByName");
			alert("담당자를 입력해 주세요.");
			return false;
		}
		var itemArr1 = document.getElementsByName("itemIndexKey").length;
		var itemArr2 = document.getElementsByName("itemIndex").length;
		if(itemArr1 ==0 && itemArr2 ==0){
			alert("관련품목을 선택해 주세요.");
			return false;
		}
		var fileArr1 = document.getElementsByName("fileIndexKey").length;
		var fileArr2 = document.getElementsByName("fileIndex").length;
		if(fileArr1 ==0 && fileArr2 ==0){
			alert("관련 파일을 입력해 주세요.");
			return false;
		}
	}
	return true;
}



//코드 수정화면
function gubunUpdate(){
	document.frm.action = "kw_gubun_uf.jsp";
	document.frm.submit();
}

//코드 수정실행
function update(){
	document.frm.action = "kw_gubun_u.jsp";
	document.frm.submit();
}


// 코드 삭제
function gubunDelete(ckey){
	if (isNaN(ckey))
	{
		alert("올바른 키값이 아닙니다.");
		return false;
	}
	document.frm.action = "kw_gubun_d.jsp?sGubunKey="+ckey;
	document.frm.submit();
}

//목록으로
function cancle(){
	document.frm.action = "kw_company_lf.jsp";
	document.frm.submit(); 
}
//담당차 추가  비고가 없을때 
var referIndex = 0;
function damdangAdd(){
	var ulobj = document.getElementById("damdangUL");
	var liobj = document.createElement('LI');
	var idx = ulobj.childNodes.length;
	
    referIndex++;
	liobj.id = "refer_" + referIndex;
	liobj.style.display = "inline";
	liobj.style.padding = "0";
	ulobj.appendChild(liobj);
	
	
	var innerStr = "";
//	innerStr+="<input type='text' id='receiveName' name='receiveName' style='width:250px' value=''/>";
	innerStr+="<b>"+referIndex+".</b>";
	innerStr+="<input type='hidden' id='referIndex' name='referIndex' value='"+referIndex+"' />";
	innerStr+="<b> 이름 : </b><input type='text' id='damdangName' 		name='damdangName' 	style='width:60px;'	maxlength='95'	value='' />";
	innerStr+="<b> Tel : </b><input type='text' id='damdangPhone1' 	name='damdangPhone1' 	style='width:30px' maxlength='4'	value=''/>";
	innerStr+="-<input type='text' id='damdangPhone2' 	name='damdangPhone2' 	style='width:30px'	maxlength='4' value=''/>";
	innerStr+="-<input type='text' id='damdangPhone3' 	name='damdangPhone3' 	style='width:30px'	maxlength='4' value=''/>";
	innerStr+="<b> E-mail : </b><input type='email' id='damdangEmail' 	name='damdangEmail' 	style='width:130px'	maxlength='95'	value=''/>";
	innerStr+="<b> 비고 : </b><input type='text' id='damdangMemo' 		name='damdangMemo' 		style='width:50px'	maxlength='450'	value=''/>";
	innerStr+="<img src=\"/intranet/image/btn/btn_del_s2.gif\" style=\"padding:0 5px 0 5px; vertical-align:middle; cursor:pointer;\" onclick=\"referDel('refer_" + referIndex+"');\"> ";
	liobj.innerHTML = innerStr;	
	
} 


//수정에서 담당자 추가시 호출 함수
function damdangUfAdd(){
	var ulobj = document.getElementById("damdangUL");
	var liobj = document.createElement('LI');
	var idx = ulobj.childNodes.length;
	
    referIndex++;
	liobj.id = "refer_" + referIndex;
	liobj.style.display = "inline";
	liobj.style.padding = "0";
	ulobj.appendChild(liobj);
	
	
	var innerStr = "";
//	innerStr+="<input type='text' id='receiveName' name='receiveName' style='width:250px' value=''/>";
	innerStr+="<b>"+referIndex+".</b>";
	innerStr+="<input type='hidden' id='referIndex' name='referIndex' value='"+referIndex+"' />";
	innerStr+="<input type='hidden' id='damdangKey' 		name='damdangKey' value='0' />";
	innerStr+="<b> 이름 : </b><input type='text' id='damdangName' 		name='damdangName' 		style='width:60px'	maxlength='95'	value='' />";
	innerStr+="<b> Tel : </b><input type='text' id='damdangPhone1' 	name='damdangPhone1' 	style='width:30px' maxlength='4'	value=''/>";
	innerStr+="-<input type='text' id='damdangPhone2' 	name='damdangPhone2' 	style='width:30px'	maxlength='4' value=''/>";
	innerStr+="-<input type='text' id='damdangPhone3' 	name='damdangPhone3' 	style='width:30px'	maxlength='4' value=''/>";
	innerStr+="<b> E-mail : </b><input type='email' id='damdangEmail' 	name='damdangEmail' 	style='width:130px'	maxlength='95'	value=''/>";
	innerStr+="<b> 비고 : </b><input type='text' id='damdangMemo' 		name='damdangMemo' 		style='width:110px'	maxlength='450'	value=''/>";
	innerStr+="<img src=\"/intranet/image/btn/btn_del_s2.gif\" style=\"padding:0 5px 0 5px; vertical-align:middle; cursor:pointer;\" onclick=\"referDel('refer_" + referIndex+"');\"> ";
	liobj.innerHTML = innerStr;	
	
} 


function referDel(idx){
	document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));
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
		innerStr+="<input type='hidden' id='comItemKey' 		name='comItemKey' 		value='0'/>";
		innerStr+="<input type='hidden' id='itemKey' 		    name='itemKey' 			value='"+obj.sItemKey+"'/>";
		innerStr+="<input type='hidden' id='itemName' 			name='itemName' 		value='"+obj.sItemName+"'/>";
		innerStr+="<input type='hidden' id='itemCateName' 		name='itemCateName' 	value='"+obj.sItemCateName+"'/>";
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

//거래처  팝업
function comPop(){ 
		var sUrl = "popup/kw_company.jsp";
		var sName = "AddrAdd";
		if(!win||win.closed){
			win = window.open(sUrl,sName,"width=320, height=500, scrollbars=yes, resizeable=no, left=150, top=150");
			win.opener.self;
		}else{
			win.location = sUrl;
			win.focus();
		}
}