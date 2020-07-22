<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<br>
<form action="<%=request.getContextPath()%>/board/modify" method="POST">
	<div class="form-group">
	  <label>번호</label>
	  <input type="text" class="form-control" name="num" value="${board.num}" readonly>
	</div>
	<div class="form-group">
	  <label>제목</label>
	  <input type="text" class="form-control" name="title" value="${board.title}">
	</div>
	<div class="form-group">
		<label>작성자</label>
		<input type="hidden" class="form-control" name="writer" value="${board.writer}" readonly>
	</div>
	<div class="form-group">
		<label>작성일</label>
		<input type="hidden" class="form-control" name="registerDate" value="${board.registerDate}" readonly>
	</div>
	<div class="form-group">
		<label>조회수</label>
		<input type="hidden" class="form-control" name="views" value="${board.views}" readonly>
	</div>
	<input type="hidden" class="form-control" name="writer" value="${board.writer}" readonly>
	<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="5" name="content">${board.content}</textarea>
	</div>
	<button>완료</button>
</form>