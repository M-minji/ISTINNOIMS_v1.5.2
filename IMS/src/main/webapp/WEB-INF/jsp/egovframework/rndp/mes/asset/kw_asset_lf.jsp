<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<!-- 그리드 S -->
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/gridjs.production.min.js"></script>

<script type="text/javascript" src="/js/cdnjs_cloudflare_com_ajax_libs_jqueryui_1.12.1/jquery-ui.min.js"></script>
<link href="/css/mes/jquery-ui.min.css" rel="stylesheet"	type="text/css" />
<script src="/js/xlsx.full.min.js"></script>
<script src="/js/papaparse.min.js"></script>
<script type="text/javascript">
function fn_guestList(pageNo) {
	$('#mloader').show();
	document.listForm.pageIndex.value = pageNo;
	if ($("#viewDetail").val() === "Y") {
		document.listForm.viewDetail.value = "Y";
    }
	document.listForm.action = "/mes/asset/kw_asset_lf.do";
	document.listForm.submit();
}

viewService.prototype.go_view = function(obj) {
	if(obj.childNodes[0].querySelector("input")){  
		$("#mloader").show();
		$("#eAssetKey").val(obj.childNodes[0].querySelector("input").value);
		document.listForm.action = "/mes/asset/kw_asset_vf.do";
		document.listForm.submit();
	}
}

function go_insert(){
	$("#mloader").show();
	document.listForm.action = "/mes/asset/kw_asset_if.do";
	document.listForm.submit();
}

function fn_keyDown(event){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}


function formDownload(){
    document.listForm.action = "/mes/asset/kw_assetExcelForm.do";
    document.listForm.submit();
    document.listForm.action = "/mes/asset/kw_asset_lf.do";
}

//날짜 처리 함수.
let dateCheckCount = 0;
var regex = /^\d{4}-\d{2}-\d{2}$/;

function checkDateFormat(dateString) {
	
		if(dateString == null){  // 빈값일땐 빈값 저장
			return true;
		}
	 
	 if (dateString === undefined || String(dateString).trim() === "") {
	        dateCheckCount++;
	        return false;
	    }

	    var dateVal = String(dateString).trim();

	    // 형식이 'yyyy-mm-dd'가 아닌 경우
	    if (!dateVal.match(regex)) {
	        dateCheckCount++;
	        return false;
	    }

	    var date = new Date(dateVal);

	    // 날짜가 유효하지 않은 경우
	    if (isNaN(date.getTime())) {
	        dateCheckCount++;
	        return false;
	    }
 
}

let checkCount = 0;
let specialCharCount = 0;
//텍스트 길이 체크 함수
function checkTextLength(text, maxLength) {
	if(text == null || text == ""){
		return;
	}
	if (text === undefined || String(text).trim() === "" ){
		specialCharCount++;
		return;
	}
 // 텍스트 길이가 최대 길이를 초과하는지 확인
 if (text && typeof text === 'string' && text.length > maxLength) {
	 checkCount++;  // 함수가 호출될 때마다 카운트 증가
 } 
//  else {
//      console.log(`텍스트 길이는 정상입니다. 현재 길이: ${text.length}`);
//  }
 const invalidChars = ['<', '>', '"', "'", '&'];
 let specialCharStatus = false; // 특수문자 포함 여부
 // 특수문자 체크
 var specialCheck = false;
 invalidChars.forEach(char => {
     if (text.includes(char)) {
         //specialCharStatus = true;  // 특수문자 포함 시 true로 설정
    	 specialCheck = true;
     }
 });
 if(specialCheck) {
	 specialCharCount++;
 }
 
}



const assetTypeChars = [];  
const assetStatusChars = [];
let assetStatusFirst = "";

let assetTypeCount = 0;
let assetStatusCount = 0;

function checkAssetType(assetType) {//자산유형 확인
	 if (!assetTypeChars.includes(String(assetType).trim())) {  // 배열에 없을 경우
	        assetTypeCount++;
	    }
}

function checkAssetStatus(assetStatus) {//자산상태 확인
	 if (!assetStatusChars.includes(String(assetStatus).trim())) {  // 배열에 없을 경우
        assetStatusCount++;
    }
}

let requiredFieldsCount = 0;  // 필수값 체크 카운트

