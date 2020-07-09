package kr.green.spring.service;

import java.util.ArrayList;

import kr.green.spring.vo.BoardVo;

public interface BoardService {

	ArrayList<BoardVo> getBoardlist();

	BoardVo getBoard(Integer num);

	void increaseViews(Integer num);

	void registerBoard(BoardVo board);

}
