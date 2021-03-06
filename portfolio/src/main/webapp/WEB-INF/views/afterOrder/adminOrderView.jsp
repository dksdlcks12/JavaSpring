<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-orderView-box">
	<div class="admin-orderView-orderViewBox">
		<h2>주문조회</h2>
		<div class="admin-orderView-orderNumberBox">
			<div class="admin-orderView-orderTitleNumber">주문번호</div>
			<div class="admin-orderView-orderNumber">${orderNum}</div>
		</div>
		<div class="admin-orderView-orderGoodsTitleBox">
			<div class="admin-orderView-orderGoodsTitleType">제품타입</div>
			<div class="admin-orderView-orderGoodsTitleName">제품명</div>
			<div class="admin-orderView-orderGoodsTitleColor">색상</div>
			<div class="admin-orderView-orderGoodsTitleCount">주문량</div>
		</div>
		<c:forEach var="orderList" items="${list}">
		<div class="admin-orderView-orderGoodsBox">
			<div class="admin-orderView-orderGoodsType"><c:if test="${orderList.goodsType==1}">목걸이</c:if><c:if test="${orderList.goodsType==2}">반지</c:if><c:if test="${orderList.goodsType==3}">귀찌</c:if><c:if test="${orderList.goodsType==4}">귀걸이</c:if></div>
			<div class="admin-orderView-orderGoodsName">${orderList.goodsName}</div>
			<div class="admin-orderView-orderGoodsColor">${orderList.optionColor}</div>
			<div class="admin-orderView-orderGoodsCount">${orderList.orderListCount} 개</div>
		</div>
		</c:forEach>
		<div class="admin-orderView-peopleInfo">
			<p>주문정보</p>
			<table class="admin-orderView-Info" border="1">
				<tr>
					<td>주문한 사람</td>
					<td class="admin-orderView-people">${order.orderSender}</td>
				</tr>
				<tr>
					<td>주소</td>
					<td class="admin-orderView-addressLine">
						<div type="text" class="admin-orderView-postcodeBox">
							<div class="admin-orderView-addressLineTitle">우편번호</div>
							<div class="admin-orderView-postcode">${order.orderSenderPostCode}</div>
						</div>
						<div type="text" class="admin-orderView-addressBox">
							<div class="admin-orderView-addressLineTitle">주소</div>
							<div class="admin-orderView-address">${order.orderSenderAddress}</div>
						</div>
						<div type="text" class="admin-orderView-detailAddressBox">
							<div class="admin-orderView-addressLineTitle">상세주소</div>
							<div class="admin-orderView-detailAddress">${order.orderSenderDetailAddress}</div>
						</div>
						<div type="text" class="admin-orderView-extraAddressBox">
							<div class="admin-orderView-addressLineTitle">참고항목</div>
							<div class="admin-orderView-extraAddress">${order.orderSenderExtraAddress}</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>주문한 사람 전화번호</td>
					<td class="admin-orderView-people">${order.orderSenderTel}</td>
				</tr>
			</table>
			<p>배송정보</p>
			<table class="admin-orderView-Info" border="1">
				<tr>
					<td>받는 사람</td>
					<td class="admin-orderView-people">${order.orderReceiver}</td>
				</tr>
				<tr>
					<td>주소</td>
					<td class="admin-orderView-addressLine">
						<div type="text" class="admin-orderView-postcodeBox">
							<div class="admin-orderView-addressLineTitle">우편번호</div>
							<div class="admin-orderView-postcode">${order.orderReceiverPostCode}</div>
						</div>
						<div type="text" class="admin-orderView-addressBox">
							<div class="admin-orderView-addressLineTitle">주소</div>
							<div class="admin-orderView-address">${order.orderReceiverAddress}</div>
						</div>
						<div type="text" class="admin-orderView-detailAddressBox">
							<div class="admin-orderView-addressLineTitle">상세주소</div>
							<div class="admin-orderView-detailAddress">${order.orderReceiverDetailAddress}</div>
						</div>
						<div type="text" class="admin-orderView-extraAddressBox">
							<div class="admin-orderView-addressLineTitle">참고항목</div>
							<div class="admin-orderView-extraAddress">${order.orderReceiverExtraAddress}</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>받는 사람 전화번호</td>
					<td class="admin-orderView-people">${order.orderReceiverTel}</td>
				</tr>
			</table>
		</div>
		<div class="admin-orderView-orderStateBox">
			<div class="admin-orderView-orderTitleState">주문상태</div>
			<select class="admin-orderView-orderState">
				<option value="미확인" <c:if test="${order.orderState eq '미확인'}">selected</c:if>>미확인</option>
				<option value="배송준비중" <c:if test="${order.orderState eq '배송준비중'}">selected</c:if>>배송준비중</option>
				<option value="배송중" <c:if test="${order.orderState eq '배송중'}">selected</c:if>>배송중</option>
				<option value="배송완료" <c:if test="${order.orderState eq '배송완료'}">selected</c:if>>배송완료</option>
			</select>
		</div>
		<a href="<%=request.getContextPath()%>/orderviewlist?page=${page}&type=${type}&search=${search}"><button class="admin-orderView-goList">목록으로</button></a>
		<button class="admin-orderView-orderStateButton">주문상태 수정</button>
	</div>
</div>
<script>
	$('.admin-orderView-orderStateButton').click(function(){
		var orderNum = $('.admin-orderView-orderNumber').text();
		var orderState = $('.admin-orderView-orderState').val();
		var arr = [];
		arr.push({'orderNum':orderNum,'orderState':orderState});
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/admin/orderstatemodify",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				alert('주문상태를 수정하였습니다.')
				location.reload();
			}
		});
	})
</script>