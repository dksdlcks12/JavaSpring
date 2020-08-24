package kr.ajs.portfolio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.pagination.PageMaker;
import kr.ajs.portfolio.service.UserService;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;
	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public ModelAndView mainGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null && user.getUserAuth().equals("admin")) {
			mv.setViewName("/main/adminMain");
		}else {
			int type = 0;
			ArrayList<GoodsVo> list;
			list = userService.getGoodsList(type, cri);
			mv.addObject("list", list);
			mv.setViewName("/goods/goodsList");
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
	@RequestMapping(value= {"/goodsList"}, method = RequestMethod.GET)
	public ModelAndView goodsListGet(ModelAndView mv, int type, Criteria cri) throws Exception{
		PageMaker pm = userService.getPageMaker(cri, type);
		ArrayList<GoodsVo> list;
		list = userService.getGoodsList(type, cri);
		mv.addObject("pm", pm);
		mv.addObject("list", list);
		mv.addObject("type", type);
		mv.setViewName("/goods/goodsList");
	    return mv;
	}
	@RequestMapping(value= {"/goodsview"}, method = RequestMethod.GET)
	public ModelAndView goodsView(ModelAndView mv, HttpServletRequest request, int num, int type, int page) throws Exception{
		GoodsVo goods = userService.getGoods(num);
		PostVo post = userService.getPost(num);
		ArrayList<OptionVo> list = userService.getOptionList(num);
		int disCountPrice = goods.getGoodsPrice()/100*(100-post.getPostDiscount());
		mv.addObject("goods", goods);
		mv.addObject("post", post);
		mv.addObject("disCountPrice", disCountPrice);
		mv.addObject("list", list);
	    mv.setViewName("/goods/goodsView");
	    return mv;
	}
	@RequestMapping(value= {"/wishlist"}, method = RequestMethod.GET)
	public ModelAndView wishListGet(ModelAndView mv, HttpServletRequest request) throws Exception{
	    mv.setViewName("/goods/goodsWishList");
	    return mv;
	}
	@RequestMapping(value= {"/wishlist"}, method = RequestMethod.POST)
	public ModelAndView wishListPost(ModelAndView mv, String[] color, int[] count) throws Exception{
		mv.setViewName("redirect:/wishlist");
		for(int i=0; i<color.length; i++) {
			System.out.println(color[i] + ':' + count[i]);
		}
		return mv;
	}
}
