<%-- 
    Document   : CheckIn
    Created on : Jul 11, 2022, 10:41:04 PM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Bảng chấm công nhân viên ${e.fullName}</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
                    <thead>
                        <tr>
                            <th scope="col">Ngày</th>
                            <th scope="col">Start time</th>
                            <th scope="col">End time</th>
                            <th scope="col">Số giờ làm việc</th>
                            <th scope="col">Số lần vi phạm</th>
                            <th scope="col">Start over time</th>
                            <th scope="col">End over time</th>
                            <th scope="col">Số giờ tăng ca</th>
                                <c:if test="${acc.roleId == 1}">
                                <th scope="col">Sửa</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody id="data">
                        <c:forEach items="${list}" var="l">            
                            <tr>
                                <td>${l.currentDate}</td>
                                <td>${l.startTime}</td>
                                <td>${l.endTime}</td>
                                <td>${l.workingHours}</td>
                                <td>${l.punish}</td>
                                <td>${l.startOverTime}</td>
                                <td>${l.endOverTime}</td>
                                <td>${l.overTimeHours}</td>
                                <c:if test="${acc.roleId == 1}">
                                    <td>
                                        <c:if test="${l.checkPay == false}">
                                            <a href="update-check-in?id=${l.id}"><button class="btn btn-danger">Sửa</button></a>
                                        </c:if>
                                        
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>               
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        function timeKeeping(type, button) {
            button.disabled = "true";
            $.ajax({
                url: '/employee-management/check-in',
                type: 'POST',
                data: {
                    type: type
                },
                success: function (data) {
                    if (type == 1) {
                        document.getElementById("checkIn").disabled = "true";
                        document.getElementById("checkOut").removeAttribute("disabled");
                    } else if (type == 2) {
                        document.getElementById("checkOut").disabled = "true";
                        document.getElementById("checkInOverTime").removeAttribute("disabled");
                    } else if (type == 3) {
                        document.getElementById("checkInOverTime").disabled = "true";
                        document.getElementById("checkOutOverTime").removeAttribute("disabled");
                    } else {
                        document.getElementById("checkOutOverTime").disabled = "true";
                    }
                    document.getElementById("data").innerHTML = data;
                },
                error: function () {
                    alert("Có lỗi xảy ra, vui lòng thử lại!");
                }
            });
        }
    </script>

    <%@include file="includes/Footer.jsp" %>