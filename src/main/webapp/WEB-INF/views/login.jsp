<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="header.jsp"/>
</header>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form:form modelAttribute="user">
        <div class="form-group">
            <form:input path="username" type="email" placeholder="Email" /><form:errors path="username" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło" />
        </div>
        <form:hidden path="donation"/>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/user/add"/>" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>