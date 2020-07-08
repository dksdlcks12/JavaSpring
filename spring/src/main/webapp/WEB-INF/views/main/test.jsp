<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>${title}</div>
<form action="<%=request.getContextPath()%>/test" method="get">
	<input type="text" name="id" placeholder="ID를 입력하십시오."><br>
	<input type="password" name="pw" placeholder="PW를 입력하십시오."><br>
	<button type="submit">전송</button>
</form>