<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${members}" var="m">
    <c:forEach items="${clubMembers}" var="cm">
        <c:if test="${m.no == cm.no}">

            <table border="" style="width: 10%;">
                <tbody>
                <tr style="width: 10%;">
                    <td style="width: 33%;">

                        <c:if test="${not empty cm.photo}">
                            <c:set var="photoUrl">../upload/${cm.photo}_30x30.jpg</c:set>
                        </c:if>
                        <c:if test="${empty cm.photo}">
                            <c:set var="photoUrl">../images/person_30x30.jpg</c:set>
                        </c:if>
                            ${cm.name}
                            ${cm.tel}

                        <img src='${photoUrl}'>

                        <c:if test="${not empty loginUser and loginUser.no == cm.no}">
                            <form action="deleteMember" method="get">
                                <input type="text" name="no" value="${loginUser.no}" hidden>
                                <input type="submit" value="클럽 탈퇴">
                            </form>
                        </c:if>

                        <c:if test="${not empty loginUser and loginUser.no == club.writer.no}">
                            <form action="deleteMembers" method="get">
                                <c:if test="${cm.no == m.no}">
                                    <input type="text" name="no" value="${cm.no}">
                                    <input type="submit" value="클럽 강퇴">
                                </c:if>
                            </form>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:if>
    </c:forEach>
</c:forEach>