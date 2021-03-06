package kr.ajs.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.pagination.Criteria;
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
import kr.ajs.portfolio.vo.AddOrderVo;
import kr.ajs.portfolio.vo.AsAddVo;
import kr.ajs.portfolio.vo.AsVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.QaVo;
import kr.ajs.portfolio.vo.RecallAddVo;
import kr.ajs.portfolio.vo.RecallViewVo;
import kr.ajs.portfolio.vo.ReviewVo;
import kr.ajs.portfolio.vo.SearchVo;
import kr.ajs.portfolio.vo.UserVo;
import kr.ajs.portfolio.vo.WishListVo;

public interface UserDao {
    public UserVo getUser(@Param("id")String id);

	public void signUp(@Param("user")UserVo user);

	public ArrayList<GoodsVo> getGoodsList(@Param("type")int type, @Param("cri")Criteria cri);
	
	public int getGoodsTotalCount(@Param("type")int type);

	public GoodsVo getGoods(@Param("num")int num);

	public PostVo getPost(@Param("num")int num);

	public ArrayList<OptionVo> getOptionList(@Param("num")int num);

	public void addWishList(@Param("option")OptionListVo option, @Param("user")UserVo user);

	public WishListVo getWishList(@Param("option")OptionListVo option, @Param("user")UserVo user);

	public int getWishListTotalCount(@Param("user")UserVo user);

	public ArrayList<BoardWishListVo> getBoardWishList(@Param("cri")Criteria cri, @Param("user")UserVo user);

	public void deleteWishList(@Param("wishList")OptionListVo wishList, @Param("user")UserVo user);

	public void addWishListCart(@Param("wishList")OptionListVo wishList, @Param("user")UserVo user);

	public ArrayList<BoardCartVo> getBoardCart(@Param("user")UserVo user);

	public void deleteCartList(@Param("cartList")OptionListVo cartList, @Param("user")UserVo user);
	
	public CartVo getCart(@Param("option")OptionListVo option, @Param("user")UserVo user);

	public void addGoodsViewCart(@Param("option")OptionListVo option, @Param("user")UserVo user);
	
	public void updateCartCount(@Param("cart")CartVo cart);

	public ArrayList<BoardCartVo> getBoardOrder(@Param("user")UserVo user, @Param("order")int order);

	public int getStock(@Param("order")AddOrderVo order);

	public void addOrder(@Param("order")AddOrderVo order, @Param("user")UserVo user, @Param("orderInfo")OrderVo orderInfo);

	public void updateStock(@Param("order") AddOrderVo order, @Param("stock")int stock);

	public void addOrderList(@Param("order")AddOrderVo order, @Param("index")int index);

	public void updateUserPoint(@Param("order")AddOrderVo order, @Param("user")UserVo user);
	
	public void addGoodsViewOrderCart(@Param("option")OptionListVo option, @Param("user")UserVo user, @Param("cart")CartVo cart);

	public void deleteCart(@Param("order")AddOrderVo order);

	public ArrayList<OrderVo> getOrderList(@Param("user")UserVo user, @Param("cri")Criteria cri);

	public int getOrderGoodsCount(@Param("order")OrderVo order);

	public String getOrderGoodsName(@Param("order")OrderVo order);

	public String getOrderGoodsColor(@Param("order")OrderVo order);

	public ArrayList<OrderListVo> getOrderGoodsList(@Param("orderNum")int orderNum, @Param("user")UserVo user);

	public OrderVo getOrder(@Param("orderNum")int orderNum, @Param("user")UserVo user);

	public int getOrderViewListTotalCount(@Param("user")UserVo user, @Param("cri")Criteria cri);

	public int getAllOrderCount();

	public ArrayList<OrderVo> getRecallOrderList(@Param("user")UserVo user, @Param("cri")Criteria cri);

	public int getRecallOrderListCount(@Param("user")UserVo user);

	public int getuserPoint(@Param("user")UserVo user);

	public String getOrderUserId(@Param("orderNum")int orderNum);

	public void addRecall(@Param("recall")RecallAddVo recall);

