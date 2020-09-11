<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-main-box">
	<h2 class="admin-main-title">미확인 주문 (오래된 순 6개)</h2>
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
	
	<h2 class="admin-main-title">미확인 반품 (오래된 순 6개)</h2>
	<div class="admin-main-contentBox">
		<div class="admin-main-leftContent">
			<c:forEach var="recall" items="${recallList}">
				<div class="admin-main-boardLine">
					<a href="<%=request.getContextPath()%>/admin/recallview?recallNum=${recall.recallNum}&page=1&type=0&search=" class="admin-main-board">
						<div class="admin-main-boardTitle">[제품명 : ${recall.goodsName} / 색상 : ${recall.goodsColor}]<c:if test="${recall.goodsCount>1}"> 외 ${recall.goodsCount-1} 종</c:if></div>
					</a>
					<div class="admin-main-boardDate">${recall.recallDate}</div>
				</div>
			</c:forEach>
		</div>
		<div class="admin-main-rightContent">
			미확인 반품 개수 : ${countUncheckRecall} 개
		</div>
	</div>
	<h2 class="admin-main-title">미확인 A/S (오래된 순 6개)</h2>
	<div class="admin-main-contentBox">
		<div class="admin-main-leftContent">
			<c:forEach var="as" items="${asList}">
				<div class="admin-main-boardLine">
					<a href="<%=request.getContextPath()%>/admin/asview?asNum=${as.asNum}&page=1&type=0&search=" class="admin-main-board">
						<div class="admin-main-boardTitle">${as.asTitle}</div>
					</a>
					<div class="admin-main-boardDate">${as.asDate}</div>
				</div>
			</c:forEach>
		</div>
		<div class="admin-main-rightContent">
			미확인 A/S 개수 : ${countUncheckAs}개
		</div>
	</div>
	<h2 class="admin-main-title">미답변 Q&A (오래된 순 6개)</h2>
	<div class="admin-main-contentBox">
		<div class="admin-main-leftContent">
			<c:forEach var="qa" items="${qaList}">
				<div class="admin-main-boardLine">
					<a href="<%=request.getContextPath()%>/qaview?qaNum=${qa.qaNum}&page=1&type=0&search=" class="admin-main-board">
						<div class="admin-main-boardTitle">${qa.qaTitle}</div>
					</a>
					<div class="admin-main-boardDate">${qa.qaDate}</div>
				</div>
			</c:forEach>
		</div>
		<div class="admin-main-rightContent">
			미답변 Q&A 개수 : ${countUncheckQa}개
		</div>
	</div>
</div>