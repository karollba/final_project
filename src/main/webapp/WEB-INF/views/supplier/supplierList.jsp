<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value='/supplier/add'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-plus fa-sm text-white-50"></i> Dodaj nowego dostawcę</a>
        </div>

  <div class="row">

<input type="hidden" name="id" value="${supplier.id}"/>

            <div class="col-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary"> Lista dostawców</h6>
                </div>
                <div class="card-body">
                  <table  class="table">
                    <thead>
                    <tr>
                        <th>ID dostawcy</th>
                        <th>Nazwa dostawcy</th>
                        <th>NIP</th>
                        <th>REGON</th>
                         <th>Kod pocztowy</th>
                        <th>Ulica</th>
                        <th>Miejscowość</th>
                        <th>Działanie </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="supplier" items="${suppliers}">
                      <tr>
                          <td>${supplier.idToShow}</td>
                          <td>${supplier.name}</td>
                          <td>${supplier.NIP}</td>
                          <td>${supplier.REGON}</td>
                          <td>${supplier.postalCode}</td>
                          <td>${supplier.street}</td>
                          <td>${supplier.city}</td>
                          <td>
                            <a href="edit?id=${supplier.id}" class="btn btn-info btn-sm">Edit</a>
                            <a href="delete?id=${supplier.id}" class="btn btn-info btn-sm">Usuń</a>
                            <a href="show?id=${supplier.id}" class="btn btn-info btn-sm">Pokaż</a>
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
