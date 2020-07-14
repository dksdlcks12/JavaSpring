package kr.green.springtest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.BoardDao;
import kr.green.springtest.vo.BoardVo;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Override
	public ArrayList<BoardVo> getList() {
		return boardDao.getList();
	}

	@Override
	public BoardVo getBoard(Integer num) {
		if(num==null) {
			return null;
		}
		return boardDao.getBoard(num);
	}

	@Override
	public void setBoard(BoardVo board) {
		boardDao.setBoard(board);		
	}

	@Override
	public BoardVo viewBoard(Integer num) {
		BoardVo board = getBoard(num);
		if(num!=null) {
			board.setViews(board.getViews()+1);
			boardDao.updateBoard(board);
		}
		return board;
	}

	@Override
	public void modifyBoard(BoardVo board) {
		boardDao.modifyBoard(board);		
	}

	@Override
	public void delBoard(Integer num) {
		boardDao.delBoard(num);		
	}
}
