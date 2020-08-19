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
			<a href="#" class="user-main-noticeLine">
				<div class="user-main-noticeTitle">공지사항1</div>
				<div class="user-main-noticeDate">2020/06/20</div>
			</a>
			<a href="#" class="user-main-noticeLine">
				<div class="user-main-noticeTitle">공지사항1</div>
				<div class="user-main-noticeDate">2020/06/20</div>
			</a>
			<a href="#" class="user-main-noticeLine">
				<div class="user-main-noticeTitle">공지사항1</div>
				<div class="user-main-noticeDate">2020/06/20</div>
			</a>
			<a href="#" class="user-main-noticeLine">
				<div class="user-main-noticeTitle">공지사항1</div>
				<div class="user-main-noticeDate">2020/06/20</div>
			</a>
			<a href="#" class="user-main-noticeLine">
				<div class="user-main-noticeTitle">공지사항1</div>
				<div class="user-main-noticeDate">2020/06/20</div>
			</a>
		</div>
	</div>
	<h2 class="user-main-newGoodsTitle">최신 제품</h2>
	<div class="user-main-newGoodsList">
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
		<div class="user-main-newGoodsBox">
			<a href="#"><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" class="user-main-newGoods"></a>
			<div class="user-main-newGoodsInfo">
				<div class="user-main-newGoodsName">제품명</div>
				<div class="user-main-newGoodsPrice">제품 가격</div>
			</div>
		</div>
	</div>
</div>