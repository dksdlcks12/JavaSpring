<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-order-box">
	<div class="user-order-orderBox">
		<c:if test="${list ne null}">
			<table class="user-order-goodsBox" border="1">
				<tr>
					<th><input type="checkbox" class="user-order-goodsCheckAll"></th>
					<th class="user-order-goodsImgTitle">이미지</th>
					<th class="user-order-goodsInfoTitle">제품 정보</th>
					<th class="user-order-goodsPriceTitle">판매가</th>
					<th class="user-order-goodsCountTitle">수량</th>
					<th class="user-order-goodsGoodsPointTitle">적립 포인트</th>
					<th class="user-order-goodsUsePointTitle">사용할 포인트</th>
					<th class="user-order-allGoodsPriceTitle">합산금액</th>
				</tr>
				<c:forEach var="order" items="${list}">
					<tr>
						<td><input type="checkbox" class="user-order-goodsCheck" name="orderList" value="${order.cartNum}"></td>
						<td class="user-order-goodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${order.goodsImg}" alt="" ></td>
						<td class="user-order-goodsInfo">제품명 : ${order.goodsName}<br>색상 : ${order.optionColor}</td>
						<td class="user-order-goodsPrice"><span class="user-order-goodsPriceNumber">${order.goodsDiscountPrice}</span>원</td>
						<td class="user-order-goodsCount">${order.cartCount}</td>
						<td class="user-order-goodsGoodsPoint">${order.totalpoint}</td>
						<td class="user-order-goodsUsePoint"><input type="text" class="number" value="0"></td>
						<td class="user-order-allGoodsPrice"><span class="user-order-allGoodsPriceNumber">${order.goodsAllPrice}</span>원</td>
					</tr>
				</c:forEach>
			</table>
			<button type="button" class="user-order-goodsDelete">선택 삭제</button>
			<div class="user-order-totalPrice"></div>
			<div class="user-order-peopleInfo">
				<p>주문정보</p>
				<table class="user-order-Info" border="1">
					<tr>
						<td>주문한 사람 (필수)</td>
						<td><input type="text" class="user-order-name" id="senderName"></td>
					</tr>
					<tr>
						<td>주소 (필수)</td>
						<td class="user-order-addressBox">
							<input type="text" class="user-order-postcode" id="sendPostcode" placeholder="우편번호" readonly>
							<input type="button" class="user-order-postcodeButton" onclick="sendExecDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" class="user-order-address" id="sendAddress" placeholder="주소" readonly><br>
							<input type="text" class="user-order-detailAddress" id="sendDetailAddress" placeholder="상세주소">
							<input type="text" class="user-order-extraAddress" id="sendExtraAddress" placeholder="참고항목">

							<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							<script>
								function sendExecDaumPostcode() {
									new daum.Postcode({
										oncomplete: function(data) {
											// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

											// 각 주소의 노출 규칙에 따라 주소를 조합한다.
											// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
											var addr = ''; // 주소 변수
											var extraAddr = ''; // 참고항목 변수

											//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
											if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
												addr = data.roadAddress;
											} else { // 사용자가 지번 주소를 선택했을 경우(J)
												addr = data.jibunAddress;
											}

											// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
											if(data.userSelectedType === 'R'){
												// 법정동명이 있을 경우 추가한다. (법정리는 제외)
												// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
												if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
													extraAddr += data.bname;
												}
												// 건물명이 있고, 공동주택일 경우 추가한다.
												if(data.buildingName !== '' && data.apartment === 'Y'){
													extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
												}
												// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
												if(extraAddr !== ''){
													extraAddr = ' (' + extraAddr + ')';
												}
												// 조합된 참고항목을 해당 필드에 넣는다.
												document.getElementById("sendExtraAddress").value = extraAddr;
											
											} else {
												document.getElementById("sendExtraAddress").value = '';
											}

											// 우편번호와 주소 정보를 해당 필드에 넣는다.
											document.getElementById('sendPostcode').value = data.zonecode;
											document.getElementById("sendAddress").value = addr;
											// 커서를 상세주소 필드로 이동한다.
											document.getElementById("sendDetailAddress").focus();
										}
									}).open();
								}
							</script>
						</td>
					</tr>
					<tr>
						<td>주문한 사람 전화번호 (필수)</td>
						<td id="sendTel"><input type="tel" class="user-order-tel tel1" maxlength="4">-<input type="tel" class="user-order-tel tel2" maxlength="4">-<input type="tel" class="user-order-tel tel3" maxlength="4"></td>
					</tr>
					<c:if test="${user==null}">
						<tr>
							<td>비회원 비밀번호 (필수)</td>
							<td><input type="password" class="user-order-password noneMemberPassword" maxlength="8">(숫자 4~8자)</td>
						</tr>
						<tr>
							<td>비회원 비밀번호 확인 (필수)</td>
							<td><input type="password" class="user-order-password noneMemberPasswordCheck" maxlength="8"></td>
						</tr>
					</c:if>
				</table>
				<p>배송정보</p>
				<table class="user-order-Info" border="1">
					<tr>
						<td>받는 사람 (필수)</td>
						<td><input type="text" class="user-order-name" id="receiverName"></td>
					</tr>
					<tr>
						<td>주소 (필수)</td>
						<td class="user-order-addressBox">
							<input type="text" class="user-order-postcode" id="receivePostcode" placeholder="우편번호" readonly>
							<input type="button" class="user-order-postcodeButton" onclick="receiveExecDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" class="user-order-address" id="receiveAddress" placeholder="주소" readonly><br>
							<input type="text" class="user-order-detailAddress" id="receiveDetailAddress" placeholder="상세주소">
							<input type="text" class="user-order-extraAddress" id="receiveExtraAddress" placeholder="참고항목">

							<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							<script>
								function receiveExecDaumPostcode() {
									new daum.Postcode({
										oncomplete: function(data) {
											// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

											// 각 주소의 노출 규칙에 따라 주소를 조합한다.
											// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
											var addr = ''; // 주소 변수
											var extraAddr = ''; // 참고항목 변수

											//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
											if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
												addr = data.roadAddress;
											} else { // 사용자가 지번 주소를 선택했을 경우(J)
												addr = data.jibunAddress;
											}
											// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
											if(data.userSelectedType === 'R'){
												// 법정동명이 있을 경우 추가한다. (법정리는 제외)
												// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
												if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
													extraAddr += data.bname;
												}
												// 건물명이 있고, 공동주택일 경우 추가한다.
												if(data.buildingName !== '' && data.apartment === 'Y'){
													extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
												}
												// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
												if(extraAddr !== ''){
													extraAddr = ' (' + extraAddr + ')';
												}
												// 조합된 참고항목을 해당 필드에 넣는다.
												document.getElementById("receiveExtraAddress").value = extraAddr;
											
											} else {
												document.getElementById("receiveExtraAddress").value = '';
											}
											// 우편번호와 주소 정보를 해당 필드에 넣는다.
											document.getElementById('receivePostcode').value = data.zonecode;
											document.getElementById("receiveAddress").value = addr;
											// 커서를 상세주소 필드로 이동한다.
											document.getElementById("receiveDetailAddress").focus();
										}
									}).open();
								}
							</script>
						</td>
					</tr>
					<tr>
						<td>받는 사람 전화번호 (필수)</td>
						<td id="receiveTel"><input type="tel" class="user-order-tel tel1" maxlength="4">-<input type="tel" class="user-order-tel tel2" maxlength="4">-<input type="tel" class="user-order-tel tel3" maxlength="4"></td>
					</tr>
				</table>
				<p>결제</p>
				<div class="user-order-pay">
					<button class="user-order-button">결제하기</button>
				</div>
			</div>
		</c:if>
	</div>
