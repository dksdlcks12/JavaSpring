package kr.ajs.portfolio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.pagination.PageMaker;
import kr.ajs.portfolio.service.AdminService;
import kr.ajs.portfolio.service.UserService;
import kr.ajs.portfolio.utils.UploadFileUtils;
import kr.ajs.portfolio.vo.AddOrderVo;
import kr.ajs.portfolio.vo.AsAddVo;
import kr.ajs.portfolio.vo.AsVo;
import kr.ajs.portfolio.vo.BoardCartVo;
import kr.ajs.portfolio.vo.BoardRecallListVo;
import kr.ajs.portfolio.vo.BoardWishListVo;
import kr.ajs.portfolio.vo.CartVo;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.LoginOrderVo;
import kr.ajs.portfolio.vo.NoticeVo;
import kr.ajs.portfolio.vo.OptionListVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.OrderListVo;
import kr.ajs.portfolio.vo.OrderVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.QaVo;
import kr.ajs.portfolio.vo.RecallAddVo;
import kr.ajs.portfolio.vo.RecallViewVo;
import kr.ajs.portfolio.vo.ReviewVo;
import kr.ajs.portfolio.vo.SearchVo;
import kr.ajs.portfolio.vo.UserVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AdminService adminService;
	@RequestMapping(value= {"/"}, method = RequestMethod.GET)
	public ModelAndView mainGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null && user.getUserAuth().equals("admin")) {
			mv = adminService.adminCountInfo(mv);
			ArrayList<OrderVo> list;
			list = adminService.getUncheckOrder();
			for(OrderVo order : list) {
				userService.getOrderGoods(order);
			}
			ArrayList<BoardRecallListVo> recallList;
			recallList = adminService.getUncheckRecallList();
			for(BoardRecallListVo recall : recallList) {
				userService.addRecallListGoodsInfo(recall);
			}
			ArrayList<AsVo> asList;
			asList = adminService.getUncheckAs();
			ArrayList<QaVo> qaList;
			qaList = adminService.getUncheckQa();
			mv.addObject("list", list);
			mv.addObject("recallList", recallList);
			mv.addObject("asList", asList);
			mv.addObject("qaList", qaList);
			mv.setViewName("/main/adminMain");
		}else {
			int type = 0;
			ArrayList<GoodsVo> slideShowList;
			slideShowList = userService.getSlideShowList();
			ArrayList<GoodsVo> list;
			list = userService.getGoodsList(type, cri);
			ArrayList<NoticeVo> noticeList;
			noticeList = userService.getNoticeMain();
			mv.addObject("list", list);
			mv.addObject("noticeList", noticeList);
			mv.addObject("slideShowList", slideShowList);
			mv.setViewName("/main/userMain");		
		}
	    return mv;
	}
	@RequestMapping(value= {"/signup"}, method = RequestMethod.GET)
	public ModelAndView signUpGet(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception{
		mv.setViewName("/user/userSignUp");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null){
			HttpSession session = request.getSession();
			String referer = (String)session.getAttribute("referer2");
			response.sendRedirect(referer);
		}
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
	public ModelAndView logInGet(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception{
		mv.setViewName("/user/userLogIn");
		HttpSession session = request.getSession();
		String url = (String)session.getAttribute("url");
		String referer = (String)session.getAttribute("referer");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user==null) {
			if(url==null) {
				referer = (String)request.getHeader("REFERER");
				if(referer.indexOf("login")==-1 && referer.indexOf("signup")==-1) {
					session.setAttribute("referer", referer);
				}else {
					referer = (String)session.getAttribute("referer2");
					session.setAttribute("referer", referer);
				}
			}
		}else{
			referer = (String)session.getAttribute("referer2");
			response.sendRedirect(referer);
		}
	    return mv;
	}
	@RequestMapping(value= {"/login"}, method = RequestMethod.POST)
	public ModelAndView logInPost(ModelAndView mv, UserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception{
		mv.setViewName("/user/userLogIn");
    	HttpSession session = request.getSession();
		boolean isLogin = userService.isLogin(user);
		mv.addObject("isLogin", isLogin);
		if(isLogin) {
	    	String url = (String)session.getAttribute("url");
	    	if(url==null) {
	    		url = (String)session.getAttribute("referer");
	    		request.getSession().removeAttribute("referer");
	    	}else {
				request.getSession().removeAttribute("url");
	    	}
			mv.addObject("user", user);
			response.sendRedirect(url);
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
	@RequestMapping(value= {"/goodslist"}, method = RequestMethod.GET)
	public ModelAndView goodsListGet(ModelAndView mv, int type, Criteria cri) throws Exception{
		mv = adminService.adminCountInfo(mv);
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
	public ModelAndView goodsView(ModelAndView mv, HttpServletRequest request, int num, int type, int page, HttpServletResponse response) throws Exception{
		mv = adminService.adminCountInfo(mv);
		PostVo post = userService.getPost(num);
		GoodsVo goods = userService.getGoods(post.getPost_goodsNum());
		ArrayList<OptionVo> list = userService.getOptionList(num);
		userService.setLateView(num, request, response);
		int disCountPrice = goods.getGoodsPrice()/100*(100-post.getPostDiscount());
		mv.addObject("goods", goods);
		mv.addObject("post", post);
		mv.addObject("disCountPrice", disCountPrice);
		mv.addObject("list", list);
		mv.addObject("type", type);
		mv.addObject("page", page);
	    mv.setViewName("/goods/goodsView");
	    return mv;
	}
	@RequestMapping("/wishlist")
	@ResponseBody
	public Map<Object, Object> addWishListItem(@RequestBody ArrayList<OptionListVo> optionList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		boolean wishListCheck=false;
	    for(OptionListVo option : optionList) {
	    	if(userService.getWishList(option, user)) {
	    		wishListCheck=true;
	    	}else {
		    	userService.addWishList(option, user);
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
	@RequestMapping("/wishlistcart")
	@ResponseBody
	public Map<Object, Object> wishListCart(@RequestBody ArrayList<OptionListVo> wishList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
	    boolean cartCheck=false;
	    for(OptionListVo wishListItem : wishList) {
	    	if(userService.getcart(wishListItem, user)) {
	    		cartCheck=true;
	    	}else {
		    	userService.addWishListCart(wishListItem, user);
		    	userService.deleteWishList(wishListItem, user);
	    	}
	    }
	    map.put("cartCheck", cartCheck);
	    return map;
	}
	@RequestMapping("/wishlistdel")
	@ResponseBody
	public Map<Object, Object> wishListDel(@RequestBody ArrayList<OptionListVo> wishList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
	    for(OptionListVo wishListItem : wishList) {
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
		}else {
			Cookie[] cookies = request.getCookies();
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("nonMemberId") != -1) {
		    		user = new UserVo();
		    		user.setUserId(cookie.getValue());
		    	}
		    }
		    if(user!=null) {
		    	ArrayList<BoardCartVo> list = userService.getBoardCart(user);
				mv.addObject("list", list);
		    }
		}
	    mv.setViewName("/goods/goodsCart");
	    return mv;
	}
	@RequestMapping("/cartdel")
	@ResponseBody
	public Map<Object, Object> cartDel(@RequestBody ArrayList<OptionListVo> cartList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
	    for(OptionListVo cartListItem : cartList) {
	    	userService.deleteCartList(cartListItem, user);
	    }
	    return map;
	}
	@RequestMapping("/goodsviewcart")
	@ResponseBody
	public Map<Object, Object> goodsViewCart(@RequestBody ArrayList<OptionListVo> optionList, HttpServletRequest request, HttpServletResponse response){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
	    boolean cartCheck=false;
	    if(user!=null) {
		    for(OptionListVo option : optionList) {
		    	if(userService.getcart(option, user)) {
		    		cartCheck=true;
		    	}else {
		    		userService.addGoodsViewCart(option, user);
		    	}
		    }
    	}else {
    		boolean cookiechcek = false;
			Cookie[] cookies = request.getCookies();
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("nonMemberId") != -1) {
			    	Cookie nonMemberCookie = new Cookie("nonMemberId", cookie.getValue());
			    	nonMemberCookie.setMaxAge(60*60*12);
			    	nonMemberCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
			    	response.addCookie(nonMemberCookie);
		    		cookiechcek = true;
		    	}
		    }
		    if(!cookiechcek) {
		    	String nonMemberId = UUID.randomUUID().toString().replace("-","");
		    	Cookie nonMemberCookie = new Cookie("nonMemberId", nonMemberId);
		    	nonMemberCookie.setMaxAge(60*60*12);
		    	nonMemberCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
		    	response.addCookie(nonMemberCookie);
		    }
		    cookies = request.getCookies();
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("nonMemberId") != -1) {
		    		user = new UserVo();
		    		user.setUserId(cookie.getValue());
		    	}
		    }
		    
		    for(OptionListVo option : optionList) {
		    	if(userService.getcart(option, user)) {
		    		cartCheck=true;
		    	}else {
		    		userService.addGoodsViewCart(option, user);
		    	}
		    }
	    }
	    map.put("cartCheck", cartCheck);
	    return map;
	}
	@RequestMapping("/cartcountchange")
	@ResponseBody
	public Map<Object, Object> cartCountChange(@RequestBody ArrayList<CartVo> cartList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		for(CartVo cart : cartList) {
			userService.updateCartCount(cart);
		}
	    return map;
	}
	@RequestMapping(value= {"/goodsvieworder"}, method = RequestMethod.GET)
	public ModelAndView wishListPost(ModelAndView mv, String[] color, int[] count, GoodsVo goods, HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		ArrayList<Integer> cartNumList = new ArrayList<Integer>();
		String address="";
		if(user!=null) {
			String goodsName = goods.getGoodsName();
			for(int i=0; i<color.length; i++) {
				OptionListVo option = new OptionListVo();
				option.setGoods(goodsName);
				option.setColor(color[i]);
				option.setCount(count[i]);
				if(userService.getcart(option, user)) {
					cartNumList.add(userService.getCartNum(option, user));
		    	}else {
		    		int cartNum = userService.addGoodsViewOrderCart(option, user);
		    		cartNumList.add(cartNum);
		    	}
			}
			for(int i=0; i<cartNumList.size(); i++) {
				if(i==0) {
					address = address + "?orderList=" + cartNumList.get(i);
				}else {
					address = address + "&orderList=" + cartNumList.get(i);
				}
			}
			mv.setViewName("redirect:/order"+address);
		}else {
			boolean cookiechcek = false;
			Cookie[] cookies = request.getCookies();
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("nonMemberId") != -1) {
			    	Cookie nonMemberCookie = new Cookie("nonMemberId", cookie.getValue());
			    	nonMemberCookie.setMaxAge(60*60*12);
			    	nonMemberCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
			    	response.addCookie(nonMemberCookie);
		    		cookiechcek = true;
		    	}
		    }
		    if(!cookiechcek) {
		    	String nonMemberId = UUID.randomUUID().toString().replace("-","");
		    	Cookie nonMemberCookie = new Cookie("nonMemberId", nonMemberId);
		    	nonMemberCookie.setMaxAge(60*60*12);
		    	nonMemberCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
		    	response.addCookie(nonMemberCookie);
		    }
		    cookies = request.getCookies();
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("nonMemberId") != -1) {
		    		user = new UserVo();
		    		user.setUserId(cookie.getValue());
		    	}
		    }
		    String goodsName = goods.getGoodsName();
			for(int i=0; i<color.length; i++) {
				OptionListVo option = new OptionListVo();
				option.setGoods(goodsName);
				option.setColor(color[i]);
				option.setCount(count[i]);
				if(userService.getcart(option, user)) {
					cartNumList.add(userService.getCartNum(option, user));
		    	}else {
		    		int cartNum = userService.addGoodsViewOrderCart(option, user);
		    		cartNumList.add(cartNum);
		    	}
			}
			for(int i=0; i<cartNumList.size(); i++) {
				if(i==0) {
					address = address + "?orderList=" + cartNumList.get(i);
				}else {
					address = address + "&orderList=" + cartNumList.get(i);
				}
			}
			mv.setViewName("redirect:/loginorder"+address);
		}
		return mv;
	}
	@RequestMapping(value= {"/order"}, method = RequestMethod.GET)
	public ModelAndView cartOrderGet(ModelAndView mv, HttpServletRequest request, Integer[] orderList) throws Exception{
	    mv.setViewName("/goods/goodsOrder");
	    UserVo user = (UserVo) request.getSession().getAttribute("user");
	    if(user!= null) {
		    userService.getUserPoint(user);
		    mv.addObject("user", user);
		    if(orderList!=null) {
			    ArrayList<BoardCartVo> list = new ArrayList<BoardCartVo>();
			    for(Integer order : orderList) {
			    	list.addAll(userService.getBoardOrder(user, order));
			    }
			    mv.addObject("list", list);
		    }
		}else {
			String address = "";
			for(int i=0; i<orderList.length; i++) {
				if(i==0) {
					address = address + "?orderList=" + orderList[i];
				}else {
					address = address + "&orderList=" + orderList[i];
				}
			}
			mv.setViewName("redirect:/loginorder"+address);
		}
	    return mv;
	}
	@RequestMapping("/addorder")
	@ResponseBody
	public Map<Object, Object> addOrder(@RequestBody ArrayList<AddOrderVo> orderList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		ArrayList<AddOrderVo> goodsList = orderList.get(0).getGoodsList();
		ArrayList<AddOrderVo> orderInfoList = orderList.get(0).getOrderList();
		int index=0;
		boolean stock = true;
		for(AddOrderVo order : goodsList) {
	    	stock = userService.getStock(order);
	    	if (stock==false) {
	    		break;
	    	}
	    }
		if(stock==true) {
			if(orderInfoList!=null) {
		    	index = userService.addOrder(orderInfoList.get(0), user);
			}
			for(AddOrderVo order : goodsList) {
		    	userService.addOrderList(order, index, user);
		    }
		}
		if(user!=null) {
			request.getSession().setAttribute("user", userService.getUser(user.getUserId()));
		}
		map.put("index", index);
		map.put("stock", stock);
		map.put("user", user);
	    return map;
	}
	@RequestMapping(value= {"/orderviewlist"}, method = RequestMethod.GET)
	public ModelAndView orderViewListGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			if(cri.getSearch()==null) {
				cri.setSearch("");
			}
			int count = userService.getAllOrderCount();
			PageMaker pm = userService.getPageMakerOrderView(cri, user);
			ArrayList<OrderVo> list;
			list = userService.getOrderList(user,cri);
			for(OrderVo order : list) {
				userService.getOrderGoods(order);
			}
			if(user.getUserAuth().equals("admin")) {
				mv = adminService.adminCountInfo(mv);
			}
			mv.addObject("pm", pm);
			mv.addObject("user", user);
			mv.addObject("list", list);
			mv.addObject("count", count);
		    mv.setViewName("/afterOrder/orderViewList");
		}else {
			mv.setViewName("redirect:/loginorderview");
		}
	    return mv;
	}
	@RequestMapping(value= {"/orderview"}, method = RequestMethod.GET)
	public ModelAndView orderViewGet(ModelAndView mv, int orderNum, HttpServletRequest request) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			ArrayList<OrderListVo> list;
			list = userService.getOrderGoodsList(orderNum, user);
			OrderVo order = userService.getOrder(orderNum, user);
			mv.addObject("orderNum", orderNum);
			mv.addObject("order", order);
			mv.addObject("list", list);
		}
		mv.setViewName("/afterOrder/orderView");
		return mv;
	}
	@RequestMapping(value= {"/recallselect"}, method = RequestMethod.GET)
	public ModelAndView recallSelectGet(ModelAndView mv, HttpServletRequest request) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			mv.setViewName("/afterOrder/recallSelect");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@RequestMapping(value= {"/recallapplylist"}, method = RequestMethod.GET)
	public ModelAndView recallApplyListGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			PageMaker pm = userService.getPageMakerRecallOrderList(cri, user);
			ArrayList<OrderVo> list = userService.getRecallOrderList(user, cri);
			for(OrderVo order : list) {
				userService.getOrderGoods(order);
			}
			mv.addObject("pm", pm);
			mv.addObject("list", list);
			mv.setViewName("/afterOrder/recallApplyList");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@RequestMapping(value= {"/recallapply"}, method = RequestMethod.GET)
	public ModelAndView recallApplyGet(ModelAndView mv, HttpServletRequest request, int orderNum) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			ArrayList<OrderListVo> list;
			list = userService.getOrderRecallList(orderNum, user);
			mv.addObject("orderNum", orderNum);
			mv.addObject("list", list);
			mv.setViewName("/afterOrder/recallApply");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@RequestMapping("/recallapplyimg")
	@ResponseBody
	public Map<Object, Object> recallApplyImg(@RequestBody MultipartFile file) throws IOException, Exception{
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\recallApply";
	    String img = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
	    map.put("img", img);
	    return map;
	}
	@RequestMapping("/recalladd")
	@ResponseBody
	public Map<Object, Object> recallAdd(@RequestBody ArrayList<RecallAddVo> recallList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		String checkUser = userService.getOrderUserId(recallList.get(0).getOrderNum());
		boolean check = false;
		if(user!=null && user.getUserId().equals(checkUser)) {
			RecallAddVo recall = recallList.get(0);
			userService.addRecall(recall);
			for(RecallAddVo recallOrderList:recall.getGoodsList()) {
				userService.addRecallList(recallOrderList, recall.getRecallNum(), user);
			}
			request.getSession().setAttribute("user", userService.getUser(user.getUserId()));
			check = true;
		}
		map.put("check", check);
	    return map;
	}
	@RequestMapping(value= {"/recallviewlist"}, method = RequestMethod.GET)
	public ModelAndView recallViewlistGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			mv = adminService.adminCountInfo(mv);
			mv.addObject("user", user);
			PageMaker pm = userService.getPageMakerRecallViewList(cri, user);
			ArrayList<BoardRecallListVo> list;
			list = userService.getBoardRecallList(user, cri);
			for(BoardRecallListVo recallList : list) {
				userService.addRecallListGoodsInfo(recallList);
			}
			mv.addObject("pm", pm);
			mv.addObject("list", list);
			mv.setViewName("/afterOrder/recallViewList");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@RequestMapping(value= {"/recallview"}, method = RequestMethod.GET)
	public ModelAndView recallViewGet(ModelAndView mv, HttpServletRequest request, int recallNum) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			RecallViewVo recall;
			recall = userService.getRecallView(recallNum);
			ArrayList<RecallViewVo> goodsList;
			goodsList = userService.getRecallGoodsList(recallNum);
			mv.addObject("recall", recall);
			mv.addObject("goodsList", goodsList);
			mv.setViewName("/afterOrder/recallView");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@RequestMapping(value= {"/asselect"}, method = RequestMethod.GET)
	public ModelAndView asSelectGet(ModelAndView mv) throws Exception{
		mv.setViewName("/afterOrder/asSelect");
		return mv;
	}
	@RequestMapping(value= {"/asapply"}, method = RequestMethod.GET)
	public ModelAndView asApplyGet(ModelAndView mv) throws Exception{
		mv.setViewName("/afterOrder/asApply");
		return mv;
	}
	@RequestMapping("/asapplyimg")
	@ResponseBody
	public Map<Object, Object> asApplyImg(@RequestBody MultipartFile file) throws IOException, Exception{
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\asApply";
	    String img = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
	    map.put("img", img);
	    return map;
	}
	@RequestMapping("/asAdd")
	@ResponseBody
	public Map<Object, Object> asAdd(@RequestBody ArrayList<AsAddVo> asApply, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		userService.addAs(asApply.get(0), user);
	    return map;
	}
	@RequestMapping(value= {"/asviewlist"}, method = RequestMethod.GET)
	public ModelAndView asViewListGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			mv = adminService.adminCountInfo(mv);
			PageMaker pm = userService.getPageMakerAsViewList(cri, user);
			ArrayList<AsVo> list;
			list = userService.getBoardAsList(user, cri);
			mv.addObject("user", user);
			mv.addObject("pm", pm);
			mv.addObject("list", list);
		}
		mv.setViewName("/afterOrder/asViewList");
		return mv;
	}
	@RequestMapping(value= {"/asview"}, method = RequestMethod.GET)
	public ModelAndView asViewGet(ModelAndView mv, int asNum, int page, HttpServletRequest request) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		mv.setViewName("/afterOrder/asView");
		if(user!=null) {
			String userCheck = userService.getAsUser(asNum ,user);
			if(userCheck!=null) {
				AsVo as = userService.getAs(asNum);
				mv.addObject("as", as);
				mv.addObject("page", page);
			}else {
				mv.setViewName("redirect:/asviewlist");
			}
		}
		return mv;
	}
	@RequestMapping(value= {"/noticelist"}, method = RequestMethod.GET)
	public ModelAndView noticeListGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		mv = adminService.adminCountInfo(mv);
		PageMaker pm = userService.getPageMakerNoticeList(cri);
		ArrayList<NoticeVo> list;
		list = userService.getNoticeList(cri);
		mv.addObject("user", user);
		mv.addObject("pm", pm);
		mv.addObject("list", list);
		mv.setViewName("/board/noticeList");
		return mv;
	}
	@RequestMapping(value= {"/noticeview"}, method = RequestMethod.GET)
	public ModelAndView noticeViewGet(ModelAndView mv, int noticeNum, int page, int type, String search) throws Exception{
		mv = adminService.adminCountInfo(mv);
		NoticeVo notice = userService.getNotice(noticeNum);
		mv.addObject("notice", notice);
		mv.addObject("page", page);
		mv.addObject("type", type);
		mv.addObject("search", search);
		mv.setViewName("/board/noticeView");
		return mv;
	}
	@RequestMapping(value= {"/qalist"}, method = RequestMethod.GET)
	public ModelAndView qaListGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		mv = adminService.adminCountInfo(mv);
		PageMaker pm = userService.getPageMakerQaList(cri);
		ArrayList<QaVo> list;
		list = userService.getQaList(cri);
		mv.addObject("user", user);
		mv.addObject("pm", pm);
		mv.addObject("list", list);
		mv.setViewName("/board/qaList");
		return mv;
	}
	@RequestMapping(value= {"/qawrite"}, method = RequestMethod.GET)
	public ModelAndView qaWriteGet(ModelAndView mv) throws Exception{
		mv.setViewName("/board/qaWrite");
		return mv;
	}
	@RequestMapping("/qaimg")
	@ResponseBody
	public Map<Object, Object> qaImg(@RequestBody MultipartFile file) throws IOException, Exception{
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\qa";
	    String img = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
	    map.put("img", img);
	    return map;
	}
	@RequestMapping("qaadd")
	@ResponseBody
	public Map<Object, Object> qaAdd(@RequestBody ArrayList<QaVo> qa, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		userService.qaAdd(qa.get(0));
    	return map;
	}
	@RequestMapping("qapwcheck")
	@ResponseBody
	public Map<Object, Object> qaPwCheck(@RequestBody ArrayList<QaVo> qa, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		boolean qaPwCheck = userService.qaPwcheck(qa.get(0));
		map.put("qaPwCheck", qaPwCheck);
    	return map;
	}
	@RequestMapping(value= {"/qaview"}, method = RequestMethod.GET)
	public ModelAndView qaViewGet(ModelAndView mv, int qaNum, int page, int type, String search) throws Exception{
		mv = adminService.adminCountInfo(mv);
		mv.setViewName("/board/qaView");
		QaVo qa = userService.getQa(qaNum);
		mv.addObject("qa", qa);
		mv.addObject("page", page);
		mv.addObject("type", type);
		mv.addObject("search", search);
		return mv;
	}
	@RequestMapping(value= {"/reviewlist"}, method = RequestMethod.GET)
	public ModelAndView reviewListGet(ModelAndView mv, HttpServletRequest request, Criteria cri) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		mv = adminService.adminCountInfo(mv);
		PageMaker pm = userService.getPageMakerReviewList(cri);
		ArrayList<ReviewVo> list;
		list = userService.getReviewList(cri);
		mv.addObject("pm", pm);
		mv.addObject("list", list);
		mv.addObject("user", user);
		mv.setViewName("/board/reviewList");
		return mv;
	}
	@RequestMapping(value= {"/reviewwritelist"}, method = RequestMethod.GET)
	public ModelAndView reviewWriteListGet(ModelAndView mv, HttpServletRequest request) throws Exception{
		mv.setViewName("/board/reviewWriteList");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		ArrayList<OrderListVo> list;
		list = userService.getReviewOrderList(user);
		mv.addObject("list", list);
		return mv;
	}
	@RequestMapping(value= {"/reviewwrite"}, method = RequestMethod.GET)
	public ModelAndView reviewWriteGet(ModelAndView mv, HttpServletRequest request, int orderListNum) throws Exception{
		mv.addObject("orderListNum", orderListNum);
		mv.setViewName("/board/reviewWrite");
		return mv;
	}
	@RequestMapping("/reviewimg")
	@ResponseBody
	public Map<Object, Object> reviewImg(@RequestBody MultipartFile file) throws IOException, Exception{
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\review";
	    String img = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
	    map.put("img", img);
	    return map;
	}
	@RequestMapping("reviewadd")
	@ResponseBody
	public Map<Object, Object> reviewAdd(@RequestBody ArrayList<ReviewVo> review, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		userService.reviewAdd(review.get(0), user);
    	return map;
	}
	@RequestMapping(value= {"/reviewlookup"}, method = RequestMethod.GET)
	public ModelAndView reviewLookUpGet(ModelAndView mv, HttpServletRequest request, int reviewNum, int page, int type, String search) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		mv = adminService.adminCountInfo(mv);
		ReviewVo review = userService.getreview(reviewNum);
		mv.addObject("user", user);
		mv.addObject("review", review);
		mv.addObject("page", page);
		mv.addObject("type", type);
		mv.addObject("search", search);
		mv.setViewName("/board/reviewLookUp");
		return mv;
	}
	@RequestMapping(value= {"/reviewmodify"}, method = RequestMethod.GET)
	public ModelAndView reviewModifyGet(ModelAndView mv, HttpServletRequest request, int reviewNum, int page, int type, String search) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		ReviewVo review = userService.getreview(reviewNum);
		mv.addObject("user", user);
		mv.addObject("review", review);
		mv.addObject("page", page);
		mv.addObject("type", type);
		mv.addObject("search", search);
		mv.setViewName("/board/reviewModify");
		return mv;
	}
	@RequestMapping("setreviewmodify")
	@ResponseBody
	public Map<Object, Object> setReviewModify(@RequestBody ArrayList<ReviewVo> review, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		boolean check = false;
		if(review.get(0).getReview_userId().equals(user.getUserId())) {
			userService.reviewModify(review.get(0));
			check = true;
		}
		map.put("check", check);
    	return map;
	}
	@RequestMapping("reviewdel")
	@ResponseBody
	public Map<Object, Object> reviewDel(@RequestBody ArrayList<ReviewVo> review, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		boolean check = false;
		if(user!=null && (review.get(0).getReview_userId().equals(user.getUserId()) || user.getUserAuth().equals("admin"))) {
			userService.reviewDel(review.get(0));
			check = true;
		}
		map.put("check", check);
    	return map;
	}
	@RequestMapping(value= {"/goodssearch"}, method = RequestMethod.GET)
	public ModelAndView goodsSearchGet(ModelAndView mv, HttpServletRequest request, Criteria cri, SearchVo search) throws Exception{
		mv = adminService.adminCountInfo(mv);
		PageMaker pm = userService.getPageMakerSearch(search, cri);
		ArrayList<SearchVo> list = userService.getGoodsSearch(search, cri);
		mv.addObject("search", search);
		mv.addObject("list", list);
		mv.addObject("pm", pm);
		mv.setViewName("/goods/goodsSearch");
		return mv;
	}
	@RequestMapping(value= {"/mypage"}, method = RequestMethod.GET)
	public ModelAndView myPageCheckGet(ModelAndView mv, HttpServletRequest request) throws Exception{
		mv = adminService.adminCountInfo(mv);
		mv.setViewName("/user/myPage");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		mv = adminService.adminCountInfo(mv);
		mv.addObject("user", user);
		return mv;
	}
	@RequestMapping("mypagecheckpw")
	@ResponseBody
	public Map<Object, Object> myPageCheckPw(@RequestBody String pw, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		boolean pwCheck = userService.myPagecheckPw(pw, user);
		map.put("pwCheck", pwCheck);
    	return map;
	}
	@RequestMapping(value= {"/mypage"}, method = RequestMethod.POST)
	public ModelAndView mypagePost(ModelAndView mv, HttpServletRequest request, UserVo user) throws Exception{
		UserVo loginUser = (UserVo) request.getSession().getAttribute("user");
		mv = adminService.adminCountInfo(mv);
		userService.myPageUpdate(user, loginUser);
		mv.setViewName("redirect:/");
		return mv;
	}
	@RequestMapping("mypagedel")
	@ResponseBody
	public Map<Object, Object> mypageDel(@RequestBody String userId, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		boolean userDel = userService.getuserDel(user, userId);
		if(userDel) {
			request.getSession().removeAttribute("user");
		}
		map.put("userDel", userDel);
    	return map;
	}
	@RequestMapping(value= {"/loginorder"}, method = RequestMethod.GET)
	public ModelAndView loginOrderGet(ModelAndView mv, HttpServletRequest request, Integer[] orderList, HttpServletResponse response) throws Exception{
		mv.setViewName("/user/loginOrder");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user==null) {
			String address = "";
			for(int i=0; i<orderList.length; i++) {
				if(i==0) {
					address = address + "?orderList=" + orderList[i];
				}else {
					address = address + "&orderList=" + orderList[i];
				}
			}
			mv.addObject("address",address);
		}else {
			HttpSession session = request.getSession();
			String referer = (String)session.getAttribute("referer2");
			response.sendRedirect(referer);
		}
		return mv;
	}
	@RequestMapping(value= {"/loginorder"}, method = RequestMethod.POST)
	public ModelAndView logInOrderPost(ModelAndView mv, LoginOrderVo loginOrder, HttpServletRequest request) throws Exception{
		mv.setViewName("/user/loginOrder");
		UserVo user =new UserVo();
		user.setUserId(loginOrder.getUserId());
		user.setUserPw(loginOrder.getUserPw());
		boolean isLogin = userService.isLogin(user);
		mv.addObject("isLogin", isLogin);
		if(isLogin) {
			mv.addObject("user", user);
		    mv.setViewName("redirect:/loginordercheck"+loginOrder.getAddress());
		}else {
			mv.addObject("address", loginOrder.getAddress());
		}
	    return mv;
	}
	@RequestMapping(value= {"/loginordercheck"}, method = RequestMethod.GET)
	public ModelAndView loginOrderCheckGet(ModelAndView mv, HttpServletRequest request, Integer[] orderList) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		String address = "";
		String referer = (String)request.getHeader("REFERER");
		if(referer!=null && referer.indexOf("loginorder")!=-1) {
			for(int i=0; i<orderList.length; i++) {
				if(i==0) {
					address = address + "?orderList=" + userService.updateCartGetCartNum(orderList[i], user);
				}else {
					address = address + "&orderList=" + userService.updateCartGetCartNum(orderList[i], user);
				}
			}
			if(user!=null) {
				mv.setViewName("redirect:order"+address);
			}else {
				mv.setViewName("redirect:loginorder"+address);
			}
		}else {
			mv.setViewName("redirect:loginorder"+address);
		}
		return mv;
	}
	@RequestMapping(value= {"/nonememberorder"}, method = RequestMethod.GET)
	public ModelAndView noneMemberOrderGet(ModelAndView mv, HttpServletRequest request, Integer[] orderList, HttpServletResponse response) throws Exception{
	    mv.setViewName("/goods/goodsOrder");
	    UserVo user = (UserVo) request.getSession().getAttribute("user");
	    if(user==null) {
		    user = new UserVo();
		    Cookie[] cookies = request.getCookies();
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("nonMemberId") != -1) {
		    		user.setUserId(cookie.getValue());
		    	}
		    }
		    if(orderList!=null) {
			    ArrayList<BoardCartVo> list = new ArrayList<BoardCartVo>();
			    for(Integer order : orderList) {
			    	list.addAll(userService.getBoardOrder(user, order));
			    }
			    mv.addObject("list", list);
		    }
	    }else {
			HttpSession session = request.getSession();
			String referer = (String)session.getAttribute("referer2");
			response.sendRedirect(referer);
	    }
	    return mv;
	}
	@RequestMapping(value= {"/loginorderview"}, method = RequestMethod.GET)
	public ModelAndView loginOrderViewGet(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		mv.setViewName("/user/loginOrderView");
		if(user!=null){
			HttpSession session = request.getSession();
			String referer = (String)session.getAttribute("referer2");
			response.sendRedirect(referer);
		}
		return mv;
	}
	@RequestMapping(value= {"/loginorderview"}, method = RequestMethod.POST)
	public ModelAndView logInOrderViewPost(ModelAndView mv, UserVo user, HttpServletRequest request) throws Exception{
		mv.setViewName("/user/loginOrderView");
		boolean isLogin = userService.isLogin(user);
		mv.addObject("isLogin", isLogin);
		if(isLogin) {
			mv.addObject("user", user);
		    mv.setViewName("redirect:/orderviewlist");
		}
	    return mv;
	}
	@RequestMapping(value= {"/nonmemberorderview"}, method = RequestMethod.GET)
	public ModelAndView nonMemberOrderViewGet(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception{
		mv.setViewName("/user/nonMemberOrderView");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null){
			HttpSession session = request.getSession();
			String referer = (String)session.getAttribute("referer2");
			response.sendRedirect(referer);
		}
		return mv;
	}
	@RequestMapping("nonmemberorderviewcheck")
	@ResponseBody
	public Map<Object, Object> nonMemberOrderViewCheck(@RequestBody ArrayList<OrderVo> order, HttpServletRequest request) throws Exception{
		Map<Object, Object> map = new HashMap<Object, Object>();
		boolean pwCheck;
		pwCheck = userService.orderPwCheck(order.get(0));
		map.put("pwCheck", pwCheck);
		HttpSession noneMemberSession = request.getSession();
		noneMemberSession.setAttribute("orderPw", order.get(0).getOrderPw());
    	return map;
	}
	@RequestMapping(value= {"/nonememberorderviewitem"}, method = RequestMethod.GET)
	public ModelAndView nonMemberOrderViewItemGet(ModelAndView mv, int orderNum, HttpServletRequest request, HttpServletResponse response) throws Exception{
		mv.setViewName("/afterOrder/orderView");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user==null){
			OrderVo order = new OrderVo();
			ArrayList<OrderListVo> list = new ArrayList<OrderListVo>();
			String orderPw = (String) request.getSession().getAttribute("orderPw");
			order = userService.nonMemberOrderView(orderNum, orderPw);
			mv.addObject("order", order);
			if(order!=null) {
				list = userService.nonMembergetOrderGoodsList(orderNum);
			}
			mv.addObject("orderNum", orderNum);
			mv.addObject("order", order);
			mv.addObject("list", list);
			request.getSession().removeAttribute("orderPw");
		}else {
			HttpSession session = request.getSession();
			String referer = (String)session.getAttribute("referer2");
			response.sendRedirect(referer);
		}
		return mv;
	}
}