<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardWrite-box">
	<div class="common-boardWrite-writeBox">
		<c:if test="${user.userId eq review.review_userId}">
			<table class="common-boardWrite-titleBox" border="1">
				<tr>
					<td class="common-boardWrite-rowTitle">제 목</td>
					<td class="common-boardWrite-rowContent"><input type="text" class="common-boardWrite-title" maxlength="" value="${review.reviewTitle}"></td>
				</tr>
			</table>
			<div class="common-boardWrite-Content">
				<div id="reviewModify"></div>
			</div>
			<div class="transImg" hidden></div>
			<textarea class="sandNote" hidden>${review.reviewContent}</textarea>
			<div class="writer" hidden>${review.review_userId}</div>
			<button class="common-boardWrite-button">등 록</button>
		</c:if>
	</div>
</div>
<script>
	var imgList = [];
	var viewNote = $('.sandNote').text();
	$('.sandNote').text("");
	$('#reviewModify').summernote({
		tabsize: 2,
		height: 300,
		minHeight: 300,
		maxHeight: 300,
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
	$('#reviewModify').summernote('code', viewNote);
	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data: form_data,
			type: "POST",
			url: '<%=request.getContextPath()%>/reviewimg',
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
	$('.common-boardWrite-button').click(function(){
		var index = 0;
		var arr = [];
		$('.transImg').html($('#reviewModify').summernote('code'));
		$('.transImg img').each(function(){
			if($(this).attr('src') == "/portfolio/resources/image/photo6.JPG"){
				$(this).attr('src', '<%=request.getContextPath()%>/resources/image/review'+imgList[index]);
				index++;
			}
		})
		$('.sandNote').html($('.transImg').html());
		console.log($('.sandNote').html());
		var reviewNum = ${review.reviewNum};
		var reviewTitle = $('.common-boardWrite-title').val();
		var reviewContent = $('.sandNote').html();
		var userId = $('.writer').text();
		if(reviewTitle != ""){
			if(reviewContent != '&lt;p&gt;&lt;br&gt;&lt;/p&gt;'){
				arr.push({"reviewNum":reviewNum, "reviewTitle":reviewTitle, "reviewContent":reviewContent, "review_userId":userId});
				$.ajax({
					async:false,
					type:'POST',
					data: JSON.stringify(arr),
					url:"<%=request.getContextPath()%>/setreviewmodify",
					dataType:"json",
					contentType:"application/json; charset=UTF-8",
					success : function(data){
						if(data.check){
							alert('공지사항이 성공적으로 삭제되었습니다.')
						}else{
							alert('잘못된 접근입니다.')
						}
						location.replace('<%=request.getContextPath()%>/reviewlookup?reviewNum=${review.reviewNum}&page=${page}&type=${type}&search=${search}');
					}
				});
			}else{
	    		alert('내용을 입력하여 주세요.')
	    	}
		}else{
	    	alert('제목을 입력하여 주세요.')
	   	}
	})
</script>