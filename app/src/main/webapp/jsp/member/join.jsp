<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>회원가입</title>

  <style>
    #wrap{
      width:400px;
      margin-left:auto;
      margin-right:auto;
      text-align:center;
    }

    table{
      border:3px solid skyblue
    }

    td{
      border:1px solid skyblue
    }

    #title{
      background-color:skyblue
    }
  </style>
</head>
<body>
<!-- 왼쪽 오른쪽 바깥여백을 auto로 주면 중앙정렬이 됩니다!  -->

<div id="wrap">
  <br><br>
  <b><font size="6" color="black">회원가입</font></b>
  <br><br><br>

  <form action="addd" method="post" enctype="multipart/form-data">
    <table>
      <tr>
        <td id="title">이름</td>
        <td>
          <input type="text" name="name" maxlength="10">
        </td>
      </tr>

      <tr>
        <td id="title">비밀번호(최소 4자, 최대 12자)</td>
        <td>
          <input type="password" name="password" minlength="4" maxlength="12">
        </td>
      </tr>

      <tr>
        <td id="title">이메일</td>
        <td>
          <input id="f-email" type="email" name="email">
          <button id="checkBtn" type="button">중복검사</button><br>
        </td>
      </tr>

      <tr>
        <td id="title">성별</td>
        <td>
          <input type="radio" name="gender" value="0" checked>남자
          <input type="radio" name="gender" value="1" checked>여자
        </td>
      </tr>

      <tr>
        <td id="title">생년월일</td>
        <td>
          <input type="date" name="birth"><br>
        </td>
      </tr>

      <tr>
        <td id="title">전화번호</td>
        <td>
          <input type="tel" name="tel" maxlength="11"><br>
        </td>
      </tr>


      <tr>
        <td id="title">사진</td>
        <td>
          <input type="file" name="photo"><br>
        </td>
      </tr>

    </table>
    <br>
    <input type="submit" value="가입"/>  <input type="button" value="취소" OnClick="javascript:history.back(-1)">
  </form>
</div>
<script>
document.querySelector("#checkBtn").onclick = function() {
  var fEmail = document.querySelector("#f-email");
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "check?email=" + fEmail.value, false);
  xhr.send();
  if (xhr.responseText == "yes") {
    alert("이미 있는 사용자입니다.");
    fEmail.value = "";
  } else {
    alert("이 이메일을 사용할 수 있습니다.")
  }
};
</script>
</body>
</html>