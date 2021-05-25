<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${clubMembers}" var="cm">
    <c:forEach items="${member}" var="m">
        <c:if test="${cm.no == m.no}">
            <td>${m.name}</td>
        </c:if>
    </c:forEach>
</c:forEach>