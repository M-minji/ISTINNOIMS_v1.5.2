<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>


<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>

<!-- 화면 캡처를 위한 (시작) --> 
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#aAssetRequestDate").datepicker({
			changeMonth : true,
			changeYear	: true,
			showMonthAfterYear : true,
			dateFormat : "yy-mm-dd",
			dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
		});
		

		settingSign();
	})
	
	// 결재
	function startApproval(aAssetKey, sSignKey){
		$("#mloader").show();
		$("#aAssetKey").val(aAssetKey);
		$("#sSignKey").val(sSignKey);
		document.frm.action = "/mes/asset/kw_asset_rv.do";
		document.frm.submit();
	}
	
	function cancle(){
		$('#mloader').show();
		document.frm.action = "/mes/asset/kw_asset_use_lf.do";
		document.frm.submit(); 
	}
	
	function changeContent(value){
		var innerStr = "";
		
		if(value == "승인"){
			innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
			innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		} else if(value == "반려"){
			innerStr += "<input type='text' id='sSignContent' name='sSignContent' value='' placeholder='반려 사유' style='width:95%' maxLength='100'/>";
		}
		document.getElementById("sSignContentSet").innerHTML = innerStr;
	}
	
	

	


	function settingSign(){
		/* 사인 ============================= */
		var canvas = $("#signature")[0];
		var signature = new SignaturePad(canvas, {
			minWidth : 2,
			maxWidth : 2,
			penColor : "rgb(0, 0, 0)"
		});
		
		$("#save").on("click", function() {
			if(signature.isEmpty()) {
				alert("내용이 없습니다.");
			} else {
				//저장버튼시 부서, 날짜, 서명을 저장한다.
				var data = signature.toDataURL("image/png");
				var sSignKey = $("#sSignKey").val();
				var kStaffKey = $("#kStaffKey").val();
				
				$.ajax({
					type : "post"
				,	url : "/mes/sign/kw_uploadSignImgAjax.do"
				,	data : {
						"sSignKey"			: sSignKey
					,	"sSignStaffKey"		: kStaffKey
					,	"sSignDecison"		: "승인"
					,	"sSignContent"		: data	
					}
				,	dataType : "json"
				,	async : false
				,	cache : false
				,	success : function(msg){
					
						innerStr = "";
						innerStr += "<img style='width:150px; height:100px;' onclick='setSignHtml(this,"+signId+");' src='"+data+"'/>";
						innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'>"+data+"</textarea>";
						
						saveObj.parentNode.innerHTML = innerStr;
						
						$('#modal-close').get(0).click();
					}
				});
			}
		});
		
	}

	/*SIGN PAD*/
	var scrollPosition = 0;
	function setSign(obj, eventTmp, signIdTmp){
			
		if($("#eProreqAppDate"+signIdTmp).val() == ""){
			if(confirm("서명일자를 오늘로 설정하시겠습니까?")){
				$("#eProreqAppDate"+signIdTmp).val(nowDate());
			}else{
				return false;
			}
			
		}
		
		event.preventDefault();
		scrollPosition = window.scrollY;
		document.body.style.overflow = "hidden";
		$("#setModal").modal({
	        escapeClose: false,
	        clickClose: false,
	        showClose: false
	    });
		saveObj = obj;
		signId = signIdTmp;
		
		var canvas = $("#signature")[0];
	  	var cnvs = document.getElementById('signature');
		// context
	    var ctx = canvas.getContext('2d');
	    // 픽셀 정리
	    ctx.clearRect(0, 0, cnvs.width, cnvs.height);
	    // 컨텍스트 리셋
	    ctx.beginPath();
	}

	function closeModal(){
		document.body.style.overflow = "auto";
		setTimeout(function() {
			window.scrollTo(0, scrollPosition);
		}, 50);
	}

	function setSignHtml(obj){
		innerStr = "";
		innerStr += "<a class='mes_btn' onclick='setSign(this, event);'>사인</a>";
		innerStr += "<textarea style='display:none' rows='5' cols='5' id='sSignContent' name='sSignContent'></textarea>";
		obj.parentNode.innerHTML = innerStr;
	}



