<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
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
            <sec:authorize access="isAuthenticated()">

            <li><a href="<c:url value="/user/details/${us}"/>" class="btn btn--without-border">Szczegóły konta</a></li>
            <li><a href="<c:url value="/donation/list/${userId}"/>" class="btn btn--without-border">Lista darów</a></li>
        </ul>

    </nav>
    </sec:authorize>
</header>
<body>
<div class="slogan container container--90">
<div class="row">
    <div class="col-sm-6">
        <h1>
        Instytucja: ${donation.institution}
        </h1>
        <h1 style="float: right"><a href="<c:url value="/donation/deactivate/${donation.id}"/>">Oznacz jako odebrane</a></h1><br>
        <c:if test="${donation.collected}">
            <h1>Odebrany: tak</h1><br>
        </c:if>
        <c:if test="${not donation.collected}">
            <h1>Odebrany: nie</h1><br>
        </c:if>
        <h1> Data odbioru daru: ${donation.pickUpDate}</h1><br>
        <h1>Data wpisu: ${donation.createdOn}</h1>
    </div>

</div>


<jsp:include page="../footer.jsp"/>