function checkRequiredFields(field1, field2, field3, field4, field5, field6) {
    // 각 필드를 trim()하여 공백을 제거한 후 빈값 또는 undefined/null인 경우 카운트 증가
   if (	  field1 == null || field1 === undefined || String(field1).trim() === "" ||   // null 또는 undefined, 빈값 처리
	      field2 == null || field2 === undefined || String(field2).trim() === "" ||
	      field3 == null || field3 === undefined || String(field3).trim() === "" ||
	      field4 == null || field4 === undefined || String(field4).trim() === "" ||
	      field5 == null || field5 === undefined || String(field5).trim() === "" ||
	      field6 == null || field6 === undefined || String(field6).trim() === ""
        ) {
        requiredFieldsCount++;  // 필수값 하나라도 없으면 카운트 증가    -> 이렇게 해버리니까 몇개가 잘못돼도 카운트가 1임 -> 필수 누락은 카운트 뺌
    }
}

let alertMessage = "";  // 경고 메시지 전역 변수
function checkAllConditions() {
	  alertMessage = "";  // 경고 메시지 초기화
	  if (assetTypeCount > 0) {
	        alertMessage += "자산유형 오류!   "+assetTypeCount+"건\n";
	    }

	    if (assetStatusCount > 0) {
	        alertMessage += "자산상태 오류!  "+assetStatusCount+"건\n";
	    }

	    if (specialCharCount > 0) {
	    	alertMessage += '특수문자 (<, >, ", \', &) 포함 셀! ' + specialCharCount + '건\n';
	    }

	    if (checkCount > 0) {
	        alertMessage += "텍스트 길이 80자 초과!  "+checkCount+"건\n";
	    }

	    if (dateCheckCount > 0) {
	        alertMessage += "날짜 형식(YYYY-MM-DD) 오류!  "+dateCheckCount+"건\n";
	    }
	  
	    if (requiredFieldsCount > 0) {
	        alertMessage += "필수 입력 항목 누락!  " ; // +requiredFieldsCount+"건\n";    // 필수 누락은 카운트 뺌
	    }
	    if(ANexcelCheckNum > 0) {
	    		alertMessage = "엑셀 파일에서 중복되는 자산번호를 발견하였습니다."
				 for(let msg of ANDuplicatearr){
					 alertMessage += msg;
				 }
	    		alertMessage += "\n";
		 }
	    if(SNexcelCheckNum > 0) {
	    	alertMessage += "\n";
			 alertMessage += "엑셀 파일에서 중복되는 제조번호를 발견하였습니다."
			 for(let msg of SNDuplicatearr){
				 alertMessage += msg;
			 }
		 }
	    if(duplicateValueAN > 0) {
			 alertMessage = "등록된 자산과 중복되는 자산번호를 발견하였습니다.\n" + duplicateeAssetNumber;
		 }
	    if(duplicateValueSN > 0) {
	    	alertMessage += "\n등록된 자산과 중복되는 제조번호를 발견하였습니다.\n" + duplicateeAssetSNumber;
		 }
	if (assetTypeCount === 0 && assetStatusCount === 0 && specialCharCount === 0 &&
	        checkCount === 0 && dateCheckCount === 0 && requiredFieldsCount === 0 && duplicateValueSN == 0 && duplicateValueAN == 0 &&
	        ANexcelCheckNum == 0 && SNexcelCheckNum == 0) {
	        return false;  // 모든 카운트가 0일 때 false  반환
	    }else{
	    	// 초기화
	    	assetTypeCount = 0;
	    	assetStatusCount=0;
	    	specialCharCount=0;
	    	checkCount=0;
	    	dateCheckCount=0;
	    	requiredFieldsCount=0;
	    	SNarr = [];
	    	ANarr = [];
	    	SNarrIdx = [];
	    	ANarrIdx = [];
	    	tmpSN = 2;
	    	tmpAN = 2;
	    	SNDuplicatearr = [];
	    	ANDuplicatearr = [];
	    	SNexcelCheckNum = 0;
	    	ANexcelCheckNum = 0;
	    	duplicateValueSN=0;
	    	duplicateValueAN=0;
	    	duplicateeAssetSNumber = "";
			duplicateeAssetNumber = "";
	    }
    return true;  // 그 외에는 true 반환
}
 
 
function processInput(input,maxDigits) {
    // 입력값이 undefined 또는 null일 경우 0 반환
    if (input == null || input === undefined || String(input).trim() === "" ) {
        return 0;
    }
    
    // 입력값에서 숫자만 추출 (정수 또는 실수)
    const number = parseInt(input.toString().replace(/[^0-9]/g, ''), 10);
    // 숫자 자릿수 체크 (양수 정수만)
    const numberOfDigits = number.toString().length; // 자릿수 계산
    if (numberOfDigits > maxDigits) {
        return 0;
    }
    return number >= 0 ? number : 0;
}

