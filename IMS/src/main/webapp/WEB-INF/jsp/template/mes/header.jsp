<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setHeader("X-Content-Type-Options", "nosniff");
	response.setHeader("Content-Security-Policy", "script-src 'self';");
%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>

<script>
function replaceSpecialCharacters(str) {
    if (!str) return ""; // 빈 문자열 처리

    // 특수문자와 대응하는 HTML 엔티티 매핑
    const entityMap = {
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        "\"": "&quot;",
        "'": "&apos;", // 또는 &#39; (HTML5에서 &apos; 사용 가능)
        "/": "&#x2F;",
        "©": "&copy;",
        "®": "&reg;",
        "™": "&trade;",
        "€": "&euro;",
        "£": "&pound;",
        "¥": "&yen;",
        "¢": "&cent;",
        "§": "&sect;",
        "¶": "&para;",
        "•": "&bull;",
        "…": "&hellip;",
        "°": "&deg;",
        "±": "&plusmn;",
        "÷": "&divide;",
        "×": "&times;",
        "‰": "&permil;",
        "†": "&dagger;",
        "‡": "&Dagger;",
        "←": "&larr;",
        "↑": "&uarr;",
        "→": "&rarr;",
        "↓": "&darr;",
        "↔": "&harr;",
        "⇒": "&rArr;",
        "⇔": "&hArr;",
        "∀": "&forall;",
        "∂": "&part;",
        "∃": "&exist;",
        "∅": "&empty;",
        "∇": "&nabla;",
        "∈": "&isin;",
        "∉": "&notin;",
        "∋": "&ni;",
        "∏": "&prod;",
        "∑": "&sum;",
        "−": "&minus;",
        "∗": "&lowast;",
        "√": "&radic;",
        "∝": "&prop;",
        "∞": "&infin;",
        "∠": "&ang;",
        "∧": "&and;",
        "∨": "&or;",
        "∩": "&cap;",
        "∪": "&cup;",
        "∫": "&int;",
        "∴": "&there4;",
        "∼": "&sim;",
        "≅": "&cong;",
        "≈": "&asymp;",
        "≠": "&ne;",
        "≡": "&equiv;",
        "≤": "&le;",
        "≥": "&ge;",
        "⊂": "&sub;",
        "⊃": "&sup;",
        "⊆": "&sube;",
        "⊇": "&supe;",
        "⊕": "&oplus;",
        "⊗": "&otimes;",
        "⊥": "&perp;",
        "⋅": "&sdot;",
        "⌈": "&lceil;",
        "⌉": "&rceil;",
        "⌊": "&lfloor;",
        "⌋": "&rfloor;",
        "⟨": "&lang;",
        "⟩": "&rang;",
        "◊": "&loz;",
        "♠": "&spades;",
        "♣": "&clubs;",
        "♥": "&hearts;",
        "♦": "&diams;"
    };

    // 정규식을 사용하여 특수문자를 대응하는 엔티티로 변환
    return str.replace(/[&<>"'\/©®™€£¥¢§¶•…°±÷×‰†‡←↑→↓↔⇒⇔∀∂∃∅∇∈∉∋∏∑−∗√∝∞∠∧∨∩∪∫∴∼≅≈≠≡≤≥⊂⊃⊆⊇⊕⊗⊥⋅⌈⌉⌊⌋⟨⟩◊♠♣♥♦]/g, function (specialChar) {
        return entityMap[specialChar] || specialChar;
    });
}
$(function(){
	const links = document.querySelectorAll('a');

    links.forEach((link) => {
        link.addEventListener("click", function (event) {
        	const inputs = document.querySelectorAll("input");
    	    inputs.forEach((input) => {
    	        input.value = replaceSpecialCharacters(input.value);
    	    });
        });
    });
	if($(".myTable").length > 0){
		$(".myTable").each(function () {
	        var $table = $(this);
	        
	    	// 각 요소의 속성을 저장하는 함수
	        function saveAttributes(element) {
	            if (element && element.length > 0) {
	            	var styles = {};
	                $.each(element.get(0).style, function(i, name) {
	                    styles[name] = element.css(name);
	                });
	                return {
	                    id: element.attr('id'),
	                    colspan: element.attr('colspan'),
	                    attrs: element.prop("attributes"),
	                    styles: styles
	                };
	            } else {
	                return null;
	            }
	        }

	        // 모든 요소들의 속성 저장
	        var tableAttributes = saveAttributes($table);
	        var theadAttributes = saveAttributes($table.find('thead'));
	        var tbodyAttributes = saveAttributes($table.find('tbody'));
	        var trAttributes = [];
	        var tdAttributes = [];

	        $table.find('tr').each(function (index) {
	            trAttributes[index] = saveAttributes($(this));
	            tdAttributes[index] = [];
	            $(this).find('td').each(function (tdIndex) {
	                tdAttributes[index][tdIndex] = saveAttributes($(this));
	            });
	        });
	        
	        if ($table.find("tbody").children().length > 0) {
	            // Grid.js 적용 및 설정 업데이트
	            var grid = $table.Grid().updateConfig({
	                sort: true,
	                resizable: true
	            });
	        } else {
	            var grid = $table.Grid().updateConfig({});
	        }

	        // Grid.js 렌더링
	        grid.forceRender();

	        // 기존 속성 유지
	        setTimeout(function () {
	            var gridTable = $table.next('div').find('table');
	            gridTable.find('tr').each(function (index) {
	                var $tr = $(this);
	                var trAttr = trAttributes[index];
	                if (trAttr) {  // trAttr이 존재할 때만 적용
	                    if (trAttr.id) $tr.attr('id', trAttr.id);
	                    $.each(trAttr.styles, function(name, value) {
	                        $tr.css(name, value);
	                    });

	                    $tr.find('td').each(function (tdIndex) {
	                        var $td = $(this);
	                        if (tdAttributes[index] && tdAttributes[index][tdIndex]) {  // tdAttr이 존재할 때만 적용
	                            var tdAttr = tdAttributes[index][tdIndex];
	                            if (tdAttr.id) $td.attr('id', tdAttr.id);
	                            if (tdAttr.colspan) $td.attr('colspan', tdAttr.colspan);
	                            $.each(tdAttr.styles, function(name, value) {
	                                $td.css(name, value);
	                            });
	                        }
	                    });
	                }
	            });
	        }, 100);
	    });
	}
});

