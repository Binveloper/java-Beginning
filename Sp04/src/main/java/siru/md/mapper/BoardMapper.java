package siru.md.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import siru.md.domain.Board;
import siru.md.domain.BoardVo;

public interface BoardMapper {
	ArrayList<Board> list(HashMap<String, Object> paging);
	long getTotalCount();
	Board contents(long seq);
	boolean insert(Board board);
	void delete(long seq);
	boolean update(Board board);
	List<Board> selectByWriter(BoardVo boardVo);	
}
