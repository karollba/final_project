<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value='/delivery/list'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-plus fa-sm text-white-50"></i>Lista dostaw</a>
        </div>

  <div class="row">

<input type="hidden" name="id" value="${product.id}"/>

            <div class="col-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary"> Lista produktów</h6>
                </div>
                <div class="card-body">
                  <table  class="table">
                    <thead>
                    <tr>
                         <th>Produkt</th>
                         <th>Zamówiono</th>
                         <th>Otrzymano</th>
                         <th>Jednostka</th>
                        <th>Status </th>
                        <th>Akcja </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${items}">
                      <tr>
                          <td>${item.product.name}</td>
                          <td>${product.orderedQuantity}</td>
                          <td>
                            <c:choose>
                                <c:when test="${item.checked}"> ${item.recievedQuantity} </c:when>
                                <c:otherwise>
                                    <form action="${pageContext.request.contextPath}/orderedproduct/updateQiantity" method="post" class="form-inline">
                                        <input type="hidden" name="id" value="${item.id}">
                                        <input type="hidden" name="deliveryId" value="${deliveryId}">
                                        <input type="number" step="0.1" name="recievedQuantity" class="form-control" style="width:100px" required>
                                        <button type="submit" class="btn btn-sm-primary ml-2"> Zatwierdź </button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                          </td>

                          <td>${item.unit}</td>


                           <c:if test="${item.checked}">
                                <c:choose>
                                    <c:when test="${item.matches}">
                                        <span class="badge badge-success">Zgadza się </span>
                                    </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger"> Rozbieżność! </span>
                                </c:otherwise>
                                </c:choose>
                           </c:if>
                          <c:if test="${!item.checked}">
                                <span class="badge badge-warning"> Nie sprawdzono </span>
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
