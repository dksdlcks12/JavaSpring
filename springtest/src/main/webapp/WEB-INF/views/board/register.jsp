<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<br>
<form action="<%=request.getContextPath()%>/board/register" method="POST">
	<div class="form-group">
		<label>제목</label>
		<input type="text" class="form-control" name="title">
	</div>
	<div class="form-group">
		<label>작성자</label>
		<input type="text" class="form-control" name="writer">
	</div>
	<div class="form-group">
		<label>내용</label>
		<textarea class="form-control" rows="5" name="content"></textarea>
	</div>
	<button>완료</button>
</form>