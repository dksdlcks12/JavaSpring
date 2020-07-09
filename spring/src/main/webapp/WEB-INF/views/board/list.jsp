<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<br>
<h2>게시판</h2>
<p></p>            
<table class="table table-striped">
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
	<c:forEach var="board" items="${list}">
	    <tr>
	      <td>${board.num}</td>
	      <td><a href="<%=request.getContextPath()%>/board/detail?num=${board.num}">${board.title}</a></td>
	      <td>${board.writer}</td>
	      <td>${board.registeredDate}</td>
	      <td>${board.views}</td>
	    </tr>
    </c:forEach>
  </tbody>
</table>
<a href="<%=request.getContextPath()%>/board/register"><button>글쓰기</button></a>