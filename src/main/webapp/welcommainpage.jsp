<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--pasidarom kelia, kad gautumem resursus, nes taip bus lengviau susirasti kelia, Bootsrap, Css--%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Welcome window</title>
    <%--randam, kur yra registracijos langas--%>
    <link rel="stylesheet" href="${path}/resources/css/style.css">
    <link rel="stylesheet" href="${path}/resources/css/bootstrap.min.css">
</head>
<body>

Veikia
<c:if test="${pageContext.request.userPrincipal.name!=null}">
    <%--/logout cia yra Spring'o logout metodas, kuris atsakingas uz token sesijos atsijungima.
    Pirma siunciam i WebSecurityConfiguration > "/logout" ir ten atjungiam bei on success nukreipia i mvc3 controlerio "/" langa, kuris pas mus yra irgi logino langas--%>
    <form id="logoutForm" method="post" action="${path}/logout">
        <%--perduodam tokenus--%>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>
    <%--cia pateikiam vartotoja ir onclick padarom, jog atjungtumem vartotoja--%>
    <h2>Labas vartotojau vardu: ${pageContext.request.userPrincipal.name} |->
        <a onclick="document.forms['logoutForm'].submit()">Logout</a>
    </h2>
</c:if>
<c:if test="${!teachersList.isEmpty()}">
    ${teachersList.get(0).getName()}
    <table>
        <tr>
            <th>teacherId</th>
            <th>name</th>
            <th>surname</th>
            <th>phone</th>
            <th>subject</th>
            <th>schoolClasses</th>
            <th>user Id</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    <c:forEach var="teach" items="${teachersList}" varStatus="theCount">
        <tr>
            <td>
                <input id="id${theCount.index}" type="text" name="id" hidden/>
                    ${teach.getTeacherId()}
            </td>
            <td>
                <input id="name${theCount.index}" type="text" name="name" hidden/>
                    ${teach.getName()}
            </td>
            <td>
                <input id="surname${theCount.index}" type="text" name="surname" hidden/>
                    ${teach.getSurname()}
            </td>
            <td>
                <input id="phone${theCount.index}" type="text" name="phone" hidden/>
                    ${teach.getPhone()}
            </td>
            <td>
                <input id="subject${theCount.index}" type="text" name="subject" hidden/>
                    ${teach.getSubject()}
            </td>
            <td>
                <input id="schoolClasses${theCount.index}" type="text" name="schoolClasses" hidden/>
                    ${teach.getSchoolClasses().getTitle()}
            </td>
            <td>
                <input id="user${theCount.index}" type="text" name="user" hidden/>
                    ${teach.getUser().getUserId()}
            </td>
            <td>
                <button id="update${theCount.index}">Update</button>
            </td>
            <td>
                <button id="delete${theCount.index}">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </table>
</c:if>

<script type="text/javascript" src="${path}/resources/js/bootstrap.js"/>
<script type="text/javascript" src="${path}/resources/js/jquery-3.2.1.js"/>
</body>
</html>