// 엑셀 업로드 시 엑셀 내 중복 체크
let SNarr = [];	// 최초로 나온 제조번호의 엑셀 행을 담을 배열
let ANarr = [];
let SNarrIdx = []; // 최초로 나온 제조번호의 엑셀 행을 담을 배열
let ANarrIdx = [];
var tmpSN = 2;		// 반복문을 돌 때 플러스 시켜 중복이 발생한 행을 알게 함
var tmpAN = 2;
let SNDuplicatearr = [];	// 중복이 발생한 행을 담을 배열
let ANDuplicatearr = [];
let SNexcelCheckNum = 0;	// 중복 여부를 판단하기 위함
let ANexcelCheckNum = 0;
function SNexcelCheck(sn, index){
	var value = sn.trim();
	if(SNarr.includes(value)) {
		SNexcelCheckNum++;
		var indexNum = SNDuplicatearr.findIndex(str => str.includes("'" + value + "'  "));
		if(indexNum < 0) {	// 중복이 처음 발견됨
			SNDuplicatearr.push("\n'" + value + "'  " + SNarrIdx[SNarr.indexOf(value)] + "행, " + (index+1) + "행");
		} else {	// 중복이 또 발견됨 (3개이상)
			SNDuplicatearr[indexNum] += (", " + (index+1) + "행");  
		}
	} else {
		SNarr.push(value);
		SNarrIdx.push(tmpSN)
	}
	tmpSN++;
}
function ANexcelCheck(an, index){
	var value = an.trim();
	if(ANarr.includes(value)) {
		ANexcelCheckNum++;
		var indexNum = ANDuplicatearr.findIndex(str => str.includes("'" + value + "'  "));
		if(indexNum < 0) {	// 중복이 처음 발견됨
			ANDuplicatearr.push("\n'" + value + "'  " + ANarrIdx[ANarr.indexOf(value)] + "행, " + (index+1) + "행");
		} else {	// 중복이 또 발견됨 (3개이상)
			ANDuplicatearr[indexNum] += (", " + (index+1) + "행");  
		}
	} else {
		ANarr.push(value);
		ANarrIdx.push(tmpAN)
	}
	tmpAN++;
}

// 등록 된 자산과 중복 비교
let duplicateValueSN = 0;
var duplicateeAssetSNumber = "";
let duplicateValueAN = 0;
var duplicateeAssetNumber = "";
function eAssetSNumberCheck(ogj, idx){
	
    var value = ogj;
    
        $.ajax({
			type		: "post"
		,	dataType	: "json"
		,	url			: "/mes/asset/kw_asset_sNumber_check.do"
		,	data		: {
			eAssetSNumber : value
			}
		, async: false
		,	success		: function(msg){
			var dataInfo  = msg.result.dataAdd;
			 if (dataInfo > 0) {
                // 데이터가 있는 경우 -> 중복 제조번호
                duplicateeAssetSNumber += (idx + "행. " + ogj + "\n");
                duplicateValueSN++;
                return false;
            }
			}
		});

}
function eAssetNumberCheck(obb, idx){
    var ttvalue = obb;
    
        $.ajax({
			type		: "post"
		,	dataType	: "json"
		,	url			: "/mes/asset/kw_eAssetNumberCheck.do"
		,	data		: {
			eAssetSNumber : ttvalue
			}
		, async: false
		,	success		: function(msg){
			var dataInfo  = msg.result.dataAdd;
			 if (dataInfo > 0) {
                // 데이터가 있는 경우 -> 중복 자산번호
				 duplicateeAssetNumber += (idx + "행. " + obb + "\n");
	                duplicateValueAN++;
	                return false;
            	}
			}
		});

}

