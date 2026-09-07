<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>

<div class="container-fluid">
    <h1> Wystąpił bład </h1>

    <div class="alert alert-danger">
        <p><strong> Status: </strong> ${status}</p>
        <p><strong> Wiadomość: </strong> ${message} </p>
        <p><strong> Wyjątek: </strong>${exception} </p>
    </div>

    <a href="${pageContext.request.contextPath}/" class="btn btn-primary"> Wróć do strony głównej</a>
</div>

<%@ include file="footer.jsp" %>