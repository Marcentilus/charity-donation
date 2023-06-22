<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../header.jsp"/>
<sec:authorize access="isAuthenticated()">

  <li><a href="<c:url value="/user/details/${userId}"/>" class="btn btn--without-border">Szczegóły konta</a></li>
  <li><a href="<c:url value="/donation/list/${userId}"/>" class="btn btn--without-border">Lista darów</a></li>
  </ul>

  </nav>
</sec:authorize>

  <div class="slogan container container--90">
    <h2>
      Dziękujemy za przesłanie formularza Na maila prześlemy wszelkie
      informacje o odbiorze.
    </h2>
  </div>
</header>

<jsp:include page="../footer.jsp"/>