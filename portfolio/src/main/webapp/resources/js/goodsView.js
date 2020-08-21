$(function(){
	$('#common-goodsView-deliveryExplainMenuBox').css('top', $('.common-goodsView-goodsExplainImg').height()+'450px');
	if($('.common-goodsView-selectOptionBox').length==0){
		$('.common-goodsView-deliveryParice').text('')
		$('.common-goodsView-allGoodsPrice').text('');
		$('.common-goodsView-allGoodsPrice').next().text('');
		$('.common-goodsView-totalPrice').text('');									
	}
})