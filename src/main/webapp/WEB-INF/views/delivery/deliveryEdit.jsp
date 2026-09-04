<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>



<html>
  <head>
    <title>Edytuj dostawcę</title>
  </head>
  <body>


  <div class="d-sm-flex align-items-center justify-content-between mb-4">
      <h1 class="h3 mb-0 text-gray-800">EmployeeCRUD</h1>
      <a href="<c:url value='/delivery/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
        <i class="fas fa-download fa-sm text-white-50"></i> Lista dostawców</a>
    </div>

   <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Edycja dostawcy</h6>
    </div>

<div class="card-body">
<form method="post" action="edit">

        <input type="hidden" name="id" value="${delivery.id}"/>

          <div class="form-group">
              <label> Nr dostawy: </label>
              <span>${delivery.deliveryId}</span>
               </div>

            <div class="form-group">
                <label for="supplierName">Nazwa dostawcy</label>
                <input value="${delivery.supplierName}" name="supplierName" type="text" class="form-control" id="supplierName" placeholder="supplierName">
            </div>


            <div class="form-group">
                <label for="invoiceDue">Płatność do</label>
                <input value="${delivery.invoiceDue}" name="invoiceDue" type="date" class="form-control" id="invoiceDue" placeholder="invoiceDue">
            </div>

             <div class="form-group">
                <label for="paid">Zapłacono</label>
                <input name="paid" type="checkbox" id="paid" ${delivery.paid ? 'checked' : ''}>
            </div>


            <div class="form-group">
                <label for="category">Kategoria</label>
                <input value="${delivery.category}" name="category" type="text" class="form-control" id="category" placeholder="category">
            </div>

     <button type="submit" class="btn btn-primary">Zapisz</button>

      </form>
         </div>
         <a href="<c:url value='/delivery/deliveryList'/>" class="btn btn-primary"> Strona główna</a>
       </div>
   </div>
<%@ include file="../footer.jsp" %>
