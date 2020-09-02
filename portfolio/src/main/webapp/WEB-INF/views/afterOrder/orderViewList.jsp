<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-orderList-box">
	<div class="admin-orderList-orderListBox">
		<c:if test="${list.size()!=0}">
			<h2>주문목록</h2>
			<div class="admin-orderList-orderTitleBox">
				<div class="admin-orderList-orderTitleNumber">주문번호</div>
				<div class="admin-orderList-orderTitleInfo">주문내용</div>
				<div class="admin-orderList-orderTitleDate">주문일시</div>
				<div class="admin-orderList-orderTitleState">주문상태</div>
			</div>
			<c:forEach var="order" items="${list}">
				<div class="admin-orderList-orderBox">
					<c:if test="${user.userAuth ne 'admin'}">
						<a href="<%=request.getContextPath()%>/orderview?orderNum=${order.orderNum}"><div class="admin-orderList-orderNumber">${order.orderNum}</div>
					</c:if>
					<c:if test="${user.userAuth eq 'admin'}">
						<a href="<%=request.getContextPath()%>/admin/orderview?orderNum=${order.orderNum}&page=${pm.criteria.page}"><div class="admin-orderList-orderNumber">${order.orderNum}</div>
					</c:if>
					<div class="admin-orderList-orderInfo">[제품명 : ${order.orderGoodsName} / 색상 : ${order.orderGoodsColor}]<c:if test="${order.orderGoodsCount>1}"> 외 ${order.orderGoodsCount-1} 종</c:if></div>
					<div class="admin-orderList-orderDate">${order.orderDate}</div>
					<div class="admin-orderList-orderState">${order.orderState}</div></a>
				</div>
			</c:forEach>
			<c:if test="${user.userAuth eq 'admin'}">
				<form action="<%=request.getContextPath()%>/orderview">
					<div class="admin-orderList-searchBox">
						<select name="type" class="admin-orderList-searchType">
							<option value="0">전체</option>
							<option value="1">주문번호</option>
							<option value="2">주문아이디</option>
							<option value="3">주문상태</option>
						</select>
						<input type="text" class="admin-orderList-searchContent" name="search">
						<button class="admin-orderList-searchButton"><i class="fas fa-search"></i></button>
					</div>
				</form>
			</c:if>
			<c:if test="${list.size()!=0}">
				<c:if test="${pm.endPage!=1}">
					<ul class="common-goodsList-pagination pagination justify-content-center">
						<c:if test="${pm.criteria.page!=1}">
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/orderviewlist?page=${pm.criteria.page-1}"><i class="fas fa-angle-left"></i></a></li>
						</c:if>
					    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
					        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
					            <a class="page-link" href="<%=request.getContextPath()%>/orderviewlist?page=${index}">${index}</a>
					        </li>
					    </c:forEach>
					    <c:if test="${pm.criteria.page!=pm.endPage}">
							<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/orderviewlist?page=${pm.criteria.page+1}"><i class="fas fa-angle-right"></i></a></li>
						</c:if>
					</ul>
				</c:if>
				<c:if test="${pm.criteria.page>pm.endPage}">
					<script>
						location.replace('<%=request.getContextPath()%>/goodslist?type=${type}&page=${pm.endPage}');
					</script>
				</c:if>
			</c:if>
		</c:if>
	</div>
	<c:if test="${list.size()==0 && pm.criteria.page>1}">
		<script>
			location.replace('<%=request.getContextPath()%>/goodslist?type=${type}&page=${pm.endPage}');
		</script>
	</c:if>
</div>