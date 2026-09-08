<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">deliveryCRUD</h1>
    <a href="<c:url value='/ordered/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-plus fa-sm text-white-50"></i> Lista zamówień</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Nowe zamówienie</h6>
    </div>

 <div class="card-body">

  <h1>Nowa dostawa</h1>

      <c:if test="${not empty error}">
          <div class="alert alert-danger">
              ${error}
          </div>
      </c:if>


       <form method="post" action="${pageContext.request.contextPath}/delivery/addwithitems" id="orderForm">



           <div clas="form-group">
               <label for="supplier">Dostawca</label>
               <select name="supplierId" class="form-control" id="supplier">
                   <option value=""> wybierz</option>
                   <c:forEach var="s" items="${suppliers}">
                      <option value="${s.id}" >${s.name}</option>
                   </c:forEach>
               </select>
           </div>

            <hr>
            <h5> Produkty w zamównieniu </h5>

            <table class="table" id="itemsTable">
            <thead>
            <tr>
               <th>Produkt</th>
               <th>Zamówiona ilość</th>
               <th></th>
           </tr>
           </thead>
           <tbody id="itemsBody">
            <tr>
            <td>
                <select name="productIds" class="form-control">
                <c:forEach var="p" items="${products}">
                    <option value="${p.id}">${p.name}</option>
                    </c:forEach>
                    </select>
                    </td>
                    <td>
                    <input type="number" step="0.1" name="orderedQuantities" class="form-control">
                    </td>
                    </tr>
                    </tbody>
                    </table>



            <button type="button" id="addRow"  class="btn btn-secondary btn-sm">Dodaj produkt</button>
            <br></br>
            <button type="submit" class="btn btn-primary"> Złóż zamówienie</button>
             </form>
           </div>


           <script>
                document.getElementById('addRow').addEventListener('click', function() {
                    const body = document.getElementById('itemsBody');
                    const newRow = body.rows[0].cloneNode(true);
                    newRow.queryselectorAll('input').forEach(input => input.value = '');
                    body.appendChild(newRow);
                });

                document.getElementById('itemsBody').addEventListener('click', function(e) {
                    if (e.target.classList.contains('removeRow')) {
                        if (document.getElementById('itemsBody').rows.length > 1) {
                            e.target.closest('tr').remove();
                            }
                        }
                    });
           </script>


               <a href="<c:url value='/delivery/list'/>" class="btn btn-primary"> Strona główna</a>
         </div>
       </div>
<%@ include file="../footer.jsp" %>