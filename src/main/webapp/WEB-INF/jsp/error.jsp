<%@include file="/WEB-INF/jspf/libs.jspf"%>
<%@include file="/WEB-INF/jspf/links.jspf"%>
<!doctype html>
<html>
<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
<head>
    <title>Error</title>

</head>

<body>
<!--================Banner Area =================-->
<section class="banner_area">
    <div class="booking_table d_flex align-items-center">
        <div class="overlay bg-parallax" data-stellar-ratio="0.9" data-stellar-vertical-offset="0"
             data-background=""></div>
        <div class="container">
            <div class="banner_content text-center">
                <c:if test="${not empty code}">
                    <h2>Oops..</h2><br/>
                    <h1>Error code: ${code}</h1>
                </c:if>
                <c:if test="${message == null}">
                    <h3>Message: Internal server error!</h3>
                </c:if>
                <c:if test="${not empty  message}">
                    <h3>Message: ${message}</h3>
                </c:if>
                <c:if test="${not empty errorMessage and empty exception and empty code}">
                    <h3>Error message: ${errorMessage}</h3>
                </c:if>
                <h3><a style="color:whitesmoke"  href="<c:url value="/index.jsp"/>"><ins>Come to home page</ins></a></h3>
            </div>
        </div>
    </div>
</section>
<!--================Banner Area =================-->
</body>
</html>
