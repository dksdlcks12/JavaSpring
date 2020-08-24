$(function(){
	$('.user-cart-goodsCheckAll').click(function(){
		if($('.user-cart-goodsCheckAll').is(":checked")){
			$('.user-cart-goodsCheck').prop('checked', true);
		}else{
			$('.user-cart-goodsCheck').prop('checked', false);
		}
	})
	$('.user-cart-goodsDelete').click(function(){
		$('.user-cart-goodsCheck').each(function(){
			if($(this).is(':checked')){
				$(this).parent().parent().remove();
			}
		})
		$('.user-cart-goodsCheckAll').prop('checked', false);
	})
})