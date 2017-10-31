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

<input  class="border border-primary" type="text" id="myInput" onkeyup="searchBy()" placeholder="Ieškoti pagal ..." >
<br>
 ${teachersList.get(0).getName()}

<table class="table table-bordered table-striped" id="myModalTable">
    <thead>
    <tr>
        <th class="">teacherId</th>
        <th class="">name</th>
        <th class="">surname</th>
        <th class="">phone</th>
        <th class="">subject</th>
        <th class="">schoolClassesId</th>
        <th class="">schoolClassesTitle</th>
        <th class="">userId</th>
        <th class="">Update</th>
        <th class="">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="teach" items="${teachersList}" varStatus="theCount">
        <tr>
                <%--<label id="surname${theCount.index}" type="text" name="surname" value="${teach.getSurname()}" hidden/>--%>
            <td style="text-align:center;" class="">${teach.getTeacherId()}</td>
            <td style="text-align:center;" class="">${teach.getName()}</td>
            <td style="text-align:center;" class="">${teach.getSurname()}</td>
            <td style="text-align:center;" class="">${teach.getPhone()}</td>
            <td style="text-align:center;" class="">
                <c:forEach var="subj" items="${teach.getSubject()}" varStatus="subjCount">
                    <p style="text-align:center;" class="">
                        <input type="number" value="${subj.getSubjectId()}" hidden />
                        <label style="text-align:center;" class="">${subj.getSubjectName()}</label>
                    </p>
                </c:forEach>
            </td>
            <td style="text-align:center;" class="">${teach.getSchoolClasses().getSchoolClassesId()}</td>
            <td style="text-align:center;" class="">${teach.getSchoolClasses().getTitle()}</td>
            <td style="text-align:center;" class="">${teach.getUser().getUserId()}</td>
            <td style="text-align:center;" class="">
                <button class="btn btn-success" data-toggle="modal" data-target="#myModal" contenteditable="false">Update</button>
            </td>
            <td style="text-align:center;" class="">
                <button class="btn btn-success" data-toggle="modal" data-target="#myModal"
                        id="delete${theCount.index}" onclick="deleteTeacher(${theCount.index})">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true" class="">×   </span><span class="sr-only">Close</span>

                </button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>

            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<%--<script type="text/javascript" src="${path}/resources/js/bootstrap.js"/>--%>
<%--<script type="text/javascript" src="${path}/resources/js/jquery-3.2.1.js"/>--%>
</body>
<script>
    function searchBy() {
        var input, filter, table, tr, td, i;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myModalTable");
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
<%--MODAL--%>
<script>
    $("#button_modal_close, #button_modal_symbol_x").click(function () {
        $(".modal-body").html("Cleared Data")
    });
    $('#myModal').on('hidden.bs.modal', function () {
        $(".modal-body").html("Cleared Data")
    });
</script>
<script>
    $(".btn[data-target='#myModal']").click(function() {
        // Row Column headings
        var columnHeadings = $("thead th").map(function() {
            return $(this).text();
        }).get();
        console.log(columnHeadings);
        columnHeadings.pop();
        // Row Column Values
        var columnValues = $(this).parent().siblings().map(function() {
            return $(this).text();
        }).get();
//        var subjectValues = $(this).parent().siblings().get(4).innerText;
//        var subjectSize = $("td p").length;
//        $("td p")[0]
        // Subject lists Titles
        var subjectTitles = $(this).parent().siblings(4).children("p").children("label").map(function() {
            return $(this).text();
        }).get();
//        var subjectSize = $(this).parent().siblings(4).children("p").length;
//        [4].children["0"].innerText
        // Subject Lists Id
        var subjectIds = $(this).parent().siblings(4).children("p").children("input").map(function () {
            return $(this).val();
        });
        console.log(subjectIds);
        var modalBody = $('<div id="modalContent"></div>');
        var modalForm = $('<form role="form" name="modalForm" action="saveAndFlushTeacher" method="post"></form>');
        $.each(columnHeadings, function(i, columnHeader) {
            console.log(columnHeader);
            var formGroup = $('<div class="form-group"></div>');
            if(columnHeader == "subject"){
//                var columnSubjects = $("tbody tr td p").map(function() {
//                    return $(this).text();
//                }).get();
//                var columnSubjectValues = $(this).parent().siblings().map(function() {
//                    return $(this).text();
//                }).get();
                var subjectGroup = $('<div class="subject-group"></div>').appendTo(formGroup);
                subjectGroup.append('<label for="'+columnHeader+'">'+columnHeader+'</label>');
                $.each(subjectTitles, function (i, columnSubject) {
                    subjectGroup.append('<input class="form-control" name="subjectName" id="'+columnHeader+i+'" value="'+subjectTitles[i]+'" />');
                    subjectGroup.append('<input class="form-control" name="subjectId" id="subjectId'+subjectIds[i]+'" value="'+subjectIds[i]+'" />');
                });
            } else {
                formGroup.append('<label for="' + columnHeader + i + '">' + columnHeader + '</label>');
                formGroup.append('<input class="form-control" name="' + columnHeader + '" id="' + columnHeader + i + '" value="' + columnValues[i] + '" />');
            }
            modalForm.append(formGroup);
        });
        modalBody.append(modalForm);
        $('.modal-body').html(modalBody);
    });
    $('.modal-footer .btn-primary').click(function() {
        $('form[name="modalForm"]').submit();
    });
</script>
</html>