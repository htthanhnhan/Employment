<%-- 
    Document   : UpdateEmployee
    Created on : Jul 9, 2022, 5:30:46 PM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Thông tin nhân viên: ${e.fullName}</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="update-employee" method="POST">

                    <table class="table" style="font-size: 15px; color: black; font-family: auto" cellspacing="3" cellpadding="3">
                        <tr>
                            <th scope="col">Họ và tên</th>
                            <td><input style="border: none; background: none" type="text" disabled="" value="${e.fullName}"></td>
                        </tr>
                        <tr>
                            <th scope="col">Account</th>
                            <td>${e.userName}</td>
                        </tr>
                        <tr>
                            <th scope="col">Email</th>
                            <td><input style="border: none; background: none" type="email" name="email" value="${e.email}"></td>
                        </tr>
                        <tr>
                            <th scope="col">Số điện thoại</th>
                            <td><input style="border: none; background: none" type="text" name="tel" value="${e.tel}" minlength="10" maxlength="10" pattern="[0-9]{1,10}" title="Số điện thoại chỉ chứa các ký tự số từ 0-9"></td>
                        </tr>
                        <tr>
                            <th scope="col">Địa chỉ</th>
                            <td><input style="border: none; background: none" type="text" name="address" value="${e.address}"></td>
                        </tr>
                        <tr>
                            <th scope="col">Chức vụ</th>
                            <td>
                                <select style="border: none; background: none" class="form-control" name="positionId" id="positionId">
                                    <option value="2" <c:if test="${e.positionId == 2}">selected</c:if>>Trưởng phòng</option>
                                    <option value="3" <c:if test="${e.positionId == 3}">selected</c:if>>Quản lý</option>
                                    <option value="4" <c:if test="${e.positionId == 4}">selected</c:if>>Nhân viên</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th scope="col">Người quản lý</th>
                                <td>
                                    <select style="border: none; background: none" class="form-control" name="managerId" id="positionId">
                                        <option value="0">--------------------</option>
                                    <c:forEach items="${manager}" var="m">
                                        <option value="${m.id}" <c:if test="${e.managerId == m.id}">selected</c:if>>${m.fullName}</option>
                                    </c:forEach> 
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="col">Phòng ban</th>
                            <td>
                                <select style="border: none; background: none" class="form-control" name="departmentId" id="departmentId">
                                    <c:forEach items="${department}" var="d">
                                        <option value="${d.id}" <c:if test="${e.departmentId == d.id}">selected</c:if>>${d.name}</option>
                                    </c:forEach>      
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="col">Giới tính</th>
                            <td>
                                <c:if test="${e.sex == true}">Nam</c:if>
                                <c:if test="${e.sex == false}">Nữ</c:if>
                                </td>
                            </tr>
                        </table>
                        <input type="number" name="id" value="${e.id}" hidden>
                    <div style="display: flex">
                        <div class="center" style="margin: 0 10px"><a class="main_bt" onclick="javascript:history.go(-1);">Quay lại</a></div>
                        <div class="center"><button type="submit" class="main_bt" id="submit" style="background: #0069d9; color: black">Sửa</button></div>
                    </div>

                </form>
            </div>

        </div>
    </div>


    <%@include file="includes/Footer.jsp" %>