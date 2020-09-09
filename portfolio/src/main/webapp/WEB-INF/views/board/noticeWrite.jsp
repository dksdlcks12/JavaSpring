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
			<div id="notice"></div>
				<script>
				$('#notice').summernote({
				tabsize: 2,
				height: 300,
				lang : 'ko-KR',
				toolbar: [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['color', ['color']],
				['insert', ['picture']],
				['view', ['fullscreen', 'codeview', 'help']]
				]
				});
				</script>
		</div>
		<button class="common-boardWrite-button">등 록</button>
	</div>
</div>