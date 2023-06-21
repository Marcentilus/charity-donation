<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../header.jsp"/>
</header>

<section class="login-page">
  <h2>Załóż konto</h2>
  <form:form modelAttribute="user" action="/admin/add" method="post">

    <div class="form-group">
      <form:input path="userName" placeholder="Imię" /><form:errors path="userName" cssClass="error"/>
    </div>
    <div class="form-group">
      <form:input path="email" type="email" placeholder="Email" /><form:errors path="email" cssClass="error"/>
    </div>
    <div class="form-group">
      <form:password path="password" placeholder="Hasło" /><form:errors path="password" cssClass="eror"/>
    </div>
    <div class="form-group">
      <input type="password" name="password2" placeholder="Powtórz hasło" />
    </div>
    <form:hidden path="enabled"/>
    <form:hidden path="admin"/>

    <div class="form-group form-group--buttons">
      <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
      <button class="btn" type="submit">Załóż konto</button>
    </div>
  </form:form>
</section>

<jsp:include page="../footer.jsp"/>