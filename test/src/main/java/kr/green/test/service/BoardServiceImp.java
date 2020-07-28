package kr.green.test.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.BoardDao;
import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.vo.BoardVo;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	BoardDao boardDao;

	@Override
	public ArrayList<BoardVo> getBoard(Criteria cri) {
		return boardDao.getBoard(cri);
	}

	@Override
	public PageMaker getPage(Criteria cri) {
		PageMaker pm = new PageMaker();
	    int totalCount = boardDao.getTotalCount(cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
		return pm;
	}
}
