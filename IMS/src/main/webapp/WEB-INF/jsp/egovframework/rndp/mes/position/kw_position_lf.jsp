<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script type="text/javascript">
		function addRow(startingPosition, depth){
			var index = document.getElementsByName("kPositionName").length;
			console.log(startingPosition + " / " + $("#kPositionPath_"+startingPosition).val());
			
			
			var showStr = "";
			var showElement = document.createElement("tr");

			// id 속성 설정
			showElement.setAttribute("id", "show_" + index);
			
			// class 속성 설정
			showElement.setAttribute("class", "show");

			// style 속성 설정
			showElement.setAttribute("style", "display:none");

			showStr += "			<td id='positionPath_"+index+"'>";
			showStr += "				"+$("#kPositionUpName").val();
			showStr += "			</td>";
			showStr += "			<td id='positionName_"+index+"'>";
			showStr += "				${list.kPositionName}";
			showStr += "			</td>";
			showStr += "			<td>";
			showStr += "				${list.kPositionStaffName}";
			showStr += "			</td>";
			showStr += "			<td>";
			showStr += "				<a class='form_btn sm'  onclick='addRow("+index+", "+(depth+1)+")'>하위 부서 추가</a>";
			showStr += "				<a class='form_btn sm' onclick='setUpdatePosition("+index+", \"Y\", this)'>수정</a>";
			showStr += "				<a class='form_btn sm'  onclick='deletePosition("+index+")'>삭제</a>";
			showStr += "				";
			showStr += "				<input type='hidden' id='kPositionNameBackup_"+index+"' name='kPositionNameBackup' value=''/>";
			showStr += "				<input type='hidden' id='kPositionKeyB_"+index+"' name='kPositionKeyB' value='0'/>";
			showStr += "				<input type='hidden' id='kPositionStaffName_"+index+"' name='kPositionStaffName' value=''/>";
			showStr += "				<input type='hidden' id='kPositionPath_"+index+"' name='kPositionPath' value=''/>";

			if(startingPosition == -1){
				showStr += "				<input type='hidden' id='kPositionUpKey_"+index+"' name='kPositionUpKey' value='0'/>";
			}else{
				showStr += "				<input type='hidden' id='kPositionUpKey_"+index+"' name='kPositionUpKey' value='"+$("#kPositionKeyB_"+startingPosition).val()+"'/>";
			}
			showStr += "			</td>";
			
			showElement.innerHTML = showStr;
			
			
			
			
			var updateStr = "";
			var updateElement = document.createElement("tr");

			// id 속성 설정
			updateElement.setAttribute("id", "update_" + index);

			// class 속성 설정
			updateElement.setAttribute("class", "update");

			// style 속성 설정
			updateElement.setAttribute("style", "background-color:#dff0f4 !important;");

			updateStr += "			<td>";
			if(startingPosition == -1){
				updateStr += "				최상위";
			}else{
				updateStr += "				"+$("#kPositionPath_"+startingPosition).val();
			}
			updateStr += "			</td>";
			updateStr += "			<td colspan='2'>";
			updateStr += "				<input type='text' id='kPositionName_"+index+"' name='kPositionName' value='' style='width:95%;' maxLength='30' placeholder='부서명'/>";
			updateStr += "			</td>";
			updateStr += "			<td>";
			updateStr += "				<a class='form_btn sm' onclick='savePosition("+index+")'>저장</a>";
			updateStr += "				<a class='form_btn sm'  onclick='setUpdatePosition("+index+", \"N\", this)'>취소</a>";
			updateStr += "			</td>";			 

			updateElement.innerHTML = updateStr;
			

			console.log(updateElement);
			console.log(showElement);
			
			if(startingPosition == -1){
				$(showElement).appendTo("#positionList");
				$(updateElement).appendTo("#positionList");
			}else{
				document.getElementById("update_"+startingPosition).insertAdjacentElement("afterend", updateElement);
				document.getElementById("update_"+startingPosition).insertAdjacentElement("afterend", showElement);
			}
			
			$("#kPositionName_"+index).focus();
		}
		

		
		function savePosition(index){
			 $.ajax({
		     	url: '/mes/position/kw_savePositionAjax.do'
		    ,	method: 'POST'
		    ,	dataType: 'JSON'
		    ,	data : {
	    			kPositionKey 	: $("#kPositionKeyB_"+index).val()
	    		,	kPositionName 	: $("#kPositionName_"+index).val()
	    		,	kPositionUpKey 	: $("#kPositionUpKey_"+index).val()
		    	}
		    ,	success: function(data) {
		        	// 성공적으로 응답을 받았을 때 처리
		        	var key = data.result.key;
		        	var path = data.result.path;
		        	$("#kPositionKeyB_"+index).val(key);
		        	$("#kPositionPath_"+index).val(path);
		        	
					$('#update_'+index).css('display', 'none');
					$('#show_'+index).css('display', '');
					
					$("#kPositionNameBackup_"+index).val($("#kPositionName_"+index).val());
					document.getElementById('positionName_'+index).innerHTML = $("#kPositionName_"+index).val();
					
					document.getElementById('positionPath_'+index).innerHTML = path;
		        	alert("저장완료");
				}
		     ,	error: function(xhr, status, error) {
			      	// 에러 발생 시 처리
			      	console.error('Error:', error);
				}
		    });
		}
		
		
		function deletePosition(index, cnt){
			var key = $("#kPositionKeyB_"+index).val();
			
			var deleteList = [];
			
			if(!$("#kPositionStaffName_"+index).val()){
	    		
				if(confirm("삭제하시겠습니까?\n삭제 시 복구가 불가능하고 하위 부서까지 삭제가 됩니다")){
					 $.ajax({
				     	url: '/mes/position/kw_deletePositionAjax.do'
				    ,	method: 'POST'
				    ,	dataType: 'JSON'
				    ,	data : {
				    	kPositionKey 	: key
				    }
				    ,	success: function(data) {
				    	 document.frm.submit();
			    	 	
			        	// 성공적으로 응답을 받았을 때 처리는 무슨 그냥 새로고침 함 ㅅㅂ
				    		
				    		/* for(var i=0; i<document.getElementsByName("kPositionUpKey").length; i++){
				    			if(document.getElementsByName("kPositionUpKey")[i].value == key){
				    				deleteList.push(i);
						    		for(var j=0; j<document.getElementsByName("kPositionUpKey").length; j++){
						    			if(document.getElementsByName("kPositionUpKey")[j].value == document.getElementsByName("kPositionKeyB")[i].value){
						    				deleteList.push(j);
						    			}
						    		}
				    			}
				    		}
				    		
				    		console.log(deleteList);
				    		 
				    		for(var i=deleteList.length-1; i>=0; i--){
					    		document.getElementsByClassName('show')[deleteList[i]].remove();
					    		document.getElementsByClassName('update')[deleteList[i]].remove();
				    		}
				    		document.getElementById('show_'+index).remove();
				    		document.getElementById('update_'+index).remove(); */
						}
				     ,	error: function(xhr, status, error) {
					      	// 에러 발생 시 처리
					      	console.error('Error:', error);
						}
				    });
				}
			}else{
				alert("사용중인 부서입니다. 삭제할 수 없습니다.");
				return false;
			}	
		}
		
		
		function setUpdatePosition(index, status, obj){
			if($("#kPositionNameBackup_"+index).val() == ""){
				/* alert("저장되지 않은 부서입니다"); */
				var tr = $(obj).parent().parent();
				tr.remove();	//라인삭제
				return false;
			}
			
			if(status == 'Y'){
				$('#update_'+index).css('display', '');
				$('#show_'+index).css('display', 'none');
			}else{
				$('#update_'+index).css('display', 'none');
				$('#show_'+index).css('display', '');
				
				$("#kPositionName_"+index).val($("#kPositionNameBackup_"+index).val());
			}
		}
		
		function backToHighest(){
			$("#kPositionUpKeySave").val(0);
			$("#kPositionUpName").val("최상위");
			
			document.frm.action = "/mes/position/kw_position_lf.do";
			document.frm.submit();
		}
		
		function getIntoPosition(index){
			$("#kPositionUpKeySave").val($("#kPositionKeyB_"+index).val());
			$("#kPositionUpName").val($("#kPositionNameBackup_"+index).val());
			
			document.frm.action = "/mes/position/kw_position_lf.do";
			document.frm.submit();
		}
		
		
	</script>
