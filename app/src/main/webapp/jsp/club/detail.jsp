<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>클럽 상세</title>
</head>
<body>
<h1>클럽 상세보기</h1>

<c:if test="${not empty club}">
<c:if test="${not empty club.photos}">
    <c:set var="photos1254x178Url">../upload/${club.photos}_254x178.jpg</c:set>
    <c:set var="photosUrl">../upload/${club.photos}</c:set>
</c:if>
<c:if test="${empty club.photos}">
    <c:set var="photos80x80Url">../images/person_80x80.jpg</c:set>
    <c:set var="photosUrl"></c:set>
</c:if>
<form action='update' method='post'>
    번호: <input type='text' name='arrive' value='${club.no}' readonly><br>
    방장: <input type='text' name='writer' value='${club.writer.name}' readonly><br>
    팀원: <br>
    <jsp:include page="/jsp/member/member_list.jsp"/>
    <br>
    도착지: <input type='text' name='arrive' value='${club.arrive}' readonly><br>
    가는날: <input type='date' name='startDate' value='${club.startDate}' readonly><br>
    오는날: <input type='date' name='endDate' value='${club.endDate}' readonly><br>
    테마: <select name='theme'>
    <option value="불멍때리기" <%If Request("불멍때리기")=1 Then%>selected<%End If%>>불멍때리기</option>
    <option value='불멍때리기' <c:if test="${theme_m=불멍때리기}">selected</c:if>>불멍때리기</option>
    <option value='고기파티' <c:if test="${theme_m.equals(고기파티)}">selected</c:if>>고기파티</option>
    <option value='낚시' <c:if test="${theme_m.equals(낚시)}">selected</c:if>>낚시</option>
    <option value='일상탈출' <c:if test="${theme_m.equals(일상탈출)}">selected</c:if>>일상탈출</option>
    <option value='글램핑' <c:if test="${theme_m.equals(글램핑)}">selected</c:if>>글램핑</option>
    <option value='캠핑' <c:if test="${theme_m.equals(캠핑)}">selected</c:if>>캠핑</option>
    <option value='별보기' <c:if test="${theme_m.equals(별보기)}">selected</c:if>>별보기</option>
    </select>
    <br>
<%--    테마: <select name='theme' value='${club.theme}'>--%>
<%--    <option value='불멍때리기'>불멍때리기</option>--%>
<%--    <option value='고기파티'>고기파티</option>--%>
<%--    <option value='낚시'>낚시</option>--%>
<%--    <option value='일상탈출'>일상탈출</option>--%>
<%--    <option value='글램핑'>글램핑</option>--%>
<%--    <option value='캠핑'>캠핑</option>--%>
<%--    <option value='별보기'>별보기</option>--%>
<%--</select>--%>
<%--    <br>--%>
        제목: <input type='text' name='title' value='${club.title}'><br>
        내용: <textarea name='content' rows='10' cols='60'>${club.content}</textarea><br>
        인원수(최대 10명): <input type='number' name='count' value='${club.total}'><br>

        사진1:
        <td><a href="${photosUrl}">
            <img src="${photos80x80Url}"></a></td>
        <input type="file" name="photos"><br>
        사진2:
        <td><a href="${photosUrl}">
            <img src="${photos80x80Url}"></a></td>
        <input type="file" name="photos"><br>
        사진3:
        <td><a href="${photosUrl}">
            <img src="${photos80x80Url}"></a></td>
        <input type="file" name="photos"><br>

        </tbody>
        <tfoot>
        <tr>
            <td colspan='2'>
                <input type='submit' value='변경'> <a href='delete?no=${club.no}'>삭제</a>
            </td>
        </tr>
        </tfoot>
        </table>
    </form>
    </c:if>
    <c:if test="${empty club}">
        <p>해당 번호의 클럽이 없습니다.</p>
    </c:if>
    <p><a href='list'>목록</a></p>
</form>
</body>
</html>