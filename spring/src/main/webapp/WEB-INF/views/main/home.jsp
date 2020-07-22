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
<input type="text" id="id" placeholder="아이디"><br>
<input type="text" id="pw" placeholder="비밀번호"><br>
<script>
	$(function(){
		$('#ajax').click(function(){
			var id = 'qwe123'
		    $.ajax({
			    //동기화, 비동기화 결정
		        async:true,
		        //전송방식(GET, POST)
		        type:'POST',
		        data:id,
		        url:"test",
		        dataType:"json",
		        contentType:"application/json; charset=UTF-8",
		        success : function(data){
		            console.log(data);
		            $('#id').val(data['id'])
		            $('#pw').val(data['pw'])
		        }
		    });
		})
	})
</script>