<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<br>
<c:if test="${board eq null}">
	<h1>해당 게시물은 없는 게시물입니다.</h1>
</c:if>

<c:if test="${board ne null}">
	<c:if test="${board.isdel eq 'Y'.charAt(0)}">
		<h1>해당 게시물은 삭제 되었습니다.</h1>
	</c:if>
	<c:if test="${board.isdel ne 'Y'.charAt(0)}">
		<form action = "<%=request.getContextPath()%>/board/modify" method="POST" enctype="multipart/form-data">
			<input type="hidden" class="form-control" name="num" value="${board.num}">
			<div class="form-group">
				<label>제목</label>
			    <input type="text" class="form-control" name="title" value="${board.title}">
			</div>
				<input type="hidden" class="form-control" name="writer" value="${board.writer}">
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows="5" name="content">${board.content}</textarea>
			</div>
			<div class="mod-file-box">
				<c:if test="${board.file != null}">
					<label>${board.oriFile}</label>
					<button type="button" id="bt-file-del"><i class="fas fa-times"></i></button>
				</c:if>
					<input type="hidden" name="file" value="${board.file}">
			</div>
			<div>
				<input type="file" name="files">
			</div>
			<button>완료</button>
		</form>
	</c:if>
</c:if>
<script>
	$(function(){
		$('#bt-file-del').click(function(){
			$('.mod-file-box').css('display', 'none')
			$('input[name=file]').val('')
		})
		$('input[name=files]').change(function(){
			if($('input[name=file]').val()!='' || $('input[name=file]').val()!=null){
				$(this).val('')
				alert('첨부된 파일이 존재 합니다.')
			}
		})
	})
</script>