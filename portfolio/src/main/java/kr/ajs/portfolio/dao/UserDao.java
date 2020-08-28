package kr.ajs.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.ajs.portfolio.pagination.Criteria;
import kr.ajs.portfolio.vo.BoardCartVo;
import kr.ajs.portfolio.vo.BoardWishListVo;
import kr.ajs.portfolio.vo.CartVo;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionListVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.OrderVo;
import kr.ajs.portfolio.vo.AddOrderVo;
import kr.ajs.portfolio.vo.PostVo;
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

	public int getStock(@Param("order")AddOrderVo order, @Param("user")UserVo user);

	public void addOrder(@Param("order")AddOrderVo order);

	public ArrayList<OrderVo> test();
}
