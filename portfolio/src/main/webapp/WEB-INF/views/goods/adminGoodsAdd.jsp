<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-goodsAdd-box">
		<div class="admin-goodsAdd-goodsAddBox">
			<form action="<%=request.getContextPath()%>/admin/goodsadd" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
				<div class="admin-goodsAdd-leftBox">
					<h2>제품 이미지</h2>
					<input type="file" class="admin-goodsAdd-goodsImgAdd" name="goodsImgAdd" accept=".jpg,.jpeg,.png,.gif" onchange="LoadImg(this);"><br>
					<img src="" alt="" class="admin-goodsAdd-goodsImg">
					<h2>제품 설명 이미지</h2>
					<input type="file" class="admin-goodsAdd-goodsExplainImgAdd" name="goodsExplainImgAdd" accept=".jpg,.jpeg,.png,.gif" onchange="LoadExplainImg(this);"><br>
					<img src="" alt="" class="admin-goodsAdd-goodsExplainImg">
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
						<input class="admin-goodsAdd-goodsName" name="goodsName">
					</div>
					<div class="admin-goodsAdd-goodsPriceBox">
						<div class="admin-goodsAdd-goodsPriceTitle">가 격</div>
						<input type="text" class="admin-goodsAdd-goodsPrice number"  name="goodsPrice">
						<div class="admin-goodsAdd-goodsPayPointTitle">적립금</div>
						<input type="text" class="admin-goodsAdd-goodsPayPoint number" name="goodsPoint">
						<div class="admin-goodsAdd-goodsDiscountTitle">할인율</div>
						<input type="text" class="admin-goodsAdd-goodsDiscount number" name="postDiscount">
					</div>
					<div class="admin-goodsAdd-goodsTypeBox">
						<div class="admin-goodsAdd-goodsTypeTitle">타 입</div>
						<select class="admin-goodsAdd-goodsType" name="goodsType">
							<option value="" selected>----제품 타입----</option>
							<option value="1">목 걸 이</option>
							<option value="2">반 지</option>
							<option value="3">귀 찌</option>
							<option value="4">귀 걸 이</option>
						</select>
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
							if($('.admin-goodsAdd-goodsStock').val()!='' && $('.admin-goodsAdd-goodsColor')!=''){
								if($('.admin-goodsAdd-goodsOptionBox').length==0){
									$('.admin-goodsAdd-goodsOptionView').append('<h2>옵션</h2>')
								}
								$('.admin-goodsAdd-goodsOptionView').append('<div class="admin-goodsAdd-goodsOptionBox"><div class="admin-goodsAdd-goodsColorTitle">색 상</div><div class="admin-goodsAdd-goodsColor">'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor').val()+'</div><div class="admin-goodsAdd-goodsStockTitle">재고량</div><div class="admin-goodsAdd-goodsStock">'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsStock').val()+'</div><button class="admin-goodsAdd-goodsOptionButton"><i class="fas fa-minus"></i></button></div><input name="color" value="'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor').val()+'" hidden><input name="stock" value="'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsStock').val()+'" hidden>')
								$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor, .admin-goodsAdd-goodsStock').val('');
								$('.admin-goodsAdd-goodsOptionBox').children('.admin-goodsAdd-goodsOptionButton').click(function(){
									if($('.admin-goodsAdd-goodsOptionBox').length==1){
										$(this).parent().prev().remove();
									}
									$(this).parent().remove();
								})
							}else{
								alert("색상과 재고량을 입력하여 주십시오.")
							}
						})
					</script>
					<div class="admin-goodsAdd-goodsOptionView"></div>
					<button class="admin-goodsAdd-button">등록하기</button>
				</div>
			</form>
		</div>
	</div>