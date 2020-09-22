<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="common-boardList-box">
	<div class="common-boardList-boardListBox">	
		<h3 class="common-boardList-title">Q & A</h3>
		<c:if test="${list.size()!=0}">
			<table class="common-boardList-table" border="1">
				<thead>
					<tr>
						<th class="common-boardList-boardNum">No.</th>
						<th class="common-boardList-boardTitle">제목</th>
						<th class="common-boardList-boardWriter">작성자</th>
						<th class="common-boardList-boardDate">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="qa" items="${list}">
						<tr>
							<td class="common-boardList-num"><c:if test="${qa.qaNum == qa.qaOriginNum}">${qa.qaNum}</c:if></td>
							<c:if test="${user.userAuth ne 'admin'}">
								<c:if test="${qa.qaIsOpen eq 'Y'}">
									<td><a href="<%=request.getContextPath()%>/qaview?qaNum=${qa.qaNum}&page=${pm.criteria.page}&type=${pm.criteria.type}&search=${pm.criteria.search}"><div class="common-boardList-titleLine"><c:if test="${qa.qaNum != qa.qaOriginNum}">ㄴ </c:if>${qa.qaTitle}</div></a></td>
								</c:if>
								<c:if test="${qa.qaIsOpen eq 'N'}">
									<td><div class="common-boardList-titleLine common-boardList-pauseOn"><c:if test="${qa.qaNum != qa.qaOriginNum}">ㄴ </c:if><i class="fas fa-lock"></i> ${qa.qaTitle}</div></a></td>
								</c:if>
							</c:if>
							<c:if test="${user.userAuth eq 'admin'}">
								<td><a href="<%=request.getContextPath()%>/qaview?qaNum=${qa.qaNum}&page=${pm.criteria.page}&type=${pm.criteria.type}&search=${pm.criteria.search}"><div class="common-boardList-titleLine"><c:if test="${qa.qaNum != qa.qaOriginNum}">ㄴ </c:if><c:if test="${qa.qaIsOpen eq 'N'}"><i class="fas fa-lock"></i> </c:if>${qa.qaTitle}</div></a></td>
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
						<option value="0" <c:if test="${pm.criteria.type==0}">selected</c:if>>전체</option>
						<option value="1" <c:if test="${pm.criteria.type==1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${pm.criteria.type==2}">selected</c:if>>작성자</option>
						<option value="3" <c:if test="${pm.criteria.type==3}">selected</c:if>>작성일</option>
					</select>
					<input type="text" class="common-boardList-searchContent" name="search" value="${pm.criteria.search}">
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
<div class="common-board-pause" style="display:none">
	<div class="common-board-pauseBox">
		<div class="common-board-pauseTitle">비밀번호를 입력하여 주세요.<button class="common-board-pauseColse"><i class="fas fa-times common-board-pauseColseImg"></i></button></div>
		<div><input type="text" class="common-board-pausePassWord noneMemberPassword" maxlength="8"></div>
		<div><button class=" common-board-pauseCheckButton">확인</button></div>
	</div>
</div>
<script>
	var qaNum;
	$('.common-boardList-pauseOn').click(function(){
		qaNum = $(this).parent().siblings('.common-boardList-num').text();
		$('.common-board-pause').css({"top":(($(window).height()-$('.common-board-pause').outerHeight())/2+$(window).scrollTop())+"px", "left":(($(window).width()-$('.common-board-pause').outerWidth())/2+$(window).scrollLeft())+"px", "display":"block"});
		$('.common-board-pause').on('scroll touchmove mousewheel', function(event) {
			event.preventDefault();
			event.stopPropagation();
			return false;
		});
	})
	$('.common-board-pauseColseImg').click(function(){
		$('.common-board-pause').off('scroll touchmove mousewheel');
		$('.common-board-pause').css("display","none");
	})
	$('.common-board-pauseCheckButton').click(function(){
		var arr = [];
		var qaPw = $('.common-board-pausePassWord').val();
		arr.push({"qaNum":qaNum, "qaPw":qaPw});
		$.ajax({
			async:false,
			type:'POST',
			data: JSON.stringify(arr),
			url:"<%=request.getContextPath()%>/qapwcheck",
			dataType:"json",
			contentType:"application/json; charset=UTF-8",
			success : function(data){
				if(data.qaPwCheck){	
					location.href='<%=request.getContextPath()%>/qaview?qaNum='+qaNum+'&page=${pm.criteria.page}&type=${pm.criteria.type}&search=${pm.criteria.search}' 
				}else{
					alert('잘못된 비밀번호 입니다..')
					location.replace('<%=request.getContextPath()%>/qalist');
				}
			}
		});
	})
</script>