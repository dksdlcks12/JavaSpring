package kr.ajs.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ajs.portfolio.service.UserService;

public class LateViewInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	UserService userService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null) {
			HttpSession session = request.getSession();
			userService.getlateview(modelAndView, request);
			String path = (String)request.getContextPath();
			String url = path + (String)request.getServletPath();
			if(url.indexOf("login")==-1 && url.indexOf("signup")==-1){
				session.setAttribute("referer2", url);
			}
		}
	}
}
