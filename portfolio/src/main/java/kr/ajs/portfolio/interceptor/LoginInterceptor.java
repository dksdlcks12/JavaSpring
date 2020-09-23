package kr.ajs.portfolio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ajs.portfolio.dao.UserDao;
import kr.ajs.portfolio.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UserDao userDao;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	    ModelMap modelMap = modelAndView.getModelMap();
	    UserVo user = (UserVo)modelMap.get("user");
	    if(user != null) {
	    	HttpSession session = request.getSession();
	    	user = userDao.getUser(user.getUserId());
	        session.setAttribute("user", user);
	    }
	}
}
