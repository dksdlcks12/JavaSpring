package kr.green.springtest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value= {"/"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView homeGet(ModelAndView mv, UserVo inputUser){
	    mv.setViewName("/main/home");
	    UserVo user = userservice.isUesr(inputUser);
	    mv.addObject("id",inputUser.getId());
	    mv.addObject("isLogin", "isLogin");
	    if(user == null) {
	    	mv.addObject("isLogin", false);
	    }
	    return mv;
	}
}
