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
    <title>Club List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/header_bl.css" type="text/css">
    <style>
    body{
		  background-image: url("../../images/airplane-2619434.jpg");
		  background-size: 100%;
		  background-position: 50% 23%;
		  background-repeat: no-repeat;
		  z-index:-1;
		}
    </style>
</head>

<header>
        <button type="button" class="logo-box" onclick="location.href='../club/main'"><img  src="../../images/joinjoylogo-wh.png"></button>
        <button type="button" class="hotplace-box" onclick="location.href='../hotplace/list'"><img  src="../../images/hotplace-wh.png"></button>
        <div class="dropdown">
            <button class="dropbtn"><img  src="../../images/community-wh.png"></button>
          <div class="dropdown-content">
            <a href="../board/list?boardtype=1">꿀팁게시판</a>
            <a href="../board/list?boardtype=2">자유게시판</a>
             <a href="../board/list?boardtype=3">세컨핸즈샵</a>
             <a href="../club/reportList">신고게시판</a>
          </div>
         </div>
         <button type="button" class="discount-box" onclick="location.href='../discount/list'"><img  src="../../images/discount-wh.png"></button>
         <button type="button" class="qna-box" onclick="location.href='../qna/list'"><img  src="../../images/qna-wh.png"></button>
        <button type="button" class="faq-box" onclick="location.href='../faq/list'"><img  src="../../images/faq-wh.png"></button>

      <c:choose>
          <c:when test="${empty loginUser}">       
		          <button type="button" class="btn-outline-primary" onclick="location.href='../member/addd'">Sign up</button>
		          <button type="button" class="btn btn-primary btn-sm" onclick="location.href='../login'">Login</button> 
          </c:when>
          <c:otherwise>
              <button type="button" class="btn-outline-primary" onclick="location.href='../member/detail'">My page</button>
              <button type="button" class="btn btn-primary btn-sm" onclick="location.href='../logout'">Logout</button> 
          </c:otherwise>
      </c:choose>
</header>


<body>
<div style="position: absolute; left:16.6%; top:22.56%; font-size:50px; color: #fff; font-weight: bold;">이제 조인하세요</div>

<div style="background-color: #20273b;
   position:absolute;
   width: 66.82%;
   height: 22.22%;
   left: 16.61%;
   top: 35.65%;
   border-radius: 20px;">
		<div class="form-check" style="position: absolute; left: 3.12%; top: 16.67%;">
		    <input class="form-check-input" type="radio" name="select" id="selectjoin" OnClick="window.location.href='main';" checked>
		    <label class="form-check-label" for="selectjoin" style="color: #fff;">
		        참여
		    </label>
		</div>
		<div class="form-check" style="position: absolute; left: 11.85%; top: 16.67%;">
		    <input class="form-check-input" type="radio" name="select" id="selectadd" OnClick="window.location.href='add';">
		    <label class="form-check-label" for="selectadd" style="color: #fff;">
		        생성
		    </label>
		</div>
		
		<form action='list' method='get'>
		    <fieldset style= "position: absolute; width: 90%; top: 40%; left: 3.35%; color:#fff;">
		        <table>
		            <thead style="font-size: 0.75em;">
			            <tr>
			              <th>도착지</th><th>가는날</th><th>오는날</th><th>테마</th>
			            </tr>
			           </thead>
		            
		            <tbody>
			            <tr>
			              <td><input type='search' name='arrive' value='${param.arrive}' style="border: none; width: 254px; height: 54px; border-top-left-radius: 10px; border-bottom-left-radius: 10px; border-color: #fff;"></td>
		                <td><input type='date' name='startDate' value='${param.startDate}' style="border: none; width: 254px; height: 54px; border-color: #fff;"></td>
			              <td><input type='date' name='endDate' value='${param.endDate}' style="border: none; width: 254px; height: 54px; border-color: #fff;"></td>
			              <td><select name="theme" id="theme" style="border: none; width: 254px; height: 54px; border-bottom-right-radius: 10px; border-top-right-radius: 10px; border-color: #fff;">
			                    <option value=''></option>
			                    <option value='불멍때리기'>불멍때리기</option>
			                    <option value='고기파티'>고기파티</option>
			                    <option value='낚시'>낚시</option>
			                    <option value='일상탈출'>일상탈출</option>
			                    <option value='글램핑'>글램핑</option>
			                    <option value='캠핑'>캠핑</option>
			                    <option value='별보기'>별보기</option>
			              </td>
			              <td></td>
			              <td>
                      <button style="left: 20%; width: 163px; height: 54px; border-radius: 10px; background-color: #4d90eb; color: #fff; border: 0;">Search</button>
                    </td>
	                 </tr>
                </tbody>
		        </table>
		    </fieldset>
		</form>
</div>

</body>
</html>