$(function(){
	$('.user-wishList-goodsCheckAll').click(function(){
		if($('.user-wishList-goodsCheckAll').is(":checked")){
			$('.user-wishList-goodsCheck').prop('checked', true);
		}else{
			$('.user-wishList-goodsCheck').prop('checked', false);
		}
	})
})