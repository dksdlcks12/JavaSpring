<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-goodsAdd-box">
		<div class="admin-goodsAdd-goodsAddBox">
			<form action="<%=request.getContextPath()%>/admin/goodsadd" method="POST" enctype="multipart/form-data">
				<div class="admin-goodsAdd-leftBox">
					<h2>제품 이미지</h2>
					<input type="file" class="admin-goodsAdd-goodsImgAdd" name="goodsImg" accept=".jpg,.jpeg,.png,.gif" onchange="LoadImg(this);"><br>
					<img src="" alt="" class="admin-goodsAdd-goodsImg">
					<h2>제품 설명 이미지</h2>
					<input type="file" class="admin-goodsAdd-goodsExplainImgAdd" name="goodsExplainImg" accept=".jpg,.jpeg,.png,.gif" onchange="LoadExplainImg(this);"><br>
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
						<input class="admin-goodsAdd-goodsName">
					</div>
					<div class="admin-goodsAdd-goodsPriceBox">
						<div class="admin-goodsAdd-goodsPriceTitle">가 격</div>
						<input type="text" class="admin-goodsAdd-goodsPrice number">
						<div class="admin-goodsAdd-goodsPayPointTitle">적립금</div>
						<input type="text" class="admin-goodsAdd-goodsPayPoint number">
					</div>
					<div class="admin-goodsAdd-goodsTypeBox">
						<div class="admin-goodsAdd-goodsTypeTitle">타 입</div>
						<select name="" id="" class="admin-goodsAdd-goodsType">
							<option value="" selected>----제품 타입----</option>
							<option value="1">목 걸 이</option>
							<option value="2">반 지</option>
							<option value="3">귀 걸 이</option>
							<option value="4">귀 찌</option>
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
							$('.admin-goodsAdd-goodsOptionView').append('<div class="admin-goodsAdd-goodsOptionBox"><div class="admin-goodsAdd-goodsColorTitle">색 상</div><div class="admin-goodsAdd-goodsColor">'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor').val()+'</div><div class="admin-goodsAdd-goodsStockTitle">재고량</div><div class="admin-goodsAdd-goodsStock">'+$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsStock').val()+'</div><button class="admin-goodsAdd-goodsOptionButton"><i class="fas fa-minus"></i></button></div>')
							$('.admin-goodsAdd-goodsOptionAddBox').children('.admin-goodsAdd-goodsColor, .admin-goodsAdd-goodsStock').val('');
							$('.admin-goodsAdd-goodsOptionBox').children('.admin-goodsAdd-goodsOptionButton').click(function(){
							$(this).parent().remove();
						})
						})
					</script>
					<h2>옵션</h2>
					<div class="admin-goodsAdd-goodsOptionView"></div>
					<button class="admin-goodsAdd-button">등록하기</button>
				</div>
			</form>
		</div>
	</div>