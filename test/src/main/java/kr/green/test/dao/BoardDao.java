package kr.green.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVo;
import kr.green.test.vo.UserVo;

public interface BoardDao {

	ArrayList<BoardVo> getBoard(@Param("cri")Criteria cri);

	int getTotalCount(@Param("cri")Criteria cri);

	void insertBoard(@Param("board")BoardVo board);

	BoardVo viewBoard(@Param("num")Integer num);

	void updateBoard(@Param("board")BoardVo board);

	void deleteBoard(@Param("boardNum")int boardNum);

	void increaseView(@Param("num")int num);

	int isLike(@Param("num")int num, @Param("user")UserVo user);

	void insertLike(@Param("num")int num, @Param("user")UserVo user);

	void updateLike(@Param("num")int num);

}
