<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/nstyle.css" />
</head>
<body>
<h3>contentview</h3>
<form action="contentupdate" method="post">
<input type="hidden" name="bid" value="${content_view.bid }" />
<table>
	<tr>
		<td class="left">번호</td>
		<td>${content_view.bid }</td>
	</tr>
	<tr>
		<td class="left">히트</td>
		<td>${content_view.bhit }</td>
	</tr>
	<tr>
		<td class="left">이름</td>
		<td>${content_view.bname }</td>
	</tr>
	<tr>
		<td class="left">제목</td>
		<td>${content_view.btitle }</td>
	</tr>
	<tr>
		<td class="left">내용</td>
		<td>${content_view.bcontent }</td>
	</tr>
	<tr>
		<td class="left">첨부</td>
		<td>
			<a href="download?p=resources/upload/&f=${content_view.filesrc }
			&bid=${content_view.bid }">${content_view.filesrc }</a>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" value="수정폼으로" />&nbsp;&nbsp;
		<a href="list">목록</a> &nbsp;&nbsp;
		<a href="delete?bid=${content_view.bid }">삭제</a> &nbsp;&nbsp;
		<a href="replyview?bid=${content_view.bid }">답변폼으로</a> &nbsp;&nbsp;
		</td>
		
	</tr>
</table>
</form>
<hr></hr>
	<img src="resources/upload/${content_view.filesrc }" alt="" />
</body>
</html>