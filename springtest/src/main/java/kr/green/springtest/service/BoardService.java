package kr.green.springtest.service;

import java.util.ArrayList;

import kr.green.springtest.vo.BoardVo;
import pagination.Criteria;
import pagination.PageMaker;

public interface BoardService {

	ArrayList<BoardVo> getList(Criteria cri);

	BoardVo getBoard(Integer num);

	void setBoard(BoardVo board);

	BoardVo viewBoard(Integer num);

	void modifyBoard(BoardVo board);

	void delBoard(Integer num);

	PageMaker getPage(Criteria cri);
}
