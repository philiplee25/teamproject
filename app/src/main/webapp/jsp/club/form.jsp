<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>클럽 생성</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" /> <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
<body>
<h1>클럽 생성</h1>
<form action="add" method="post" enctype="multipart/form-data">
    도착지: <input type="text" name="arrive"><br>
    가는날: <input id="start" name="startDate"><br>
    <script>
        $(function () {
            $("#start").datepicker({
                currentText: '오늘 날짜',
                showAnim: "slide",
                dateFormat: "yy-mm-dd",
                minDate: 0
            });
        });
    </script>

    오는날: <input id="end" name="endDate"><br>
    <script>
        $(function () {
            $("#end").datepicker({
                currentText: '오늘 날짜',
                showAnim: "slide",
                dateFormat: "yy-mm-dd",
                minDate: 0
            });
        });
    </script>

    테마: <select name="theme" id="theme">
    <option value="불멍때리기">불멍때리기</option>
    <option value="고기파티">고기파티</option>
    <option value="낚시">낚시</option>
    <option value="일상탈출">일상탈출</option>
    <option value="글램핑">글램핑</option>
    <option value="캠핑">캠핑</option>
    <option value="별보기">별보기</option>
</select><br>
    제목: <input type="text" name="title"><br>
    내용: <textarea name="content" rows="10" cols="60"></textarea><br>
    인원수(최대 10명): <input type="number" name="count" min="2" max="10"><br>
    사진1: <input type="file" name="photo1"><br>
    사진2: <input type="file" name="photo2"><br>
    사진3: <input type="file" name="photo3"><br>
    <input type="submit" value="등록">
    <p><a href='list'>취소</a></p>
</form>
</body>
</html>