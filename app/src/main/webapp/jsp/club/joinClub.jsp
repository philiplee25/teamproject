<%@ page import="com.osk.team.domain.Member" %>
<%@ page import="com.osk.team.domain.Club" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: EUNSUK
  Date: 2021-05-22
  Time: 오전 3:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:forEach items="${loginUser}" var="l">--%>
<%--    <c:forEach items="${clubMembers}" var="clubMembers">--%>
<%--        <c:if test="${l.no == clubMembers.no}">--%>
<%--        <c:set var="checked" value="checked"/>--%>
<%--        </c:if>--%>
<%--    </c:forEach>--%>
<%--    <input type='checkbox' name='member' value='${l.no}' ${checked}>${l.name} ${l.tel} ${l.photos}<br>--%>
<%--    <c:remove var="checked"/>--%>
<%--</c:forEach>--%>
<%
    Club club = new Club();
%>
<c:if test="${empty club.members}">없음</c:if><br>
<c:if test="${not empty loginUser}">
    <form action="join" method="post">
        <tr>
            <td colspan='2'>
                <input type='submit' value='클럽 참여'>
            </td>
        </tr>
    </form>
</c:if><br>