function readExcel(e) {
    var input = event.target;
    var reader = new FileReader();
	var cnt = 0;
    reader.onload = function () {
        var data = reader.result;
        var workBook = XLSX.read(data, { type: 'binary' , cellDates: true, dateNF: 'yyyy-mm-dd' });
        workBook.SheetNames.forEach(function (sheetName) {
            var arr = XLSX.utils.sheet_to_json(workBook.Sheets[sheetName], {header:1, raw:false});
            var val1 = true;
            var val2 = true;
            var val3 = true;
            // 필수값 확인
            for(var i = 1 ; i < arr.length; i++){  
        		//필수값 확인
        		checkRequiredFields(arr[i][0],arr[i][1],arr[i][2],arr[i][3],arr[i][4],arr[i][5]);
        		if(requiredFieldsCount != 0) {	// 필수값이 비어있으면 나감
        			val1 = false;
        			break;
        		}
            }
            // 중복 체크 - 엑셀 내
            if(val1){
            	for(var i = 1 ; i < arr.length; i++){  
            	ANexcelCheck(arr[i][1], i); // 자산번호
        		SNexcelCheck(arr[i][4], i);	// 제조번호
        		if(ANexcelCheckNum != 0 || SNexcelCheckNum != 0) {	// 중복이 있다면
        			val2 = false;
        			if(i < arr.length - 1) {
        				continue;
        			}
        			break;
        			}
            	}
            }
            // 중복체크 - 등록 자산
            if(val1 && val2){
            	for(var i = 1 ; i < arr.length; i++){  
            		eAssetNumberCheck(arr[i][1], i+1); // 자산번호
            		eAssetSNumberCheck(arr[i][4], i+1) // 제조번호
            		if(duplicateValueSN != 0 || duplicateValueAN != 0) {
            			val3 = false;
            			if(i < arr.length - 1) {
            				continue;
            			}
            			break;
            		}
            	}
            }
            
            if(val1 && val2 && val3) {
        	for(var i = 1 ; i < arr.length; i++){  
        		
        		//자산유형 확인
        		checkAssetType(arr[i][0]);
                //텍스트 입력값 길이 확인        		
        		checkTextLength(arr[i][1],80);//자산번호
        		checkTextLength(arr[i][2],80);//자산명
        		checkTextLength(arr[i][3],80);//제조사
        		checkTextLength(arr[i][4],80);//제조번호
        		checkTextLength(arr[i][5],80);//모델명
        		//자산상태 확인
        	//	console.log( "6:자산상태(선택)="+ arr[i][6]); 
        		if(arr[i][6] == null || arr[i][6] == "") {
        			arr[i][6] = assetStatusFirst;
        		}
        		checkAssetStatus(arr[i][6]);
        		//도입원가
        		//processInput(arr[i][7],8);//숫자로만 입력 확인 후 그 외  0 처리
        		//도입일자 날짜 체크
        		checkDateFormat(arr[i][8]);//'yyyy-mm-dd'
        		
        		checkTextLength(arr[i][9],80);//장비구분
        		checkTextLength(arr[i][10],80);//사업명
        		checkTextLength(arr[i][11],80);//망구분
        		checkTextLength(arr[i][12],80);//호스트
        		checkTextLength(arr[i][13],80);//아이피
        		checkTextLength(arr[i][14],80);//운영체제
        		
        		//EoS
        		if(arr[i][15] == null) {
        			arr[i][15] = '2999-01-01';
        		}
        		checkDateFormat(arr[i][15]);//'yyyy-mm-dd'
        		//EoL
        		if(arr[i][16] == null) {
        			arr[i][16] = '2999-01-01';
        		}
        		checkDateFormat(arr[i][16]);//'yyyy-mm-dd'
        		//내구연수
        		//processInput(arr[i][17],2);//숫자로만 입력 확인 후 그 외  0 처리
        		//비고
        		checkTextLength(arr[i][18],80);//비고
        		
//         			console.log( "0:자산유형(선택)="+ arr[i][0]); 
//         			console.log( "1:자산번호(50)="+  arr[i][1]); 
//         			console.log( "2:자산명(80)="+   arr[i][2]); 
//         			console.log( "3:제조사(30)="+   arr[i][3]); 
//         			console.log( "4:제조번호(30)="+  arr[i][4]); 
//         			console.log( "5:모델명(30)="+   arr[i][5]); 
//         			console.log( "6:자산상태(선택)="+ arr[i][6]); 
//         			console.log( "7:도입원가(슷자)="+ arr[i][7]); 
//         			console.log( "8:도입일자(일자)="+ arr[i][8]); 
//         			console.log( "9:장비구분(50)="+  arr[i][9]); 
//         			console.log( "10:사업명(50)="+  arr[i][10]); 
//         			console.log( "11:망구분(50)="+  arr[i][11]); 
//         			console.log( "12:호스트(50)="+  arr[i][12]); 
//         			console.log( "13:아이피(50)="+  arr[i][13]); 
//         			console.log( "14:운영체제(50)="+ arr[i][14]); 
//         			console.log( "15:EoS(일자)="+  arr[i][15]); 
//         			console.log( "16:EoL(일자)="+  arr[i][16]); 
//         			console.log( "17:내구연수(숫자)="+arr[i][17]); 
//         			console.log( "18:비고값(50)="+  arr[i][18]); 
       		}}
        	
        	
        	 if(checkAllConditions()){//체크하여 문제있으면 메시지 출력 후 종료
    			 alert(alertMessage);
    			 return;
    		 }else{
    			 for(var i = 1 ; i < arr.length; i++){
    	  			$.ajax({
    			  			method : "post"
    			  		,	cache  : false
    			  		,	url    : "/mes/asset/kw_uploadAssetAjax.do"
    			  		,	dataType : "json"
    			  		,	async: false
    			       	, 	data: {
    			       			eAssetTypeName    :	arr[i][0]
    				       ,	eAssetNumber	  : arr[i][1]
    				       ,	eAssetName        : arr[i][2]
    				       ,	eAssetMaker       : arr[i][3]
    				       ,	eAssetSNumber     : arr[i][4]
    				       ,	eAssetModel       : arr[i][5]
    				       ,	eAssetStatusName  : arr[i][6]
    				       ,	eAssetCost        : processInput(arr[i][7],10) 
    				       ,	aAssetDate        : arr[i][8]
    				       ,	eDeviceType       : arr[i][9]
    				       ,	eAssetPurpose     : arr[i][10]
    				       ,	eNetworkType      : arr[i][11]
    				       ,	eHostName         : arr[i][12]
    				       ,	eIp               : arr[i][13]
    				       ,	eOs               : arr[i][14]
    				       ,	eEosDate          : arr[i][15]
    				       ,	eEolDate          : arr[i][16]
    				       ,	eLifespan    : processInput(arr[i][17],2)
    				       ,	eAssetEtc    : ConverttoHtml(arr[i][18])
    			       		
    		             	}
    			  		,	success:function(msg){
    			  			//	console.log("성공");
    			  			}
    			  		,	error: function(xhr, status, error) {
    			           		// 요청이 실패했을 때 수행할 작업
    			           		console.log(error);
    			           		if (xhr.status === 500) {
    			           			// AJAX 요청 중단
    			           			console.log('AJAX 요청이 중지되었습니다.');
    									}
    								} 
    			   		});
    			 } //for
    			 alert("업로드 완료!");
    			 
    		 }//if else
        	
        });
        
       
    };
    reader.readAsBinaryString(input.files[0]);
    
    e.target.value = ''; 
}

