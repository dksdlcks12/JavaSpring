package kr.green.springtest.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.pagination.PageMaker;
import kr.green.springtest.service.BoardService;
import kr.green.springtest.service.UserService;
import kr.green.springtest.utils.UploadFileUtils;
import kr.green.springtest.vo.BoardVo;
import kr.green.springtest.vo.UserVo;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userservice;
	@Resource
	private String uploadPath;
	
	@RequestMapping(value= {"/board/list"}, method = RequestMethod.GET)
	public ModelAndView listGet(ModelAndView mv, Criteria cri, HttpServletRequest request){
	    mv.setViewName("/board/list");
	    ArrayList<BoardVo> list;
	    PageMaker pm=boardService.getPage(cri);
	    list = boardService.getList(cri);
	    mv.addObject("list",list);
	    mv.addObject("pm",pm);
	    return mv;
	}
	@RequestMapping(value= {"/board/detail"}, method = RequestMethod.GET)
	public ModelAndView detailGet(ModelAndView mv, Integer num, Criteria cri, HttpServletRequest request){
	    mv.setViewName("/board/detail");
	    BoardVo board = boardService.viewBoard(num, request);
	    mv.addObject("board", board);
	    mv.addObject("cri", cri);
	    return mv;
	}
	@RequestMapping(value= {"/board/register"}, method = RequestMethod.GET)
	public ModelAndView registerGet(ModelAndView mv) {
		mv.setViewName("/board/register");
		return mv;
	}
	@RequestMapping(value= {"/board/register"}, method = RequestMethod.POST)
	public ModelAndView registerPost(ModelAndView mv, BoardVo board, HttpServletRequest request, MultipartFile files)throws IOException, Exception {
		mv.setViewName("redirect:/board/list");
		if(board.getWriter()!="" && board.getTitle()!="" && board.getContent()!="") {
			String fileName = UploadFileUtils.uploadFile(uploadPath, files.getOriginalFilename(),files.getBytes());
			board.setFile(fileName);
			boardService.setBoard(board, request);
		}
		return mv;
	}
	@RequestMapping(value= {"/board/modify"}, method = RequestMethod.GET)
	public ModelAndView modifyGet(ModelAndView mv, Integer num, HttpServletRequest request) {
		mv.setViewName("/board/modify");
		BoardVo board = boardService.getBoard(num);
		UserVo user = userservice.getUser(request);
		if(num!=null && board.getWriter().equals(user.getId())) {
			mv.addObject("board", board);
		}else {
			mv.setViewName("redirect:/board/list");
		}
		return mv;
	}
	@RequestMapping(value= {"/board/modify"}, method = RequestMethod.POST)
	public ModelAndView modifyPOST(ModelAndView mv, BoardVo board, HttpServletRequest request) {
		mv.setViewName("redirect:/board/detail?num="+board.getNum());
		UserVo user = userservice.getUser(request);
		if(user!=null) {
			boardService.modifyBoard(board, user);
		}
		return mv;
	}
	@RequestMapping(value= {"/board/del"}, method = RequestMethod.GET)
	public ModelAndView delGet(ModelAndView mv, Integer num, HttpServletRequest request) {
		mv.setViewName("redirect:/board/list");
		boardService.delBoard(num);
		return mv;
	}
	@RequestMapping(value ="/like")
	@ResponseBody
	public Map<Object, Object> like(@RequestBody Integer num, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    UserVo user = userservice.getUser(request);
	    if(user==null) {
	    	map.put("isUser", false);
	    }else {
	    	map.put("isUser", true);
	    	int like = boardService.updateLike(num, user.getId());
	    	map.put("like",like);
	    }
	    return map;
	}
	@ResponseBody
	@RequestMapping("/board/download")
	public ResponseEntity<byte[]> downloadFile(String fileName)throws Exception{
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    try{
	        String FormatName = fileName.substring(fileName.lastIndexOf(".")+1);
	        HttpHeaders headers = new HttpHeaders();
	        in = new FileInputStream(uploadPath+fileName);

	        fileName = fileName.substring(fileName.indexOf("_")+1);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.add("Content-Disposition",  "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
	    }catch(Exception e) {
	        e.printStackTrace();
	        entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	    }finally {
	        in.close();
	    }
	    return entity;
	}
}
