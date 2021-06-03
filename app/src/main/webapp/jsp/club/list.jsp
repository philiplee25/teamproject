<%@ page 
    language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>검색 결과</title>

<link rel="stylesheet" href="../../css/header_wh.css" type="text/css">
        
</head>

<header>
        <button type="button" class="logo-box" onclick="location.href='../club/main'"><img  src="../../images/joinjoylogo-bl.png"></button>
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
<div style="background-color: #20273b;
   position:absolute;
   width: 66.82%;
   height: 22.22%;
   left: 16.61%;
   top: 15%;
   border-radius: 20px;">
		<div class="form-check" style="position: absolute; left: 3.12%; top: 16.67%;">
		    <input class="form-check-input" type="radio" name="select" id="selectjoin" OnClick="window.location.href='list';" checked>
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
            <table class="search">
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

<table class="list" style="position: absolute; border: 1px; width: 66.6%; border-collapse: separate; border-spacing: 0 13px; top: 40%; left: 16.7%">
    <thead>
    <tr>
        <th>번호</th>
        <th>도착지</th>
        <th>가는날</th>
        <th>오는날</th>
        <th>테마</th>
        <th>인원수</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${clubs}" var="c">
        <tr>
            <td><a href='detail?no=${c.no}'>${c.no}</a></td>
            <td>${c.arrive}</td>
            <td>${c.startDate}</td>
            <td>${c.endDate}</td>
            <td>${c.theme}</td>
            <td>${c.total}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>