<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../header.jsp" %>


<div class="container-fluid">
<div class="d-sm-flex align-items-center justify-content-between mb-4">
    <h1 class="h3 mb-0 text-gray-800">ProductCRUD</h1>
    <a href="${pageContext.request.contextPath}/product/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
      <i class="fas fa-plus fa-sm text-white-50"></i> Lista produktów</a>
  </div>

 <div class="card shadow mb-4">
    <div class="card-header py-3">
      <h6 class="m-0 font-weight-bold text-primary">Dodawanie produktu</h6>
    </div>

 <div class="card-body">

  <h1>Dodaj produkt</h1>

      <c:if test="${not empty error}">
          <div class="alert alert-danger">
              ${error}
          </div>
      </c:if>

          <div class="card mb-4">
              <div class="card-body">
                  <h5>Zeskanuj kod kreskowy</h5>
                  <form action="${pageContext.request.contextPath}/product/scan" method="post" enctype="multipart/form-data">
                  <input type="file" name="file" accept="image/*" class="form-control">
                  <button type="submit" class="btn btn-primary mt-2">Skanuj</button>
                  </form>
              </div>
          </div>


       <form:form method="post" action="${pageContext.request.contextPath}/product/add" modelAttribute="product">

            <div class="form-group">
                <label for="barcode">Kod kreskowy</label>
                <form:input path="barcode" cssClass="form-control" id="barcode" placeholder="Kod kreskowy"/>
                <form:errors path="barcode" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="name">Nazwa</label>
                <form:input path="name" cssClass="form-control" id="name" placeholder="Nazwa"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="category">Kategoria</label>
                <form:select path="category" cssClass="form-control" id="category">
                    <form:option value="">wybierz</form:option>
                    <c:forEach var="cat" items="${productCategories}">
                        <form:option value="${cat}">${cat}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="category" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="defaultUnit">Jednostka</label>
                <form:select path="defaultUnit" cssClass="form-control" id="defaultUnit">
                    <form:option value="">wybierz</form:option>
                    <c:forEach var="u" items="${units}">
                        <form:option value="${u}">${u}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="defaultUnit" cssClass="text-danger"/>
            </div>

            <div class="form-group">
                <label for="quantity">Ilość</label>
                <form:input path="quantity" type="number" min="0" cssClass="form-control" id="quantity" placeholder="Ilość"/>
                <form:errors path="quantity" cssClass="text-danger"/>
            </div>

            <button type="submit" class="btn btn-primary">Dodaj</button>

       </form:form>
    </div>
    <a href="${pageContext.request.contextPath}/product/list" class="btn btn-primary">Strona główna</a>
 </div>
</div>
<%@ include file="../footer.jsp" %>