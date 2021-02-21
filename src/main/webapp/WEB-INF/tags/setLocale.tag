<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%@attribute name="attr" type="java.lang.String" rtexprvalue="true" required="true" description="set locale, var is 'language'" %>

<c:choose>
    <c:when test="${not empty attr}">
        <c:set scope="session" var="language" value="${attr}"/>
    </c:when>
    <c:otherwise>
        <c:set var="language" value="en" scope="session"/>
    </c:otherwise>
</c:choose>