</div>
<script>
	var allGoodsPrice=0;
	var totalPrice=0;
	var price=0;
	var point=0;
	var count=0;
	var defaultDeliveryPrice = 2500;
	var freeDeliveryLimit = 16000;
	var usePoint = 0;
	function totalPriceCalculation(){
		totalPrice = 0;
		$('.user-order-allGoodsPrice').each(function(){
			totalPrice = totalPrice + Number($(this).children('.user-order-allGoodsPriceNumber').text());
			if(totalPrice<freeDeliveryLimit){
				$('.user-order-totalPrice').text('배송비 '+defaultDeliveryPrice+'원 + '+totalPrice+'원 = '+Number(totalPrice+defaultDeliveryPrice)+'원');
			}else{
				$('.user-order-totalPrice').text('배송비 0원 + '+totalPrice+'원 = '+totalPrice+'원');
			} 
		})
	}
	$('.user-order-goodsUsePoint').children('input').change(function(){
		price = Number($(this).parent().siblings('.user-order-goodsPrice').children('.user-order-goodsPriceNumber').text());
		count = Number($(this).parent().siblings('.user-order-goodsCount').text());
		if($(this).val()<0){
			$(this).val(0);
		}
		if(Number(${user.userPoint}-usePoint)<$(this).val()){
			$(this).val(Number(${user.userPoint}-usePoint))
		}
		if($(this).val()>(price*count)/10){
			$(this).val((price*count)/10);
		}
		if($(this).val()%10!=0){
			$(this).val($(this).val()-($(this).val()%10));
		}
		$(this).parent().siblings('.user-order-allGoodsPrice').children('.user-order-allGoodsPriceNumber').text(price*count-$(this).val());
		totalPriceCalculation();
	})
	$('.user-order-goodsUsePoint').children('input').focusin(function(){
		usePoint = usePoint - Number($(this).val());
		console.log(usePoint);
	})
		$('.user-order-goodsUsePoint').children('input').focusout(function(){
		usePoint = usePoint + Number($(this).val());
		console.log(usePoint);
	})
	if($('.user-order-goodsCheck').length==0){
		location.href='<%=request.getContextPath()%>/cart' 
	}
	$('.user-order-goodsDelete').click(function(){
		$('.user-order-goodsCheck').each(function(){
			if($(this).is(':checked')){
				$(this).parent().parent().remove();
			}
		})
		$('.user-order-goodsCheck').prop('checked', false);
		if($('.user-order-goodsCheck').length==0){
			location.href='<%=request.getContextPath()%>/cart' 
		}else{
			totalPriceCalculation()
		}
	})
	$('.user-order-button').click(function(){
		totalPriceCalculation();
		if(totalPrice<freeDeliveryLimit){
			totalPrice = Number(totalPrice+defaultDeliveryPrice);
		}
		var arr = [];
		var goodsList = [];
		var orderList = [];
		var senderName = $('#senderName').val();
		var sendPostcode = $('#sendPostcode').val();
		var sendAddress = $('#sendAddress').val();
		var sendDetailAddress = $('#sendDetailAddress').val();
		var sendExtraAddress = $('#sendExtraAddress').val();
		var sendTel = $('#sendTel').children('.tel1').val()+$('#sendTel').children('.tel2').val()+$('#sendTel').children('.tel3').val();
		var noneMemberPassword = $('.noneMemberPassword').val();
		var receiverName = $('#receiverName').val();
		var receivePostcode = $('#receivePostcode').val();
		var receiveAddress = $('#receiveAddress').val();
		var receiveDetailAddress = $('#receiveDetailAddress').val();
		var receiveExtraAddress = $('#receiveExtraAddress').val();
		var receiveTel = $('#receiveTel').children('.tel1').val()+$('#receiveTel').children('.tel2').val()+$('#receiveTel').children('.tel3').val();
		if(senderName!="" && sendPostcode!="" && sendAddress!="" && sendDetailAddress!="" && $('#sendTel').children('.tel1').val()!="" && $('#sendTel').children('.tel2').val()!="" && $('#sendTel').children('.tel3').val()!="" && noneMemberPassword!="" && receiverName!="" && receivePostcode!="" && receiveAddress!="" && receiveDetailAddress!="" && $('#receiveTel').children('.tel1').val()!="" && $('#receiveTel').children('.tel2').val()!="" && $('#receiveTel').children('.tel3').val()!=""){
			$('.user-order-goodsCheck').each(function(){
				var orderNum = $(this).val();
				var orderCount = $(this).parent().siblings('.user-order-goodsCount').text();
				var orderPrice = $(this).parent().siblings('.user-order-goodsPrice').children('.user-order-goodsPriceNumber').text();
				var orderUsePoint = $(this).parent().siblings('.user-order-goodsUsePoint').children('.number').val();
				var orderPoint = $(this).parent().siblings('.user-order-goodsGoodsPoint').text();
				goodsList.push({'orderNum':orderNum, 'orderCount':orderCount, 'orderPrice':orderPrice, 'orderUsePoint':orderUsePoint, 'orderPoint':orderPoint});
			})
			orderList.push({'totalPrice':totalPrice,'senderName':senderName,  'sendPostcode':sendPostcode, 'sendAddress':sendAddress, 'sendDetailAddress':sendDetailAddress, 'sendExtraAddress':sendExtraAddress, 'sendTel':sendTel, 'noneMemberPassword':noneMemberPassword, 'receiverName':receiverName, 'receivePostcode':receivePostcode, 'receiveAddress':receiveAddress, 'receiveDetailAddress':receiveDetailAddress, 'receiveExtraAddress':receiveExtraAddress, 'receiveTel':receiveTel})
			arr.push({'goodsList':goodsList, 'orderList':orderList});
			$.ajax({
				async:false,
				type:'POST',
				data: JSON.stringify(arr),
				url:"<%=request.getContextPath()%>/addorder",
				dataType:"json",
				contentType:"application/json; charset=UTF-8",
				success : function(data){
					if(data.stock){
						alert('주문이 완료되었습니다.')
						location.replace('<%=request.getContextPath()%>/orderviewlist')
					}else{
						alert('재고가 부족합니다.')
					}
				}
			});
		}else{
			alert('주문정보를 모두 입력하여 주십시오.')
		}
	})
</script>