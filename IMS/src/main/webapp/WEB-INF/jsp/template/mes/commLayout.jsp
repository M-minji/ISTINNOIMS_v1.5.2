<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>통합유지관리시스템</title>

<link href="/css/mes/common.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mes_board.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mes_main.css" rel="stylesheet"	type="text/css" />

<script src="/js/gridjs/gridjs.js"></script>

<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />

<script type="text/javascript" src="/js/kw_common.js"></script>
<script  type="text/javascript" src="/js/main/script.js"></script>

<style>
#mloader{
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 99900;
  background-color: #3f3f3f;
  opacity: 0.7;
  display: block;
}

#mloader .masterkey_blink {
	font-size:20px;
	font-weight:bold;
	margin: 10% 45%;
    -webkit-animation: masterkey_blink 3s linear infinite;
} 
@-webkit-keyframes masterkey_blink {
    15% { color: white; }
/*     40% { color: black; }
    75% { color: white; } */
}

#loading-image {   
 position: absolute;
 top: 40%; 
 left: 40%;
 z-index: 100; } 
 
 
.modal{
	 max-width: 80%;
	 max-height: 300px;
}
.scrolltbody  >table{
	width: 100%;
}
.scrolltbody{
    max-height: 450px; 
    overflow-y: scroll;
}
.scrolltbody::-webkit-scrollbar {
    display: none;
}
.blocker {
	/*background-color: transparent;*/
}

</style>

<script type="text/javascript">

//소수점이 여러 개 있을 경우, 맨 앞에 것을 제외하고 모두 제거하는 함수
function removeMultipleDotsA(x) {
    x = x.toString();
    var dotCount = 0;
    var result = "";
    for (var i = 0; i < x.length; i++) {
        if (x[i] === ".") {
            // 소수점이 처음 나오는 경우만 결과값에 포함
            if (dotCount === 0) {
                result += ".";
            }
            dotCount++;
        } else {
            result += x[i];
        }
    }
    return result;
}

// 천 단위 구분 기호(,)를 추가하는 함수
/*
function commaA(x) {
    x = x.toString();
    // removeMultipleDots 함수를 사용하여 여러 개의 소수점이 있을 경우 하나만 남기고 제거
    x = removeMultipleDotsA(x);
    // 입력값이 "."일 경우 "0."으로 변환
    if(x == "."){
        x = "0.";
    }
    if (x != "0" && x.slice(-1) != ".") {
        // 입력값에서 천 단위 구분 기호(,)를 제거하는 함수 호출
        uncommaA(x);
        const filterInput = (str) => {
       		return str.replace(/[^-\d.]/g, '') // 숫자, 소수점, 음수 부호를 제외한 문자 제거
       	            .replace(/^(-?\d*\.?\d{0,4}).*$/, '$1'); // 소수점 아래 4자리까지 허용하는 정규식을 이용하여 잘못된 문자 제거
       	};
        return filterInput(x);
    }
    return x;
}
*/



function editDeleteCheck(gubun , key){
	var numChk = 0;
	$.ajax({
		type : "post",
		url : "/mes/editDeleteCheck.do",
		data : {"gubunTxt": gubun,
        "gubunKey": key},
		dataType : "json",
		async : false,
		cache : false,
		success : function(msg){
			var chk = msg.result.num;
			numChk = chk;
		}
	});
	if(gubun == "MC" && numChk == 1){
		alert("재고 반영되어 수정 삭제가 불가능합니다.");
		return false;
	}else{
		return true;
	}
}


//천 단위 구분 기호(,)를 제거하는 함수
function uncomma(x) {
 var parts = x.toString().split("."); 
 return parts[0].replace(/[^\d]+/g, '') + (parts[1] ? "." + parts[1] : ""); 
}

function setComma(id){	
	$("#"+id).val(comma($("#"+id).val()));
}


