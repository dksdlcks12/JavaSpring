<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardWrite-box">
	<div class="common-boardWrite-writeBox">
		<table class="common-boardWrite-titleBox" border="1">
			<tr>
				<td class="common-boardWrite-rowTitle">제 목</td>
				<td class="common-boardWrite-rowContent"><input type="text" class="common-boardWrite-title" value="${notice.noticeTitle}")></td>
			</tr>
		</table>
		<div class="common-boardWrite-Content">
			<div id="noticeModify"></div>
		</div>
		<div class="transImg" hidden></div>
		<textarea class="sandNote" hidden>${notice.noticeContent}</textarea>
		<button class="common-boardWrite-button">수정완료</button>
	</div>
</div>
<script>
	var imgList = [];
	var viewNote = $('.sandNote').text();
	$('.sandNote').text("");
	$('#noticeModify').summernote({
		tabsize: 2,
		height: 300,
		minHeight: 300,
		maxHeight: 300,
		lang : 'ko-KR',
		toolbar: [
			['style', ['style']],
			['font', ['bold', 'underline', 'clear']],
			['color', ['color']],
			['insert', ['picture']],
			['view', ['fullscreen', 'codeview', 'help']]
		],
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
	            for (var i = files.length - 1; i >= 0; i--) {
	            	sendFile(files[i], this);
	            }
	        }
		}
	});
	$('#noticeModify').summernote('code', viewNote);
	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data: form_data,
			type: "POST",
			url: '<%=request.getContextPath()%>/admin/noticeimg',
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
    	$('.transImg').html($('#noticeModify').summernote('code'));
    	$('.transImg img').each(function(){
        	if($(this).attr('src') == "/portfolio/resources/image/photo6.JPG"){
        		$(this).attr('src', '<%=request.getContextPath()%>/resources/image/notice'+imgList[index]);
        		index++;
        	}
    	})
		$('.sandNote').html($('.transImg').html());
		var noticeNum = ${notice.noticeNum};
    	var noticeTitle = $('.common-boardWrite-title').val();
		var noticeContent = $('.sandNote').html();
    	if(noticeTitle != ""){
    		if(noticeContent != '&lt;p&gt;&lt;br&gt;&lt;/p&gt;'){
    			arr.push({"noticeNum":noticeNum, "noticeTitle":noticeTitle, "noticeContent":noticeContent})
    			$.ajax({
					async:false,
					type:'POST',
					data: JSON.stringify(arr),
					url:"<%=request.getContextPath()%>/admin/noticemodify",
					dataType:"json",
					contentType:"application/json; charset=UTF-8",
					success : function(data){
						alert('공지사항이 성공적으로 수정되었습니다.')
						location.replace('<%=request.getContextPath()%>/noticeview?noticeNum=${notice.noticeNum}&page=${page}&type=${type}&search=${search}');
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