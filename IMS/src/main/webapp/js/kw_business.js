/**
 * 이전 개발 소스  
 * 업무일지용 js
 * /intranet/business/kw_business_if.jsp
 * /intranet/business/kw_business_rf.jsp
 * /intranet/business/kw_business_svf.jsp
 * /intranet/business/kw_business_uf.jsp
 * /intranet/business/kw_business_vf.jsp
 * 에서 사용
 * */
function check()
{
	with (document.writeform)
	{
		var objTmp = true;
		var obj = document.getElementById("receiveUL");
		if(obj != null){
			var items = obj.getElementsByTagName("input");		
			if( items != null && items.length> 0){
				 objTmp = false;
			}
		}
		
		if (objTmp)
		{
			alert("받는사람을 선택하세요.");
			return false;
		}

		if (subject.value == "")
		{
			alert("제목을 입력하세요.");
			subject.focus();
			return false;
		}

		var oEditor = FCKeditorAPI.GetInstance('content');
		if (oEditor.GetXHTML(true) == '') {
		    alert('내용을 입력하세요');
		    oEditor.Focus();
		    return false;
		}

		return true;
	}
}


function reset()
{
	with (document.writeform)
	{
		reset();
		subject.focus();
	}
}


function checkDel()
{
	var msg = "정말로 삭제하시겠습니까?";
	return confirm(msg);
}

function dayAttention(txt){
    var oEditor = FCKeditorAPI.GetInstance('content');        
    if(txt == "D"){
    	oEditor.SetHTML( document.getElementById("WordSection1").value ) ;    	
    }else if(txt == "W"){
    	oEditor.SetHTML( document.getElementById("WordSection2").value ) ;  
    }else if(txt == "M"){
    	oEditor.SetHTML( document.getElementById("WordSection3").value ) ;  
    }   
    
}

function winPop(busiKey){
	var width = 900;
    var height = 600;
    var ediWindow =  
	window.open("kw_business_print.jsp?busiKey="+busiKey,"업무일지","toolbar=0,menubar=0,location=0,directories=0,status=1,scrollbars=1,resizable=0,width="+width+",height="+height+", top=0, left=0");
    ediWindow.focus();								
}

function winPr(){
	window.print();							
}



var referIndex = 0;
function referadd(){
	
    window.open('kw_business_staff_lf.jsp', 'refer', 'width=450,height=400px,scrollbars=yes');   
    
}


function refersetVal(staffkey, staffval){
	var ulobj = document.getElementById("receiveUL");
	var liobj = document.createElement('LI');
	
	var idx = ulobj.childNodes.length;
    referIndex++;
    
	liobj.id = "refer_" + referIndex;
	liobj.style.display = "inline";
	liobj.style.padding = "0";
	ulobj.appendChild(liobj);

	var innerStr = "";
	innerStr+="<input type='text' id='receiveName' name='receiveName' value='"+staffval+"'/>";
	innerStr+="<input type='hidden' id='receiveKey' name='receiveKey' value='"+staffkey+"'/>";
	innerStr+="<img src=\"/intranet/image/btn/btn_del_s2.gif\" style=\"padding:0 5px 0 5px; vertical-align:middle; cursor:pointer;\" onclick=\"referDel('refer_" + referIndex+"');\"> ";
	liobj.innerHTML = innerStr;	
}


function referDupleChk(staffkey){
	var obj = document.getElementById("receiveUL");
	if(obj == null) return 'N';
	
	var items = obj.getElementsByTagName("input");
	
	if( items != null && items.length> 0){
         for (var i = 0 ; i < items.length ; i++){
             if(staffkey == items(i).value){
            	 return 'Y';
             }
         }
     }

	return 'N';
}



function referDel(idx){
	document.getElementById(idx).parentNode.removeChild(document.getElementById(idx));
}
