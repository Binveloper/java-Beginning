package siru.md.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siru.md.dao.AddressDao;
import siru.md.dao.BoardDao;
import siru.md.domain.Board;
import siru.md.vo.ListResult;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public Board contentsS(long seq) {
		// TODO Auto-generated method stub
		return boardDao.contents(seq);
	}

	@Override
	public boolean insertS(Board board) {
		// TODO Auto-generated method stub
		return boardDao.insert(board);
	}

	@Override
	public void deleteS(long seq) {
		// TODO Auto-generated method stub
		boardDao.delete(seq);
	}

	@Override
	public boolean updateS(Board board) {
		// TODO Auto-generated method stub
		return boardDao.update(board);
	}
	
	@Override
	public ListResult getListResult(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		ArrayList<Board> list = boardDao.list(currentPage, pageSize);
		long totalCount = boardDao.getTotalCount();
		long totalPageCount = totalCount/pageSize; 
		if(totalCount%pageSize != 0) totalPageCount++;
		

		return new ListResult(currentPage, totalCount, pageSize, list, totalPageCount);
	}
}
