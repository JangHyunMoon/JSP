package com.rclass.member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.rclass.member.vo.Member;

public class MemberDao {

	private Properties prop = new Properties();

	public MemberDao() {
		try {
			String fileName = MemberDao.class.getResource("./member-query.properties").getPath();
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member selectOne(Connection conn, Member m) {
		Member result = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 아이디, 비밀번호 체크를 컨트롤러에서 하고 여기서는 값만 받음
				result = new Member();
				result.setUserId(rs.getString("userid"));
				result.setPassword(rs.getString("password"));
				result.setUserName(rs.getString("username"));
				result.setGender(rs.getString("gender"));
				result.setAge(rs.getInt("age"));
				result.setEmail(rs.getString("email"));
				result.setPhone(rs.getString("phone"));
				result.setAddress(rs.getString("address"));
				result.setHobby(rs.getString("hobby"));
				result.setEnrollDate(rs.getDate("enrolldate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
}
