package kr.green.spring.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.spring.vo.BoardVo;
import pagination.Criteria;

public interface BoardDao {

	ArrayList<BoardVo> getBoardlist(@Param("cri")Criteria cri);

	BoardVo getBoard(@Param("num")Integer num);

	void increaseViews(@Param("num")Integer num);

	void registerBoard(@Param("board")BoardVo board);

	void updateBoard(@Param("board")BoardVo board);

	void delBoard(@Param("num")Integer num);

	int getTotalCount(@Param("cri")Criteria cri);

	void insertLike(@Param("boNum")int boNum, @Param("id")String id);

	void updateLike(@Param("board")BoardVo board);

	int isLike(@Param("boNum")int boNum, @Param("id")String id);
	
}
