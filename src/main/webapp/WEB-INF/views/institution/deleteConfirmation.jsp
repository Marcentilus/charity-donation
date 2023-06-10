<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header.jsp"/>
</ul>

</nav>

<section class="login-page">
<h1>Czy na pewno chcesz usunąć organizację?</h1>
    <a href="<c:url value="/admin/institution/delete/${id}"/>" class="btn btn--without-border">Potwierdzam</a>
    <a href="<c:url value="/admin/institution/list"/>" class="btn btn--without-border">Anuluj</a>
</section>
</header>

<jsp:include page="../footer.jsp"/>