<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Dodaj partię produktu</h1>
    <a href="<c:url value='${pageContext.request.contextPath}/product/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-plus fa-sm text-white-50"></i> Lista produktów</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Nowa partia</h6>
    </div>

 <div class="card-body">

       <form method="post" action="${pageContext.request.contextPath}/product/addbatch" id="orderForm">

           <div clas="form-group">
               <label for="product">Produkt</label>
               <select name="product.id" class="form-control" id="product">
                   <option value=""> wybierz</option>
                   <c:forEach var="p" items="${products}">
                      <option value="${p.id}" >${p.name}</option>
                   </c:forEach>
               </select>
           </div>


            <div class="form-group">
                <label for="quantity"> Ilość </label>
                <input type="number" step="0.1" name="quantity" class="form-control" id="quantity" placeholder="Ilość">
            </div>

            <div class="form-group">
                <label for="expirationDate"> Termin ważności </label>
                <input type="date" name="expirationDate" class="form-control" id="expirationDate">
            </div>


            <button type="submit" class="btn btn-primary"> Dodaj partię</button>



               <a href="<c:url value='/delivery/list'/>" class="btn btn-primary"> Strona główna</a>
         </div>
       </div>
<%@ include file="../footer.jsp" %>