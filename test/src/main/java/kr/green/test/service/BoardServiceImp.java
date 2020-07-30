package kr.green.test.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.test.dao.BoardDao;
import kr.green.test.pagination.Criteria;
import kr.green.test.pagination.PageMaker;
import kr.green.test.vo.BoardVo;
import kr.green.test.vo.UserVo;

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

	@Override
	public void insertBoard(BoardVo board) {
		boardDao.insertBoard(board);
		
	}

	@Override
	public BoardVo viewBoard(Integer num) {
		return boardDao.viewBoard(num);
	}

	@Override
	public void updateBoard(BoardVo board) {
		boardDao.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int boardNum) {
		boardDao.deleteBoard(boardNum);
		
	}

	@Override
	public void increaseView(Integer num) {
		boardDao.increaseView((int)num);
	}

	@Override
	public int updateLike(Integer num, UserVo user) {
		if(boardDao.isLike((int)num, user)!=0) {
			return -1;
		}else {
			boardDao.insertLike((int)num, user);
		}
		boardDao.updateLike((int)num);
		BoardVo board = viewBoard((int)num);
		return board.getLike();
	}
}
