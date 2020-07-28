<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<h1>로그인</h1>
<form method="post" action="<%=request.getContextPath()%>/login">
	<input type="text" name="id" id="id" placeholder="아이디">
	<input type="password" name="pw" id="pw" placeholder="비밀번호">
	<button>로그인</button>
</form>
<script>
	$(function(){
		if(${isLogin==false}){
			alert("존재하지 않는 ID 또는 잘못된 비밀번호 입니다.")
		}
	})
</script>