//	화면 캡처 후 인쇄로 넘어가기
	function capture() { 
		html2canvas($("#print_div"), {
			onrendered: function(canvas) { 
				var img = canvas.toDataURL("image/png");
				console.log(img); 
//					window.open(img); // 이미지를 윈도우 팝업으로..
				win = window.open();
				self.focus(); 
				win.document.open();
		  		win.document.write('<html><head><title></title>');
		  		win.document.write('</haed><body><table><tr><td>');
		  		win.document.write('<img  width="95%" src=' + img + '>');
		  		win.document.write('</td></tr></table></body></html>');
				win.document.close();
	  			setTimeout(function(){
	  			    win.print();
	  			    win.close();
	  			    }, 5);

			}
		}); 
	} 	

	function printPop(setKey){
		var url = "/mes/proreq/popup/kw_proreq_vf_popup.do";
		url += "?eProreqKey="+setKey;
		window.open(url ,"addrAdd" ,"width=1200,height=650,menubar=no,status=no,scrollbars=yes,location=no,resizable=yes");
	}
	

	
	function setApproval(){
		if(document.getElementById("sSignContent") == null){
			alert("승인 후 싸인이 필요합니다");
			return false;
		}
		if(document.getElementById("sSignContent").value == ""){
			alert("반려사유가 입력되지 않았습니다 ");
			document.getElementById("sSignContent").focus();
			return false;
		}
		$("#mloader").show();
		document.frm.action = "/mes/asset/kw_asset_s.do";
		document.frm.submit();
	}

</script>


