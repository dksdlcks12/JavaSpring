<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${board eq null}">
	<br><h1>해당 게시물은 없는 게시물입니다.</h1>
</c:if>
<c:if test="${board ne null}">
	<c:if test="${board.isdel eq 'Y'.charAt(0)}">
		<br><h1>해당 게시물은 삭제 되었습니다.</h1>
	</c:if>
	<c:if test="${board.isdel ne 'Y'.charAt(0)}">
		<br><div class="form-group">
			<label>번호</label>
		    <input type="text" class="form-control" name="num" value="${board.num}" readonly>
		</div>
		<div class="form-group">
			<label>제목</label>
		    <input type="text" class="form-control" name="title" value="${board.title}" readonly>
		</div>
		<div class="form-group">
			<label>작성자</label>
		    <input type="text" class="form-control" name="writer" value="${board.writer}" readonly>
		</div>
		<div class="form-group">
			<label>작성일</label>
		    <input type="text" class="form-control" name="registeredDate" value="${board.registeredDate}" readonly>
		</div>
		<div class="form-group">
			<label>조회수</label>
		    <input type="text" class="form-control" name="views" value="${board.views}" readonly>
		</div>
		<div class="form-group">
			<label>추천수</label>
		    <input type="text" class="form-control" name="like" value="${board.like}" readonly>
		    <button type="button" class="btn btn-outline-success col-12" id="like">추천</button>
		</div>
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="5" name="content" readonly>${board.content}</textarea>
		</div>
		<c:if test="${board.file != null}">
			<div>
				<a href="<%=request.getContextPath()%>/board/download?fileName=${board.file}">${board.oriFile}</a>
			</div>
		</c:if>
		<a href="<%=request.getContextPath()%>/board/list?page=${cri.page}&type=${cri.type}&search=${cri.search}"><button>목록</button></a>
		<c:if test="${user.id == board.writer}">
			<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}"><button>수정</button></a>
			<a href="<%=request.getContextPath()%>/board/del?num=${board.num}"><button>삭제</button></a>
		</c:if>
	</c:if>
</c:if>
<script>
	$(function(){
		$('#like').click(function(){
			var num = $('input[name=num]').val();
			$.ajax({
				//동기화, 비동기화 결정
			    async:true,
			    //전송방식(GET, POST)
			    type:'POST',
			    data:num,
			    url:"<%=request.getContextPath()%>/like",
			    dataType:"json",
			    contentType:"application/json; charset=UTF-8",
			    success : function(data){
				    if(!data['isUser']){
				    	alert('로그인 한 회원만 추천 가능합니다.')
					}else{
						if(data['like']<0){
							alert('추천은 한번만 할 수 있습니다.')
						}else{
							$('input[name=like]').val(data['like'])
						}
					}
			    }
			});
		})
	})
</script>