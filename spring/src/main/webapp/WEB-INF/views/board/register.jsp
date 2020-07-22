<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<br>
<form action="<%=request.getContextPath() %>/board/register" method=POST enctype="multipart/form-data">
	<div class="form-group">
	  <label>제목</label>
	  <input type="text" class="form-control" name="title">
	</div>
	<div class="form-group">
 		<label>내용</label>
 		<textarea class="form-control" rows="5" name="content"></textarea>
	</div>
	<div class="form-group">
        <label>파일</label>
        <input type="file" class="form-control" name="files"/>
    </div>
	<button>완료</button>
</form>
