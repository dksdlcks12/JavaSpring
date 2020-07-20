package kr.green.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.spring.service.BoardService;
import kr.green.spring.service.UserService;
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
	public ModelAndView boardRegisterPost(ModelAndView mv, BoardVo board, HttpServletRequest request) {
		mv.setViewName("redirect:/board/list");
		if(board.getWriter()!="" && board.getTitle()!="" && board.getContent()!="") {
			boardService.registerBoard(board, request);
		}
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public ModelAndView boardModifyGet(ModelAndView mv, Integer num, HttpServletRequest request) {
		mv.setViewName("/board/modify");
		BoardVo board = null;
		UserVo user =  userService.getUser(request);
		if(num!=null) {
			board = boardService.getBoard(num);
			if(user==null || !board.getWriter().equals(user.getId()))
				mv.setViewName("redirect:/board/list");
			mv.addObject("board", board);
		}
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public ModelAndView boardModifyPost(ModelAndView mv, BoardVo board, HttpServletRequest request) {
		mv.setViewName("redirect:/board/detail?num="+board.getNum());
		UserVo user = userService.getUser(request);
		if(user!=null && board.getWriter().equals(user.getId())) {
			boardService.updateBoard(board, user);
		}else {
			mv.setViewName("redirect:/board/list");
		}
		return mv;
	}
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public ModelAndView boardDeleteGet(ModelAndView mv, Integer num, HttpServletRequest request) {
		mv.setViewName("redirect:/board/list");
		boardService.delBoard(num, userService.getUser(request));
		return mv;
	}
}
