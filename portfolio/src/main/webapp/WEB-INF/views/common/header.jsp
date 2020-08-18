<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-set-center">
   	<div class="user-set-header">
		<a href="#"><img src="<%=request.getContextPath()%>/resources/image/로고대용.jpg" alt="" class="user-set-header-logo"></a>
       	<div class="user-set-header-menuBox">
           	<ul class="user-set-topMenuBox">
				<a href="#" class="user-set-notice">공지사항</a>
				<a href="#" class="user-set-QA">Q&A</a>
				<a href="#" class="user-set-review">리뷰</a>
				<a href="#" class="user-set-cart">장바구니</a>
				<a href="#" class="user-set-myPage">마이페이지</a>
				<a href="<%=request.getContextPath()%>/signup" class="user-set-signUpButton">회원가입</a>
				<a href="#" class="user-set-logInButton">로그인</a>
			</ul>
			<ul class="admin-set-topMenuBox" style="display: none;">
				<a href="#" class="admin-set-notice">공지사항</a>
				<a href="#" class="admin-set-QA">Q&A</a>
				<a href="#" class="admin-set-review">리뷰</a>
				<a href="#" class="admin-set-goodsManage">상품등록</a>
				<a href="#" class="admin-set-myPage">마이페이지</a>
				<div class="admin-set-loginInfo">관리자님 환영합니다.</div>
				<a href="#" class="admin-set-logout">로그아웃</a>
			</ul>
			<ul class="user-set-bottomMenuBox" >
				<a href="#" class="user-set-goNecklace">목걸이</a>
				<a href="#" class="user-set-goRing">반 지</a>
				<ul class="user-set-listEarRing">귀걸이종류
					<li><a href="#" class="user-set-goEarClip">귀찌</a></li>
					<li><a href="#" class="user-set-goEarRing">귀걸이</a></li>
				</ul>
				<div class="user-set-searchBox">
					<input type="text" class="user-set-search" placeholder="Search">
					<button class="user-set-searchButton" type="submit"><i class="fas fa-search"></i></button>
				</div>
			</ul>
		</div>
	</div>
	<div class="user-set-banner" ></div>
</div>
<div class="user-set-right">
	<a href="#"><div class="user-set-orderCheck">주문 조회</div></a>
	<a href="#"><div class="user-set-orderCheck">반품 조회</div></a>
	<a href="#"><div class="user-set-orderCheck">A/S 조회</div></a>
	<a href="#"><div class="user-set-deliveryCheck">배송 조회</div></a>
	<div class="user-set-csTel">C/S 관련<br>xxx)xxx-xxxx</div>
	<div class="user-set-latelyViewGoodsList">
		<div class="user-set-goods"><a href="" ><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" alt="" class="user-set-goodsImg"></a><div class="user-set-goodsClose"><i class="user-set-goodsCloseImg far fa-times-circle"></i></div></div>
		<div class="user-set-goods"><a href="" ><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" alt="" class="user-set-goodsImg"></a><div class="user-set-goodsClose"><i class="user-set-goodsCloseImg far fa-times-circle"></i></div></div>
		<div class="user-set-goods"><a href="" ><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" alt="" class="user-set-goodsImg"></a><div class="user-set-goodsClose"><i class="user-set-goodsCloseImg far fa-times-circle"></i></div></div>
		<script>
			$('.user-set-goodsClose').click(function(){
					$(this).parent().remove();
			})
		</script>
		<div class="user-set-goodsList-pageBox">
			<div class="user-set-goodsList-pageButton"><i class="user-set-goodsList-pageLeft fas fa-angle-left"></i></div>
			<span class="user-set-goodsList-page">1/2</span>
			<div class="user-set-goodsList-pageButton"><i class="user-set-goodsList-pageRight fas fa-angle-right"></i></div>
		</div>
	</div>
	<div class="user-set-goTop">T O P</div>
</div>
<div class="admin-set-right" style="display: none;">
	<a href="#"><div class="admin-set-rightInfo">미확인<br>주문 개수<br></div></a>
	<a href="#"><div class="admin-set-rightInfo">미확인<br>반품 개수</div></a>
	<a href="#"><div class="admin-set-rightInfo">미확인<br>A/S 개수</div></a>
	<a href="#"><div class="admin-set-rightInfo">미확인<br>Q<span>&</span>A 개수</div></a>
	<div class="user-set-goTop">T O P</div>
</div>