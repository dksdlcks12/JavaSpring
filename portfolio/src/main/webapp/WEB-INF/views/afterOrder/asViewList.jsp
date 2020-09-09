<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-asList-box">
	<div class="admin-asList-orderListBox">
		<c:if test="${list.size()!=0}">
			<h2>A/S목록</h2>
			<div class="admin-asList-orderTitleBox">
				<div class="admin-asList-orderTitleNumber">A/S번호</div>
				<div class="admin-asList-orderTitleInfo">A/S내용</div>
				<div class="admin-asList-orderTitleDate">A/S 신청날짜</div>
				<div class="admin-asList-orderTitleState">A/S상태</div>
			</div>
			<c:forEach var="as" items="${list}">
				<div class="admin-asList-orderBox">
					<div class="admin-asList-orderNumber">${as.asNum}</div>
					<c:if test="${user.userAuth ne 'admin'}">
						<a href="<%=request.getContextPath()%>/asview?asNum=${as.asNum}&page=${pm.criteria.page}"><div class="admin-asList-orderInfo">${as.asTitle}</div></a>
					</c:if>
					<c:if test="${user.userAuth eq 'admin'}">
						<a href="<%=request.getContextPath()%>/admin/asview?asNum=${as.asNum}&page=${pm.criteria.page}&type=${pm.criteria.type}&search=${pm.criteria.search}"><div class="admin-asList-orderInfo">${as.asTitle}</div></a>
					</c:if>
					<div class="admin-asList-orderDate">${as.asDate}</div>
					<div class="admin-asList-orderState">${as.asState}</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${user.userAuth eq 'admin'}">
			<form action="<%=request.getContextPath()%>/asviewlist">
				<div class="common-boardList-searchBox">
					<select name="type" class="common-boardList-searchType">
						<option value="0" selected>전체</option>
						<option value="1">A/S 번호</option>
						<option value="2">A/S 신청날짜</option>
						<option value="3">A/S 상태</option>
						<option value="4">신청 아이디</option>
					</select>
					<input type="text" class="common-boardList-searchContent" name="search">
					<button class="common-boardList-searchButton"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</c:if>
		<c:if test="${list.size()!=0}">
			<c:if test="${pm.endPage!=1}">
				<ul class="common-goodsList-pagination pagination justify-content-center">
					<c:if test="${pm.criteria.page!=1}">
					<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/asviewlist?page=${pm.criteria.page-1}<c:if test="${user.userAuth eq 'admin'}">&type=${pm.criteria.type}&search=${pm.criteria.search}</c:if>"><i class="fas fa-angle-left"></i></a></li>
					</c:if>
				    <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="index">
				        <li class="page-item <c:if test="${index==pm.criteria.page}">active</c:if>">
				            <a class="page-link" href="<%=request.getContextPath()%>/asviewlist?page=${index}<c:if test="${user.userAuth eq 'admin'}">&type=${pm.criteria.type}&search=${pm.criteria.search}</c:if>">${index}</a>
				        </li>
				    </c:forEach>
				    <c:if test="${pm.criteria.page!=pm.endPage}">
						<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/asviewlist?page=${pm.criteria.page+1}<c:if test="${user.userAuth eq 'admin'}">&type=${pm.criteria.type}&search=${pm.criteria.search}</c:if>"><i class="fas fa-angle-right"></i></a></li>
					</c:if>
				</ul>
			</c:if>
			<c:if test="${pm.criteria.page>pm.endPage && user.userAuth ne 'admin'}">
				<script>
					location.replace('<%=request.getContextPath()%>/asviewlistt?page=${pm.endPage}');
				</script>
			</c:if>
			<c:if test="${pm.criteria.page>pm.endPage && user.userAuth eq 'admin'}">
				<script>
					location.replace('<%=request.getContextPath()%>/asviewlist?page=${pm.endPage}&type=${pm.criteria.type}&search=${pm.criteria.search}');
				</script>
			</c:if>
		</c:if>
	</div>
	<c:if test="${list.size()==0 && pm.criteria.page>1 && user.userAuth ne 'admin'}">
		<script>
			location.replace('<%=request.getContextPath()%>/asviewlist?page=${pm.endPage}');
		</script>
	</c:if>
		<c:if test="${list.size()==0 && pm.criteria.page>1 && user.userAuth eq 'admin'}">
		<script>
			location.replace('<%=request.getContextPath()%>/asviewlist?page=${pm.endPage}&type=${pm.criteria.type}&search=${pm.criteria.search}');
		</script>
	</c:if>
</div>