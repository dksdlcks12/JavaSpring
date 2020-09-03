<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-recallApply-box">
	<div class="user-recallApply-recallApplyBox">
		<h2>반품신청</h2>
		<div class="user-recallApply-recallApplyInfoBox">
				<div class="user-recallApply-orderNumberBox">
					<div>주문번호</div><div>1</div>
				</div>
				<div style="overflow-x: hidden;width:980px;height: 200px;">
					<table class="user-recallApply-goodsBox" border="1">
						<tr>
							<th><input type="checkbox" class="user-recallApply-goodsCheckAll"></th>
							<th class="user-recallApply-goodsImgTitle">이미지</th>
							<th class="user-recallApply-goodsInfoTitle">제품 정보</th>
							<th class="user-recallApply-goodsPriceTitle">판매가</th>
							<th class="user-recallApply-goodsCountTitle">수량</th>
						</tr>
						<tr>
							<td><input type="checkbox" class="user-recallApply-goodsCheck"></td>
							<td class="user-recallApply-goodsImg"><img src="#" alt="" ></td>
							<td class="user-recallApply-goodsInfo"></td>
							<td class="user-recallApply-goodsPrice"></td>
							<td class="user-recallApply-goodsCount"></td>
						</tr>
						<tr>
							<td><input type="checkbox" class="user-recallApply-goodsCheck"></td>
							<td class="user-recallApply-goodsImg"><img src="#" alt="" ></td>
							<td class="user-recallApply-goodsInfo"></td>
							<td class="user-recallApply-goodsPrice"></td>
							<td class="user-recallApply-goodsCount"></td>
						</tr>
						<tr>
							<td><input type="checkbox" class="user-recallApply-goodsCheck"></td>
							<td class="user-recallApply-goodsImg"><img src="#" alt="" ></td>
							<td class="user-recallApply-goodsInfo"></td>
							<td class="user-recallApply-goodsPrice"></td>
							<td class="user-recallApply-goodsCount"></td>
						</tr>
					</table>
				</div>
				<div class="user-recallApply-recallOptionBox">
					<div class="user-recallApply-selectGoodsBox">
						<div class="user-recallApply-selectGoodsTitle">선택된 상품명</div><div class="user-recallApply-selectGoods"></div>
					</div>
					<select name="" id="" class="user-recallApply-recallReason">
						<option selected>----반품사유----</option>
						<option value="0">단순변심</option>
						<option value="1">제품파손</option>
						<option value="2">배송지연</option>
						<option value="3">오배송</option>
					</select>
				</div><br>
				<div class="common-boardWrite-Content">
						<div id="recallApply"></div>
						<script>
							
						</script>
				</div>
				<div class="transImg" rows="" cols="" hidden></div>
				<textarea class="sandNote" rows="" cols="" hidden></textarea>
				<button class="user-recallApply-button">반품신청</button>
		</div>
	</div>
</div>
<script>
	$(function(){
		var imgList = [];
		$('.user-recallApply-goodsCheckAll').click(function(){
			if($('.user-recallApply-goodsCheckAll').is(':checked')){
				$('.user-recallApply-goodsCheck').prop('checked', true);
			}else{
				$('.user-recallApply-goodsCheck').prop('checked', false);
			}
		})
		$('.user-recallApply-button').click(function(){
			var index = 0;
			$('.transImg').html($('#recallApply').summernote('code'));
			$('.transImg img').each(function(){
				$(this).attr('src', '<%=request.getContextPath()%>/resources/image/recallApply'+imgList[index]);
				index++;
			})
			$('.sandNote').html($('.transImg').html());
			console.log($('.sandNote').html());
			console.log(imgList);
		})
		$('#recallApply').summernote({
			tabsize: 2,
			height: 300,
			lang : 'ko-KR',
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['color', ['color']],
				['insert', ['picture']],
				['view', ['codeview']]
			],
			callbacks: {
				onImageUpload: function(files, editor, welEditable) {
		            for (var i = files.length - 1; i >= 0; i--) {
		            	sendFile(files[i], this);
		            }
		        }
			}
		});
		function sendFile(file, el) {
			var form_data = new FormData();
	      	form_data.append('file', file);
	      	$.ajax({
	        	data: form_data,
	        	type: "POST",
	        	url: '<%=request.getContextPath()%>/recallApplyImg',
	        	cache: false,
	        	contentType: false,
	        	enctype: 'multipart/form-data',
	        	processData: false,
	        	success: function(data) {
	          		$(el).summernote('editor.insertImage', '<%=request.getContextPath()%>/resources/image/photo6.JPG');
	          		imgList.push(data.img);
	        	}
	      	});
	    }
	})
</script>