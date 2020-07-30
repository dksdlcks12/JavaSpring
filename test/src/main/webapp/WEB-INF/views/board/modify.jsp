<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
	<c:if test="${board eq null}">
		<h1>존재하지 않는 게시물 입니다.</h1>
	</c:if>
	<c:if test="${board ne null}">
		<c:if test="${board.isdel eq 'Y'.charAt(0)}">
			<br><h1>해당 게시물은 삭제 되었습니다.</h1>
		</c:if>
		<c:if test="${board.isdel ne 'Y'.charAt(0)}">
			<form action="<%=request.getContextPath()%>/board/modify" method="POST" enctype="multipart/form-data">
				<div class="form-group">
				  <label>제목</label>
				  <input type="text" class="form-control" name="title" value="${board.title}">
				</div>
				<input type="hidden"name="writer" value="${board.writer}">
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="5" name="content">${board.content}</textarea>
				</div>
				<c:if test="${board.file!=null}">
					<div class="form-group file-box">
						<label>${board.oriFile}</label><button type="button" class="bt-file-del"><i class="fas fa-times"></i></button>
					</div>
				</c:if>
				<input type="hidden" name="file" value="${board.file}">
				<div class="form-group"><input type="file" name="files"></div>
				<input type="hidden" name="num" value="${board.num}">
				<input type="hidden" name="page" value="${cri.page}">
				<input type="hidden" name="search" value="${cri.search}">
				<input type="hidden" name="type" value="${cri.type}">
				<button>완료</button>
			</form>
		</c:if>
	</c:if>
<script>
	$(function(){
		$('.bt-file-del').click(function(){
			$('.file-box').css('display', 'none')
			$('input[name=file]').val('')
		})
		$('input[name=files]').change(function(){
			if($('input[name=file]').val()!=''){
				$(this).val('')
				alert('첨부파일을 추가하려면 기존 첨부파일을 삭제해야 합니다.')
			}
		})
		$('form').submit(function(){
			return validates();
		})
		function validates(){
			if($('input[name=title]').val()=='' || $('textarea[name=content]').val()==''){
				alert('제목과 내용을 입력하여 주십시오.')
				return false;
			}
		}
	})
</script>