<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>



<html>
  <head>
    <title>Edytuj pracownika</title>
  </head>
  <body>


  <div class="d-sm-flex align-items-center justify-content-between mb-4">
      <h1 class="h3 mb-0 text-gray-800">EmployeeCRUD</h1>
      <a href="<c:url value='/product/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
        <i class="fas fa-plus fa-sm text-white-50"></i> Lista pracowników</a>
    </div>

   <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Edycja pracownika</h6>
    </div>

<div class="card-body">
<form method="post" action="edit">

        <input type="hidden" name="id" value="${product.id}"/>

          <div class="form-group">
                <label for="name">Nazwa</label>
                <input value="${product.name}" name="name" type="text" class="form-control" id="name" placeholder="Nazwa">
            </div>

            <div class="form-group">
                <label for="category">Kategoria</label>
                <input value="${product.category}" name="category" type="text" class="form-control" id="category" placeholder="Kategoria">
            </div>

            <div class="form-group">
                <label for="expirationDate">Termin ważności</label>
                <input value="${product.expirationDate}" name="expirationDate" type="date" class="form-control" id="expirationDate" placeholder="Termin ważności">
            </div>

            <div class="form-group">
                <label for="quantity">Ilość</label>
                <input value="${product.quantity}" name="quantity" type="number" class="form-control" id="quantity" placeholder="Ilość">
            </div>

     <button type="submit" class="btn btn-primary">Zapisz</button>

      </form>
         </div>
         <a href="<c:url value='/product/productList'/>" class="btn btn-primary"> Strona główna</a>
       </div>
   </div>
<%@ include file="../footer.jsp" %>
