var root_num = 0;

function set_root(root)
{
	root_num = root;
}

$(document).ready(function(){
	$("#searchWord").focus();
});

function datepickerSetAnNot(obj){
	var list = obj.split(",");
	for(var i = 0; i < list.length; i++){
		var id = list[i].replace(/ /g, "");
		$("#"+id).datepicker(
			{
				changeMonth : true,
				changeYear : true,
				dateFormat : "yy-mm-dd",
				dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
				monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
				monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
			});
	}
}

function formatNum(obj) {
	var num = $("#"+obj.id+"").val();
	var pattern = /^(\d{1,10})([.]\d{0,3}?)?$/; // 정규식
	if ( !pattern.test(num) && num != '') {
		if ( num.indexOf('.') > 0) {	// 정수인지 아닌지 판별
			num = Number(num);
			num = Math.floor(num*1000)/1000;
			num = num.toFixed(3);
		} else {
			num = num.substring(0,10);
		}
		$("#"+obj.id+"").val(num);
	}
}

/**
 * 모든 키 누름 이벤트에 바인딩 (행추가 하거나 키 이벤트 정의된 태그에서 탭키 안먹으므로 엔터키로 넘어가는 공통함수 제작 )
 * 만일 아이디 정의되지 않은 인풋태그 등이 있다면 length 와 index 가 안맞으면서 처음으로 돌아오지 않는다. 
 * 
 * 현황에서 submit 처리 , disabled제외처리
 * **/  
/*
window.onkeydown = function(event) {
	//엔터키 일 경우 (탭키 -9-면 두번 실행된다.) 
	//console.log(' all key event.target.id = '+event.target.id);
	
	if(event.keyCode == '13') {
		var id = event.target.id ; //현재 코드의 id를 가져온다. (아이디는 중복없다고 가정) 
		var ck = '';//체킹용  
		var length  =$('input[type="text"]:not(:disabled) , select :not(:disabled)').length; //입력할 것들은 인풋박스와 셀렉트 박스로 이루어져 있다고 가정 
		$('input[type="text"]:not(:disabled) , select:not(:disabled)').each(function(index){
	      	//console.log('input select id list = '+$(this).attr('id') );
			if(event.target.id =="searchWord"){
				var formAction = document.forms.frm.action;
				if(formAction != ""){
					document.frm.submit();
				}
			}else{
				//직전에 엔터 입력 있었으면 다음 선택자에 포커스  
				if(ck == 'focusPoint'){
					$(this).focus();
				}
				//일치하면 다음 
				if(event.target.id == $(this).attr('id') ){
					ck = 'focusPoint';
					//console.log('event.target.id = '+event.target.id);
					//console.log('length = '+length+' index = '+index);
				}else{
					ck = '';
				}
				//마지막 포커스면 최초로 돌려준다.
				if(length == (index+1)){
					if(ck == 'focusPoint'){
						$('input[type="text"] , select')[0].focus();
					}
				}
			}
    	});
	}
};

*/



/*function sub_on(root, imgname)
{
	for (var i = 0; i < root_num; i++)
	{
		var obj = document.getElementById("sub" + i);
		var img = eval("document.all.top" + i);

		if (i == root)
		{
			obj.style.display = "block";
			if (imgname != "")
			{
    			img.background = "/image/top/" + imgname;
			}
		}
		else
		{
			obj.style.display = "none";
			img.background = "";
		}
	}
}


function sub_on3(root)
{
	for (var i = 0; i < root_num; i++)
	{
		var obj = document.getElementById("sub" + i);

		if (i == root)
		{
			obj.style.display = "block";
		}
		else
		{
			obj.style.display = "none";
		}
	}
}


function sel_menu(root, imgname)
{
	var obj = document.getElementById("sub" + root);
	var img = eval("document.all.top" + root);

	obj.style.display = "block";
	img.background = "/image/top/" + imgname;
}


function sel_menu3(root)
{
	var obj = document.getElementById("sub" + root);

	obj.style.display = "block";
}
*/

function win_open(url, title, top, left, width, height)
{
	var option = "scrollbars=no, top=" + top + ", left=" + left + ", ";
	option += "width=" + width + ", height=" + height;
	window.open(url, title, option);
}