function comma(value) {
	/*
	var returnVale = "";
	if(x != 0){
		var tmp = String(x).split(".");
		if(tmp.length == 1){
			returnVale = tmp[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");			
		}else{
			returnVale = tmp[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") +"."+ tmp[1];	
		}
	}else{
		returnVale = "0";
	}
    return returnVale;
    */
	// 문자열인 경우 숫자 이외의 문자를 모두 제거한 후 숫자로 변환
 	if (typeof value === 'string') {
 		const numericValue = parseFloat(value.replace(/[^0-9.-]+/g, ''));
	 	// NaN인 경우 빈 문자열 반환
	 	if (isNaN(numericValue)) {
	 		return '';
	 	}
 		value = numericValue;
 	}

 	// 숫자가 아닌 경우 빈 문자열 반환
 	/*
 	if (isNaN(value) || typeof value !== 'number') {
 		return '';
 	}
 	*/

 	// 숫자를 정수로 변환
 	const integer = Math.floor(value);

 	// 정수부분에 쉼표 추가
 	const numberWithCommas = integer.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

 	return numberWithCommas;
}

/*
function uncomma(str) {
    str = String(str);
    return str.replace(/(,)/g, '');
}
*/

function isNullChk(obj){
	if(obj == ""){
		return 0;
	}else{
		return obj;
	}
}

function dotchk(id){
	var str = $('#'+id).val();
	$('#'+id).val(comma(str.replace(/[^0-9\d.]/g,'')));
}

function numchk(id){
	var str = $('#'+id).val();
	$('#'+id).val(comma(str.replace(/[^0-9]/g,'')));
}


function trimChk(obj){
	var str = String(obj);
	if(str == ""){
		return str;
	}else{
		return str.replace(/\s/gi, "");	
	}
}

//자리수 0으로 채우기, n:숫자, width:글자수
//ex)pad(10,4) => 0010
function pad(n, width) {
  n = n + '';
  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
} 

$(function(){     
	$('#mloader').hide();   
}); 
 

// 12-06 임석현 데이트포맷 추가
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

Date.prototype.getYearYY = function(){
	 var a = this.getYear();
	 return a >= 100 ? a-100 : a;
	}
	
	
//왼쪽에서부터 채운다는 의미
function LPAD(s, c, n) {    
    if (! s || ! c || s.length >= n) {
        return s;
    }
 
    var max = (n - s.length)/c.length;
    for (var i = 0; i < max; i++) {
        s = c + s;
    }
 
    return s;
}
 
// 오른쪽에서부터 채운다는 의미
function RPAD(s, c, n) {  
    if (! s || ! c || s.length >= n) {
        return s;
    }
 
    var max = (n - s.length)/c.length;
    for (var i = 0; i < max; i++) {
        s += c;
    }
 
    return s;
}

// 1주일,1개월,3개월 세팅
function setStartDate(d) {
	
    var settingDate = new Date();
    if(d == '7'){
        settingDate.setDate(settingDate.getDate()-7);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else if(d == '1'){
        settingDate.setMonth(settingDate.getMonth()-1);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }else{
        settingDate.setMonth(settingDate.getMonth()-3);
    	$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
    }
   
    fn_guestList(1);
	
}

//파일 뷰팝업 이미지 사이즈만큼
function fn_egov_viewFile(url){
	var img=new Image();
	img.src=url;
	img.onload = function (){
		var img_width=img.width;
		var win_width=img.width+25;
		var img_height=img.height;
		var win=img.height+30;
		var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
		OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
	}
}

//파일 뷰팝업 이미지 사이즈만큼
function fn_view_pdf(url){
	var OpenWindow=window.open('','_blank', 'menubars=no, scrollbars=auto');
	OpenWindow.document.write("<style>body{margin:0px;}</style><iframe width='100%' height='100%' src='"+url+"'></iframe>");
}


// 한글 입력방지 
// onkeyup='return delHangle(event);' <- 이렇게 사용
function delHangle(evt){ 
	var objTarget = evt.srcElement || evt.target;
	var _value = event.srcElement.value;
	if(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g.test(_value)){
		objTarget.value = null;
	}
}

// 영문 입력방지, 소수점 2개 입력방지
// onkeypress='return isNumberKey(event);' <- 이렇게 사용
function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;

    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

    // Textbox value    
    var _value = event.srcElement.value;    

    // 소수점(.)이 두번 이상 나오지 못하게
    var _pattern0 = /^\d*[.]\d*$/; // 현재 value값에 소수점(.) 이 있으면 . 입력불가
    if (_pattern0.test(_value)) {
        if (charCode == 46) {
            return false;
        }
    }

    // 소수점 둘째자리까지만 입력가능
    var _pattern2 = /^\d*[.]\d{4}$/; // 현재 value값이 소수점 둘째짜리 숫자이면 더이상 입력 불가
    if (_pattern2.test(_value)) {
        alert("소수점 4자리까지만 입력가능합니다.");
        return false;
    }  

    return true;
}


