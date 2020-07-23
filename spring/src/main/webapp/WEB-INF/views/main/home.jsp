<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${user==null}">
	<div>
		<form action="<%=request.getContextPath()%>/" method="post">
			<input type="text" name="id" placeholder="아이디"><br>
			<input type="password" name="pw" placeholder="비밀번호"><br>
			<button>로그인</button>
		</form>
	</div>
</c:if>
<button id="ajax">ajax</button><br>
<script>
	$(function(){
		$('#ajax').click(function(){
			var id = 'qwe123'
		    $.ajax({
			    //동기화, 비동기화 결정
		        async:true,
		        //전송방식(GET, POST)
		        type:'POST',
		        data:JSON.stringify({"id":"asd", "pw":"123"}),
		        url:"<%=request.getContextPath()%>/test",
		        dataType:"json",
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){
		            console.log(data['res']);
		        }
		    });
		})
	})
</script>