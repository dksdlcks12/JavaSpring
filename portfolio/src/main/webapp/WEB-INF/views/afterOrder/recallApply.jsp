<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-recallApply-box">
	<div class="user-recallApply-recallApplyBox">
		<c:if test="${list.size()!=0}">
			<h2>반품신청</h2>
			<div class="user-recallApply-recallApplyInfoBox">
				<div class="user-recallApply-orderNumberBox">
					<div>주문번호</div><div class="user-recallApply-orderNumber">${orderNum}</div>
				</div>
				<div style="overflow-x: hidden;width:980px;height: 203px;">
					<table class="user-recallApply-goodsBox" border="1">
						<tr>
							<th><input type="checkbox" class="user-recallApply-goodsCheckAll"></th>
							<th class="user-recallApply-goodsImgTitle">이미지</th>
							<th class="user-recallApply-goodsInfoTitle">제품 정보</th>
							<th class="user-recallApply-goodsPriceTitle">구매비용</th>
							<th class="user-recallApply-goodsCountTitle">수량</th>
						</tr>
						<c:forEach var="orderList" items="${list}">
							<c:if test="${orderList.orderListIsRecall=='N'}">
								<tr>
									<td><input type="checkbox" class="user-recallApply-goodsCheck"><sapn class="user-recallApply-orderListNum" hidden>${orderList.orderListNum}</td>
									<td class="user-recallApply-goodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${orderList.goodsImg}" alt="" ></td>
									<td class="user-recallApply-goodsInfo">제품명 : <span class="user-recallApply-goodsName">${orderList.goodsName}</span><br>색상 : <span class="user-recallApply-goodsColor">${orderList.optionColor}</span></td>
									<td class="user-recallApply-goodsPrice">${orderList.payPrice}</td>
									<td class="user-recallApply-goodsCount">${orderList.orderListCount}</td>
								</tr>
							</c:if>
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
				<div class="user-recallApply-Content">
						<div id="recallApply"></div>
				</div>
				<div class="transImg" hidden></div>
				<textarea class="sandNote" hidden></textarea>
				<button class="user-recallApply-button">반품신청</button>
			</div>
		</c:if>
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
				$('.user-recallApply-Content').before('')
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
		$('.user-recallApply-recallChange').change(function(){
			if($(this).val()=='환불'){
				$('.user-recallApply-Content').before('<div class="user-recallAdd-recallAccount"><h4>환불받을 계좌정보</h4><div>은행명</div><input class="bank" type="text"><div>계좌번호( \'-\' 제외 )</div><input class="account" type="text"></div>')
			}else{
				$('.user-recallAdd-recallAccount').remove();
			}
			$('.account').keydown(function(event){
				var limit = 18;
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
		})
		$('.user-recallApply-button').click(function(){
			var index = 0;
			var goodsList = [];
			var arr = [];
			var recallReaseon = $('.user-recallApply-recallReason').val();
			var recallChange = $('.user-recallApply-recallChange').val();
			var orderNum = Number($('.user-recallApply-orderNumber').text());
			var recallAccount = $('.account').val();
			var recallBank = $('.bank').val();
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
			arr.push({"goodsList":goodsList, "sandNote":sandNote, "recallReaseon":recallReaseon, "recallChange":recallChange, "orderNum":orderNum, "recallAccount":recallAccount, "recallBank":recallBank});
			if(goodsList.length!=0){
				if(recallReaseon!=""){
					if(recallChange!=""){
						if(sandNote != '&lt;p&gt;&lt;br&gt;&lt;/p&gt;'){
							$.ajax({
								async:false,
								type:'POST',
								data: JSON.stringify(arr),
								url:"<%=request.getContextPath()%>/recalladd",
								dataType:"json",
								contentType:"application/json; charset=UTF-8",
								success : function(data){
									if(data.check){
										alert('반품신청이 성공적으로 이루어졌습니다.')
										location.replace('<%=request.getContextPath()%>/recallapplylist');
									}else{
										alert('비정상적인 접근으로 인하여 메인 페이지로 돌아갑니다.')
										location.replace('<%=request.getContextPath()%>/');
									}
								}
							});
						}else{
							alert("반품내용을 작성해주세요.")
						}
					}else{
						alert("교환여부를 선택하여 주세요.")
					}
				}else{
					alert("반품사유를 선택하여 주세요.")
				}
			}else{
				alert("반품 할 물건을 선택하여 주세요")
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
	        	url: '<%=request.getContextPath()%>/recallapplyimg',
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