<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<br>
<div class="form-group">
	<label>번호</label>
    <input type="text" class="form-control" name="num" value="${board.num}" readonly>
</div>
<div class="form-group">
	<label>제목</label>
    <input type="text" class="form-control" name="num" value="${board.title}" readonly>
</div>
<div class="form-group">
	<label>작성자</label>
    <input type="text" class="form-control" name="num" value="${board.writer}" readonly>
</div>
<div class="form-group">
	<label>작성일</label>
    <input type="text" class="form-control" name="num" value="${board.registeredDate}" readonly>
</div>
<div class="form-group">
	<label>조회수</label>
    <input type="text" class="form-control" name="num" value="${board.views}" readonly>
</div>
<div class="form-group">
		<label>내용</label>
		<textarea class="form-control" rows="5" name="content" readonly>${board.content}</textarea>
</div>