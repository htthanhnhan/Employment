<%-- 
    Document   : CreateEmployee
    Created on : Jul 3, 2022, 3:22:13 AM
    Author     : ACER
--%>

<%@include file="./includes/Header.jsp" %>
<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%> 

<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Thêm mới nhân viên</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="create-employee" method="POST" style="display: flex" onsubmit="disable()">
                    <div class="col-6">
                        <h4>Thông tin nhân viên</h4>
                        <div class="form-group">
                            <label for="fullName" class="text-info">Tên nhân viên:</label><br>
                            <input type="text" name="fullName" id="fullName" class="form-control value" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="email" class="text-info">Email:</label><br>
                            <input type="email" name="email" id="email" class="form-control value" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="address" class="text-info">Địa chỉ:</label><br>
                            <input type="text" name="address" id="address" class="form-control value" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="tel" class="text-info">Số điện thoại:</label><br>
                            <input type="text" name="tel" id="tel" class="form-control value" autocomplete="off" required minlength="10" maxlength="10" pattern="[0-9]{1,10}" title="Số điện thoại chỉ chứa các ký tự số từ 0-9">
                        </div>
                        <div class="form-group">
                            <label for="positionId" class="text-info">Chức vụ:</label><br>
                            <select class="form-control" name="positionId" id="positionId" onchange="change(1)">
                                <option value="2" selected>Trưởng phòng</option>
                                <option value="3">Quản lý</option>
                                <option value="4">Nhân viên</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="departmentId" class="text-info">Phòng ban:</label><br>
                            <select class="form-control value" name="departmentId" id="departmentId" onchange="change(2)">
                                <c:forEach items="${department}" var="d">
                                    <option value="${d.id}">${d.name}</option>
                                </c:forEach>      
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="managerId" class="text-info">Người quản lý:</label><br>
                            <select class="form-control" name="managerId" id="managerId">
                                <option value='1001'>Ceo</option>     
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="sex" class="text-info">Giới tính:</label><br>
                            <select class="form-control" name="sex" id="sex">
                                <option value="true" selected>Nam</option>
                                <option value="false">Nữ</option>     
                            </select>
                        </div>
                    </div>
                    <div class='col-6'>
                        <h4>Thông tin hợp đồng</h4>
                        <div class="form-group">
                            <label for="basicSalary" class="text-info">Lương cơ bản:</label><br>
                            <input type="number" name="basicSalary" id="basicSalary" class="form-control value" autocomplete="off" required>
                        </div>
                        <div class="form-group">
                            <label for="tDate" class="text-info">Hạn hợp đồng:</label><br>
                            <input type="date" name="tDate" min="${current}" class="value" required>
                        </div>
                        <div class="form-group">
                            <label for="note" class="text-info">Ghi chú:</label><br>
                            <textarea name="note" id="note" class="form-control" style="overflow: auto" rows="10"></textarea>
                        </div>
                        <div class="form-group">
                            <br>
                            <input type="submit" id="submit" name="submit" class="btn btn-info btn-md" value="Đồng ý">
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- graph -->

        <!-- end graph -->


    </div>
    <!-- footer -->
    <script>
        let positionId = 0;
        let departmentId = 0;
        function getPositionId() {
            positionId = document.getElementById("positionId").value;
        }

        function getDepartmentId() {
            departmentId = document.getElementById("departmentId").value;
        }

        function change(type) {
            getPositionId();
            getDepartmentId();
            $.ajax({
                url: '/employee-management/change-value-create-employee',
                type: 'POST',
                data: {
                    type: type,
                    positionId: positionId,
                    departmentId: departmentId
                },
                success: function (data) {
                    if (type == 1) {
                        document.getElementById("departmentId").innerHTML = data.substring(0, data.indexOf('/////'));
                        document.getElementById("managerId").innerHTML = data.substring(data.indexOf('/////') + 5, data.length);
                    } else if (type == 2) {
                        document.getElementById("managerId").innerHTML = data;
                    }
                }
            });
        }
    </script>
    <%@include file="./includes/Footer.jsp" %>
