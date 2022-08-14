<%-- 
    Document   : Login
    Created on : Jul 1, 2022, 11:23:12 AM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Welcome to employee management</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- site icon -->
        <link rel="icon" href="assets/imgs/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
        <!-- site css -->
        <link rel="stylesheet" href="assets/css/style.css" />
        <!-- responsive css -->
        <link rel="stylesheet" href="assets/css/responsive.css" />
        <!-- color css -->
        <link rel="stylesheet" href="assets/css/colors.css" />
        <!-- select bootstrap -->
        <link rel="stylesheet" href="assets/css/bootstrap-select.css" />
        <!-- scrollbar css -->
        <link rel="stylesheet" href="assets/css/perfect-scrollbar.css" />
        <!-- custom css -->
        <link rel="stylesheet" href="assets/css/custom.css" />
        <!-- calendar file css -->
        <link rel="stylesheet" href="assets/js/semantic.min.css" />
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="inner_page login">
        <div class="full_container">
            <div class="container">
                <div class="center verticle_center full_height">
                    <div class="login_section">
                        <div class="logo_login">
                            <div class="center">
                                <img width="210" src="assets/imgs/logo/logo.png" alt="#" />
                            </div>
                        </div>
                        <div class="login_form">
                            <form action="forgot-password" method="post" onsubmit="disable()">
                                <fieldset>
                                    <div class="field">
                                        <label class="label_field">Tên đăng nhập</label>
                                        <input type="text" name="userName" required="" placeholder="Tên đăng nhập..." />
                                    </div>
                                    <div class="field">
                                        <label class="label_field">Email</label>
                                        <input type="email" name="email" required="" placeholder="Email..." />
                                    </div>
                                    <div style="display: flex; text-align: right">
                                        <div class="field margin_0">
                                            <label class="label_field hidden">hidden label</label>
                                            <button class="main_bt" id="submit">Đồng ý</button>
                                        </div>
                                        <div class="field margin_0">
                                            <label class="label_field hidden">hidden label</label>
                                            <a href="login"><button type="button" class="btn btn-link">Quay lại trang đăng nhập?</button></a>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- wow animation -->
        <script src="js/animate.js"></script>
        <!-- select country -->
        <script src="js/bootstrap-select.js"></script>
        <!-- nice scrollbar -->
        <script src="js/perfect-scrollbar.min.js"></script>
        <script>
            var ps = new PerfectScrollbar('#sidebar');
        </script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
    </body>
</html>
