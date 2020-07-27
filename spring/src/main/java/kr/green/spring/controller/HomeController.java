package kr.green.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	@Autowired
	private JavaMailSender mailSender;
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
	
	@RequestMapping(value ="/test")
	@ResponseBody
	public Map<Object, Object> test(@RequestBody TestVo test){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    map.put("res", test);
	    return map;
	}
	@RequestMapping(value = "/mail/mailSending")
	public String mailSending(HttpServletRequest request) {

	    String setfrom = "stajun@naver.com";         
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String id = request.getParameter("content");    // 내용
	    
	    int len = 13;
	    String newPw = "";
	    for(int i = 0; i< len; i++) {
	    	int r = (int)(Math.random()*62);
	    	char ch;
	    	if(r<=9) {
	    		ch=(char)('0'+r);
	    	}else if(r<=35) {
	    		ch=(char)('a'+(r-10));
	    	}else {
	    		ch=(char)('A'+(r-36));
	    	}
	    	newPw += ch;
	    }
	    userService.newPw(id, newPw);
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper 
	            = new MimeMessageHelper(message, true, "UTF-8");

	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(tomail);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(newPw);  // 메일 내용

	        mailSender.send(message);
	    } catch(Exception e){
	        System.out.println(e);
	    }

	    return "redirect:/";
	}
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public ModelAndView mailGet(ModelAndView mv) {
		mv.setViewName("/main/mail");
		return mv;
	}
}
class TestVo{
	private String id;
	private int pw;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "TestVo [id=" + id + ", pw=" + pw + "]";
	}
}
