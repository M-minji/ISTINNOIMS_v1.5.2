<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>


<!-- SIGN PAD -->
<link rel="stylesheet" href="/js/modal/jquery.modal.min.css" />
<script src="/js/modal/jquery.modal.min.js"></script>
<script src="/js/signature/signature_pad.min.js"></script>


<script type="text/javascript">

// 수정
function update_go(){
	$("#mloader").show();
	document.frm.action = "/mes/asset/kw_asset_use_uf.do";
	document.frm.submit();
}

// 목록
function cancle(){
	$("#mloader").show();
	document.frm.action = "/mes/asset/kw_asset_use_lf.do";
	document.frm.submit(); 
}
	
// 삭제
function delete_go(){
	if(confirm("선택한 내역을 삭제하시겠습니까?")){
		$("#mloader").show();
		document.frm.action = "/mes/asset/kw_asset_use_d.do";
		document.frm.submit();
	}
}

function requestApproval(key, sSignKey, gubun){
		$("#mloader").show();
		$("#gubun").val(gubun);
		$("#aAssetUseKey").val(key);
		$("#sSignKey").val(sSignKey);
		document.frm.action = "/mes/asset/kw_asset_use_r.do";
		document.frm.submit();
}

function setApproval(key, sSignKey){
	$("#mloader").show();
	$("#aAssetUseKey").val(key);
	$("#sSignKey").val(sSignKey);
	document.frm.action = "/mes/asset/kw_asset_use_sf.do";
	document.frm.submit();
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




</script>


<form id="frm" name="frm" method="post">
	<input type="hidden" name="searchWord" id="searchWord" value="<c:out value='${mesAssetVO.searchWord}'/>">
	<input type="hidden" name="searchType" id="searchType" value="${mesAssetVO.searchType}" />
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	
	<input type="hidden" id="aAssetKey" name="aAssetKey" value="${assetUseInfo.aAssetKey}">
	<input type="hidden" id="aAssetUseHKey" name="aAssetUseHKey" value="${assetUseInfo.aAssetUseHKey}">
	<input type="hidden" id="sSignKey" name="sSignKey" value="${assetUseInfo.sSignKey}">
	
	<div class="content">
		<div class="content_tit">
			<h2>대상장비 이력 상세</h2>
		</div>
	</div>
	
	<div class="tbl_write">
		<table>
			<caption style="text-align: left; margin-bottom:10px;">
				□ 대상장비 정보
			</caption>
			<tbody>
				<tr>
					<th>자산번호</th>
					<td>
						${assetUseInfo.aAssetNumber}
					</td>
					<th>작성일자</th>
					<td>
						${assetUseInfo.aAssetUseDate}
					</td>
				</tr>
				<tr>
					<th>제조사</th>
					<td>
						${assetUseInfo.aAssetMaker}
					</td>
					<th>자산명</th>
					<td>
						${assetUseInfo.aAssetName}
					</td>
				</tr>
				<tr>
					<th>제조번호</th>
					<td>
						${assetUseInfo.aAssetManufactureNumber}
					</td>
					<th>모델명</th>
					<td>
						${assetUseInfo.aAssetModel}
					</td>
				</tr>
          		<tr>
            		<th>설치위치</th>
					<td>
						${assetUseInfo.aAssetIntroducer}
					</td>
            		<th>도입일</th>
					<td>
						${assetUseInfo.aAssetDate}
					</td>
          		</tr>				
          		<tr>  
            		<th>장비사진</th>
            		<td colspan="3">
						<a class="mes_btn" onclick="view_img()">view</a>
						<input type="hidden" id="aAssetImage" name="aAssetImage" value="${assetInfo.aAssetImage}">
						<!-- 
						<img style="width:100%;"src='/cmm/fms/getImage.do?atchFileId=${assetInfo.aAssetImage}&fileSn=0' onerror="this.style.display='none'"/>
					 	-->
					</td>    
          		</tr>					
			</tbody>
		</table>
	</div>
	
	<div class="tbl_list">
      	<table>
      		<caption style="text-align: left; margin-bottom:10px;">
				□ 대상장비 이력 
			</caption>
        	<thead>
          		<tr>
            		<th style="width:7%;">구분</th>
            		<th style="width:7%;">상태</th>
            		<th style="width:7%;">요청일</th>
            		<th style="width:7%;">설치장소</th>
            		<th style="width:7%;">세부장소</th>
            		<th style="width:7%;">HOST명</th>
            		<th style="width:7%;">망구분</th>
            		<th style="width:7%;">IP주소</th>
            		<th style="width:7%;">MAC주소</th>
            		<th style="width:7%;">OS버전</th>
            		<th style="width:7%;">운영부서</th>
            		<th style="width:7%;">운영담당자</th>
            		<th style="width:7%;">연락처</th>
          		</tr>
        	</thead>
        	<tbody>
        		<c:forEach var="assetUseList" items="${assetUseList}" varStatus="i">
        			<tr>
						<td>
							${assetUseList.aAssetUseType}
						</td>
						<td>
							${assetUseList.aAssetRequestDate}
						</td>
						<td>
							${assetUseList.aAssetInstallPlace}
						</td>
						<td>
							${assetUseList.aAssetInstallPlaceDetail}
						</td>
						<td>
							${assetUseList.aAssetUseHost}
						</td>
						<td>
							${assetUseList.aAssetUseNet}
						</td>
						<td>
							${assetUseList.aAssetUseIp}
						</td>
						<td>
							${assetUseList.aAssetUseMac}
						</td>
						<td>
							${assetUseList.aAssetUseOS}
						</td>
						<td>
							${assetUseList.aAssetUseSector}
						</td>
						<td>
							${assetUseList.aAssetUseManager}
						</td>
						<td>
							${assetUseList.aAssetUsePhone}
						</td>
						<td></td>
        			</tr>
        		</c:forEach>
	        	<c:if test="${empty assetUseList}">
					<tr>
						<td colspan="100">사용내역이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
	<div style="margin-top:60px;">   
		<div class="tbl_list">
			<table>
				<caption style="text-align: left; margin-bottom:10px;">
					<c:if test="${assetUseInfo.oSignPass eq 'Y' }">
					&nbsp;&nbsp;
					결재 제외<input type="checkbox" id="oPass" name="oPass" class="checkbox"<c:if test="${assetUseInfo.oSignPass eq 'Y' }">checked="checked"</c:if>/>
					</c:if>
				</caption>
				<thead>
					<tr>
						<th colspan="5">결재 정보</th>
					</tr>
					<tr>
						<th style="width: 10%;">결재순서</th>
						<th style="width: 10%;">결재구분</th>
						<th style="width: *%;">결재자</th>
					</tr>
				</thead>
				<tbody id="lineRow3">		
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr>
							<td>
								${i.index + 1}
							</td>
							<td>
								${signList.sSignStaffPosition}
							</td>
							<td style="text-align:left; padding-left:5px; width:20%;">
								${signList.sSignStaffName}
							</td>
							<td>
								<c:if test="${assetUseInfo.sSignStatus eq '승인요청' && 
								              signList.sSignStaffKey eq staffVO.kStaffKey}">
									<select id="sSignDecison" name="sSignDecison" onChange="changeContent(this.value)">
										<option value="승인" selected>승인</option>
										<option value="반려">반려</option>
									</select>
									
									<a class="mes_btn" onclick="setSign(this, event);">사인</a>
									<c:if test="${signList.sSignDecison eq '승인'}">
										<img src="${signList.sSignContent}"/>
									</c:if>
									<c:if test="${signList.sSignDecison eq '반려'}">
										${signList.sSignContent}
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty signList}">
						<tr>
							<td colspan="100">내역이 없습니다.</td>
						</tr>
					</c:if>	
				</tbody>
				
			</table>
		</div>
	</div>	

	
	
	<c:if test="${assetUseInfo.sSignStatus eq '요청등록'}">
		<div class="content" style="padding-top:20px;">
			<div class="content_tit">
				<h2>결재현황</h2>
			</div>
		</div>
		
		<div class="tbl_write">
	        <table>
	        	<thead>
		          	<tr>
						<th style="width:5%; border-left: 1px solid #bfdaf7;">결재순서</th>
						<th style="width:10%;">결재자</th>
						<th style="width:10%;">결정</th>
						<th style="width:10%;">결재여부</th>
						<th style="width:60%;">반려사유 및 싸인</th>
					</tr>
	        	</thead>
		        <tbody>
					<c:forEach var="signList" items="${signList}" varStatus="i">
			          	<tr <c:if test="${signList.sSignStaffKey eq staffVO.kStaffKey && signList.sSignDecison eq '결재대기'}">style="background-color:yellow;"</c:if>>
							<td style="text-align:center; width:5%; padding-left:0px;">
								${i.index + 1}
							</td>
							<td style="text-align:left; padding-left:5px; width:10%;">
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
	</c:if>
	
	<div class="tbl_btn_right">
		<ul>
			<c:if test="${assetUseInfo.sSignStatus eq '등록'}">
			<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="update_go();">수정</a>
				</li>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'}">
				<li>
					<a style="cursor: pointer;" onclick="delete_go();">삭제</a>
				</li>
			</c:if>
			</c:if>
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

