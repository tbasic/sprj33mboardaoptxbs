<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
	#d1{
		background-color: cyan;
	}
	#d2{
		background-color: blue;
	}
	#d3{
		background-color: red;
	}
	
	table{
		margin-top: 20px;
	}

</style>
</head>
<body>

<div class="jumbotron text-center">
	<h1>Goott List Page</h1>
	<p>Goott IT Developer 4Lec Room effect!</p>
</div>
<!-- col-md-4는 12/4 md에서 전체를 3등분
col-sm-6는 12/6 md에서 전체를 2등분
col-xs-12 생략가
 ???-->

<div class="container">
	<div class="row">
		<div id="d1" class="col-xs-12 col-md-4 col-sm-6">
			<a href="#">Menu1</a>
		</div>
		
		<div id="d2" class="col-xs-12 col-md-4 col-sm-6">
			<a href="#">Menu2</a>
		</div>
		
		<div id="d3" class="col-xs-12 col-md-4 col-sm-6">
			<a href="#">Menu3</a>
		</div>
		<div id="d2" class="col-xs-12 col-md-4 col-sm-6">
			<a href="#">Menu3</a>
		</div>
	</div>
</div>
<br /> <br />
<div class="container">
	<div class="row">
		<div id="d11" class="col-xs-12 col-md-4 col-sm-6">
			<table width="300" border="1">
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>제목</td>
					<td>날자</td>
					<td>히트</td>
				</tr>
				<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.bid }</td>
					<td>${dto.bname }</td>
					<td>
						<c:set value="${dto.bindent }" var="endindent" />
						<c:forEach begin="1" end="${dto.bindent }" var="cnt">
							&nbsp;
							<c:if test="${cnt eq endindent }">
								<img src="resources/img/icon_reply.gif" />[re]
							</c:if>
						</c:forEach>
						<a href="contentview?bid=${dto.bid }">${dto.btitle }</a>
					</td>
					<td>
					<fmt:formatDate value="${dto.bdate }" pattern="yyyy/MM/dd"/>
					
					
					</td>
					<td>${dto.bhit }</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><a href="writeview">글쓰기</a></td>
				</tr>
			</table>
		</div>
		
		<div id="d21" class="col-xs-12 col-md-4 col-sm-6">
			<table width="300" border="1">
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>제목</td>
					<td>날자</td>
					<td>히트</td>
				</tr>
				<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.bid }</td>
					<td>${dto.bname }</td>
					<td>
						<c:set value="${dto.bindent }" var="endindent" />
						<c:forEach begin="1" end="${dto.bindent }" var="cnt">
							&nbsp;
							<c:if test="${cnt eq endindent }">
								<img src="resources/img/icon_reply.gif" />[re]
							</c:if>
						</c:forEach>
						<a href="contentview?bid=${dto.bid }">${dto.btitle }</a>
					</td>
					<td>
					<fmt:formatDate value="${dto.bdate }" pattern="yyyy/MM/dd"/>
					
					
					</td>
					<td>${dto.bhit }</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><a href="writeview">글쓰기</a></td>
				</tr>
			</table>
		</div>
		
		
		
	</div>
</div>



<hr />
totCnt : ${totRowcnt } <br />
현재페이지/토탈페이지 : ${searchVo.page }/${searchVo.totPage } <br />
<hr />
<form action="list" method="post">

	<c:if test="${searchVo.page>1 }">
		<a href="list?page=1">[처음]</a>
		<a href="list?page=${searchVo.page-1 }">[이전]</a>	
	</c:if>
	
	<c:forEach begin="${searchVo.pageStart }" end="${searchVo.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVo.page }">
				<span style="color:red; font-weight:bold">${i }&nbsp;</span>
			</c:when>
			<c:otherwise>
				<a href="list?page=${i }" style="text-decoration:none">${i }&nbsp;</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${searchVo.page < searchVo.totPage }">
		<a href="list?page=${searchVo.page+1 }">[다음]</a>	
		<a href="list?page=${searchVo.totPage }">[마지막]</a>
	</c:if>

	<div>
		<c:choose>
			<c:when test="${btitle }">
				<input type="checkbox" name="searchType" value="btitle" checked />
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="searchType" value="btitle" />
			</c:otherwise>	
		</c:choose>
		 제목
		<c:choose>
			<c:when test="${bcontent }">
				<input type="checkbox" name="searchType" value="bcontent" checked /> 내용
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="searchType" value="bcontent" /> 내용
			</c:otherwise>	
		</c:choose>

		<input type="text" name="sk" value="${resk }" />
		<input type="submit" value="검색" />
		
	</div>

</form>
</body>
</html>