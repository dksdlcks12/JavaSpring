<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-main-box"> 
	<div class="user-main-topContent">
		<div id="slideBoxGoods" class="user-main-slideBox carousel slide" data-ride="carousel">
		<!-- Indicators -->
			<ul class="carousel-indicators">
				<li data-target="#slideBoxGoods" data-slide-to="0" class="active"></li>
				<li data-target="#slideBoxGoods" data-slide-to="1"></li>
				<li data-target="#slideBoxGoods" data-slide-to="2"></li>
				<li data-target="#slideBoxGoods" data-slide-to="3"></li>
			</ul>  
            <!-- The slideshow -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<a href="#"><img src="<%=request.getContextPath()%>/resources/image/photo6.JPG" alt="" width="800px" height="200px"></a>
				</div>
				<div class="carousel-item ">
					<a href="#"><img src="<%=request.getContextPath()%>/resources/image/배경4.jpg" alt="" width="800px" height="200px"></a>
				</div>
				<div class="carousel-item">
					<a href="#"><img src="<%=request.getContextPath()%>/resources/image/배경13.jpg" alt="" width="800px" height="200px"></a>
				</div>
				<div class="carousel-item">
					<a href="#"><img src="<%=request.getContextPath()%>/resources/image/배경11.jpg" alt="" width="800px" height="200px"></a>
				</div>
			</div>     
			<!-- Left and right controls -->
			<a class="carousel-control-prev" href="#slideBoxGoods" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a>
			<a class="carousel-control-next" href="#slideBoxGoods" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>
		<div class="user-main-notice">
			<h2>공지사항</h2>
			<c:forEach var="notice" items="${noticeList}">
				<a href="<%=request.getContextPath()%>/noticeview?noticeNum=${notice.noticeNum}&page=1&type=0&search=" class="user-main-noticeLine">
					<div class="user-main-noticeTitle">${notice.noticeTitle}</div>
					<div class="user-main-noticeDate">${notice.noticeDate}</div>
				</a>
			</c:forEach>
		</div>
	</div>
	<h2 class="user-main-newGoodsTitle">최신 제품</h2>
	<div class="user-main-newGoodsList">
		<c:if test = "${list.size()!=0}">
			<c:forEach var="goods" items="${list}">
				<div class="user-main-newGoodsBox">
					<a href="<%=request.getContextPath()%>/goodsview?num=${goods.goodsNum}&type=0&page=1"><img src="<%=request.getContextPath()%>/resources/image/goodsImg${goods.goodsImg}" class="user-main-newGoods"></a>
					<div class="user-main-newGoodsInfo">
						<div class="user-main-newGoodsName">제품명 : ${goods.goodsName}</div>
						<div class="user-main-newGoodsPrice">가격 : ${goods.goodsPrice}원</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
</div>