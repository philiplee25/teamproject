<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 상세</title>
<link rel="stylesheet" type="text/css" href="../../css/clublist.css">
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

<h1>게시글 상세보기1</h1>

<c:if test="${not empty board}">

<form action='update' method='post'>
<table border='1'>
<tbody>
<tr><th>번호</th> <td><input type='text' name='no' value='${board.no}' readonly></td></tr>
<tr><th>제목</th> <td><input name='title' type='text' value='${board.title}'></td></tr>

<tr><th>사진</th>
<c:if test="${not empty board.photos}">
  <c:forEach items="${board.photos}" var="p" >
  <c:set var="photo250x250Url">../../upload/${p.photo}_250x250.jpg</c:set>
   <td><img src='${photo250x250Url}'><br></td>
<%--    <input name='photo' type='file'></td> --%>  
   </c:forEach> 
</c:if>
</tr>

<tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${board.content}</textarea></td></tr>
<tr><th>작성자</th> <td>${board.writer.name}</td></tr>
<tr><th>등록일</th> <td>${board.registeredDate}</td></tr>
<tr><th>조회수</th> <td>${board.viewCount}</td></tr>
</tbody>

<c:if test="${not empty loginUser and loginUser.no == board.writer.no or loginUser.power == 1 }">

<tfoot>
<tr>
  <td colspan='2'>
    <input type='hidden' name='boardtype' value='${boardtype}'>
    <input type='submit' value='변경'>
    <a href='delete?no=${board.no}&boardtype=${boardtype}'>삭제</a>
  </td>
</tr>
</tfoot>
</c:if>

</table>
</form>
</c:if>

<p><a href='list'>목록</a></p>
</body>
</html>