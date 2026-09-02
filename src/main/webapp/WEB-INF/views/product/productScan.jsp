<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Skanuj produkt</h1>
  </div>

 <div class="card-body">



       <div id="result" class="alert alert-info" style="display:none">
            Zeskanowano: <span id="barcodeResult"></span>
       </div>

       <div id="scanner" style="width:500px"></div>

       <form action="/product/scan" method="post">
       <input type="text" id="barcodeInput" name="barcode"
       class="form-control" placeholder="lub wpisz kod ręcznie">
       <button type="submit" class="btn btn-primary mt-2"> Szukaj</button>
       </form>
       </div>



  <h1>Dodaj produkt</h1>

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