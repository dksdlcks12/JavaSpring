package kr.green.springtest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.pagination.PageMaker;
import kr.green.springtest.service.BoardService;
import kr.green.springtest.vo.BoardVo;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value= {"/board/list"}, method = RequestMethod.GET)
	public ModelAndView listGet(ModelAndView mv, Criteria cri){
	    mv.setViewName("/board/list");
	    ArrayList<BoardVo> list;
	    PageMaker pm=boardService.getPage(cri);
	    list = boardService.getList(cri);
	    mv.addObject("list",list);
	    mv.addObject("pm",pm);
	    return mv;
	}
	@RequestMapping(value= {"/board/detail"}, method = RequestMethod.GET)
	public ModelAndView detailGet(ModelAndView mv, Integer num, Criteria cri){
	    mv.setViewName("/board/detail");
	    BoardVo board = boardService.viewBoard(num);
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
	public ModelAndView registerPost(ModelAndView mv, BoardVo board) {
		if(board.getTitle()!="" && board.getWriter()!="" && board.getContent()!="") {
			boardService.setBoard(board);
		}
		mv.setViewName("redirect:/board/list");
		return mv;
	}
	@RequestMapping(value= {"/board/modify"}, method = RequestMethod.GET)
	public ModelAndView modifyGet(ModelAndView mv, Integer num) {
		mv.setViewName("/board/modify");
		BoardVo board = boardService.getBoard(num);
		mv.addObject("board", board);
		return mv;
	}
	@RequestMapping(value= {"/board/modify"}, method = RequestMethod.POST)
	public ModelAndView modifyPOST(ModelAndView mv, BoardVo board) {
		mv.setViewName("redirect:/board/detail?num="+board.getNum());
		boardService.modifyBoard(board);
		return mv;
	}
	@RequestMapping(value= {"/board/del"}, method = RequestMethod.GET)
	public ModelAndView delGet(ModelAndView mv, Integer num) {
		mv.setViewName("redirect:/board/list");
		boardService.delBoard(num);
		return mv;
	}
}
