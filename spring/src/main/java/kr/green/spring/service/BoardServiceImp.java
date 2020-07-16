package kr.green.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.spring.dao.BoardDao;
import kr.green.spring.vo.BoardVo;
import pagination.Criteria;
import pagination.PageMaker;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Override
	public ArrayList<BoardVo> getBoardlist(Criteria cri) {
		return boardDao.getBoardlist(cri);
	}

	@Override
	public BoardVo getBoard(Integer num) {
		return boardDao.getBoard(num);
		
	}

	@Override
	public void increaseViews(Integer num) {
		boardDao.increaseViews(num);
	}

	@Override
	public void registerBoard(BoardVo board) {
		boardDao.registerBoard(board);
		
	}

	@Override
	public void updateBoard(BoardVo board) {
		boardDao.updateBoard(board);
		
	}

	@Override
	public void delBoard(Integer num) {
		boardDao.delBoard(num);		
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) {
	    PageMaker pm = new PageMaker();
	    int totalCount = boardDao.getTotalCount(cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
		return pm;
	}

}