$(document).ready(function() {
	
	if ($("#viewDetail").val() === "Y") {
        $("#search_detail").css("display", "flex");
    }
	

	$('#searchTypeSet1 option').each(function () {
	     const dataname = $(this).data('value2');  
	     if (dataname) {
	    	assetTypeChars.push(dataname); 
	     }
	   });
	$('#searchType option').each(function () {
	     const dataname = $(this).data('value2');
	     if (dataname) {
	    	 assetStatusChars.push(dataname); 
	     }
	   });
	if(assetStatusChars.length > 0){
		assetStatusFirst = assetStatusChars[0];
	}
	
    
	    
	 datepickerSet('topStartDate', 'topEndDate');
		// No를 제외한 나머지 설정-다른항목 한가지를 제외하고 공통으로 변경할때 -
		// 	  $('table[role="grid"].gridjs-table th:not(:nth-child(1))').css('width', '150px');
	    // 그리드 헤더의 너비를 조정하는 스타일 변경
	  $('gridjs-wrapper').css('overflow-y', 'hidden');  
	  $('gridjs-tr').css('overflow-y', 'hidden');  
	  //$('table[role="grid"].gridjs-table th').css('pointer-events', 'none');
	  $('table[role="grid"].gridjs-table th:nth-child(1) button').hide();
	  $('table[role="grid"].gridjs-table th:nth-child(1)').css('width', '70px'); // No.
	  $('table[role="grid"].gridjs-table th:nth-child(2)').css('width', '100px'); // 작성자.
	  $('table[role="grid"].gridjs-table th:nth-child(3)').css('width', '100px'); // 자산유형
	  $('table[role="grid"].gridjs-table th:nth-child(4)').css('width', '140px'); // 자산번호
	  $('table[role="grid"].gridjs-table td:nth-child(4)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(5)').css('width', '200px'); // 자산명
	  $('table[role="grid"].gridjs-table td:nth-child(5)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(6)').css('width', '140px'); // 설치위치
	  $('table[role="grid"].gridjs-table td:nth-child(6)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(7)').css('width', '180px'); // 제조사
	  $('table[role="grid"].gridjs-table td:nth-child(7)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(8)').css('width', '200px'); // 모델명
	  $('table[role="grid"].gridjs-table td:nth-child(8)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(9)').css('width', '240px'); // 제조번호
	  $('table[role="grid"].gridjs-table td:nth-child(9)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(10)').css('width', '120px'); // 망구분
	  $('table[role="grid"].gridjs-table td:nth-child(10)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(11)').css('width', '120px'); // 장비구분
	  $('table[role="grid"].gridjs-table td:nth-child(11)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(12)').css('width', '120px'); // Host Name
	  $('table[role="grid"].gridjs-table td:nth-child(12)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(13)').css('width', '200px'); // IP
	  $('table[role="grid"].gridjs-table td:nth-child(13)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(14)').css('width', '120px'); // OS
	  $('table[role="grid"].gridjs-table td:nth-child(14)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(15)').css('width', '200px'); // 사업명
	  $('table[role="grid"].gridjs-table td:nth-child(15)').each(function() {
		    var content = $(this).html();
		    // <p>와 </p>를 제거
		    $(this).html(content);
		 // nowrap을 적용하여 줄내림 방지, overflow는 숨기기
		    $(this).css({
		        'white-space': 'nowrap',
		        'overflow': 'hidden',
		        'text-overflow': 'ellipsis'  // 텍스트가 넘칠 경우 '...'로 표시
		    });
		});
	  $('table[role="grid"].gridjs-table th:nth-child(16)').css('width', '120px'); // 도입일
	  $('table[role="grid"].gridjs-table th:nth-child(17)').css('width', '120px'); // 도입원가
	  $('table[role="grid"].gridjs-table th:nth-child(18)').css('width', '100px'); // 자산구분
	  $('table[role="grid"].gridjs-table th:nth-child(19)').css('width', '140px'); // 결재
	  $('table[role="grid"].gridjs-table td:nth-child(19)').each(function() {
		    $(this).css({
		    	  "cursor": "default"
		    });
		});
	  tdBlock(18);
	  
	  $('table[role="grid"].gridjs-table td[data-column-id="도입원가"]').css('text-align', 'left'); //정렬 변경
	  $('#mloader').hide();
		
});
function startApproval(eAssetKey, sSignStatus){
	$("#mloader").show();
	$("#eAssetKey").val(eAssetKey);
	$("#sSignStatus").val(sSignStatus);
	document.listForm.action = "/mes/asset/kw_asset_lfr.do";
	document.listForm.submit();
}

