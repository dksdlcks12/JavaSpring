<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-asView-box">
	<div class="admin-asView-asViewBox">
		<h2>A/S조회</h2>
		<div class="admin-asView-asNumberBox">
			<div class="admin-asView-asTitleNumber">A/S번호</div>
			<div class="admin-asView-asNumber">${as.asNum}</div>
		</div>
		<div class="admin-asView-asGoodsTitleBox">
			<div class="admin-asView-asGoodsTitleType">제목</div>
			<div class="admin-asView-asGoodsTitleName">${as.asTitle}</div>
		</div>
		<div class="admin-asView-asGoodsBox">
			<div class="admin-asView-asGoodsType">이름</div>
			<div class="admin-asView-asGoodsName">${as.asName}</div>
		</div>
		<div class="admin-asView-asGoodsBox">
			<div class="admin-asView-asGoodsType">전화번호</div>
			<div class="admin-asView-asGoodsName">${as.asTel}</div>
		</div>
		<div class="admin-asView-asStateBox">
			<div class="admin-asView-asTitleState">A/S상태</div>
			<div class="admin-asView-asState">${as.asState}</div>
		</div>
		<div class="admin-recallView-board">${as.asContent}</div>
		<a class="admin-asView-memberGoList" href="<%=request.getContextPath()%>/asviewlist?page=${page}")><button class="admin-asView-asStateButton">목록으로</button></a>
	</div>
</div>
<script>
	$('.admin-recallView-board').html($('.admin-recallView-board').text());
</script>