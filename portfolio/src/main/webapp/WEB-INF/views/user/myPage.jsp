<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-mypage-box">
	<div class="user-mypage-mypageBox mypagecheck">
		<h3 class="user-mypage-title">마 이 페 이 지</h3>
		<input type="password" class="common-login-inputInfo" placeholder="비밀번호를 입력하세요">
		<button class="common-login-loginButton">확인</button><br>
	</div>
	<div class="user-mypage-mypageBox mypage" style="display: none;">
		<h3 class="user-mypage-title">마 이 페 이 지</h3>
		<form>
			<table class="user-mypage-table" border="1">
				<tbody>
					<tr>
						<td class="user-mypage-rowTitle">비밀번호 수정</td>
						<td class="user-mypage-rowContent"><input type="password" class="user-mypage-inputInfo" name=userPw> (영문자, 숫자 포함 4~8자)</td>
					</tr>
					<tr>
						<td class="user-mypage-rowTitle">비밀번호 확인</td>
						<td class="user-mypage-rowContent"><input type="password" class="user-mypage-inputInfo"></td>
					</tr>
					<tr>
						<td class="user-mypage-rowTitle">이메일 수정</td>
						<td class="user-mypage-rowContent"><input type="email" class="user-mypage-inputInfo" name=userMail></td>
					</tr>
				</tbody>
			</table>
			<button class="user-mypage-mypageButton">수 정 완 료</button>
			<button type="button" class="user-mypage-memberOut">회 원 탈 퇴</button>
		</form>
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
					$('.mypage').css('display', 'block');
					$('.mypagecheck').css('display', 'none');
					$('.user-mypage-mypageButton').addClass('mypageOn')
				}else{
					alert('잘못된 비밀번호 입니다.')
					$('.common-login-inputInfo').val('');
				}
			}
		});
	})
	$('form').submit(function(){
		if($('.mypageOn').length!=0){
			alert('정상작동')
			return true;
		}else{
			alert('오류')
			return false;
		}
	})
</script>