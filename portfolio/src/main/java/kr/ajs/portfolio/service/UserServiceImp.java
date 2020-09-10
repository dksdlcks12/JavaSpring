package kr.ajs.portfolio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import kr.ajs.portfolio.vo.UserVo;
import kr.ajs.portfolio.vo.WishListVo;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
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
		userDao.addOrder(order, user, orderInfo);
		return orderInfo.getOrderNum();
	}
	@Override
	public void addOrderList(AddOrderVo order, int index) {
		// TODO Auto-generated method stub
		int count = order.getOrderCount();
		int stock = userDao.getStock(order);
		stock = stock-count;
		userDao.updateStock(order, stock);
		userDao.addOrderList(order, index);
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
	public void addRecallList(RecallAddVo recallOrderList, int recallNum) {
		// TODO Auto-generated method stub
		userDao.addRecallList(recallOrderList, recallNum);
		userDao.updateOrderListRecall(recallOrderList);
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
		return userDao.getQaList(cri);
	}
}
