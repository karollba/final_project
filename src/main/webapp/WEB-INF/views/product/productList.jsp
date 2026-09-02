<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value='/product/add'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-download fa-sm text-white-50"></i> Dodaj nowy produkt</a>
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
                         <th>Nazwa</th>
                         <th>Kategoria</th>
                         <th>Dostępność</th>
                         <th>Termin ważności</th>
                         <th>Ilość</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}">
                      <tr>
                          <td>${product.name}</td>
                          <td>${product.category}</td>
                          <td>${product.availability}</td>
                          <td>${product.expirationDate}</td>
                          <td>${product.quantity}</td>
                          <td>
                            <a href="edit?id=${product.id}" class="btn btn-info btn-sm">Edit</a>
                            <a href="delete?id=${product.id}" class="btn btn-info btn-sm">Usuń</a>
                            <a href="show?id=${product.id}" class="btn btn-info btn-sm">Pokaż</a>
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
