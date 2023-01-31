package com.tech.sprj09.controller;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tech.sprj09.dao.IDao;
import com.tech.sprj09.dto.BoardDto;
import com.tech.sprj09.service.BContentUpdateService;
import com.tech.sprj09.service.BContentViewService;
import com.tech.sprj09.service.BDeleteService;
import com.tech.sprj09.service.BDownLoadService;
import com.tech.sprj09.service.BListService;
import com.tech.sprj09.service.BModifyService;
import com.tech.sprj09.service.BReplyService;
import com.tech.sprj09.service.BReplyViewService;
import com.tech.sprj09.service.BServiceInter;
import com.tech.sprj09.service.BWriteService;
import com.tech.sprj09.vopage.SearchVO;

//@Controller
public class BController_origin {
	BServiceInter bServiceInter;
	
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//목록표현
	@RequestMapping("/list")
	public String list(HttpServletRequest request,SearchVO searchVO, Model model) {
		System.out.println("========list=======");
		//db에서 데이터 가져오기
		
		
		model.addAttribute("request",request);
		model.addAttribute("searchVO",searchVO);

		bServiceInter=new BListService(sqlSession);
		bServiceInter.execute(model);
		
//		IDao dao=sqlSession.getMapper(IDao.class);
//		
//		String[] brdtitle=request.getParameterValues("searchType");	
//		
////		if(brdtitle!=null) {//null처리
////			for (int i = 0; i < brdtitle.length; i++) {
////				System.out.println("aaa : "+brdtitle[i]);
////			}
////		}
//		
//		String btitle="";
//		String bcontent="";
//		
//		if(brdtitle!=null) {
//			for (String val : brdtitle) {
//				if (val.equals("btitle")) {
//					model.addAttribute("btitle","true");
//					btitle="btitle";
//				}else if (val.equals("bcontent")) {
//					model.addAttribute("bcontent","true");
//					bcontent="bcontent";
//				}
//			}
//		}		
////		키워드 가져오기
//		String searchKeyword=request.getParameter("sk");
//		
//		if(searchKeyword==null)
//			searchKeyword="";
//		
//		model.addAttribute("resk",searchKeyword);
//		
//		System.out.println("keyword : "+searchKeyword);
//		
//		String strPage=request.getParameter("page");
//		if (strPage==null) {
//			strPage="1";
//		}
//		System.out.println("pagggggg : "+strPage);
//		
//		int page=Integer.parseInt(strPage);
//		searchVO.setPage(page);
//		
//		//토탈 글의 갯수 구하기
////		int total=dao.selectBoardTotCount();
//		int total=0;
//		if(btitle.equals("btitle") && bcontent.equals("")) {
//			total=dao.selectBoardTotCount1(searchKeyword);
//		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
//			total=dao.selectBoardTotCount2(searchKeyword);
//		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
//			total=dao.selectBoardTotCount3(searchKeyword);
//		}else if(btitle.equals("") && bcontent.equals("")) {
//			total=dao.selectBoardTotCount4(searchKeyword);
//		}
//				
////		System.out.println("total : "+total);
//		searchVO.pageCalculate(total);
//		
//		//계산된 내용 출력
//		System.out.println("totRow : "+total);
//		System.out.println("clickpage : "+page);
//		System.out.println("pageStart : "+searchVO.getPageStart());
//		System.out.println("pageEnd : "+searchVO.getPageEnd());
//		System.out.println("pageTot : "+searchVO.getTotPage());
//		System.out.println("rowStart : "+searchVO.getRowStart());
//		System.out.println("rowEnd : "+searchVO.getRowEnd());
//	
//		int rowStart=searchVO.getRowStart();
//		int rowEnd=searchVO.getRowEnd();
//		
//		
////		ArrayList<BoardDto> list=null;
//		
//		if(btitle.equals("btitle") && bcontent.equals("")) {
////			list=dao.list(rowStart,rowEnd,searchKeyword,"1");
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"1"));
//		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
////			list=dao.list(rowStart,rowEnd,searchKeyword,"2");
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"2"));
//		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
////			list=dao.list(rowStart,rowEnd,searchKeyword,"3");
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"3"));
//		}else if(btitle.equals("") && bcontent.equals("")) {
////			list=dao.list(rowStart,rowEnd,searchKeyword,"4");
//			model.addAttribute("list",dao.list(rowStart,rowEnd,searchKeyword,"4"));
//		}
//		
//		//model.addAttribute("list",list);
//		model.addAttribute("totRowcnt",total);
//		model.addAttribute("searchVo",searchVO);
//		
		return "list";
	}
//	글쓰기폼기능
	@RequestMapping("/writeview")
	public String writeview(Model model) {
		System.out.println("========writeview=======");

		return "writeview";
	}
