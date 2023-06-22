<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<br><br>

<c:forEach items="${donations}" var="donation">
<div class="slogan container container--90">


    <div class="row">
        <div class="col-sm-6">
            <h1>
                <c:forEach items="${donation.categoryList}" var="category">
                Kategoria daru:  ${category.name}  <a style="float: right" href="<c:url value="/donation/details/${donation.id}/${userId}"/>">Pokaż szczegóły</a> <br><br>
                    <c:if test="${donation.collected}">
                        <h1>Odebrane: tak</h1><br>
                        <h1>Data odbioru: ${donation.collectedOn}</h1><br>
                    </c:if>
                    <c:if test="${not donation.collected}">
                        <h1>Odebrane: nie</h1><br>
                    </c:if>

                </c:forEach>
            </h1>
        </div>
    </div>
    <hr>

</div>
</c:forEach>




<jsp:include page="../footer.jsp"/>