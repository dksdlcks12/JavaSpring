package kr.ajs.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ajs.portfolio.service.UserService;

public class LateViewInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	UserService userService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	    userService.getlateview(modelAndView, request);
	}
}
