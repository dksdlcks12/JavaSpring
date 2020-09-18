<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-login-box">
	<div class="common-login-loginBox">
		<div class="common-nonMemberOrderView-title">비회원 주문 조회</div>
		<input type="text" class="common-login-inputInfo number nonMemberOrderNum" placeholder="주문번호를 입력하세요">
		<input type="password" class="common-login-inputInfo noneMemberPassword" maxlength="8" placeholder="주문확인용 비밀번호를 입력하세요">
		<button class="common-login-loginButton">조회하기</button><br>
	</div>
</div>
<script>
	$('.common-login-loginButton').click(function(){
		var arr = [];
		var orderNum = Number($('.nonMemberOrderNum').val());
		var orderPw = $('.noneMemberPassword').val();
		arr.push({"orderNum":orderNum, "orderPw":orderPw});
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/nonmemberorderviewcheck",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				if(data.pwCheck){
					location.replace('<%=request.getContextPath()%>/nonememberorderviewitem?orderNum='+orderNum);
				}else{
					alert("존재하지 않는 주문번호 또는 잘못된 비밀번호 입니다.")
				}
			}
		});
	})
</script>