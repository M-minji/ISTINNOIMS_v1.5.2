<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!--  AROK 새디자인  -->
<script>
$(document).ready(function(){
	var slider = $('.bxslider').bxSlider({
			auto: true
		});
		// 클릭시 멈춤 현상 해결 //
		$(document).on('click','.bx-next, .bx-prev,  #bx-pager1',function() {
		slider.stopAuto();
		slider.startAuto();
		slider_01.stopAuto();
		slider_01.startAuto();
		slider_02.stopAuto();
		slider_02.startAuto();
	});	
});
</script>
<!-- 슬라이드 시작--->
<div id="s_banner_wrap">
	<ul class="bxslider">
		<li><img src="/images_main/rndpmain/slide1.jpg"></li>
      	<li><img src="/images_main/rndpmain/slide2.jpg"></li>
      	<li><img src="/images_main/rndpmain/slide3.jpg"></li>
    </ul>
</div>
<!-- 슬라이드 끝-->

<!-- 사업영역역 -->
<div class="hs_bnwrap">
	<div class="bntxt">
		<h1>RNDP FIELD OF BUSINESS</h1>
		<h3>파트너사의 성공을 위해 RNDP가 함께합니다</h3>
	</div>
	<ul>
		<li><a href="/webMenu.do?key=471&groupKey=1"><img src="/images_main/rndpmain/bn_img1.png" onmouseover="this.src='/images_main/rndpmain/hover_img1.png' "  onmouseout="this.src='/images_main/rndpmain/bn_img1.png' "  alt="그룹웨어"></a>
		<p>그룹웨어</p></li>
		<li><a href="/webMenu.do?key=472&groupKey=1"><img src="/images_main/rndpmain/bn_img2.png" onmouseover="this.src='/images_main/rndpmain/hover_img2.png' "  onmouseout="this.src='/images_main/rndpmain/bn_img2.png' " alt="그룹웨어"></a>
		<p>SMART공장 솔루션</p></li>
		<li><a href="/webMenu.do?key=473&groupKey=1"><img src="/images_main/rndpmain/bn_img3.png" onmouseover="this.src='/images_main/rndpmain/hover_img3.png' "  onmouseout="this.src='/images_main/rndpmain/bn_img3.png' " alt="그룹웨어"></a>
		<p>프로젝트관리 솔루션</p></li>
		<li><a href="/webMenu.do?key=474&groupKey=1"><img src="/images_main/rndpmain/bn_img4.png" onmouseover="this.src='/images_main/rndpmain/hover_img4.png' "  onmouseout="this.src='/images_main/rndpmain/bn_img4.png' " alt="그룹웨어"></a>
		<p>금융시스템 SI</p></li>
	</ul>
</div>

<!-- 커뮤니티영역-->
<div class="hs_cmwrap">
	<h1>RNDP COMMUNITY</h1>
	<div id="cmbox">
	<a class="cmbox1" href="/webMenu.do?key=496&groupKey=1">
		<h3>공지사항</h3>
		<span class="cmtxt1">궁금한 사항을<br>올려주세요<br>언제든지<br>문의가능합니다.</span>
		<span class="cmtxt2">궁금한 사항을 올려주세요! 언제든지 문의가능합니다.</span>
		<img src="/images_main/rndpmain/cm_img1.png">
	</a>
	<a class="cmbox2" href="/webMenu.do?key=494&groupKey=1">
		<h3>인재채용</h3>
		<span class="cmtxt1">창의적 사고와<br>도전하는 열정을<br>가진 인재를<br>기다립니다! </span>
		<span class="cmtxt2">창의적 사고와 도전하는 열정을 가진 인재를 기다립니다!</span>
		<img src="/images_main/rndpmain/cm_img2.png">
	</a>
	<a class="cmbox3" href="/webMenu.do?key=495&groupKey=1">
		<h3>홍보센터</h3>
		<span class="cmtxt1">다양한<br>세미나소식과<br>최신이슈를<br>받아볼수있습니다!</span>
		<span class="cmtxt2">다양한 세미나소식과 최신이슈를 받아볼수있습니다!</span>
		<img src="/images_main/rndpmain/cm_img3.png">
	</a>
	</div>
