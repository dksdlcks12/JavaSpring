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
							<th class="user-recallApply-goodsPriceTitle">구매비용</th>
							<th class="user-recallApply-goodsCountTitle">수량</th>
						</tr>
						<c:forEach var="orderList" items="${list}">
						<tr>
							<td><input type="checkbox" class="user-recallApply-goodsCheck"><sapn class="user-recallApply-orderListNum" hidden>${orderList.orderListNum}</td>
							<td class="user-recallApply-goodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${orderList.goodsImg}" alt="" ></td>
							<td class="user-recallApply-goodsInfo">제품명 : <span class="user-recallApply-goodsName">${orderList.goodsName}</span><br>색상 : <span class="user-recallApply-goodsColor">${orderList.optionColor}</span></td>
							<td class="user-recallApply-goodsPrice">${orderList.payPrice}</td>
							<td class="user-recallApply-goodsCount">${orderList.orderListCount}</td>
						</tr>
						</c:forEach>
					</table>
				</div>
				<div class="user-recallApply-recallOptionBox">
					<div class="user-recallApply-selectGoodsBox">
						<div class="user-recallApply-selectGoodsTitle">선택된 상품명</div><div class="user-recallApply-selectGoods"></div>
					</div>
					<select name="" id="" class="user-recallApply-recallReason">
						<option value="" selected>----반품사유----</option>
						<option>단순변심</option>
						<option>제품파손</option>
						<option>배송지연</option>
						<option>오배송</option>
					</select>
					<select name="" id="" class="user-recallApply-recallChange">
						<option value="" selected>----교환여부----</option>
						<option>교환</option>
						<option>환불</option>
					</select>
				</div><br>
				<div class="common-boardWrite-Content">
						<div id="recallApply"></div>
				</div>
				<div class="transImg" hidden></div>
				<textarea class="sandNote" hidden></textarea>
				<button class="user-recallApply-button">반품신청</button>
		</div>
	</div>
</div>
<script>
	$(function(){
		var imgList = [];
		var goodsInfo = "";
		$('.user-recallApply-goodsCheckAll').click(function(){
			goodsInfo = "";
			if($('.user-recallApply-goodsCheckAll').is(':checked')){
				$('.user-recallApply-goodsCheck').prop('checked', true);
			}else{
				$('.user-recallApply-goodsCheck').prop('checked', false);
			}
			$('.user-recallApply-goodsCheck').each(function(){
				if($(this).is(':checked')){
					goodsInfo = goodsInfo + '[' + $(this).parent().siblings('.user-recallApply-goodsInfo').children('.user-recallApply-goodsName').text();
					goodsInfo = goodsInfo + '/' + $(this).parent().siblings('.user-recallApply-goodsInfo').children('.user-recallApply-goodsColor').text() + ']';
				}
				$('.user-recallApply-selectGoods').text(goodsInfo);
			})
		})
		$('.user-recallApply-goodsCheck').click(function(){
			goodsInfo = "";
			if($(this).is(':checked')){
				$(this).prop('checked', true);
			}else{
				$(this).prop('checked', false);
			}
			$('.user-recallApply-goodsCheck').each(function(){
				if($(this).is(':checked')){
					goodsInfo = goodsInfo + '[' + $(this).parent().siblings('.user-recallApply-goodsInfo').children('.user-recallApply-goodsName').text();
					goodsInfo = goodsInfo + '/' + $(this).parent().siblings('.user-recallApply-goodsInfo').children('.user-recallApply-goodsColor').text() + ']';
				}
				$('.user-recallApply-selectGoods').text(goodsInfo);
			})
		})
		$('.user-recallApply-button').click(function(){
			var index = 0;
			var goodsList = [];
			var arr = []; 
			$('.transImg').html($('#recallApply').summernote('code'));
			$('.transImg img').each(function(){
				$(this).attr('src', '<%=request.getContextPath()%>/resources/image/recallApply'+imgList[index]);
				index++;
			})
			$('.sandNote').html($('.transImg').html());
			console.log($('.sandNote').html());
			$('.user-recallApply-goodsCheck').each(function(){
				if($(this).is(':checked')){
					orderListNum = Number($(this).siblings('.user-recallApply-orderListNum').text());
					goodsList.push({"orderListNum":orderListNum});
				}
			})
			var sandNote = $('.sandNote').html();
			console.log(goodsList);	
			arr.push({"goodsList":goodsList, "sandNote":sandNote});
			console.log(arr);
			console.log($('.user-recallApply-recallReason').val());
			if(sandNote != '&lt;p&gt;&lt;br&gt;&lt;/p&gt;'){
				$.ajax({
					async:false,
					type:'POST',
					data: JSON.stringify(arr),
					url:"<%=request.getContextPath()%>/recallAdd",
					dataType:"json",
					contentType:"application/json; charset=UTF-8",
					success : function(data){
					}
				});
			}else{
				alert("반품내용를 작성해주세요.")
			}
		})
		$('#recallApply').summernote({
			tabsize: 2,
			height: 300,
			lang : 'ko-KR',
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['color', ['color']],
				['insert', ['picture']]
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