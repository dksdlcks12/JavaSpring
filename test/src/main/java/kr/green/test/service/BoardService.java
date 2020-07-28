package kr.green.test.service;

import java.util.ArrayList;

import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.vo.BoardVo;

public interface BoardService {

	ArrayList<BoardVo> getBoard(Criteria cri);

	PageMaker getPage(Criteria cri);

}
