package kr.green.springtest.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.BoardDao;
import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.pagination.PageMaker;
import kr.green.springtest.vo.BoardVo;
import kr.green.springtest.vo.UserVo;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private UserService userservice;

	@Override
	public ArrayList<BoardVo> getList(Criteria cri) {
		return boardDao.getList(cri);
	}

	@Override
	public BoardVo getBoard(Integer num) {
		if(num==null) {
			return null;
		}
		return boardDao.getBoard(num);
	}

	@Override
	public void setBoard(BoardVo board, HttpServletRequest request) {
		UserVo user = userservice.getUser(request);
		if(user == null) {
			return;
		}
		boardDao.setBoard(board);
	}

	@Override
	public BoardVo viewBoard(Integer num, HttpServletRequest request) {
		BoardVo board = getBoard(num);
		UserVo user = null;
		if(request!=null) {
			user = userservice.getUser(request);
		}
		if(num!=null && (user==null || !board.getWriter().equals(user.getId()))) {
			board.setViews(board.getViews()+1);
			boardDao.updateBoard(board);
		}
		return board;
	}

	@Override
	public void modifyBoard(BoardVo board, UserVo user) {
		board.setWriter(user.getId());
		boardDao.modifyBoard(board);
	}

	@Override
	public void delBoard(Integer num) {
		boardDao.delBoard(num);		
	}

	@Override
	public PageMaker getPage(Criteria cri) {
		PageMaker pm = new PageMaker();
		int totalcount = boardDao.getTotalCount(cri);
		pm.setCriteria(cri);
		pm.setTotalCount(totalcount);
		return pm;
	}
}