function eCheckLog(name, key, id) {
    window.location.href = "/mes/eLog.do?kStaffKey=" + key + "&kStaffId=" + id;
}

 
function viewService() {

}

$(function() {        
    $(".icon .bar").removeClass("notActive").addClass("active");
    $(".container").css({
        "flex": "1",
        "max-width": "calc(99% - 350px)",
        "margin-right": "4%"
    });
    
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

$(window).resize(function() {
    // 화면 너비가 1200px 이하일 때
    if ($(window).width() <= 1200) {
        $(".container").css({
            "width": "99%",
            "max-width": "99%",
            "margin-right": marginValue,
            "margin-left": "0"
        });

        $(".left-content").css({
            "width": "100%",
            "margin-right": "20px",
            "margin-left": "0"
        });
    } else {
   	   let marginValue = "2%";
        // 화면 너비가 1200px 이상일 때
        $(".container").css({
            "width": "auto",
            "max-width": "calc(100% - 400px)",
            "margin-right": "3%",
            "margin-left": marginValue
        });

        $(".left-content").css({
            "width": "380px",
            "margin-right": "20px",
            "margin-left": "0"
        });
    }
});

// 페이지 로드 시 초기 상태로 리사이즈 트리거
$(window).trigger('resize');


$(function() {
    $("#myTable").remove();
    $(".gridjs-notfound").text("조회 정보가 없습니다.").css({
        "text-align": "center",
        "cursor": "default"
    });
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
	            if (confirm("선택한 결재자 정보를 삭제하고 \n결재 제외처리 하시겠습니까?")) {
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
var eObjBbn = "";
var eObjId = "";
function selectWorkerPop(gbn, id){
	eObjBbn =gbn;
	eObjId =id;
	var sUrl = "";
	if(eObjBbn == "R"){
	  sUrl = "/mes/contact/kw_info_list.do"; //요청자
	}else{
	  sUrl = "/mes/user/kw_user_list.do";
	}
	if(sUrl != ""){
		window.open(sUrl, "AddrAdd", "width=1400, height=650, status=no, toolbar=no, resizable=yes, menubar=no, location=no, scrollbars=yes");
		window.focus();
	}
	
}
function setReturnTextPop(obj){
// 	alert($("#" + eObjId).length);
	 if ($("#" + eObjId).length > 0) {
		$("#"+eObjId).val(obj.eName);
	 }
	 if ($("#" + eObjId +"Org").length > 0) {
		 $("#"+eObjId+"Org").val(obj.ePosition);
		 $("#"+eObjId+"OrgTxt").html(obj.ePosition);
	 }
};
</script>

<style>
body, ul, li { margin:0; padding:0;list-style:none;}
body {word-break:keep-all;}
a {text-decoration:none; color:inherit;}
html {overflow-x:auto; line-height:1.15;}
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
	    <div class="top-bar">
	    	<p>${formatDate}</p>
			<ul class="header_login">
				<li class="user"><a href="/mes/myPage/kw_myPage_lf.do" onclick="eCheckLog('${staff.kStaffName}','${staff.kStaffKey}','${staff.kStaffId}')"><c:out value='${staff.kStaffName}' />님</a></li>
				<li class="logout"><a href="/mes/logout.do">로그아웃</a></li>  
			</ul>
		</div>
		<nav id="side-nav">
			<div class="logo">
		      <c:if test="${staff.comLogImg ne '' && staff.comLogImg ne null}">
			    	<a href="/mes/main.do">
						<img src='/cmm/fms/getImage.do?atchFileId=${staff.comLogImg}&fileSn=0'  alt="로고" onerror="this.src='/images/img/ico_h1_logo.svg'"/>						
					</a>
				</c:if> 
				<c:if test="${staff.comLogImg eq ''}">
					<a href="/mes/main.do">
						<img src='/images/img/ico_h1_logo.svg'  alt="로고" />
					</a>
				</c:if>    
			</div>
			<div class="gnb">   
		         <ul class="gnb_menu">
		         	<li><a	href="/mes/main.do" class="dashboard no_sub">Dashboard</a></li>
		             <c:forEach var="item" items="${mesTopMenu}" varStatus="i">
		                 <li>
		                     <a href="#" class="menu_item menu_key_${item.key} <c:if test="${item.root eq root}">active</c:if>">  
		                         <span><c:out value="${item.name}" /></span> 
		                     </a>
		                     <ul class="gnb_sub" style="<c:if test='${item.root eq root}'>display: block;</c:if>">
		                         <c:forEach var="sub" items="${mesAllRefMenu}" varStatus="j">
		                             <c:if test="${item.key eq sub.ref}">
		                                 <li id='${sub.key}'  class='<c:if test="${sub.key eq key}">active</c:if>'   >    
		                                     <a href="/mes/webMenu.do?key=<c:out value='${sub.key}' />&root=<c:out value='${sub.root}' />">
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
		 </nav>
	</div>
</div>

<script>
var nav = $("#side-nav ul li");
var contents = $("#contents > div");
var sideBar = $("#side-nav");

$(".icon").click(function(){
    if($(".icon .bar").hasClass("active")) {
        sideBar.animate({"left":"0px"}, {
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

// 특수문자를 검증하는 함수
function validateInput(event) {
    // 금지할 특수문자 목록
    const forbiddenChars = /[<>"'/]/g;

    // 현재 입력 값
    const inputElement = event.target;
    const currentValue = inputElement.value;

    // 금지된 문자가 포함되었는지 확인
    if (forbiddenChars.test(currentValue)) {
        // 경고 메시지 표시
        alert("입력할 수 없는 특수문자가 포함되어 있습니다: <, >, \", ', /");
        
        // 금지된 문자를 제거
        inputElement.value = currentValue.replace(forbiddenChars, '');
    }
}

// 모든 input 요소에 이벤트 리스너 추가
function attachValidation() {
	//const inputs = document.querySelectorAll("input.validate"); // validate 클래스만 검증
    const inputs = document.querySelectorAll("input");
    inputs.forEach(input => {
        input.addEventListener("input", validateInput);
    });
}

// 페이지 로드 후 이벤트 리스너를 모든 input에 연결
// window.onload = attachValidation;

function validateInput(event) {
    const invalidChars = ['<', '>', '"', "'", '&'];
    let value = event.target.value;

    if (invalidChars.some(char => value.includes(char))) {
        alert('특수문자는 입력할 수 없습니다.\n(사용할 수 없는 특수 문자: <, >, ", \', &)');
       
        event.target.value = value.replace(/[<>"'&]/g, '');
    }
}

function bindValidationToInputs(parent) {
    const inputs = parent.querySelectorAll('input');
    inputs.forEach(input => {
        if (!input.dataset.validationBound) { // 중복 바인딩 방지
            input.addEventListener('input', validateInput);
            input.dataset.validationBound = true; // 바인딩 여부 표시
        }
    });
}

function observeDOMChanges() {
    const observer = new MutationObserver(mutationsList => {
        mutationsList.forEach(mutation => {
            if (mutation.type === 'childList' && mutation.addedNodes.length > 0) {
                mutation.addedNodes.forEach(node => {
                    if (node.nodeType === 1) { // 요소 노드인지 확인
                        if (node.tagName === 'INPUT') {
                            bindValidationToInputs(node.parentElement);
                        } else {
                            bindValidationToInputs(node);
                        }
                    }
                });
            }
        });
    });

    observer.observe(document.body, { childList: true, subtree: true });
}

// 페이지 로드 시 초기 바인딩 및 동적 감지 시작
window.onload = () => {
    bindValidationToInputs(document.body); // 기존 input에 바인딩
    observeDOMChanges(); // 동적으로 추가되는 input에 바인딩
    
//     console.log($.fn.jquery);
};



</script> 
		
	


