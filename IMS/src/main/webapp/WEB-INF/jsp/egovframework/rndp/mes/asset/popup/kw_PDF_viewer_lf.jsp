<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="/css/mes/popup.css" rel="stylesheet" type="text/css" />
<link href="/css/mes/print.css" rel="stylesheet" media="print" type="text/css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="<c:url value='/js/html2canvas.js'/>"></script>
<script src="/js/jquery/jquery-barcode.js"></script>

<script type="text/javascript">
$(document).ready(function(){	
	if("${eDeviceImgExtension}" == "pdf"){
		
    var pdfUrl = "/cmm/fms/PDFView.do?atchFileId=${eDeviceImgId}&fileSn=0";
//     var pdfUrl = "/cmm/fms/PDFView.do?atchFileId=FILE_000000000001380&fileSn=0";
    $('#pdfViewer').attr('src', pdfUrl);
    $('#pdfViewer').show();
    $('#eImgViewer').hide();
	}else{
	var imageUrl = '/cmm/fms/getImage.do?atchFileId=${eDeviceImgId}&fileSn=0';	
    	$('#eImgViewer').attr('src',imageUrl);
    	$('#eImgViewer').show();
    	$('#pdfViewer').hide();
	}
}); 	
function eDownload(){
	 var eDeviceImgId  = $('#eDeviceImgId').val();
	 if(confirm("이미지를 다운로드 하시겠습니까?")){
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+eDeviceImgId+"&fileSn=0'/>");
		}
}
</script>

<form id="frm" name="frm" method="post" enctype="multipart/form-data">
    <div class="pop_head">
        <div id="pop_head">
            <div class="tit">
                <h3>PDF Viewer Pop</h3>
            </div>
            <a href="javascript:self.close();"><img src="/images/btn/close.gif" width="22" height="21" /></a>
        </div>  
    </div>
    
<!--     <div class="tbl_btn_right"> -->
<!--         <ul> -->
<!--             <li> -->
<!--             </li> -->
<!--         </ul> -->
<!--     </div> -->
<!--     <div id="print_div"> -->
    
<!--         <div class="total_inspect_pt"> -->
<!--             <div class="inspect_pt"> -->
                <iframe id="pdfViewer" width="100%" height="1200px;" frameborder="0"></iframe>
                <img id="eImgViewer"  style="display: none;width: 100%;height: 800px;"  src="" onclick="eDownload();">
                <input type="hidden" id="eDeviceImgId" value="${eDeviceImgId}"> 
<!--             </div>       -->
<!--         </div> -->
    
<!--     </div> -->
</form>