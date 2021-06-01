<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>FAQ 목록</title>

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
}

.dropdown {
  position: relative;
  top: 0%;
  left:55%;
  display: inline-block;
}

.dropbtn {
  position: absolute;
  background-color: #20273b;
  border: none;
}

.dropdown-content {
  position: absolute;
  top: 100%;
  display: none;
  background-color: #20273b;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  border-radius: 5px;
}

.dropdown-content a {
  color: #a3a3a3;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #fff;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #fff;}

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
</style>

</head>

<header>
        <button type="button" class="logo-box" onclick="location.href='../club/list'"><img  src="../../images/joinjoylogo-bl.png"></button>
        <button type="button" class="hotplace-box" onclick="location.href='../hotplace/list'"><img  src="../../images/hotplace-bl.png"></button>
        <div class="dropdown">
            <button class="dropbtn"><img  src="../../images/community-bl.png"></button>
          <div class="dropdown-content">
            <a href="#">꿀팁게시판</a>
            <a href="#">자유게시판</a>
             <a href="#">세컨핸즈샵</a>
             <a href="#">신고게시판</a>
          </div>
         </div>
         <button type="button" class="discount-box" onclick="location.href='../discount/list'"><img  src="../../images/discount-bl.png"></button>
         <button type="button" class="qna-box" onclick="location.href='../qna/list'"><img  src="../../images/qna-bl.png"></button>
        <button type="button" class="faq-box" onclick="location.href='../faq/list'"><img  src="../../images/faq-bl.png"></button>
         
      <button type="button" class="btn btn-primary btn-sm">logout</button>
</header>

<body>
<br><br><br>
<h1>FAQ 목록</h1>
<c:if test="${not empty loginUser and loginUser.power == 1}">
<p><a href='add'>새 글</a></p>
</c:if>
<table border='1'>
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

<form action='list' method='get'>
<input type='search' name='keyword' value='${param.keyword}'> 
<button>검색</button>
</form>

</body>
</html>