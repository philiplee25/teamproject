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
<style>
body{
  background-color: #fff;
}
        
header{
  background-color: #20273b;
  position: fixed;
  width: 100%;
  height: 80px;
  margin: 0 0 149px;
  padding: 16px 100px 15px 83px;
  z-index: 200;
}

.dropbtn {
  background-color: #20273b;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}

.dropdown {
  position: relative;
  top: -10%;
  left:54%;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #20273b;
  min-width: 115px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  border-radius: 10px;
}

.dropdown-content a {
  color: #a3a3a3;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #fff;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #20273b;}

button.btn.btn-primary.btn-sm {
    position: absolute;
    top: 25%;
    right: 5.2%;
}

.btn-primary {
    width: 80px;
    height: 40px;
    padding: 8px 15px 11px;
    border-radius: 10px;
    background-color:#4d90eb;
    border-color:#4d90eb;
}


.logo-box {
    position: absolute;
    top:25%;
    left: 5.2%;
    background-color: #20273b;
    border: none;
}

.hotplace-box {
    position: absolute;
    top:32.8%;
    left: 46%;
    background-color: #20273b;
    border: none;
}

.discount-box {
    position: absolute;
    top:32.8%;
    left: 61.3%;
    background-color: #20273b;
    border: none;
}

.qna-box {
    position: absolute;
    top:32.8%;
    left: 68.6%;
    background-color: #20273b;
    border: none;
}
.faq-box {
    position: absolute;
    top:32.8%;
    left: 75.9%;
    background-color: #20273b;
    border: none;
}

table{
border-collapse: separate;
border-spacing: 0 40px;
border: 1px solid #f4f4f4;
}

  th, td {
    border: 1px solid #f4f4f4;
    text-align: center;
    bgcolor: #f4f4f4;
  }
</style>

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
<h1>클럽 신고게시판</h1>

<table border='1'>
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