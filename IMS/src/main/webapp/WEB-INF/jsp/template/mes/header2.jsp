<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<script>
function eCheckLog(name, key, id) {
    window.location.href = "/mes/eLog.do?kStaffKey=" + key + "&kStaffId=" + id;
}

// 파일 다운로드
function fn_egov_downFileHeader() {
    var id = $('#atchFileId').val();
    if (id == "") {
        alert("파일이 존재하지 않습니다");
        return false;
    }
    if (confirm("메뉴얼 다운로드 하시겠습니까?")) {
        window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId=" + id + "&fileSn=0'/>");
    }
}

function viewService() {

}

$(function() {        
	
    sideBar.animate({"left":"0px"}, {
        duration: 200,
        easing: "easeInOutCirc",
        complete: function() {
            $(".container").animate({"width": "80%"}, 200, "easeInOutCirc");
            $(".container").animate({"right": "2%"}, 200, "easeInOutCirc");
        }
    });
    $(".icon .bar").removeClass("notActive").addClass("active");
	
    
    if ($("#myTable").length > 0) {
        if ($("#myTable>tbody").children().length > 0) {
            $("table#myTable").Grid().updateConfig({
                sort: true,
                resizable: true,
                fixedHeader: true,
                height: '500px'
            }).forceRender();
        } else {
            $("table#myTable").Grid().updateConfig({}).forceRender();
        }

        $(document).on("click", ".gridjs-tr", function() {
            var view = new viewService();
            if (this.childNodes[0].innerText != "조회 정보가 없습니다." && this.childNodes[0].innerText != "No matching records found") {
                if (typeof(view.go_view) != "undefined") {
                    view.go_view(this);
                }
            }
        });
    }
});

$(function() {
    $("#myTable").remove();
    $(".gridjs-notfound").text("조회 정보가 없습니다.").css("text-align", "center");
});

function tdBlock(idx) {
    $(".gridjs-tr").each(function(index, item) {
        if (this.childNodes[0].innerText != "조회 정보가 없습니다." && this.childNodes[0].innerText != "No matching records found") {
            var td = this.childNodes[idx];
            td.onclick = function(event) {
                event.cancelBubble = true;
            }
        }
    });
}

var thList = new Array();

for (var i = 0; i < 20; i++) {
    thList[i] = "";
}

function replaceInput(idx) {
    var build = $(".gridjs-th-content").eq(idx)[0].childNodes[0];
    $(".gridjs-th-content").eq(idx).empty().html(build.data.replaceAll('\n', '').replaceAll('\t', ''));
    thList[idx] = build.data.replaceAll('\n', '').replaceAll('\t', '');

    $(".gridjs-th-content").eq(idx).parent().on("click", function() {
        var build = this.childNodes[0];
        build.innerHTML = thList[idx];
    });
}

console.clear();

// 메뉴 아이콘 클릭
var mobile__menuBtn = $('.mobile-header > .m-top-bar > .m-menu-btn');

mobile__menuBtn.click(function() {
    $(this).parent().siblings('.m-menu-1').toggleClass('active');
});

// 메뉴 클릭
var mobile__menuList = $('.mobile-header > .m-menu-1 ul > li');

mobile__menuList.click(function(e) {
    if ($(this).hasClass('active')) {
        $(this).removeClass('active');
    } else {
        $(this).siblings().removeClass('active');
        $(this).addClass('active');
    };
    e.stopPropagation();
});

$('.gnb_menu .menu_item').click(function() {
    var submenu = $(this).next('.gnb_sub');
    var isActive = $(this).hasClass('active');

    // Store the state and index of the clicked sub-menu
    sessionStorage.setItem('submenuIndex', isActive ? -1 : $(this).index());
    sessionStorage.setItem('submenuState', isActive ? 'closed' : 'open');
});

//Restore the state of sub-menus on page load
var submenuIndex = sessionStorage.getItem('submenuIndex');
var submenuState = sessionStorage.getItem('submenuState');

if (submenuIndex !== null && submenuState !== null) {
    // Check if there's a stored state and index
    var $subMenus = $('.mobile-header > .m-menu-1 ul > li');

    if (submenuIndex >= 0 && submenuIndex < $subMenus.length) {
        // Restore the state of the corresponding sub-menu
        var $clickedMenu = $subMenus.eq(submenuIndex);
        var submenu = $clickedMenu.children('.gnb_sub');

        if (submenuState === 'open') {
            submenu.slideDown();
            $clickedMenu.addClass('active');
        } else {
            submenu.slideUp();
            $clickedMenu.removeClass('active');
        }
    }
}

