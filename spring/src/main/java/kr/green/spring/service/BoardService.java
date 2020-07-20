package kr.green.spring.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.spring.vo.BoardVo;
import kr.green.spring.vo.UserVo;
import pagination.Criteria;
import pagination.PageMaker;

public interface BoardService {

	ArrayList<BoardVo> getBoardlist(Criteria cri);

	BoardVo getBoard(Integer num);

	void increaseViews(Integer num);

	void registerBoard(BoardVo board, HttpServletRequest request);

	void updateBoard(BoardVo board, UserVo user);

	void delBoard(Integer num, UserVo userVo);

	PageMaker getPageMaker(Criteria cri);

}
