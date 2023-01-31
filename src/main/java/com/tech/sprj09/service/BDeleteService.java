package com.tech.sprj09.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;


@Service
public class BDeleteService implements BServiceInter{

	private SqlSession sqlSession;
	
	public BDeleteService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	
	@Override
	public void execute(Model model) {
		System.out.println(">>>BDeleteService");
		
//		model에서 request를 풀기
//		model을 Map로 변환
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest)map.get("request");
		
		
		String bid=request.getParameter("bid");
		IDao dao=sqlSession.getMapper(IDao.class);
//		BoardDao dao=new BoardDao();
		
		//컨디션체그
		boolean delflag=delCondition(bid);
		
		if (delflag) {
			dao.delete(bid);
			System.out.println("삭제성공");
			System.out.println("삭제성공후flag : "+delflag);
		}else {			
			System.out.println("삭제실패");
			System.out.println("삭제실패후flag : "+delflag);
		}
		
	}
	public boolean delCondition(String bid) {
//		선택글에 자식글있는지 없는지 판정
		System.out.println("===>>>BDeleteService delcondition()====");
		IDao dao=sqlSession.getMapper(IDao.class);
		boolean delflag=true;
		BoardDto dto1=dao.contentview(bid);
		ArrayList<BoardDto> sonList=dao.sonReply(dto1.getBgroup());
		
		for (BoardDto dto2 : sonList) {
			if(dto1.getBgroup()==dto2.getBgroup() &&
					dto1.getBstep()<dto2.getBstep() &&
					dto1.getBindent()<dto2.getBindent()) {
				delflag=false;
			}
		}

		return delflag;
	}
	

}