</head>

<body>
	<form name="frm" id="frm" method="post" >
		<input type="hidden" id="kPositionKey" name="kPositionKey"/>
		<input type="hidden" id="kPositionUpKeySave" name="kPositionUpKeySave" value="${mesPositionVO.kPositionUpKeySave}"/>
		<input type="hidden" id="kPositionUpName" name="kPositionUpName" value="${mesPositionVO.kPositionUpName}"/>
		<input type="hidden" id="maxKey" name="maxKey" value="${maxKey}" />
	
		<div class="content_top">
			<div class="content_tit">
				<h2>
					부서 관리
				</h2>
			</div>
		</div>	
		
		<div class="normal_table">
			<table>
				<thead>
					<tr>
						<th style="width:30%;">부서경로</th>
						<th style="width:20%;">부서명</th>
						<th style="width:30%;">부서원</th>
						<th style="width:20%;">관리</th>
					</tr>
				</thead>
				<tbody id="positionList">
					<c:forEach var="list" items="${positionList}" varStatus="i">
						<c:if test="${list.kPositionUpKey eq '0'}">
							<tr id="show_${i.index}" class="show"  >
								<td id="positionPath_${i.index}" >
									${list.kPositionPath}
								</td>
								<td id="positionName_${i.index}">
									${list.kPositionName}
								</td>
								<td >
									${list.kPositionStaffName}
								</td>
								<td >
									<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
										<a class="form_btn sm" onclick="addRow(${i.index}, 1)">하위 부서 추가</a>
									</c:if>
									<c:if test="${staffVo.kStaffAuthModifyFlag eq 'T'}">
										<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'Y', this)">수정</a>
									</c:if>
									<c:if test="${staffVo.kStaffAuthDelFlag eq 'T'}">
										<a class="form_btn sm" onclick="deletePosition(${i.index}, ${list.kPositionStaffCount})">삭제</a>
									</c:if>
									<input type="hidden" id="kPositionNameBackup_${i.index}" name="kPositionNameBackup" value="${list.kPositionName}"/>
									<input type="hidden" id="kPositionKeyB_${i.index}" name="kPositionKeyB" value="${list.kPositionKey}"/>
									<input type="hidden" id="kPositionStaffName_${i.index}" name="kPositionStaffName" value="${list.kPositionStaffName}"/>
									<input type="hidden" id="kPositionUpKey_${i.index}" name="kPositionUpKey" value="${list.kPositionUpKey}"/>
									<input type="hidden" id="kPositionPath_${i.index}" name="kPositionPath" value="${list.kPositionPath}"/>
								</td>
							</tr>
							<tr id="update_${i.index}"  class="update" style="display:none; background-color:#F3F9FF !important;cursor:default">
								<td>
									${list.kPositionPath}
								</td>
								<td colspan="2">
									<input type="text" id="kPositionName_${i.index}" name="kPositionName" value="${list.kPositionName}" style="width:95%;" maxLength="50" placeholder="부서명"/>
								</td>
								<td>
									<a class="form_btn sm"  onclick="savePosition(${i.index})">저장</a>
									<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'N', this)">취소</a>
								</td>
							</tr>
							<c:forEach var="list2" items="${positionList}" varStatus="i">
								<c:if test="${list2.kPositionUpKey eq list.kPositionKey}">
									<tr id="show_${i.index}" class="show">
										<td id="positionPath_${i.index}">
											${list2.kPositionPath}
										</td>
										<td id="positionName_${i.index}">
											${list2.kPositionName}
										</td>
										<td>
											${list2.kPositionStaffName}
										</td>
										<td>
											<a class="form_btn sm" onclick="addRow(${i.index}, 2)">하위 부서 추가</a>
											<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'Y', this)">수정</a>
											<a class="form_btn sm" onclick="deletePosition(${i.index}, ${list2.kPositionStaffCount})">삭제</a>
											
											<input type="hidden" id="kPositionNameBackup_${i.index}" name="kPositionNameBackup" value="${list2.kPositionName}"/>
											<input type="hidden" id="kPositionKeyB_${i.index}" name="kPositionKeyB" value="${list2.kPositionKey}"/>
											<input type="hidden" id="kPositionStaffName_${i.index}" name="kPositionStaffName" value="${list2.kPositionStaffName}"/>
											<input type="hidden" id="kPositionUpKey_${i.index}" name="kPositionUpKey" value="${list2.kPositionUpKey}"/>
											<input type="hidden" id="kPositionPath_${i.index}" name="kPositionPath" value="${list2.kPositionPath}"/>
										</td>
									</tr>
									<tr id="update_${i.index}" class="update" style="display:none; background-color:#F3F9FF !important;">
										<td>
											${list2.kPositionPath}
										</td>
										<td colspan="2">
											<input type="text" id="kPositionName_${i.index}" name="kPositionName" value="${list2.kPositionName}" style="width:95%;" maxLength="50" placeholder="부서명"/>
										</td>
										<td>
											<a class="form_btn sm"  onclick="savePosition(${i.index})">저장</a>
											<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'N', this)">취소</a>
										</td>
									</tr>
									<c:forEach var="list3" items="${positionList}" varStatus="i">
										<c:if test="${list3.kPositionUpKey eq list2.kPositionKey}">
											<tr id="show_${i.index}" class="show">
												<td id="positionPath_${i.index}" >
													${list3.kPositionPath}
												</td>
												<td id="positionName_${i.index}">
													${list3.kPositionName}
												</td>
												<td >
													${list3.kPositionStaffName}
												</td>
												<td >
												 	<a class="form_btn sm" onclick="addRow(${i.index}, 3)">하위 부서 추가</a>
													<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'Y', this)">수정</a>
													<a class="form_btn sm" onclick="deletePosition(${i.index}, ${list3.kPositionStaffCount})">삭제</a>
													
													<input type="hidden" id="kPositionNameBackup_${i.index}" name="kPositionNameBackup" value="${list3.kPositionName}"/>
													<input type="hidden" id="kPositionKeyB_${i.index}" name="kPositionKeyB" value="${list3.kPositionKey}"/>
													<input type="hidden" id="kPositionStaffName_${i.index}" name="kPositionStaffName" value="${list3.kPositionStaffName}"/>
													<input type="hidden" id="kPositionUpKey_${i.index}" name="kPositionUpKey" value="${list3.kPositionUpKey}"/>
													<input type="hidden" id="kPositionPath_${i.index}" name="kPositionPath" value="${list3.kPositionPath}"/>
												</td>
											</tr>
											<tr id="update_${i.index}" class="update" style="display:none; background-color:#F3F9FF !important;">
												<td>
													${list3.kPositionPath}
												</td>
												<td colspan="2">
													<input type="text" id="kPositionName_${i.index}" name="kPositionName" value="${list3.kPositionName}" style="width:95%;" maxLength="50" placeholder="부서명"/>
												</td>
												<td>
													<a class="form_btn sm"  onclick="savePosition(${i.index})">저장</a>
													<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'N', this)">취소</a>
												</td>
											</tr>
											<c:forEach var="list4" items="${positionList}" varStatus="i">
												<c:if test="${list4.kPositionUpKey eq list3.kPositionKey}">
													<tr id="show_${i.index}" class="show">
														<td id="positionPath_${i.index}" >
															${list4.kPositionPath}
														</td>
														<td id="positionName_${i.index}" >
															${list4.kPositionName}
														</td>
														<td>
															${list4.kPositionStaffName}
														</td>
														<td>
															<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'Y', this)">수정</a>
															<a class="form_btn sm" onclick="deletePosition(${i.index}, ${list4.kPositionStaffCount})">삭제</a>
															
															<input type="hidden" id="kPositionNameBackup_${i.index}" name="kPositionNameBackup" value="${list4.kPositionName}"/>
															<input type="hidden" id="kPositionKeyB_${i.index}" name="kPositionKeyB" value="${list4.kPositionKey}"/>
															<input type="hidden" id="kPositionStaffName_${i.index}" name="kPositionStaffName" value="${list4.kPositionStaffName}"/>
															<input type="hidden" id="kPositionUpKey_${i.index}" name="kPositionUpKey" value="${list4.kPositionUpKey}"/>
															<input type="hidden" id="kPositionPath_${i.index}" name="kPositionPath" value="${list4.kPositionPath}"/>
														</td>
													</tr>
													<tr id="update_${i.index}" class="update" style="display:none; background-color:#F3F9FF !important;">
														<td>
															${list4.kPositionPath}
														</td>
														<td colspan="2" style="cursor:default;">
															<input type="text" id="kPositionName_${i.index}" name="kPositionName" value="${list4.kPositionName}" style="width:95%;" maxLength="50" placeholder="부서명"/>
														</td>
														<td>
															<a class="form_btn sm"  onclick="savePosition(${i.index})">저장</a>
															<a class="form_btn sm" onclick="setUpdatePosition(${i.index}, 'N', this)">취소</a>
														</td>
													</tr>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</c:if>
					</c:forEach>
					<%-- <c:if test="${empty positionList}">
						<tr>
							<td colspan="4" align="center">등록된 부서 목록이 없습니다.</td>
						</tr>
					</c:if> --%>
				</tbody>
			</table>
		</div>
		
		<div class="list_btm right">
			<div class="btns">
				<c:if test="${staffVo.kStaffAuthWriteFlag eq 'T'}">
				<button type="button" class="form_btn active" onClick="addRow(-1, 0)">최상위 부서추가</button>
				</c:if>
			</div>
		</div>
	</form>
</body>
</html>
