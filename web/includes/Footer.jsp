<%-- 
    Document   : Footer_admin
    Created on : Oct 23, 2021, 10:56:34 PM
    Author     : ThanhNhan
--%>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>

<div class="container-fluid">
    <div class="footer">
        <p>Quản lý nhân viên FPT<br><br>
        </p>
    </div>
</div>
</div>
</div>
</div>
<!-- model popup -->
</div>
<script>
    function disable() {
        document.getElementById("submit").disabled = "true";
    }
    window.onhashchange = function () {
        document.getElementById("submit").removeAttribute("disabled");
    }
</script>
<script src="./assets/js/jquery.min.js"></script>
<script src="./assets/js/popper.min.js"></script>
<script src="./assets/js/bootstrap.min.js"></script>
<!-- wow animation -->
<script src="./assets/js/animate.js"></script>
<!-- select country -->
<script src="./assets/js/bootstrap-select.js"></script>
<!-- owl carousel -->
<script src="./assets/js/owl.carousel.js"></script> 
<!-- chart js -->
<script src="./assets/js/Chart.min.js"></script>
<script src="./assets/js/Chart.bundle.min.js"></script>
<script src="./assets/js/utils.js"></script>
<script src="./assets/js/analyser.js"></script>
<!-- nice scrollbar -->
<script src="./assets/js/perfect-scrollbar.min.js"></script>
<script>
    var ps = new PerfectScrollbar('#sidebar');
</script>
<!-- fancy box js -->
<script src="./assets/js/jquery-3.3.1.min.js"></script>
<script src="./assets/js/jquery.fancybox.min.js"></script>
<!-- custom js -->
<script src="./assets/js/custom.js"></script>
<!-- calendar file css -->     
<script src="./assets/js/semantic.min.js"></script>
</body>
</html>