<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-goodsSearch-box"> 
	<div class="common-goodsSearch-optionBox">
		<h2>상세검색</h2>
		<div class="common-goodSearch-optionSubBox"><div class="common-goodsSearch-optionTitle">제품명</div><input type="text" class="common-goodsSearch-option goodsName"></div>
		<div class="common-goodSearch-optionSubBox"><div class="common-goodsSearch-optionTitle">가격</div><input type="text" class="common-goodsSearch-option number common-goodsSearch-priceMin"><span>~</span><input type="text"  class="common-goodsSearch-option number common-goodsSearch-priceMax"></div>
		<div class="common-goodSearch-optionSubBox"><div class="common-goodsSearch-optionTitle">할인율</div><input type="text" class="common-goodsSearch-option number common-goodsSearch-discountMin" maxlength="3"><span>~</span><input type="text" class="common-goodsSearch-option number common-goodsSearch-discountMax" maxlength="3"></div>
		<button class="common-goodSearch-optionSearchButton">검 색</button>
	</div>
	<div class="common-goodsSearch-goodsSearchBox">
		<c:if test="${list.size()!=0}">
			<c:forEach var="goods" items="${list}">
				<div class="common-goodsSearch-goodsBox">
					<a href="<%=request.getContextPath()%>/goodsview?num=${goods.goodsNum}&type=0&page=1"><img src="<%=request.getContextPath()%>/resources/image/goodsImg${goods.goodsImg}" class="common-goodsSearch-goods"></a>
					<div class="common-goodsSearch-goodsInfo">
						<div class="common-goodsSearch-goodsName">제품명 : ${goods.goodsName}</div>
						<div class="common-goodsSearch-goodsPrice">가격 : ${goods.goodsPrice}원</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<c:if test="${list.size()!=0}">
		<c:if test="${pm.endPage!=1}">
			<ul class="common-goodsList-pagination pagination justify-content-center">
				<c:if test="${pm.criteria.page!=1}">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/goodssearch?page=${pm.criteria.page-1}&search=${search.search}&minPrice=${search.minPrice}&maxPrice=${search.maxPrice}&minDisCount=${search.minDisCount}&maxDisCount=${search.maxDisCount}"><i class="fas fa-angle-left"></i></a></li>
				</c:if>
			    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
			        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
			            <a class="page-link" href="<%=request.getContextPath()%>/goodssearch?page=${index}&search=${search.search}&minPrice=${search.minPrice}&maxPrice=${search.maxPrice}&minDisCount=${search.minDisCount}&maxDisCount=${search.maxDisCount}">${index}</a>
			        </li>
			    </c:forEach>
			    <c:if test="${pm.criteria.page!=pm.endPage}">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/goodssearch?page=${pm.criteria.page+1}&search=${search.search}&minPrice=${search.minPrice}&maxPrice=${search.maxPrice}&minDisCount=${search.minDisCount}&maxDisCount=${search.maxDisCount}"><i class="fas fa-angle-right"></i></a></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${pm.criteria.page>pm.endPage}">
			<script>
				location.replace('<%=request.getContextPath()%>/goodssearch?&page=${pm.endPage}&search=${search.search}&minPrice=${search.minPrice}&maxPrice=${search.maxPrice}&minDisCount=${search.minDisCount}&maxDisCount=${search.maxDisCount}');
			</script>
		</c:if>
	</c:if>
	<c:if test="${list.size()==0 && pm.criteria.page>1}">
		<script>
			location.replace('<%=request.getContextPath()%>/goodssearch?&page=${pm.endPage}&search=${search.search}&minPrice=${search.minPrice}&maxPrice=${search.maxPrice}&minDisCount=${search.minDisCount}&maxDisCount=${search.maxDisCount}');
		</script>
	</c:if>
</div>