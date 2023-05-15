<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta http-equiv=”Content-Type” content=”text/html; charset=utf-8″>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>Centro Hospitalar Upskill :: Consultas</title>
    <link href="/styles/sidebarStyles.css" rel="stylesheet">
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/doctorManagementStyles.css" rel="stylesheet">
</head>

<body>

<!-- cust-sub-body-cont -->
<div class="container-fluid h-100 cust-sub-body-cont">
    <!-- cust-main-sb-and-maincont-body-ctn -->
    <div class="row h-100 cust-row-sb-and-maincont-ctn">
        <%@include file="templates/sidebar.jsp"%>

        <!-- Main Content Container -->
        <div class="cust-mainContContainer h-100 col-md d-flex flex-column overflow-auto">

            <div class="cust-title1-ctn">
                <div class="cust-title1-txt col-md px-3 m-2">Especialidades</div>
            </div>

            <c:if test="${specialtyUpdated != null}">
                <div class="alert alert-success">
                        ${specialtyUpdated}
                </div>
            </c:if>

            <div class="row">
                <div class="col-md">

                    <div class=" cust-header-ctn d-flex flex-md-wrap">

                        <form method="get" class="cust-header-menu-ctn m-2">
                            <button formaction="registerSpecialty" type="submit" class="btn btn-primary cust-btn-blu-Up">Nova Especialidade</button>
                        </form>

                    </div>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Especialidade</th>
                            <th>Número de Médicos</th>
                            <th>Acção</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="specialty" items="${specialtyList}">
                            <tr>
                                <td>${specialty.getName()}</td>
                                <td>${specialtiesDoctorCount.get(specialty)}</td>

                                <td>
                                    <form method="get">
                                        <input value="${specialty.getId()}" name="id" type="hidden">
                                        <button formaction="editSpecialty" class="btn btn-sm btn-success" type="submit">Editar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>


        </div> <!-- END Main Content Container -->

    </div> <!-- END cust-main-sb-and-maincont-body-ctn -->

</div> <!-- END cust-sub-body-cont -->

</body>
</html>