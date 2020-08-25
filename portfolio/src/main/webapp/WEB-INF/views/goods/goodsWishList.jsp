<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-wishList-box">
	<div class="user-wishList-wishListBox">
		<table class="user-wishList-goodsBox" border="1">
			<tr>
				<th><input type="checkbox" class="user-wishList-goodsCheckAll"></th>
				<th class="user-wishList-goodsImgTitle">이미지</th>
				<th class="user-wishList-goodsInfoTitle">제품 정보</th>
				<th class="user-wishList-goodsPriceTitle">판매가</th>
				<th class="user-wishList-goodsCountTitle">수량</th>
			</tr>
			<c:forEach var="wishList" items="${list}">
				<tr>
					<td><input type="checkbox" class="user-wishList-goodsCheck"></td>
					<td class="user-wishList-goodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg${wishList.goodsImg}" alt="" ></td>
					<td class="user-wishList-goodsInfo">제품명 : ${wishList.goodsName} / 색상 : ${wishList.optionColor}</td>
					<td class="user-wishList-goodsPrice">${wishList.goodsPrice}</td>
					<td class="user-wishList-goodsCount"><input type="number" value="${wishList.wishListCount}"></td>
				</tr>
			</c:forEach>
		</table>
		<div class="user-wishList-buttonBox">
			<button class="user-wishList-goodsGoCart">선택 항목<br>장바구니로 이동</button>
			<button class="user-wishList-goodsDelete">선택 삭제</button>
		</div>
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
	</div>
</div>