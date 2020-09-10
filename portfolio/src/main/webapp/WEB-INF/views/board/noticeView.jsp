<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardView-box">
	<div class="common-boardView-ViewBox">
		<table class="common-boardView-titleBox" border="1">
			<tr>
				<td class="common-boardView-rowTitle">제 목</td>
				<td class="common-boardView-rowContent" colspan="3">${notice.noticeTitle}</td>
			</tr>
			<tr>
				<td class="common-boardView-menu">작 성 자</td>
				<td class="common-boardView-menu">${notice.notice_userId}</td>
				<td class="common-boardView-menu">게 시 일</td>
				<td class="common-boardView-menu">${notice.noticeDate}</td>
			</tr>
		</table>
		<div class="common-boardView-content">${notice.noticeContent}</div>
		<a href="<%=request.getContextPath()%>/noticelist?page=${page}&type=${type}&search=${search}"><button class="common-boardView-button common-boardView-goList">목 록</button></a>
		<c:if test="${user.userAuth eq 'admin'}">
			<a href="<%=request.getContextPath()%>//admin/noticemodify?noticeNum=${notice.noticeNum}&page=${page}&type=${type}&search=${search}"><button class="common-boardView-button common-boardView-goModify">수 정</button></a>
			<button class="common-boardView-button common-boardView-goDelete">삭 제</button>
		</c:if>
	</div>
</div>
<script>
	$('.common-boardView-content').html($('.common-boardView-content').text());
</script>