package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;

@Service
public class BModifyService implements BServiceInter{
	private SqlSession sqlSession;
	
	public BModifyService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BModifyService");
		
//		model에서 request를 풀기
//		model을 Map로 변환
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest)map.get("request");
		
		
		String bid=request.getParameter("bid");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
		
//		BoardDao dao=new BoardDao();
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.modify(bid,bname,btitle,bcontent);
		
	}

}
