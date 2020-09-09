package kr.ajs.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ajs.portfolio.vo.UserVo;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/login");
		}else if(!(user.getUserAuth().equals("admin"))) {
			response.sendRedirect(request.getContextPath()+"/");
		}
		return true;
	}
}