//	글쓰기기능
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model)
			throws Exception {
		System.out.println("========write=======");
		
//		db에 글쓰기기능		
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BWriteService(sqlSession);
		bServiceInter.execute(model);
		
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		String fname=request.getFilesystemName("file");
		
////		upload code
//		String attachPath="resources\\upload\\";
//		String uploadPath=request.getSession().getServletContext().getRealPath("/");
//		System.out.println("uploadpathhhhh:"+uploadPath);
//		
//		//String path=uploadPath+attachPath;
//		
//		String path="C:\\2022spring\\springwork1\\sprj28mboardremypgsupmvc\\src\\main\\webapp\\resources\\upload";
//		MultipartRequest req=
//				new MultipartRequest(request, path, 1024*1024*20, "utf-8",
//						new DefaultFileRenamePolicy());
//		
//		String bname=req.getParameter("bname");
//		String btitle=req.getParameter("btitle");
//		String bcontent=req.getParameter("bcontent");
//		String fname=req.getFilesystemName("file");
//		
//		if(fname==null)
//			fname="";
//		IDao dao=sqlSession.getMapper(IDao.class);
//		dao.write(bname,btitle,bcontent,fname);
//		
		
		return "redirect:list";
	}
//	상세페이지기능
	@RequestMapping("/contentview")
	public String contentview(HttpServletRequest request, Model model) {
		System.out.println("========contentview=======");
//		db에 디테일기능		
		//toss
		model.addAttribute("request",request);
		
		
		bServiceInter=new BContentViewService(sqlSession);
		bServiceInter.execute(model);
		
//		IDao dao=sqlSession.getMapper(IDao.class);
//		String bid=request.getParameter("bid");
//		
//		dao.upHit(bid);
//		
//		BoardDto dto=dao.contentview(bid);
//		model.addAttribute("content_view",dto);
		
		return "contentview";
	}
	
//	다운로드페이지기능
	@RequestMapping("/download")
	public String download(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception{
		System.out.println("========download=======");

//		String path=request.getParameter("p");
//		String fname=request.getParameter("f");
//		String bid=request.getParameter("bid");
		
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		bServiceInter=new BDownLoadService(sqlSession);
		bServiceInter.execute(model);
		
		
////		down
////		header에 첨부라는 신호전달
//		response.setHeader("Content-Disposition",
//				"Attachment;filename="+URLEncoder.encode(fname,"utf-8"));
//		
//		String attachPath="resources\\upload";
//		String realPath=request.getSession().getServletContext().getRealPath(attachPath)+"\\"+fname;
//		
////		stream연결
//		FileInputStream fin=new FileInputStream(realPath);
//		ServletOutputStream sout=response.getOutputStream();
//		
//		byte[] buf=new byte[1024];
//		int size=0;
//		while((size=fin.read(buf,0,1024))!=-1) {
//			sout.write(buf,0,size);
//		}
//		fin.close();
//		sout.close();


		return null;
		
	}
	
	
//	수정폼으로
	@RequestMapping("/contentupdate")
	public String contentupdate(HttpServletRequest request, Model model) {
		System.out.println("========contentupdate=======");
//		db에 디테일기능		
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BContentUpdateService(sqlSession);
		bServiceInter.execute(model);
		
//		String sbid=request.getParameter("bid");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		BoardDto dto=dao.contentupdate(sbid);
//		model.addAttribute("content_view",dto);
		
		return "contentupdate";
	}
//	글수정하기
	@RequestMapping(method = RequestMethod.POST,value="/modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("========modify=======");
//		db에 디테일기능		
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BModifyService(sqlSession);
		bServiceInter.execute(model);
//		String bid=request.getParameter("bid");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		
//		IDao dao=sqlSession.getMapper(IDao.class);
//		dao.modify(bid,bname,btitle,bcontent);
		
		
		return "redirect:list";
	}
//	글삭제하기
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("========delete=======");
//		db에 디테일기능		
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BDeleteService(sqlSession);
		bServiceInter.execute(model);
		
//		String bid=request.getParameter("bid");
//		IDao dao=sqlSession.getMapper(IDao.class);
//		dao.delete(bid);
		
		return "redirect:list";
	}
	
//	답변달기
	@RequestMapping("/replyview")
	public String replyview(HttpServletRequest request, Model model) {
		System.out.println("========replyview=======");
//		db에 디테일기능		
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BReplyViewService(sqlSession);
		bServiceInter.execute(model);
//		IDao dao=sqlSession.getMapper(IDao.class);
//		
//		String bid=request.getParameter("bid");
//		BoardDto dto=dao.replyview(bid);
//		
//		//리턴받은 db의 선택글을 모델에 담기
//		model.addAttribute("reply_view",dto);
		return "replyview";
	}
//	답변을 db에 insert
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("========reply=======");
//		db에 답글기능		
		//toss
		model.addAttribute("request",request);
		bServiceInter=new BReplyService(sqlSession);
		bServiceInter.execute(model);
		
//		String bid=request.getParameter("bid");
//		String bname=request.getParameter("bname");
//		String btitle=request.getParameter("btitle");
//		String bcontent=request.getParameter("bcontent");
//		String bgroup=request.getParameter("bgroup");
//		String bstep=request.getParameter("bstep");
//		String bindent=request.getParameter("bindent");
//		
//		IDao dao=sqlSession.getMapper(IDao.class);
//		int cnt=dao.replyShape(bgroup,bstep);
//		System.out.println("cnt : "+cnt);
//		dao.reply(bid,bname,btitle,bcontent,
//				bgroup,bstep,bindent);

		
		return "redirect:list";
	}
	
}
