<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-cart-box">
	<div class="user-cart-cartBox">
		<table class="user-cart-goodsBox" border="1">
			<tr>
				<th><input type="checkbox" class="user-cart-goodsCheckAll"></th>
				<th class="user-cart-goodsImgTitle">이미지</th>
				<th class="user-cart-goodsInfoTitle">제품 정보</th>
				<th class="user-cart-goodsPriceTitle">할인 적용가</th>
				<th class="user-cart-goodsCountTitle">수량</th>
				<th class="user-cart-goodsUsePointTitle">포인트 사용량</th>
				<th class="user-cart-goodsGoodsAllPriceTitle">합계금액</th>
			</tr>
			<tr>
				<td><input type="checkbox" class="user-cart-goodsCheck"></td>
				<td class="user-cart-goodsImg"><img src="#" alt="" ></td>
				<td class="user-cart-goodsInfo">aaaa</td>
				<td class="user-cart-goodsPrice"><span class="user-cart-goodsPriceNumber">8000</span>원</td>
				<td class="user-cart-goodsCount"><input type="number" value="1"></td>
				<td class="user-cart-goodsUsePoint"><input class="number" type="text"></td>
				<td class="user-cart-goodsGoodsAllPrice"><span class="user-cart-goodsGoodsAllPriceNumber"></span>원</td>
			</tr>
			<tr>
				<td><input type="checkbox" class="user-cart-goodsCheck"></td>
				<td class="user-cart-goodsImg"><img src="#" alt="" ></td>
				<td class="user-cart-goodsInfo">bbbb</td>
				<td class="user-cart-goodsPrice"><span class="user-cart-goodsPriceNumber">7000</span>원</td>
				<td class="user-cart-goodsCount"><input type="number" value="2"></td>
				<td class="user-cart-goodsUsePoint"><input class="number" type="text"></td>
				<td class="user-cart-goodsGoodsAllPrice"><span class="user-cart-goodsGoodsAllPriceNumber"></span>원</td>
			</tr>
			<tr>
				<td><input type="checkbox" class="user-cart-goodsCheck"></td>
				<td class="user-cart-goodsImg"><img src="#" alt="" ></td>
				<td class="user-cart-goodsInfo">cccc</td>
				<td class="user-cart-goodsPrice"><span class="user-cart-goodsPriceNumber">9000</span>원</td>
				<td class="user-cart-goodsCount"><input type="number" value="3"></td>
				<td class="user-cart-goodsUsePoint"><input class="number" type="text"></td>
				<td class="user-cart-goodsGoodsAllPrice"><span class="user-cart-goodsGoodsAllPriceNumber"></span>원</td>
			</tr>
			<script>
			var price = 0;
			var count = 0; 
			var point = 0;
			var totalPrice = 0;
			function totalPriceCalculation(){
				totalPrice = 0;
				var defaultDeliveryPrice = 2500;
				var freeDeliveryLimit = 16000;
				$('.user-cart-goodsGoodsAllPrice').each(function(){
					totalPrice = totalPrice + Number($(this).children('.user-cart-goodsGoodsAllPriceNumber').text());
					if(totalPrice<16000){
						$('.user-cart-totalPrice').text('배송비 '+defaultDeliveryPrice+'원 + '+totalPrice+'원 = '+Number(totalPrice+defaultDeliveryPrice)+'원');
					}else{
						$('.user-cart-totalPrice').text('배송비 0원 + '+totalPrice+'원 = '+totalPrice+'원');
					} 
				})
			}
			$('.user-cart-goodsCount').children('input').change(function(){
				price = Number($(this).parent().siblings('.user-cart-goodsPrice').children('.user-cart-goodsPriceNumber').text());
				point = Number($(this).parent().siblings('.user-cart-goodsUsePoint').children('input').val());
				count = $(this).val();
				if(count<1){
					count=1;
					$(this).val(1);
				}
				if(point>(price*count)/10){
					$(this).parent().siblings('.user-cart-goodsUsePoint').children('input').val((price*count)/10);
					point = Number( $(this).parent().siblings('.user-cart-goodsUsePoint').children('input').val());
				}
				$(this).parent().siblings('.user-cart-goodsGoodsAllPrice').children('.user-cart-goodsGoodsAllPriceNumber').text(price*count-point);
				totalPriceCalculation()
			})
			$('.user-cart-goodsGoodsAllPrice').each(function(){
				price = Number($(this).siblings('.user-cart-goodsPrice').children('.user-cart-goodsPriceNumber').text());
				count = Number($(this).siblings('.user-cart-goodsCount').children('input').val());
				point = Number($(this).siblings('.user-cart-goodsUsePoint').children('input').val());
				if(isNaN(point)){
					point = 0;
				}
				if(point>(price*count)/10){
					$(this).parent().siblings('.user-cart-goodsUsePoint').children('input').val((price*count)/10);
					point = Number( $(this).parent().siblings('.user-cart-goodsUsePoint').children('input').val());
				}
				$(this).children('.user-cart-goodsGoodsAllPriceNumber').text(price*count-point);
			})
			$('.user-cart-goodsUsePoint').children('input').change(function(){
				price = Number($(this).parent().siblings('.user-cart-goodsPrice').children('.user-cart-goodsPriceNumber').text());
				count = Number($(this).parent().siblings('.user-cart-goodsCount').children('input').val());
				if($(this).val()<0){
					$(this).val(0);
				}
				if($(this).val()>(price*count)/10){
					$(this).val((price*count)/10);
					point = Number( $(this).parent().siblings('.user-cart-goodsUsePoint').children('input').val());
				}
				$(this).parent().siblings('.user-cart-goodsGoodsAllPrice').children('.user-cart-goodsGoodsAllPriceNumber').text(price*count-$(this).val());
				totalPriceCalculation();
			})
			</script>
		</table>
		<button class="user-cart-goodsDelete">선택 삭제</button>
		<button class="user-cart-goBuy">구매하기</button>
		<div class="user-cart-totalPrice"></div>
	</div>
</div>