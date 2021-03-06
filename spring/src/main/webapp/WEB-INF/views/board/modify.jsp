<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<br>
<form action="<%=request.getContextPath()%>/board/modify" method="POST" enctype="multipart/form-data">
	<div class="form-group">
	  <label>번호</label>
	  <input type="text" class="form-control" name="num" value="${board.num}" readonly>
	</div>
	<div class="form-group">
	  <label>제목</label>
	  <input type="text" class="form-control" name="title" value="${board.title}">
	</div>
	<div class="form-group">
		<label>작성자</label>
		<input type="hidden" class="form-control" name="writer" value="${board.writer}" readonly>
	</div>
	<input type="hidden" class="form-control" name="writer" value="${board.writer}" readonly>
	<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="5" name="content">${board.content}</textarea>
	</div>
	
	<div class="box-file">
		<c:if test="${board.file != null}">
			<span>${board.oriFile}</span>
			<button type="button" id="bt-file-close"><i class="fas fa-times"></i></button>
		</c:if>
		<input type="hidden" name="file" value="${board.file}">
	</div>
	<div>
		<input type="file" name="files">
	</div>
	<button>완료</button>
</form>
<script>
	$(function(){
		$('#bt-file-close').click(function(){
			$('.box-file').css('display','none')
			$('input[name=file]').val('')
		})
		$('input[name=files]').change(function(){
			if($('input[name=file]').val()!=''){
				$(this).val('')
				alert('첨부파일을 추가하려면 기존 첨부파일을 삭제해야 합니다.')
			}
		})
	})
</script>