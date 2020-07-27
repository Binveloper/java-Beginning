package siru.md.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Board;
import siru.md.sql.BoardSQL;

@Log4j
@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private DataSource dataSource;
	
	public ArrayList<Board> list(int currentPage, int pageSize){//1,3//2,3
		ArrayList<Board> list = new ArrayList<Board>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	    String sql = BoardSQL.LIST_PAGE;
	    //select * from (select ROWNUM rnum, aa.* from (select * from BOARD order by SEQ desc) aa) where rnum>? and rnum<=?
	    int startRow = (currentPage-1)*pageSize;//0//3
	    int endRow = currentPage*pageSize;//3//6
		try{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Long seq = rs.getLong("SEQ");
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				Date rdate = rs.getDate("RDATE");
				
				list.add(new Board(seq, writer, email, subject, content, rdate));
			
				}
				return list;
			}catch(SQLException se){
				return null;
			}finally{
				try{
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(con != null)  con.close();
				}catch(SQLException se){}
			}  
	}
 	public long getTotalCount() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
	    String sql = BoardSQL.COUNT;
	    
	    try{
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				long count = rs.getLong(1);
				return count;
			}else {
			return -1L;
			}
		}catch(SQLException se){
			return -1L;
		}finally{
				try{
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con != null)  con.close();
				}catch(SQLException se){}
			}  
 }
	

 	@Override
    public Board contents(long seq) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from BOARD where SEQ ="+seq+" order by SEQ desc";
        try{
            con = dataSource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                seq = rs.getInt(1);
                String writer = rs.getString(2);
                String email = rs.getString(3);
                String subject = rs.getString(4);
                String content = rs.getString(5);
                Date rdate = rs.getDate(6);
                return new Board(seq, writer, email, subject, content, rdate);
            }else {
                return null;
            }
        }catch(SQLException se){
            log.info("컨텐트 로딩 실패 : "+se);
            return null;
        }finally{
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            }catch(SQLException se){}
        }
    }

	@Override
	public boolean insert(Board board) {
		String sql= BoardSQL.WRITE;
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException se){
			log.info("BoardDaoImpl insert() se: " + se);
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}

	}

	@Override
	public void delete(long seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = BoardSQL.DELETE;

		try{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}

	}

	@Override
	public boolean update(Board board) {
		String sql= BoardSQL.UPDATE;
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setLong(5, board.getSeq());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException se){
			log.info("BoardDaoImpl update() se: " + se);
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}

}
