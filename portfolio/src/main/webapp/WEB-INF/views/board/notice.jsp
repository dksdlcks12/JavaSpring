<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardList-box">
	<div class="common-boardList-boardListBox">
		<h3 class="common-boardList-title">공 지 사 항</h3>
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
				<tr>
					<td>3</td>
					<td><a href="#"><div class="common-boardList-titleLine">가나다라마바사아자차카타파하가나다라마바사아자차</div></a></td>
					<td>ABCDEFABCDEABCDE</td>
					<td>2020/08/06 15:15:15</td>
				</tr>
				<tr>
					<td>2</td>
					<td><a href="#"><div class="common-boardList-titleLine"></div></a></td>
					<td>mary</td>
					<td>2020/08/05</td>
				</tr>
				<tr>
					<td>1</td>
					<td><a href="#"><div class="common-boardList-titleLine">Dooley</div></a></td>
					<td>july</td>
					<td>2020/08/04</td>
				</tr>
			</tbody>
		</table>
		<div class="common-boardList-etcBox">
			<form action="#">
				<div class="common-boardList-searchAndButton">
					<select name="" id="" class="common-boardList-searchType">
						<option value="0" selected>전체</option>
						<option value="1">제목</option>
						<option value="2">작성자</option>
					</select>
					<input type="text" class="common-boardList-searchContent">
					<button class="common-boardList-searchButton"><i class="fas fa-search"></i></button>
				</div>
			</form>
			<c:if test="${user.userAuth eq 'admin'}">
				<a href="<%=request.getContextPath()%>/noticewrite"><button class="common-board-write">글쓰기</button></a>
			</c:if>
		</div>
		<ul class="common-goodsList-pagination pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-right"></i></a></li>
		</ul>
	</div>
</div>