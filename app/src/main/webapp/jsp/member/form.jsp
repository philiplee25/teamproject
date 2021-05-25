<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>새 회원 등록</title>
</head>
<body>
<h1>새 회원 등록</h1>
<form action="add" method="post" enctype="multipart/form-data">
이름: <input type="text" name="name"><br>
비밀번호: <input type="password" name="password"><br>
이메일: <input type="email" name="email"><br>
생년월일: <input type='date' name='birth'><br>
전화번호: <input type='tel' name='tel'><br>
성별: <input type="radio" name="gender" value="0">남자<br>
     <input type="radio" name="gender" value="1">여자<br>
사진: <input type="file" name="photo"><br>
<!-- 탈퇴여부: <input type="radio" name="status" value="0">탈퇴한적 없음<br>
     <input type="radio" name="status" value="1">탈퇴한적 있음<br>
관리자권한: <input type="radio" name="power" value="0">일반회원<br>
     <input type="radio" name="power" value="1">관리자<br>
제제횟수: <input type="radio" name="count" value="0">없음<br>
     <input type="radio" name="count" value="1">있음<br> -->
<input type="submit" value="등록">
</form>
</body>
</html>