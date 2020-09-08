<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="user_recallView-box">
	<div class="user_recallView-recallApplyBox">
		<h2>반품조회</h2>
		<div class="user_recallView-recallApplyInfoBox">
			<div class="user_recallView-orderNumberBox">
				<div>반품번호</div><div>${recall.recallNum}</div><div>주문번호</div><div>${recall.orderNum}</div>
			</div>
			<div style="overflow-x: hidden;width:980px;height: 203px;">
				<table class="user_recallView-goodsBox" border="1">
					<tr>
						<th class="user_recallView-goodsImgTitle">이미지</th>
						<th class="user_recallView-goodsInfoTitle">제품 정보</th>
						<th class="user_recallView-goodsPriceTitle">신청 일시</th>
					</tr>
					<c:forEach var="goods" items="${goodsList}">
						<tr>
							<td class="user_recallView-goodsImg"><img src="<%=request.getContextPath()%>/resources/image/goodsImg/${goods.goodsImg}" alt=""></td>
							<td class="user_recallView-goodsInfo">[${goods.goodsName} / ${goods.optionColor}]</td>
							<td class="user_recallView-goodsPrice">${recall.recallDate}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="user_recallView-recallOptionBox">
				<div class="user_recallView-selectGoodsBox">
					<div class="user_recallView-selectGoodsTitle">교환여부</div><div class="user_recallView-selectGoods">${recall.recallIsChange}</div>
				</div>
				<div class="user_recallView-recallReasonTitle">반품사유</div>
				<div class="user_recallView-recallReason">${recall.recallReason}</div>
				<div class="user_recallView-recallStateBox">
					<div>반품상태</div><div>${recall.recallState}</div>
				</div>
				<div class="user_recallView-board">${recall.recallContent}</div>
				<script>
					$('.user_recallView-board').html($('.user_recallView-board').text())
				</script>
			</div>
		</div>
	</div>
</div>