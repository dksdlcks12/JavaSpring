package kr.green.test.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.vo.BoardVo;
import kr.green.test.vo.UserVo;

public interface BoardService {

	ArrayList<BoardVo> getBoard(Criteria cri);

	PageMaker getPage(Criteria cri);

	void insertBoard(BoardVo board);

	BoardVo viewBoard(Integer num);

	void updateBoard(BoardVo board);

	void deleteBoard(int boardNum);

	void increaseView(Integer num);

	int updateLike(Integer num, UserVo user);

}
