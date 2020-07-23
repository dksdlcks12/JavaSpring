package kr.green.springtest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.service.UserService;
import kr.green.springtest.vo.UserVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userservice;
	@RequestMapping(value= {"/", "/signin"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView home(ModelAndView mv, UserVo inputUser){
	    mv.setViewName("/main/home");
	    UserVo user = userservice.isUesr(inputUser);
	    mv.addObject("id",inputUser.getId());
	    mv.addObject("isLogin", "isLogin");
    	mv.addObject("user", user);
	    if(user == null) {
	    	mv.addObject("isLogin", false);
	    }
	    return mv;
	}
	@RequestMapping(value= {"/signup"}, method = RequestMethod.GET)
	public ModelAndView signUpGet(ModelAndView mv){
	    mv.setViewName("/main/signup");
	    return mv;
	}
	@RequestMapping(value= {"/signup"}, method = RequestMethod.POST)
	public ModelAndView signUpPet(ModelAndView mv, UserVo user){
		if(userservice.signUp(user)) {
			mv.setViewName("redirect:/");
		}else {
			mv.setViewName("redirect:/signup");
		}
	    return mv;
	}
	@RequestMapping(value= {"/signout"}, method = RequestMethod.GET)
	public ModelAndView signUpOut(ModelAndView mv, HttpServletRequest request){
	    mv.setViewName("redirect:/");
	    request.getSession().removeAttribute("user");
	    return mv;
	}
	@RequestMapping(value ="/idcheck")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    UserVo user = userservice.getUser(id);
	    boolean dup = user == null ? false : true;
	    map.put("dup", dup);
	    return map;
	}
}
