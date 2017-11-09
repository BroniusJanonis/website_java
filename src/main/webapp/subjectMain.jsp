<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Subjects</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xl-2">
            <%@include file="headerMain.jsp"%>
        </div>
        <div class="col-xl-10">
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

            <input  class="border border-primary" type="text" id="myInput" onkeyup="searchBy()" placeholder="Ieškoti pagal dalyka..." >
            <br>
            <button class="btn btn-success addSubject" data-toggle="modal" data-target="#myModal" onclick="addSubject()">Add Subject</button>
            <table class="table table-bordered table-striped" id="myModalTable">
                <thead>
                <tr>
                    <th class="" hidden>subjectId</th>
                    <th class="">subjectName</th>
                    <th class="">teacher</th>
                    <th class="">Update</th>
                    <th class="">Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="subject" items="${subjectsList}" varStatus="theCount">
                    <tr>
                        <td style="text-align:center;" class="" hidden>${subject.getSubjectId()}</td>
                        <td style="text-align:center;" class="">${subject.getSubjectName()}</td>
                        <td>
                            <c:forEach var="teacher" items="${subject.getTeachersList()}">
                                <label style="text-align:center;" class="" hidden>${teacher.getTeacherId()}</label>
                                <label style="text-align:center;" class="">${teacher.getName()} ${teacher.getSurname()}</label>
                                <br>
                            </c:forEach>
                        </td>
                        <td style="text-align:center;" class="">

                            <button class="btn btn-success updateSubject" data-toggle="modal" data-target="#myModal" contenteditable="false">Update</button>
                        </td>
                        <td style="text-align:center;" class="">
                            <button class="btn btn-success" id="delete${theCount.index}" onclick="deleteSubject(${subject.getSubjectId()})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--Add/ Update Modal--%>
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
                            <h4 class="modal-title" id="myModalLabel">Classes</h4>
                            <button type="button" class="close" data-dismiss="modal"> <span aria-hidden="true" class="">×   </span><span class="sr-only">Close</span>
                            </button>
                        </div>
                        <div class="modal-body"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
<script>
    function searchBy() {
        var input, filter, table, tr, td, i;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myModalTable");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
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
    $(".updateSubject[data-target='#myModal']").click(function() {
        var columnHeadings = $("thead th").map(function() {
            return $(this).text();
        }).get();
        var columnValues = $(this).parent().siblings().map(function() {
            return $(this).text();
        }).get();
        var subjectTitles = $(this).parent().siblings(4).children("p").children("label").map(function() {
            return $(this).text();
        }).get();
        var subjectIds = $(this).parent().siblings(4).children("p").children("input").map(function () {
            return $(this).val();
        });
        var modalBody = $('<div id="modalContent"></div>');
        var modalForm = $('<form role="form" name="modalForm" action="updateSubject" method="post"></form>');
        $.each(columnHeadings, function(i, columnHeader) {
            var formGroup = $('<div class="form-group"></div>');
            if (columnHeader == "Update" || columnHeader == "Delete"){
                return;
            }else if (columnHeader == "subjectId"){
                formGroup.append('<input class="form-control" name="' + columnHeader + '" value="' + columnValues[i] + '" hidden/>');
            }else if(columnHeader == "teacher") {
                formGroup.append('<label>' + columnHeader + '</label>');
                var teacherGroup = $('<select name="teacherId" multiple/>').appendTo(formGroup);
                var teacherId = 0;
                teacherGroup.append('<option value="'+teacherId+'">'+"Nepasirinktas mokytojas"+'</option>');
                <c:forEach var="teacher" items="${allTeachers}">
                teacherGroup.append('<option value="' + "${teacher.getTeacherId()}" + '">' + "${teacher.getName()} ${teacher.getSurname()}" + '</option>');
                </c:forEach>
            }else{
                formGroup.append('<input class="form-control" name="' + columnHeader + '" value="' + columnValues[i] + '" hidden/>');
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
<script>
    function addSubject() {
        $('#h4 .modal-title').innerText = "Add";
        var modalBody = $('<div id="modalAddContent"></div>');
        var modalForm = $('<form role="form" name="modalForm" action="addSubject" method="post"></form>');
        var formGroup = $('<div class="form-group"></div>');
        formGroup.append('<label> SubjectName </label>');
        formGroup.append('<input class="form-control" name="subjectName"/>');
        modalForm.append(formGroup);
        modalBody.append(modalForm);
        $('.modal-body').html(modalBody);

        $('.modal-footer .btn-primary').click(function() {
            $('form[name="modalForm"]').submit();
        });
    }
</script>
<script>
    function deleteSubject(subjectId) {
        $.post("/deleteSubject?subjectId="+subjectId, function (data) {
            location.reload(true);
        });
    }
</script>
</html>
