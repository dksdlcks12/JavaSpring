$(function(){
	$('.number').keydown(function(event){
		if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) { 
			return true;
		}else if(event.keyCode==8 || event.keyCode==9 || event.keyCode==13 || event.keyCode==37 || event.keyCode==39 || event.keyCode==46){
			return true;
		}else{
			return false;
		}
	})
	$('.number').keyup(function(){
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi,''));
	})
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