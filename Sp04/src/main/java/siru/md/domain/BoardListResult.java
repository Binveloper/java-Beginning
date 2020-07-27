package siru.md.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardListResult {
	private int page;
	private int pageSize;
	private long totalCount;
	private List<Board> list;
	private long totalPageCount;

	public BoardListResult(int page, int pageSize, long totalCount, List<Board> list) {
		this.page = page;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.list = list;
		this.totalPageCount = calTotalPageCount();
	}
	private long calTotalPageCount() {
		long tpc = totalCount/pageSize; 
		if(totalCount%pageSize != 0) tpc++;
		
		return tpc;
	}
}
