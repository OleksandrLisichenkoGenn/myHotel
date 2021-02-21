<%@ include file="../jspf/libs.jspf"%>
<%@ include file="/WEB-INF/jspf/myTagsLib.jspf" %>
<myLibTag:setLocale attr="${locale}"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="MyBundle"/>

<header class="header_area">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <!-- Brand and toggle get grouped for better mobile display -->
            <a class="navbar-brand logo_h" href="../../index.jsp"><img src="../../static/image/Logo.png" alt=""></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <c:if test="${sessionScope.id==null}">
                <form class="col-lg-5 row" action="/api?command=login" method="post">
                    <div class="col-sm-14">
                        <c:if test="${param.get('error') == 4}">
                            <span class="error text-danger"><fmt:message key="invalid_email_or_pass"/></span>
                        </c:if>
                        <input class="form-control input-sm" type="text" placeholder='<fmt:message key="email"/>'
                               name="email">
                    </div>
                    <div class="col-sm-14">
                        <input class="form-control input-sm" type="password" placeholder='<fmt:message key="password"/>'
                               name="password">
                    </div>
                    <div class="col-sm-5">
                        <input type="submit" class="book_now_btn button_hover" value=<fmt:message key="login"/>>
                    </div>
                </form>
            </c:if>
            <c:if test="${sessionScope.id != null}">
                <div class="col-lg-3">
                    <h2 style="color:black"><fmt:message key="hello"/>, <myFileTag:name/></h2>
                </div>
                <form class="col-sm-2" action="/api?command=logout" method="post">
                    <input type="submit" class="book_now_btn button_hover" value='<fmt:message key="logout"/>'>
                </form>
            </c:if>

            <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                <ul class="nav navbar-nav menu_nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="../../index.jsp"><fmt:message key="home"/></a></li>
                    <c:if test="${sessionScope.userRole == 'ADMIN'}">
                        <li class="nav-item"><a class="nav-link" href="/api?command=getOrdersAdmin"><fmt:message
                                key="my_account"/></a></li>
                    </c:if>
                    <c:if test="${sessionScope.userRole == 'CUSTOMER'}">
                        <li class="nav-item"><a class="nav-link" href="/api?command=getOrdersCustomer"><fmt:message
                                key="my_account"/></a></li>
                    </c:if>
                    <li class="nav-item"><a class="nav-link" href="/api?command=getRooms"><fmt:message
                            key="accommodation"/></a></li>
                    <li class="nav-item"><a class="nav-link" href="/api?command=aboutUs"><fmt:message key="about_us"/></a></li>
                    <c:if test="${sessionScope.id == null}">
                        <li class="nav-item"><a class="nav-link" href="/api?command=register"><fmt:message key="sign_up"/></a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
        <form action="/api?command=setLocale" method="post" class="dropdown">
            <input type="hidden" name="path" value="${param}">
            <input type="submit" class="glyphicon glyphicon-globe" value='<fmt:message key="btn_set"/>'>
            <select class="form-select col-sm-1" name="localeName">
                <option value="en">en</option>
                <option value="ru">ru</option>
            </select>
        </form>
    </div>
</header>