	public void addRecallList(@Param("recall")RecallAddVo recallOrderList, @Param("recallNum")int recallNum);

	public void updateOrderListRecall(@Param("recall")RecallAddVo recallOrderList);

	public ArrayList<OrderListVo> getOrderRecallList(@Param("orderNum")int orderNum, @Param("user")UserVo user);

	public ArrayList<BoardRecallListVo> getBoardRecallList(@Param("user")UserVo user, @Param("cri")Criteria cri);

	public String getRecallGoodsName(@Param("recallNum")int recallNum);

	public String getRecallGoodsColor(@Param("recallNum")int recallNum);

	public int getRecallGoodsCount(@Param("recallNum")int recallNum);

	public int getRecallViewListCount(@Param("user")UserVo user, @Param("cri")Criteria cri);

	public RecallViewVo getRecallView(@Param("recallNum")int recallNum);

	public ArrayList<RecallViewVo> getRecallGoodsList(@Param("recallNum")int recallNum);

	public void addAs(@Param("as")AsAddVo asAddVo, @Param("user")UserVo user);

	public int getAsViewListCount(@Param("user")UserVo user, @Param("cri")Criteria cri);

	public ArrayList<AsVo> getAsViewList(@Param("user")UserVo user, @Param("cri")Criteria cri);
	
	public String getAsUser(@Param("asNum")int asNum, @Param("user")UserVo user);

	public AsVo getAs(@Param("asNum")int asNum);

	public int getNoticeListCount(@Param("cri")Criteria cri);

	public ArrayList<NoticeVo> getNoticeList(@Param("cri")Criteria cri);

	public NoticeVo getNotice(@Param("noticeNum")int noticeNum);

	public void qaAdd(@Param("qa")QaVo qa);
	
	public void qaOriginNumAdd(@Param("qaNum")int qaNum);

	public int getQaCount(@Param("cri")Criteria cri);

	public ArrayList<QaVo> getQaList(@Param("cri")Criteria cri);

	public String getQaPw(@Param("qaNum")int qaNum);

	public QaVo getQa(@Param("qaNum")int qaNum);

	public int getReviewCount(@Param("cri")Criteria cri);

	public ArrayList<ReviewVo> getReviewList(@Param("cri")Criteria cri);

	public ArrayList<OrderListVo> getReviewOrderList(@Param("user")UserVo user);

	public void reviewAdd(@Param("review")ReviewVo review, @Param("user")UserVo user);

	public void orderListIsReviewUpdate(@Param("review")ReviewVo review);

	public ReviewVo getreview(@Param("reviewNum")int reviewNum);

	public void reviewModify(@Param("review")ReviewVo review);

	public void reviewDel(@Param("review")ReviewVo review);

	public void orderListIsReviewDel(@Param("review")ReviewVo review);

	public ArrayList<NoticeVo> getNoticeMain();

	public int getSearchCount(@Param("search")SearchVo search);

	public ArrayList<SearchVo> getGoodsSearch(@Param("search")SearchVo search, @Param("cri")Criteria cri);

	public void myPageUpdate(@Param("user")UserVo user);

	public void userDel(@Param("userId")String userId);

	public void userPointRollBack(@Param("user")UserVo user, @Param("recall")RecallAddVo recallOrderList);

	public String checkRefund(@Param("recallNum")int recallNum);

	public ArrayList<GoodsVo> getSlideShowList();

	public GoodsVo getLateView(@Param("postNum")String postNum);

	public CartVo getCartToCartNum(@Param("cartNum")int checkCartNum);

	public CartVo checkCart(@Param("optionNum")int optionNum, @Param("user")UserVo user);

	public void updateCartUserId(@Param("cart")CartVo checkCart, @Param("user")UserVo user);

	public void deleteCheckCart(@Param("cart")CartVo checkCart);

	public OrderVo getNoneMemberOrder(@Param("order")OrderVo order);

	public ArrayList<OrderListVo> getnonMemberOrderGoodsList(@Param("orderNum")int orderNum);
	
}
