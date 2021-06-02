<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 이위치x  <meta http-equiv='Refresh' content='1;url=list'> -->
<link rel="stylesheet" type="text/css" href="../../css/clublist.css">
<title>새 게시글</title>
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

<h1>새 게시글</h1>
<form action="add" method="post" enctype="multipart/form-data">
제목: <input type='text' name='title'><br>
내용: <textarea name='content' rows='10' cols='60'></textarea><br>
사진1: <input type='file' name='photo1'><br>
사진2: <input type='file' name='photo2'><br>
사진3: <input type='file' name='photo3'><br>
<input type='hidden' name='boardtype' value='${boardtype}'>
<input type='submit' value='등록'>
<p><a href='list'>목록</a></p>
</form>
</body>
</html>