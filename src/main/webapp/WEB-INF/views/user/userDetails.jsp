<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<jsp:include page="../header.jsp"/>
<sec:authorize access="isAuthenticated()">

    <li><a href="<c:url value="/user/details/${userId}"/>" class="btn btn--without-border">Szczegóły konta</a></li>
    <li><a href="<c:url value="/donation/list/${userId}"/>" class="btn btn--without-border">Lista darów</a></li>
    </ul>

    </nav>
</sec:authorize>

</header>


<section class="login-page">
    <h2>Sczegóły konta</h2>
    <form:form modelAttribute="user" method="post" action="/user/edit/${userId}">

        <div class="form-group">
            <form:input path="userName" placeholder="Imię" /><form:errors path="userName" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="email" readonly="true" />
        </div>
        <div class="form-group form-group--checkbox">

            <form:hidden path="admin"/>
            <form:hidden path="enabled"/>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/"/>" class="btn btn--without-border">Anuluj</a>
            <a href="<c:url value="/user/editPassword/${userId}"/>" class="btn btn--without-border">Zmień hasło</a>
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form:form>


</section>

<jsp:include page="../footer.jsp"/>