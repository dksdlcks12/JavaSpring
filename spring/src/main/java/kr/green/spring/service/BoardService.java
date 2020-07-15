package kr.green.spring.service;

import java.util.ArrayList;

import kr.green.spring.vo.BoardVo;
import pagination.Criteria;
import pagination.PageMaker;

public interface BoardService {

	ArrayList<BoardVo> getBoardlist(Criteria cri);

	BoardVo getBoard(Integer num);

	void increaseViews(Integer num);

	void registerBoard(BoardVo board);

	void updateBoard(BoardVo board);

	void delBoard(Integer num);

	PageMaker getPageMaker(Criteria cri);

}
