<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../../css/clublist.css">
<title>게시글 목록</title>
</head>
<body>

<!-- 헤더 -->
<button type="button" class="dropbtn" onclick="location.href='../hotplace/list'">핫플레이스</button>
<div class="dropdown">
    <button class="dropbtn">커뮤니티</button>
  <div class="dropdown-content">
    <a href="../board/list?boardtype=1">꿀팁게시판</a>
    <a href="../board/list?boardtype=2">자유게시판</a>
    <a href="../board/list?boardtype=3">세컨핸즈샵</a>
    <a href="#">신고게시판</a>
  </div>
</div>
<button type="button" class="dropbtn" onclick="location.href='../discount/list'">할인정보</button>
<button type="button" class="dropbtn" onclick="location.href='../qna/list'">고객센터</button>
<button type="button" class="dropbtn" onclick="location.href='../faq/list'">도움말</button>
<!-- 헤더 -->

<h1>게시글 목록</h1>
<p><a href='add?boardtype=${boardtype}'>새 글</a></p>
<table border='1'>
<thead>
<tr>
<th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th> <th>조회수</th>
</tr>
</thead>
<tbody>
<c:forEach items="${list}" var="b">
<tr>
 <td>${b.no}</td> 
  <td><a href='detail?no=${b.no}&boardtype=${boardtype}'>${b.title}</a></td>
  <td>${b.writer.name}</td>
  <td>${b.registeredDate}</td>
  <td>${b.viewCount}</td>
 </tr>

</c:forEach>
</tbody>
</table>
<form action='list' method='get'>
<input type='text' name='keyword' value='${param.keyword}'>
<input type='hidden' name='boardtype' value='${boardtype}'>
<button>검색</button>
</form>
</body>
</html>