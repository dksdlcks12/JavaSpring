package kr.ajs.portfolio.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

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

public interface UserService {
	 public UserVo getUser(String id);

	public void signUp(UserVo user);

	public boolean isLogin(UserVo user);

	public ArrayList<GoodsVo> getGoodsList(int type, Criteria cri);

	public PageMaker getPageMaker(Criteria cri, int type);

	public GoodsVo getGoods(int num);

	public PostVo getPost(int num);

	public ArrayList<OptionVo> getOptionList(int num);
	
	public void addWishList(OptionListVo option, UserVo user);

	public boolean getWishList(OptionListVo option, UserVo user);

	public PageMaker getPageMaker(Criteria cri, UserVo user);

	public ArrayList<BoardWishListVo> getBoardWishList(Criteria cri, UserVo user);

	public void deleteWishList(OptionListVo wishListItem, UserVo user);

	public void addWishListCart(OptionListVo wishListItem, UserVo user);

	public ArrayList<BoardCartVo> getBoardCart(UserVo user);

	public void deleteCartList(OptionListVo cartListItem, UserVo user);
	
	public boolean getcart(OptionListVo option, UserVo user);

	public void addGoodsViewCart(OptionListVo option, UserVo user);
	
	public void updateCartCount(CartVo cart);

	public ArrayList<BoardCartVo> getBoardOrder(UserVo user, Integer cartOrder);

	public boolean getStock(AddOrderVo order);

	public int addOrder(AddOrderVo orderVo, UserVo user);

	public void addOrderList(AddOrderVo order, int index, UserVo user);

	public int addGoodsViewOrderCart(OptionListVo option, UserVo user);

	public ArrayList<OrderVo> getOrderList(UserVo user, Criteria cri);

	public void getOrderGoods(OrderVo order);

	public ArrayList<OrderListVo> getOrderGoodsList(int orderNum, UserVo user);

	public OrderVo getOrder(int orderNum, UserVo user);

	public PageMaker getPageMakerOrderView(Criteria cri, UserVo user);

	public int getAllOrderCount();

	public ArrayList<OrderVo> getRecallOrderList(UserVo user, Criteria cri);

	public PageMaker getPageMakerRecallOrderList(Criteria cri, UserVo user);

	public void getUserPoint(UserVo user);

	public String getOrderUserId(int orderNum);

	public void addRecall(RecallAddVo recall);

	public void addRecallList(RecallAddVo recallOrderList, int recallNum, UserVo user);

	public ArrayList<OrderListVo> getOrderRecallList(int orderNum, UserVo user);

	public ArrayList<BoardRecallListVo> getBoardRecallList(UserVo user, Criteria cri);

	public void addRecallListGoodsInfo(BoardRecallListVo recallList);

	public PageMaker getPageMakerRecallViewList(Criteria cri, UserVo user);

	public RecallViewVo getRecallView(int recallNum);

	public ArrayList<RecallViewVo> getRecallGoodsList(int recallNum);

	public void addAs(AsAddVo asAddVo, UserVo user);

	public PageMaker getPageMakerAsViewList(Criteria cri, UserVo user);

	public ArrayList<AsVo> getBoardAsList(UserVo user, Criteria cri);
	
	public String getAsUser(int asNum, UserVo user);

	public AsVo getAs(int asNum);

	public PageMaker getPageMakerNoticeList(Criteria cri);

	public ArrayList<NoticeVo> getNoticeList(Criteria cri);

	public NoticeVo getNotice(int noticeNum);

	public void qaAdd(QaVo qa);

	public PageMaker getPageMakerQaList(Criteria cri);

	public ArrayList<QaVo> getQaList(Criteria cri);

	public boolean qaPwcheck(QaVo qa);

	public QaVo getQa(int qaNum);

	public PageMaker getPageMakerReviewList(Criteria cri);

	public ArrayList<ReviewVo> getReviewList(Criteria cri);
	
	public ArrayList<OrderListVo> getReviewOrderList(UserVo user);

	public void reviewAdd(ReviewVo review, UserVo user);

	public ReviewVo getreview(int reviewNum);

	public void reviewModify(ReviewVo review);

	public void reviewDel(ReviewVo review);

	public ArrayList<NoticeVo> getNoticeMain();

	public PageMaker getPageMakerSearch(SearchVo search, Criteria cri);

	public ArrayList<SearchVo> getGoodsSearch(SearchVo search, Criteria cri);

	public boolean myPagecheckPw(String pw, UserVo user);

	public void myPageUpdate(UserVo user, UserVo loginUser);

	public boolean getuserDel(UserVo user, String userId);

	public ArrayList<GoodsVo> getSlideShowList();

	public void setLateView(int num, HttpServletRequest request, HttpServletResponse response);
	
	public ModelAndView getlateview(ModelAndView mv, HttpServletRequest request);

	public Integer getCartNum(OptionListVo option, UserVo user);

	public int updateCartGetCartNum(int checkCartNum, UserVo user);

	public boolean orderPwCheck(OrderVo order);

	public OrderVo nonMemberOrderView(int orderNum, String orderPw);

	public ArrayList<OrderListVo> nonMembergetOrderGoodsList(int orderNum);

}
