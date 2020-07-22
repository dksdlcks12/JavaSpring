package kr.green.spring.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.spring.dao.BoardDao;
import kr.green.spring.vo.BoardVo;
import kr.green.spring.vo.UserVo;
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
	public void registerBoard(BoardVo board, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		if(user == null) {
			return;
		}
		board.setWriter(user.getId());
		boardDao.registerBoard(board);
		
	}

	@Override
	public void updateBoard(BoardVo board, UserVo user) {
		board.setWriter(user.getId());
		boardDao.updateBoard(board);
		
	}

	@Override
	public void delBoard(Integer num, UserVo userVo) {
		if(num!=null && userVo!=null) {
			BoardVo board = boardDao.getBoard(num);
			if(board != null && board.getWriter().equalsIgnoreCase(userVo.getId())) {
				boardDao.delBoard(num);
			}
		}
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) {
	    PageMaker pm = new PageMaker();
	    int totalCount = boardDao.getTotalCount(cri);
	    pm.setCriteria(cri);
	    pm.setTotalCount(totalCount);
		return pm;
	}

	@Override
	public int updateLike(Integer num, String id) {
		int boNum = num;
		if(boardDao.isLike(boNum, id) == 0){
			boardDao.insertLike(boNum, id);
		}else {
			return -1;
		}
		BoardVo board = boardDao.getBoard(num);
		boardDao.updateLike(board);
		board = boardDao.getBoard(num);
		return board.getLike();
	}
}
