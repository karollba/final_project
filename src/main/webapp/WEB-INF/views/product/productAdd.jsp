<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">ProductCRUD</h1>
    <a href="<c:url value='/product/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-download fa-sm text-white-50"></i> Lista produktów</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Dodawanie produktu</h6>
    </div>

 <div class="card-body">

  <h1>Dodaj produkt</h1>

          <div class="card mb-4">
              <div class="card-body">
                  <h5> zeskanuj kod kreskowy </h5>
                  <form action="/product/scan" method="post" enctype="multipart/form-data">
                  <input type="file" name="file" accept="image/*" class="form-control">
                  <button type="submit" class="btn btn-primary mt-2"> Skanuj </button>
                  </form>
              </div>
          </div>


       <form method="post" action="add">
        <form:errors path="quantity" cssClass="text-danger"/>

            <div class="form-group">
                <label for="name">Nazwa</label>
                <input value="${product.name}" name="name" type="text" class="form-control" id="name" placeholder="name">
            </div>

            <div class="form-group">
                <label for="category">Kategoria</label>
                <input value="${product.category}" name="category" type="text" class="form-control" id="category" placeholder="category">
            </div>

            <div class="form-group">
                <label for="expirationDate">Termin ważności</label>
                <input value="${product.expirationDate}" name="expirationDate" type="date" class="form-control" id="expirationDate" placeholder="expirationDate">
            </div>

            <div class="form-group">
                <label for="quantity">Ilość</label>
                <input value="${product.quantity}" name="quantity" type="number" min="0" class="form-control" id="quantity" placeholder="quantity">
            </div>

            <button type="submit" class="btn btn-primary">Dodaj</button>

             </form>
           </div>
               <a href="<c:url value='/product/list'/>" class="btn btn-primary"> Strona główna</a>
         </div>
       </div>
<%@ include file="../footer.jsp" %>