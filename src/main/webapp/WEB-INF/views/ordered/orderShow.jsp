<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Szczegóły zamówienia</h1>
  </div>

<a href="${pageContext.request.contextPath}/productorder/list" class="btn btn-sm btn-primary">  Lista zamówień</a>
  <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h3 class="m-0 font-weight-bold text-primary">Zamówienie nr: ${order.orderNumber}</h3>
             <h6 class="m-0 text-primary"> Dostawca: ${order.supplier.name}</h6>
             <h6 class="m-0  text-primary"> Data złożenia zamówienia: ${order.orderDate}</h6>
  </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary"> Pozycje </h6>
        </div>
    <div class="card-body">
        <table class="table">
            <thead>
            <tr>
                <th>Produkt</th>
               <th>Jednostka</th>
                <th>Zamówiono</th>
                <th>Otrzymano</th>
                <th>Termin przydatności</th>
                <th>Status</th>
                <th>Operacja</th>

            </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}">
                  <tr>
                    <td>${item.product.name}</td>
                    <td>${item.unit}</td>
                    <td>${item.orderedQuantity}</td>

                  <td>
                    <c:choose>
                        <c:when test="${item.checked}">
                            ${item.recievedQuantity}
                        </c:when>
                        <c:otherwise>
                             <form id="form-${item.id}" action="${pageContext.request.contextPath}/productorder/updatequantity" method="post" class="form-inline">
                                <input type="hidden" name="id" value="${item.id}">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <input type="number" step="0.1" name="recievedQuantity" class="form-control" style="width:100px" required>
                               </form>
                          </c:otherwise>
                  </c:choose>
                  </td>
                  <td>
                      <c:choose>
                          <c:when test="${item.checked}">
                            ${item.expirationDate}
                         </c:when>
                      <c:otherwise>
                          <input type="date" name="expirationDate" class="form-control" style="width:150px" form="form-${item.id}" required>
                     </c:otherwise>
                     </c:choose>
                   </td>

                    <td>
                        <c:if test="${item.checked}">
                             <c:choose>
                                 <c:when test="${item.matches}">
                                     <span class="badge badge-success"> Zgadza się</span>
                                 </c:when>
                                 <c:otherwise>
                                     <span class="badge badge-danger"> Rozbieżność</span>
                                 </c:otherwise>
                             </c:choose>
                         </c:if>
                         <c:if test="${!item.checked}">
                           <span class="badge badge-warning"> Nie sprawdzono!</span>
                         </c:if>
                     </td>
                    <td>
                     <c:if test="${!item.checked}">
                        <button type="submit" form="form-${item.id}" class="btn btn-sm btn-primary ml-2">Zatwierdź </button>
                    </c:if>
                    </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="../footer.jsp" %>



