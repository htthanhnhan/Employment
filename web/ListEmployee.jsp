<%-- 
    Document   : ListEmployee
    Created on : Jul 4, 2022, 8:32:13 PM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Danh sách nhân viên</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
                    <thead>
                        <tr>
                            <th scope="col">Tên nhân viên</th>
                            <th scope="col">Account</th>
                            <th scope="col">Avatar</th>
                            <th scope="col">Người quản lý</th>
                            <th scope="col">Giới tính</th>
                            <th scope="col">Phòng ban</th>
                            <th scope="col">Chức vụ</th>
                            <th scope="col">Thưởng phạt</th>
                                <c:if test="${acc.roleId == 1}">
                                <th scope="col">Khóa tài khoản</th>
                                <th scope="col">Update</th>
                                <th scope="col">Reset password</th>
                                </c:if>
                                <c:if test="${acc.roleId == 2}">
                                <th scope="col">Chốt lương</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="l">            
                            <tr>
                                <td><a href="contract-detail?id=${l.id}">${l.fullName}</a></td>
                                <td>${l.userName}</td>
                                <td><img width="100px" src="${l.avatar}" alt="${l.fullName}"></td>
                                <td>${l.managerName}</td>
                                <td>${l.gen}</td>
                                <td>${l.departmentName}</td>
                                <td>${l.positionName}</td>
                                <td><a href="payoff-detail?id=${l.id}">Xem</a></td>
                                <c:if test="${acc.roleId == 1}">
                                    <td><a id="block${l.id}" onclick="block(${l.id}, '${l.fullName}')" style="cursor: pointer">${l.block}</a></td>
                                    <td><a style="color: blue" href="update-employee?id=${l.id}">Update</a></td>
                                    <td><a style="color: red; cursor: pointer" onclick="reset(${l.id}, '${l.fullName}')">Reset</a></td>
                                </c:if>
                                <c:if test="${acc.roleId == 2}">
                                    <td><button class="btn btn-dark" onclick="closing(${l.id}, '${l.fullName}',this)" <c:if test="${l.checkSalary}">disabled</c:if> >Chốt lương</button></td>
                                    </c:if>
                            </tr>
                        </c:forEach>               
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        function block(id, fullName) {
            var status = document.getElementById("block" + id).innerHTML;
            if (status.trim() == "Khóa") {
                var conf = confirm("Bạn có chắc chắn muốn khóa tài khoản của nhân viên " + fullName + " không?");
            } else {
                var conf = confirm("Bạn có chắc chắn muốn mở tài khoản của nhân viên " + fullName + " không?");
            }
            if (conf) {
                $.ajax({
                    url: '/employee-management/list-employee',
                    type: 'POST',
                    data: {
                        id: id,
                        type: 1
                    },
                    success: function () {

                        if (status.trim() == "Khóa") {
                            document.getElementById("block" + id).innerHTML = "Mở";
                            alert("Khóa tài khoản của nhân viên " + fullName + " thành công!");
                        } else {
                            document.getElementById("block" + id).innerHTML = "Khóa";
                            alert("Mở tài khoản của nhân viên " + fullName + " thành công!");
                        }
                    }
                });
            }
        }

        function reset(id, fullName) {
            var conf = confirm("Bạn có chắc chắn muốn đặt lại mật khẩu cho nhân viên " + fullName + " không?");
            if (conf) {
                $.ajax({
                    url: '/employee-management/list-employee',
                    type: 'POST',
                    data: {
                        id: id,
                        type: 2
                    },
                    success: function () {
                        alert("Đặt lại mật khẩu cho nhân viên " + fullName + " thành công!")
                    }
                });
            }
        }
        
        function closing(id, fullName, button) {
            var conf = confirm("Bạn có chắc chắn muốn chốt lương cho nhân viên " + fullName + " không?");
            if (conf) {
                button.disabled = "true";
                $.ajax({
                    url: '/employee-management/cal-salary',
                    type: 'POST',
                    data: {
                        id: id,
                    },
                    success: function () {
                        alert("Chốt lương cho nhân viên " + fullName + " thành công!")
                    },
                    error: function () {
                        alert("Có lỗi xảy ra, vui lòng thử lại!");
                    }
                });
            }
        }
    </script>

    <%@include file="includes/Footer.jsp" %>

