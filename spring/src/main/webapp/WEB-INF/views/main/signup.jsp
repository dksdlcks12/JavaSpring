<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<form action="<%=request.getContextPath()%>/signup" method="post">
	<div class="container-body">
		<div class="container-id">
			<div class="text-id">아이디</div>
			<div class="box-id">
				<input type="text" name="id" id="id">
			</div>
			<label id='id-error' class='error' for='id'></label>
			<div class="dup-fail-msg display-none" >이미 사용중이거나 탈퇴한 ID입니다.</div>
			<div class="dup-suc-msg display-none" >멋진 ID네요!</div>
		</div>
		<div class="container-pw">
			<div class="text-pw">비밀번호</div>
			<div class="box-pw">
				<input type="password" name="pw" id="pw">
				<a href="#"></a>
			</div>
		</div>
		<div class="container-pw">
			<div class="text-pw">비밀번호 확인</div>
			<div class="box-pw">
				<input type="password" name="pw2" id="pw2">
				<a href="#"></a>
			</div>
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
		</div>
		<div class="container-email">
			<div class="text-bold">본인확인 이메일</div>
			<div class="box-email">
				<input type="text" name="email" id="email">
			</div>
		</div>
		<button class="btn-submit">가입하기</button>
	</div>
</form>
<script>
	$(function(){
		$('#id').keyup(function(){
			var id = $(this).val();
			if(id.length >= 4){
				$.ajax({
				    //동기화, 비동기화 결정
			        async:true,
			        //전송방식(GET, POST)
			        type:'POST',
			        data:id,
			        url:"<%=request.getContextPath()%>/idCheck",
			        dataType:"json",
			        contentType:"application/json; charset=UTF-8",
			        success : function(data){
				        if(data['check']){       
					        $('.dup-suc-msg').removeClass('display-none')
					        $('.dup-fail-msg').addClass('display-none')
						}else{
					        $('.dup-fail-msg').removeClass('display-none')
					    	$('.dup-suc-msg').addClass('display-none')
						}
			        }
				});
			}else{
				$('.dup-fail-msg').addClass('display-none')
				$('.dup-suc-msg').addClass('display-none')
			}
		})
		$("form").validate({
	        rules: {
	            id: {
	                required : true,
	                minlength : 4
	            },
	            pw: {
	                required : true,
	                minlength : 8,
	                maxlength : 20,
	                regex: /^\w*(\d[A-z]|[A-z]\d)\w*$/
	            },
	            pw2: {
	                required : true,
	                minlength : 8,
	                equalTo : pw
	            },
	            email: {
	                required : true,
	                email : true
	            },
	            gender: {
	                required : true
	            }
	        },
	        //규칙체크 실패시 출력될 메시지
	        messages : {
	            id: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다"
	            },
	            pw: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                maxlength : "최대 {0}글자이하이어야 합니다",
	                regex : "영문자, 숫자로 이루어져있으며 최소 하나이상 포함"
	            },
	            pw2: {
	                required : "필수로입력하세요",
	                minlength : "최소 {0}글자이상이어야 합니다",
	                equalTo : "비밀번호가 일치하지 않습니다."
	            },
	            email: {
	                required : "필수로입력하세요",
	                email : "메일규칙에 어긋납니다"
	            },
	            gender: {
		            required : "필수로입력하세요"
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