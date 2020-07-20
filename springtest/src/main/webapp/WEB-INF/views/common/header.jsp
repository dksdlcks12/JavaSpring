<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	<div class="container">
	  	<a class="navbar-brand" href="#">Navbar</a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	  	<div class="collapse navbar-collapse" id="collapsibleNavbar">
	    	<ul class="navbar-nav">
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%=request.getContextPath()%>/">Main</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%=request.getContextPath()%>/board/list">Board</a>
		      	</li>
				<c:if test="${user==null}">
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%=request.getContextPath()%>/signin">Sign In</a>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%=request.getContextPath()%>/signup">Sign Up</a>
		      	</li>
		      	</c:if>
		      	<c:if test="${user!=null}">
		      	<li class="nav-item">
		      	<span style="line-height: 40px; color: white">'${user.id}'으로 로그인 중</span>
		      	</li>
		      	<li class="nav-item">
		        	<a class="nav-link" href="<%=request.getContextPath()%>/signout">Sign Out</a>
		      	</li>
		      	</c:if>
	    	</ul>
		</div> 
	</div> 
</nav>