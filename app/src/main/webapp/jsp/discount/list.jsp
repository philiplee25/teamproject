<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>할인정보 목록</title>

<link rel="stylesheet" href="../../css/header_wh.css" type="text/css">

</head>

<header>
        <button type="button" class="logo-box" onclick="location.href='../club/list'"><img  src="../../images/joinjoylogo-bl.png"></button>
        <button type="button" class="hotplace-box" onclick="location.href='../hotplace/list'"><img  src="../../images/hotplace-bl.png"></button>
        <div class="dropdown">
            <button class="dropbtn"><img  src="../../images/community-bl.png"></button>
          <div class="dropdown-content">
            <a href="../board/list?boardtype=1">꿀팁게시판</a>
            <a href="../board/list?boardtype=2">자유게시판</a>
             <a href="../board/list?boardtype=3">세컨핸즈샵</a>
             <a href="../club/reportList">신고게시판</a>
          </div>
         </div>
         <button type="button" class="discount-box" onclick="location.href='../discount/list'"><img  src="../../images/discount-bl.png"></button>
         <button type="button" class="qna-box" onclick="location.href='../qna/list'"><img  src="../../images/qna-bl.png"></button>
        <button type="button" class="faq-box" onclick="location.href='../faq/list'"><img  src="../../images/faq-bl.png"></button>
         
      <button type="button" class="btn btn-primary btn-sm">logout</button>
</header>

<body>
<br><br><br>
<h1>할인정보 목록</h1>
<c:if test="${not empty loginUser and loginUser.power == 1}">
<a class="btn btn-primary" href="add" role="button">할인정보 등록</a>
</c:if>
<table border='1'>
<thead>
<tr>
<th>번호</th> <th> </th><th>제목</th> <th>작성자</th> <th>등록일</th> <th>조회수</th>
</tr>
</thead>
<tbody>

<c:forEach items="${list}" var="d">
  <c:if test="${not empty d.photo}">
    <c:set var="photoUrl">../../upload/${d.photo}_80x80.jpg</c:set>
  </c:if>
    <c:if test="${empty d.photo}">
    <c:set var="photoUrl">../../images/image-default_80x80.png</c:set>
  </c:if>

<tr> 
  <td>${d.no}</td> 
  <td><img src='${photoUrl}'></td>
  <td><a href='detail?no=${d.no}'>${d.title}</a></td>
  <td>관리자</td>
  <td>${d.date}</td>
  <td>${d.count}</td>
</tr>
</c:forEach>
</tbody>
</table>

<form action='list' method='get'>
<input type='search' name='keyword' value='${param.keyword}'> 
<button>검색</button>
</form>

</body>
</html>