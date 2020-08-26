$(function(){
	$('.user-cart-goodsCheckAll').click(function(){
		if($(this).is(':checked')){
			$('.user-cart-goodsCheck').prop('checked', true);
		}else{
			$('.user-cart-goodsCheck').prop('checked', false);
		}
	})
})