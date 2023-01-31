package com.tech.sprj09.service;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;

@Service
public class BDownLoadService implements BServiceInter{
	private SqlSession sqlSession;
	
	public BDownLoadService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BDownLoadService");
		
//		model에서 request를 풀기
//		model을 Map로 변환
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest)map.get("request");
		HttpServletResponse response=
				(HttpServletResponse)map.get("response");		
		
		String path=request.getParameter("p");
		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
//		BoardDao dao=new BoardDao();
		IDao dao=sqlSession.getMapper(IDao.class);
		
		try {
//			down
//			header에 첨부라는 신호전달
			response.setHeader("Content-Disposition",
					"Attachment;filename="+URLEncoder.encode(fname,"utf-8"));
			
			String attachPath="resources\\upload";
			String realPath=request.getSession().getServletContext().getRealPath(attachPath)+"\\"+fname;
			
//			stream연결
			FileInputStream fin=new FileInputStream(realPath);
			ServletOutputStream sout=response.getOutputStream();
			
			byte[] buf=new byte[1024];
			int size=0;
			while((size=fin.read(buf,0,1024))!=-1) {
				sout.write(buf,0,size);
			}
			fin.close();
			sout.close();

		} catch (Exception e) {
			System.out.println("down errrorrrrrr");
//			e.printStackTrace();
			
		}
		
	}

}
