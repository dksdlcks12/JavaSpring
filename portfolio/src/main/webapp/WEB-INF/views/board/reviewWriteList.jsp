<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-reviewOrderList-box">
	<div class="user-reviewOrderList-reviewOrderListBox">
		<h2>리뷰선택</h2>
		<table class="user-reviewOrderList-goodsBox" border="1">
		<tr>
			<th class="user-reviewOrderList-goodsImgTitle">이미지</th>
			<th class="user-reviewOrderList-goodsInfoTitle">제품 정보</th>
			<th class="user-reviewOrderList-addressTitle">주문날짜</th>
		</tr>
		</table>
		<c:forEach var="orderList" items="${list}">
			<div class="user-reviewOrderList-line">
				<div class="user-reviewOrderList-goodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${orderList.goodsImg}"></div>
				<a href="<%=request.getContextPath()%>/reviewwrite?orderListNum=${orderList.orderListNum}"><div class="user-reviewOrderList-goodsInfo">[제품명 : ${orderList.goodsName} / 색상 : ${orderList.optionColor}]</div></a>
				<div class="user-reviewOrderList-address">${orderList.orderDate}</div>
			</div>
		</c:forEach>
	</div>
</div>