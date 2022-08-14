<%-- 
    Document   : ListDepartment
    Created on : Jul 10, 2022, 12:04:25 AM
    Author     : ACER
--%>

<%@page contentType="text/html" errorPage="error.jsp" pageEncoding="UTF-8"%>
<%@include file="./includes/Header.jsp" %>



<div class="midde_cont">
    <div class="container-fluid">
        <div class="row column_title">
            <div class="col-md-12">
                <div class="page_title">
                    <h2>Danh sách phòng ban</h2>
                </div>
            </div>
        </div>
        <div class="row column1">
            <div class="col-lg-12">
                <table class="table" style="font-size: 15px" cellspacing="3" cellpadding="3">
                    <thead>
                        <tr>
                            <th scope="col">Tên phòng</th>
                            <th scope="col">Số phòng</th>
                            <th scope="col">Người quản lý</th>
                            <th scope="col">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="l">            
                            <tr>
                                <td>${l.name}</td>
                                <td>${l.roomNo}</td>
                                <td>${l.managerName}</td>
                                <td><a href="update-department?id=${l.id}"><button class="btn btn-info">Update</button></a></td>
                            </tr>
                        </c:forEach>               
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <%@include file="includes/Footer.jsp" %>