<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-recallList-box">
	<div class="admin-recallList-orderListBox">
		<c:if test="${list.size()!=0}">
			<h2>반품목록</h2>
			<div class="admin-recallList-orderTitleBox">
				<div class="admin-recallList-orderTitleNumber">반품번호</div>
				<div class="admin-recallList-orderTitleInfo"> 반품내용</div>
				<div class="admin-recallList-orderTitleDate">반품날짜</div>
				<div class="admin-recallList-orderTitleState">반품상태</div>
			</div>
			<c:forEach var="recallList" items="${list}">
				<div class="admin-recallList-orderBox">
					<div class="admin-recallList-orderNumber">${recallList.recallNum}</div>
					<c:if test="${user.userAuth ne 'admin'}">
						<a href="<%=request.getContextPath()%>/recallview?recallNum=${recallList.recallNum}&page=${pm.criteria.page}"><div class="admin-recallList-orderInfo">[제품명 : ${recallList.goodsName} / 색상 : ${recallList.goodsColor}] <c:if test="${recallList.goodsCount>1}">외 ${recallList.goodsCount-1}종</c:if></div></a>
					</c:if>
					<c:if test="${user.userAuth eq 'admin'}">
						<a href="<%=request.getContextPath()%>/admin/recallview?recallNum=${recallList.recallNum}&page=${pm.criteria.page}&type=${pm.criteria.type}&search=${pm.criteria.search}"><div class="admin-recallList-orderInfo">[제품명 : ${recallList.goodsName} / 색상 : ${recallList.goodsColor}] <c:if test="${recallList.goodsCount>1}">외 ${recallList.goodsCount-1}종</c:if></div></a>
					</c:if>
					<div class="admin-recallList-orderDate">${recallList.recallDate}</div>
					<div class="admin-recallList-orderState">${recallList.recallState}</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${user.userAuth eq 'admin'}">
			<form action="<%=request.getContextPath()%>/recallviewlist">
				<div class="admin-orderList-searchBox">
					<select name="type" class="admin-orderList-searchType">
						<option value="0" <c:if test="${pm.criteria.type==0}">selected</c:if>>전체</option>
						<option value="1" <c:if test="${pm.criteria.type==1}">selected</c:if>>반품 번호</option>
						<option value="2" <c:if test="${pm.criteria.type==3}">selected</c:if>>반품 내용</option>
						<option value="3" <c:if test="${pm.criteria.type==2}">selected</c:if>>반품 상태</option>
						<option value="4" <c:if test="${pm.criteria.type==3}">selected</c:if>>반품 날짜</option>
					</select>
					<input type="text" class="admin-orderList-searchContent" name="search" value="${pm.criteria.search}">
					<button class="admin-orderList-searchButton"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</c:if>
		<c:if test="${pm.endPage!=1 && list.size()!=0}">
			<ul class="common-goodsList-pagination pagination justify-content-center">
				<c:if test="${pm.criteria.page!=1}">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/recallviewlist?page=${pm.criteria.page-1}<c:if test="${user.userAuth eq 'admin'}">&type=${pm.criteria.type}&search=${pm.criteria.search}</c:if>"><i class="fas fa-angle-left"></i></a></li>
				</c:if>
			    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
			        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
			            <a class="page-link" href="<%=request.getContextPath()%>/recallviewlist?page=${index}<c:if test="${user.userAuth eq 'admin'}">&type=${pm.criteria.type}&search=${pm.criteria.search}</c:if>">${index}</a>
			        </li>
			    </c:forEach>
			    <c:if test="${pm.criteria.page!=pm.endPage}">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/recallviewlist?page=${pm.criteria.page+1}<c:if test="${user.userAuth eq 'admin'}">&type=${pm.criteria.type}&search=${pm.criteria.search}</c:if>"><i class="fas fa-angle-right"></i></a></li>
				</c:if>
			</ul>
		</c:if>
		<c:if test="${pm.criteria.page>pm.endPage}">
			<script>
				location.replace('<%=request.getContextPath()%>/recallviewlist?&page=${pm.endPage}');
			</script>
		</c:if>
		<c:if test="${list.size()==0 && pm.criteria.page>1}">
			<script>
				location.replace('<%=request.getContextPath()%>/recallviewlist?&page=${pm.endPage}');
			</script>
		</c:if>
	</div>
</div>