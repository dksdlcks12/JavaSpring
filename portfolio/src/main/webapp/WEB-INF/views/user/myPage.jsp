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
		<form id="form1" action="<%=request.getContextPath()%>/mypage" method="POST">
			<table class="user-mypage-table" border="1">
				<tbody>
					<tr>
						<td class="user-mypage-rowTitle">비밀번호 수정</td>
						<td class="user-mypage-rowContent"><input type="password" class="user-mypage-inputInfo" name=userPw id=userPw> (영문자, 숫자 포함 4~8자) <label id='userPw-error' class='error' for='userPw'></label></td>
					</tr>
					<tr>
						<td class="user-mypage-rowTitle">비밀번호 확인</td>
						<td class="user-mypage-rowContent"><input type="password" class="user-mypage-inputInfo" name=userPw2 id=userPw2> <label id='userPw2-error' class='error' for='userPw2'></label></td>
					</tr>
					<tr>
						<td class="user-mypage-rowTitle">이메일 수정</td>
						<td class="user-mypage-rowContent"><input type="email" class="user-mypage-inputInfo" name=userMail> <label id='userMail-error' class='error' for='userMail'></label></td>
					</tr>
				</tbody>
			</table>
			<button class="user-mypage-mypageButton">수 정 완 료</button>
			<div class="user-mypage-userId" hidden>${user.userId}</div>
			<c:if test="${user.userAuth ne 'admin'}">
				<button type="button" class="user-mypage-memberOut">회 원 탈 퇴</button>
			</c:if>
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
					$('.user-mypage-userId').addClass('userOn')
				}else{
					alert('잘못된 비밀번호 입니다.')
					$('.common-login-inputInfo').val('');
				}
			}
		});
	})
	$('form').submit(function(){
		if($('.mypageOn').length!=0){
			return true;
		}else{
			return false;
		}
	})
	$(function(){
	    $("#form1").validate({
	        rules: {
	            userPw: {
	                minlength : 8,
	                maxlength : 16,
	                regex: /^(?=\w{8,16}$)\w*(\d[A-z]|[A-z]\d)\w*$/
	            },
	            userPw2: {
	                equalTo : userPw
	            },
	            userMail: {
	                minlength : 2,
	                email : true
	            }
	        },
	        //규칙체크 실패시 출력될 메시지
	        messages : {
	            userPw: {
	                minlength : "최소 {0}글자이상이어야 합니다",
	                maxlength : "최대 {0}글자이하이어야 합니다",
	                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
	            },
	            userPw2: {
	                equalTo : "비밀번호가 일치하지 않습니다."
	            },
	            userMail: {
	                minlength : "최소 {0}글자이상이어야 합니다",
	                email : "메일규칙에 어긋납니다"
	            }
	        }
	    });
	})
	$.validator.addMethod(
	    "regex",
	    function(value, element, regexp) {
	        var re = new RegExp(regexp);
	        return this.optional(element) || re.test(value);
	    },
	    "Please check your input."
	);
	$('.user-mypage-memberOut').click(function(){
		var userId = $('.userOn').text();
		$.ajax({
			async:false,
			type:'POST',
			data:userId,
			url:"<%=request.getContextPath()%>/mypagedel",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				if(data.userDel){
					alert('성공적으로 탈퇴하였습니다.');
					location.replace('<%=request.getContextPath()%>/'); 
				}else{
					alert('잘못된 접근입니다.');
					location.replace('<%=request.getContextPath()%>/');
				}
			}
		});
	})
</script>