</div>
<!-- 서비스 영역-->
<div class="hs_svwrap">
	<h1>RNDP SERVICE</h1>
	<div class="sv_img">
			<div class="svbox2">
			<img src="/images_main/rndpmain/sv_img4.jpg">
			<div class="sv_more">
				<p>평일 AM:09:00~PM:18:00</p>
				<p>TEL)042-862-1090~1</p>
			</div>
		</div>
			<div class="svbox3">
			<img src="/images_main/rndpmain/sv_img5.jpg">
			<div class="sv_more">
			<a href="/webMenu.do?key=384&groupKey=1">[ 오시는길 바로가기 ]</a>
			</div>
		</div>
	</div>
</div>


<!-- <body> -->

<!--슬라이드 영역-->

<!-- <div id="slide"> -->
<!-- 	<div id="jssor_1" style="position: relative; top: 0px; left: 0px; width: 1400px; height: 500px; overflow: hidden; visibility: hidden;"> -->

<!-- 		<div data-u="loading" style="position: absolute; top: 0px; left: 0px; background-color: rgba(0, 0, 0, 0.7);"> -->
<!-- 			<div style="filter: alpha(opacity = 70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div> -->
<!-- 		</div> -->
<!-- 		<div id="slides" data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1400px; height: 500px; overflow: hidden;"> -->

<!-- 			<div> -->
<!-- 				<img data-u="image" src="/images_main/main/bg11.jpg" /> -->
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<img data-u="image" src="/images_main/main/bg2-1.jpg" /> -->
<!-- 			</div> -->
<!-- 			<div> -->
<!-- 				<img data-u="image" src="/images_main/main/bg3-1.jpg" /> -->
<!-- 			</div> -->
<!-- 		</div> -->

<!-- 		<div data-u="navigator" class="jssorb05" -->
<!-- 			style="bottom: 16px; right: 16px;" data-autocenter="1"> -->

<!-- 			<div data-u="prototype" style="width: 16px; height: 16px;"></div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!--슬라이드영역 끝-->


<!-- 공지사항 영역-->
<!-- <div class="hsnotice"> -->
<!-- 	<ul> -->
<!-- 		<li><a href="/webMenu.do?key=477"><img src="/images_main/main/notice1.png"></a> -->
<!-- 		<p>공지사항</p></li> -->
<!-- 		<li><a href="/webMenu.do?key=446"><img src="/images_main/main/notice2.png"></a> -->
<!-- 		<p>전문자료</p></li> -->
<!-- 		<li><a href="/webMenu.do?key=454"><img src="/images_main/main/notice3.png"></a> -->
<!-- 		<p>게시판</p></li> -->
<!-- 	</ul> -->
<!-- </div> -->
<!-- 공지사항 영역 끝-->


<!--제품소개-->
<!-- <div id="rndpboxwrap"> -->
<!-- 	<div class="rndpbox"> -->
<!-- 		<div class="rndpbox1"> -->
<!-- 			<h2>RNDP SOLUTION</h2> -->
<!-- 			<ul> -->
<!-- 				<li> -->
<!-- 					<a href="/intra/loginfrm.do"><img src="/images_main/main/img1.png"></a> -->
<!-- 					<p class="title">그룹웨어<EVS></p> -->
<!-- 					<p>신속한업무<br>원활한의사소통</p> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<a href="#"><img src="/images_main/main/img2.png"></a> -->
<!-- 					<p class="title">영업관리</p> -->
<!-- 					<p>편리한 거래처<br>품목,매출,매입관리</p> -->
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 		<div class="rndpbox2"> -->
<!-- 			<h2>RNDP PRODUCT</h2> -->
<!-- 			<ul> -->
<!-- 				<li> -->
<!-- 					<a href="#"><img src="/images_main/main/img3.png"></a> -->
<!-- 					<p class="title">영업관리 시스템<br>&lt;MINI ERP&gt;</p> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<a href="#"><img src="/images_main/main/img4.png"></a> -->
<!-- 					<p class="title">컨텐츠 관리 시스템<br>&lt;CMS&gt;</p> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<a href="#"><img src="/images_main/main/img5.png"></a> -->
<!-- 					<p class="title">전자연구노트<br>환지관리시스템</p> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<a href="#"><img src="/images_main/main/img6.png"></a> -->
<!-- 					<p class="title">프로젝트<br>관리시스템</p> -->
<!-- 				</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!--제품소개 영역 끝-->


