package kr.green.springtest.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.vo.BoardVo;

public interface BoardDao {

	public ArrayList<BoardVo> getList(@Param("cri")Criteria cri);

	public BoardVo getBoard(@Param("num")Integer num);

	public void increaseViews(@Param("num")Integer num);

	public void setBoard(@Param("board")BoardVo board);

	public void updateBoard(@Param("board")BoardVo board);

	public void modifyBoard(@Param("board")BoardVo board);

	public void delBoard(@Param("num")Integer num);

	public int getTotalCount(@Param("cri")Criteria cri);
}
