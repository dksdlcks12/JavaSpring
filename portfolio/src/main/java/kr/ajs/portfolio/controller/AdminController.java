package kr.ajs.portfolio.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.ajs.portfolio.service.AdminService;
import kr.ajs.portfolio.utils.UploadFileUtils;

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
	public ModelAndView adminGoodsAddPost(ModelAndView mv, HttpServletRequest request, MultipartFile goodsImgAdd, MultipartFile goodsExplainImgAdd, String[] color, int[] stock) throws IOException, Exception{
		String uploadPath = "D:\\AJS\\JavaSpring\\portfolio\\src\\main\\webapp\\resources\\image\\goodsImg";
		UploadFileUtils.uploadFile(uploadPath, goodsImgAdd.getOriginalFilename(),goodsImgAdd.getBytes());
		UploadFileUtils.uploadFile(uploadPath, goodsExplainImgAdd.getOriginalFilename(),goodsExplainImgAdd.getBytes());
		for (int i=0 ; i<color.length ; i++) {
			System.out.println("색상: "+color[i]+", 재고량: "+stock[i]);
		}
		mv.setViewName("redirect:/");
	    return mv;
	}
}
