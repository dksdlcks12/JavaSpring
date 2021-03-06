<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-cart-box">
	<div class="user-cart-cartBox">
		<c:if test="${list.size()!=0}">
			<form action="<%=request.getContextPath()%>/order" method="GET">
				<table class="user-cart-goodsBox" border="1">
					<tr>
						<th><input type="checkbox" class="user-cart-goodsCheckAll"></th>
						<th class="user-cart-goodsImgTitle">이미지</th>
						<th class="user-cart-goodsInfoTitle">제품 정보</th>
						<th class="user-cart-goodsPriceTitle">할인 적용가</th>
						<th class="user-cart-goodsCountTitle">수량</th>
						<th class="user-cart-goodsPointTitle">포인트(개당)</th>
						<th class="user-cart-goodsGoodsAllPriceTitle">합계금액</th>
					</tr>
					<c:forEach var="cart" items="${list}">
						<tr>
							<td class="user-cart-goodsCheckBox"><input type="checkbox" class="user-cart-goodsCheck" name="orderList" value="${cart.cartNum}"></td>
							<td class="user-cart-goodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${cart.goodsImg}" alt="" ></td>
							<td class="user-cart-goodsInfo">제품명 : <span class="user-cart-goodsName">${cart.goodsName}</span><br>색상 : <span class="user-cart-optionColor">${cart.optionColor}</span></td>
							<td class="user-cart-goodsPrice"><span class="user-cart-goodsPriceNumber">${cart.goodsDiscountPrice}</span>원</td>
							<td class="user-cart-goodsCount"><input type="text" class="number" value="${cart.cartCount}"></td>
							<td class="user-cart-goodsPoint">${cart.goodsPoint}</td>
							<td class="user-cart-goodsGoodsAllPrice"><span class="user-cart-goodsGoodsAllPriceNumber">${cart.goodsAllPrice}</span>원</td>
						</tr>
					</c:forEach>		
				</table>
				<button type="button" class="user-cart-goodsDelete">선택 삭제</button>
				<button class="user-cart-goBuy">구매하기</button>
			</form>
			<div class="user-cart-totalPrice"></div>
		</c:if>
	</div>
</div>
<script>
	var price = 0;
	var count = 0; 
	var totalPrice = 0;
	var defaultDeliveryPrice = 2500;
	var freeDeliveryLimit = 16000;
	function totalPriceCalculation(){
		totalPrice = 0;
		if($('.user-cart-goodsCheck').length!=0){
			var checkCount = 0;
			$('.user-cart-goodsGoodsAllPrice').each(function(){
				if($(this).siblings('.user-cart-goodsCheckBox').children('.user-cart-goodsCheck').is(':checked')){
					totalPrice = totalPrice + Number($(this).children('.user-cart-goodsGoodsAllPriceNumber').text());
					checkCount++;
				}
				if(checkCount!=0){
					if(totalPrice<freeDeliveryLimit){
						$('.user-cart-totalPrice').text('배송비 '+defaultDeliveryPrice+'원 + '+totalPrice+'원 = '+Number(totalPrice+defaultDeliveryPrice)+'원');
					}else{
						$('.user-cart-totalPrice').text('배송비 0원 + '+totalPrice+'원 = '+totalPrice+'원');
					}
				}
				else{
					$('.user-cart-totalPrice').text('0원');
				}
			})
		}else{
			$('.user-cart-cartBox').empty();
		}
	}
	$('.user-cart-goodsCheckAll').click(function(){
		if($(this).is(':checked')){
			$('.user-cart-goodsCheck').prop('checked', true);
		}else{
			$('.user-cart-goodsCheck').prop('checked', false);
		}
		totalPriceCalculation()
	})
	$('.user-cart-goodsCheck').click(function(){
		totalPriceCalculation()
	})
	$('.user-cart-goodsCount').children('input').change(function(){
		price = Number($(this).parent().siblings('.user-cart-goodsPrice').children('.user-cart-goodsPriceNumber').text());
		count = $(this).val();
		if(count<1){
			count=1;
			$(this).val(1);
		}
		$(this).parent().siblings('.user-cart-goodsGoodsAllPrice').children('.user-cart-goodsGoodsAllPriceNumber').text(price*count);
		totalPriceCalculation()
	})
	$('.user-cart-goodsGoodsAllPrice').each(function(){
		price = Number($(this).siblings('.user-cart-goodsPrice').children('.user-cart-goodsPriceNumber').text());
		count = Number($(this).siblings('.user-cart-goodsCount').children('input').val());
		$(this).children('.user-cart-goodsGoodsAllPriceNumber').text(price*count);
	})
	$('.user-cart-goodsDelete').click(function(){
		totalPrice = 0
		var arr = [];
		$('.user-cart-goodsCheck').each(function(){
			if($(this).is(':checked')){
				var color = $(this).parent().siblings('.user-cart-goodsInfo').children('.user-cart-optionColor').text();
				var goods = $(this).parent().siblings('.user-cart-goodsInfo').children('.user-cart-goodsName').text();
				arr.push({'color':color, 'goods':goods});
				$(this).parent().parent().remove();
			}
		})
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/cartdel",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				alert('삭제 완료')
				location.reload();
			}
		});
		$('.user-cart-goodsCheckAll').prop('checked', false);
		totalPriceCalculation();
	})
	$('.user-cart-goodsCount').children('input').change(function(){
		var arr = []
		var cartNum = $(this).parent().siblings('.user-cart-goodsCheckBox').children('.user-cart-goodsCheck').val();  
		var cartCount = $(this).val();
		arr.push({'cartNum':cartNum, 'cartCount':cartCount});
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/cartcountchange",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
			}
		});
	})
	$('form').on("submit", function(){
		var check = false;
		$('.user-cart-goodsCheck').each(function(){
			if($(this).is(':checked')){
				check = true;
			}
		})
		if(check==true){
			return true
		}else{
			alert('선택된 항목이 없습니다.')
			return false
		}
	})
	totalPriceCalculation();
</script>