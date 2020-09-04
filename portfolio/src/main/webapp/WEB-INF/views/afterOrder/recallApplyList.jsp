<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<div class="user-recallApplyList-box">
	<div class="user-recallApplyList-recallApplyListBox">
		<c:if test="${list.size()!=0}">
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
					<a href="<%=request.getContextPath()%>/recallapply?orderNum=${order.orderNum}">
						<div class="user-recallApplyList-orderNumber">${order.orderNum}</div>
						<div class="user-recallApplyList-orderInfo">[제품명 : ${order.orderGoodsName} / 색상 : ${order.orderGoodsColor}]<c:if test="${order.orderGoodsCount>1}"> 외 ${order.orderGoodsCount-1} 종</c:if></div>
						<div class="user-recallApplyList-address">${order.orderSender} / ${order.orderReceiver}</div>
				    	<div class="user-recallApplyList-orderDate">${order.orderDate}</div>
					</a>
				</div>
			</c:forEach>
			<c:if test="${pm.endPage!=1}">
				<ul class="common-goodsList-pagination pagination justify-content-center">
					<c:if test="${pm.criteria.page!=1}">
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/recallapplylist?page=${pm.criteria.page-1}"><i class="fas fa-angle-left"></i></a></li>
					</c:if>
				    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
				        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
				            <a class="page-link" href="<%=request.getContextPath()%>/recallapplylist?page=${index}">${index}</a>
				        </li>
				    </c:forEach>
				    <c:if test="${pm.criteria.page!=pm.endPage}">
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/recallapplylist?page=${pm.criteria.page+1}"><i class="fas fa-angle-right"></i></a></li>
					</c:if>
				</ul>
			</c:if>
			<c:if test="${pm.criteria.page>pm.endPage}">
				<script>
					location.replace('<%=request.getContextPath()%>/recallapplylist?&page=${pm.endPage}');
				</script>
			</c:if>
		</c:if>
	</div>
	<c:if test="${list.size()==0 && pm.criteria.page>1}">
		<script>
			location.replace('<%=request.getContextPath()%>/recallapplylist?&page=${pm.endPage}');
		</script>
	</c:if>
</div>