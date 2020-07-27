package siru.md.service;

import java.util.ArrayList;

import siru.md.domain.Board;
import siru.md.vo.ListResult;

public interface BoardService {
	ListResult getListResult(int currentPage, int pageSize);
	Board contentsS(long seq);
	boolean insertS(Board board);
	void deleteS(long seq);
	boolean updateS(Board board);
}