<!-- 사업엉역-->
<!-- <div id="bnwrap"> -->
<!-- 	<div class="bnbox"> -->
<!-- 		<p><span>RNDP</span> FIELD OF BUSINESS</p> -->
<!-- 		<p>파트너사의 성공을 위해 RNDP가 함께합니다</p> -->
<!-- 	</div> -->
<!-- 	<div id="hsbn"> -->
<!-- 		<div class="bnbox2"> -->
<!-- 			<p>- 오라클 ERP 커스터 마이징</p> -->
<!-- 			<p>- 전자정부프레잉 관련 SI</p> -->
<!-- 		</div> -->
<!-- 		<div class="bnbox2"> -->
<!-- 			<p>- 웹 포털 및 관리 시스템 개발</p> -->
<!-- 			<p>- 홍보 및 자체 회계감사 시스템 개발</p> -->
<!-- 		</div> -->
<!-- 		<div class="bnbox2"> -->
<!-- 			<p>- 공공기관 내부업무시스템 및 금융권</p> -->
<!-- 			<p>- 업무시스템, 학사 시스템 개발</p> -->
<!-- 		</div> -->
<!-- 		<div class="hsmore"> -->
<!-- 			<a href="#">[자세히보기]</a> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!-- 사업엉역 영역 끝-->


<!-- 문의-->
<!-- <div id="hscall"> -->
<!-- 	<ul> -->
<!-- 		<li><a href="#"><img src="/images_main/main/callimg1.jpg" onmouseover="this.src='/images_main/main/callimg1-1.jpg' " onmouseout="this.src='/images_main/main/callimg1.jpg' " alt="image1"></a></li> -->
<!-- 		<li><a href="/webMenu.do?key=455"><img src="/images_main/main/callimg2.jpg" onmouseover="this.src='/images_main/main/callimg2-1.jpg' " onmouseout="this.src='/images_main/main/callimg2.jpg' " alt="image1"></a></li> -->
<!-- 		<li><a href="/webMenu.do?key=453"><img src="/images_main/main/callimg3.jpg" onmouseover="this.src='/images_main/main/callimg3-1.jpg' " onmouseout="this.src='/images_main/main/callimg3.jpg' " alt="image1"></a></li> -->
<!-- 	</ul> -->
<!-- </div> -->
<!-- 문의 영역 끝-->


<!-- </body> -->














<%-- <script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS --> --%>

<!-- <div class="container" id="maincontainer2"> -->

<!-- 	<div class="cycle-slideshow"  -->
<!-- 		 data-cycle-loader="wait" -->
<!-- 		 data-cycle-speed="400" -->
<!-- 	> -->
<!-- 		<div class="cycle-pager"></div> -->
<!-- 		<img src="/images_main/slide1.jpg" alt="" width="100%"> -->
<!-- 		<img src="/images_main/slide2.jpg" alt="" width="100%"> -->
<!-- 		<img src="/images_main/slide3.jpg" alt="" width="100%"> -->
<!-- 	</div> -->
	
	<!--게시판 영역-->
