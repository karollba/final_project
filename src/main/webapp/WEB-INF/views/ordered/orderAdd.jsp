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
                  <a href="${pageContext.request.contextPath}/delivery/list" class="btn btn-primary btn-sm"> Lista dostaw</a>
        </div>

              <div class="card shadow mb-4">
                <div class="card-body">
                    <form method="post: action=${pageContext.request.contextPath}/delivery/addwithitems" id="orderForm">

                    <div class="form-group">
                        <label for="supplier">Dostawca</label>
                        <select name="supplierId" class="form-control" id="supplier">
                        <option value=""> wybierz </option>
                            <c:forEach var="s" items="${suppliers}">
                                <option value="${s.id}">${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <hr>
                    <h5> Produkty w zamówieniu</h5>

                  <table  class="table" id="itemsTable">
                    <thead>
                    <tr>
                         <th>Produkt</th>
                         <th>Podaj ilość</th>
                         <th></th>
                    </tr>
                    </thead>
                    <tbody id="itemsBody">
                      <tr>
                          <td>
                            <select name="productsIds" class="form-control">
                                <c:forEach var="p" items="${products}">
                                    <option value="${p.id}">${p.name}</option>
                                </c:forEach>
                            </select>
                            </td>
                            <td>
                                <input type="number" step="0.1" name="orderedQuantities" class="form-control">
                                </td>
                                    <td>
                                        <button type="button" class="btn btn-danger btn-sm removeRow"> Usuń </button>
                                    </td>
                                </tr>
                    </tbody>
                    </table>
                        <button type="button" id="addRow" class="btn btn-secondary btn-sm">Dodaj produkt</button>
                        <br><br>
                        <button type="submit" class="btn btn-primary">Złóż zamówienie</button>
                          </form>
                        </div>
                    </div>
                </div>


           <script>
                document.getElementById('addRow').addEventListener('click', function() {
                    const body = document.getElementById('itemsBody');
                    const newRow = body.rows[0].cloneNode(true);
                    newRow.querySelectorAll('input').forEach(input => input.value = '');
                    body.appendChild(newRow);
                });

                document.getElementById('itemsBody').addEventListener('click', function(e) {
                    if (e.target.classList.contains('removeRow')) {
                        if (document.getElementById('itemsBody').rows.length > 1) {
                            e.target.closest('tr').remove();
                            }
                        }
                    });
           </script>


<%@ include file="../footer.jsp" %>
