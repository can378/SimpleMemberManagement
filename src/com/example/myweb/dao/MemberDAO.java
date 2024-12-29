package com.example.myweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.example.myweb.model.MemberVO;

public class MemberDAO {
	private DataSource ds;
	
	public MemberDAO()
	{
		try {
			Context ctx=new InitialContext();
			ds=(DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void insert(MemberVO member)
	{
		Connection con=null;
		try {
			 con = ds.getConnection(); // DataSource에서 Connection 가져오기
             String sql = "INSERT INTO member (userid, name, password, email, address) VALUES (?, ?, ?, ?, ?)";
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.setString(1, member.getUserid());
             pstmt.setString(2, member.getName());
             pstmt.setString(3, member.getPassword());
             pstmt.setString(4, member.getEmail());
             pstmt.setString(5, member.getAddress());
	            pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		finally {
			
		}
	}
	 
}
