<%-- 
    Document   : UpdateDepartment
    Created on : Jul 10, 2022, 12:13:16 AM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Cập nhật thông tin checkin nhân viên ${t.employee.fullName}</h2>
                    <i>(Lưu ý: mỗi lần cập nhật sẽ tính một lần vi phạm cho nhân viên)</i>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <form action="update-check-in" method="POST">

                    <table class="table" style="font-size: 15px; color: black; font-family: auto" cellspacing="3" cellpadding="3">
                        <tr>
                            <th scope="col">Start time</th>
                            <td><input style="border: none; background: none" type="time" name="startTime" value="${t.startTime}"></td>
                        </tr>
                        <tr>
                            <th scope="col">End time</th>
                            <td><input style="border: none; background: none" type="time" name="endTime" value="${t.endTime}"></td>
                        </tr>
                        <tr>
                            <th scope="col">Start over time</th>
                            <td><input style="border: none; background: none" type="time" name="startOverTime" value="${t.startOverTime}"></td>
                        </tr>
                        <tr>
                            <th scope="col">End over time</th>
                            <td><input style="border: none; background: none" type="time" name="endOverTime" value="${t.endOverTime}"></td>
                        </tr>
                    </table>
                    <input type="number" name="id" value="${t.id}" hidden>
                    <div style="display: flex">
                        <div class="center" style="margin: 0 10px"><a class="main_bt" onclick="javascript:history.go(-1);">Quay lại</a></div>
                        <div class="center"><button type="submit" class="main_bt" id='submit' style="background: #0069d9; color: black">Sửa</button></div>
                    </div>

                </form>
            </div>

        </div>
    </div>


    <%@include file="includes/Footer.jsp" %>