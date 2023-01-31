package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

@Service
public class BReplyService implements BServiceInter{
	private SqlSession sqlSession;
	
	public BReplyService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BReplyService");
		
//		model에서 request를 풀기
//		model을 Map로 변환
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest)map.get("request");
				
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		String bgroup=request.getParameter("bgroup");
		String bstep=request.getParameter("bstep");
		String bindent=request.getParameter("bindent");	
		
//		db에 연결해서 sql write는 dao에서 처리
//		BoardDao dao=new BoardDao();
		IDao dao=sqlSession.getMapper(IDao.class);
		int cnt=dao.replyShape(bgroup,bstep);
		
		dao.reply(bid,bname,btitle,bcontent,
				bgroup,bstep,bindent);

		
	}

}
