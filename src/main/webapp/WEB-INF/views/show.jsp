<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Szczegóły użytkownika</h1>
  </div>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Pracownik: ${employee.firstName} ${employee.lastName}</h6>
    </div>

    <div class="card-body">
      <table class="table">
        <tr>
          <th> ID</th>
          <td> ${employee.id}</td>
        </tr>
        <tr>
          <th> Imię i nazwisko</th>
          <td> ${employee.firstName} ${employee.lastName}</td>
        </tr>
        <tr>
          <th> Email:</th>
          <td> ${employee.email}</td>
        </tr>
      </table>
      <a href="<c:url value='/employee/list'/>" class="btn btn-primary"> Main page</a>
    </div>
  </div>
</div>
<%@ include file="/users/footer.jsp" %>


