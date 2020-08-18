package kr.ajs.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ModelAndView openTilesView(ModelAndView mv) throws Exception{
	    mv.setViewName("/main/home");
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
}
