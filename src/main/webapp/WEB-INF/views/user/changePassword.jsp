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
    <h2>Zmiana hasła</h2>
    <form method="post" action="/user/editPassword/${username}">

        <c:if test="${not empty message}">
        <div class="form-group">
            <input type="password" placeholder="${message}" name="oldPassword"/>
        </div>
        </c:if>
        <c:if test="${empty message}">
            <div class="form-group">
                <input type="password" placeholder="Wprowadź stare hasło" name="oldPassword"/>
            </div>
        </c:if>
        <div class="form-group">
            <input type="password" placeholder="Wprowadź nowe hasło" name="newPassword" />
        </div>
        <div class="form-group">
            <input type="password" placeholder="Powtórz nowe hasło" name="newPasswordRep" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/"/>" class="btn btn--without-border">Anuluj</a>
            <button class="btn" type="submit">Zapisz</button>
        </div>
    </form>
</section>

<jsp:include page="../footer.jsp"/>