<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: osk2090
  Date: 2021/05/17
  Time: 11:17 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>클럽 신고게시판</title>
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
<div style="position:absolute; left:16.6%; top:10.8%; font-size:2em">신고게시판</div>

<table style="position: absolute; border: 1px; width: 66.6%; border-collapse: separate; border-spacing: 0 13px; top: 16.76%; left: 16.7%">
    <thead>
    <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>신고사유</th>
        <th>처리여부</th>
        <th>신고일자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clubs}" var="c">
        <tr>
            <td><a href='detail?no=${c.no}'>${c.no}</a></td>
            <c:forEach items="${members}" var="m">
                <c:if test="${m.no == c.writer.no}">
                <td>${m.name} 님을 신고합니다.</td>
                </c:if>
            </c:forEach>
            <td>${c.reason}</td>
            <td>
                <c:if test="${c.result == 0}">미처리</c:if>
                <c:if test="${c.result == 1}">처리완료</c:if>
            </td>
            <td>${c.date}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href='add'>목록</a></p>
</body>
</html>