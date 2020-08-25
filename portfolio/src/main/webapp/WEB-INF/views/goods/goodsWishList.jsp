<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-wishList-box">
	<div class="user-wishList-wishListBox">
		<c:if test="${list.size()!=0}">
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
						<td class="user-wishList-goodsInfo">제품명 : <span class="user-wishList-goodsName">${wishList.goodsName}</span> / 색상 : <span class="user-wishList-optionColor">${wishList.optionColor}</span></td>
						<td class="user-wishList-goodsPrice">${wishList.goodsPrice} 원</td>
						<td class="user-wishList-goodsCount">${wishList.wishListCount} 개</td>
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
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/wishlist?page=${pm.criteria.page-1}"><i class="fas fa-angle-left"></i></a></li>
					</c:if>
				    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
				        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
				            <a class="page-link" href="<%=request.getContextPath()%>/wishlist?page=${index}">${index}</a>
				        </li>
				    </c:forEach>
				    <c:if test="${pm.criteria.page!=pm.endPage}">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/wishlist?page=${pm.criteria.page+1}"><i class="fas fa-angle-right"></i></a></li>
					</c:if>
				</ul>
			</c:if>
			<c:if test="${pm.criteria.page>pm.endPage}">
				<script>
					location.href = "<%=request.getContextPath()%>/wishlist?page=${pm.endPage}"
				</script>
			</c:if>
		</c:if>
	</div>
</div>
<script>
	$('.user-wishList-goodsGoCart').click(function(){
		var arr = [];
		$('.user-wishList-goodsCheck').each(function(){
			if($(this).is(':checked')){
				var color = $(this).parent().siblings('.user-wishList-goodsInfo').children('.user-wishList-optionColor').text();
				var goods = $(this).parent().siblings('.user-wishList-goodsInfo').children('.user-wishList-goodsName').text();
				arr.push({'color':color, 'goods':goods});
			}
		})
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/gocart",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				if(data){
				}
			}
		});
		$('.user-wishList-goodsCheckAll').prop('checked', false);
	})
	
	$('.user-wishList-goodsDelete').click(function(){
		var arr = [];
		$('.user-wishList-goodsCheck').each(function(){
			if($(this).is(':checked')){
				$(this).parent().parent().remove();
				var color = $(this).parent().siblings('.user-wishList-goodsInfo').children('.user-wishList-optionColor').text();
				var goods = $(this).parent().siblings('.user-wishList-goodsInfo').children('.user-wishList-goodsName').text();
				arr.push({'color':color, 'goods':goods});
			}
		})
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/wishlistdel",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				alert('삭제 완료')
				location.reload();
			}
		});
		$('.user-wishList-goodsCheckAll').prop('checked', false);
	})
</script>