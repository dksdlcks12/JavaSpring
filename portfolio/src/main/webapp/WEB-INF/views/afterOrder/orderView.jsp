<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-orderView-box">
	<div class="user-orderView-orderViewBox">
		<c:if test="${list.size()!=0}">
			<h2>주문조회</h2>
			<div class="user-orderView-orderNumberBox">
				<div class="user-orderView-orderTitleNumber">주문번호</div>
				<div class="user-orderView-orderNumber">${orderNum}</div>
			</div>
			<div class="user-orderView-orderGoodsTitleBox">
				<div class="user-orderView-orderGoodsTitleImg">이미지</div>
				<div class="user-orderView-orderGoodsTitleName">제품명</div>
				<div class="user-orderView-orderGoodsTitleDisCountPrice">할인가</div>
				<div class="user-orderView-orderGoodsTitleCount">주문량</div>
				<div class="user-orderView-orderGoodsTitleUsePoint">사용포인트</div>
				<div class="user-orderView-orderGoodsTitlePayPrice">결제금액</div>
			</div>
			<c:forEach var="orderList" items="${list}">
			<div class="user-orderView-orderGoodsBox">
				<div class="user-orderView-orderGoodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${orderList.goodsImg}" alt=""></div>
				<div class="user-orderView-orderGoodsName">제품명 : ${orderList.goodsName}<br>색상 : ${orderList.optionColor}</div>
				<div class="user-orderView-orderGoodsDisCountPrice">${orderList.orderListPrice} 원</div>
				<div class="user-orderView-orderGoodsCount">${orderList.orderListCount} 개</div>
				<div class="user-orderView-orderGoodsUsePoint">${orderList.orderListUsePoint}</div>
				<div class="user-orderView-orderGoodsPayPrice"><span>${orderList.payPrice}</span> 원</div>
			</div>
			</c:forEach>
			<div class="user-orderView-totalPriceBox">
				<div class="user-orderView-totalPrice"></div>
			</div>	
			<div class="user-orderView-peopleInfo">
				<p>주문정보</p>
				<table class="user-orderView-Info" border="1">
					<tr>
						<td>주문한 사람</td>
						<td class="user-orderView-people">${order.orderSender}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td class="user-orderView-addressLine">
							<div class="user-orderView-postcodeBox">
								<div class="user-orderView-addressLineTitle">우편번호</div>
								<div class="user-orderView-postcode">${order.orderSenderPostCode}</div>
							</div>
							<div class="user-orderView-addressBox">
								<div class="user-orderView-addressLineTitle">주소</div>
								<div class="user-orderView-address">${order.orderSenderAddress}</div>
							</div>
							<div class="user-orderView-detailAddressBox">
								<div class="user-orderView-addressLineTitle">상세주소</div>
								<div class="user-orderView-detailAddress">${order.orderSenderDetailAddress}</div>
							</div>
							<div class="user-orderView-extraAddressBox">
								<div class="user-orderView-addressLineTitle">참고항목</div>
								<div class="user-orderView-extraAddress">${order.orderSenderExtraAddress}</div>
							</div>
						</td>
					</tr>
				</table>
				<p>배송정보</p>
				<table class="user-orderView-Info" border="1">
					<tr>
						<td>받는 사람</td>
						<td class="user-orderView-people">${order.orderReceiver}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td class="user-orderView-addressLine">
							<div class="user-orderView-postcodeBox">
								<div class="user-orderView-addressLineTitle">우편번호</div>
								<div class="user-orderView-postcode">${order.orderReceiverPostCode}</div>
							</div>
							<div class="user-orderView-addressBox">
								<div class="user-orderView-addressLineTitle">주소</div>
								<div class="user-orderView-address">${order.orderReceiverAddress}</div>
							</div>
							<div class="user-orderView-detailAddressBox">
								<div class="user-orderView-addressLineTitle">상세주소</div>
								<div class="user-orderView-detailAddress">${order.orderReceiverDetailAddress}</div>
							</div>
							<div class="user-orderView-extraAddressBox">
								<div class="user-orderView-addressLineTitle">참고항목</div>
								<div class="user-orderView-extraAddress">${order.orderReceiverExtraAddress}</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</c:if>
	</div>
</div>
<script>
	var totalPrice = 0;
	var defaultDeliveryPrice = 2500;
	var freeDeliveryLimit = 16000;
	$('.user-orderView-orderGoodsPayPrice').each(function(){
		totalPrice = totalPrice+ Number($(this).children('span').text());
		if(totalPrice<freeDeliveryLimit){
			$('.user-orderView-totalPrice').html('배송비 : '+ defaultDeliveryPrice + '원 + ' + totalPrice + '원  <br>= ' + Number(defaultDeliveryPrice+totalPrice) + '원');
		}else{
			$('.user-orderView-totalPrice').html('배송비 : 0 원 + ' + totalPrice + '원 <br>= ' + totalPrice + '원');
		}
	})
</script>