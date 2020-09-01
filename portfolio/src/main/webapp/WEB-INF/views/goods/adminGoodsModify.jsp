<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-goodsAdd-box">
	<div class="admin-goodsAdd-goodsAddBox">
		<form action="<%=request.getContextPath()%>/admin/goodsmodify" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
			<div class="admin-goodsAdd-leftBox">
				<h2>제품 이미지</h2>
				<input type="file" class="admin-goodsAdd-goodsImgAdd" name="goodsImgAdd" accept=".jpg,.jpeg,.png,.gif" onchange="LoadImg(this);"><br>
				<img src="<%=request.getContextPath()%>/resources/image/goodsImg/${goods.goodsImg}" alt="" class="admin-goodsAdd-goodsImg" style="display: block;">
				<h2>제품 설명 이미지</h2>
				<input type="file" class="admin-goodsAdd-goodsExplainImgAdd" name="goodsExplainImgAdd" accept=".jpg,.jpeg,.png,.gif" onchange="LoadExplainImg(this);"><br>
				<img src="<%=request.getContextPath()%>/resources/image/goodsImg/${post.postImg}" alt="" class="admin-goodsAdd-goodsExplainImg" style="display: block;">
				<!-- 미리보기 -->
				<script>
					$('.admin-goodsAdd-goodsImgAdd').change(function(){
						$('.admin-goodsAdd-goodsImg').attr('src', '').css('display','none');
						if( $(this).val() != "" ){
							var ext = $(this).val().split('.').pop().toLowerCase();
							if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
								alert('gif,png,jpg,jpeg 파일만 업로드 할수 있습니다.');
								$(this).val('');
								return;
							}
						}
					})
					$('.admin-goodsAdd-goodsExplainImgAdd').change(function(){
						$('.admin-goodsAdd-goodsExplainImg').attr('src', '').css('display','none');
						if( $(this).val() != "" ){
							var ext = $(this).val().split('.').pop().toLowerCase();
							if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
								alert('gif,png,jpg,jpeg 파일만 업로드 할수 있습니다.');
								$(this).val('');
								return;
							}
						}
					})
					function LoadImg(value){
						var ext = $('.admin-goodsAdd-goodsImgAdd').val().split('.').pop().toLowerCase();
						if($.inArray(ext, ['gif','png','jpg','jpeg']) != -1) {
							var reader = new FileReader();
							reader.onload = function (e){
								$('.admin-goodsAdd-goodsImg').attr('src', e.target.result).css('display','block');
							}
							reader.readAsDataURL(value.files[0]);
						}
					}
					function LoadExplainImg(value){
						var ext = $('.admin-goodsAdd-goodsExplainImgAdd').val().split('.').pop().toLowerCase();
						if($.inArray(ext, ['gif','png','jpg','jpeg']) != -1) {
							var reader = new FileReader();
							reader.onload = function (e){
								$('.admin-goodsAdd-goodsExplainImg').attr('src', e.target.result).css('display','block');
							}
							reader.readAsDataURL(value.files[0]);
						}
					}
				</script>
			</div>
			<div class="admin-goodsAdd-rightBox">
				<h2>제품 정보</h2>
				<div class="admin-goodsAdd-goodsNameBox">
					<div class="admin-goodsAdd-goodsNameTitle">제품명</div>
					<div class="admin-goodsAdd-goodsName" name="goodsName">${goods.goodsName}</div>
				</div>
				<div class="admin-goodsAdd-goodsPriceBox">
					<div class="admin-goodsAdd-goodsPriceTitle">가 격</div>
					<input type="text" class="admin-goodsAdd-goodsPrice number" name="goodsPrice" value="${goods.goodsPrice}">
					<div class="admin-goodsAdd-goodsPayPointTitle">적립금</div>
					<input type="text" class="admin-goodsAdd-goodsPayPoint number" name="goodsPoint" value="${goods.goodsPoint}">
					<div class="admin-goodsAdd-goodsDiscountTitle">할인율(%)</div>
					<input type="text" class="admin-goodsAdd-goodsDiscount number" name="postDiscount" value="${post.postDiscount}">
				</div>
				<div class="admin-goodsAdd-goodsTypeBox">
					<div class="admin-goodsAdd-goodsTypeTitle">타 입</div>
					<div class="admin-goodsAdd-goodsType"><c:if test="${goods.goodsType==1}">목걸이</c:if><c:if test="${goods.goodsType==2}">반지</c:if><c:if test="${goods.goodsType==3}">귀찌</c:if><c:if test="${goods.goodsType==4}">귀걸이</c:if></div>
				</div>
				<h2>옵션 추가</h2>
				<div class="admin-goodsAdd-goodsOptionAddBox">
					<div class="admin-goodsAdd-goodsColorTitle">색 상</div>
					<input type="text" class="admin-goodsAdd-goodsColor">
					<div class="admin-goodsAdd-goodsStockTitle">재고량</div>
					<input type="text" class="admin-goodsAdd-goodsStock number">
					<button type="button" class="admin-goodsAdd-goodsOptionButton"><i class="fas fa-plus"></i></button>
				</div>
				<script>
					$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsOptionButton').click(function(){
						var check = false;
						$('.admin-goodsAdd-goodsOptionBox').each(function(){
							if($(this).children('.admin-goodsAdd-goodsColor').text()==$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor').val()){
								check = true;
							}
						})
						if(check==false){
							if($('.admin-goodsAdd-goodsStock').val()!='' && $('.admin-goodsAdd-goodsColor')!=''){
								if($('.admin-goodsAdd-goodsOptionBox').length==0){
									$('.admin-goodsAdd-goodsOptionView').append('<h2>옵션</h2>')
								}
								$('.admin-goodsAdd-goodsOptionView').append('<div class="admin-goodsAdd-goodsOptionBox"><div class="admin-goodsAdd-goodsColorTitle">색 상</div><div class="admin-goodsAdd-goodsColor">'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor').val()+'</div><div class="admin-goodsAdd-goodsStockTitle">재고량</div><input class="admin-goodsAdd-goodsStock number" name="stock" value="'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsStock').val()+'"><button class="admin-goodsAdd-goodsOptionButton"><i class="fas fa-minus"></i></button></div><input name="color" value="'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor').val()+'" hidden>')
								$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor, .admin-goodsAdd-goodsStock').val('');
								$('.admin-goodsAdd-goodsOptionBox').children('.admin-goodsAdd-goodsOptionButton').click(function(){
									if($('.admin-goodsAdd-goodsOptionBox').length==1){
										$(this).parent().prev().remove();
									}
									$(this).parent().remove();
								})
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
							}else{
								alert("색상과 재고량을 입력하여 주십시오.")
							}
						}else{
							alert("동일한 색상이 있습니다.")
							$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor, .admin-goodsAdd-goodsStock').val('');
						}
					})
					$('.admin-goodsAdd-goodsOptionBox').children('.admin-goodsAdd-goodsOptionButton').click(function(){
						if($('.admin-goodsAdd-goodsOptionBox').length==1){
							$(this).parent().prev().remove();
						}
						$(this).parent().remove();
					})
				</script>
				<div class="admin-goodsAdd-goodsOptionView">
					<h2>옵션</h2>
					<c:forEach var="option" items="${list}">
						<div class="admin-goodsAdd-goodsOptionBox">
							<div class="admin-goodsAdd-goodsColorTitle">색 상</div>
							<div class="admin-goodsAdd-goodsColor">${option.optionColor}</div>
							<div class="admin-goodsAdd-goodsStockTitle">재고량</div><input class="admin-goodsAdd-goodsStock number" name="stock" value="${option.optionStock}">
							<button class="admin-goodsAdd-goodsOptionButton delOptionButton"><i class="fas fa-minus"></i></button>
						</div>
						<input name="color" value="${option.optionColor}" hidden>
					</c:forEach>
				</div>
				<input name="page" value="${page}" hidden>
				<input name="postNum" value="${post.postNum}" hidden>
				<input name="goodsNum" value="${goods.goodsNum}" hidden>
				<button class="admin-goodsModify-button">수정완료</button>
			</div>
		</form>
	</div>
</div>