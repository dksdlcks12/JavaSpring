<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-goodsView-box">
	<div class="common-goodsView-goodsAddBox">
			<div class="common-goodsView-leftBox">
				<img src="<%=request.getContextPath()%>/resources/image/goodsImg${goods.goodsImg}" alt="" class="common-goodsView-goodsImg">
			</div>
		<form action="#">
			<div class="common-goodsView-rightBox">
				<div class="common-goodsView-goodsNameBox">
					<div class="common-goodsView-goodsNameTitle">제품명</div>
					<div class="common-goodsView-goodsName">${goods.goodsName}</div>
				</div>
				<div class="common-goodsView-goodsPriceBox">
					<div class="common-goodsView-goodsPriceTitle">가 격</div>
					<div class="common-goodsView-goodsPrice">${goods.goodsPrice}원</div>
					<div class="common-goodsView-goodsPayPointTitle">적립금</div>
					<div class="common-goodsView-goodsPayPoint">${goods.goodsPoint}점</div>
					<div class="common-goodsView-goodsDiscountTitle">할인율</div>
					<div class="common-goodsView-goodsDiscount">${post.postDiscount}%</div>
					<div class="common-goodsView-goodsDiscountPriceTitle">할인가</div>
					<div class="common-goodsView-goodsDiscountPrice">${disCountPrice}원</div>
				</div>
				<div class="common-goodsView-goodsTypeBox">
					<div class="common-goodsView-goodsTypeTitle">색 상</div>
					<select name="" class="common-goodsView-goodsType" id="common-goodsView-goodsType" onchange="colorSelect()">
						<option value="" selected>----색상 선택----</option>
						<c:forEach var="option" items="${list}">
							<option value="${option.optionColor}">${option.optionColor}----남은 수량 : ${option.optionStock}</option>
						</c:forEach>
					</select>
					<!-- 색상 선택시 항목 추가 및 예상가격 계산하는 부분 -->
					<script>
						var totalCount = 0;
						var defaultDeliveryPrice = 2500;
						var freeDeliveryLimit = 16000;
						function totalCountCalculation(){
								totalCount = 0;
								$('.common-goodsView-optionCount').each(function(){
									totalCount = totalCount + Number($(this).val());
								})
							}
						function colorSelect(){
							var colorSelect = document.getElementById("common-goodsView-goodsType");
							var text = colorSelect.options[document.getElementById("common-goodsView-goodsType").selectedIndex].text;
							var value = colorSelect.options[document.getElementById("common-goodsView-goodsType").selectedIndex].value;
							var str = $('.common-goodsView-optionName').text();
							var price = ${disCountPrice};
							var count;
							function priceCalculation(){
								$('.common-goodsView-allGoodsPrice').text(${disCountPrice}*totalCount);
								$('.common-goodsView-allGoodsPrice').next().text('원 =');
								var allGoodsPrice = Number($('.common-goodsView-allGoodsPrice').text());
								if(allGoodsPrice<freeDeliveryLimit){
									$('.common-goodsView-deliveryParice').text('기본배송비 '+defaultDeliveryPrice+' +')
									$('.common-goodsView-totalPrice').text(allGoodsPrice+defaultDeliveryPrice);
								}else{
									$('.common-goodsView-deliveryParice').text('무료배송 0원 +')
									$('.common-goodsView-totalPrice').text($('.common-goodsView-allGoodsPrice').text());
								}
							}
							function optionSelect(){
								$('.common-goodsView-selectBox').append('<div class="common-goodsView-selectOptionBox" id="common-goodsView-selectOptionBox"><div class="common-goodsView-optionName">'+value+'</div><input type="text" class="common-goodsView-optionCount" value="1"><div class="common-goodsView-optionCountButtonBox"><div class="common-goodsView-optionCountButton"><i class="fas fa-plus"></i></div><div class="common-goodsView-optionCountButton"><i class="fas fa-minus"></i></div></div><div class="common-goodsView-optionPrice"><span class="common-goodsView-optionPriceNumber">'+${disCountPrice}+'</span>원</div><div class="common-goodsView-optionCancel"><div class="common-goodsView-optionCancelButton"><i class="fas fa-times"></i></div></div></div')
								count = Number($('.common-goodsView-optionName:contains('+value+')').next().val());
								++totalCount;
								priceCalculation();
								$('.common-goodsView-optionName:contains('+value+')').siblings('.common-goodsView-optionCancel').children().click(function(){
									totalCount = totalCount - Number($('.common-goodsView-optionName:contains('+value+')').next().val());
									priceCalculation();
									$(this).parent().parent().remove();
									if($('.common-goodsView-selectOptionBox').length==0){
										$('.common-goodsView-deliveryParice').text('')
										$('.common-goodsView-allGoodsPrice').text('');
										$('.common-goodsView-allGoodsPrice').next().text('');
										$('.common-goodsView-totalPrice').text('0');	
										totalCount = 0;								
									}
								})
								$('.common-goodsView-optionName:contains('+value+')').siblings('.common-goodsView-optionCountButtonBox').children().click(function(){
									count = Number($(this).parent().siblings('.common-goodsView-optionCount').val())
									if($(this).children().hasClass('fa-minus')){
										if(count > 1){
											$(this).parent().siblings('.common-goodsView-optionCount').val(count-1);
											$(this).parent().siblings('.common-goodsView-optionPrice').children('.common-goodsView-optionPriceNumber').text((count-1)*price);
											$('.common-goodsView-optionCount').each(function(){
												priceCalculation();
												totalCountCalculation();
											})
										}
									}else{
										$(this).parent().siblings('.common-goodsView-optionCount').val(count+1);
										$(this).parent().siblings('.common-goodsView-optionPrice').children('.common-goodsView-optionPriceNumber').text((count+1)*price);
										$('.common-goodsView-optionCount').each(function(){
											priceCalculation();
											totalCountCalculation();
										})
									}
								})
								$('.common-goodsView-optionCount').keydown(function(event){
									if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) { 
										return true;
									}else if(event.keyCode==8 || event.keyCode==9 || event.keyCode==13 || event.keyCode==37 || event.keyCode==39 || event.keyCode==46){
										return true;
									}else{
										return false;
									}
								})
								$('.common-goodsView-optionCount').keyup(function(){
									$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi,''));
								})
								$('.common-goodsView-optionCount').blur(function(){
									if($(this).val()=='' || Number($(this).val())<1){
										$(this).val(1);
									}
									count = Number($(this).val());
									totalCountCalculation();
									$(this).siblings('.common-goodsView-optionPrice').children('.common-goodsView-optionPriceNumber').text(count*price);
									priceCalculation();
								})
								$('.common-goodsView-optionCount').change(function(){
									if($(this).val()=='' || Number($(this).val())<1){
										$(this).val(1);
									}
									count = Number($(this).val());
									totalCountCalculation();
									$(this).siblings('.common-goodsView-optionPrice').children('.common-goodsView-optionPriceNumber').text(count*price);
									priceCalculation();
								})
							}
							count = Number($('.common-goodsView-optionName:contains('+value+')').next().val());
							colorSelect.selectedIndex=0;
							if(value!="" && $('.common-goodsView-selectOptionBox').length==0){
								optionSelect();
							}else{
								if(str.indexOf(value)==-1){
									optionSelect();
								}else{
									++totalCount;
									$('.common-goodsView-optionName:contains('+value+')').siblings('.common-goodsView-optionCount').val(count+1);
									$('.common-goodsView-optionName:contains('+value+')').siblings('.common-goodsView-optionPrice').children('.common-goodsView-optionPriceNumber').text((count+1)*price);
									priceCalculation();
								}
							}
						}
					</script>
					<div class="common-goodsView-goodsBuyBox">
						<div class="common-goodsView-selectBox" style="overflow-x: hidden;"></div>
						<div class="common-goodsView-selectPriceBox">
								<div class="common-goodsView-selectAllPrice">
									<span class="common-goodsView-deliveryParice"></span>
									<span class="common-goodsView-allGoodsPrice"></span><span></span>
									<span class="common-goodsView-totalPrice">0</span><span>원</span></div>
								<div>
									<a href="#"><div class="common-goodsView-goCart">장바구니</div></a>
									<a href="#"><div class="common-goodsView-goBuy">구매하기</div></a>
								</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div id="common-goodsView-goodsExplainMenuBox"></div>
		<div class="common-goodsView-goodsExplainMenuBox">
			<div class="common-goodsView-goodsExplain">제품설명</div>
			<div class="common-goodsView-deliveryExplain">배송/반품정보</div>
		</div>
		<img src="<%=request.getContextPath()%>/resources/image/goodsImg${post.postImg}" alt="" class="common-goodsView-goodsExplainImg">
		<div id="common-goodsView-deliveryExplainMenuBox"></div>
		<div class="common-goodsView-deliveryExplainMenuBox">
			<div class="common-goodsView-goodsExplain">제품설명</div>
			<div class="common-goodsView-deliveryExplain">배송/반품정보</div>
		</div>
		
		<img src="#" alt="" class="common-goodsView-deliveryExplainImg">
		<script>
			$('.common-goodsView-goodsExplain').click(function(){
				location.replace('#common-goodsView-goodsExplainMenuBox')
			})
			$('.common-goodsView-deliveryExplain').click(function(){
				location.replace('#common-goodsView-deliveryExplainMenuBox')
			})
		</script>
	</div>
</div>