<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-orderView-box">
	<div class="user-orderView-orderViewBox">
		<h2>주문조회</h2>
		<div class="user-orderView-orderNumberBox">
			<div class="user-orderView-orderTitleNumber">
				주문번호
			</div>
			<div class="user-orderView-orderNumber">
				1
			</div>
		</div>
		<div class="user-orderView-orderGoodsTitleBox">
			<div class="user-orderView-orderGoodsTitleImg">이미지</div>
			<div class="user-orderView-orderGoodsTitleName">제품명</div>
			<div class="user-orderView-orderGoodsTitleDisCountPrice">할인가</div>
			<div class="user-orderView-orderGoodsTitleCount">주문량</div>
			<div class="user-orderView-orderGoodsTitleUsePoint">사용포인트</div>
			<div class="user-orderView-orderGoodsTitlePayPrice">결제금액</div>
		</div>
		<div class="user-orderView-orderGoodsBox">
			<div class="user-orderView-orderGoodsImg"><img src="상품대용.gif" alt=""></div>
			<div class="user-orderView-orderGoodsName">d<br>d</div>
			<div class="user-orderView-orderGoodsDisCountPrice">11111111원</div>
			<div class="user-orderView-orderGoodsCount">d</div>
			<div class="user-orderView-orderGoodsUsePoint">d</div>
			<div class="user-orderView-orderGoodsPayPrice">d</div>
		</div>
		<div class="user-orderView-orderGoodsBox">
			<div class="user-orderView-orderGoodsImg"></div>
			<div class="user-orderView-orderGoodsName"></div>
			<div class="user-orderView-orderGoodsDisCountPrice"></div>
			<div class="user-orderView-orderGoodsCount"></div>
			<div class="user-orderView-orderGoodsUsePoint"></div>
			<div class="user-orderView-orderGoodsPayPrice"></div>
		</div>
		<div class="user-orderView-orderGoodsBox">
			<div class="user-orderView-orderGoodsImg"></div>
			<div class="user-orderView-orderGoodsName"></div>
			<div class="user-orderView-orderGoodsDisCountPrice"></div>
			<div class="user-orderView-orderGoodsCount"></div>
			<div class="user-orderView-orderGoodsUsePoint"></div>
			<div class="user-orderView-orderGoodsPayPrice"></div>
		</div>
		<div class="user-orderView-totalPriceBox">
			<div class="user-orderView-totalPrice">배송비 : 2500원 + 15000원<br>= 17500원</div>
		</div>	
		<div class="user-orderView-peopleInfo">
			<p>주문정보</p>
			<table class="user-orderView-Info" border="1">
				<tr>
					<td>주문한 사람</td>
					<td></td>
				</tr>
				<tr>
					<td>주소</td>
					<td class="user-orderView-addressLine">
						<div class="user-orderView-postcodeBox">
							<div class="user-orderView-addressLineTitle">우편번호</div>
							<div class="user-orderView-postcode"></div>
						</div>
						<div class="user-orderView-addressBox">
							<div class="user-orderView-addressLineTitle">주소</div>
							<div class="user-orderView-address"></div>
						</div>
						<div class="user-orderView-detailAddressBox">
							<div class="user-orderView-addressLineTitle">상세주소</div>
							<div class="user-orderView-detailAddress"></div>
						</div>
						<div class="user-orderView-extraAddressBox">
							<div class="user-orderView-addressLineTitle">참고항목</div>
							<div class="user-orderView-extraAddress"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td>주문한 사람 전화번호</td>
					<td></td>
				</tr>
			</table>
			<p>배송정보</p>
			<table class="user-orderView-Info" border="1">
				<tr>
					<td>받는 사람</td>
					<td></td>
				</tr>
				<tr>
					<td>주소</td>
					<td class="user-orderView-addressLine">
						<div type="text" class="user-orderView-postcodeBox">
							<div class="user-orderView-addressLineTitle">우편번호</div>
							<div class="user-orderView-postcode"></div>
						</div>
						<div type="text" class="user-orderView-addressBox">
							<div class="user-orderView-addressLineTitle">주소</div>
							<div class="user-orderView-address"></div>
						</div>
						<div type="text" class="user-orderView-detailAddressBox">
							<div class="user-orderView-addressLineTitle">상세주소</div>
							<div class="user-orderView-detailAddress"></div>
						</div>
						<div type="text" class="user-orderView-extraAddressBox">
							<div class="user-orderView-addressLineTitle">참고항목</div>
							<div class="user-orderView-extraAddress"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td>받는 사람 전화번호</td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
</div>