$(document).ready(function() {
	
	/* 모바일 */
	$(".m_logo>ul>li.m_menu").click(function(){
		$("#m_header").stop().animate({left:"0px"});
		
	});
	$(".m_left_top_close>li>a").click(function(){
		$("#m_header").stop().animate({left:"-6000px"});
		
	});
	
	/* admin모바일 */
	$(".m_menu_right").click(function(){
		$("#m_all_nav").stop().animate({left:"0px"});
		
	});
	$("#m_all_nav>.m_nav>.m_header_img>li.close").click(function(){
		$("#m_all_nav").stop().animate({left:"-6000px"});
		
	});
	

	$(".m_gnb>ul>li>a").click(function(){
		$(".m_gnb ul ul").slideUp();
			if(!$(this).next("ul").is(":visible"))
			{
				$(this).next("ul").slideDown();
			}
	});

	

	/* 어드민 왼쪽메뉴 */
	$("#lnb_box>ul.lnb>li>a").filter(function() {
	    return this.href === document.location.href;
	}).addClass("over");
	
	
	/* 어드민 서브메뉴 */
	$(".lnb>li>a, .gnb>li>a").filter(function() {
	    return this.href === document.location.href;
	}).addClass("active");
	
	
	
	/* 어드민 큰 메뉴 */
	$(".gnb>li>a").filter(function() {
	    return this.href === document.location.href;
	}).addClass("move");
	
	
	
});     

		/*$("ul.gnb_sub li a").click(function(){
			$(this).addClass("hover");
				
		});
		$("ul.gnb_sub li a").click(function(){
			$(this).removeClass("hover");
				*/
		
/*	$("ul.gnb_sub").css("display","none"); 
	
		$(".gnb_menu>li>a").click(function(){ 
			
			$(".gnb_menu>li>a:first").addClass("selected"); 
			 
				if($(this).next("ul").is(":visible")){
					
					$(this).next("ul").addClass("over").slideUp("slow"); 
					
				} else{
					
					$(".gnb_menu>li>ul.gnb_sub").addClass("over").slideUp("slow");
					$(this).next("ul").addClass("over").slideToggle("slow"); 
				} 
					
			$(".gnb_menu>li>a").removeClass("selected"); 
				
			$(this).addClass("selected"); 
			
			
		});
		
		

	
	$("ul.gnb_sub li a").mouseover(function(){
		$(this).addClass("hover");
			
	});
	$("ul.gnb_sub li a").mouseout(function(){
		$(this).removeClass("hover");
			
	});
	*/
		
		/*$("ul.gnb_sub li a").click(function(){
			$("ul.gnb_sub li a").addClass("hover");
				
		});
			*/	
		
		























/*$('ul li:has(ul)').addClass('has-submenu');
$('ul li ul').addClass('sub-menu');
$('ul.dropdown li').hover(function () {
    $(this).addClass('hover');
}, function () {
    $(this).removeClass('hover');
});
var $menu = $('#menu'), $menulink = $('#spinner-form'), $search = $('#search'), $search_box = $('.search_box'), $menuTrigger = $('.has-submenu > a');
$menulink.click(function (e) {
    $menulink.toggleClass('active');
    $menu.toggleClass('active');
    if ($search.hasClass('active')) {
        $('.menu.active').css('padding-top', '50px');
    }
});
$search.click(function (e) {
    e.preventDefault();
    $search_box.toggleClass('active');
});
$menuTrigger.click(function (e) {
    e.preventDefault();
    var t = $(this);
    t.toggleClass('active').next('ul').toggleClass('active');
});
$('ul li:has(ul)');
$(function () {
    var e = $(document).scrollTop();
    var t = $('.nav_wrapper').outerHeight();
    $(window).scroll(function () {
        var n = $(document).scrollTop();
        if ($(document).scrollTop() >= 50) {
            $('.nav_wrapper').css('position', 'fixed');
        } else {
            $('.nav_wrapper').css('position', 'fixed');
        }
        if (n > t) {
            $('.nav_wrapper').addClass('scroll');
        } else {
            $('.nav_wrapper').removeClass('scroll');
        }
        if (n > e) {
            $('.nav_wrapper').removeClass('no-scroll');
        } else {
            $('.nav_wrapper').addClass('no-scroll');
        }
        e = $(document).scrollTop();
    });
});*/