<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="viewport" content="width-device-width, initial-scale=1.0">
<script type="text/javascript" src="<c:url value='/js/script.js'/>"></script><!-- 반응형 JS -->

<table class="containerimage" width="740" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td>						
		<c:choose>
		<c:when test="${content eq '' }">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr style="text-align:center; height: 500px;">
					<td>준비중입니다.</td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<c:out value="${content }" escapeXml="false"/>
			
<%-- 			<c:if test="${name eq '오시는길' }">
				<div><img src="/images_main/dj.png"/></div>
				<div id="map" style="width:700px;height:250px;"></div>
				<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=af497499d87200393bcec3d9c3808170be127984"></script>
				<script type="text/javascript">
				    $(window).load(function(){
				        var longitude='127.3929890';
				        var latitude='36.4279990';
				        var mapLongitude='127.3929890';
				        var mapLatitude='36.4279990';
				 
				        map=new daum.maps.Map(document.getElementById("map"),{
				            center:new daum.maps.LatLng(mapLatitude,mapLongitude),
				            level:4
				        });
				         
				        var marker=new daum.maps.Marker({
				            position:new daum.maps.LatLng(latitude,longitude)
				        });
				        
				        //말풍선, 지도 위에 말풍선을 표시한다. (자신이 위치한 빌딩명이나 업체명을 넣으면 좋겠죠?)
				        var infowindow = new daum.maps.InfoWindow({
				        	content: '<p style="margin:7px 7px 7px 22px;font:12px/1.5 sans-serif"><strong>아록ENG</strong></p>'
				        });
				         
				        var zoomControl=new daum.maps.ZoomControl();
				        map.addControl(zoomControl,daum.maps.ControlPosition.RIGHT);
				        var mapTypeControl=new daum.maps.MapTypeControl();
				        map.addControl(mapTypeControl,daum.maps.ControlPosition.TOPRIGHT);
				        marker.setMap(map);
				        infowindow.open(map, marker);
				    });
				</script>
				<BR/>
				<div><img src="/images_main/se.png"/></div>
				<div id="map1" style="width:700px;height:250px;"></div>
				<script type="text/javascript">
				    $(window).load(function(){
				        var longitude1='127.0096250';
				        var latitude1='37.4919346';
				        var mapLongitude1='127.0096250';
				        var mapLatitude1='37.4919346';
				 
				        map1=new daum.maps.Map(document.getElementById("map1"),{
				            center:new daum.maps.LatLng(mapLatitude1,mapLongitude1),
				            level:4
				        });
				         
				        var marker1=new daum.maps.Marker({
				            position:new daum.maps.LatLng(latitude1,longitude1)
				        });

				        //말풍선, 지도 위에 말풍선을 표시한다. (자신이 위치한 빌딩명이나 업체명을 넣으면 좋겠죠?)
				        var infowindow1 = new daum.maps.InfoWindow({
				        	content: '<p style="margin:7px 7px 7px 22px;font:12px/1.5 sans-serif"><strong>아록ENG</strong></p>'
				        });
				         
				        var zoomControl=new daum.maps.ZoomControl();
				        map1.addControl(zoomControl,daum.maps.ControlPosition.RIGHT);
				        var mapTypeControl=new daum.maps.MapTypeControl();
				        map1.addControl(mapTypeControl,daum.maps.ControlPosition.TOPRIGHT);
				        marker1.setMap(map1);
				        infowindow1.open(map1, marker1);
				    });
				</script> 
			</c:if>		 --%>					
		</c:otherwise>
		</c:choose>
		</td>
	</tr>
</table>

							
							