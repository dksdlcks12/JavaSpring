<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardList-box">
	<div class="common-boardList-boardListBox">	
		<h3 class="common-boardList-title">게시판 타입</h3>
		<c:if test="${list.size()!=0}">
			<table class="common-boardList-table" border="1">
				<thead>
					<tr>
						<th class="common-boardList-boardNum">No.</th>
						<th class="common-boardList-boardTitle">제목</th>
						<th class="common-boardList-boardWriter">작성자</th>
						<th class="common-boardList-boardDate">게시일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="qa" items="${list}">
						<tr>
							<td>${qa.qaNum}</td>
							<c:if test="${qa.qaIsOpen eq 'Y'}">
								<td><a href="#"><div class="common-boardList-titleLine">${qa.qaTitle}</div></a></td>
							</c:if>
							<c:if test="${qa.qaIsOpen eq 'N'}">
								<td><a href="#"><div class="common-boardList-titleLine"><i class="fas fa-lock"></i> ${qa.qaTitle}</div></a></td>
							</c:if>
							<td>${qa.qaWriter}</td>
							<td>${qa.qaDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<div class="common-boardList-etcBox">
			<form action="">
				<div class="common-boardList-searchAndButtonBox">
					<select name="type" class="common-boardList-searchType">
						<option value="0" selected>전체</option>
						<option value="1">제목</option>
						<option value="2">작성자</option>
						<option value="3">작성일</option>
					</select>
					<input type="text" class="common-boardList-searchContent" name="search">
					<button class="common-boardList-searchButton"><i class="fas fa-search"></i></button>
				</div>
			</form>
			<c:if test="${user.userAuth ne 'admin'}">
			<a href="<%=request.getContextPath()%>/qawrite"><button class="common-board-write">글쓰기</button></a>
			</c:if>
		</div>
		<c:if test="${list.size()!=0}">
			<c:if test="${pm.endPage!=1}">
				<ul class="common-goodsList-pagination pagination justify-content-center">
					<c:if test="${pm.criteria.page!=1}">
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/qalist?page=${pm.criteria.page-1}&type=${pm.criteria.type}&search=${pm.criteria.search}"><i class="fas fa-angle-left"></i></a></li>
					</c:if>
				    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
				        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
				            <a class="page-link" href="<%=request.getContextPath()%>/qalist?page=${index}&type=${pm.criteria.type}&search=${pm.criteria.search}">${index}</a>
				        </li>
				    </c:forEach>
				    <c:if test="${pm.criteria.page!=pm.endPage}">
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/qalist?page=${pm.criteria.page+1}&type=${pm.criteria.type}&search=${pm.criteria.search}"><i class="fas fa-angle-right"></i></a></li>
					</c:if>
				</ul>
			</c:if>
			<c:if test="${pm.criteria.page>pm.endPage}">
				<script>
					location.replace('<%=request.getContextPath()%>/qalist?&page=${pm.endPage}&type=${pm.criteria.type}&search=${pm.criteria.search}');
				</script>
			</c:if>
		</c:if>
	</div>
	<c:if test="${list.size()==0 && pm.criteria.page>1}">
		<script>
			location.replace('<%=request.getContextPath()%>/qalist?&page=${pm.endPage}&type=${pm.criteria.type}&search=${pm.criteria.search}');
		</script>
	</c:if>
</div>