function setBackgroundBasedOnValue() {
    var trElements = document.querySelectorAll('.gridjs-tr');
    trElements.forEach(function(tr) {
        var secondInput = tr.querySelector('input:nth-of-type(2)');  
        var value = secondInput ? secondInput.value.trim() : '';
        if (value === 'N') {
           // tr.style.backgroundColor = '#c0c0c0'; 
//         	 tr.classList.add('highlighted-row');
           $(tr).css('backgroundColor', '#c0c0c0');
        }
    });
}
function updateAssetStatus() {
	document.listForm.action = "/mes/asset/kw_asset_updateProcess.do";
	document.listForm.submit();
}

function excelDwonload(){
    document.listForm.action = "/mes/asset/kw_assetExcelInfoDwonload.do";
    document.listForm.submit();
    document.listForm.action = "/mes/asset/kw_asset_lf.do";
}


</script>
  
<style>
  .highlighted-row {
    background-color: #c0c0c0 !important;
     #tableContainer {
        width: 100%;
        overflow-x: auto; /* 가로 스크롤 추가 */
    }

    #myTable {
        width: 100%;
        border-collapse: collapse;
        table-layout: fixed; /* 고정 테이블 레이아웃 */
    }

    #myTable th, #myTable td {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #myTable th {
        background-color: #f2f2f2;
        text-align: left;
    }
}
</style>
<form name="listForm" id="listForm" method="post" action = "/mes/asset/kw_asset_lf.do">		
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}"/>
	<input type="hidden" id="eAssetKey" name="eAssetKey" value="" />
	<input type="hidden" id="sSignStatus" name="sSignStatus" value="" />
	<input type="hidden" id="viewDetail" name="viewDetail" value="${mesAssetVO.viewDetail}" />
	
	<div class="content_top">
		<div class="content_tit">
			<h2>정보관리대장</h2>
		</div>		
		<div class="filter_wrap"> 
			<div class="search_filter">
				<ul> 
					<li>
						<span>자산유형</span>
						<select id='searchTypeSet1' name='searchTypeSet1'  >
							<option value=''>전체조회</option>
							<c:forEach var='list' items='${gubun36List}'>
							<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'   <c:if test="${mesAssetVO.searchTypeSet1 eq list.sGubunKey  }">selected="selected"</c:if> >${list.sGubunName}</option>						
							</c:forEach>
						</select> 
					</li>
					<li>
						<span>자산번호</span>
						<input type="text" id="searchWord" name="searchWord" class="searchWord" value="${mesAssetVO.searchWord}" maxlength="30" />
					</li>
					<li>
						<span>자산명</span>
						<input type="text" id="searchTypeSet2" name="searchTypeSet2" class="searchWord" value="${mesAssetVO.searchTypeSet2}" maxlength="30" />
					</li>
					<li>
						<span>제조사</span>
						<input type="text" id="searchTypeSet3" name="searchTypeSet3" class="searchWord" value="${mesAssetVO.searchTypeSet3}" maxlength="30"  />
					</li>
					<li>
						<span>자산상태</span>
						<select id='searchType' name='searchType'  >
							<option value=''>전체</option>
							<c:forEach var='list' items='${gubun37List}'>
							<option value='${list.sGubunKey}' data-value2='${list.sGubunName}'   <c:if test="${mesAssetVO.searchType eq list.sGubunKey  }">selected="selected"</c:if> >${list.sGubunName}</option>						
							</c:forEach>
						</select> 
					</li>
					<li>
						<span>일반/노후 </span>
						<select id='searchTypeSet9' name='searchTypeSet9'  >
							<option value='' >전체</option>
							<option value='노후장비' <c:if test="${mesAssetVO.searchTypeSet9 eq '노후장비'  }">selected="selected"</c:if>>노후장비</option>
							<option value='일반장비' <c:if test="${mesAssetVO.searchTypeSet9 eq '일반장비'  }">selected="selected"</c:if>>일반장비</option>
						</select>
					</li>
				</ul>
				<ul id="search_detail">
					<li>
						<span>모델명</span>
						<input type="text" id="searchTypeSet4" name="searchTypeSet4" value="${mesAssetVO.searchTypeSet4}" maxlength="30" />
					</li>
					<li>
						<span>제조번호</span>
						<input type="text" id="searchTypeSet5" name="searchTypeSet5" value="${mesAssetVO.searchTypeSet5}" maxlength="30" />
					</li>
					<li>
						<span>HOST NAME</span>
						<input type="text" id="searchTypeSet6" name="searchTypeSet6" value="${mesAssetVO.searchTypeSet6}" maxlength="30" />
					</li>
					<li>
						<span>IP</span>
						<input type="text" id="searchTypeSet7" name="searchTypeSet7" value="${mesAssetVO.searchTypeSet7}" maxlength="30" />
					</li>
					<li>
						<span>OS</span>
						<input type="text" id="searchTypeSet8" name="searchTypeSet8" value="${mesAssetVO.searchTypeSet8}" maxlength="30" />
					</li>
				</ul>
			</div>
			<div class="button_wrap">
				<button type="button" class="form_btn ico_search" onclick="fn_search_detail();" >상세검색</button>
				<button type="button" class="form_btn ico_excel" onclick="excelDwonload();">엑셀 다운로드</button>
	     		<button type="button" class="form_btn bg" onclick="fn_guestList(1)">검색</button>
			</div>
		</div>
	</div> 	
	
	<div class="data_table">
		<table id="myTable"  style="width:100%;  overflow:auto;">
			<thead>
				<tr>
					<th>No.</th>
					<th>작성자</th>
					<th>자산유형</th>
					<th>자산번호</th>
					<th>자산명</th>
					<th>모델명</th>
					<th>제조사</th>
					<th>제조번호</th>
					<th>설치위치</th>
					<th>망구분</th>
					<th>장비구분</th>
					<th>Host Name</th>
					<th>IP</th>
					<th>OS</th>
					<th>사업명</th>
					<th>도입일</th>
					<th>도입원가</th>
					<th>자산상태</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="list" items="${assetList}" varStatus="i">
					<tr onclick="assetView(<c:out value='${list.eAssetKey}'/>);" >
						<td>
							<c:if test="${list.eViewGubun eq 'Y'}"><span style="color: RED;">!</span></c:if>
