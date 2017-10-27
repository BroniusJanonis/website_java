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
    <%--<link rel="stylesheet" href="${path}/resources/css/style.css">--%>
    <%--<link rel="stylesheet" href="${path}/resources/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

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
    <br>
    <input  class="border border-primary" type="text" id="myInput" onkeyup="searchBy()" placeholder="Ieškoti pagal ..." >
    <table id="myTable">
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
            <%--<label id="surname${theCount.index}" type="text" name="surname" value="${teach.getSurname()}" hidden/>--%>
            <td>
                <label id="idTeacherId${theCount.index}" contenteditable="true"> ${teach.getTeacherId()} </label>
            </td>
            <td>
                <label id="idName${theCount.index}" contenteditable="true"> ${teach.getName()} </label>
            </td>
            <td>
                <label id="idSurname${theCount.index}" contenteditable="true"> ${teach.getSurname()} </label>
            </td>
            <td>
                <label id="idPhone${theCount.index}" contenteditable="true"> ${teach.getPhone()} </label>
            </td>
            <td>
                <c:forEach var="subj" items="${teach.getSubject()}" varStatus="subjCount">
                    <%--${subj.getSubjectId()}--%>
                    <label id="subjectId${theCount.index}And${subjCount.index}" contenteditable="true"> ${subj.getSubjectId()} </label><br>
                    <label id="subjectName${theCount.index}And${subjCount.index}" contenteditable="true"> ${subj.getSubjectName()} </label>
                </c:forEach>
                <%--<label id="idSubject${theCount.index}" contenteditable="true"> ${teach.getSubject()} </label>--%>
            </td>
            <td>
                <label id="idSchoolClasses_Title${theCount.index}" contenteditable="true"> ${teach.getSchoolClasses().getTitle()} </label>
            </td>
            <td>
                <label id="idUser_UserId${theCount.index}" contenteditable="true"> ${teach.getUser().getUserId()} </label>
            </td>
            <td>
                <%--onclick="updateTeacher('${teach.getSchoolClasses().getTitle()}')--%>
                <button id="update${theCount.index}" onclick="updateTeacher('${teach.getSubject()}')">Update</button>
            </td>
            <td>
                <button id="delete${theCount.index}" onclick="deleteTeacher(${theCount.index})">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </table>
</c:if>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<%--<script type="text/javascript" src="${path}/resources/js/bootstrap.js"/>--%>
<%--<script type="text/javascript" src="${path}/resources/js/jquery-3.2.1.js"/>--%>
</body>
<script>
    function updateTeacher(val) {
        alert(val);

//        private int teacherId;
//        private String name;
//        private String surname;
//        private String phone;
//        private List<subject> subject;
//        private SchoolClasses schoolClasses;
//        private Users user;
//
//            var dataToSend = {
//                teacherId: $('#name').val(),
//                name: $('#surname').val(),
//                surname: $('#parentinfo').val(),
//                phone: $('#email').val(),
//                address: $('#address').val(),
//                libraryCard: {
//                    name: $('#namecard').val(),
//                    expiredDate: $('#expiredDatecard').val(),
//                    status: $('#statuscard').prop('checked')
//                }
//            };
//            $.ajax({
//                type: "POST",
//                url: "/addchild?teacher_id=" + $('#teacher_id').val(),
//                contentType: "application/json; charset=utf-8",
//                data: JSON.stringify(dataToSend),
//                dataType: "json"
//            });
//            modifSchoolchild();
    }
</script>
<script>
    function searchBy() {
        var input, filter, table, tr, td, i;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
</html>