package kr.ajs.portfolio.controller;

import java.io.IOException;
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
import kr.ajs.portfolio.vo.OptionListVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.OrderListVo;
import kr.ajs.portfolio.vo.OrderVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.RecallAddVo;
import kr.ajs.portfolio.vo.RecallViewVo;
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
			mv.addObject("list", list);
			mv.addObject("recallList", recallList);
			mv.addObject("asList", asList);
			mv.setViewName("/main/adminMain");
		}else {
			int type = 0;
			ArrayList<GoodsVo> list;
			list = userService.getGoodsList(type, cri);
			mv.addObject("list", list);
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
	public ModelAndView goodsView(ModelAndView mv, HttpServletRequest request, int num, int type, int page) throws Exception{
		mv = adminService.adminCountInfo(mv);
		GoodsVo goods = userService.getGoods(num);
		PostVo post = userService.getPost(num);
		ArrayList<OptionVo> list = userService.getOptionList(num);
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
		    mv.setViewName("/goods/goodsCart");
		}else {
			mv.setViewName("redirect:/login");
		}
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
	public Map<Object, Object> goodsViewCart(@RequestBody ArrayList<OptionListVo> optionList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
	    boolean cartCheck=false;
	    for(OptionListVo option : optionList) {
	    	if(userService.getcart(option, user)) {
	    		cartCheck=true;
	    	}else {
	    		userService.addGoodsViewCart(option, user);
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
	public ModelAndView wishListPost(ModelAndView mv, String[] color, int[] count, GoodsVo goods, HttpServletRequest request) throws Exception{
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
			mv.setViewName("redirect:/login");
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
			mv.setViewName("redirect:/login");
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
		    	userService.addOrderList(order, index);
		    }
		}
		map.put("stock", stock);
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
			mv.setViewName("redirect:/login");
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
			mv.setViewName("/afterOrder/orderView");
		}else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	@RequestMapping(value= {"/recallselect"}, method = RequestMethod.GET)
	public ModelAndView recallSelectGet(ModelAndView mv) throws Exception{
		mv.setViewName("/afterOrder/recallSelect");
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
	@RequestMapping("/recallApplyImg")
	@ResponseBody
	public Map<Object, Object> recallApplyImg(@RequestBody MultipartFile file) throws IOException, Exception{
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\recallApply";
	    String img = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
	    map.put("img", img);
	    return map;
	}
	@RequestMapping("/recallAdd")
	@ResponseBody
	public Map<Object, Object> recallAdd(@RequestBody ArrayList<RecallAddVo> recallList, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		String checkUser = userService.getOrderUserId(recallList.get(0).getOrderNum());
		boolean check = false;
		if(user!=null && user.getUserId().equals(checkUser)) {
			RecallAddVo recall = recallList.get(0);
			userService.addRecall(recall);
			System.out.println(recall.getRecallNum());
			for(RecallAddVo recallOrderList:recall.getGoodsList()) {
				userService.addRecallList(recallOrderList, recall.getRecallNum());
			}
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
	@RequestMapping("/asApplyImg")
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
}