<%-- 						<c:if test="${list.gubun eq 'Y'}"><span style="color: RED;">결재!</span></c:if>   --%>
<!-- 						<img src="/images/images/exclamationMark.png" alt="확인사항" onerror="this.style.display='none';"> -->
							${paginationInfo.totalRecordCount - (mesAssetVO.pageIndex-1) * paginationInfo.recordCountPerPage  - i.index}
							<input type="hidden" value="${list.eAssetKey}" />
						</td>
						<td>${list.kStaffName}</td>
						<td>${list.eAssetTypeName}</td>
						<td>${list.eAssetNumber}</td>
						<td>${list.eAssetName}</td>
						<td>${list.eAssetModel}</td>
						<td>${list.eAssetMaker}</td>
						<td>${list.eAssetSNumber}</td>
						<td>${list.ePositionName1}</td>
						<td>${list.eNetworkType}</td>
						<td>${list.eDeviceType}</td>
						<td>${list.eHostName}</td>
						<td>${list.eIp}</td>
						<td>${list.eOs}</td>
						<td>
							${list.eAssetPurpose}
						</td>
						<td>
							${list.eAssetDate}
						</td>
						<td>
							${list.eAssetCost}
						</td>
						<td>
							${list.eAssetStatusName}
						</td>
						<td>
							<c:if test="${list.sSignStatus eq '제외' || list.sSignStatus eq '승인' || list.sSignStatus eq '반려'}">결재 ${list.sSignStatus}</c:if>
							<c:if test="${list.sSignStatus eq '등록'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey}"><a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eAssetKey}','Y');">승인요청</a></c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey}">결재 준비</c:if>
							</c:if>
							<c:if test="${list.sSignStatus eq '승인요청'}">
								<c:if test="${list.kStaffKey eq staffVO.kStaffKey && list.sSignProgress eq '0'}">
									<a style="cursor: pointer;" class="form_btn sm" onclick="startApproval('${list.eAssetKey}','N');">요청취소</a>
								</c:if>
								<c:if test="${list.kStaffKey ne staffVO.kStaffKey || list.sSignProgress ne '0'}">결재 진행 중</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="list_btm">
		<div class="options">
			<select name="recordCountPerPage" class="selectbox select_recordCountPerPage" id="recordCountPerPage" onchange="fn_guestList(1)">
           		<option value="10" <c:if test="${mesAssetVO.recordCountPerPage eq 10}">selected="selected"</c:if>>10개씩 보기</option>
           		<option value="20" <c:if test="${mesAssetVO.recordCountPerPage eq 20}">selected="selected"</c:if>>20개씩 보기</option>
           		<option value="50" <c:if test="${mesAssetVO.recordCountPerPage eq 50}">selected="selected"</c:if>>50개씩 보기</option>
           		<option value="100" <c:if test="${mesAssetVO.recordCountPerPage eq 100}">selected="selected"</c:if>>100개씩 보기</option>
    		</select> 
    		<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
	    		<button type="button" class="form_btn ico_excel" onclick="formDownload()">양식 다운로드</button>
	    		<button type="button" class="form_btn ico_excel" onclick="document.getElementById('managerFile').click();">엑셀 등록</button>
	    		<input id="managerFile" type="file"  style="display: none;" onchange="readExcel(event);"  accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
			</c:if>
		</div>
		<div class="paging">
			<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_guestList" />
		</div>
		<div class="btns">
			<c:if test="${staffVO.kStaffAuthWriteFlag eq 'T'}">
				<button type="button" class="form_btn ico_refresh" onclick="updateAssetStatus()">노후화 정보 갱신</button>
				<button type="button" class="form_btn active" onclick="go_insert()">자산 등록</button>
			</c:if>
		</div>
	</div>
</form>