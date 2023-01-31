package com.tech.sprj09.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.tech.sprj09.db.DBCon;
import com.tech.sprj09.dto.BoardDto;

public class BoardDao {
	public ArrayList<BoardDto> list() {
		System.out.println("list()");
		// 오라클에 접속해서 글전체를 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();

		try {
			con = DBCon.getConnection();
			String sql = "select bid,bname,btitle,"
					+ "bcontent,bdate,bhit,bgroup,"
					+ "bstep,bindent from mboard "
					+ "order by bgroup desc,bstep asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
//			rs.next();
//			System.out.println(rs.getString("btitle"));
			while (rs.next()) {
				// rs에서 각필드값을 하나씩 가져와서 BoardDto에 담아서 리스트에 담기
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");// sql페키지 임포트

				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");

				// 생성자를 통해 BoardDto에 전달하기
				BoardDto dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
				// 리스트에 담기
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dtos;
	}

	public void write(String bname, String btitle, String bcontent) {
		System.out.println("write()");
		// 오라클에 접속해서 글쓰기
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBCon.getConnection();
			String sql = "insert into mboard(bid,bname,btitle,bcontent," + "bdate,bhit,bgroup,bstep,bindent) "
					+ "values(mboard_seq.nextval,?,?,?," + "sysdate,0,mboard_seq.currval,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);

			int rn = pstmt.executeUpdate();
			System.out.println("rn : >>>>>>>> " + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}
	public void upHit(String gbid) {
		System.out.println("upHit()");
		// 오라클에 접속해서 글전체를 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCon.getConnection();
			String sql = "update mboard set bhit=bhit+1"
					+ " where bid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(gbid));
			int rn=pstmt.executeUpdate();
			if (rn>0) {
				System.out.println("조회수 증가");
			}else{
				System.out.println("조회수 증가 안됨");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	
	public BoardDto contentview(String gbid) {
		System.out.println("contentview()");

		// 조회수 증가
		upHit(gbid);
		
		
		// 오라클에 접속해서 글전체를 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto dto = null;

		try {
			con = DBCon.getConnection();
			String sql = "select bid,bname,btitle,bcontent," + "bdate,bhit,bgroup,bstep,bindent "
					+ "from mboard where bid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(gbid));
			rs = pstmt.executeQuery();
			rs.next();
			// System.out.println(">>>>>>> :"+rs.getString("btitle"));
//			BoardDto에 담아주기
			// rs에서 각필드값을 하나씩 가져와서 BoardDto에 담아서 리스트에 담기
			int bid = rs.getInt("bid");
			String bname = rs.getString("bname");
			String btitle = rs.getString("btitle");
			String bcontent = rs.getString("bcontent");
			Timestamp bdate = rs.getTimestamp("bdate");// sql페키지 임포트

			int bhit = rs.getInt("bhit");
			int bgroup = rs.getInt("bgroup");
			int bstep = rs.getInt("bstep");
			int bindent = rs.getInt("bindent");

			dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}

	public BoardDto contentupdate(String gbid) {
		System.out.println("contentupdate()");

		// 조회수 증가
		//upHit(gbid);
		
		
		// 오라클에 접속해서 글전체를 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto dto = null;

		try {
			con = DBCon.getConnection();
			String sql = "select bid,bname,btitle,bcontent," 
					+ "bdate,bhit,bgroup,bstep,bindent "
					+ "from mboard where bid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(gbid));
			rs = pstmt.executeQuery();
			rs.next();
			// System.out.println(">>>>>>> :"+rs.getString("btitle"));
//			BoardDto에 담아주기
			// rs에서 각필드값을 하나씩 가져와서 BoardDto에 담아서 리스트에 담기
			int bid = rs.getInt("bid");
			String bname = rs.getString("bname");
			String btitle = rs.getString("btitle");
			String bcontent = rs.getString("bcontent");
			Timestamp bdate = rs.getTimestamp("bdate");// sql페키지 임포트

			int bhit = rs.getInt("bhit");
			int bgroup = rs.getInt("bgroup");
			int bstep = rs.getInt("bstep");
			int bindent = rs.getInt("bindent");

			dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}

	public void modify(String bid, String bname, String btitle, String bcontent) {
		System.out.println("modify()");
		// 오라클에 접속해서 글전체를 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCon.getConnection();
			String sql = "update mboard set bname=?," 
			+ "btitle=?, bcontent=? " + "where bid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bid));

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	public void delete(String bid) {
		System.out.println("delete()");
		// 오라클에 접속해서 글삭제
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCon.getConnection();
			String sql = "delete from mboard where bid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bid));
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public BoardDto replyview(String gbid) {
		System.out.println("replyview()");
		
		// 오라클에 접속해서 글전체를 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto dto = null;

		try {
			con = DBCon.getConnection();
			String sql = "select bid,bname,btitle,bcontent," 
					+ "bdate,bhit,bgroup,bstep,bindent "
					+ "from mboard where bid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(gbid));
			rs = pstmt.executeQuery();
			rs.next();
			// System.out.println(">>>>>>> :"+rs.getString("btitle"));
//			BoardDto에 담아주기
			// rs에서 각필드값을 하나씩 가져와서 BoardDto에 담아서 리스트에 담기
			int bid = rs.getInt("bid");
			String bname = rs.getString("bname");
			String btitle = rs.getString("btitle");
			String bcontent = rs.getString("bcontent");
			Timestamp bdate = rs.getTimestamp("bdate");// sql페키지 임포트

			int bhit = rs.getInt("bhit");
			int bgroup = rs.getInt("bgroup");
			int bstep = rs.getInt("bstep");
			int bindent = rs.getInt("bindent");

			dto = new BoardDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	public int replyShape(String bgroup,String bstep) {
		//update
		System.out.println("replyShape()");
		// 오라클에 접속해서 글전체를 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		int rn1=0;
		try {
			con = DBCon.getConnection();
			String sql = "update mboard " + 
					"set bstep=bstep+1 " + 
					"where bgroup=? and bstep>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(bgroup));
			pstmt.setInt(2, Integer.parseInt(bstep));
			
			rn1=pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return rn1;	
	}
	
	public void reply(String bid,String bname,
			String btitle,String bcontent,
			String bgroup,String bstep,
			String bindent) {
		System.out.println("reply()");
		
		//현재스텝보다 더큰 스텝이있다면 1씩 증가
		int rn1=replyShape(bgroup,bstep);
		System.out.println("rn111 : >>>>>>>> " + rn1);
		int rn2=0;
		
		// 오라클에 접속해서 답글쓰기
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBCon.getConnection();
			//자동커밋을 수동커밋으로
			con.setAutoCommit(false);
			
			String sql = "insert into mboard(bid,bname,btitle,bcontent,"
						+ "bdate,bgroup,bstep,bindent) "
						+ "values(mboard_seq.nextval,?,?,?," 
						+ "sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bgroup));
			pstmt.setInt(5, Integer.parseInt(bstep)+1);
			pstmt.setInt(6, Integer.parseInt(bindent)+1);
			
			rn2 = pstmt.executeUpdate();
			System.out.println("rn2222222 : >>>>>>>> " + rn2);
			if(rn1>=0 && rn2>=1) {
				con.commit();
				System.out.println("commmmmmmmmit");
			}else {
				con.rollback();
				System.out.println("rollbackkkkkkk");
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
	}
}
