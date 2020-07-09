package kr.green.spring.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.spring.service.BoardService;
import kr.green.spring.vo.BoardVo;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView boardlist(ModelAndView mv) {
		mv.setViewName("/board/list");
		ArrayList<BoardVo> list;
		list = boardService.getBoardlist();
		mv.addObject("list", list);
		return mv;
	}
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public ModelAndView boardDetail(ModelAndView mv, Integer num) {
		mv.setViewName("/board/detail");
		BoardVo board = null;
		if(num!=null) {
			board = boardService.getBoard(num);
			mv.addObject("board", board);
			if(board != null) {
				boardService.increaseViews(num);
				board.setViews(board.getViews()+1);
			}
		}
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public ModelAndView boardRegisterGet(ModelAndView mv) {
		mv.setViewName("/board/register");
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.POST)
	public ModelAndView boardRegisterPost(ModelAndView mv, BoardVo board) {
		mv.setViewName("redirect:/board/list");
		if(board.getWriter()!="" && board.getTitle()!="" && board.getContent()!="") {
			boardService.registerBoard(board);
		}
		return mv;
	}
}
