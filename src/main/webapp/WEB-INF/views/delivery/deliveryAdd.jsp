<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">deliveryCRUD</h1>
    <a href="<c:url value='/delivery/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-plus fa-sm text-white-50"></i> Lista dostaw</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Nowa dostawa</h6>
    </div>

 <div class="card-body">

  <h1>Nowa dostawa</h1>

      <c:if test="${not empty error}">
          <div class="alert alert-danger">
              ${error}
          </div>
      </c:if>


       <form method="post" action="add">
        <form:errors path="quantity" cssClass="text-danger"/>


            <div class="form-group">
                <label for="deliveryId">Nr dostawy </label>
                <input value="${delivery.deliveryId}" name="deliveryId" type="text" class="form-control" id="deliveryId" placeholder="deliveryId">
            </div>


           <div clas="form-group">
               <label for="supplier">Dostawca</label>
               <select name="supplier.id" class="form-control" id="supplier">
                   <option value=""> wybierz</option>
                   <c:forEach var="s" items="${suppliers}">
                      <option value="${s.id}" >${s.name}</option>
                   </c:forEach>
               </select>
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
                <label for="acceptingEmployeeId">Pracownik przyjmujący </label>
                <input value="${delivery.acceptingEmployeeId}" name="acceptingEmployeeId" type="text" class="form-control" id="acceptingEmployeeId" placeholder="acceptingEmployeeId">
            </div>

             <div class="form-group">
                <label for="deliveryManId">ID kuriera</label>
                <input value="${delivery.deliveryManId}" name="deliveryManId" type="number" class="form-control" id="deliveryManId" placeholder="deliveryManId">
            </div>

            <div class="form-group">
                <label for="category">Kategoria</label>
                <input value="${delivery.category}" name="category" type="text" class="form-control" id="category" placeholder="category">
            </div>

            <div class="form-group">
                <label for="deliveryIntact">Dostawa nienaruszona</label>
                <input name="deliveryIntact" type="checkbox" id="deliveryIntact" ${delivery.deliveryIntact ? 'checked' : ''}>
            </div>

            <div class="form-group">
                <label for="dateOfAcceptTheDelivery">Data przyjęcia dostawy</label>
                <input value="${delivery.dateOfAcceptTheDelivery}" name="dateOfAcceptTheDelivery" type="date" class="form-control" id="dateOfAcceptTheDelivery" placeholder="dateOfAcceptTheDelivery">
            </div>


            <button type="submit" class="btn btn-primary">Dodaj</button>

             </form>
           </div>
               <a href="<c:url value='/delivery/list'/>" class="btn btn-primary"> Strona główna</a>
         </div>
       </div>
<%@ include file="../footer.jsp" %>