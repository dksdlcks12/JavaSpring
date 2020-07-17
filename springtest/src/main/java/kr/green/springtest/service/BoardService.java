package kr.green.springtest.service;

import java.util.ArrayList;

import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.pagination.PageMaker;
import kr.green.springtest.vo.BoardVo;

public interface BoardService {

	ArrayList<BoardVo> getList(Criteria cri);

	BoardVo getBoard(Integer num);

	void setBoard(BoardVo board);

	BoardVo viewBoard(Integer num);

	void modifyBoard(BoardVo board);

	void delBoard(Integer num);

	PageMaker getPage(Criteria cri);
}