function MM_openBrWindow(theURL,winName,features)
{
	window.open(theURL, winName, features);
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
		if(trim(returnValue) == "")	returnValue = 0;
		obj.value = cashReturn(returnValue); 
		return; 
	} 
	
	obj.focus(); 
	obj.value = returnValue; 
}

function trim(str) {		
	return str.replace(/(^\\s*)|(\\s*$)/g, "");		
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

function _CurrencyClear(s){
	var cntArray = new Array();

	for( var i = 0 ; i < s.length ; i++)
	{
		cntArray[i] = s.indexOf(",");
		s = s.replace(",","");

	}
	return s;
}


/**
 * 게스트 id 일 경우 등록 , 수정 , 삭제 불가능 
 * */
function intraGuestChk(id){
	if(id == 'guest'){
		alert("guest 는 조회만 가능합니다.");
		return true;
	} 
	return false;
}


/**
 * 메일 더블클릭 방지 
 * */
function openLoadingPage(){
    SLB("/js/loading/data_loading_01.jsp",'iframe',200,50,false,false);    
} 

function closeLoadingPage(){
    SLB();    
} 

/**
 * 게스트 id 일 경우 등록 , 수정 , 삭제 불가능 
 * */
function intraGuestChk(id){
	if(id == 'guest'){
		alert("guest 는 조회만 가능합니다.");
		return true;
	} 
	return false;
}


/**
 * 메일 더블클릭 방지 
 * */
function openLoadingPage(){
    SLB("/js/loading/data_loading_01.jsp",'iframe',200,50,false,false);    
} 

function closeLoadingPage(){
    SLB();    
} 

/**
 * 쉼표 공통 변환 모듈 ( 리스트 입력시 자바에서 연결문자로 , 를 사용하므로 입력값인 , 와 구분하기 위해 입력전 처리 )
 * */
function restCharChk(inputboxName){
	//널체크( '' || null || undefined || 0 || NaN ) 
	if(inputboxName ){
		var inputTagList = document.getElementsByName(inputboxName); 
		for (var i = 0; i < inputTagList.length; i++) { 
			inputTagList[i].value  = inputTagList[i].value.replace(/\,/gi,"इइइइइ");
		}
	}
}


/**
 * 파일 다운 함수 공통화 (중복코드 비효율 제거)
 * */
function fn_egov_downFile(atchFileId, fileSn){
	if(confirm("파일을 다운로드 하시겠습니까?")){
		window.open("/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn);
	}
}


/**
 * 파일 삭제 함수 공통화 (중복코드 비효율 제거)
 * */
function fn_egov_deleteFile(idx, atchFileId, fileSn) {
	if(confirm('첨부파일을 삭제하시겠습니까? ')){
		var params = 'atchFileId=' + atchFileId + '&fileSn=' + fileSn;
		var result = "";
		$.ajax({
			method: "post",
			url : "/cmm/fms/deleteFileInfs2.do",
			dataType : "json",
			data : params,
			success : function() {
				fileRowDel(idx);
			}
		});
	}
}

/**
 * 파일 행삭제 함수 공통화 (직 상위 노드 제거)
 * */
function fileRowDel(idx){
	document.getElementById(idx).parentNode.remove();
}


/**
 * 금액 한글화 함수 공통화 
 * */
function viewKoreanPrice(num) {
	var hanA = new Array("","일","이","삼","사","오","육","칠","팔","구","십"); 
	var danA = new Array("","십","백","천","","십","백","천","","십","백","천","","십","백","천"); 
	num = num.toString();
	var result = ""; 
	for(var i = 0; i < num.length; i++) {	
		str = ""; 
		han = hanA[num.charAt(num.length-(i+1))]; 
		if(han != "" && han != "undefined") str += han+danA[i]; 
		if(i == 4) str += "만"; 
		if(i == 8) str += "억"; 
		if(i == 12) str += "조"; 
		result = str + result; 
	} 
	if(num != 0) result = result + "원";
	
	return result ; 
}

/**
 * 날자선택 세팅 함수 공통화 
 * YYYY-MM-DD형태
 * */
function datepickerSet(topStartDate, topEndDate){
	$("#"+topStartDate).datepicker({
		monthNamesShort			: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']	// 월
		,dayNamesMin			: ['일', '월', '화', '수', '목', '금', '토']												// 일
		,weekHeader				: 'Wk'																				// 헤더
		,dateFormat				: 'yy-mm-dd'																		// 날짜 형식(20200610)
		,autoSize				: false																				// 오토사이즈(body등 상위태그의 설정에 따른다)
		,changeMonth			: true																				// 월변경가능
		,changeYear				: true																				// 년변경가능
		,showMonthAfterYear		: true 																				// 년 뒤에 월 표시-dd"
        ,yearRange				: 'c-50:c+10' 																		// 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가  (50년 전:10년 후)
		,onClose				: function(selectedDate){															// Calendar를 닫았을 때 함수
			$("#"+topEndDate).datepicker("option", "minDate", selectedDate);
		}
	})
	$("#"+topEndDate).datepicker({
		monthNamesShort			: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']	// 월
		,dayNamesMin			: ['일', '월', '화', '수', '목', '금', '토']												// 일
		,weekHeader				: 'Wk'																				// 헤더
		,dateFormat				: 'yy-mm-dd'																		// 날짜 형식(20200610)
		,autoSize				: false																				// 오토사이즈(body등 상위태그의 설정에 따른다)
		,changeMonth			: true																				// 월변경가능
		,changeYear				: true																				// 년변경가능
		,showMonthAfterYear		: true 																				// 년 뒤에 월 표시-dd"
        ,yearRange				: 'c-50:c+10' 																		// 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가  (50년 전:10년 후)
		,minDate				: $("#"+topStartDate).val()															// 최소 일자 (topStartDate부터 시작)
	})
}
function datepickerToInDate(toDate, topEndDate){
	$("#"+topEndDate).datepicker({
		monthNamesShort			: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']	// 월
		,dayNamesMin			: ['일', '월', '화', '수', '목', '금', '토']												// 일
		,weekHeader				: 'Wk'																				// 헤더
		,dateFormat				: 'yy-mm-dd'																		// 날짜 형식(20200610)
		,autoSize				: false																				// 오토사이즈(body등 상위태그의 설정에 따른다)
		,changeMonth			: true																				// 월변경가능
		,changeYear				: true																				// 년변경가능
		,showMonthAfterYear		: true 																				// 년 뒤에 월 표시-dd"
        ,yearRange				: 'c-50:c+10' 																		// 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가  (50년 전:10년 후)
		,minDate				: toDate															// 최소 일자 (topStartDate부터 시작)
	})
}
/**
 * 날자선택 세팅 함수 공통화 (id기준) 
 * YYYY-MM-DD형태
 * */
function datepickerIdSet(id){
	$("#"+id).datepicker(
		{
			changeMonth : true,
			changeYear	: true,
			showMonthAfterYear : true,
			dateFormat : "yy-mm-dd",
			dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
		});
	
	$("#"+id).val();
}

/**
 * 지정날자이후제한 세팅 함수 공통화 
 * YYYY-MM-DD 형태
 * */
function datepickerMinDateSet(id , date){
	$('#'+id).datepicker(
		{
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
			minDate : date 
		});
	$('#'+id).val(getToday());
}

/**
 * 날자당일세팅 함수 공통화 
 * YYYY-MM-DD형태
 * */
function datepickerTodaySet(id){
	$('#'+id).datepicker(
		{
			changeMonth : true,
			dateFormat : "yy-mm-dd",
			dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
		});
	$('#'+id).val(getToday());
}

/**
 * 날자세팅 함수 공통화 
 * name 기준 
 * 연도 변경, 월 변경 앞에 연도 변경 셀렉트박스 추가  
 * */
function datepickerNameSet(name){
    var pList = $("input[name="+name+"]");
    for(var i = 0 ; i < pList.length ; i++ ){
    	$("input[name="+name+"]").eq(i).datepicker(
		{
			changeMonth : true,
			changeYear	: true,
			showMonthAfterYear : true,
			dateFormat : "yy-mm-dd",
			dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
		});
    }     
}




/**
 * 당일날자 YYYY-MM-DD형태 획득 
 * */
function getToday(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
    return nowDate;
}

/**
 * 3 자리마다 콤마 세팅  
 * */
function commaSetting(x) {
	var obj; 
    var strArr = (x+"").split(".");//문자형변환
    if (strArr.length > 1 ) { 
        obj = strArr[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "." + strArr[1];
    } else { //정수만 있을경우 //소수점 문자열 존재하면 양수 반환 (금액이니까)
        obj = strArr[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    return obj;
}

/**
 * 금액용 콤마 없애기 (소수점 남긴다.)
 * */
function commaRemove(str) {
    str = String(str);
    return str.replace(/,/gi, '');
}

/**
 * 금액용 콤마 전체 없애기 
 * 리스트 name 받는다.
 * */
function commaRemoveAll(listName) {
    var pList = $("input[name="+listName+"]"); 
    for(var i = 0 ; i < pList.length ; i++ ){ 
    	pList[i].value =  Number(commaRemove(pList[i].value));
    } 
}


/**
 * 금액 리스트 합계 한글로 반환 
 * 리스트 name , 입력 span id 받는다. 
 * */
function koreanPriceSet(listName , spanId) {
    var sumPrice = 0 ; 
    var pList = $("input[name="+listName+"]"); 
    for(var i = 0 ; i < pList.length ; i++ ){ 
    	sumPrice += Number(commaRemove(pList[i].value));
    }
    $("#"+spanId).html(viewKoreanPrice(sumPrice));
}
 

/**
 * 금액 리스트 합계 숫자로 반환 
 * 리스트 name 받는다. 
 * */
function priceAmount(listName) {
    var sumPrice = 0 ; 
    var pList = $("input[name="+listName+"]"); 
    for(var i = 0 ; i < pList.length ; i++ ){ 
    	sumPrice += Number(commaRemove(pList[i].value));
    }
    return sumPrice;
}


/**
 * 금액 형태로 콤마 붙인다. 
 * 리스트 name 받는다. 
 * */
function priceFormatSet(listName) {
    var pList = $("input[name="+listName+"]"); 
    for(var i = 0 ; i < pList.length ; i++ ){
    	pList[i].value = commaSetting(pList[i].value);
    }
}

/**
 * Date()로 .format 함수 사용
 * ex) 	var date = new Date();
 * 		date.format("yyyy-MM-dd"); -> 2021-02-25
 **/
/** 문자나 숫자의 길이가 매개변수인 len 값보다 작을 경우 앞에 '0' 을 붙힌다. **/
String.prototype.string = function (len) { var s = '', i = 0; while (i++ < len) { s += this; } return s; };
String.prototype.zf = function (len) { return "0".string(len - this.length) + this; };
Number.prototype.zf = function (len) { return this.toString().zf(len); };

/** 매개변수로 날짜 포맷을 전달 받아 해당 포맷 형태로 문자열을 반환 한다. **/
Date.prototype.format = function (f) {
    if (!this.valueOf()) return " ";

    var weekKorName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var weekKorShortName = ["일", "월", "화", "수", "목", "금", "토"];
    var weekEngName = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    var weekEngShortName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    var d = this;

    return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi, function ($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear(); 						// 년 (4자리)
            case "yy": return (d.getFullYear() % 1000).zf(2); 			// 년 (2자리)
            case "MM": return (d.getMonth() + 1).zf(2); 				// 월 (2자리)
            case "dd": return d.getDate().zf(2); 						// 일 (2자리)
            case "KS": return weekKorShortName[d.getDay()]; 			// 요일 (짧은 한글)
            case "KL": return weekKorName[d.getDay()]; 					// 요일 (긴 한글)
            case "ES": return weekEngShortName[d.getDay()]; 			// 요일 (짧은 영어)
            case "EL": return weekEngName[d.getDay()]; 					// 요일 (긴 영어)
            case "HH": return d.getHours().zf(2); 						// 시간 (24시간 기준, 2자리)
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간 (12시간 기준, 2자리)
            case "ss": return d.getSeconds().zf(2); 					// 초 (2자리)
            case "a/p": return d.getHours() < 12 ? "오전" : "오후"; 		// 오전/오후 구분
            default: return $1;
        }
	});
};