package kr.ajs.portfolio.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.service.AdminService;
import kr.ajs.portfolio.utils.UploadFileUtils;
import kr.ajs.portfolio.vo.GoodsVo;
import kr.ajs.portfolio.vo.OptionVo;
import kr.ajs.portfolio.vo.PostVo;
import kr.ajs.portfolio.vo.UserVo;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@RequestMapping(value= {"/admin/goodsadd"}, method = RequestMethod.GET)
	public ModelAndView adminGoodsAddGet(ModelAndView mv, HttpServletRequest request) throws Exception{
		mv.setViewName("/goods/adminGoodsAdd");
	    return mv;
	}
	@RequestMapping(value= {"/admin/goodsadd"}, method = RequestMethod.POST)
	public ModelAndView adminGoodsAddPost(ModelAndView mv, HttpServletRequest request, MultipartFile goodsImgAdd, MultipartFile goodsExplainImgAdd, PostVo post, GoodsVo goods, OptionVo option,String[] color, int[] stock) throws IOException, Exception{
		String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\goodsImg";
		String goodsImg = UploadFileUtils.uploadFile(uploadPath, goodsImgAdd.getOriginalFilename(),goodsImgAdd.getBytes());
		String postImg = UploadFileUtils.uploadFile(uploadPath, goodsExplainImgAdd.getOriginalFilename(),goodsExplainImgAdd.getBytes());
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("user");
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
}
