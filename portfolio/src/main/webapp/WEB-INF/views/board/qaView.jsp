<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardView-box">
	<div class="common-boardView-ViewBox">
		<table class="common-boardView-titleBox" border="1">
			<tr>
				<td class="common-boardView-rowTitle">제 목</td>
				<td class="common-boardView-rowContent" colspan="3">${qa.qaTitle}</td>
			</tr>
			<tr>
				<td class="common-boardView-menu">작 성 자</td>
				<td class="common-boardView-menu">${qa.qaWriter}</td>
				<td class="common-boardView-menu">게 시 일</td>
				<td class="common-boardView-menu">${qa.qaDate}</td>
			</tr>
		</table>
		<div class="common-boardView-content">${qa.qaContent}</div>
		<a href="<%=request.getContextPath()%>/qalist?page=${page}&type=${type}&search=${search}"><button class="common-boardView-button common-boardView-goList">목 록</button></a>
		<c:if test="${user.userAuth eq 'admin'}">
			<c:if test="${qa.qaNum == qa.qaOriginNum}">
				<a href="<%=request.getContextPath()%>/admin/qaanswer?qaNum=${qa.qaNum}&page=${page}&type=${type}&search=${search}" class="common-boardView-goModify"><button class="common-boardView-button">답 글</button></a>
			</c:if>
			<c:if test="${qa.qaNum != qa.qaOriginNum}">
				<a href="<%=request.getContextPath()%>/admin/qaanswermodify?qaNum=${qa.qaNum}&page=${page}&type=${type}&search=${search}" class="common-boardView-goModify"><button class="common-boardView-button">수 정</button></a>
			</c:if>
			<button class="common-boardView-button common-boardView-goDelete">삭 제</button>
		</c:if>
	</div>
</div>
<script>
	$('.common-boardView-content').html($('.common-boardView-content').text());
</script>