<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-goodsList-box">
	<h2 class="common-goodsList-goodsTitle"><c:if test="${type==1}">목걸이</c:if><c:if test="${type==2}">반 지</c:if><c:if test="${type==3}">귀 찌</c:if><c:if test="${type==4}">귀걸이</c:if></h2>
	<div class="common-goodsList-goodsListBox">
		<c:if test="${list.size()!=0}">
			<c:if test="${pm.criteria.page<=pm.endPage&&pm.criteria.page>=1}">
				<c:forEach var="goods" items="${list}">
					<div class="common-goodsList-goodsBox">
						<a href="<%=request.getContextPath()%>/goodsview?num=${goods.goodsNum}&type=${type}&page=${pm.criteria.page}"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${goods.goodsImg}" class="common-goodsList-goods"></a>
						<div class="common-goodsList-goodsInfo">
							<div class="common-goodsList-goodsName">제품명 : ${goods.goodsName}</div>
							<div class="common-goodsList-goodsPrice">가격 : ${goods.goodsPrice}원</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</c:if>
	</div>
	<c:if test="${list.size()!=0}">
		<c:if test="${pm.endPage!=1}">
			<ul class="common-goodsList-pagination pagination justify-content-center">
				<c:if test="${pm.criteria.page!=1}">
				<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/goodsList?type=${type}&page=${pm.criteria.page-1}"><i class="fas fa-angle-left"></i></a></li>
				</c:if>
			    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
			        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
			            <a class="page-link" href="<%=request.getContextPath()%>/goodsList?type=${type}&page=${index}">${index}</a>
			        </li>
			    </c:forEach>
			    <c:if test="${pm.criteria.page!=pm.endPage}">
				<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/goodsList?type=${type}&page=${pm.criteria.page+1}"><i class="fas fa-angle-right"></i></a></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${pm.criteria.page>pm.endPage}">
			<script>
				location.href = "<%=request.getContextPath()%>/goodsList?type=${type}&page=${pm.endPage}"
			</script>
		</c:if>
	</c:if>
</div>