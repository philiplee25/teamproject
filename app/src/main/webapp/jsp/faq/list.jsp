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
<title>FAQ 목록</title>

<link rel="stylesheet" href="../../css/header_wh.css" type="text/css">

</head>

<header>
        <button type="button" class="logo-box" onclick="location.href='../club/main'"><img  src="../../images/joinjoylogo-bl.png"></button>
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
         
      <c:choose>
          <c:when test="${empty loginUser}">       
              <button type="button" class="btn-outline-primary" onclick="location.href='../member/addd'">Sign up</button>
              <button type="button" class="btn btn-primary btn-sm" onclick="location.href='../login'">Login</button> 
          </c:when>
          <c:otherwise>
              <button type="button" class="btn-outline-primary" onclick="location.href='../member/detail'">My page</button>
              <button type="button" class="btn btn-primary btn-sm" onclick="location.href='../logout'">Logout</button> 
          </c:otherwise>
      </c:choose>
</header>

<body>
<div style="position:absolute; left:16.6%; top:10.8%; font-size:2em">FAQ</div>
<c:if test="${not empty loginUser and loginUser.power == 1}">
<a class="btn btn-primary" href="add" role="button" style="position: absolute; right:16.6%; top:10.8%;">Add</a>
</c:if>

<table class="list" style="position: absolute; border: 1px; width: 66.6%; border-collapse: separate; border-spacing: 0 13px; top: 16.76%; left: 16.7%">
		<thead>
			<tr>
			<th>번호</th> <th>제목</th> <th>작성자</th> <th>등록일</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${list}" var="f">
			<tr> 
			  <td>${f.no}</td> 
			  <td><a href='detail?no=${f.no}'>${f.title}</a></td>
			  <td>관리자</td>
			  <td>${f.date}</td>
			</tr>
		</c:forEach>
		</tbody>
</table>

<form action='list' method='get' style= "position: absolute; bottom: 7%; left: 36%; color:#fff;">
  <input type='text' name='keyword' value='${param.keyword}' style="width: 500px;">
  <button type="button" class="btn btn-primary btn-sm2">search</button>
</form>

</body>
</html>