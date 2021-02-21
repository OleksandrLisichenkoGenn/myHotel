<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="../../static/image/favicon.png" type="image/png">
    <title>Accommodation</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../static/css/bootstrap.css">
    <link rel="stylesheet" href="../../static/vendors/linericon/style.css">
    <link rel="stylesheet" href="../../static/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../static/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../../static/vendors/nice-select/css/nice-select.css">
    <link rel="stylesheet" href="../../static/vendors/owl-carousel/owl.carousel.min.css">
    <!-- main css -->
    <link rel="stylesheet" href="../../static/css/style.css">
    <link rel="stylesheet" href="../../static/css/responsive.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<!--================Header Area =================-->
<%@include file="header.jsp" %>
<!--================Header Area =================-->

<!--================Breadcrumb Area =================-->
<section class="breadcrumb_area">
    <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
    <div class="container">
        <div class="page-cover text-center">
            <h2 class="page-cover-tittle"><fmt:message key="accommodation"/></h2>

        </div>
    </div>
</section>
<!--================Breadcrumb Area =================-->
<!--================ Accomodation Area  =================-->
<br>
<div class="row col-lg-6">
    <div type="empty-cells"></div>
</div>
<div class="row col-lg-12">
    <div class="row col-lg-8"><div type="empty-cells"></div></div>
    <div class="dropdown col-lg">
        <button class="btn-lg btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><fmt:message key="sort_by"/></button>
        <ul class="dropdown-menu">
           <li <c:if test="${param.sort == null || param.sort.equals('byStatus')}"> class="active" </c:if>><a href="/api?command=getRooms&sort=byStatus"><fmt:message key="status"/></a></li>
            <li <c:if test="${param.sort.equals('byPrice')}"> class="active" </c:if>><a href="/api?command=getRooms&sort=byPrice"><fmt:message key="price"/></a></li>
            <li <c:if test="${param.sort.equals('byPlaces')}"> class="active" </c:if>><a href="/api?command=getRooms&sort=byPlaces"><fmt:message key="places"/></a></li>
            <li <c:if test="${param.sort.equals('byType')}"> class="active" </c:if>><a href="/api?command=getRooms&sort=byType"><fmt:message key="type"/></a></li>
        </ul>
    </div>
</div>
<div class="text-center">
    <c:if test="${param.error == 1}">
        <div class="alert alert-danger">
            <fmt:message key="set_days_error"/>
        </div>
    </c:if>
</div>
<section class="accomodation_area section_gap">
    <div class="container">
        <%--@elvariable id="roomList" type="java.util.List"--%>
        <c:forEach var="room" items="${roomList}">
            <form action="/api?command=orderCurrentRoom" method="post">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-lg-3 col-sm-10">
                            <div class="accomodation_item text-left">
                                <div class="hotel_img">
                                    <img src="static/image/room${room.id}.jpg" alt="">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-7 col-sm-10">
                            <h4 class="sec_h4"><fmt:message key="room"/> #${room.id}
                                <c:if test="${room.type == 1}">
                                    &#9755
                                    <c:out value="ECONOM"/>
                                </c:if>
                                <c:if test="${room.type == 2}">
                                    &#9755
                                    <c:out value="STANDARD"/>
                                </c:if>
                                <c:if test="${room.type == 3}">
                                    &#9755
                                    <c:out value="LUX"/>
                                </c:if>

                                <fmt:message key="places"/>: ${room.places}

                            </h4>
                        </div>
                        <c:if test="${sessionScope.id != null}">
                            <c:choose>
                                <c:when test="${room.status == 'FREE'}">
                                    <div class="col-lg-2 col-sm-1">
                                        <input type="hidden" name="idRoom" value="${room.id}">
                                        <input type="submit" value='<fmt:message key="book"/>' class="book_now_btn button_hover">
                                        <h4>$${room.price}<small>/<fmt:message key="night"/></small></h4>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <h5 style="color:darkgreen"> ${room.status}</h5>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${sessionScope.id == null}">
                            <div class="col-lg-2 col-sm-1">
                                <h5 style="color:red"><fmt:message key="need_for_curr"/></h5>
                            </div>
                        </c:if>
                        <div class="col-lg-7">
                            <div class="form-grouped">
                                <div class="area">
                                    <h5><fmt:message key="info_about_room"/></h5>
                                </div>
                            </div>
                        </div>
                            <%--  CHECK NA JS CHTOBU BUL VVOD DNEI V LUBOM SLYCHAE  --%>
                        <c:if test="${room.status == 'FREE' && sessionScope.id != null}">
                            <div class="col-lg-2">
                                <input name="days" type="number" class="form-control form-control-lg"
                                       placeholder='<fmt:message key="days"/>'/>
                            </div>
                        </c:if>
                    </div>
                </div>
            </form>
        </c:forEach>
    </div>
    <!--================ Pagination ================== -->
    <div class="container">
        <ul class="pagination pagination-lg justify-content-center">
            <c:choose>
                <c:when test="${param.page == null}">
                    <li class="active"><a href="/api?command=getRooms&page=0">1</a></li>
                    <c:forEach begin="1" items="${pages}" var="p">
                        <li <c:if test="${param.page == p - 1}"> class="active" </c:if>><a href="/api?command=getRooms&page=${p-1}&sort=${sort}">${p}</a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${pages}" var="p">
                        <li <c:if test="${param.page == p - 1}"> class="active" </c:if>><a
                                href="/api?command=getRooms&page=${p-1}&sort=${sort}">${p}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <!--================ Pagination Finish ================== -->
</section>
<!--================ Accomodation Area  =================-->
<!--================ start footer Area  =================-->
<%@include file="footer.jsp" %>
<!--================ End footer Area  =================-->


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../../static/js/jquery-3.2.1.min.js"></script>
<script src="../../static/js/popper.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="../../static/js/jquery.ajaxchimp.min.js"></script>
<script src="../../static/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="../../static/vendors/nice-select/js/jquery.nice-select.js"></script>
<script src="../../static/js/mail-script.js"></script>
<script src="../../static/js/stellar.js"></script>
<script src="../../static/vendors/lightbox/simpleLightbox.min.js"></script>
<script src="../../static/js/custom.js"></script>
</body>
</html>