/**
 * 파일 확장자 및 용량 체크
 * @param file
 * @param ext(제어하고 싶은 확장자)
 * @returns
 */
function checkFileExt(obj, ext) { 

	var check = false; 
	var extName = obj.val().substring(obj.val().lastIndexOf(".") + 1).toLowerCase(); 
	
	for ( var i = 0; i < ext.length; i++ ) { 

		if (extName == ext[i].trim()) { 
			check = true; 
			break; 
		} else check = false; 
	} 
	
	if ( !check ) { 
		alert(ext + " 파일만 업로드 가능합니다."); 
	} 
	
	return check; 
}

/**
 * 파일 용량 체크
 * @param obj
 * @param size(제어하고 싶은 사이즈)
 * @returns
 */
function checkFileSize(obj, size) { 
	
	var check = false; 
	var sizeinbytes = obj[0].files[0].size; 
	var fSExt = new Array('Bytes', 'KB', 'MB', 'GB'); 
	var i = 0; 
	var checkSize = size; 
	
	while (checkSize > 900) { 
		checkSize /= 1024; 
		i++; 
	} 
	
	checkSize = (Math.round(checkSize * 100) / 100) + ' ' + fSExt[i]; 
	var fSize = sizeinbytes; 
	
	if (fSize > size) { 
		alert("첨부파일은 " + checkSize + " 이하로 첨부 바랍니다."); 
		check = false; 
	} else { 
		check = true; 
	} 
	return check; 
}

/**
* 특수문자 치환
* @param str
* @returns
*/
function ConverttoHtml(str){
	str = $("<div>").text(str).html();  // HTML 특수 문자를 변환
	str = str.replace(/</g,"&lt;");
	str = str.replace(/>/g,"&gt;");
	str = str.replace(/\"/g,"&quot;");
	str = str.replace(/\'/g,"&#39;");
	str = str.replace(/\n/g,"<br />");
	str = str.replace(/\,/g,"&#44;");
	return str;
}

/**
* 특수문자 jsp 표현
* @param str
* @returns
*/
function HtmltoConvert(str){
	str = str.replace("&lt;","<");
	str = str.replace("&gt;",">");
	str = str.replace("&quot;","\"");
	str = str.replace("&#39;","'");
	str = str.replace("<br />","\n");
	str = str.replace("&#44;",",");
	return str;
}

function fn_search_detail(){
	var viewGubun = $("#viewGubun");
    // viewGubun 요소가 존재하는 경우
    if(viewGubun.length > 0){
        // viewGubun의 값이 빈 값인 경우
            if($("#search_detail").is(":visible")){
                viewGubun.val("N");
            } else {
                viewGubun.val("Y");
            }
    }
    var viewDetail = $("#viewDetail");
    viewDetail.val(viewDetail.val() === "Y" ? "N" : "Y");

    // 기본 동작 유지
    if($("#search_detail").is(":visible")){
        $('#search_detail').attr("style", "display: none;");
    } else {
        $('#search_detail').attr("style", "display: flex;");
    }
}
function viewGubun() {
    var viewGubunElement = $("#viewGubun");
    // viewGubun 요소가 존재하고 값이 "Y"인 경우
    if (viewGubunElement.length > 0 && viewGubunElement.val() === "Y") {
        $('#search_detail').attr("style", "display: flex;");
    } else {
        $('#search_detail').attr("style", "display: none;");
    }
}

</script>
</head>
<body>
<div id="mloader"></div>
	<div class="wrap">
		<div class="header">
			<t:insertAttribute name="header" />
		</div>
		<div id="sub_container">
<!-- 			  <div id="left"> -->
<%-- 				<t:insertAttribute name="leftMenu" /> --%>
<!-- 			</div>  -->
<!-- 			<div class="path"> -->
<!-- 				<div class="path_div" > -->
<%-- 							<t:insertAttribute name="menuTitle" /> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			<div id="content">
				<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"/>
				<t:insertAttribute name="content" />
			</div>
		</div>
	</div>
</body>
</html>


