<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-asView-box">
	<div class="admin-asView-asViewBox">
		<h2>A/S조회</h2>
		<div class="admin-asView-asNumberBox">
			<div class="admin-asView-asTitleNumber">A/S번호</div>
			<div class="admin-asView-asNumber">${as.asNum}</div>
		</div>
		<div class="admin-asView-asGoodsTitleBox">
			<div class="admin-asView-asGoodsTitleType">제목</div>
			<div class="admin-asView-asGoodsTitleName">${as.asTitle}</div>
		</div>
		<div class="admin-asView-asGoodsBox">
			<div class="admin-asView-asGoodsType">이름</div>
			<div class="admin-asView-asGoodsName">${as.asName}</div>
		</div>
		<div class="admin-asView-asGoodsBox">
			<div class="admin-asView-asGoodsType">전화번호</div>
			<div class="admin-asView-asGoodsName">${as.asTel}</div>
		</div>
		<div class="admin-recallView-board">${as.asContent}</div>
		<div class="admin-asView-asStateBox">
			<div class="admin-asView-asTitleState">A/S상태</div>
			<select class="admin-asView-asState">
				<option <c:if test="${as.asState eq '미확인'}">selected</c:if>>미확인</option>
				<option <c:if test="${as.asState eq 'AS접수'}">selected</c:if>>AS접수</option>
				<option <c:if test="${as.asState eq 'AS대기'}">selected</c:if>>AS대기</option>
				<option <c:if test="${as.asState eq 'AS완료'}">selected</c:if>>AS완료</option>
				<option <c:if test="${as.asState eq '배송중'}">selected</c:if>>배송중</option>
				<option <c:if test="${as.asState eq '배송완료'}">selected</c:if>>배송완료</option>
				<option <c:if test="${as.asState eq 'AS불가'}">selected</c:if>>AS불가</option>
			</select>
		</div>
		<a href="<%=request.getContextPath()%>/asviewlist?page=${page}&type=${type}&search=${search}"><button class="admin-asView-goList">목록으로</button></a>
		<button class="admin-asView-asStateModifyButton">A/S상태 수정</button>
	</div>
</div>
<script>
	$('.admin-recallView-board').html($('.admin-recallView-board').text());
	$('.admin-asView-asStateModifyButton').click(function(){
		var asState = $('.admin-asView-asState').val();
		var asNum = Number($('.admin-asView-asNumber').text());
		var arr = [];
		arr.push({"asState":asState, "asNum":asNum});
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/admin/asstatemodify",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				alert('A/S상태를 수정하였습니다.')
				location.reload();
			}
		});
	})
</script>