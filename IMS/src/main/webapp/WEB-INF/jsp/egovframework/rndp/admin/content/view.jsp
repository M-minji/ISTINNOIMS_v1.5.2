<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body class="content_bg">
<div id="contents" >
	  <div class="title">
	  		<h2>미리보기</h2>
		  	<div class="path">
		  		<p>HOME&nbsp;>&nbsp;컨텐츠관리&nbsp;>&nbsp;미리보기</p>
		  	</div>
	 	</div>
<table width="750" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
				<tr>
		          	<td class="path">          
			            <table width="" border="0" align="right" cellpadding="0" cellspacing="0">
			              <tr>
			                <td>
			               HOME <span class="path">컨텐츠 관리</span> > <span class="path_s">컨텐츠 미리보기</span></td>
			              </tr>
			            </table>
			        </td>
		        </tr>			
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
				         		<td>
				          			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="center_tbg">
										<tr><td width="100%" class="center_txt" >컨텐츠 미리보기</td></tr>
									</table>
								</td>
				       	 	</tr>				       
				        	<tr>
				          		<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="center" valign="top">
												<table border="0" cellpadding="0" cellspacing="1" bgcolor="#F3F3EE" width="100%">
													<tr>
														<td bgcolor="white" style="padding:2">
															<table border="0" cellspacing="0" cellpadding="1" width="100%">
																<tr>
																	<td class="content"><c:out value="${menu.content }" escapeXml="false"/></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
				        	</tr>
				        	<tr><td height="30"></td></tr>
							<tr>
								<td>
								<DIV class=list_btn>
          						<DIV class=bg align="center">
								<table border="0" cellspacing="1" cellpadding="1" width="100%">
									<tr>
										<td align="right" valign="middle">
										<a href="<c:url value='/admin/adminContentList.do'/>?rootKey=${menu.rootKey}&groupKey=${menu.groupKey}">목록</a>
										</td>
									</tr>
								</table>
								</DIV></DIV>
								</td>
							</tr>			      
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
