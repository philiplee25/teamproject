<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${member}" var="m" >
  <c:forEach items="${clubMembers}" var="clubMember">
    <c:if test="${m.no == clubMember.no}">
      <c:set var="checked" value="checked"/>
    </c:if>
  </c:forEach>
  <input type='checkbox' name='member' value='${m.no}' ${checked}>${m.name} ${m.tel}<br>
  <c:remove var="checked"/>

  <!--회원 프로필 사진 보이기 기능-->
  <c:forEach items="${list}" var="m">
    <c:if test="${not empty m.photo}">
      <c:set var="photoUrl">../upload/${m.photo}_30x30.jpg</c:set>
    </c:if>
    <c:if test="${empty m.photo}">
      <c:set var="photoUrl">../images/person_30x30.jpg</c:set>
    </c:if>
    <tr>
      <td>${m.no}</td>
      <td><img src='${photoUrl}'></td>
      <td><a href='detail?no=${m.no}'>${m.name}</a></td>
      <td>${m.email}</td>
      <td>${m.tel}</td>
    </tr>
  </c:forEach>
</c:forEach>
