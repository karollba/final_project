
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/theme/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/theme/css/sb-admin-2.css" rel="stylesheet">




</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">


        <!-- Divider -->
        <hr class="sidebar-divider my-0">



            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Zarządzanie
            </div>

            <!-- Nav Item - Produkty -->

            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/product/list">
                    <i class="fas fa-fw fa-box"></i>
                    <span>Produkty</span></a>
            </li>

            <!-- Nav Item - Zamówienia -->
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/productorder/list">
                    <i class="fas fa-box"></i>
                    <span>Zamówienia</span></a>
            </li>

            <!-- Nav Item - Dostawy -->
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/delivery/list">
                    <i class="fas fa-fw fa-truck"></i>
                    <span>Dostawy</span></a>
            </li>

            <!-- Nav Item - Pracownicy -->
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/employee/list">
                    <i class="fas fa-fw fa-users"></i>
                    <span>Pracownicy</span></a>
            </li>

           <!-- Nav Item - Dostawcy -->
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/supplier/list">
                    <i class="fas fa-fw fa-handshake"></i>
                    <span>Dostawcy</span></a>
            </li>



            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>


                 <ul class="navbar-nav ml-auto">
                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <span class="mr-2 d-none d-lg-inline text-gray-800 small font-weight-bold">
                           ${loggedInEmployee.firstName} ${loggedInEmployee.lastName}
                        </span>
                            <a href="${pageContext.request.contextPath}/logout" class="btn btn-sm btn-outline-danger">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Wyloguj
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>

