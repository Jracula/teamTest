package com.litbooks.ooo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.litbooks.ooo.vo.OneOnOne;
import com.litbooks.ooo.vo.OneOnOneComment;
import com.litbooks.qna.model.vo.Qna;

import common.JDBCTemplate;

public class OneOnOneDao {

	public ArrayList<OneOnOne> selectOneOnOneList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OneOnOne> list = new ArrayList<OneOnOne>();
		String query = "select * from (select rownum as rnum, n.* from(select o_no, o_title, o_writer, o_date from oneonone order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OneOnOne o = new OneOnOne();
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoWriter(rset.getString("o_writer"));
				o.setoRegDate(rset.getString("o_date"));
				list.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int insertOneOnOne(Connection conn, OneOnOne o) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into oneonone values(ooo_no_seq.nextval, ?, ?, ?, ?, ?, 0, to_char(sysdate, 'yyyy-mm-dd'),null,null)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, o.getoMemberNo());
			pstmt.setString(2, o.getoWriter());
			pstmt.setString(3, o.getoTitle());
			pstmt.setString(4, o.getoContent());
			pstmt.setInt(5, o.getOflag());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateReadCount(Connection conn, int oNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update oneonone set o_read_count = o_read_count+1 where o_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, oNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public OneOnOne selectOneNotice(Connection conn, int oNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OneOnOne o = null;
		
		String query = "select * from oneonone where o_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, oNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new OneOnOne();
				o.setoContent(rset.getString("o_content"));
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoWriter(rset.getString("o_writer"));
				o.setOflag(rset.getInt("o_flag"));
				o.setoRegDate(rset.getString("o_date"));
				o.setoMemberNo(rset.getInt("o_member_no"));
				o.setoRegDate(rset.getString("o_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return o;
		
		
	}

	public ArrayList<OneOnOne> selectOneOnOneList(Connection conn, int start1, int end1, String qMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OneOnOne> list = new ArrayList<OneOnOne>();
		String query = "select * from (select rownum as rnum, n.* from(select o_no, o_title, o_content, o_writer, o_date, o_flag "
				+ "from oneonone where o_member_no=? order by 1 desc)n) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qMemberNo);
			pstmt.setInt(2, start1);
			pstmt.setInt(3, end1);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OneOnOne o = new OneOnOne();
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoContent(rset.getString("o_content"));
				o.setoRegDate(rset.getString("o_date"));
				o.setoWriter(rset.getString("o_writer"));
				o.setOflag(rset.getInt("o_flag"));
				list.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		

		return list;
	}

	public int selectOneOnOneCount(Connection conn, String qMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from oneonone where o_member_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qMemberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return totalCount;
	}

	public int updateOneOnOne(Connection conn, OneOnOne o) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update oneonone set o_title = ?, o_content=?, filename = ?, filepath = ? where o_no=? and o_member_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, o.getoTitle());
			pstmt.setString(2, o.getoContent());
			pstmt.setString(3, o.getFileName());
			pstmt.setString(4, o.getFilepath());
			pstmt.setInt(5, o.getoNo());
			pstmt.setInt(6, o.getoMemberNo());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int updateReadCount(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	public OneOnOne selectOneNotice(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OneOnOne o = null;
		
		String query = "select * from oneonone";
		
		try {
			pstmt = conn.prepareStatement(query); 
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new OneOnOne();
				o.setoContent(rset.getString("o_content"));
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoWriter(rset.getString("o_writer"));
				o.setOflag(rset.getInt("o_flag"));
				o.setoRegDate(rset.getString("o_date"));
				o.setoMemberNo(rset.getInt("o_member_no"));
				o.setoRegDate(rset.getString("o_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return o;
		
		
	}

	public int selectOneOnOneCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		
		String query = "select count(*) as cnt from oneonone";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return totalCount;
	}

	public OneOnOne selectOneNotice(Connection conn, int oNo, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OneOnOne o = null;
		
		String query = "select * from oneonone where o_no = ? and o_member_no=?";
		
		try {
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, oNo);
			pstmt.setInt(2, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new OneOnOne();
				o.setoContent(rset.getString("o_content"));
				o.setoNo(rset.getInt("o_no"));
				o.setoTitle(rset.getString("o_title"));
				o.setoWriter(rset.getString("o_writer"));
				o.setOflag(rset.getInt("o_flag"));
				o.setoRegDate(rset.getString("o_date"));
				o.setoMemberNo(rset.getInt("o_member_no"));
				o.setoRegDate(rset.getString("o_date"));
				o.setFileName(rset.getString("filename"));
				o.setFilepath(rset.getString("filepath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return o;
		
	}

	public ArrayList<OneOnOneComment> selectNoticeComment(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OneOnOneComment> list = new ArrayList<OneOnOneComment>();
		String query = "select * from oneonone_comment where oo_ref = ? and oo_ref is null order by 1";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				OneOnOneComment oc = new OneOnOneComment();
				oc.setOo_content(rset.getString("oo_content"));
				oc.setOo_date(rset.getString("oo_date"));
				oc.setOo_no(rset.getInt("oo_no"));
				oc.setOo_writer(rset.getString("oo_writer"));
				oc.setOo_ref(rset.getInt("oo_ref"));
				list.add(oc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}		
		return list;
	}

	public int insertOneOnOneCommnet(Connection conn, OneOnOneComment oc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into oneonone_comment values(oc_no_seq.nextval, ?, ?, ?, to_char(sysdate, 'yyyy-mm-dd'))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, oc.getOo_writer());
			pstmt.setString(2, oc.getOo_content());
			pstmt.setInt(3, oc.getOo_ref());
			
			//NcRef에 0을 넣어줬는데 insert될 때 references조건을 만족하지X
			//댓글의 경우 nc_no == 0에 해당하는 값이 없으므로 에러발생
			//댓글일 경우 null / 대댓글의경우 해당번호를 넣어줘야함
			/*
			 * if(oc.getOo_ref() == 0) { pstmt.setString(4, null); }else { pstmt.setInt(4,
			 * oc.getNcRef()); }
			 */
			//3항연산자
			//pstmt.setString(4, (nc.getNcRef()==0)?null:String.valueOf(nc.getNcRef()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int delectOneOnOne(Connection conn, int oNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from oneonone where o_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, oNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
