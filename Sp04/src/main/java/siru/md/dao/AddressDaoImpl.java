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
import siru.md.domain.Address;
import siru.md.sql.AddressSQL;

@Log4j
@Repository //스프링 어노테이션.. 저장소..
public class AddressDaoImpl implements AddressDao {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Address> list() {
		List<Address> list = new ArrayList<Address>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = AddressSQL.SEL;
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				long seq = rs.getLong(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				Date rdate = rs.getDate(4);
				
				list.add(new Address(seq, name, addr, rdate));
			}
		}catch(SQLException se) {
			
		}finally{
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException se) {}
		}
		return list;
		
	}

	@Override
	public void insert(Address address) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = AddressSQL.IN;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address.getName());
			pstmt.setString(2, address.getAddr());
			pstmt.executeUpdate();
		}catch(SQLException se) {
			log.info("AddressDaoImpl insert() se: " + se);
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException se) {}
		}
		
	}

	@Override
	public void delete(long seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = AddressSQL.DEL;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se) {
			log.info("AddressDaoImpl delete() se: " + se);
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException se) {}
		}
	}

}
