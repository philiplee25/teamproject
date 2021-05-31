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
    <title>클럽 신고게시판</title>
</head>
<body>
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