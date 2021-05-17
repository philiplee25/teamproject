<%@page import="com.osk.team.domain.Discount"%>
<%@page import="java.util.List"%>
<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>할인정보 목록</title>
</head>
<body>
<h1>할인정보 목록</h1>
<p><a href='add'>새 글</a></p>
<table border='1'>
<thead>
<tr>
<th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th> <th>조회수</th>
</tr>
</thead>
<tbody>
<jsp:useBean id="list" type="List<Discount>" scope="request"/>
<% 
for (Discount d : list) {
  pageContext.setAttribute("d", d);
%>
<tr> 
  <td>${d.no}</td> 
  <td><a href='detail?no=${d.no}'>${d.title}</a></td>
  <td>${d.writer.name}</td>
  <td>${d.date}</td>
  <td>${d.count}</td>
</tr>
<%
}
%>
</tbody>
</table>

<form action='list' method='get'>
<input type='search' name='keyword' value='${param.keyword}'> 
<button>검색</button>
</form>

</body>
</html>