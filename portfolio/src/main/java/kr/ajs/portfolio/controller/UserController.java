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
import kr.ajs.portfolio.vo.BoardCartVo;
import kr.ajs.portfolio.vo.BoardWishListVo;
import kr.ajs.portfolio.vo.CartVo;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.InputOptionVo;
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
	@RequestMapping("/wishlist")
	@ResponseBody
	public Map<Object, Object> addWishListItem(@RequestBody ArrayList<InputOptionVo> optionList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		boolean wishListCheck=false;
	    for(InputOptionVo option : optionList) {
	    	if(userService.getWishList(option, user)) {
	    		wishListCheck=true;
	    	}else {
		    	userService.setWishList(option, user);
	    	}
	    }
	    map.put("wishListCheck", wishListCheck);
	    return map;
	}
	@RequestMapping(value= {"/wishlist"}, method = RequestMethod.GET)
	public ModelAndView wishListGet(ModelAndView mv, Criteria cri, HttpServletRequest request) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			PageMaker pm = userService.getPageMaker(cri, user);
			ArrayList<BoardWishListVo> list;
			list = userService.getBoardWishList(cri, user);
			mv.addObject("pm", pm);
			mv.addObject("list", list);
			mv.setViewName("/goods/goodsWishList");
		}else {
			mv.setViewName("redirect:/login");
		}
	    return mv;
	}
	@RequestMapping("/wishListCart")
	@ResponseBody
	public Map<Object, Object> test(@RequestBody ArrayList<InputOptionVo> wishList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		System.out.println(user);
	    for(InputOptionVo wishListItem : wishList) {
	    	userService.addWishListCart(wishListItem, user);
	    	userService.deleteWishList(wishListItem, user);
	    }
	    return map;
	}
	@RequestMapping("/wishlistdel")
	@ResponseBody
	public Map<Object, Object> test2(@RequestBody ArrayList<InputOptionVo> wishList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
	    for(InputOptionVo wishListItem : wishList) {
	    	userService.deleteWishList(wishListItem, user);
	    }
	    return map;
	}
	@RequestMapping(value= {"/cart"}, method = RequestMethod.GET)
	public ModelAndView cartGet(ModelAndView mv, HttpServletRequest request) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			ArrayList<BoardCartVo> list = userService.getBoardCart(user);
			mv.addObject("list", list);
		    mv.setViewName("/goods/goodsCart");
		}else {
			mv.setViewName("redirect:/login");
		}
	    return mv;
	}
	/*@RequestMapping(value= {"/gocart"}, method = RequestMethod.POST)
	public ModelAndView wishListPost(ModelAndView mv, String[] color, int[] count, GoodsVo goods, HttpServletRequest request) throws Exception{
		mv.setViewName("redirect:/wishlist");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		System.out.println(goods);
		System.out.println(user);
		for(int i=0; i<color.length; i++) {
			System.out.println(color[i] + ':' + count[i]);
		}	
		for(int i=0; i<color.length; i++) {
		}	
		return mv;
	}*/
}
