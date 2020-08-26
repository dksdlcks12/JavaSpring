$(function(){
	$('.admin-goodsAdd-button').click(function(event){
		if($('.admin-goodsAdd-goodsImgAdd').val()!=''&& $('.admin-goodsAdd-goodsExplainImgAdd').val()!=''){
			if($('.admin-goodsAdd-goodsType').val()!='' && $('.admin-goodsAdd-goodsName').val()!='' && $('.admin-goodsAdd-goodsPrice').val()!='' && $('.admin-goodsAdd-goodsPayPoint').val()!='' && $('.admin-goodsAdd-goodsDiscount').val()!=''){
				if($('.admin-goodsAdd-goodsOptionBox').length==0){
					alert('옵션이 없습니다.')
					return false;
				}
			}else{
				alert('제품 정보를 모두 입력하여 주십시오.')
				return false;
			}
		}else{
			alert('제품 이미지 와 제품 이미지를 등록해 주십시오.')
			return false;
		}
	})
})