package siru.md.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siru.md.dao.AddressDao;
import siru.md.dao.BoardDao;
import siru.md.domain.Board;
import siru.md.mapper.BoardMapper;
import siru.md.vo.ListResult;

@Service("BoardServiceImpl1")
public class BoardServiceImpl1 implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public Board contentsS(long seq) {
		// TODO Auto-generated method stub
		return boardMapper.contents(seq);
	}

	@Override
	public boolean insertS(Board board) {
		// TODO Auto-generated method stub
		return boardMapper.insert(board);
	}

	@Override
	public void deleteS(long seq) {
		// TODO Auto-generated method stub
		boardMapper.delete(seq);
	}

	@Override
	public boolean updateS(Board board) {
		// TODO Auto-generated method stub
		return boardMapper.update(board);
	}
	
	@Override
	public ListResult getListResult(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> paging = new HashMap<String, Object>();
		int startRow = (currentPage-1)*pageSize;
	    int endRow = currentPage*pageSize;
	    paging.put("startNum", startRow);
	    paging.put("endNum", endRow);
		ArrayList<Board> list = boardMapper.list(paging);
		long totalCount = boardMapper.getTotalCount();
		long totalPageCount = totalCount/pageSize; 
		if(totalCount%pageSize != 0) totalPageCount++;
		

		return new ListResult(currentPage, totalCount, pageSize, list, totalPageCount);
	}
}
