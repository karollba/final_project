<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="header.jsp" %>



<html>
  <head>
    <title>Edytuj pracownika</title>
  </head>
  <body>


  <div class="d-sm-flex align-items-center justify-content-between mb-4">
      <h1 class="h3 mb-0 text-gray-800">EmployeeCRUD</h1>
      <a href="<c:url value='/employee/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
        <i class="fas fa-download fa-sm text-white-50"></i> Lista pracowników</a>
    </div>

   <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Edycja pracownika</h6>
    </div>

<div class="card-body">
<form method="post" action="edit">

<input type="hidden" name="id" value="${employee.id}"/>


            <div class="form-group">
                <label for="firstName">Imię</label>
                <input value="${employee.firstName}" name="firstName" type="text" class="form-control" id="firstName" placeholder="firstName">
            </div>

            <div class="form-group">
                <label for="lastName">Nazwisko</label>
                <input value="${employee.lastName}" name="lastName" type="text" class="form-control" id="lastName" placeholder="lastName">
            </div>

            <div class="form-group">
                <label for="password">Hasło</label>
                <input name="password" type="password" class="form-control" id="password" placeholder="password">
            </div>

            <div class="form-group">
                <label for="adminAccess">Uprawnienia Admina</label>
                <input name="adminAccess" type="checkbox" id="adminAccess" placeholder="adminAccess" value="true">
            </div>
     <button type="submit" class="btn btn-primary">Zapisz</button>

      </form>
         </div>
         <a href="<c:url value='/employee/list'/>" class="btn btn-primary"> Strona główna</a>
       </div>
   </div>
<%@ include file="footer.jsp" %>
