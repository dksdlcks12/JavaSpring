package kr.green.springtest.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.pagination.PageMaker;
import kr.green.springtest.vo.BoardVo;
import kr.green.springtest.vo.UserVo;

public interface BoardService {

	ArrayList<BoardVo> getList(Criteria cri);

	BoardVo getBoard(Integer num);

	void setBoard(BoardVo board, HttpServletRequest request);

	BoardVo viewBoard(Integer num, HttpServletRequest request);

	void modifyBoard(BoardVo board, UserVo user);

	void delBoard(Integer num);

	PageMaker getPage(Criteria cri);
}
