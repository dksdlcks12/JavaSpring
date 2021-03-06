package kr.ajs.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.dao.UserDao;
import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.pagination.PageMaker;
import kr.ajs.portfolio.vo.AddOrderVo;
import kr.ajs.portfolio.vo.AsAddVo;
import kr.ajs.portfolio.vo.AsVo;
import kr.ajs.portfolio.vo.BoardCartVo;
import kr.ajs.portfolio.vo.BoardRecallListVo;
import kr.ajs.portfolio.vo.BoardWishListVo;
import kr.ajs.portfolio.vo.CartVo;
import kr.ajs.portfolio.vo.GoodsVo;
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
import kr.ajs.portfolio.vo.WishListVo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	ArrayList<String> lateViewList = new ArrayList<String>();
	@Override
	public ModelAndView getlateview(ModelAndView mv, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    lateViewList.removeAll(lateViewList);
	    if(cookies!=null) {
			for(Cookie tmp : cookies) {
				if(tmp.getName().indexOf("lateView") != -1) {
					lateViewList.add(tmp.getValue());
				}
			}
			ArrayList<GoodsVo> list = new ArrayList<GoodsVo>();
			
			for(int i=lateViewList.size()-1; i>=0; i--) {
				list.add(userDao.getLateView(lateViewList.get(i)));
			}
			mv.addObject("lateViewList", list);
	    }
		return mv;
	}
	@Override
	public UserVo getUser(String id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}
	@Override
	public void signUp(UserVo user) {
		// TODO Auto-generated method stub
		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		userDao.signUp(user);
	}
	@Override
	public boolean isLogin(UserVo user) {
		// TODO Auto-generated method stub
		UserVo dbUser = userDao.getUser(user.getUserId());
		if(dbUser!=null && passwordEncoder.matches(user.getUserPw(), dbUser.getUserPw())){
			user.setUserAuth(dbUser.getUserAuth());
			return true;
		}else {
			return false;
		}
	}
	@Override
	public ArrayList<GoodsVo> getGoodsList(int type, Criteria cri) {
		// TODO Auto-generated method stub
	    cri.setPerPageNum(12);
		return userDao.getGoodsList(type, cri);
	}
	@Override
	public PageMaker getPageMaker(Criteria cri, int type) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(12);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getGoodsTotalCount(type);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
		return pm;
	}
	@Override
	public GoodsVo getGoods(int num) {
		// TODO Auto-generated method stub
		return userDao.getGoods(num);
	}
	@Override
	public PostVo getPost(int num) {
		// TODO Auto-generated method stub
		return userDao.getPost(num);
	}
	@Override
	public ArrayList<OptionVo> getOptionList(int num) {
		// TODO Auto-generated method stub
		return userDao.getOptionList(num);
	}
	@Override
	public void addWishList(OptionListVo option, UserVo user) {
		// TODO Auto-generated method stub
		userDao.addWishList(option, user);
	}
	@Override
	public boolean getWishList(OptionListVo option, UserVo user) {
		// TODO Auto-generated method stub
		WishListVo wishListCheck = userDao.getWishList(option, user);
		if(wishListCheck!=null) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public PageMaker getPageMaker(Criteria cri, UserVo user) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
		int totalCount = userDao.getWishListTotalCount(user);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
		return pm;
	}
	@Override
	public ArrayList<BoardWishListVo> getBoardWishList(Criteria cri, UserVo user) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		return userDao.getBoardWishList(cri, user);
	}
	@Override
	public void deleteWishList(OptionListVo wishList, UserVo user) {
		// TODO Auto-generated method stub
		userDao.deleteWishList(wishList, user);
	}
	@Override
	public void addWishListCart(OptionListVo wishList, UserVo user) {
		// TODO Auto-generated method stub
		userDao.addWishListCart(wishList, user);
	}
	@Override
	public ArrayList<BoardCartVo> getBoardCart(UserVo user) {
		// TODO Auto-generated method stub
		return userDao.getBoardCart(user);
	}
	@Override
	public void deleteCartList(OptionListVo cartList, UserVo user) {
		// TODO Auto-generated method stub
		userDao.deleteCartList(cartList, user);
	}
	@Override
	public boolean getcart(OptionListVo option, UserVo user) {
		// TODO Auto-generated method stub
		CartVo cartCheck = userDao.getCart(option, user);
		if(cartCheck!=null) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void addGoodsViewCart(OptionListVo option, UserVo user) {
		// TODO Auto-generated method stub
		userDao.addGoodsViewCart(option, user);
	}
	@Override
	public void updateCartCount(CartVo cart) {
		// TODO Auto-generated method stub
		userDao.updateCartCount(cart);
	}
	@Override
	public ArrayList<BoardCartVo> getBoardOrder(UserVo user, Integer cartOrder) {
		// TODO Auto-generated method stub
		int order = cartOrder;
		return userDao.getBoardOrder(user, order);
	}
	@Override
	public boolean getStock(AddOrderVo order) {
		int count = order.getOrderCount();
		int stock = userDao.getStock(order);
		if(count<stock) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int addOrder(AddOrderVo order, UserVo user) {
		// TODO Auto-generated method stub
		OrderVo orderInfo = new OrderVo();
		if(order.getNoneMemberPassword()!=null) {
			order.setNoneMemberPassword(passwordEncoder.encode(order.getNoneMemberPassword()));
		}
		userDao.addOrder(order, user, orderInfo);
		return orderInfo.getOrderNum();
	}
	@Override
	public void addOrderList(AddOrderVo order, int index, UserVo user) {
		// TODO Auto-generated method stub
		int count = order.getOrderCount();
		int stock = userDao.getStock(order);
		stock = stock-count;
		userDao.updateStock(order, stock);
		userDao.addOrderList(order, index);
		userDao.updateUserPoint(order, user);
		userDao.deleteCart(order);
	}
	@Override
	public int addGoodsViewOrderCart(OptionListVo option, UserVo user) {
		// TODO Auto-generated method stub
		CartVo cart = new CartVo();
		userDao.addGoodsViewOrderCart(option, user, cart);
		return cart.getCartNum();
	}
	@Override
	public ArrayList<OrderVo> getOrderList(UserVo user, Criteria cri) {
		cri.setPerPageNum(6);
		return userDao.getOrderList(user, cri);
	}
	@Override
	public void getOrderGoods(OrderVo order) {
		order.setOrderGoodsCount(userDao.getOrderGoodsCount(order));
		order.setOrderGoodsName(userDao.getOrderGoodsName(order));
		order.setOrderGoodsColor(userDao.getOrderGoodsColor(order));	
	}
	@Override
	public ArrayList<OrderListVo> getOrderGoodsList(int orderNum, UserVo user) {
		// TODO Auto-generated method stub
		return userDao.getOrderGoodsList(orderNum, user);
	}
	@Override
	public OrderVo getOrder(int orderNum, UserVo user) {
		// TODO Auto-generated method stub
		return userDao.getOrder(orderNum, user);
	}
	@Override
	public PageMaker getPageMakerOrderView(Criteria cri, UserVo user) {
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
		int totalCount = userDao.getOrderViewListTotalCount(user, cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
		return pm;
	}
	@Override
	public int getAllOrderCount() {
		// TODO Auto-generated method stub
		return userDao.getAllOrderCount();
	}
	@Override
	public ArrayList<OrderVo> getRecallOrderList(UserVo user, Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		return userDao.getRecallOrderList(user, cri);
	}
	@Override
	public PageMaker getPageMakerRecallOrderList(Criteria cri, UserVo user) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getRecallOrderListCount(user);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
	    return pm;
	}
	@Override
	public void getUserPoint(UserVo user) {
		// TODO Auto-generated method stub
		int userPoint = userDao.getuserPoint(user);
		user.setUserPoint(userPoint);
	}
	@Override
	public String getOrderUserId(int orderNum) {
		// TODO Auto-generated method stub
		return userDao.getOrderUserId(orderNum);
	}
	@Override
	public void addRecall(RecallAddVo recall) {
		// TODO Auto-generated method stub
		userDao.addRecall(recall);
	}
	@Override
	public void addRecallList(RecallAddVo recallOrderList, int recallNum, UserVo user) {
		// TODO Auto-generated method stub
		userDao.addRecallList(recallOrderList, recallNum);
		userDao.updateOrderListRecall(recallOrderList);
		String checkRefund = userDao.checkRefund(recallNum);
		if(checkRefund.equals("환불")) {
			userDao.userPointRollBack(user, recallOrderList);
		}
	}
	@Override
	public ArrayList<OrderListVo> getOrderRecallList(int orderNum, UserVo user) {
		// TODO Auto-generated method stub
		return userDao.getOrderRecallList(orderNum, user);
	}
	@Override
	public ArrayList<BoardRecallListVo> getBoardRecallList(UserVo user, Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		return userDao.getBoardRecallList(user, cri);
	}
	@Override
	public void addRecallListGoodsInfo(BoardRecallListVo recallList) {
		// TODO Auto-generated method stub
		recallList.setGoodsName(userDao.getRecallGoodsName(recallList.getRecallNum()));
		recallList.setGoodsColor(userDao.getRecallGoodsColor(recallList.getRecallNum()));
		recallList.setGoodsCount(userDao.getRecallGoodsCount(recallList.getRecallNum()));
	}
	@Override
	public PageMaker getPageMakerRecallViewList(Criteria cri, UserVo user) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getRecallViewListCount(user, cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
	    return pm;
	}
	@Override
	public RecallViewVo getRecallView(int recallNum) {
		// TODO Auto-generated method stub
		return userDao.getRecallView(recallNum);
	}
	@Override
	public ArrayList<RecallViewVo> getRecallGoodsList(int recallNum) {
		// TODO Auto-generated method stub
		return userDao.getRecallGoodsList(recallNum);
	}
	@Override
	public void addAs(AsAddVo asAddVo, UserVo user) {
		// TODO Auto-generated method stub
		userDao.addAs(asAddVo, user);
	}
	@Override
	public PageMaker getPageMakerAsViewList(Criteria cri, UserVo user) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getAsViewListCount(user, cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
	    return pm;
	}
	@Override
	public ArrayList<AsVo> getBoardAsList(UserVo user, Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		return userDao.getAsViewList(user, cri);
	}
	@Override
	public String getAsUser(int asNum, UserVo user) {
		// TODO Auto-generated method stub
		return userDao.getAsUser(asNum, user);
	}
	@Override
	public AsVo getAs(int asNum) {
		// TODO Auto-generated method stub
		return userDao.getAs(asNum);
	}
	@Override
	public PageMaker getPageMakerNoticeList(Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getNoticeListCount(cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
	    return pm;
	}
	@Override
	public ArrayList<NoticeVo> getNoticeList(Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		return userDao.getNoticeList(cri);
	}
	@Override
	public NoticeVo getNotice(int noticeNum) {
		// TODO Auto-generated method stub
		return userDao.getNotice(noticeNum);
	}
	@Override
	public void qaAdd(QaVo qa) {
		// TODO Auto-generated method stub
		if(qa.getQaIsOpen().equals("Y")) {
			qa.setQaPw(null);
		}
		if(qa.getQaPw()!=null) {
			qa.setQaPw(passwordEncoder.encode(qa.getQaPw()));
		}
		userDao.qaAdd(qa);
		userDao.qaOriginNumAdd(qa.getQaNum());
	}
	@Override
	public PageMaker getPageMakerQaList(Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getQaCount(cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
	    return pm;
	}
	@Override
	public ArrayList<QaVo> getQaList(Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		return userDao.getQaList(cri);
	}
	@Override
	public boolean qaPwcheck(QaVo qa) {
		// TODO Auto-generated method stub
		if(passwordEncoder.matches(qa.getQaPw(), userDao.getQaPw(qa.getQaNum()))){
			return true;
		}else {
			return false;
		}
	}
	@Override
	public QaVo getQa(int qaNum) {
		// TODO Auto-generated method stub
		return userDao.getQa(qaNum);
	}
	@Override
	public PageMaker getPageMakerReviewList(Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getReviewCount(cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
	    return pm;
	}
	@Override
	public ArrayList<ReviewVo> getReviewList(Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(6);
		return userDao.getReviewList(cri);
	}
	@Override
	public ArrayList<OrderListVo> getReviewOrderList(UserVo user) {
		// TODO Auto-generated method stub
		return userDao.getReviewOrderList(user);
	}
	@Override
	public void reviewAdd(ReviewVo review, UserVo user) {
		// TODO Auto-generated method stub
		userDao.reviewAdd(review, user);
		userDao.orderListIsReviewUpdate(review);
	}
	@Override
	public ReviewVo getreview(int reviewNum) {
		// TODO Auto-generated method stub
		return userDao.getreview(reviewNum);
	}
	@Override
	public void reviewModify(ReviewVo review) {
		// TODO Auto-generated method stub
		userDao.reviewModify(review);
	}
	@Override
	public void reviewDel(ReviewVo review) {
		// TODO Auto-generated method stub
		userDao.reviewDel(review);
		userDao.orderListIsReviewDel(review);
	}
	@Override
	public ArrayList<NoticeVo> getNoticeMain() {
		// TODO Auto-generated method stub
		return userDao.getNoticeMain();
	}
	@Override
	public PageMaker getPageMakerSearch(SearchVo search, Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(12);
		PageMaker pm = new PageMaker();
	    int totalCount = userDao.getSearchCount(search);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
	    return pm;
	}
	@Override
	public ArrayList<SearchVo> getGoodsSearch(SearchVo search, Criteria cri) {
		// TODO Auto-generated method stub
		cri.setPerPageNum(12);
		return userDao.getGoodsSearch(search, cri);
	}
	@Override
	public boolean myPagecheckPw(String pw, UserVo user) {
		// TODO Auto-generated method stub
		if(user!=null && passwordEncoder.matches(pw, user.getUserPw())){
			return true;
		}else {
			return false;
		}
	}
	@Override
	public void myPageUpdate(UserVo user, UserVo loginUser) {
		// TODO Auto-generated method stub
		user.setUserId(loginUser.getUserId());
		if(user.getUserPw()!="") {
			user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		}
		if(user.getUserPw()!="" || user.getUserMail()!="") {
			userDao.myPageUpdate(user);
		}
	}
	@Override
	public boolean getuserDel(UserVo user, String userId) {
		// TODO Auto-generated method stub
		if(user!=null && user.getUserId().equals(userId) && user.getUserAuth().equals("user")) {
			userDao.userDel(userId);
			return true;
		}
		return false;
	}
	@Override
	public ArrayList<GoodsVo> getSlideShowList() {
		// TODO Auto-generated method stub
		return userDao.getSlideShowList();
	}
	@Override
	public void setLateView(int num, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int index = 0;
		boolean check = false;
		String postNum = Integer.toString(num);
		Cookie[] cookies = request.getCookies();
	    for(Cookie cookie : cookies) {
	    	if(cookie.getName().indexOf("lateView") != -1) {
	    		index++;
	    	}
	    }
	    if(index==0) {
	    	Cookie lateViewCookie = new Cookie("lateView1", postNum);
			lateViewCookie.setMaxAge(60*60*12);
			lateViewCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
			response.addCookie(lateViewCookie);
	    }else if(index==1) {
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("lateView") != -1) {
		    		if(cookie.getValue().equals(postNum)) {
		    			Cookie lateViewCookie = new Cookie(cookie.getName(), cookie.getValue());
						lateViewCookie.setMaxAge(60*60*12);
						lateViewCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
						response.addCookie(lateViewCookie);
		    			check=true;
		    		}
		    	}
		    }
		    if(check==false) {
		    	Cookie lateViewCookie = new Cookie("lateView2", postNum);
				lateViewCookie.setMaxAge(60*60*12);
				lateViewCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
				response.addCookie(lateViewCookie);
		    }
	    }else if(index==2) {
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("lateView") != -1) {
		    		Cookie lateViewCookie = new Cookie(cookie.getName(), cookie.getValue());
					lateViewCookie.setMaxAge(60*60*12);
					lateViewCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
					response.addCookie(lateViewCookie);
		    		if(cookie.getValue().equals(postNum)) {
		    			check=true;
		    		}
		    	}
		    }
		    if(check==false) {
		    	Cookie lateViewCookie = new Cookie("lateView3", postNum);
				lateViewCookie.setMaxAge(60*60*12);
				lateViewCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
				response.addCookie(lateViewCookie);
		    }
	    }
	    else if(index==3) {
		    for(Cookie cookie : cookies) {
		    	if(cookie.getName().indexOf("lateView") != -1) {
		    		if(cookie.getValue().equals(postNum)) {
		    			check=true;
		    		}
		    	}
		    }
		    if(check==false) {
		    	index = 1;
	    		for(int i = 0; i < cookies.length; i++) {
			    	if(cookies[i].getName().indexOf("lateView") != -1) {
			    		if(index!=3) {
				    		Cookie lateViewCookie = new Cookie("lateView"+index, lateViewList.get(index));
							lateViewCookie.setMaxAge(60*60*12);
							lateViewCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
							response.addCookie(lateViewCookie);
			    		}else {
					    	Cookie lateViewCookie = new Cookie("lateView"+index, postNum);
							lateViewCookie.setMaxAge(60*60*12);
							lateViewCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
							response.addCookie(lateViewCookie);
			    		}
			    		index++;
			    	}
			    }
		    }
	    }
		cookies = request.getCookies();
	}
	@Override
	public Integer getCartNum(OptionListVo option, UserVo user) {
		// TODO Auto-generated method stub
		CartVo cart = userDao.getCart(option, user);
		cart.setCartCount(option.getCount());
		userDao.updateCartCount(cart);
		return cart.getCartNum();
	}
	@Override
	public int updateCartGetCartNum(int checkCartNum, UserVo user) {
		// TODO Auto-generated method stub
		CartVo checkCart = userDao.getCartToCartNum(checkCartNum);
		CartVo cart = userDao.checkCart(checkCart.getCart_optionNum(), user);
		if(cart!=null) {
			cart.setCartCount(checkCart.getCartCount());
			userDao.updateCartCount(cart);
			userDao.deleteCheckCart(checkCart);
			return cart.getCartNum();
		}else {
			userDao.updateCartUserId(checkCart, user);
			return checkCartNum;
		}
	}
	@Override
	public boolean orderPwCheck(OrderVo order) {
		// TODO Auto-generated method stub
		OrderVo checkOrder = userDao.getNoneMemberOrder(order);
		if(checkOrder.getOrderPw()!=null && passwordEncoder.matches(order.getOrderPw(), checkOrder.getOrderPw())) {
			return true;
		}
		return false;
	}
	@Override
	public OrderVo nonMemberOrderView(int orderNum, String orderPw) {
		// TODO Auto-generated method stub
		OrderVo order = new OrderVo();
		order.setOrderNum(orderNum);
		order.setOrderPw(orderPw);
		OrderVo dbOrder = userDao.getNoneMemberOrder(order);
		if(order.getOrderPw()!=null && passwordEncoder.matches(order.getOrderPw(), dbOrder.getOrderPw())) {
			return dbOrder;
		}else {
			return null;	
		}
	}
	@Override
	public ArrayList<OrderListVo> nonMembergetOrderGoodsList(int orderNum) {
		// TODO Auto-generated method stub
		return userDao.getnonMemberOrderGoodsList(orderNum);
	}
}
