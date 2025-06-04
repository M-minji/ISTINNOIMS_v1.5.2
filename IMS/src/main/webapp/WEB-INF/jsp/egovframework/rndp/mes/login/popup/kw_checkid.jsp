<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>아이디(ID) 확인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
	<table>
		<tr>
			<td class="tit">아이디 중복 검색</td>
		</tr>
	</table>
	<table>
	<!-- 중복 아이디가 있을때 -->
		
			<c:if test="${idCount>0}">
				<tr>
					<td>입력하신 [     ]아이디(ID)는 사용하실 수 없습니다.</td>
				</tr>
			</c:if>
	</table> 
</html>
