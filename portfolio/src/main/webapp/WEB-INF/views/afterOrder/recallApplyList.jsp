<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<div class="user-recallApplyList-box">
	<div class="user-recallApplyList-recallApplyListBox">
		<h2 class="user-recallApplyList-title">반품신청</h2>
		<table class="user-recallApplyList-orderTitle" border="1">
			<tr>
				<th class="user-recallApplyList-orderNumberTitle">주문 번호</th>
				<th class="user-recallApplyList-orderInfoTitle">주문 정보</th>
				<th class="user-recallApplyList-addressTitle">보낸사람 / 받는사람</th>
				<th class="user-recallApplyList-orderDateTitle">주문날짜</th>
			</tr>
		</table>
		<c:forEach var="order" items="${list}">
			<div class="user-recallApplyList-orderBox">
				<a href="#">
					<div class="user-recallApplyList-orderNumber">${order.orderNum}</div>
					<div class="user-recallApplyList-orderInfo">[제품명 : ${order.orderGoodsName} / 색상 : ${order.orderGoodsColor}]<c:if test="${order.orderGoodsCount>1}"> 외 ${order.orderGoodsCount-1} 종</c:if></div>
					<div class="user-recallApplyList-address">${order.orderSender} / ${order.orderReceiver}</div>
			    	<div class="user-recallApplyList-orderDate">${order.orderDate}</div>
				</a>
			</div>
		</c:forEach>
		<ul class="user-recallApplyList-pagination pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
		</ul>
	</div>
</div>