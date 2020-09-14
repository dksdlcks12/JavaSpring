<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-set-center">
   	<div class="user-set-header">
		<a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/resources/image/로고대용.jpg" alt="" class="user-set-header-logo"></a>
       	<div class="user-set-header-menuBox">
       		<c:if test="${user.userAuth ne 'admin'}">
	           	<ul class="user-set-topMenuBox">
					<a href="<%=request.getContextPath()%>/noticelist" class="user-set-notice">공지사항</a>
					<a href="<%=request.getContextPath()%>/qalist" class="user-set-QA">Q&A</a>
					<a href="<%=request.getContextPath()%>/reviewlist" class="user-set-review">리뷰</a>
					<a href="<%=request.getContextPath()%>/cart" class="user-set-cart">장바구니</a>
					<a href="<%=request.getContextPath()%>/wishlist" class="user-set-wishList">위시리스트</a>
					<a href="#" class="user-set-myPage">마이페이지</a>
					<c:if test="${user==null}">
						<a href="<%=request.getContextPath()%>/signup" class="user-set-signUpButton">회원가입</a>
						<a href="<%=request.getContextPath()%>/login" class="user-set-logInButton">로그인</a>
					</c:if>
					<c:if test="${user!=null}">
						<div href="<%=request.getContextPath()%>/logout" class="user-set-signUpButton">로그아웃</div>
						<div class="user-set-logInInfo">${user.userId}님 환영합니다.</div>
						<script>
							var logout = false;
							$(".user-set-signUpButton").on("click",function(){
								$.ajax({
									async:false,
									type:'POST',
									data: logout,
									url:"<%=request.getContextPath()%>/logout",
									dataType:"json",
									contentType:"application/json; charset=UTF-8",
									success : function(data){
										location.reload();
									}
								});
							})
						</script>
					</c:if>
				</ul>
			</c:if>
			<c:if test="${user.userAuth eq 'admin'}">
				<ul class="admin-set-topMenuBox">
					<a href="<%=request.getContextPath()%>/noticelist" class="admin-set-notice">공지사항</a>
					<a href="<%=request.getContextPath()%>/qalist" class="admin-set-QA">Q&A</a>
					<a href="<%=request.getContextPath()%>/reviewlist" class="admin-set-review">리뷰</a>
					<a href="<%=request.getContextPath()%>/admin/goodsadd" class="admin-set-goodsManage">상품등록</a>
					<a href="#" class="admin-set-myPage">마이페이지</a>
					<div class="admin-set-loginInfo">관리자님 환영합니다.</div>
					<div class="admin-set-logout">로그아웃</div>
					<script>
							$(".admin-set-logout").on("click",function(){
								$.ajax({
									async:false,
									type:'POST',
									url:"<%=request.getContextPath()%>/logout",
									dataType:"json",
									contentType:"application/json; charset=UTF-8",
									success : function(data){
										location.href="<%=request.getContextPath()%>/login";
									}
								});
							})
					</script>
				</ul>
			</c:if>
			<ul class="user-set-bottomMenuBox" >
				<a href="<%=request.getContextPath()%>/goodslist?type=1&page=1" class="user-set-goNecklace">목걸이</a>
				<a href="<%=request.getContextPath()%>/goodslist?type=2&page=1" class="user-set-goRing">반  지</a>
				<ul class="user-set-listEarRing">귀걸이종류
					<li><a href="<%=request.getContextPath()%>/goodslist?type=3&page=1" class="user-set-goEarClip">귀  찌</a></li>
					<li><a href="<%=request.getContextPath()%>/goodslist?type=4&page=1" class="user-set-goEarRing">귀걸이</a></li>
				</ul>
				<form action="<%=request.getContextPath()%>/goodssearch">
					<div class="user-set-searchBox">
						<input type="text" class="user-set-search" placeholder="Search" name="search">
						<input type="text" name="minPrice" hidden>
						<input type="text" name="maxPrice" hidden>
						<input type="text" name="minDisCount" hidden>
						<input type="text" name="maxDisCount" hidden>
						<button class="user-set-searchButton" type="submit"><i class="fas fa-search"></i></button>
					</div>
				</form>
			</ul>
		</div>
	</div>
	<div class="user-set-banner" ></div>
</div>
<c:if test="${user.userAuth ne 'admin'}">
	<div class="user-set-right">
		<a href="<%=request.getContextPath()%>/orderviewlist"><div class="user-set-orderCheck">주문 조회</div></a>
		<a href="<%=request.getContextPath()%>/recallselect"><div class="user-set-orderCheck">반품 관련</div></a>
		<a href="<%=request.getContextPath()%>/asselect"><div class="user-set-orderCheck">A / S 관련</div></a>
		<a href="#"><div class="user-set-deliveryCheck">배송 조회</div></a>
		<div class="user-set-csTel">C / S 관련<br>xxx)xxx-xxxx</div>
		<div class="user-set-latelyViewGoodsList">
			<div class="user-set-goods"><a href="#" ><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" alt="" class="user-set-goodsImg"></a><div class="user-set-goodsClose"><i class="user-set-goodsCloseImg far fa-times-circle"></i></div></div>
			<div class="user-set-goods"><a href="#" ><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" alt="" class="user-set-goodsImg"></a><div class="user-set-goodsClose"><i class="user-set-goodsCloseImg far fa-times-circle"></i></div></div>
			<div class="user-set-goods"><a href="#" ><img src="<%=request.getContextPath()%>/resources/image/상품대용.gif" alt="" class="user-set-goodsImg"></a><div class="user-set-goodsClose"><i class="user-set-goodsCloseImg far fa-times-circle"></i></div></div>
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
</c:if>
<c:if test="${user.userAuth eq 'admin'}">
	<div class="admin-set-right">
		<a href="<%=request.getContextPath()%>/orderviewlist"><div class="admin-set-rightInfo">미확인<br>주문 개수<br>${countUncheckOrder} 개</div></a>
		<a href="<%=request.getContextPath()%>/recallviewlist"><div class="admin-set-rightInfo">미확인<br>반품 개수<br>${countUncheckRecall} 개</div></a>
		<a href="<%=request.getContextPath()%>/asviewlist"><div class="admin-set-rightInfo">미확인<br>A/S 개수<br>${countUncheckAs} 개</div></a>
		<a href="<%=request.getContextPath()%>/qalist"><div class="admin-set-rightInfo">미답변<br>Q<span>&</span>A 개수<br>${countUncheckQa} 개</div></a>
		<div class="user-set-goTop">T O P</div>
	</div>
</c:if>