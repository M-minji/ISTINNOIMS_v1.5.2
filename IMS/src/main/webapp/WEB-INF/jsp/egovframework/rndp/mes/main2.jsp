<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>


<script type="text/javascript">
/* 페이징 */
function fn_guestList(pageNo) {
	$('#mloader').show();
	document.frm.submit();
}

function fn_keyDown(){
	if(event.keyCode == 13){
		fn_guestList(1);
	}			
}

function nowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var nowDate = year + "-" + month + "-" + day;
	
    return nowDate;
}


$(document).ready(function(){	
	datepickerSet('topStartDate', 'topEndDate');
	

});






function setStartDate(d) {
	var settingDate = new Date();
	if(d == '1d'){
		settingDate.setDate(settingDate.getDate()-1);
		$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
	}else if(d == '2d'){
		settingDate.setDate(settingDate.getDate()-2);
		$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
	}else if(d == '3d'){
		settingDate.setDate(settingDate.getDate()-3);
		$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
	}else if(d == '7'){
		settingDate.setDate(settingDate.getDate()-7);
		$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
	}else if(d == '1'){
		settingDate.setMonth(settingDate.getMonth()-1);
		$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
	}else{
		settingDate.setMonth(settingDate.getMonth()-3);
		$('#topStartDate').val(settingDate.format("yyyy-MM-dd"));
	}

	fn_guestList(1);
}





function myFunction() {
if (/menu/.test(window.location.href)) {
       document.getElementById('searchfield').display = 'none';
    }
}
  

</script>
<style>
.seoeun_top{background:#f5f7fa; border: 1px solid #c8d1db; width: 100%; text-align: left;margin-bottom:10px; padding: 0.5em 0;}
</style>
<form id="frm" name="frm" method="post"	action="/mes/main.do">
		 <div id="mes_container">
			<div class="main_title" >
				관리현황
			</div>
			<div style="width:100%; display:block;">
				<ul class="tbl_top_left seoeun_top">
					<li style="padding-left:1em;">
						<input type="text" name="topStartDate" id="topStartDate" value="${vo.topStartDate}" style="width:100px;" readonly />
						~ <input type="text" name="topEndDate" id="topEndDate" value="${vo.topEndDate}" style="width:100px;" readonly />
					</li>
					<li>
						<a onclick="fn_guestList(1)">검색</a>
					</li>
					<li>
						<a style="cursor: pointer;" onclick="setStartDate('1d');">
							1일전
						</a>
					</li>
					<li>
						<a style="cursor: pointer;" onclick="setStartDate('2d');">
							2일전
						</a>
					</li>
					<li>
						<a style="cursor: pointer;" onclick="setStartDate('3d');">
							3일전
						</a>
					</li>
					<li>
						<a style="cursor: pointer;" onclick="setStartDate(7);">
							1주일
						</a>
					</li>
					<li>
						<a style="cursor: pointer;" onclick="setStartDate(1);">
							1개월
						</a>
					</li>
					<li>
						<a style="cursor: pointer;" onclick="setStartDate(3);">
							3개월
						</a>
					</li>
				</ul>
				
				
				
				<div class="main_left">
					<ul>
						<li>
							<div>
								<table>
									<tr>
										<td rowspan="2" style="text-align:center !important;vertical-align: middle;" ><img src="/images/common/menu1.png" onclick="location.href='/mes/production/order/kw_order_lf.do'"></td>
										<th style="text-align: left">생산지시</th>
									</tr>
									<tr>
										<td>총 ${info.sendCount }건 / ${info.sendEndCount } 건 완료</td>
									</tr>
								</table>
							</div>
						</li>
						<li> 
							<div>
								<table>
									<tr>
										<td rowspan="2" style="text-align:center !important;vertical-align: middle"><img src="/images/common/menu4.png" onclick="location.href='/mes/suju/kw_suju_lf.do'"></td>
										<th style="text-align: left">수주건</th>
									</tr>
									<tr>
										<td>${info.sujuCount }건</td>
									</tr>
								</table>
							</div>
						</li>
						<li>
							<div>
								<table>
									<tr>
										<td rowspan="2" style="text-align:center !important;vertical-align: middle"><img src="/images/common/menu2.png" onclick="location.href='/mes/production/prod/kw_prod_lf.do'"></td>
										<th style="text-align: left">생산 제작중</th>
									</tr>
									<tr>
										<td>${info.orderProcessCount }건</td>
									</tr>
								</table>
							</div>
						</li>
					</ul>
				</div>
				
				
				<div class="main_left">
					<ul>
						<li>
							<div>
								<table>
									<tr>
										<td rowspan="2" style="text-align:center !important;vertical-align: middle;" ><img src="/images/common/menu1.png" onclick="location.href='/mes/production/order/kw_order_lf.do'"></td>
										<th style="text-align: left">생산지시</th>
									</tr>
									<tr>
										<td>총 ${info.sendCount }건 / ${info.sendEndCount } 건 완료</td>
									</tr>
								</table>
							</div>
						</li>
						<li> 
							<div>
								<table>
									<tr>
										<td rowspan="2" style="text-align:center !important;vertical-align: middle"><img src="/images/common/menu4.png" onclick="location.href='/mes/suju/kw_suju_lf.do'"></td>
										<th style="text-align: left">수주건</th>
									</tr>
									<tr>
										<td>${info.sujuCount }건</td>
									</tr>
								</table>
							</div>
						</li>
						<li>
							<div>
								<table>
									<tr>
										<td rowspan="2" style="text-align:center !important;vertical-align: middle"><img src="/images/common/menu2.png" onclick="location.href='/mes/production/prod/kw_prod_lf.do'"></td>
										<th style="text-align: left">생산 제작중</th>
									</tr>
									<tr>
										<td>${info.orderProcessCount }건</td>
									</tr>
								</table>
							</div>
						</li>

						<li>
							<div>
								<table>
									<tr>
										<td rowspan="2" style=";text-align:center !important;vertical-align: middle"><img src="/images/common/menu3.png" onclick="location.href='/mes/moveplace/out/kw_moveplace_out_lf.do'"></td>
										<th style="text-align: left">출고 완료건</th>
									</tr>
									<tr>
										<td>${info.sujuNotCompleteCount }건</td>
									</tr>
								</table>
							</div>
						</li>
					</ul>
				</div>
				
				
			</div>
		</div> 
	</form> 
 










