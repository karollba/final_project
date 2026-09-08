<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid">

        <a href="<c:url value='/product/addbatch'/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                        class="fas fa-plus fa-sm text-white-50"></i> Dodaj nową partię </a>
        </div>

  <div class="row">

<input type="hidden" name="id" value="${product.id}"/>

            <div class="col-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary"> ${product.name}</h6>
                </div>
                <div class="card-body">

                <select name="expiryFilter" calss="form-control d-inline-block w-auto" onchange="this.form.submit()">
                    <option value="">Wszystkie terminy</option>
                    <option value="today" ${selectedExpiryFilter == 'today' ? 'selected' : ''}>Dziś</option>
                    <option value="week" ${selectedExpiryFilter == 'week' ? 'selected' : ''}> W tym tygodniu</option>
                </select>

                </form>

                  <table  class="table">
                    <thead>
                    <tr>
                         <th>Ilość</th>
                         <th>Jednostka</th>
                         <th>Termin ważności</th>
                         <th>Działanie</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="batch" items="${batches}">
                      <tr>
                          <td>${batch.quantity}</td>
                           <td>${batch.product.defaultUnit}</td>
                          <td>${batch.expirationDate}</td>
                          <td>
                            <a href="delete?id=${batch.id}" class="btn btn-info btn-sm">Usuń</a>
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
