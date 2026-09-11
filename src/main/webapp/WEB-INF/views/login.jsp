<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


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



   <!-- Begin Page Content -->
      <div class="container-fluid" style="max-width: 400px; margin-top: 100px;">
        <div class="card shadow">
            <div class="card-body">
                <h3 class="text-center mb-4">Logowanie</h3>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger"> Nieprawidłowy login lub hasło</div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success"> Wylogowano pomyślnie</div>
                </c:if>


           <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label for="username"> Login</label>
                    <input type="text" name="username" class="form-control" placeholder="Nazwa użytkownika" id="username">
                </div>
                <div class="form-group">
                    <label for="password"> Hasło </label>
                    <input type="password" name="password" class="form-control" placeholder="Hasło" id="password">
                </div>
                <button type="submit" class="btn btn-primary btn-block"> Zaloguj </button>
            </form>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
