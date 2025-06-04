<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript">
function delete_go(){
	if(confirm("삭제하시겠습니까?")){
		document.frm.action = "/mes/asset/kw_asset_d.do";
		document.frm.submit();
	}
}
function list_go(){
	document.frm.action = "/mes/asset/kw_asset_lf.do";
	document.frm.submit();
}
function update_go(){
	$('#mloader').show();
	document.frm.action = "/mes/asset/kw_asset_uf.do";
	document.frm.submit();
}


$.fn.tap = function(){
    var self = $(this),
        btn = self.find('.btn_area button');
  
    btn.click(function(){
      var num = $(this).attr('data-num');
      
      btn.removeClass('act');
      $(this).addClass('act');      
      self.find('.tap_item').removeClass('act');
      self.find('.tap_item[data-num="'+ num +'"]').addClass('act');
    });
}
$('.tap').tap();



</script>
<style>
.tabs {
  background-color: #ffffff;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.2);
  width: 100%;
  margin: 50px auto;}

/* 탭 스타일 */
.tab_item {
  width: calc(100%/3 - 2px);
  height: 50px;
  border-bottom: 3px solid #22499d;
  background-color: #f8fafc;
  line-height: 50px;
  font-size: 16px;
  text-align: center;
  color: #333333;
  display: block;
  float: left;
  text-align: center;
  font-weight: bold;
  transition: all 0.2s ease;
  border-top: 1px solid #ddd;
} 

.tabs>label.tab_item:nth-child(3){
border-right: 3px solid #f8fafc;
} 

/* 라디오 버튼 UI삭제*/
input[name="tab_item"] {
  display: none;
}

/* 탭 컨텐츠 스타일 */
.tab_content {
  display: none;
  padding: 40px;
  clear: both;
  overflow: hidden;
  border: 1px solid #ddd;
  border-top: 0;
} 


/* 선택 된 탭 콘텐츠를 표시 */
#all:checked ~ #all_content,
#programming:checked ~ #programming_content,
#design:checked ~ #design_content {
  display: block;
}

/* 선택된 탭 스타일 */
.tabs input:checked + .tab_item {
  background-color: #fff;
  color: #009edb;
  position: relative;
  
}
.tabs input:checked + .tab_item::after{ 
  content: "";
  position: absolute;
  left: 0;
  bottom: -3px;
  width: 100%;
  border-bottom: 3px solid #fff;
   border-top: 3px solid #22499d;
   top: 0;
   border-left: 3px solid #22499d;
   border-right: 3px solid #22499d;
}
</style> 



<form id="frm" name="frm" method="post" enctype="multipart/form-data">
	<input  type="hidden" id="aAssetKey" name="aAssetKey" value="${assetInfo.aAssetKey}" />
	<input type="hidden" name="searchWord" id="searchWord" value="${mesAssetVO.searchWord}">
	<input type="hidden" name="pageIndex" id="pageIndex" value="${mesAssetVO.pageIndex}" />
	<input type="hidden" name="recordCountPerPage" id="recordCountPerPage" value="${mesAssetVO.recordCountPerPage}" />
	<input type="hidden" name="searchType" id="searchType" value="${mesAssetVO.searchType}" />
		
	<div class="content">	
		<div class="content_tit">
			<h2>자산 상세정보</h2>
		</div>
	</div>
	
	<div class="tbl_write">
		<table>
			<tbody>
				<tr>
					<th>자산번호</th>
					<td>
						${assetInfo.aAssetNumber}
					</td>
					<th>상태</th>
					<td>
						${assetInfo.aAssetStatus}
					</td>
				</tr>
				<tr>
					<th>제조사</th>
					<td>
						${assetInfo.aAssetMaker}
					</td>
					<th>자산명</th>
					<td>
						${assetInfo.aAssetName}
					</td>
				</tr>
				<tr>
					<th>제조번호</th>
					<td>
						${assetInfo.aAssetManufactureNumber}
					</td>
					<th>모델명</th>
					<td>
						${assetInfo.aAssetModel}
					</td>
				</tr>
          		<tr>
            		<th>설치위치</th>
					<td>
						${assetInfo.aAssetIntroducer}
					</td>
            		<th>도입일</th>
					<td>
						${assetInfo.aAssetDate}
					</td>
          		</tr>	
          		<tr>
          			<th>도입원가</th>
            		<td>
						${assetInfo.aAssetCost}원
            		</td>
            		<th>도입목적</th>
					<td>
						${assetInfo.aAssetPurpose}
					</td>
          		</tr>			
          		<tr> 
            		<th>사진</th>
            		<td colspan="3">
            			<c:if test="${not empty assetInfo.aAssetImage}">
						<img style="width:300px; height: 300px;" src='/cmm/fms/getImage.do?atchFileId=${assetInfo.aAssetImage}&fileSn=0' onerror="this.style.display='none'"/>
						</c:if>
					</td>    
          		</tr>
          		<tr> 	
          			<th>비고</th>
					<td colspan="3">
						${assetInfo.aAssetEtc}
					</td>	
				</tr>			
			</tbody>
		</table>
	</div>
		
<div class="tabs">
    <input id="all" type="radio" name="tab_item" checked>
    <label class="tab_item" for="all">Tab 1</label>
    <input id="programming" type="radio" name="tab_item">
    <label class="tab_item" for="programming">Tab 2</label>
    <input id="design" type="radio" name="tab_item">
    <label class="tab_item" for="design" style="border-right: 3px solid #f8fafc;">Tab 3</label> 
    
    <div class="tab_content" id="all_content">
        contents1
    </div>
    <div class="tab_content" id="programming_content">
       contents2
	</div>
	    <div class="tab_content" id="design_content">
	        contents3
	</div>
</div>	
		
		
		
		
		
		
	<div class="tbl_btn_right">
		<ul>
		<c:if test="${staffVO.kStaffAuthModifyFlag eq 'T'}">
			<li>
				<a style="cursor: pointer;" onclick="update_go();">
					수정
				</a> 
			</li>
			</c:if>
			<c:if test="${staffVO.kStaffAuthDelFlag eq 'T'}">
			<li>
				<a style="cursor: pointer;" onclick="delete_go();">
					삭제
				</a> 
			</li>
			</c:if>
			<li>
				<!--취소버튼--> 
				<a style="cursor: pointer;" onclick="list_go();">
					목록
				</a>
			</li>
		</ul>
	</div>
</form>
