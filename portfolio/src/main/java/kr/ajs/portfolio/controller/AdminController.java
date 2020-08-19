package kr.ajs.portfolio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;
	@RequestMapping(value= {"/admin/goodsadd"}, method = RequestMethod.GET)
	public ModelAndView openTilesView(ModelAndView mv, HttpServletRequest request) throws Exception{
		mv.setViewName("/goods/adminGoodsAdd");
	    return mv;
	}
}
