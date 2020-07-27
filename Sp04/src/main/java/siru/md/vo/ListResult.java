package siru.md.vo;

import java.sql.Date;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import siru.md.domain.Board;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResult {
	private int currentPage;
	private long totalCount;
	private int pageSize;
	private ArrayList<Board> list;
	private long totalPageCount;
	
}
