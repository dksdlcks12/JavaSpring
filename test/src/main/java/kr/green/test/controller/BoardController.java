package kr.green.test.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.service.BoardService;
import kr.green.test.service.UserService;
import kr.green.test.utils.UploadFileUtils;
import kr.green.test.vo.BoardVo;
import kr.green.test.vo.UserVo;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;	
	@Autowired
	UserService userService;
	@Resource
	private String uploadPath;
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView boardListGet(ModelAndView mv, Criteria cri) {
		mv.setViewName("/board/list");
	    PageMaker pm = boardService.getPage(cri);
		ArrayList<BoardVo> list;
		list = boardService.getBoard(cri);
		mv.addObject("list",list);
		mv.addObject("pm",pm);
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public ModelAndView boardRegGet(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("/board/register");
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public ModelAndView boardRegPost(ModelAndView mv, BoardVo board, HttpServletRequest request, MultipartFile files) throws IOException, Exception {
		UserVo user = userService.getUser(request);
		if(user!=null && user.getId().equals(board.getWriter())) {
			if(board.getWriter().length()!=0 && board.getTitle().length()!=0 && board.getContent().length()!=0) {
				mv.setViewName("redirect:/board/list");
				String fileName = UploadFileUtils.uploadFile(uploadPath, files.getOriginalFilename(),files.getBytes());
				board.setFile(fileName);
				boardService.insertBoard(board);	
			}else {
				mv.setViewName("redirect:/board/register");
			}
		}else if(user!=null){
			mv.setViewName("redirect:/board/list");
		}
		return mv;
	}
	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView boardViewGet(ModelAndView mv, Integer num, Criteria cri) {
		mv.setViewName("/board/view");
		BoardVo board = boardService.viewBoard(num);
		mv.addObject("board", board);
		mv.addObject("cri", cri);
		return mv;
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
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public ModelAndView boardModGet(ModelAndView mv, Integer num, Criteria cri) {
		mv.setViewName("/board/modify");
		BoardVo board = boardService.viewBoard(num);
		mv.addObject("board", board);
		System.out.println(cri);
		mv.addObject("cri", cri);
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public ModelAndView boardModPost(ModelAndView mv, BoardVo board, HttpServletRequest request, MultipartFile files, Criteria cri) throws IOException, Exception {
		System.out.println(cri);
		UserVo user = userService.getUser(request);
		if(user!=null && user.getId().equals(board.getWriter())) {
			if(board.getWriter().length()!=0 && board.getTitle().length()!=0 && board.getContent().length()!=0) {
				if(files.getOriginalFilename().length()!=0) {
					String fileName = UploadFileUtils.uploadFile(uploadPath, files.getOriginalFilename(),files.getBytes());
					board.setFile(fileName);
				}else if(board.getFile()==null || board.getFile().length()==0){
					board.setFile(null);
				}
				System.out.println("¼º°ø");
				mv.setViewName("redirect:/board/view?num="+board.getNum()+"&page="+cri.getPage()+"&type="+cri.getType()+"&search="+cri.getSearch());
			}
		}else {
			mv.setViewName("redirect:/board/list");
		}
		return mv;
	}
}