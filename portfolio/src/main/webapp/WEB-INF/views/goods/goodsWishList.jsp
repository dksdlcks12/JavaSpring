<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user-cart-box">
	<div class="user-cart-cartBox">
		<table class="user-cart-goodsBox" border="1">
			<tr>
				<th><input type="checkbox" class="user-cart-goodsCheckAll"></th>
				<th class="user-cart-goodsImgTitle">이미지</th>
				<th class="user-cart-goodsInfoTitle">제품 정보</th>
				<th class="user-cart-goodsPriceTitle">판매가</th>
				<th class="user-cart-goodsCountTitle">수량</th>
			</tr>
			<tr>
				<td><input type="checkbox" class="user-cart-goodsCheck"></td>
				<td class="user-cart-goodsImg"><img src="상품대용.gif" alt="" ></td>
				<td class="user-cart-goodsInfo"></td>
				<td class="user-cart-goodsPrice"></td>
				<td class="user-cart-goodsCount"><input type="number"></td>
			</tr>
			<tr>
				<td><input type="checkbox" class="user-cart-goodsCheck"></td>
				<td class="user-cart-goodsImg"><img src="상품대용.gif" alt="" ></td>
				<td class="user-cart-goodsInfo"></td>
				<td class="user-cart-goodsPrice"></td>
				<td class="user-cart-goodsCount"><input type="number"></td>
			</tr>
			<tr>
				<td><input type="checkbox" class="user-cart-goodsCheck"></td>
				<td class="user-cart-goodsImg"><img src="상품대용.gif" alt="" ></td>
				<td class="user-cart-goodsInfo"></td>
				<td class="user-cart-goodsPrice"></td>
				<td class="user-cart-goodsCount"><input type="number"></td>
			</tr>
		</table>
		<button class="user-cart-goodsDelete">선택 삭제</button>
	</div>
</div>