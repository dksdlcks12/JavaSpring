<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-login-box">
	<div class="common-login-loginBox">
		<div class="common-login-title">로 그 인</div>
			<form action="<%=request.getContextPath()%>/loginorder" method="POST">
				<input type="text" class="common-login-inputInfo" name="userId" placeholder="아이디를 입력하세요">
				<input type="password" class="common-login-inputInfo" name="userPw" placeholder="비밀번호를 입력하세요">
				<input class="common-login-address" name="address" value="${address}" hidden>
				<button class="common-login-loginButton">로그인</button><br>
			</form>
		<div class="common-login-findBox">
			<a href="#" class="common-login-idFind"><i class="fas fa-chevron-circle-right"></i>아이디 찾기</a>
			<a href="#" class="common-login-passWordFind"><i class="fas fa-chevron-circle-right"></i>비밀번호 찾기</a>
		</div>
		<button class="common-login-nonMemberButton">비회원 구매</button><br></a>
	</div>
</div>
<script>
	$(function(){
		if(${isLogin==false}){
			alert("존재하지 않는 ID 또는 잘못된 비밀번호 입니다.")
		}
		$('.common-login-nonMemberButton').click(function(){
			location.replace('<%=request.getContextPath()%>/nonememberorder'+$(".common-login-address").val());
		})
	})
</script>