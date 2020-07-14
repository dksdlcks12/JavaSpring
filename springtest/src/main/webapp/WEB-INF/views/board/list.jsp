<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<br><h1>게시판</h1><br>
<table class="table table-bordered">
	<thead>
    	<tr>
    		<th>번호</th>
	    	<th>제목</th>
	    	<th>작성자</th>
    		<th>작성일</th>
    		<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${list.size() != 0}">
			<c:forEach var="board" items="${list}">
		  		<tr>
		  			<td>${board.num}</td>
		  			<td><a href="<%=request.getContextPath()%>/board/detail?num=${board.num}">${board.title}</a></td>
		  			<td>${board.writer}</td>
		  			<td>${board.registeredDate}</td>
		  			<td>${board.views}</td>
		  		</tr>
		  	</c:forEach>
		</c:if>
		<c:if test="${list.size() == 0}">
			<tr>
				<td colspan="5">등록된 게시글이 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>
<a href="<%=request.getContextPath()%>/board/register"><button>글쓰기</button></a>