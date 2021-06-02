<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <title>QnA 상세</title>
    <c:if test="${not empty loginUser and loginUser.no == qna.writer.no or loginUser.power == 1 }">

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
<h1>QnA 게시글 상세보기(JSP + JSP 액션태그 + EL)</h1>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
<form action='update' method='post'>
    <table border='1'>
        <tbody>
        <tr><th>번호</th> <td><input type='text' name='no' value='${qna.no}' readonly></td></tr>
        <tr><th>제목</th> <td><input name='title' type='text' value='${qna.title}'></td></tr>
        <tr><th>내용</th> <td><textarea name='content' rows='10' cols='60'>${qna.content}</textarea></td></tr>
        <tr><th>작성자</th> <td>${qna.writer.name}</td></tr>
        <tr><th>등록일</th> <td>${qna.registeredDate}</td></tr>

        <c:if test="${qna.getAnswer() != null or loginUser.power == 1 }">
            <tr><th>답변일</th> <td>${qna.answerDate}</td></tr>
            <tr><th>답변내용</th> <td><textarea name='answer' rows='10' cols='60'>${qna.answer}</textarea></td></tr>
        </c:if>
        </tbody>

        <tfoot>
        <tr><td colspan='2'>
            <c:if test="${loginUser.getPower() == 1 || qna.getAnswer() == null }">
                <input type='submit' value='변경'>
            </c:if>
            <a href='delete?no=${qna.no}'>삭제</a>
        </td></tr>
    </table>
</form>
<c:if test="${loginUser.power == 1 }">
    <form action='send' method='post'>
        <input type='hidden' name='content' value='${qna.content}' readonly>
        <input type='hidden' name='email' value='${qna.writer.email}' readonly>
        <input type='hidden' name='answer' value='${qna.answer}'>
        <input type='submit' value='답변메일 전송'>
    </form>
</c:if>
</tfoot>
</c:if>
<c:if test="${empty loginUser and loginUser.no != qna.writer.no or loginUser.power != 1}">
    <tr>
        <td colspan='5'>해당 게시자가 아닙니다.</td>
    </tr>
</c:if>


<p><a href='list'>목록</a></p>

</body>
</html>