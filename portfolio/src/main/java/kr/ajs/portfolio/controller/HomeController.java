package kr.ajs.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.service.userService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	userService userService;
	
	@RequestMapping(value= {"/"})
	public ModelAndView openTilesView(ModelAndView mv) throws Exception{
	    mv.setViewName("/main/home");
	    System.out.println(userService.getUser("qwe"));
	    return mv;
	}
	@RequestMapping(value= {"/signUp"})
	public ModelAndView signUpGet(ModelAndView mv) throws Exception{
	    mv.setViewName("/user/userSignUp");
	    return mv;
	}
}
