<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-signup-box">
	<div class="user-signup-signupBox">
		<h3 class="user-signup-title">회 원 가 입</h3>
		<form action="<%=request.getContextPath()%>/signup" method="POST">
			<table class="user-signup-table" border="1">
				<tbody>
					<tr>
						<td class="user-signup-rowTitle">아이디(필수)</td>
						<td class="user-signup-rowContent"><input type="text" class="user-signup-inputInfo" name="id" id="id"> (영문자, 숫자 8~16자)</td>
					</tr>
					<tr>
						<td class="user-signup-rowTitle">비밀번호(필수)</td>
						<td class="user-signup-rowContent"><input type="password" class="user-signup-inputInfo" name="pw" id="pw"> (영문자, 숫자 포함 4~8자)</td>
					</tr>
					<tr>
						<td class="user-signup-rowTitle">비밀번호 확인(필수)</td>
						<td class="user-signup-rowContent"><input type="password" class="user-signup-inputInfo" name="pwcheck" id="pwcheck"></td>
					</tr>
					<tr>
						<td class="user-signup-rowTitle">이메일(필수)</td>
						<td class="user-signup-rowContent"><input type="email" class="user-signup-inputInfo" name="email" id="email"> (비밀번호를 찾을 때 필요합니다.)</td>
					</tr>
				</tbody>
			</table>
			<div class="user-signup-lawBox">
				<h3>이용약관</h3>
				<textarea class="user-signup-lawInfo" readonly></textarea>
				<span class="user-signup-lawSpan">이용약관에 동의 하십니까?</span><input type="checkbox" class="user-signup-termsOfUse"><span class="user-signup-lawSpan">동의함</span>
			</div>
			<div class="user-signup-personalInfoConsent">
				<h3>개인정보 수집 및 이용 동의</h3>
				<textarea class="user-signup-lawInfo" readonly></textarea>
				<span class="user-signup-lawSpan">개인정보 수집 및 이용에 동의하십니까?</span><input type="checkbox" class="user-signup-termsOfUse"><span class="user-signup-lawSpan">동의함</span>
			</div>
			<button class="user-signup-signupButton">회 원 가 입</button>
		</form>
       </div>
</div>