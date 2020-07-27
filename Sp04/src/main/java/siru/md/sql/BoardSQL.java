package siru.md.sql;

public class BoardSQL {
	public static final String LIST = "select * from BOARD order by SEQ desc";
	public static final String DELETE = "delete from BOARD where SEQ=?";
	public static final String WRITE = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	public static final String UPDATE = "update BOARD set writer=?, email=?, subject=?, content=? where SEQ = ?";
	public static final String LIST_PAGE = "select * from (select ROWNUM rnum, aa.* from (select * from BOARD order by SEQ desc) aa) where rnum>? and rnum<=?";
	public static final String COUNT= "select COUNT(SEQ) from BOARD";
}
