<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<jsp:include page="../header.jsp"/>
</header>
</ul>

</nav>

<div class="row">
    <div class="col-sm-6">
        Instytucja: ${donation.institution}<br>
        <c:if test="${donation.collected}">
        Odebrany: tak<br>
        </c:if>
        <c:if test="${not donation.collected}">
            Odebrany: nie<br>
        </c:if>
        Data odbioru daru: ${donation.pickUpDate}<br>
        Data wpisu: ${donation.dateAdded}
    </div>
</div>


<jsp:include page="../footer.jsp"/>