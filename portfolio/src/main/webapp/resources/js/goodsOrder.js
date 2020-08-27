$(function(){
	var allGoodsPrice=0;
	var totalPrice=0;
	var price=0;
	var point=0;
	var count=0;
	var defaultDeliveryPrice = 2500;
	var freeDeliveryLimit = 16000;
	function totalPriceCalculation(){
		totalPrice = 0;
		$('.user-order-allGoodsPrice').each(function(){
			totalPrice = totalPrice + Number($(this).children('.user-order-allGoodsPriceNumber').text());
			if(totalPrice<freeDeliveryLimit){
				$('.user-order-totalPrice').text('배송비 '+defaultDeliveryPrice+'원 + '+totalPrice+'원 = '+Number(totalPrice+defaultDeliveryPrice)+'원');
			}else{
				$('.user-order-totalPrice').text('배송비 0원 + '+totalPrice+'원 = '+totalPrice+'원');
			} 
		})
	}
	$('.user-order-goodsCheckAll').click(function(){
		if($('.user-order-goodsCheckAll').is(':checked')){
			$('.user-order-goodsCheck').prop('checked', true);
		}else{
			$('.user-order-goodsCheck').prop('checked', false);
		}
	})
	$('.user-order-allGoodsPrice').each(function(){
		price = Number($(this).siblings('.user-order-goodsPrice').children('.user-order-goodsPriceNumber').text());
		count = Number($(this).siblings('.user-order-goodsCount').text());
		point = Number($(this).siblings('.user-order-goodsUsePoint').children('input').val());
		if(isNaN(point)){
		point = 0;
		}
		if(point>(price*count)/10){
			$(this).siblings('.user-order-goodsUsePoint').children('input').val((price*count)/10);
			point = Number($(this).siblings('.user-order-goodsUsePoint').children('input').val());
		}
		if(point%10!=0){
			point = point-(point%10);
		}
		$(this).children('.user-order-allGoodsPriceNumber').text(price*count-point);
	})
	$('.user-order-goodsUsePoint').children('input').change(function(){
		price = Number($(this).parent().siblings('.user-order-goodsPrice').children('.user-order-goodsPriceNumber').text());
		count = Number($(this).parent().siblings('.user-order-goodsCount').text());
		if($(this).val()<0){
			$(this).val(0);
		}
		if($(this).val()>(price*count)/10){
			$(this).val((price*count)/10);
		}
		if($(this).val()%10!=0){
			$(this).val($(this).val()-($(this).val()%10));
		}
		$(this).parent().siblings('.user-order-allGoodsPrice').children('.user-order-allGoodsPriceNumber').text(price*count-$(this).val());
		totalPriceCalculation();
	})
	totalPriceCalculation();
})