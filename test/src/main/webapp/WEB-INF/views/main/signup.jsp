<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<h1>회원가입</h1>
<form action="<%=request.getContextPath()%>/signup" method="POST">
	<div class="container-id">
		<div class="text-id">아이디</div>
		<div class="box-id">
			<input type="text" name="id" id="id">
		</div>
		<label class=id-dup-msg></label>
		<label id='id-error' class='error' for='id'></label>
	</div>
	<div class="container-pw">
		<div class="text-pw">비밀번호</div>
		<div class="box-pw">
			<input type="password" name="pw" id="pw">
			<a href="#"></a>
		</div>
		<label id='id-error' class='error' for='pw'></label>
	</div>
	<div class="container-pw">
		<div class="text-pw">비밀번호 확인</div>
		<div class="box-pw">
			<input type="password" name="pw2" id="pw2">
			<a href="#"></a>
		</div>
		<label id='id-error' class='error' for='pw2'></label>
	</div>
	<div class="container-gender">
		<div class="text-gender">성별</div>
		<div class="box-gender">
			<select name="gender" id="gender">
				<option value="">성별</option>
				<option value="male">남자</option>
				<option value="female">여자</option>
			</select>
		</div>
		<label id='id-error' class='error' for='gender'></label>
	</div>
	<div class="container-email">
		<div class="text-bold">본인확인 이메일</div>
		<div class="box-email">
			<input type="text" name="email" id="email">
		</div>
		<label id='id-error' class='error' for='email'></label>
	</div>
	<button class="btn-submit">가입하기</button>
</form>
<script>
	$(function(){
		$("#id").keyup(function(){
			var id = $("#id").val();//id가 id인 input 태그에 입력된 id 가져오기
			if(id.length>=4 && id.length<=21){
				$.ajax({
					async:true,
					type:'POST',
					data: id,
					url: "<%=request.getContextPath()%>/idcheck",
					dataType:"json",
					contentType:"application/json; charset=UTF-8",
					success : function(data){
						if(data['check']){
							$('.id-dup-msg').text('중복된 아이디 입니다.')
						}else{
							$('.id-dup-msg').text('사용가능한 아이디 입니다.')
						}
					}
				});
			}else{
				$('.id-dup-msg').empty()
			}
		});
	    $("form").validate({
	        rules: {
	            id: {
	                required : true,
	                minlength : 4,
	                maxlength : 21
	            },
	            pw: {
	                required : true,
	                minlength : 8,
	                regex: /^(?=\w{8,20}$)\w*(\d[A-z]|[A-z]\d)\w*$/
	            },
	            pw2: {
	                required : true,
	                minlength : 8,
	                equalTo : pw
	            },
	            gender: {
	                required : true,
	            },
	            email: {
	                required : true,
	                minlength : 2,
	                email : true
	            }
	        },
	        //규칙체크 실패시 출력될 메시지
	        messages : {
	            id: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                maxlength : "최대 {0}글자이하이어야 합니다"
	            },
	            pw: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
	            },
	            pw2: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                equalTo : "비밀번호가 일치하지 않습니다."
	            },
	            gender: {
	                required : "필수로입력하세요",
	            },
	            email: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                email : "메일규칙에 어긋납니다"
	            }
	        }
	    });
		$.validator.addMethod(
		    "regex",
		    function(value, element, regexp) {
		        var re = new RegExp(regexp);
		        return this.optional(element) || re.test(value);
		    },
		    "Please check your input."
		);
	})
</script>