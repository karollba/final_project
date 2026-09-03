<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value='/delivery/add'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-download fa-sm text-white-50"></i> Nowa dostawa</a>
        </div>

  <div class="row">

    <input type="hidden" name="id" value="${delivery.id}"/>
        <div class="col-12">
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary"> Lista dostaw</h6>
            </div>
            <div class="card-body">
              <table  class="table">
                <thead>
                <tr>
                     <th>Nr dostawy</th>
                     <th>ID Dostawcy</th>
                     <th>Nazwa dostawcy</th>
                     <th>Dostawę przyjęto</th>
                     <th>Płatność do</th>
                     <th>Opłacono </th>
                     <th>Pracownik przyjmujący</th>
                     <th>ID kuriera</th>
                     <th>Dostawa nienaruszona</th>
                     <th>Kategoria</th>
                     <th>Działanie </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="delivery" items="${deliveries}">
                  <tr>
                      <td>${delivery.deliveryId}</td>
                      <td>${delivery.supplierId}</td>
                      <td>${delivery.supplierName}</td>
                      <td>${delivery.dateOfAcceptTheDelivery}</td>
                      <td>${delivery.invoiceDue}</td>
                      <td>${delivery.paid}</td>
                      <td>${delivery.acceptingEmployeeId}</td>
                      <td>${delivery.deliveryManId}</td>
                      <td>${delivery.deliveryIntact}</td>
                      <td>${delivery.category}</td>
                      <td>
                        <a href="edit?id=${delivery.id}" class="btn btn-info btn-sm">Edytuj</a>
                        <a href="delete?id=${delivery.id}" class="btn btn-info btn-sm">Usuń</a>
                        <a href="show?id=${delivery.id}" class="btn btn-info btn-sm">Pokaż</a>
                      </td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
    </div>
  </div>

<%@ include file="../footer.jsp" %>
