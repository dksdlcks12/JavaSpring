package kr.green.test.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.green.test.pagination.Criteria;
import kr.green.test.vo.BoardVo;

public interface BoardDao {

	ArrayList<BoardVo> getBoard(@Param("cri")Criteria cri);

	int getTotalCount(@Param("cri")Criteria cri);

}
