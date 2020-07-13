package kr.green.springtest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.service.BoardService;
import kr.green.springtest.vo.BoardVo;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value= {"/board/list"}, method = RequestMethod.GET)
	public ModelAndView listGet(ModelAndView mv){
	    mv.setViewName("/board/list");
	    ArrayList<BoardVo> list;
	    list = boardService.getList();
	    mv.addObject("list",list);
	    return mv;
	}
	@RequestMapping(value= {"/board/detail"}, method = RequestMethod.GET)
	public ModelAndView detailGet(ModelAndView mv, Integer num){
	    mv.setViewName("/board/detail");
	    BoardVo board = null;
	    if(num != null) {
	    	board = boardService.getBoard(num);
	    	mv.addObject("board", board);
	    	if(board != null) {
	    		boardService.increaseViews(num);
	    		board.setViews(board.getViews()+1);
	    	}
	    }
	    return mv;
	}
}
