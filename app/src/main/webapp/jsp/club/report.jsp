<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>클럽 신고</title>
</head>
<body>
<h1>이 게시물을 신고 하는 이유</h1>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>

    <!--클럽신고 관련-->
<%--            <form action="report" method="post">--%>
<%--                <input type="text" name="no" value="${club.no}" >--%>
<%--                <input type="text" name="clubWriterNo" value="${club.writer.no}" >--%>
<%--                <input type="number" name="result" value="${0}" >--%>

<%--                <p>회원님의 신고는 익명으로 처리됩니다.<br>--%>
<%--                    해당되는 신고 유형을 선택하기시 바랍니다.</p>--%>
<%--                신고 사유:<br>--%>
<%--                <input type="radio" name="reason" value="불법 또는 규체 상품 판매">불법 또는 규체 상품 판매 <br/>--%>
<%--                <input type="radio" name="reason" value="지적 재산권 침해">지적 재산권 침해 <br/>--%>
<%--                <input type="radio" name="reason" value="사기 또는 거짓">사기 또는 거짓 <br/>--%>
<%--                <input type="radio" name="reason" value="스팸">스팸 <br/>--%>
<%--                <input class="btn btn-primary" type="submit" value="클럽 신고11111">--%>
<%--            </form>--%>

                <form action="report" method="post">
                    <input type="text" name="no" value="${club.no}" >
                    <input type="text" name="clubWriterNo" value="${club.writer.no}" >
                    <input type="number" name="result" value="${0}" >

                    <p>회원님의 신고는 익명으로 처리됩니다.<br>
                        해당되는 신고 유형을 선택하기시 바랍니다.</p>
                    신고 사유:<br>
                    <input type="radio" name="reason" value="불법 또는 규체 상품 판매">불법 또는 규체 상품 판매 <br/>
                    <input type="radio" name="reason" value="지적 재산권 침해">지적 재산권 침해 <br/>
                    <input type="radio" name="reason" value="사기 또는 거짓">사기 또는 거짓 <br/>
                    <input type="radio" name="reason" value="스팸">스팸 <br/>
                    <input class="btn btn-primary" type="submit" value="클럽 신고">
                </form>
</body>
</html>