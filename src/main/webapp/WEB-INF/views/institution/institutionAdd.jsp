<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>

<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <sec:authorize access="not isAuthenticated()">
                <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="<c:url value="/logout"/>" class="btn btn--small btn--without-border">Wyloguj</a></li>
            </sec:authorize>
            <sec:authorize access="not isAuthenticated()">
                <li><a href="<c:url value="/user/add"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            </sec:authorize>
        </ul>

        <ul>
            <li><a href="<c:url value="/donation/add"/>" class="btn btn--without-border active">Start</a></li>
            <li><a href="<c:url value="/"/>" class="btn btn--without-border">Home</a></li>
            <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#" class="btn btn--without-border">O nas</a></li>
            <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Uzupełnij wszystkie pola</h2>
    <form:form modelAttribute="institution" method="post" action="/admin/institution/edit">
        <div class="form-group">
            <h1>Nazwa:</h1> <br>
            <form:input size="50" path="name" placeholder="Nazwa" /><form:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <h1>Opis:</h1> <br>
            <form:textarea path="description" rows="10" cols="30" placeholder="Opis" /><form:errors path="description" cssClass="error"/>
        </div>
        <form:hidden path="id"/>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/admin/institution/list"/>" class="btn btn--without-border">Anuluj</a>
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form:form>
</section>



<jsp:include page="../footer.jsp"/>