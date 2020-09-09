<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-asList-box">
	<div class="admin-asList-orderListBox">
		<h2>A/S목록</h2>
		<div class="admin-asList-orderTitleBox">
			<div class="admin-asList-orderTitleNumber">A/S번호</div>
			<div class="admin-asList-orderTitleInfo">A/S내용</div>
			<div class="admin-asList-orderTitleDate">A/S 신청날짜</div>
			<div class="admin-asList-orderTitleState">A/S상태</div>
		</div>
		<div class="admin-asList-orderBox">
			<div class="admin-asList-orderNumber">1</div>
			<a href="#"><div class="admin-asList-orderInfo">1</div></a>
			<div class="admin-asList-orderDate">2020-01-02 11:12:12</div>
			<div class="admin-asList-orderState">1</div>
		</div>
		<div class="admin-asList-orderBox">
			<div class="admin-asList-orderNumber">1</div>
			<a href="#"><div class="admin-asList-orderInfo">1</div></a>
			<div class="admin-asList-orderDate">1</div>
			<div class="admin-asList-orderState">1</div>
		</div>
		<div class="admin-asList-orderBox">
			<div class="admin-asList-orderNumber">1</div>
			<a href="#"><div class="admin-asList-orderInfo">1</div></a>
			<div class="admin-asList-orderDate">2020-01-02 11:12:12</div>
			<div class="admin-asList-orderState">1</div>
		</div>
		<c:if test="${user.userAuth eq 'admin'}">
			<form action="#">
				<div class="common-boardList-searchBox">
					<select name="" id="" class="common-boardList-searchType">
						<option value="0" selected>전체</option>
						<option value="1">A/S 번호</option>
						<option value="2">A/S 신청날짜</option>
						<option value="3">A/S 상태</option>
					</select>
					<input type="text" class="common-boardList-searchContent">
					<button class="common-boardList-searchButton"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</c:if>
		<ul class="admin-asList-pagination pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
		</ul>
	</div>
</div>