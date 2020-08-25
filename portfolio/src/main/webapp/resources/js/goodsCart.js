$(function(){
	var totalPrice = 0;
	var defaultDeliveryPrice = 2500;
	var freeDeliveryLimit = 16000;
	function totalPriceCalculation(){
		totalPrice = 0;
		$('.user-cart-goodsGoodsAllPrice').each(function(){
			totalPrice = totalPrice + Number($(this).children('.user-cart-goodsGoodsAllPriceNumber').text());
			if(totalPrice<16000){
				$('.user-cart-totalPrice').text('배송비 '+defaultDeliveryPrice+'원 + '+totalPrice+'원 = '+Number(totalPrice+defaultDeliveryPrice)+'원');
			}else{
				$('.user-cart-totalPrice').text('배송비 0원 + '+totalPrice+'원 = '+totalPrice+'원');
			} 
		})
	}
	$('.user-cart-goodsCheckAll').click(function(){
		if($(this).is(':checked')){
			$('.user-cart-goodsCheck').prop('checked', true);
		}else{
			$('.user-cart-goodsCheck').prop('checked', false);
		}
	})
	$('.user-cart-goodsDelete').click(function(){
		totalPrice = 0
		$('.user-cart-goodsCheck').each(function(){
			if($(this).is(':checked')){
				$(this).parent().parent().remove();
				$('.user-cart-goodsCheckAll').prop('checked', false);
			}
		})
		totalPriceCalculation();
	})
	totalPriceCalculation();
})