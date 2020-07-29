package kr.green.test.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.vo.BoardVo;

public interface BoardService {

	ArrayList<BoardVo> getBoard(Criteria cri);

	PageMaker getPage(Criteria cri);

	void insertBoard(BoardVo board);

	BoardVo viewBoard(Integer num);

}
