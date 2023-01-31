package com.tech.sprj09.dao;

import java.util.ArrayList;

import com.tech.sprj09.dto.BoardDto;

public interface IDao {
	public ArrayList<BoardDto> list(int start,int end,String sk,String selNum);
	
	public void write(String bname, String btitle, String bcontent,String fname);
	
	
	public BoardDto contentview(String gbid);
	public void upHit(String gbid);
	public BoardDto contentupdate(String gbid);
	public void modify(String bid, String bname, 
			String btitle, String bcontent);
	public void delete(String bid);
	public BoardDto replyview(String gbid);
	public void reply(String bid,String bname,
			String btitle,String bcontent,
			String bgroup,String bstep,
			String bindent);
	public int replyShape(String bgroup,String bstep);
//	public int selectBoardTotCount();
	public int selectBoardTotCount1(String searchKeyword);
	public int selectBoardTotCount2(String searchKeyword);
	public int selectBoardTotCount3(String searchKeyword);
	public int selectBoardTotCount4(String searchKeyword);
	
	public ArrayList<BoardDto> sonReply(int bgroup);
	
}