<!-- 	<div id="noticewrap"> -->
<!-- 	    <div id="notice"> -->
<!-- 	        <a href="/webMenu.do?key=454&groupKey=1"> -->
<!-- 	        	<div class="box1"> -->
<!-- 	        		<img src="/images_main/box1.png" onmouseover="this.src='/images_main/box1-1.png' "  onmouseout="this.src='/images_main/box1.png' "   /> -->
<!-- 	        		<p>자유게시판</p> -->
<!-- 	        	</div> -->
<!-- 	        </a> -->
<!-- 	        <a href="/webMenu.do?key=453&groupKey=1"> -->
<!-- 	        	<div class="box2"> -->
<!-- 	        		<img src="/images_main/box2.png" onmouseover="this.src='/images_main/box2-2.png' "  onmouseout="this.src='/images_main/box2.png' "   /> -->
<!-- 	       	 		<p>공지사항</p> -->
<!-- 	        	</div> -->
<!-- 	        </a> -->
<!-- 	        <a href="/webMenu.do?key=455&groupKey=1"> -->
<!-- 	        	<div class="box3"> -->
<!-- 	        		<img src="/images_main/box3.png" onmouseover="this.src='/images_main/box3-3.png' "  onmouseout="this.src='/images_main/box3.png' "   /> -->
<!-- 	        		<p>자료실</p> -->
<!-- 	        	</div> -->
<!-- 	        </a> -->
<!-- 	    </div> -->
<!-- 	</div>	 -->
	
	<!-- 반응형 때문에 추가된 부분 -->
<!-- 	<div id="noticewrap2"> -->
<!-- 		<ul class="notice2"> -->
<!-- 			<li> -->
<!-- 				<span>Free bulletin board</span><a href="/webMenu.do?key=454&groupKey=1"><img src="images_main/notice2.png"><p>자유게시판</p></a> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<span>Notice</span><a href="/webMenu.do?key=453&groupKey=1"><img src="images_main/notice2-1.png"><p>공지사항</p></a> -->
<!-- 			</li> -->
<!-- 			<li> -->
<!-- 				<span>Reference room</span><a href="/webMenu.do?key=455&groupKey=1"><img src="images_main/notice2-2.png"><p>자료실</p></a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
<!-- 	</div> -->
	<!-- 반응형 때문에 추가된 부분 끝 -->
		

<!-- 	<div id="boxwrap"> -->
		
<!-- 		<h2>Area for technique</h2> -->
<!--     	<div class="boxlist1"> -->
<!--     		<img src="images_main/image1-1.png" alt="" /> -->
<!--     		<a href="/webMenu.do?key=462&groupKey=1"><p>M.S.H.S<br />공법</p></a> -->
<!--     		<a class="amore" href="/webMenu.do?key=462&groupKey=1">+more</a> -->
<!--     	</div> -->
<!--     	<div class="boxlist2"> -->
<!-- 		    <img src="images_main/image2-1.png" alt="" /> -->
<!-- 		    <a href="/webMenu.do?key=463&groupKey=1"><p>높이조절<br />보수공법</p></a> -->
<!-- 		    <a class="amore" href="/webMenu.do?key=463&groupKey=1">+more</a> -->
<!--   		</div> -->
<!-- 		<div class="boxlist3"> -->
<!-- 		    <img src="images_main/image3-1.png" alt="" /> -->
<!-- 		    <a href="/webMenu.do?key=464&groupKey=1"><p>특허출원<br />공법</p></a> -->
<!-- 		    <a class="amore" href="/webMenu.do?key=464&groupKey=1">+more</a> -->
<!--   		</div> -->
<!--     	<div class="boxlist4"> -->
<!-- 		    <img src="images_main/image4-1.png" alt="" /> -->
<!-- 		    <a href="/webMenu.do?key=466&groupKey=1"><p>기술시공<br />상담문의</p></a> -->
<!-- 		    <a class="amore" href="/webMenu.do?key=466&groupKey=1">+more</a> -->
<!--   		</div> -->
<!--     	<div class="boxlist5"> -->
<!-- 		    <img src="images_main/image5-1.png" alt="" /> -->
<!-- 		    <a href="/webMenu.do?key=465&groupKey=1"><p>공사신청<br />서류확인</p></a> -->
<!-- 		    <a class="amore" href="/webMenu.do?key=465&groupKey=1">+more</a> -->
<!--   		</div> -->
<!--   		반응형 때문에 추가된 부분 -->
<!--   		<div class="boxlist6"> -->
<!-- 		    <img src="images_main/logo.png" alt="" /> -->
<!-- 		    <a class="amore" href="/webMenu.do?key=467&groupKey=1"><p><br /></p></a> -->
<!-- 		    <a class="amore" href="/webMenu.do?key=467&groupKey=1">+more</a> -->
<!--   		</div> -->
  		<!-- 반응형 때문에 추가된 부분 끝 -->
