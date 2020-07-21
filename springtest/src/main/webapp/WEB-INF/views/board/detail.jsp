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
		    <input type="text" class="form-control" name="num" value="${board.title}" readonly>
		</div>
		<div class="form-group">
			<label>작성자</label>
		    <input type="text" class="form-control" name="num" value="${board.writer}" readonly>
		</div>
		<div class="form-group">
			<label>작성일</label>
		    <input type="text" class="form-control" name="num" value="${board.registeredDate}" readonly>
		</div>
		<div class="form-group">
			<label>조회수</label>
		    <input type="text" class="form-control" name="num" value="${board.views}" readonly>
		</div>
		<div class="form-group">
			<label>내용</label>
			<textarea class="form-control" rows="5" name="content" readonly>${board.content}</textarea>
		</div>
		<a href="<%=request.getContextPath()%>/board/list?page=${cri.page}&type=${cri.type}&search=${cri.search}"><button>목록</button></a>
		<c:if test="${user.id == board.writer}">
			<a href="<%=request.getContextPath()%>/board/modify?num=${board.num}"><button>수정</button></a>
			<a href="<%=request.getContextPath()%>/board/del?num=${board.num}"><button>삭제</button></a>
		</c:if>
	</c:if>

</c:if>
