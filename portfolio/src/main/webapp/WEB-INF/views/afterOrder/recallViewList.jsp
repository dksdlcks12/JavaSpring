<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-recallList-box">
	<div class="admin-recallList-orderListBox">
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
				<a href="#"><div class="admin-recallList-orderInfo">1</div></a>
				<div class="admin-recallList-orderDate">${recallList.recallDate}</div>
				<div class="admin-recallList-orderState">${recallList.recallState}</div>
			</div>
		</c:forEach>
		<c:if test="${user.userAuth eq 'admin'}">
			<form action="#">
				<div class="common-boardList-searchBox">
					<select name="" id="" class="common-boardList-searchType">
						<option value="0" selected>전체</option>
						<option value="1">반품 번호</option>
						<option value="2">주문 번호</option>
						<option value="3">반품 날짜</option>
						<option value="4">반품 상태</option>
					</select>
					<input type="text" class="common-boardList-searchContent">
					<button class="common-boardList-searchButton"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</c:if>
		<ul class="admin-recallList-pagination pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
		</ul>
	</div>
</div>