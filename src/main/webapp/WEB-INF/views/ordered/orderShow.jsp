<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp" %>
<div class="container-fluid">

  <!-- Page Heading -->
  <div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">Szczegóły zamówienia</h1>
  </div>
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Zamówienie: ${order.id}</h6>
    </div>

    <div class="card-body">
      <table class="table">
        <tr>
          <th> ID</th>
          <td> ${order.id}</td>
        </tr>

        <c:forEach var="item" items="${orderedProducts}">
          <tr>

              <td>
                <c:if test="${item.checked}">
                  <c:choose>
                     <c:when test="${item.matches}">
                        <span class="badge badge-success">Zgadza się</span>
                    </c:when>
                    <c:otherwise>
                      <span class="badge badge-success">Rozbieźność </span>
                  </c:otherwise>
                  </c:choose>
                </c:if>
                <c:if test="${!item.checked}">
                    <span class="badge badge-warning"> Nie sprawdzono</span>
                </c:if>
              </td>
          </tr>
      </c:forEach>


      </table>
      <a href="<c:url value='/employee/list'/>" class="btn btn-primary"> Main page</a>
    </div>
  </div>
</div>
  </div>
<%@ include file="../footer.jsp" %>



