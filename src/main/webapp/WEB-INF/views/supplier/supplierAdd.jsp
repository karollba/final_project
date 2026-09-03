<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">supplierCRUD</h1>
    <a href="<c:url value='/supplier/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-download fa-sm text-white-50"></i> Lista dostawców</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Nowy dostawca</h6>
    </div>

 <div class="card-body">

  <h1>Dodaj produkt</h1>

      <c:if test="${not empty error}">
          <div class="alert alert-danger">
              ${error}
          </div>
      </c:if>


       <form method="post" action="add">
        <form:errors path="quantity" cssClass="text-danger"/>


            <div class="form-group">
                <label for="name">Nazwa dostawcy </label>
                <input value="${supplier.name}" name="name" type="text" class="form-control" id="name" placeholder="name">
            </div>

            <div class="form-group">
                <label for="REGON">REGON</label>
                <input value="${supplier.REGON}" name="REGON" type="number" min="0" class="form-control" id="REGON" placeholder="REGON">
            </div>


            <div class="form-group">
                <label for="NIP">NIP</label>
                <input value="${supplier.NIP}" name="NIP" type="number" min="0" class="form-control" id="NIP" placeholder="NIP">
            </div>


            <div class="form-group">
                <label for="postalCode">Kod pocztowy</label>
                <input value="${supplier.postalCode}" name="postalCode" type="text" class="form-control" id="postalCode" placeholder="postalCode">
            </div>

            <div class="form-group">
                <label for="street">Ulica</label>
                <input value="${supplier.street}" name="street" type="text" class="form-control" id="street" placeholder="street">
            </div>

             <div class="form-group">
                <label for="city">Miejscowosc</label>
                <input value="${supplier.city}" name="city" type="text" class="form-control" id="city" placeholder="city">
            </div>

            <button type="submit" class="btn btn-primary">Dodaj</button>

             </form>
           </div>
               <a href="<c:url value='/supplier/list'/>" class="btn btn-primary"> Strona główna</a>
         </div>
       </div>
<%@ include file="../footer.jsp" %>