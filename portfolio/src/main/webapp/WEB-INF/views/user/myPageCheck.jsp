<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-mypage-box">
	<div class="user-mypage-mypageBox">
		<h3 class="user-mypage-title">마 이 페 이 지</h3>
		<input type="password" class="common-login-inputInfo" placeholder="비밀번호를 입력하세요">
		<button class="common-login-loginButton">확인</button><br>
	</div>
</div>
<script>
	$('.common-login-loginButton').click(function(){
		var pw = $('.common-login-inputInfo').val();
		$.ajax({
			async:false,
			type:'POST',
			data:pw,
			url:"<%=request.getContextPath()%>/mypagecheckpw",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				if(data.pwCheck){
					location.replace('<%=request.getContextPath()%>/mypage');
				}else{
					alert('잘못된 비밀번호 입니다.')
				}
			}
		});
	})
</script>