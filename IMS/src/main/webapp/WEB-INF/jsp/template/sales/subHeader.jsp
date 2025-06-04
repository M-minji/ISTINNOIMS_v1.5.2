<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>




<!-- div id='quick' style='position:absolute; z-index:2; top: 0; right:0; width:100px; border:0px solid black;'>
	<ul id="Contentright">	          
		<li><a href="#"><img src="/images/ican/sub_right_01_25.jpg" alt="제품문의"></a></li>
		<li><a href="#"><img src="/images/ican/sub_right_01_27.jpg" alt="오시는길"></a></li>
		<li><a href="#"><img src="/images/ican/sub_right_01_29.jpg" alt="자료실"></a></li>
		<li><a href="#"><img src="/images/ican/sub_right_01_31.jpg" alt="TOP"></a></li>		         
	</ul>
</div> 

<script type="text/javascript">	
var quick_menu = $('#quick');
var quick_top = 350;


/* quick menu initialization */
quick_menu.css('top', $(window).height() );
$(window).resize(function() {
 quick_menu.stop();
 quick_menu.animate( { "right": $('#sub_container2').offset().left -300 - $('#quick').width()+"px" }, 500 ); 
});

$(document).ready(function(){
// quick_menu.animate( { "top": $(document).scrollTop() + quick_top +"px" }, 500 ); 
// quick_menu.animate( { "right": $('#center_33').offset().left -300 - $('#quick').width()+"px" }, 500 );  

quick_menu.css('top', $(document).scrollTop() + quick_top +"px" );
quick_menu.css('right', $('#sub_container2').offset().left -300 - $('#quick').width()+"px");

 $(window).scroll(function(){
  quick_menu.stop();
  quick_menu.animate( { "top": $(document).scrollTop() + quick_top + "px" }, 1000 );
 });
});
</script> 
 -->

<div id="sub_container" >
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
     <tr>
      <td height="190" align="center"><img src="./images_enp/img/sub_img.jpg" /></td>
     </tr>
     <tr>
     	<td height="30"></td>
     </tr>
  </table>  
</div>



