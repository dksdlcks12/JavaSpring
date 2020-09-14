<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<div class="common-boardView-box">
		<div class="common-boardView-ViewBox">
			<table class="common-boardView-titleBox" border="1">
				<tr>
					<td class="common-boardView-rowTitle">제 목</td>
					<td class="common-boardView-rowContent" colspan="3">${review.reviewTitle}</td>
				</tr>
				<!-- 리뷰에서만 작성자와 비밀번호 사용 -->
				<tr>
					<td class="common-boardView-menu">작 성 자</td>
					<td class="common-boardView-menu writer">${review.review_userId}</td>
					<td class="common-boardView-menu">게 시 일</td>
					<td class="common-boardView-menu">${review.reviewDate}</td>
				</tr>
			</table>
			<div class="common-boardView-content">${review.reviewContent}</div>
			<a href="<%=request.getContextPath()%>/reviewlist?page=${page}&type=${type}&search=${search}"><button class="common-boardView-button common-boardView-goList">목 록</button></a>
			<c:if test="${user.userId eq review.review_userId}">
					<a href="<%=request.getContextPath()%>/reviewmodify?reviewNum=${review.reviewNum}&page=${page}&type=${type}&search=${search}" class="common-boardView-goModify"><button class="common-boardView-button">수 정</button></a>
					<button class="common-boardView-button common-boardView-goDelete">삭 제</button>
			</c:if>
			<c:if test="${user.userAuth eq 'admin'}">
				<button class="common-boardView-button common-boardView-adminDelete">삭 제</button>
			</c:if>
		</div>
	</div>
</body>
<script>
	$('.common-boardView-content').html($('.common-boardView-content').text());
	$('.common-boardView-goDelete, .common-boardView-adminDelete').click(function(){
		var arr = [];
		var reviewNum = ${review.reviewNum};
		var userId = $('.writer').text();
		arr.push({"reviewNum":reviewNum, "review_userId":userId});
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/reviewdel",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				if(data.check){
					alert('공지사항이 성공적으로 수정되었습니다.')
				}else{
					alert('잘못된 접근입니다.')
				}
				location.replace('<%=request.getContextPath()%>/reviewlist?page=${page}&type=${type}&search=${search}');
			}
		});
	})
</script>