<!-- 	</div> -->


	<!-- end-->
<!-- 	<div id="business"> -->
<!-- 		<div class="bn_wrap"> -->
<!-- 			<div class="bn_list"> -->
<!-- 		    	<img src="/images_main/img1.jpg" alt="" /> -->
<!-- 		    </div> -->
<!-- 		    <div class="bn_list"> -->
<!-- 		    	<div class="txt"> -->
<!-- 		    	<span>준설공사</span> -->
<!-- 		        <p>진공흡입차의 흡입호스를<br/>통하여 퇴적물을 제거</p> -->
<!-- 		         <a href="/webMenu.do?key=439&groupKey=1">▶ 자세히보기</a> -->
<!-- 		    </div> -->
<!-- 		</div> -->
<!-- 		<div class="bn_list"> -->
<!-- 			<img src="/images_main/img3.jpg" alt="" /> -->
<!-- 		</div> -->
<!-- 		<div class="bn_list"> -->
<!-- 			<div class="txt"> -->
<!-- 		    	<span>비굴착보수</span> -->
<!-- 		        <p>시공성,신뢰성,경제성 등<br />  장점이 많음</p> -->
<!-- 		        <a href="/webMenu.do?key=438&groupKey=1">▶ 자세히보기</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="bn_list"> -->
<!-- 			<img src="/images_main/img2.jpg" alt="" /> -->
<!-- 		</div> -->
<!-- 		<div class="bn_list"> -->
<!-- 			<div class="txt"> -->
<!-- 		    	<span>시험 및 용역</span> -->
<!-- 		        <p>진공흡입차의 흡입호스를<br/>통하여 퇴적물을 제거</p> -->
<!-- 				<a href="/webMenu.do?key=440&groupKey=1">▶ 자세히보기</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
	
<!-- 		<div class="txtbox"> -->
<!-- 		    <h1>Business Area</h1> -->
<!-- 		    <h2>사업영역</h2> -->
<!-- 		    <p class="txtlist"> -->
<!-- 			    <p>- 비굴착보수</p> -->
<!-- 			    <p>- 준설공사</p> -->
<!-- 			    <p>- 시험및용역</p> -->
<!-- 		    </p> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div>	 -->
	
<!-- 	<div id="bn_wrap2"> -->
<!-- 		<h2>Field of business</h2> -->
<!-- 		<div class="bn_list2"> -->
<!-- 			<img src="images_main/img1.jpg" alt="" /> -->
<!-- 			<p>비굴착보수</p> -->
<!-- 			<p><a href="/webMenu.do?key=438&groupKey=1">+MORE</a></p> -->
<!-- 		</div> -->
<!-- 		<div class="bn_list2"> -->
<!-- 			<img src="images_main/img2.jpg" alt="" /> -->
<!-- 			<p>준설공사</p> -->
<!-- 			<p><a href="/webMenu.do?key=439&groupKey=1">+MORE</a></p> -->
<!-- 		</div> -->
<!-- 		<div class="bn_list2"> -->
<!-- 			<img src="images_main/img3.jpg" alt="" /> -->
<!-- 			<p>시험및용역</p> -->
<!-- 			<p><a href="/webMenu.do?key=440&groupKey=1">+MORE</a></p> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
<!-- </div> -->


