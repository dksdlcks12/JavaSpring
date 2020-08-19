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
		$('.admin-goodsAdd-button').click(function(){
			console.log($('.admin-goodsAdd-goodsOptionBox').length)
			if($('.admin-goodsAdd-goodsOptionBox').length==0){
				alert('옵션이 없습니다.')
				return false;
			}
		})
})