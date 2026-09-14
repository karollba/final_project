<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>

<div class="container-fluid">

  <div class="d-sm-flex align-items-center justify-content-between mb-4">
      <h1 class="h3 mb-0 text-gray-800">DeliveryCRUD</h1>
      <a href="${pageContext.request.contextPath}/delivery/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
        Lista dostaw</a>
    </div>

   <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Edycja dostawy</h6>
    </div>

<div class="card-body">
<form:form method="post" action="${pageContext.request.contextPath}/delivery/edit" modelAttribute="delivery">

        <form:hidden path="id"/>

        <div class="form-group">
            <label>Nr dostawy:</label>
            <span>${delivery.deliveryId}</span>
        </div>

        <div class="form-group">
            <label for="invoiceDue">Płatność do</label>
            <form:input path="invoiceDue" type="date" cssClass="form-control" id="invoiceDue"/>
            <form:errors path="invoiceDue" cssClass="text-danger"/>
        </div>

        <div class="form-group">
            <label for="paid">Zapłacono</label>
            <form:checkbox path="paid" id="paid"/>
        </div>

        <div class="form-group">
            <label for="category">Kategoria</label>
            <form:select path="category" cssClass="form-control" id="category">
                <c:forEach var="cat" items="${deliveryCategories}">
                    <form:option value="${cat}">${cat}</form:option>
                </c:forEach>
            </form:select>
            <form:errors path="category" cssClass="text-danger"/>
        </div>

        <button type="submit" class="btn btn-primary">Zapisz</button>

    </form:form>
</div>
<a href="${pageContext.request.contextPath}/delivery/list" class="btn btn-primary">Strona główna</a>
</div>
</div>
<%@ include file="../footer.jsp" %>