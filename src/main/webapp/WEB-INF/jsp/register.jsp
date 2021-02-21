<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="../../static/image/favicon.png" type="image/png">
    <title>Register</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../../static/css/bootstrap.css">
    <link rel="stylesheet" href="../../static/vendors/linericon/style.css">
    <link rel="stylesheet" href="../../static/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../static/vendors/owl-carousel/owl.carousel.min.css">
    <link rel="stylesheet" href="../../static/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../../static/vendors/nice-select/css/nice-select.css">
    <link rel="stylesheet" href="../../static/vendors/owl-carousel/owl.carousel.min.css">
    <!-- main css -->
    <link rel="stylesheet" href="../../static/css/style.css">
    <link rel="stylesheet" href="../../static/css/responsive.css">
</head>
<body>
<!--================Header Area =================-->
<%@include file="header.jsp" %>
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
</section>
<section>
    <div class="container register-form">
        <div class="form">
            <div class="note">
                <p><fmt:message key="register"/>.</p>
            </div>

            <div class="form-content">
                <form action="/api?command=createAccount" method="post">
                    <div class="row-center">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder='<fmt:message key="enter_email"/>'
                                       value="" name="email"/>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control"
                                       placeholder='<fmt:message key="enter_pass"/>' value="" name="password"/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder='<fmt:message key="enter_name"/>'
                                       value="" name="name"/>
                            </div>
                        </div>
                        <div class="text-center">
                            <c:if test="${param.get('error') == 2}">
                            <br/>
                            <div class="alert alert-danger">
                               <fmt:message key="invalid_input"/>
                            </div>
                        </c:if>
                        </div>
                    </div>

                    <div class="text-center">
                        <input type="submit" class="btnSubmit" value='<fmt:message key="btn.submit"/>'/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
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
<script src="../../static/js/mail-script.js"></script>
<script src="../../static/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="../../static/vendors/nice-select/js/jquery.nice-select.js"></script>
<script src="../../static/js/mail-script.js"></script>
<script src="../../static/js/stellar.js"></script>
<script src="../../static/vendors/lightbox/simpleLightbox.min.js"></script>
<script src="../../static/js/custom.js"></script>
</body>
</html>