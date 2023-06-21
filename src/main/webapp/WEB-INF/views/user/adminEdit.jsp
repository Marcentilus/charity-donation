<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="../header.jsp"/>
</header>
</ul>

</nav>

<section class="login-page">
    <h2>Sczegóły konta</h2>
    <form:form modelAttribute="user" method="post" action="/admin/user/edit/${userId}">

        <div class="form-group">
            <form:input path="userName" placeholder="Imię" /><form:errors path="userName" cssClass="error"/>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="email"/><form:errors path="email" cssClass="error"/>
        </div>
        <div class="form-group form-group--checkbox">
            <label>
                <form:checkbox
                        path="enabled"/>
                <span class="checkbox"></span>
                <span class="description">Active</span>
            </label>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/"/>" class="btn btn--without-border">Anuluj</a>
            <a href="<c:url value="/user/editPassword/${userId}"/>" class="btn btn--without-border">Zmień hasło</a>
            <button class="btn" type="submit">Zapisz</button>
        </div>

    </form:form>


</section>

<jsp:include page="../footer.jsp"/>