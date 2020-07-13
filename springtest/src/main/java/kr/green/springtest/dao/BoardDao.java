package kr.green.springtest.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.springtest.vo.BoardVo;

public interface BoardDao {

	ArrayList<BoardVo> getList();

	BoardVo getBoard(@Param("num")Integer num);

	void increaseViews(@Param("num")Integer num);
}