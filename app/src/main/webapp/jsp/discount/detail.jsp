<%@page import="com.osk.team.domain.Member"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.osk.team.domain.Discount"%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>할인정보 상세</title>
</head>
<body>
<h1>할인정보 상세보기</h1>
<% 
if (request.getAttribute("discount") != null) {
%>
<form action='update' method='post'>
<table border='1'>
<tbody>
<tr><th>번호</th> <td><input type='text' name='no' value='${discount.no}' readonly></td></tr>
<tr><th>제목</th> <td><input name='title' type='text' value='${discount.title}'></td></tr>
<tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${discount.content}</textarea></td></tr>
<tr><th>작성자</th> <td>${discount.writer.name}</td></tr>
<tr><th>등록일</th> <td>${discount.date2}</td></tr>
<tr><th>조회수</th> <td>${discount.count}</td></tr>
<tr><th>사진</th> 
  <td><a href='${photoUrl}'>
  <img src='${photo80x80Url}'></a><br>
  <input name='photo' type='file'></td></tr>
</tbody>
<jsp:useBean id="loginUser" type="com.osk.team.domain.Member" scope="session"/>
<% 
Discount discount = (Discount) request.getAttribute("discount");
if (loginUser != null && discount.getWriter().getPower() == loginUser.getPower()) {
%>
<tfoot>
<tr>
  <td colspan='2'>
    <input type='submit' value='변경'><a href='delete?no=${discount.no}'>삭제</a>
  </td>
</tr>
</tfoot>
<%}%>
      
</table>
</form>

<%} else {%>
<p>해당 번호의 게시글이 없습니다.</p>
<%}%>

<p><a href='list'>목록</a></p>
</body>
</html>