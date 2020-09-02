package kr.ajs.portfolio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.service.AdminService;
import kr.ajs.portfolio.service.UserService;
import kr.ajs.portfolio.utils.UploadFileUtils;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.OrderListVo;
import kr.ajs.portfolio.vo.OrderVo;
import kr.ajs.portfolio.vo.PostDeleteVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;

	
	@RequestMapping(value= {"/admin/goodsadd"}, method = RequestMethod.GET)
	public ModelAndView adminGoodsAddGet(ModelAndView mv, HttpServletRequest request) throws Exception{
		mv = adminService.adminCountInfo(mv);
		mv.setViewName("/goods/adminGoodsAdd");
	    return mv;
	}
	@RequestMapping(value= {"/admin/goodsadd"}, method = RequestMethod.POST)
	public ModelAndView adminGoodsAddPost(ModelAndView mv, HttpServletRequest request, MultipartFile goodsImgAdd, MultipartFile goodsExplainImgAdd, PostVo post, GoodsVo goods, OptionVo option,String[] color, int[] stock) throws IOException, Exception{
		String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\goodsImg";
		String goodsImg = UploadFileUtils.uploadFile(uploadPath, goodsImgAdd.getOriginalFilename(),goodsImgAdd.getBytes());
		String postImg = UploadFileUtils.uploadFile(uploadPath, goodsExplainImgAdd.getOriginalFilename(),goodsExplainImgAdd.getBytes());
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		goods.setGoodsImg(goodsImg);
		post.setPostImg(postImg);
		post.setPost_userId(user.getUserId());
		adminService.goodsAdd(goods, post);
		for (int i=0 ; i<color.length ; ++i) {
			adminService.optionAdd(color[i], stock[i], goods);
		}
		mv.setViewName("redirect:/admin/goodsadd");
	    return mv;
	}
	@RequestMapping(value= {"/admin/goodsmodify"}, method = RequestMethod.GET)
	public ModelAndView adminGoodsModifyGet(ModelAndView mv, HttpServletRequest request, int postNum, int page) throws Exception{
		mv.setViewName("/goods/adminGoodsModify");
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			PostVo post = adminService.getPost(postNum);
			GoodsVo goods = adminService.getGoods(post.getPost_goodsNum());
			ArrayList<OptionVo> list = adminService.getOptionList(goods.getGoodsNum());
			mv = adminService.adminCountInfo(mv);
			mv.addObject("user", user);
			mv.addObject("post", post);
			mv.addObject("goods", goods);
			mv.addObject("list", list);
			mv.addObject("page", page);
		}
	    return mv;
	}
	@RequestMapping(value= {"/admin/goodsmodify"}, method = RequestMethod.POST)
	public ModelAndView adminGoodsModifyPost(ModelAndView mv, HttpServletRequest request, MultipartFile goodsImgAdd, MultipartFile goodsExplainImgAdd, PostVo post, GoodsVo goods, OptionVo option, String[] color, int[] stock, int page) throws IOException, Exception{
		String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\goodsImg";
		String goodsImg = null;
		String postImg = null;
		if(!(goodsImgAdd.getOriginalFilename()=="")) {
			goodsImg = UploadFileUtils.uploadFile(uploadPath, goodsImgAdd.getOriginalFilename(),goodsImgAdd.getBytes());
		}
		if(!(goodsExplainImgAdd.getOriginalFilename()=="")) {
			postImg = UploadFileUtils.uploadFile(uploadPath, goodsExplainImgAdd.getOriginalFilename(),goodsExplainImgAdd.getBytes());
		}
		goods.setGoodsImg(goodsImg);
		post.setPostImg(postImg);
		adminService.goodsModify(goods, post);
		adminService.optionAllDel(goods);
		for (int i=0 ; i<color.length ; ++i) {
			adminService.optionModify(color[i], stock[i], goods);
		}
		mv.setViewName("redirect:/goodslist?type="+goods.getGoodsType()+"&page="+page);
	    return mv;
	}
	@RequestMapping("/admin/postdelete")
	@ResponseBody
	public Map<Object, Object> adminPostDelete(@RequestBody ArrayList<PostDeleteVo> postDeleteInfo){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    int page = postDeleteInfo.get(0).getPage();
	    int type = postDeleteInfo.get(0).getType();
	    adminService.postDelete(postDeleteInfo.get(0).getPostNum());
	    map.put("page",page);
	    map.put("type",type);
	    return map;
	}
	@RequestMapping(value= {"/admin/orderview"}, method = RequestMethod.GET)
	public ModelAndView adminOrderViewGet(ModelAndView mv, HttpServletRequest request, int orderNum, int page) throws Exception{
		UserVo user = (UserVo) request.getSession().getAttribute("user");
		if(user!=null) {
			mv = adminService.adminCountInfo(mv);
			ArrayList<OrderListVo> list;
			list = userService.getOrderGoodsList(orderNum, user);
			OrderVo order = userService.getOrder(orderNum, user);
			mv.addObject("orderNum", orderNum);
			mv.addObject("order", order);
			mv.addObject("list", list);
			mv.addObject("page", page);
		}
		mv.setViewName("/afterOrder/adminOrderView");
	    return mv;
	}
	@RequestMapping("/admin/orderstatemodify")
	@ResponseBody
	public Map<Object, Object> adminOrderstateModify(@RequestBody ArrayList<OrderVo> orderState){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    adminService.orderStateModify(orderState.get(0).getOrderNum(), orderState.get(0).getOrderState());
	    return map;
	}
}
