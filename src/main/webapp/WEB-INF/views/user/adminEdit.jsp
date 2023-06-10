<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header.jsp"/>
</header>

<section class="login-page">
    <h2>Edytuj konto</h2>
    <form:form modelAttribute="user" method="post" action="/user/edit">

        <div class="form-group">
            <form:input path="name" placeholder="Imię" /><form:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input path="username" type="email" placeholder="Email" /><form:errors path="username" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło" /><form:errors path="password" cssClass="error"/>
        </div>
        <div class="form-group">
            <label>
                <form:checkbox
                        path="enabled"
                />
                <span class="description">Aktywny</span>
            </label>
        </div>

        <form:hidden path="donation"/>
        <form:hidden path="id"/>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/"/>" class="btn btn--without-border">Anuluj</a>
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form:form>
</section>

<jsp:include page="../footer.jsp"/>