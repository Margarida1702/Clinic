<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/styles/sidebarStyles.css" rel="stylesheet">

<!-- Sidebar Begin -->
<nav class="col-md-2 cust-sidebar d-flex flex-column h-md-100 p-0 navbar navbar-expand-md flex-nowrap">

    <!-- Sidebar Logo Begin-->
    <div class="cust-navbar-logo text-center p-3 d-flex w-100 justify-content-between">
        <a href="/dashboard">
            <img class="img-fluid h-auto" src="/images/common/centro_hospitalar_upskill_logo.svg" alt="centro_hospitalar_upskill_logo">
        </a>

        <button class="navbar-toggler d-md-none d-block" type="button" data-bs-toggle="collapse" data-bs-target="#collapsible-sidebar" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>

    <!-- Sidebar Logo End-->

    <!-- Collapsible nav -->
    <div class="navbar-collapse collapse w-100 flex-column align-items-start" id="collapsible-sidebar">


        <!-- Sidebar Greetings Begin-->
        <div class="cust-greetings pb-3 px-4">
            <div class="cust-greet-txt">Olá de novo,</div>
            <div class="cust-username-txt">${user.getUsername()}</div>
            <div class="cust-userType-txt">${user.getUserSimpleTypeToString()}</div>

        </div>
        <!-- Sidebar Greetings End-->

        <c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}"/>

        <!-- Sidebar Menu Container -->
        <div class="cust-menu-contn justify-content-between d-flex flex-column w-100 p-0">
            <!-- Sidebar-Main-Nav Begin-->
            <div class="container-fluid mt-4 p-0">
                <ul class="navbar-nav">
                    <li class="${url == "/dashboard" ? "active" : ""} nav-item cust-sb-nav-select">
                        <a class="nav-link" href="/dashboard">
                            <span class="cust-menu-icon"><img src="/images/sidebar/home.svg" alt="home.svg"></span>Início
                        </a>
                    </li>
                    <c:if test="${user.getRole().getName() == 'ROLE_ADMIN'}">

                        <li class="${url == "/userManagement" ? "active" : ""} nav-item cust-sb-nav-select">
                            <a class="nav-link" href="/userManagement">
                                <span class="cust-menu-icon"><img src="/images/sidebar/user.svg" alt="user.svg"></span>Utilizadores
                            </a>
                        </li>
                    </c:if>


                    <c:if test="${user.getRole().getName() == 'ROLE_UNITMANAGER' || user.getRole().getName() == 'ROLE_DOCTOR' ||
                        user.getRole().getName() == 'ROLE_DESKRECEPTIONIST'}">

                        <li class="${url == "/patient_list" ? "active" : ""} nav-item cust-sb-nav-select">
                                <a class="nav-link" href="/patient_list">
                                    <span class="cust-menu-icon"><img src="/images/sidebar/user.svg" alt="user.svg"></span>Utentes
                                </a>
                        </li>
                    </c:if>
                    <c:if test="${user.getRole().getName() != 'ROLE_ADMIN'}">
                        <li class="${url == "/visualiseDoctors" ? "active" : ""} nav-item cust-sb-nav-select">
                            <a class="nav-link" href="/visualiseDoctors">
                                <span class="cust-menu-icon"><img src="/images/sidebar/doctor.svg" alt="doctor.svg"></span>Médicos
                            </a>

                        </li>

                    </c:if>

                    <c:if test="${user.getRole().getName() == 'ROLE_ADMIN'}">
                        <li class="${url == "/specialtyManagement" ? "active" : ""} nav-item cust-sb-nav-select">
                            <a class="nav-link" href="/specialtyManagement">
                                <span class="cust-menu-icon"><img src="/images/sidebar/medicine-svgrepo-com.png" alt="specialty.svg"></span>Especialidades
                            </a>

                        </li>
                    </c:if>

                    <c:if test="${user.getRole().getName() != 'ROLE_ADMIN'}">
                        <li class="${url == "/visualiseSpecialties" ? "active" : ""} nav-item cust-sb-nav-select">
                            <a class="nav-link" href="/visualiseSpecialties">
                                <span class="cust-menu-icon"><img src="/images/sidebar/medicine-svgrepo-com.png" alt="specialty.svg"></span>Especialidades
                            </a>
                        </li>
                    </c:if>

                    <li class="${url == "/appointments_main" ? "active" : ""} nav-item cust-sb-nav-select">
                        <a class="nav-link" href="/appointments_main">
                            <span class="cust-menu-icon"><img src="/images/sidebar/appointment.svg" alt="appointment.svg"></span>Consultas
                        </a>

                    </li>
                    <li class="${url == "/editAccountDetails" ? "active" : ""} nav-item cust-sb-nav-select">
                        <a class="nav-link" href="/editAccountDetails">
                            <span class="cust-menu-icon"><img src="/images/sidebar/settings-cust-svgrepo-com.png" alt="appointment.svg"></span>Definições
                        </a>

                    </li>


                    <li class="nav-item cust-sb-menu-title">
                        <div>Acessos Rápidos</div>
                    </li>
                    <li class="${url == "/generalInformation" ? "active" : ""} nav-item cust-sb-nav-select">
                        <a class="nav-link" href="/generalInformation">
                            <span class="cust-menu-icon"><img src="/images/sidebar/generalInf.png" alt="generalInf.png"></span>Informações Gerais
                        </a>

                    </li>
                    <li class="${url == "/appointments_choose_specialty" ? "active" : ""} nav-item cust-sb-nav-select">
                        <a class="nav-link" href="/appointments_choose_specialty">
                            <span class="cust-menu-icon"><img src="/images/sidebar/calendar.png" alt="calendar.png"></span>Calendário de vagas
                        </a>

                    </li>
                    <li class="${url == "/contacts" ? "active" : ""} nav-item cust-sb-nav-select">
                        <a class="nav-link" href="/contacts">
                            <span class="cust-menu-icon me-2"><img src="/images/sidebar/noun_Phone.png" alt="noun_Phone.png"></span>Contactos
                        </a>

                    </li>

                </ul>
            </div>
            <!-- Sidebar-Main-Nav End-->


            <!-- Sidebar-Bottom-Menu Start-->
            <div class="row cust-sb-b-contn">
                <!-- Sidebar-Logo-Small Start-->
                <div class="cust-img-logo-small">
                    <img src="/images/sidebar/upskill_logo.png" alt="centro_hospitalar_upskill_logo">
                </div>
                <!-- Sidebar-Logo-Small End-->

                <div class="container-fluid">
                    <ul class="navbar-nav bt">
                        <li class="nav-item cust-sb-nav-select">
                            <a class="nav-link" href="\logout">
                                <span class="cust-menu-icon me-2"><img src="/images/sidebar/noun_logout_541763.svg" alt="noun_logout_541763.svg"></span>Terminar Sessão
                            </a>

                        </li>
                    </ul>
                </div>

            </div>
            <!-- Sidebar-Bottom-Menu End-->
        </div>
        <!-- Sidebar Menu Container End -->

    </div>
</nav>
<!-- End Sidebar -->