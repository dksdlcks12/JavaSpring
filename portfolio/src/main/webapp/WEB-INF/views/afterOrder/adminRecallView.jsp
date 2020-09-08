<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-recallView-box">
	<div class="admin-recallView-recallViewBox">
		<c:if test="${list.size()!=0}">
			<h2>반품조회</h2>
			<div class="admin-recallView-recallNumberBox">
				<div class="admin-recallView-recallTitleNumber">반품번호</div>
				<div class="admin-recallView-recallNumber">${recallInfo.recallNum}</div>
				<div class="admin-recallView-orderTitleNumber">주문번호</div>
				<div class="admin-recallView-orderNumber">${recallInfo.orderNum}</div>
			</div>
			<div class="admin-recallView-recallGoodsTitleBox">
				<div class="admin-recallView-recallGoodsTitleType">제품타입</div>
				<div class="admin-recallView-recallGoodsTitleName">제품명</div>
				<div class="admin-recallView-recallGoodsTitleColor">색상</div>
			</div>
			<c:forEach var="goods" items="${list}">
				<div class="admin-recallView-recallGoodsBox">
					<div class="admin-recallView-recallGoodsType"><c:if test="${goods.goodsType==1}">목걸이</c:if><c:if test="${goods.goodsType==2}">반지</c:if><c:if test="${goods.goodsType==3}">귀찌</c:if><c:if test="${goods.goodsType==4}">귀걸이</c:if></div>
					<div class="admin-recallView-recallGoodsName">${goods.goodsName}</div>
					<div class="admin-recallView-recallGoodsColor">${goods.optionColor}</div>
				</div>
			</c:forEach>
			<div class="admin-recallView-recallReasonBox">
				<div>반품사유</div><div>${recallInfo.recallReason}</div>
				<div>교환여부</div><div>${recallInfo.recallIsChange}</div>
			</div>
			<c:if test="${recallInfo.recallAccount ne null}">
				<div class="admin-recallView-recallReasonBox">
					<div>은행명</div><div>${recallInfo.recallBankName}</div>
					<div>환불계좌</div><div class="admin-recallView-recallAccount">${recallInfo.recallAccount}</div>
				</div>
			</c:if>
			<div class="admin-recallView-board">${recallInfo.recallContent}</div>
			<div class="admin-recallView-recallStateBox">
				<div class="admin-recallView-recallTitleState">반품상태</div>
				<select class="admin-recallView-recallState">
					<option <c:if test="${recallInfo.recallState eq '미확인'}">selected</c:if>>미확인</option>
					<option <c:if test="${recallInfo.recallState eq '반품대기'}">selected</c:if>>반품대기</option>
					<option <c:if test="${recallInfo.recallState eq '배송중'}">selected</c:if>>배송중</option>
					<option <c:if test="${recallInfo.recallState eq '반품완료'}">selected</c:if>>반품완료</option>
					<option <c:if test="${recallInfo.recallState eq '반품불가'}">selected</c:if>>반품불가</option>
				</select>
			</div>
			<a href="<%=request.getContextPath()%>/recallviewlist?page=${page}&type=${type}&search=${search}"><button class="admin-recallView-goList">목록으로</button></a>
			<button class="admin-recallView-recallStateButton">반품상태 수정</button>
		</c:if>
	</div>
</div>
<script>
	$('.admin-recallView-board').html($('.admin-recallView-board').text());
	$('.admin-recallView-recallStateButton').click(function(){
		var recallNum = Number($('.admin-recallView-recallNumber').text());
		var recallState = $('.admin-recallView-recallState').val();
		var arr = [];
		arr.push({"recallNum":recallNum, "recallState":recallState})
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/admin/recallstatemodify",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				alert('반품상태를 수정하였습니다.')
				location.reload();
			}
		});
	})
</script>