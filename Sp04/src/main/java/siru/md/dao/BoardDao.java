package siru.md.dao;

import java.util.ArrayList;

import siru.md.domain.Board;
import siru.md.vo.ListResult;


public interface BoardDao {
	ArrayList<Board> list(int currentPage, int pageSize);
	long getTotalCount();
	Board contents(long seq);
	boolean insert(Board board);
	void delete(long seq);
	boolean update(Board board);
}
