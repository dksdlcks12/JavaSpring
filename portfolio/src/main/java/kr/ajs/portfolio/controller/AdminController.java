package kr.ajs.portfolio.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.service.UserService;
import kr.ajs.portfolio.utils.UploadFileUtils;

@Controller
public class AdminController {
	
	@Autowired
	UserService userService;
	@RequestMapping(value= {"/admin/goodsadd"}, method = RequestMethod.GET)
	public ModelAndView adminGoodsAddGet(ModelAndView mv, HttpServletRequest request) throws Exception{
		mv.setViewName("/goods/adminGoodsAdd");
	    return mv;
	}
	@RequestMapping(value= {"/admin/goodsadd"}, method = RequestMethod.POST)
	public ModelAndView adminGoodsAddPost(ModelAndView mv, HttpServletRequest request, MultipartFile goodsImg, MultipartFile goodsExplainImg) throws IOException, Exception{
		String uploadPath = "C:\\Users\\Administrator\\Desktop\\test";
		UploadFileUtils.uploadFile(uploadPath, goodsImg.getOriginalFilename(),goodsImg.getBytes());
		UploadFileUtils.uploadFile(uploadPath, goodsExplainImg.getOriginalFilename(),goodsExplainImg.getBytes());
		mv.setViewName("redirect:/");
	    return mv;
	}
}
