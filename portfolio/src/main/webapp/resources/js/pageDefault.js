$(function(){
	if($('.user-cart-cartBox').height()<=369){
	    $('.user-cart-cartBox').height('369');
	}
	if($('.common-goodsList-goodsListBox').height()<=401){
	    $('.common-goodsList-goodsListBox').height('401');
	}
	if($('.user-recallApplyList-recallApplyListBox').height()<=429){
		$('.user-recallApplyList-recallApplyListBox').height('429');
	}
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
		if($(this).val().substring(0,1)==0){
			if($(this).val().substring(1,2)==''){
				$(this).val(0);
			}else if($(this).val().substring(1,2)==0){
				$(this).val(0);
			}else{
				$(this).val( $(this).val().replace(/(^0+)/, ""))
			}
		}
	})
	$('.account').keydown(function(event){
		if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) { 
			return true;
		}else if(event.keyCode==8 || event.keyCode==9 || event.keyCode==13 || event.keyCode==37 || event.keyCode==39 || event.keyCode==46){
			return true;
		}else{
			return false;
		}
	})
	$('input[type=tel]').keydown(function(event){
		var limit = 4;
		if($(this).val().length<limit){
			if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) { 
				return true;
			}else if(event.keyCode==8 || event.keyCode==9 || event.keyCode==13 || event.keyCode==37 || event.keyCode==39 || event.keyCode==46){
				return true;
			}else{
				return false;
			}
		}else{
			if(event.keyCode==8 || event.keyCode==9 || event.keyCode==13 || event.keyCode==37 || event.keyCode==39 || event.keyCode==46){
				return true;
			}else{
				return false;
			}
		}
	})
	$('input[type=tel]').keyup(function(){
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi,''));
	})
	$('.noneMemberPassword, .noneMemberPasswordCheck').keydown(function(event){
		if($(this).val().length<8){
			if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105)) { 
				return true;
			}else if(event.keyCode==8 || event.keyCode==9 || event.keyCode==13 || event.keyCode==37 || event.keyCode==39 || event.keyCode==46){
				return true;
			}else{
				return false;
			}
		}else{
			if(event.keyCode==8 || event.keyCode==9 || event.keyCode==13 || event.keyCode==37 || event.keyCode==39 || event.keyCode==46){
				return true;
			}else{
				return false;
			}
		}
	})
	$('.noneMemberPassword').keyup(function(){
		$(this).val( $(this).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/gi,''));
	})
})