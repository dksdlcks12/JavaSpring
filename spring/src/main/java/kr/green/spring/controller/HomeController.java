package kr.green.spring.controller;

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

import kr.green.spring.service.UserService;
import kr.green.spring.vo.UserVo;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public ModelAndView homeGet(ModelAndView mv) {
	    mv.setViewName("/main/home");
	    return mv;
	}
	@RequestMapping(value= {"/"}, method = RequestMethod.POST)
	public ModelAndView homePost(ModelAndView mv, UserVo user) {
	    mv.setViewName("/main/home");
	    UserVo dbUser = userService.isSignin(user);
	    if(dbUser != null) {
	    	mv.setViewName("redirect:/board/list");
	    	mv.addObject("user", dbUser);
	    }else {
	    	mv.setViewName("redirect:/");
	    }
	    return mv;
	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupGet(ModelAndView mv) {
		mv.setViewName("/main/signup");
		return mv;
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signupPost(ModelAndView mv, UserVo user) {
		if(userService.singup(user)) {
			mv.setViewName("redirect:/");
		}else {
			mv.setViewName("redirect:/signup");		
			mv.addObject("user",user);
		}		
		return mv;
	}
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ModelAndView signoutGet(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("redirect:/");
		request.getSession().removeAttribute("user");
		return mv;
	}
	@RequestMapping(value ="/idCheck")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){

	    Map<Object, Object> map = new HashMap<Object, Object>();
	    UserVo user = userService.getUser(id);
	    boolean check = user == null ? true : false;
	    map.put("id", id);
	    map.put("check", check);
	    return map;
	}
}
