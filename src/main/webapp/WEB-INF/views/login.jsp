<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../header.jsp" %>

   <!-- Begin Page Content -->
      <div class="container-fluid" style="max-width: 400px; margin-top: 100px;">
        <div class="card shadow">
            <div class="card-body">
                <h3 class="text-center mb-4">Logowanie</h3>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger"> Nieprawidłowy login lub hasło</div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success"> Wylogowano pomyślnie</div>
                </c:if>


           <form method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label for="username"> Login</label>
                    <input type="text" name="username" class="form-control" placeholder="Nazwa użytkownika" id="username">
                </div>
                <div class="form-group">
                    <label for="password"> Hasło </label>
                    <input type="password" name="password" class="form-control" placeholder="Hasło" id="password">
                </div>
                <button type="submit" class="btn btn-primary btn-block"> Zaloguj </button>
            </form>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
