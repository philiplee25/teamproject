<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>할인정보 상세</title>
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
<br><br><br>
<h1>할인정보 상세보기</h1>
		<c:if test="${not empty discount.photo}">
		  <c:set var="photo200x200Url">../../upload/${discount.photo}_200x200.jpg</c:set>
		  <c:set var="photoUrl">../../upload/${discount.photo}</c:set>
		</c:if>
		
		<form action='update' method='post' enctype='multipart/form-data'>
		<table border='1'>
		  <tbody>
		    <tr><th>번호</th> <td><input type='text' name='no' value='${discount.no}' readonly></td></tr>
		    <tr><th>제목</th> <td><input name='title' type='text' value='${discount.title}'></td></tr>
		    <tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${discount.content}</textarea></td></tr>
		    <tr><th>작성자</th> <td>관리자</td></tr>
		    <tr><th>등록일</th> <td>${discount.date}</td></tr>
		    <tr><th>조회수</th> <td>${discount.count}</td></tr>
		    
		    <tr><th>사진</th> 
		  <td><a href='${photoUrl}'><img src='${photo200x200Url}'></a><br>
		  <c:if test="${not empty loginUser and loginUser.power == 1}">
		    <input name='photo' type='file'></td></tr>
		  </c:if>
		</tbody>
		
		  <c:if test="${not empty loginUser and loginUser.power == 1}">
		  <tfoot>
		  <tr>
		    <td colspan='2'>
		      <input type='submit' value='변경'><a href='delete?no=${discount.no}'>삭제</a>
		    </td>
		  </tr>
		  </tfoot>
		  </c:if>
		      
		</table>
		</form>

<c:if test="${empty discount}">
  <p>해당 번호의 할인정보가 없습니다.</p>
</c:if>

<p><a href='list'>목록</a></p>
</body>
</html>