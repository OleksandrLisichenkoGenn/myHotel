<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="../../static/image/favicon.png" type="image/png">
        <title>My Account</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="../../static/css/bootstrap.css">
		<link rel="stylesheet" href="../../static/css/customer_acc.css">
        <!-- main css -->
        <link rel="stylesheet" href="../../static/css/style.css">
        <link rel="stylesheet" href="../../static/css/responsive.css">
    </head>
    <body>

        <!--================Header Area =================-->
        <%@include file="header.jsp"%>
        <!--================Header Area =================-->
		 <!--================Banner Area =================-->
        <section class="banner_area">
            <div class="booking_table d_flex align-items-center">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-12 col-lg-8 col-xl-8 mx-auto">
                            <p></p>
                            <h2 class="h3 mb-4 page-title text-white"><fmt:message key="my_account"/></h2>
                            <div class="my-4">
                                <form>
                                    <div class="row mt-5 align-items-center">
                                        <div class="col">
                                            <div class="row mb-4">
                                                <div class="col-md-7">
                                                    <p class="text-white">
                                                       <fmt:message key="some_info"/>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr class="my-4"/>
                                    <div class="form-row">
                                        <div class="text-white col-md-6">
                                            <label>  <fmt:message key="name"/></label>
                                            <h1 class="form-controlac"><myFileTag:name/></h1>
                                        </div>
                                        <div class="text-white col-md-6">
                                            <label>  <fmt:message key="email"/></label>
                                            <h1 class="form-controlac">${sessionScope.email}</h1>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="text-white col-md-4">
                                            <label><fmt:message key="create_time"/></label>
                                            <h1 class="form-controlac">${sessionScope.createTime}</h1>
                                        </div>
                                    </div>
                                </form>
                                <!-- < BEGIN OF TEMPLATE HISTORY>  -->
                                <div class="acc-overview-first-section column">
                                    <div type="text-center ">
                                        <h2 class="text-white">  <fmt:message key="orders"/></h2>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <ul type="list-group" class="items">
                                                <!-- <item1> -->
                                                <c:forEach var="order" items="${list}">
                                                    <form action="/api?command=payOrder" method="post">
                                                        <li class="list-group-item ">
                                                            <div class="row">
                                                                <div type="text" class="col-lg-2 row">
                                                                    <fmt:message key="order_id"/>
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                    <fmt:message key="account_email"/>
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                    <fmt:message key="room_id"/>
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                    <fmt:message key="room_type"/>
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                    <fmt:message key="order_status"/>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div type="text" class="col-lg-2 row">
                                                                        ${order.id}
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                        ${order.accountEmail}
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                        ${order.roomId}
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                        ${order.type}
                                                                </div>
                                                                <div type="text" class="form-control col-lg-3 row" style="color:green">
                                                                        ${order.orderStatus}
                                                                </div>
                                                            </div>
                                                            <br>

                                                            <div class="row text-center">
                                                                <div type="text" class="col-lg-3">

                                                                </div>>
                                                                <div type="text" class="col-lg-3 row">
                                                                    <fmt:message key="total_price"/>
                                                                </div>
                                                                <div type="text" class="col-lg-2 row">
                                                                    <fmt:message key="places"/>
                                                                </div>
                                                                <div type="text" class="col-lg-3 row">
                                                                    <fmt:message key="days"/>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                    <c:if test="${order.orderStatus == 'NEW'}">
                                                                        <div type="text" class="col-lg-2"><fmt:message key="wait_for_accept"/></div>
                                                                    </c:if>
                                                                <div type="empty-cells" class="form col-lg-1"></div>

                                                                <c:choose>
                                                                    <c:when test="${order.orderStatus == 'ACCEPT_BY_ADMIN'}">
                                                                        <input type="submit" class="btn btn-success col-lg-2" value='<fmt:message key="agree"/> '>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div type="empty-cells" class="form col-lg-2"></div>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <div type="text" class="form-control col-lg-2">${order.totalPrice}</div>
                                                                <div type="text" class="form-control col-lg-2">${order.places}</div>
                                                                <div type="text" class="form-control col-lg-2">${order.days}</div>
                                                                <input type="hidden" name="orderId" value="${order.id}">
                                                            </div>
                                                        </li>
                                                    </form>
                                                </c:forEach>
                                                <br class="row">
                                            </ul>

                                        </div>
                                    </div>
                                </div>
                                <!-- < END OF TEMPLATE HISTORY> -->
                            </div>
                        </div>
                    </div>

                </div>
                <div class="overlay bg-parallax" data-stellar-ratio="0.9" data-stellar-vertical-offset="0"
                     data-background=""></div>
            </div>
        </section>
        <!--================Banner Area =================-->
		 <!--================ start footer Area  =================-->	
       <%@include file="footer.jsp"%>>
		<!--================ End footer Area  =================-->