<form id="frm" name="frm" method="post">
	<input type="hidden" id="pageIndex" name="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" id="searchWord"name="searchWord" value="${mesAssetVO.searchWord}">
	<input type="hidden" id="searchType" name="searchType" value="${mesAssetVO.searchType}" />
	
	<input type="hidden" id="aAssetKey" name="aAssetKey" value="${assetInfo.aAssetKey}">
	<input type="hidden" id="aAssetUseKey" name="aAssetUseKey" value="${assetUseInfo.aAssetUseKey}">
	<input type="hidden" id="sSignKey" name="sSignKey" value="${assetUseInfo.sSignKey}">
	<input type="hidden" id="kStaffKey" name="kStaffKey" value="${staffVO.kStaffKey}" />
	
	<div class="content">
		<div class="content_tit">
			<h2>자산 반입/반출 결재</h2>
		</div>
	</div>
	
	<div class="tbl_write">
		<table>
			<caption style="text-align: left; margin-bottom:10px;">
				□ 자산 정보
			</caption>
			<tbody>
				<tr>
					<th>자산번호</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetNumber}
					</td>
					<th>상태</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetStatus}
					</td>
				</tr>
				<tr>
					<th>자산유형</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetType}
					</td>
					<th>자산명</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetName}
					</td>
				</tr>
				<tr>
					<th>제조사</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetMaker}
					</td>
					<th>모델명</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetModel}
					</td>
				</tr>
          		<tr>
            		<th>제조번호</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetManufactureNumber}
					</td>
            		<th>도입형태</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetForm}
					</td>
          		</tr>
          		<tr>
            		<th>도입사명</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetIntroducer}
					</td>
            		<th>도입일</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetDate}
					</td>
          		</tr>	
          		<tr>
          			<th>도입원가</th>
            		<td style="text-align:right; padding-right:5px;">
						${assetInfo.aAssetCost}
            		</td>
            		<th>도입목적</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetPurpose}
					</td>
          		</tr>			
          		<tr>       		
					<th>비고</th>
					<td style="text-align:left; padding-left:5px;">
						${assetInfo.aAssetEtc}
					</td>
            		<th>사진</th>
            		<td style="padding: 0.5em;">
						<img style="width:100%;"src='/cmm/fms/getImage.do?atchFileId=${assetInfo.aAssetImage}&fileSn=0' onerror="this.style.display='none'"/>
					</td>    
          		</tr>					
			</tbody>
		</table>
	</div>
	
	<div class="tbl_list">
      	<table>
        	<thead>
          		<tr>
            		<th style="width:7.5%;">구분</th>
            		<th style="width:7.5%;">요청일</th>
            		<th style="width:7.5%;">설치장소</th>
            		<th style="width:7.5%;">세부장소</th>
            		<th style="width:7.5%;">HOST명</th>
            		<th style="width:7.5%;">망구분</th>
            		<th style="width:7.5%;">IP주소</th>
            		<th style="width:7.5%;">MAC주소</th>
            		<th style="width:7.5%;">OS버전</th>
            		<th style="width:7.5%;">운영부서</th>
            		<th style="width:7.5%;">운영담당자</th>
            		<th style="width:7.5%;">연락처</th>
            		<th style="width:10%;">결재현황</th>
          		</tr>
        	</thead>
        	<tbody>
       			<tr>
					<td>
						${assetUseInfo.aAssetUseType}
					</td>
					<td>
						${assetUseInfo.aAssetRequestDate}
					</td>
					<td>
						${assetUseInfo.aAssetInstallPlace}
					</td>
					<td>
						${assetUseInfo.aAssetInstallPlaceDetail}
					</td>
					<td>
						${assetUseInfo.aAssetUseHost}
					</td>
					<td>
						${assetUseInfo.aAssetUseNet}
					</td>
					<td>
						${assetUseInfo.aAssetUseIp}
					</td>
					<td>
						${assetUseInfo.aAssetUseMac}
					</td>
					<td>
						${assetUseInfo.aAssetUseOS}
					</td>
					<td>
						${assetUseInfo.aAssetUseSector}
					</td>
					<td>
						${assetUseInfo.aAssetUseManager}
					</td>
					<td>
						${assetUseInfo.aAssetUsePhone}
					</td>
					<td>
						${assetUseInfo.sSignStatus}
					</td>
       			</tr>
			</tbody>
		</table>
	</div>
	
	
	<div style="margin-top:60px;">   
		<div class="tbl_list">
			<table>
				<caption style="text-align: left; margin-bottom:10px;">
					□ 사용내역 결재자 명단
				</caption>
				<thead>
					<tr>
						<th colspan="5">결재 정보</th>
					</tr>
					<tr>
						<th style="width: 10%;">결재순서</th>
						<th style="width: 10%;">구분</th>
						<th style="width: 10%;">결재여부</th>
						<th style="width: *%;">결재자</th>
					</tr>
				</thead>
				<tbody id="lineRow3">		
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr <c:if test="${signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">style="background-color:yellow;"</c:if>>
							<td style="text-align:center; width:5%; padding-left:0px;">
								${i.index + 1}
							</td>
							<td style="text-align:left; padding-left:5px; width:20%;">
								${signList.sSignStaffName}
							</td>
							<td style="text-align:center; width:10%;">
								${signList.sSignDecison}
							</td>
							<td>
								<c:if test="${assetUseInfo.sSignStatus eq '승인요청' && assetUseInfo.sSignStaffKey eq staffVO.kStaffKey && signList.sSignStaffKey eq staffVO.kStaffKey}">
									<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
										<option value="승인" selected>승인</option>
										<option value="반려">반려</option>
									</select>
								</c:if>
							</td>
							<td <c:if test="${assetUseInfo.sSignStatus eq '승인요청' && assetUseInfo.sSignStaffKey eq staffVO.kStaffKey && signList.sSignStaffKey eq staffVO.kStaffKey}">id="sSignContentSet"</c:if> style="text-align:left; padding-left:5px; width:60%;">
								<c:if test="${assetUseInfo.sSignStatus eq '승인요청' && assetUseInfo.sSignStaffKey eq staffVO.kStaffKey && signList.sSignStaffKey eq staffVO.kStaffKey}">
						        		<a class="mes_btn" onclick="setSign(this, event);">사인</a>
								</c:if>
								<c:if test="${signList.sSignDecison eq '승인'}">
									<img src="${signList.sSignContent}"/>
								</c:if>
								<c:if test="${signList.sSignDecison eq '반려'}">
									${signList.sSignContent}
								</c:if>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>	
	
	<div class="tbl_btn_right">
		<ul>
			<li>
				<a style="cursor: pointer;" onclick="setApproval();">결재처리</a>
			</li>
			<li>
         		<a style="cursor: pointer;" onclick="cancle();">
					목록
				</a>
	 		</li>
	 	</ul>
	</div>
	
	
	
	<div id="setModal" class="modal" style="display:none;">
		<a id="modal-close" href="#close-modal" rel="modal:close" class="close-modal " onclick="closeModal()">Close</a>
		<div class="modal-content">
			<div class="tbl_top" id="modalAddRow">
				<ul class="tbl_top_right">
					<li>
		          		<a class="mes_btn" id="save">저장</a>
			   		</li>
				</ul>
			</div>
			<div class="lf_tbl_list scrolltbody" style="margin-top: 0px; border: 0.5px #d1d1d1 solid; border-radius:5px;">
				<canvas id="signature" width="450" height="200"></canvas>
			</div>
		</div>
	</div>	
</form>