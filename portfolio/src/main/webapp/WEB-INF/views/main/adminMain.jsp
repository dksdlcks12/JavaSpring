<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-main-box">
	<h2 class="admin-main-title">미확인 주문 (최근 6개)</h2>
	<div class="admin-main-contentBox">
		<div class="admin-main-leftContent">
			<c:forEach var="order" items="${list}">
				<div class="admin-main-boardLine">
					<a href="<%=request.getContextPath()%>/admin/orderview?orderNum=${order.orderNum}&page=1&type=0&search=" class="admin-main-board">
						<div class="admin-main-boardTitle">[제품명 : ${order.orderGoodsName} / 색상 : ${order.orderGoodsColor}]<c:if test="${order.orderGoodsCount>1}"> 외 ${order.orderGoodsCount-1} 종</c:if></div>
					</a>
					<div class="admin-main-boardDate">${order.orderDate}</div>
				</div>
			</c:forEach>
		</div>
		<div class="admin-main-rightContent">
			미확인 주문 개수 : ${countUncheckOrder} 개
		</div>
	</div>
	<h2 class="admin-main-title">미확인 반품</h2>
	<div class="admin-main-contentBox">
		<div class="admin-main-leftContent">
			<div class="admin-main-boardLine">
				<a href="#" class="admin-main-board">
					<div class="admin-main-boardTitle">미확인 반품 1</div>
				</a>
				<div class="admin-main-boardDate">2020/08/10 11:33:22</div>
			</div>
		</div>
		<div class="admin-main-rightContent">
			미확인 반품 개수 : ${countUncheckRecall} 개
		</div>
	</div>
	<h2 class="admin-main-title">미확인 A/S</h2>
	<div class="admin-main-contentBox">
		<div class="admin-main-leftContent">
			<div class="admin-main-boardLine">
				<a href="#" class="admin-main-board">
					<div class="admin-main-boardTitle">미확인 A/S 1</div>
				</a>
				<div class="admin-main-boardDate">2020/08/10 11:33:22</div>
			</div>
		</div>
		<div class="admin-main-rightContent">
			미확인 A/S 개수 : n개
		</div>
	</div>
	<h2 class="admin-main-title">미확인 Q&A</h2>
	<div class="admin-main-contentBox">
		<div class="admin-main-leftContent">
			<div class="admin-main-boardLine">
				<a href="#" class="admin-main-board">
					<div class="admin-main-boardTitle">미확인 Q&A 1</div>
				</a>
				<div class="admin-main-boardDate">2020/08/10 11:33:22</div>
			</div>
		</div>
		<div class="admin-main-rightContent">
			미확인 Q&A 개수 : n개
		</div>
	</div>
</div>