function approvalPop(){
	 var checkbox = $('#oPass');
    if (checkbox.prop('checked')) {
    	if(confirm("결재 제외로 선택되었습니다.\n결재자를 선택하시겠습니까?")){
    		checkbox.prop('checked', false);
    		$("#oSignPass").val("N");
    	}else{
    		return;
    	}
    }
    var kStaffKey = "";
	var gubun = "";
	var preKStaffKey = "";
	var sUrl = "/mes/sign/popup/kw_signStaff_lf.do";
	window.open(sUrl, "AddrAdd", "width=1000, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
}

var referIndex = 0;
function reCirSet(obj){
	//결재순서
	var lnTmp = document.getElementsByName("sSignStaffKey").length;
	
	var innerStr = "";
	
	// 행삭제
	innerStr += "	<tr>";
	innerStr += "		<td>";
	innerStr += "			<a class='del' onclick=\"delRowTwo(this);\">X</a>";

	innerStr += "			<input type='hidden' id='sSignStaffKey_"+referIndex+"' name='sSignStaffKey' value='"+(obj.kStaffKey)+"'/>";
	innerStr += "			<input type='hidden' id='sSignStaffPosition_"+referIndex+"' name='sSignStaffPosition' value='"+(obj.kPositionName)+"'/>";
	innerStr += "			<input type='hidden' id='sSignStaffName_"+referIndex+"' name='sSignStaffName' value='"+(obj.kStaffName)+"'/>";
	innerStr += "			<input type='hidden' id='sSignSequence_"+referIndex+"' name='sSignSequence' value='"+(Number(lnTmp)+1)+"'/>";
	innerStr += "			<input type='hidden' id='sSignStaffGubun_"+referIndex+"' name='sSignStaffGubun' value='"+(obj.gubun)+"'/>";
	innerStr += "		</td>";
	// 순번
	innerStr += "		<td>"
	innerStr += "			<span id='sn_sp_"+referIndex+"' class='sn_sp'>"+(Number(lnTmp)+1)+"</span>";
	innerStr += "		</td>";
	// 종류
	innerStr += "		<td>"
		innerStr += "			<span id='sn_sp_"+referIndex+"' class='sn_sp'>"+obj.gubun+"</span>";
	innerStr += "		</td>";		
	// 이름
	innerStr += "		<td>";
	innerStr += "			"+(obj.kPositionName)+" / "+(obj.kClassName)+" / "+(obj.kStaffName)+"";
	innerStr += "		</td>";
	innerStr += "	</tr>";
	
	$(innerStr).appendTo("#lineRow3");		
	
	referIndex++;
}

function deleteGyeoljaeList(){
	$('#lineRow3').empty();
}

function handleOPassClick() {
	// 체크박스의 상태를 직접 변수에 저장
    var isChecked = $("#oPass").is(":checked");
    if(isChecked){
    	 $("#oSignPass").val("Y");
    		var elements = document.getElementsByName("sSignStaffKey");
	        if (elements.length > 0) {
	            if (confirm("선택한 결재자 정보를 삭제고 \n결재 제외처리 하시겠습니까?")) {
	                $('#lineRow3').empty();
	            } else {
	            	$("#oPass").prop('checked', false);
	            	 $("#oSignPass").val("N");
	                return; 
	            }
	        }
    } else {
        $("#oSignPass").val("N");
    }
}
</script>

<style>
body, ul, li { margin:0; padding:0;list-style:none;}
body {word-break:keep-all;}
a {text-decoration:none; color:inherit;}
html {overflow-x:hidden; line-height:1.15;}
.con { max-width:1400px;margin:0 auto;}
.row::after {content:"";display:block;clear:both;}
.cell { float:left; box-sizing:border-box;}
.cell-right { float:right;}
.line-height-0 { line-height:0;}
.line-height-0 > * { line-height:normal;}
.visible-on {display:block;}
.visible-off { display:none;}
.img-box {overflow:hidden;}
.img-box > img { width:100%;display:block;}
.gnb_sub { display: none;}
.menu_item.active {font-weight: 600;}
.circle2 {width: 1px; height: 1px; border: 2px solid #ccc;border-radius: 50%; margin-right: 10px;}  


</style>
    
<c:if test="${menu.key eq 0}">
	<c:set target="${menu}" property="key" value="${user.menuKey}" />
</c:if>


 
<!-- web -->

<div id="header_wrap"> 

<div class="header pc-visible-on">
     <div class="top-bar line-height-0">
     
<!-- 햄버거메뉴123 -->
	<div class="table">
		<div class="icon-wrapper">
			<div class="icon">
				<div class="bar one"></div>
				<div class="bar two"></div> 
				<div class="bar three"></div>
			</div> 
		</div> 
		<div class="logo">
		      <c:if test="${staff.comLogImg ne '' && staff.comLogImg ne null}">
			    	<a href="/mes/main.do">
						<img src='/cmm/fms/getImage.do?atchFileId=${staff.comLogImg}&fileSn=0'  alt="로고" onerror="this.style.display='none';" />						
					</a>
				</c:if> 
				<c:if test="${staff.comLogImg eq ''}">
					<a href="/mes/main.do">
						설정된 이미지가 없습니다.
					</a>
				</c:if>    
			</div>
	</div>
	<div>
          <ul class="header_login">
		         <li><a>${formatDate }</a></li>
		         <li><a>Mypage</a></li>
		         <li><a href="/mes/myPage/kw_myPage_lf.do" onclick="eCheckLog('${staff.kStaffName}','${staff.kStaffKey}','${staff.kStaffId}')"><c:out value='${staff.kStaffName}' />님</a></li>
				 <li><a href="/mes/logout.do">LOGOUT</a></li>  
     		</ul>
   	</div>
	
	
<nav id="side-nav">
<div class="gnb">   
    <ul class="gnb_menu">
        <c:forEach var="item" items="${mesTopMenu}" varStatus="i">
            <li>
             
                <a href="#" <c:if test="${item.root eq root}"> class="menu_item active"</c:if><c:if test="${item.root ne root}"> class="menu_item"</c:if>>  
                   <span><c:out value="${item.name}" /></span> 
                </a>
                <ul class="gnb_sub"  <c:if test="${item.root eq root}"> style="display: block;"</c:if>>
                    <c:forEach var="sub" items="${mesAllRefMenu}" varStatus="j">
                        <c:if test="${item.key eq sub.ref}">
                            <li>    
                                <a href="/mes/webMenu.do?key=<c:out value='${sub.key}' />&root=<c:out value='${sub.root}' />">
                                 <div class="circle2"> </div>  ${sub.name} 
                                </a>                                
                            </li>
                        </c:if>
                    </c:forEach>    
                </ul>
            </li>
        </c:forEach>    
    </ul>
</div>




<!-- //햄버거메뉴 -->
</nav>  
<script>
var nav = $("#side-nav ul li");
var contents = $("#contents > div");
var sideBar = $("#side-nav");

$(".icon").click(function(){
    if($(".icon .bar").hasClass("active")) {
        sideBar.animate({"left":"-300px"}, {
            duration: 200,
            easing: "easeInOutCirc",
            complete: function() {
                $(".container").animate({"width": "100%"}, 200, "easeInOutCirc");
                $(".container").animate({"right": "0"}, 200, "easeInOutCirc");
/*                 $(".icon-wrapper")[0].style.removeProperty("left");
                $(".icon-wrapper").animate({"right": "-200px"}, 200, "easeInOutCirc");
                $(".icon-wrapper").css("text-align", "center"); */
            } 
        });
        $(".icon .bar").removeClass("active").addClass("notActive");
    } else {
        sideBar.animate({"left":"0px"}, {
            duration: 200,
            easing: "easeInOutCirc",
            complete: function() {
                $(".container").animate({"width": "80%"}, 200, "easeInOutCirc");
                $(".container").animate({"right": "2%"}, 200, "easeInOutCirc");
/*                 $(".icon-wrapper")[0].style.removeProperty("right");
                $(".icon-wrapper").animate({"left": "0"}, 200, "easeInOutCirc");
                $(".icon-wrapper").css("text-align", "left"); */
            }
        });
        $(".icon .bar").removeClass("notActive").addClass("active");
    }
});




$(".menu_item").click(function(){
    // 클릭된 메뉴 아이템의 하위 메뉴
    var submenu = $(this).next(".gnb_sub");

    // 현재 클릭된 메뉴 아이템이 활성화되어 있는지 확인
    var isActive = $(this).hasClass("active");

    // 모든 하위 메뉴 닫기
    $(".gnb_sub").slideUp();
    // 모든 메뉴 아이템의 활성화 클래스 제거
    $(".menu_item").removeClass("active");

    // 클릭된 메뉴 아이템이 활성화되어 있지 않은 경우
    if (!isActive) {
        // 클릭된 메뉴 아이템의 하위 메뉴 열기
        submenu.slideDown();
        // 클릭된 메뉴 아이템에 활성화 클래스 추가
        $(this).addClass("active");
    }
});
</script> 



<%-- 			<div class="logo">
		      <c:if test="${staff.comLogImg ne ''}">
			    	<a href="/mes/main.do">
						<img src='/cmm/fms/getImage.do?atchFileId=${staff.comLogImg}&fileSn=0'  alt="로고"/>
					</a>
				</c:if> 
				<c:if test="${staff.comLogImg eq ''}">
					<a href="/mes/main.do">
						설정된 이미지가 없습니다.
					</a>
				</c:if>    
			</div>    --%>
			
<%--  <div class="menu-1">	
				<ul class="row">
					<c:forEach var="item" items="${mesTopMenu}" varStatus="i">
						<li class="cell">
						
						<a  class="<c:if test="${item.root eq root}">move</c:if><c:if test="${item.root ne root}"></c:if>"  href="/mes/webMenu.do?key=<c:out value='${item.key}' />&root=<c:out value='${item.root}' />">
							<c:out value="${item.name}" /> 
						</a>
						
								<ul>
									<c:forEach var="sub" items="${mesAllRefMenu}" varStatus="j">
										<c:if test="${item.key eq sub.ref}">	
												<li>
													<a class="fromLeft" href="/mes/webMenu.do?key=<c:out value='${sub.key}' />&root=<c:out value='${sub.root}' />">
														${sub.name}
													</a>								
												</li>
										</c:if>
									</c:forEach>	
								</ul>
						</li>
					</c:forEach>	
				</ul>
			</div>
      		   <ul class="header_login">
			         <li><a>${formatDate }</a></li>
			         <span></span>
			         <li><a href="/mes/myPage/kw_myPage_lf.do" onclick="eCheckLog('${staff.kStaffName}','${staff.kStaffKey}','${staff.kStaffId}')"><c:out value='${staff.kStaffName}' />님</a></li>
					 <span></span>
					 <li><a href="/mes/logout.do">LOGOUT</a></li>  
      		   </ul> --%>
		</div>
	</div>




</div>






<!-- 모바일 -->

	<div id="m_header_wrap">
		 <!--logo-->
			<div class="m_logo">
				<ul>
					<li class="logo_img">
					<c:if test="${staff.comLogImg ne ''}">
					    	<a href="/mes/main.do">
								<img src='/cmm/fms/getImage.do?atchFileId=${staff.comLogImg}&fileSn=0'  alt="로고" onerror="this.style.display='none';" />
							</a>
					</c:if> 
					<c:if test="${staff.comLogImg eq ''}">
							<a href="/mes/main.do">
								설정된 이미지가 없습니다.
							</a>
					</c:if>    
					</li> 
					<li class="m_menu">
						<a>
							<img src="/images/btn/site_map.gif" alt="모바일메뉴" style="margin-top: 9px;"/>
						</a>
					</li>
					
				</ul>
			</div>
			
			
		<div id="m_header">
			
			<div class="m_left_top">	
				<ul class="m_left_top_bottom">
					<li><span style="color: #CC0000">${formatDate }</span></li>
					<li><a><c:out value='${staff.kStaffName}' />님</a></li>
					<li><a href="/mes/logout.do">LOGOUT</a></li>
					<li class="close">
						<a href="#">
							<img src="/images/btn/close.png" width="22" height="21" />
						</a>
					</li>
				</ul>
			</div>
			
			<div class="m_gnb">	
					<ul class="m_gnb_menu">
							<c:forEach var="item" items="${mesTopMenu}" varStatus="i">
								<li>
									<a>
										<c:out value="${item.name}" />
									</a>
										<ul class="m_gnb_sub">
											<c:forEach var="sub" items="${mesAllRefMenu}" varStatus="j">
												<c:if test="${item.key eq sub.ref}">
													<li>	
														<a  href="/mes/webMenu.do?key=<c:out value='${sub.key}' />&root=<c:out value='${sub.root}' />">
															${sub.name}
														</a>								
													</li>
												</c:if>
											</c:forEach>	
										</ul>
								</li>
							</c:forEach>	
						</ul>
					</div>
			</div>
	</div>

		
	


