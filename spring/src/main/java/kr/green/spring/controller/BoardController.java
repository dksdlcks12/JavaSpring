package kr.green.spring.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import kr.green.spring.service.BoardService;
import kr.green.spring.service.UserService;
import kr.green.spring.utils.UploadFileUtils;
import kr.green.spring.vo.BoardVo;
import kr.green.spring.vo.UserVo;
import pagination.Criteria;
import pagination.PageMaker;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	private String uploadPath = "D:\\uploadfiles";
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView boardlist(ModelAndView mv, Criteria cri, HttpServletRequest request) {
		mv.setViewName("/board/list");
		PageMaker pm = boardService.getPageMaker(cri);
		ArrayList<BoardVo> list;
		list = boardService.getBoardlist(cri);
		mv.addObject("list", list);
		mv.addObject("pm", pm);
		return mv;
	}
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public ModelAndView boardDetail(ModelAndView mv, Integer num, Criteria cri, HttpServletRequest request) {
		mv.setViewName("/board/detail");
		HttpSession session = request.getSession();
		BoardVo board = null;
		if(num!=null) {
			board = boardService.getBoard(num);
			mv.addObject("board", board);
			if(board != null) {
				boardService.increaseViews(num);
				board.setViews(board.getViews()+1);
			}
		}
		mv.addObject("cri", cri);
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public ModelAndView boardRegisterGet(ModelAndView mv) {
		mv.setViewName("/board/register");
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public ModelAndView boardRegisterPost(ModelAndView mv, BoardVo board, HttpServletRequest request, MultipartFile files) throws IOException, Exception {	
		if(board.getWriter()!="" && board.getTitle()!="" && board.getContent()!="") {
			String fileName = UploadFileUtils.uploadFile(uploadPath, files.getOriginalFilename(),files.getBytes());
			board.setFile(fileName);
			boardService.registerBoard(board, request);
			mv.setViewName("redirect:/board/list");
		}else {
			mv.setViewName("redirect:/board/register");
		}
		return mv;
	}
	@RequestMapping(value= {"/board/modify"}, method = RequestMethod.GET)
	public ModelAndView modifyGet(ModelAndView mv, Integer num, HttpServletRequest request) {
		mv.setViewName("/board/modify");
		BoardVo board = boardService.getBoard(num);
		UserVo user = userService.getUser(request);
		if(num!=null && board.getWriter().equals(user.getId())) {
			mv.addObject("board", board);
		}else {
			mv.setViewName("redirect:/board/list");
		}
		return mv;
	}
	@RequestMapping(value= {"/board/modify"}, method = RequestMethod.POST)
	public ModelAndView modifyPOST(ModelAndView mv, BoardVo board, HttpServletRequest request, MultipartFile files, MultipartFile file) throws IOException, Exception {
		mv.setViewName("redirect:/board/detail?num="+board.getNum());
		UserVo user = userService.getUser(request);
		if(user!=null) {
			if(files.getOriginalFilename().length() != 0) {
				String fileName = UploadFileUtils.uploadFile(uploadPath, 
						files.getOriginalFilename(),files.getBytes());
				board.setFile(fileName);
			}else if(board.getFile().length() == 0){
				board.setFile(null);
			}
			boardService.updateBoard(board, user);
		}
		return mv;
	}
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public ModelAndView boardDeleteGet(ModelAndView mv, Integer num, HttpServletRequest request) {
		mv.setViewName("redirect:/board/list");
		boardService.delBoard(num, userService.getUser(request));
		return mv;
	}
	@RequestMapping(value = "/board/like")
	@ResponseBody
	public Map<Object, Object> boardLike(@RequestBody Integer num, HttpServletRequest request){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    UserVo user = userService.getUser(request);
	    if(user == null) {
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
