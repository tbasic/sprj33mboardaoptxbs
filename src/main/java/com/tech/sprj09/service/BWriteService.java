package com.tech.sprj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj09.dao.BoardDao;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;


@Service
public class BWriteService implements BServiceInter{

	
	private SqlSession sqlSession;
	
	public BWriteService(SqlSession sqlSession) {
		this.sqlSession=sqlSession;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BWriteService");
		
//		model에서 request를 풀기
//		model을 Map로 변환
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest)map.get("request");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
		
//		db에 연결해서 sql write는 dao에서 처리
		
//		upload code
		String attachPath="resources\\upload\\";
		String uploadPath=request.getSession().getServletContext().getRealPath("/");
		System.out.println("uploadpathhhhh:"+uploadPath);
		
		//String path=uploadPath+attachPath;
		
		String path="C:\\2022spring\\springwork1\\sprj28mboardremypgsupmvc\\src\\main\\webapp\\resources\\upload";
		
		MultipartRequest req=null;
		try {
			req=new MultipartRequest(request, path, 1024*1024*20, "utf-8",
					new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String bname=req.getParameter("bname");
		String btitle=req.getParameter("btitle");
		String bcontent=req.getParameter("bcontent");
		String fname=req.getFilesystemName("file");
		
		if(fname==null)
			fname="";
		IDao dao=sqlSession.getMapper(IDao.class);
		dao.write(bname,btitle,bcontent,fname);
		
		
//		BoardDao dao=new BoardDao();
//		dao.write(bname,btitle,bcontent);
		
	}

}
