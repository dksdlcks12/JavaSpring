package kr.green.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.spring.service.UserService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public ModelAndView homeget(ModelAndView mv) throws Exception{
	    mv.setViewName("/main/home");
	    return mv;
	}
	@RequestMapping(value= "/test", method = RequestMethod.GET)
	public ModelAndView testget(ModelAndView mv, String id, String pw) throws Exception{
	    mv.setViewName("/main/test");
	    mv.addObject("title", "�׽�Ʈ");
	    logger.info("���۵� ID : "+id+", ���۵� PW : "+pw);
	    String userPw = userService.getPw(id);
	    logger.info("��ȸ�� ��й�ȣ : "+userPw);
	    int usercount = userService.getCount();
	    logger.info("���� ���Ե� ȸ�� �� : "+usercount);
	    return mv;
	}
	@RequestMapping(value = "/main/naver", method = RequestMethod.GET)
	public String naverget() {
		return "naver";
	}
}
