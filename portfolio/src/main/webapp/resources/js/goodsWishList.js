$(function(){
	$('.user-wishList-goodsCheckAll').click(function(){
		if($('.user-wishList-goodsCheckAll').is(":checked")){
			$('.user-wishList-goodsCheck').prop('checked', true);
		}else{
			$('.user-wishList-goodsCheck').prop('checked', false);
		}
	})
	$('.user-wishList-goodsDelete').click(function(){
		$('.user-wishList-goodsCheck').each(function(){
			if($(this).is(':checked')){
				$(this).parent().parent().remove();
			}
		})
		$('.user-wishList-goodsCheckAll').prop('checked', false);
	})
})