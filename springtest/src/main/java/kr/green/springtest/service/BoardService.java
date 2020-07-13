package kr.green.springtest.service;

import java.util.ArrayList;

import kr.green.springtest.vo.BoardVo;

public interface BoardService {

	ArrayList<BoardVo> getList();

	BoardVo getBoard(Integer num);

	void increaseViews(Integer num);
}
