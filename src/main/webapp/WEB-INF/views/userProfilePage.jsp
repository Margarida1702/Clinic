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
    <title>Centro Hospitalar Upskill :: Utilizadores</title>
    <link href="/styles/sidebarStyles.css" rel="stylesheet">
    <link href="/styles/genericStyles.css" rel="stylesheet">
    <link href="/styles/userProfilePage.css" rel="stylesheet">
</head>

<body>

<!-- cust-sub-body-cont -->
<div class="container-fluid h-100 cust-sub-body-cont">
    <!-- cust-main-sb-and-maincont-body-ctn -->
    <div class="row h-100 cust-row-sb-and-maincont-ctn">
        <%@include file="templates/sidebar.jsp"%>

        <!-- Main Content Container -->
        <div class="cust-mainContContainer h-100 col-md d-flex flex-column overflow-auto">
            <c:set var="isSameUser" value="${profileUser.getId().equals(user.getId())}"/>
            <div class="cust-title1-ctn">
                <div class="cust-title1-txt col-md px-3 m-2">Perfil de Utilizador</div>
            </div>

            <!--Container Needed to Contain and limit Menu COL Height START -->
            <div class="row ">

                <div class="cust-header-opt-ctn col d-flex flex-wrap"> <!--Wrapping Style Button Menu Col START-->
                    <c:if test="${user.getRole().getName() == 'ROLE_ADMIN' || isSameUser}">
                        <form method="get" class="cust-header-menu-ctn m-2">
                            <input value="${profileUser.getId()}" name="profileUserId" type="hidden">
                            <button formaction="edit_user_profile" type="submit" class="btn btn-primary cust-btn-blu-Up h-100 ">Editar Conta</button>
                        </form>
                    </c:if>
                    <c:if test="${user.getRole().getName() == 'ROLE_ADMIN' || user.getRole().getName() == 'ROLE_UNITMANAGER'
                        || (isSameUser && !(user.getRole().getName() == 'ROLE_PATIENT'))}">
                        <form method="get" class="cust-header-menu-ctn m-2">
                            <input value="${profileUser.getId()}" name="profileUserId" type="hidden">
                            <button formaction="#" type="submit" class="btn btn-primary cust-btn-blu-Up h-100 ">Horários</button>
                        </form>
                    </c:if>

                    <c:if test="${user.getRole().getName() == 'ROLE_ADMIN' || isSameUser}">
                        <form method="get" class="cust-header-menu-ctn m-2">
                            <input value="${profileUser.getId()}" name="profileUserId" type="hidden">
                            <button formaction="#" type="submit" class="btn btn-primary cust-btn-blu-Up h-100">Desactivar Conta</button>
                        </form>
                    </c:if>
                </div> <!--Wrapping Style Button Menu Col END-->

            </div><!--Container Needed to Contain and limit Menu COL Height END -->

            <!--Profile Container START-->
            <div class="cus-prof-ctn d-flex flex-column align-items-center mt-4">

                <!-- Profile Image Container START-->
                <div class="cus-img-prof-ctn d-flex justify-content-center mt-2">
                    <div class="card col-9 d-flex flex-column justify-content-between align-items-center">

                        <c:if test="${profileUser.getRole().getName() == 'ROLE_ADMIN'}">
                            <img class= "portrait-photo mt-3 mx-5" src="/images/user_profile_photos/admin_profile_pic.png" width="300" height="300" alt="admin_profile_pic.png">

                            <div class="card-body text-center">
                                <h5 class="card-title text-center">Administrador</h5>
                            </div>

                        </c:if>

                        <c:if test="${profileUser.getRole().getName() == 'ROLE_PATIENT'}">
                            <img class= "portrait-photo mt-3 mx-5" src="/images/user_profile_photos/patient_profile_pic.png" width="300" height="300" alt="admin_profile_pic.png">

                            <div class="card-body text-center">
                                <h5 class="card-title text-center">Utente</h5>
                            </div>

                        </c:if>

                        <c:if test="${profileUser.getRole().getName() == 'ROLE_UNITMANAGER'}">
                            <img class= "portrait-photo mt-3 mx-5" src="/images/user_profile_photos/unitManager_profile_pic copy.png" width="300" height="300" alt="admin_profile_pic.png">

                            <div class="card-body text-center">
                                <h5 class="card-title text-center">Chefe de Secção</h5>
                            </div>

                        </c:if>

                        <c:if test="${profileUser.getRole().getName() == 'ROLE_DOCTOR'}">
                            <img class= "portrait-photo mt-3 mx-5" src="/images/user_profile_photos/doctor_profile_pic.png" width="300" height="300" alt="admin_profile_pic.png">

                            <div class="card-body text-center">
                                <h5 class="card-title text-center">Doutor</h5>
                            </div>

                        </c:if>

                        <c:if test="${profileUser.getRole().getName() == 'ROLE_DESKRECEPTIONIST'}">
                            <img class= "portrait-photo mt-3 mx-5" src="/images/user_profile_photos/deskReceptionist_profile_pic.png" width="300" height="300" alt="admin_profile_pic.png">

                            <div class="card-body text-center">
                                <h5 class="card-title text-center">Recepcionista</h5>
                            </div>

                        </c:if>

                    </div>


                </div><!-- Profile Image Container END-->

                <c:choose>
                <c:when test="${user.getRole().getName().equals('ROLE_PATIENT') && profileUser.getRole().getName().equals('ROLE_PATIENT') && !isSameUser}">
                    <div class="text_t">Acesso interdito
                    </div>
                </c:when>
                <c:otherwise>
                <table class="table table-lg table-striped">
                    <thead>
                    <tr>
                        <td>Nome</td>
                        <td>${profileUser.getName()}</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Nome de utilizador</td>
                        <td>${profileUser.getUsername()}</td>
                    </tr>
                    <tr>
                        <td>Data de nascimento</td>
                        <td>${profileUser.getBirthdate()}</td>
                    </tr>
                    <c:if test="${isSameUser || !user.getRole().getName().equals('ROLE_PATIENT')}">
                        <tr>
                            <td>Morada</td>
                            <td>${profileUser.getAddress()}</td>
                        </tr>
                        <tr>
                            <td>Telemóvel</td>
                            <td>${profileUser.getPhoneNumber()}</td>
                        </tr>
                        <tr>
                            <td>NIF</td>
                            <td>${profileUser.getNIF()}</td>
                        </tr>
                    </c:if>
                    <c:if test="${profileUser.getRole().getName().equals('ROLE_DOCTOR')}">
                        <tr>
                            <td>Cédula Profissional</td>
                            <td>${profileUser.getProfessionalCertificateNumber()}</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

                <div class="cus-prof-data-ctn d-flex justify-content-start w-100 mt-5">




                </div>
            </div><!--Profile Container END-->


        </div> <!-- END Main Content Container -->

        </c:otherwise>
        </c:choose>

    </div> <!-- END cust-main-sb-and-maincont-body-ctn -->

</div> <!-- END cust-sub-body-cont -->

</body>
</html>