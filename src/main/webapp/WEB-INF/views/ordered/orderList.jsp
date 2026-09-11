<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value='/order/add'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-plus fa-sm text-white-50"></i>Nowe zamówienie</a>
        </div>

  <div class="row">

<input type="hidden" name="id" value="${product.id}"/>

    <div class="col-12">
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h3 class="m-0 font-weight-bold text-primary"> Zamówienia</h3>
        </div>

           <form method="get" action="${pageContext.request.contextPath}/productorder/search" class="mb-3 form-inline">
                    <input type="text" name="query" value="${query}" class="form-control mr-2" placeholder="Szukaj po ID, kategorii lub nazwie">
                        <button type="submit" class="btn btn-primary">Szukaj</button>
                    <a href="${pageContext.request.contextPath}/productorder/list" class="btn btn-secondary ml-2"> Wyczyść</a>
                </form>

        <div class="card-body">
          <table  class="table">
            <thead>
            <tr>
                <th>Nr zamówienia</th>
                <th>Dostawca</th>
                <th>Data złożenia </th>
                <th>Status dostawy </th>
                <th> Operacja </td>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
          <tr>
            <td>${order.orderNumber}</td>
            <td>${order.supplier.name}</td>
            <td>${order.orderDate}</td>

              <td>
                  <c:choose>
                        <c:when test="${order.delivery != null}">
                            <span class="badge badge-success"> Dostarczono</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-warning"> Oczekuje</span>
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/productorder/show?id=${order.id}" class="btn btn-sm btn-info"> Szczegóły </a>

                        <a href="delete?id=${order.id}" class="btn btn-info btn-sm">Usuń</a>
               </td>
          </tr>
      </c:forEach>
              </tbody>
          </table>
      </div>
  </div>
  </div>
<%@ include file="../footer.jsp" %>
