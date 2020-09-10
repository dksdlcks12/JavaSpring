$(function(){
	$('.user-set-listEarRing').hover(function(){$('.user-set-listEarRing').children().css('display', 'list-item')},function(){$('.user-set-listEarRing').children().css('display', 'none')})
	$('.user-set-goTop').click(function(){
		window.scrollTo(0, 0);
	})
	window.onbeforeunload = function () {
		$('.common-board-pause').css("display","none")
		window.scrollTo(0, 0);
	}
})