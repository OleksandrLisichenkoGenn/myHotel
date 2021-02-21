<%@include file="WEB-INF/jspf/libs.jspf"%>
<%@include file="WEB-INF/jspf/links.jspf"%>

<!doctype html>
<html lang="en">
<head>
    <title>My Hotel</title>
</head>
<body>
<!--================Header Area =================-->
<%@include file="WEB-INF/jsp/header.jsp" %>
<!--================Header Area =================-->

<!--================Banner Area =================-->
<section class="banner_area">
    <div class="booking_table d_flex align-items-center">
        <div class="overlay bg-parallax" data-stellar-ratio="0.9" data-stellar-vertical-offset="0"
             data-background=""></div>
        <div class="container">
            <div class="banner_content text-center">
                <h6><fmt:message key="away_from"/></h6>
                <h2><fmt:message key="relax"/></h2>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.id != null}">
        <form action="/api?command=orderRawRoom" method="post">
            <div class="hotel_booking_area position">
                <div class="container">
                    <div class="hotel_booking_table">
                        <div class="col-md-3">
                            <h2><fmt:message key="book"/><br> <fmt:message key="your_room"/></h2>
                        </div>
                        <div class="col-md-9">
                            <div class="boking_table">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="book_tabel_item">
                                            <div class="input-group">
                                                <input name="days" type='number' class="form-control nice-select"
                                                       placeholder='<fmt:message key="days"/>'/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="book_tabel_item">
                                            <div class="input-group">
                                                <input name="places" type='number' class="form-control nice-select"
                                                       placeholder='<fmt:message key="places"/>'/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="book_tabel_item">
                                        <div class="input-group">
                                            <select class="wide" name="type">
                                                <option value="1"><fmt:message key="economy"/></option>
                                                <option value="2"><fmt:message key="standard"/></option>
                                                <option value="3"><fmt:message key="lux"/></option>
                                            </select>
                                        </div>

                                        <input type="submit" class="book_now_btn button_hover" value='<fmt:message key="book_now"/>'/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <c:if test="${flag == true}">
            <div class="alert alert-light" role="alert">
                <fmt:message key="after_book"/>
            </div>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.id == null}">
        <div class="hotel_booking_area position">
            <div class="container">
                <div class="hotel_booking_table">
                    <div class="col-md-12">
                        <h1><fmt:message key="need_for_raw"/></h1>
                        <h4><fmt:message key="or_you_can_sign_up"/> <a href="/api?command=register"><fmt:message key="sign_up"/></a>.</h4>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</section>
<div class="text-center">
    <c:if test="${param.error == 1}">
        <div class="alert alert-danger">
            <fmt:message key="raw_order_error"/>
        </div>
    </c:if>
</div>
<!--================Banner Area =================-->


<!--================ Testimonial Area  =================-->
<%@include file="WEB-INF/jspf/testimonials.jspf"%>

<!--================ start footer Area  =================-->
<%@include file="WEB-INF/jsp/footer.jsp" %>
<!--================ End footer Area  =================-->


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="static/js/jquery-3.2.1.min.js"></script>
<script src="static/js/popper.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/vendors/owl-carousel/owl.carousel.min.js"></script>
<script src="static/js/jquery.ajaxchimp.min.js"></script>
<script src="static/js/mail-script.js"></script>
<script src="static/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="static/vendors/nice-select/js/jquery.nice-select.js"></script>
<script src="static/js/mail-script.js"></script>
<script src="static/js/stellar.js"></script>
<script src="static/vendors/lightbox/simpleLightbox.min.js"></script>
<script src="static/js/custom.js"></script>
</body>
</html>