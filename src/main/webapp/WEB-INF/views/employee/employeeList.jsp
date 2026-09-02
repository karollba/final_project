<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
          <a href="<c:url value='employee/add'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                  class="fas fa-download fa-sm text-white-50"></i> Dodaj nowego pracownika</a>
        </div>

  <div class="row">
            <div class="col-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary"> Lista pracowników</h6>
                </div>
                <div class="card-body">
                  <table  class="table">
                    <thead>
                    <tr>
                         <th>ID</th>
                         <th>Imię</th>
                         <th>Nazwisko</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="employee" items="${employees}">
                      <tr>
                       <td>${employee.id}</td>
                          <td>${employee.firstName}</td>
                          <td>${employee.lastName}</td>
                          <td>
                            <a href="edit?id=${employee.id}" class="btn btn-info btn-sm">Edit</a>
                            <a href="delete?id=${employee.id}" class="btn btn-info btn-sm">Usuń</a>
                            <a href="show?id=${employee.id}" class="btn btn-info btn-sm">Pokaż</a>
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
