<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardWrite-box">
	<div class="common-boardWrite-writeBox">
		<table class="common-boardWrite-titleBox" border="1">
			<tr>
				<td class="common-boardWrite-rowTitle">제 목</td>
				<td class="common-boardWrite-rowContent"><input type="text" class="common-boardWrite-title" maxlength="30" value="답변 : ${qa.qaTitle}" readonly></td>
			</tr>
		</table>
		<div class="common-boardWrite-Content">
			<div id="qaAnswer"></div>
		</div>
		<div class="transImg" hidden></div>
		<textarea class="sandNote" hidden></textarea>
		<button class="common-boardWrite-button">등 록</button>
	</div>
</div>
<script>
	var imgList = [];
	$('#qaAnswer').summernote({
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
	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data: form_data,
			type: "POST",
			url: '<%=request.getContextPath()%>/admin/qaanswerimg',
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
    	$('.transImg').html($('#qaAnswer').summernote('code'));
    	$('.transImg img').each(function(){
    		$(this).attr('src', '<%=request.getContextPath()%>/resources/image/qaanswer'+imgList[index]);
    		index++;
    	})
    	$('.sandNote').html($('.transImg').html());
    	var qaOriginNum = ${qa.qaOriginNum};
    	var qaTitle = $('.common-boardWrite-title').val();
		var qaContent = $('.sandNote').html();
    	if(qaTitle != ""){
    		if(qaContent != '&lt;p&gt;&lt;br&gt;&lt;/p&gt;'){
    			arr.push({"qaOriginNum":qaOriginNum, "qaTitle":qaTitle, "qaContent":qaContent});
    			$.ajax({
					async:false,
					type:'POST',
					data: JSON.stringify(arr),
					url:"<%=request.getContextPath()%>/admin/qaansweradd",
					dataType:"json",
					contentType:"application/json; charset=UTF-8",
					success : function(data){
						alert('답변이 성공적으로 등록되었습니다.')
						location.replace('<%=request.getContextPath()%>/qalist');
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