<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>로그아웃 처리</title>
</head>
<body>
<%
  request.getSession().invalidate(); // 모든세션정보 삭제
  response.sendRedirect("login"); // 로그인 화면으로 다시 돌아간다.

  //request.getSession().invalidate();
  //response.sendRedirect("login");
%>

<h1>로그아웃이 완료되었습니다!</h1>
</body>
</html>
