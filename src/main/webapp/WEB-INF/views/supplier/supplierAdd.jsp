<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">supplierCRUD</h1>
    <a href="${pageContext.request.contextPath}/supplier/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-plus fa-sm text-white-50"></i> Lista dostawców</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Nowy dostawca</h6>
    </div>

 <div class="card-body">

  <h1>Dodaj dostawcę</h1>

      <c:if test="${not empty error}">
          <div class="alert alert-danger">
              ${error}
          </div>
      </c:if>


       <form:form method="post" action="${pageContext.request.contextPath}/supplier/add" modelAttribute="supplier">

            <div class="form-group">
                <label for="name">Nazwa dostawcy</label>
                <form:input path="name" cssClass="form-control" id="name" placeholder="Nazwa"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="REGON">REGON</label>
                <form:input path="REGON" type="text"  cssClass="form-control" id="REGON" placeholder="REGON"/>
                <form:errors path="REGON" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="NIP">NIP</label>
                <form:input path="NIP" type="text" cssClass="form-control" id="NIP" placeholder="NIP"/>
                <form:errors path="NIP" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="postalCode">Kod pocztowy</label>
                <form:input path="postalCode" cssClass="form-control" id="postalCode" placeholder="Kod pocztowy"/>
                <form:errors path="postalCode" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="street">Ulica</label>
                <form:input path="street" cssClass="form-control" id="street" placeholder="Ulica"/>
                <form:errors path="street" cssClass="text-danger"/>
            </div>

             <div class="form-group">
                <label for="city">Miejscowość</label>
                <form:input path="city" cssClass="form-control" id="city" placeholder="Miejscowość"/>
                <form:errors path="city" cssClass="text-danger"/>
            </div>

            <button type="submit" class="btn btn-primary">Dodaj</button>

       </form:form>
    </div>
    <a href="${pageContext.request.contextPath}/supplier/list" class="btn btn-primary">Strona główna</a>
 </div>
</div>
<%@ include file="../footer.jsp" %>