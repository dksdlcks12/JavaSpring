<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardWrite-box">
	<div class="common-boardWrite-writeBox">
		<table class="common-boardWrite-titleBox" border="1">
			<tr>
				<td class="common-boardWrite-rowTitle">제 목</td>
				<td class="common-boardWrite-rowContent"><input type="text" class="common-boardWrite-title"></td>
			</tr>
		</table>
		<div class="common-boardWrite-Content">
			<div id="noticeModify"></div>
		</div>
		<div class="transImg" hidden></div>
		<textarea class="sandNote" hidden></textarea>
		<button class="common-boardWrite-button">수정완료</button>
	</div>
</div>
<script>
	$('#noticeModify').summernote({
		tabsize: 2,
		height: 300,
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
</script>