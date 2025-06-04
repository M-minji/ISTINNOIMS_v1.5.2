<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<!-- <script src="/js/jquery/jquery-3.7.1.min.js"></script> -->
<!-- <script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script> -->
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>
<script src="/js/jquery/qrcode.min.js"></script>
 <script src="/js/jquery/jquery-3.7.1.min.js"></script>
<!-- <script src="/js/jquery/jquery-3.6.0.min.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="/css/mes/print.css" media="print" />  -->

<script type="text/javascript">
$(document).ready(function(){	
	barcodeCr();
}); 	

function capture() {
	html2canvas($("#print_div2"), {
		onrendered: function(canvas) { 
			var img = canvas.toDataURL("image/jpg");
//				window.open(img); // 이미지를 윈도우 팝업으로..
			win = window.open();
			self.focus(); 
			win.document.open();
	  		win.document.write('<html><head><title></title>');
	  		win.document.write('</haed><body><table><tr><td>');
	  		win.document.write('<img src=' + img + '>');
	  		win.document.write('</td></tr></table></body></html>');
  			setTimeout(function(){top.window.opener = top;top.window.open('','_parent','');top.window.close();});
			win.document.close();
  			setTimeout(function(){
  			    win.print();
  			    win.close();
  			    }, 5);

		}
	}); 
}

function in_print(){
// 	var initBody = document.body.innerHTML;
// 	window.onbeforeprint = function () {
// 		document.body.innerHTML = document.getElementById("print_div2").innerHTML;

// 	}

// 	window.onafterprint = function () {
// 		document.body.innerHTML = initBody;
// 	}

// 	window.print();
	 var initBody = document.body.innerHTML;
	    var printContent = document.getElementById("print_div2").innerHTML;

	    // 프린트 이벤트 발생 전에 클래스 추가하여 스타일을 변경
	    document.body.classList.add('printing-mode');

	    window.onbeforeprint = function() {
	        // 프린트 미리보기 시 컨텐츠를 변경
	        document.body.innerHTML = printContent;
	    };

	    window.onafterprint = function() {
	        // 프린트 후에 초기화
	        document.body.innerHTML = initBody;
	        document.body.classList.remove('printing-mode'); // 프린트 모드 클래스 제거
	    };

	    window.print();
}

function barcodeCr(){
// 	var ln = ${fn:length(barlist)};
	var ln = 1;
	
// 	for(var i=0; i<ln; i++){
// 		var eBarcode = $('#eBarCode_'+i).val();
		var eBarcode = $('#eBarCode_1').val();
// 		$("#bcTarget_"+i).barcode(eBarcode, "qrcode", {barWidth:1, barHeight:50});
// 		$("#bcTarget_1").barcode(eBarcode, "qrcode");
// 	}

		  var qrcode = new QRCode(document.getElementById("bcTarget_1"), {
              text: eBarcode,
              width: 150,
              height: 150
          });

}
</script>
 <style>
 	 .container {
            display: flex;
            width: 100%;
            justify-content: flex-start; 
            border: 4px solid #000; /* 바깥 테두리 두껍게 */
            box-sizing: border-box;
		    width: 18cm;
		    height: 7cm;
		    margin: 0 auto;
        }
        .left {
            width: 26%;
            border-right: 1px solid #ccc; /* 좌측 영역 오른쪽 얇은 테두리 */
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .right {
            width: 74%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            border-left: 1px solid #ccc; /* 우측 영역 왼쪽 얇은 테두리 */
        }
        .row {
            flex: 1;
            border-bottom: 1px solid #ccc; /* 각 행의 얇은 테두리 */
            display: flex;
            align-items: center;
            padding: 1em;
            box-sizing: border-box;
        }
        .row:last-child {
            border-bottom: none; /* 마지막 행은 아래쪽 테두리 제거 */
        }
            .left-content {
            width: 20%;
            border-right: 1px solid #ccc; /* 좌측 콘텐츠 오른쪽 얇은 테두리 */
            padding-right: 1em;
            box-sizing: border-box;
            font-weight: bold;
       		font-size: 16px;
        }
        .right-content {
            width: 80%;
            padding-left: 1em;
            box-sizing: border-box;
        }
         @media print {
            .container {
                display: flex !important;
                flex-direction: row !important;
            }
            .left, .right {
                width: auto !important;
                display: flex !important;
            }
            .right {
                flex-direction: column !important;
            }
            .row {
                border-bottom: 1px solid #000 !important;
            }
        }
 </style>
<head>
    <meta charset="UTF-8">
    <title>Barcode</title>
   
<form id="frm" name="frm" method="post" >
		<div class="pop_head">
			<div id="pop_head">
				<div class="tit">
					<h3>바코드 출력</h3>
				</div>
				<a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
			</div>  
		</div>
		
		<div id="print_div2" >
			 <input type="hidden" name="eBarCode" id="eBarCode_1" value="http://${eIPaddress}:8080/asset/qr_asset_info.do?eAssetKey=${assetInfo.eAssetKey}"/>
					 <div class="container" > 
							<div class="left">
					            <div id="bcTarget_1"></div>
					        </div>
						    <div class="right">
					            <div class="row">
					            	<div class="left-content">호스트명</div>
			                        <div class="right-content">${assetInfo.eHostName}</div>
				            	</div>
					            <div class="row">
					            	<div class="left-content">모델명</div>
			                        <div class="right-content">${assetInfo.eAssetModel}</div>
		                        </div>
					            <div class="row">
						            <div class="left-content">제조번호</div>
				                    <div class="right-content">${assetInfo.eAssetSNumber}</div>
								</div>
					              <div class="row">
			                        <div class="left-content">설치장소</div>
			                        <div class="right-content">${assetInfo.ePositionName1} - ${assetInfo.eAssetDate}</div>
			                      </div>
					        </div>
			         </div>
		</div>
		<div class="tbl_btn_right">
			<ul>
				<li>
					<a class="mes_btn" style="cursor: pointer;"   onclick="capture()">출력</a>
<!-- 					<a onclick="in_print()">출력</a> -->
          		</li>
          	</ul>
		</div>
</form>