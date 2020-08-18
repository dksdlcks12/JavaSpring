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
						<td class="user-signup-rowContent"><input type="text" class="user-signup-inputInfo user-signup-id" name="userId" id="userId"> (영문자/숫자, 8~16자) <label id='id-error' class='msgId'></label></td>
					</tr>
					<tr>
						<td class="user-signup-rowTitle">비밀번호(필수)</td>
						<td class="user-signup-rowContent"><input type="password" class="user-signup-inputInfo" name="userPw" id="userPw"> (영문자/숫자 조합, 8~16자) <label id='id-error' class='error' for='userPw'></label></td>
					</tr>
					<tr>
						<td class="user-signup-rowTitle">비밀번호 확인(필수)</td>
						<td class="user-signup-rowContent"><input type="password" class="user-signup-inputInfo" name="pwcheck" id="pwcheck"> <label id='id-error' class='error' for='pwcheck'></label></td>
					</tr>
					<tr>
						<td class="user-signup-rowTitle">이메일(필수)</td>
						<td class="user-signup-rowContent"><input type="email" class="user-signup-inputInfo" name="userMail" id="userMail"> (비밀번호를 찾을 때 필요합니다.) <label id='id-error' class='error' for='userMail'></label></td>
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
<script>
	var check = false;
	$(".user-signup-id").on("keyup",function(){
		check = false;
		if($(this).siblings('.user-signup-inputInfo').val()!=''){
			var id = $("#userId").val();//id가 id인 input 태그에 입력된 id 가져오기
			var idReg = /^[A-Za-z]/;
			if(id.length!=0){
				if(idReg.test(id)){
					idReg = /[A-Za-z0-9]/;
					if(idReg.test(id)){
						if(id.length>=8 && id.length<=16){
							$.ajax({
								async:false,
								type:'POST',
								data:id,
								url:"<%=request.getContextPath()%>/signup/idcheck",
								dataType:"json",
								contentType:"application/json; charset=UTF-8",
								success : function(data){
									if(data.idCheck){
										$('.msgId').text('중복된 아이디 입니다.')
										check = false;
									}else{
										$('.msgId').text('사용 가능한 아이디 입니다.')
										check = true;
									}
								}
							});
						}else{
							$('.msgId').text('아이디는 8자이상 16자 이하로 가능합니다.')
						}
					}else{
						$('.msgId').text('아이디는 영문자와 숫자만 사용 가능 합니다.')
						console.log("아이디는 영문자와 숫자만 사용 가능 합니다.")
					}
				}else{
					$('.msgId').text('아이디는 영문자로 시작해야 합니다.')
					console.log("아이디는 영문자로 시작해야 합니다.");
				}
			}else{
				$('.msgId').text('필수로 입력하세요.')
			}
		}
	});
	$(".user-signup-signupButton").click(function(){
		if(check){
			if($('.user-signup-lawBox').children('.user-signup-termsOfUse').is(':checked')){
				if($('.user-signup-personalInfoConsent').children('.user-signup-termsOfUse').is(':checked')){
					$("form").on("submit",function(){
						return true;
					});
				}else{
					alert("개인정보 수집및 이용에 동의 해주십시오");
					return false;
				}
			}else{
				alert("이용약관을 동의 해주십시오.");
				return false;
			}
		}else{
			alert("아이디 중복확인을 하십시오.");
			return false;
		}
	})
	$(function(){
	    $("form").validate({
	        rules: {
	            userId: {
	                required : true,
	                minlength : 8,
	                maxlength : 16
	            },
	            userPw: {
	                required : true,
	                minlength : 8,
	                regex: /^(?=\w{8,20}$)\w*(\d[A-z]|[A-z]\d)\w*$/
	            },
	            pwcheck: {
	                required : true,
	                equalTo : userPw
	            },
	            userMail: {
	                required : true,
	                minlength : 2,
	                email : true
	            }
	        },
	        //규칙체크 실패시 출력될 메시지
	        messages : {
	            userId: {
	                required : "",
	                minlength : "",
	                maxlength : ""
	            },
	            userPw: {
	                required : "필수로 입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
	            },
	            pwcheck: {
	                required : "필수로 입력하세요",
	                equalTo : "비밀번호가 일치하지 않습니다."
	            },
	            userMail: {
	                required : "필수로 입력하세요",
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
</script>