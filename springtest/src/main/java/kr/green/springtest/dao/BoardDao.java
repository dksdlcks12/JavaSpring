package kr.green.springtest.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.vo.BoardVo;

public interface BoardDao {

	ArrayList<BoardVo> getList(@Param("cri")Criteria cri);

	BoardVo getBoard(@Param("num")Integer num);

	void increaseViews(@Param("num")Integer num);

	void setBoard(@Param("board")BoardVo board);

	void updateBoard(@Param("board")BoardVo board);

	void modifyBoard(@Param("board")BoardVo board);

	void delBoard(@Param("num")Integer num);

	int getTotalCount(@Param("cri")Criteria cri);
}
