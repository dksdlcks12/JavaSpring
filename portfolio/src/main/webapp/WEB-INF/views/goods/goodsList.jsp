<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-goodsList-box"> 
	<h2 class="common-goodsList-goodsTitle">제품타입</h2>
	<div class="common-goodsList-goodsListBox">
		<c:if test = "${list.size()!=0}">
			<c:forEach var="goods" items="${list}">
				<div class="common-goodsList-goodsBox">
					<img src="<%=request.getContextPath()%>/resources/image/goodsImg${goods.goodsImg}" class="common-goodsList-goods">
					<div class="common-goodsList-goodsInfo">
						<div class="common-goodsList-goodsName">제품명 : ${goods.goodsName}</div>
						<div class="common-goodsList-goodsPrice">가격 : ${goods.goodsPrice}원</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test = "${list.size()==0}">
		</c:if>
	</div>
	<ul class="common-goodsList-pagination pagination justify-content-center">
		<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
		<li class="page-item"><a class="page-link" href="#">1</a></li>
		<li class="page-item active"><a class="page-link" href="#">2</a></li>
		<li class="page-item"><a class="page-link" href="#">3</a></li>
		<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
	</ul>
</div>