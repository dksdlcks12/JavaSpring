$(function(){
	$('.user-set-listEarRing').hover(function(){$('.user-set-listEarRing').children().css('display', 'list-item')},function(){$('.user-set-listEarRing').children().css('display', 'none')})
	$('.user-set-goTop').click(function(){
		document.documentElement.scrollTop = 0;
	})
})