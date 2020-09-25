package kr.ajs.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ajs.portfolio.vo.UserVo;

public class MemberInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		if(user == null) {
			String path = (String)request.getContextPath();
	    	String url = path + (String)request.getServletPath();
	        session.setAttribute("referer", url);
			response.sendRedirect(request.getContextPath()+"/login");
		}
		return true;
	}
}
