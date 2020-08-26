<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-order-box">
	<form action="#">
		<div class="user-order-orderBox">
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
				<tr>
					<td><input type="checkbox" class="user-order-goodsCheck"></td>
					<td class="user-order-goodsImg"><img src="상품대용.gif" alt="" ></td>
					<td class="user-order-goodsInfo"></td>
					<td class="user-order-goodsPrice"><span class="user-order-goodsPriceNumber">2000</span>원</td>
					<td class="user-order-goodsCount">1</td>
					<td class="user-order-goodsGoodsPoint">1111</td>
					<td class="user-order-goodsUsePoint"><input type="text" class="number" value="0"></td>
					<td class="user-order-allGoodsPrice"><span class="user-order-allGoodsPriceNumber"></span></td>
				</tr>
				<tr>
					<td><input type="checkbox" class="user-order-goodsCheck"></td>
					<td class="user-order-goodsImg"><img src="상품대용.gif" alt="" ></td>
					<td class="user-order-goodsInfo"></td>
					<td class="user-order-goodsPrice"><span class="user-order-goodsPriceNumber">3000</span>원</td>
					<td class="user-order-goodsCount">2</td>
					<td class="user-order-goodsGoodsPoint"></td>
					<td class="user-order-goodsUsePoint"><input type="text" class="number" value="400"></td>
					<td class="user-order-allGoodsPrice"><span class="user-order-allGoodsPriceNumber"></span></td>
				</tr>
				<tr>
					<td><input type="checkbox" class="user-order-goodsCheck"></td>
					<td class="user-order-goodsImg"><img src="상품대용.gif" alt="" ></td>
					<td class="user-order-goodsInfo"></td>
					<td class="user-order-goodsPrice"><span class="user-order-goodsPriceNumber">4000</span>원</td>
					<td class="user-order-goodsCount">3</td>
					<td class="user-order-goodsGoodsPoint"></td>
					<td class="user-order-goodsUsePoint"><input type="text" class="number" value="400"></td>
					<td class="user-order-allGoodsPrice"><span class="user-order-allGoodsPriceNumber"></span></td>
				</tr>
			</table>
			<button class="user-order-goodsDelete">선택 삭제</button>
			<div class="user-order-totalPrice"></div>
			<div class="user-order-peopleInfo">
				<p>주문정보</p>
				<table class="user-order-Info" border="1">
					<tr>
						<td>주문한 사람 (필수)</td>
						<td><input type="text" class="user-order-name"></td>
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
						<td><input type="tel" class="user-order-tel">-<input type="tel" class="user-order-tel">-<input type="tel" class="user-order-tel"></td>
					</tr>
					<tr>
						<td>비회원 비밀번호 (필수)</td>
						<td><input type="password" class="user-order-password">(영문자 또는 숫자로 이루어진 4~8자)</td>
					</tr>
					<tr>
						<td>비회원 비밀번호 확인 (필수)</td>
						<td><input type="password" class="user-order-password"></td>
					</tr>
				</table>
				<p>배송정보</p>
				<table class="user-order-Info" border="1">
					<tr>
						<td>받는 사람 (필수)</td>
						<td><input type="text" class="user-order-name"></td>
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
						<td><input type="tel" class="user-order-tel">-<input type="tel" class="user-order-tel">-<input type="tel" class="user-order-tel"></td>
					</tr>
				</table>
				<p>결제</p>
				<div class="user-order-pay"></div>
			</div>
		</div>
	</form>
</div>