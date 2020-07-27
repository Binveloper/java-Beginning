package siru.md.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyBoardVo {
	private int pageCnt;		// 출력할 페이지번호 갯수 (변경해볼것)
	private int index;			// 출력할 페이지번호
	private long pageStartNum;	// 출력할 페이지 시작 번호
	private int listCnt;		// 출력할 리스트 갯수
	private long total;			// 리스트 총 갯수	
	
	{
		pageCnt = 10; 
		index = 0;
		pageStartNum = 1;
		listCnt = 5;
	}
	
	public int getStart() {
		return index*listCnt+1;
	}
	public int getLast() {
		return (index*listCnt)+listCnt;
	}
	public long getPageLastNum() {
		long remainListCnt = total-listCnt*(pageStartNum-1);
		long remainPageCnt = remainListCnt/listCnt;
		if(remainListCnt%listCnt != 0) {
			remainPageCnt++;
		}
		long pageLastNum = 0L;
		if(remainListCnt <= listCnt) {
			pageLastNum = pageStartNum;
		}else if(remainPageCnt <= pageCnt) {
			pageLastNum = remainPageCnt+pageStartNum-1;
		}else {
			pageLastNum = pageCnt+pageStartNum-1;
		}
		return pageLastNum;
	}
	public boolean getLastChk() {
		int n = (int)Math.ceil((double)total/listCnt);
		return getPageLastNum()==n ? false : n==0 ? false : true;
	}
	
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public long getPageStartNum() {
		return pageStartNum;
	}
	public void setPageStartNum(long pageStartNum) {
		this.pageStartNum = pageStartNum;
	}
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "PagingVo [pageCnt=" + pageCnt + ", index=" + index + ", pageStartNum=" + pageStartNum + ", listCnt="
				+ listCnt + ", total=" + total + "]";
	}
}
