<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="image/favicon.png" type="image/png">
        <title>My Hotel</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="../../static/css/bootstrap.css">
        <link rel="stylesheet" href="../../static/vendors/linericon/style.css">
        <link rel="stylesheet" href="../../static/css/font-awesome.min.css">
        <link rel="stylesheet" href="../../static/vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" href="../../static/vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" href="../../static/vendors/owl-carousel/owl.carousel.min.css">
        <!-- main css -->
        <link rel="stylesheet" href="../../static/css/style.css">
        <link rel="stylesheet" href="../../static/css/responsive.css">
    </head>
    <body>
        <!--================Header Area =================-->
        <%@include file="header.jsp"%>
        <!--================Header Area =================-->
        
        <!--================Breadcrumb Area =================-->
        <section class="breadcrumb_area">
            <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
            <div class="container">
                <div class="page-cover text-center">
                    <h2 class="page-cover-tittle"><fmt:message key="about_us"/></h2>
                    <ol class="breadcrumb">
                        <li><a href="../../index.jsp"><fmt:message key="home"/></a></li>
                        <li class="active"><fmt:message key="about_us"/></li>
                    </ol>
                </div>
            </div>
        </section>
        <!--================Breadcrumb Area =================-->
        
        <!--================ About History Area  =================-->
        <section class="about_history_area section_gap">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 d_flex align-items-center">
                        <div class="about_content ">
                            <h2 class="title title_color"><fmt:message key="about_us"/></h2>
                            <p><fmt:message key="desc"/></p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <img class="img-fluid" src="../../static/image/about_bg.jpg" alt="img">
                    </div>
                </div>
            </div>
        </section>
        <!--================ About History Area  =================-->
        
       
        
        <!--================ Testimonial Area  =================-->
        <section class="testimonial_area section_gap">
            <div class="container">
                <div class="section_title text-center">
                    <h2 class="title_color"><fmt:message key="testimonial"/></h2>
                </div>
                <div class="testimonial_slider owl-carousel">
                    <div class="media testimonial_item">
                        <img class="rounded-circle" src="../../static/image/testtimonial-1.jpg" alt="">
                        <div class="media-body">
                            <p>Beautiful place, Thank you for the great  </p>
                            <a href="#"><h4 class="sec_h4">Lisa Kudrow</h4></a>
                            <div class="star">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star-half-o"></i>
                            </div>
                        </div>
                    </div>
					<div class="media testimonial_item">
                        <img class="rounded-circle" src="../../static/image/testtimonial-2.jpg" alt="">
                        <div class="media-body">
                            <p>Comfortable, nice, decent price for an apartment </p>
                            <a href="#"><h4 class="sec_h4">Jennifer Aniston</h4></a>
                            <div class="star">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================ Testimonial Area  =================-->
        
       <!--================ start footer Area  =================-->	
      <%@include file="footer.jsp"%>
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