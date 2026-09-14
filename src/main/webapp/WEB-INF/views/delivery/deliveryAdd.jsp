<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">deliveryCRUD</h1>
    <a href="${pageContext.request.contextPath}/delivery/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-plus fa-sm text-white-50"></i> Lista dostaw</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Nowa dostawa</h6>
    </div>

 <div class="card-body">

  <h1>Nowa dostawa</h1>

      <c:if test="${not empty error}">
          <div class="alert alert-danger">
              ${error}
          </div>
      </c:if>


       <form:form method="post" action="${pageContext.request.contextPath}/delivery/add" modelAttribute="delivery">

            <div class="form-group">
               <label for="orderNumber">Zamówienie</label>
               <select name="orderId" class="form-control" id="orderNumber">
                   <option value="">wybierz</option>
                   <c:forEach var="o" items="${orders}">
                      <option value="${o.id}">${o.orderNumber}</option>
                   </c:forEach>
               </select>
           </div>

            <div class="form-group">
                <label for="invoiceDue">Płatność do</label>
                <form:input path="invoiceDue" type="date" cssClass="form-control" id="invoiceDue"/>
                <form:errors path="invoiceDue" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="invoiceId">Numer Faktury</label>
                <form:input path="invoiceId" type="number" cssClass="form-control" id="invoiceId"/>
                <form:errors path="invoiceId" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="paid">Zapłacono</label>
                <form:checkbox path="paid" id="paid"/>
            </div>

            <div class="form-group">
                <label for="category">Kategoria</label>
                <form:select path="category" cssClass="form-control" id="category">
                    <form:option value="">wybierz</form:option>
                    <c:forEach var="cat" items="${deliveryCategories}">
                        <form:option value="${cat}">${cat}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="category" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="deliveryIntact">Dostawa nienaruszona</label>
                <form:checkbox path="deliveryIntact" id="deliveryIntact"/>
            </div>

            <div class="form-group">
                <label for="dateOfAcceptTheDelivery">Data przyjęcia dostawy</label>
                <form:input path="dateOfAcceptTheDelivery" type="date" cssClass="form-control" id="dateOfAcceptTheDelivery"/>
                <form:errors path="dateOfAcceptTheDelivery" cssClass="text-danger"/>
            </div>

            <button type="submit" class="btn btn-primary">Dodaj</button>

       </form:form>
    </div>
    <a href="${pageContext.request.contextPath}/delivery/list" class="btn btn-primary">Strona główna</a>
 </div>
</div>
<%@ include file="../footer.jsp" %>