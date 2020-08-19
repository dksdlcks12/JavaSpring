package kr.ajs.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.service.UserService;
import kr.ajs.portfolio.vo.UserVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	UserService userService;

	
	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public ModelAndView openTilesView(ModelAndView mv, HttpServletRequest request) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null && user.getUserAuth().equals("admin")) {
			mv.setViewName("/main/adminMain");
		}else {
			mv.setViewName("/main/userMain");		
		}
	    return mv;
	}
	@RequestMapping(value= {"/signup"}, method = RequestMethod.GET)
	public ModelAndView signUpGet(ModelAndView mv) throws Exception{
	    mv.setViewName("/user/userSignUp");
	    return mv;
	}
	@RequestMapping(value= {"/signup"}, method = RequestMethod.POST)
	public ModelAndView signUpPost(ModelAndView mv, UserVo user) throws Exception{
		mv.setViewName("redirect:/");
		userService.signUp(user);
		return mv;
	}
	@RequestMapping("/signup/idcheck")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    UserVo user = userService.getUser(id);
	    boolean idCheck = user != null ? true : false;
	    map.put("idCheck", idCheck);
	    return map;
	}
	@RequestMapping(value= {"/login"}, method = RequestMethod.GET)
	public ModelAndView logInGet(ModelAndView mv, HttpServletRequest request) throws Exception{
	    mv.setViewName("/user/userLogIn");
	    return mv;
	}
	@RequestMapping(value= {"/login"}, method = RequestMethod.POST)
	public ModelAndView logInPost(ModelAndView mv, UserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception{
		mv.setViewName("/user/userLogIn");
		boolean isLogin = userService.isLogin(user);
		mv.addObject("isLogin", isLogin);
		if(isLogin) {
			mv.addObject("user", user);
		    mv.setViewName("redirect:/");
		}
	    return mv;
	}
	@RequestMapping("/logout")
	@ResponseBody
	public Map<Object, Object> logout(HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		request.getSession().removeAttribute("user");
	    return map;
	}
}
