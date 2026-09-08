<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value='/product/add'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-plus fa-sm text-white-50"></i> Dodaj nowy produkt</a>
        </div>

  <div class="row">

<input type="hidden" name="id" value="${product.id}"/>

            <div class="col-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary"> Lista produktów</h6>
                </div>
                <div class="card-body">


            <!-- Filtry -->
            <form method="get" action="${pageContext.request.contextPath}/product/list" class="mb-3">
                <select name="category" calss="form-control d-inline-block w-auto" onchange="this.form.submit()">
                    <option value="">Wszystkie kategorie</option>
                    <c:forEach var="cat" items="${productCategories}">
                        <option value="${cat}" ${cat.toString() == selectedCategory ? 'selected' : ''}>${cat} </option>
                    </c:forEach>
                </select>


                <select name="expiryFilter" calss="form-control d-inline-block w-auto" onchange="this.form.submit()">
                    <option value="">Wszystkie terminy</option>
                    <option value="today" ${selectedExpiryFilter == 'today' ? 'selected' : ''}>Dziś</option>
                    <option value="week" ${selectedExpiryFilter == 'week' ? 'selected' : ''}> W tym tygodniu</option>
                </select>

                </form>

                  <table  class="table">
                    <thead>
                    <tr>
                         <th>Nazwa</th>
                         <th>Kategoria</th>
                         <th>Ilość</th>
                         <th>Jednostka</th>
                        <th>Działanie </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="product" items="${products}">
                      <tr>
                          <td>${product.name}</td>
                          <td>${product.category}</td>
                          <td>${product.totalQuantity}</td>
                          <td>${product.defaultUnit}</td>
                          <td>
                            <a href="details?id=${product.id}" class="btn btn-info btn-sm">Szczegóły</a>
                            <a href="delete?id=${product.id}" class="btn btn-info btn-sm">Usuń</a>
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
