/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DepartmentDB;
import model.dao.EmployeeDB;
import model.entity.Account;
import model.entity.Contract;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
public class CreateEmployee extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateEmployee at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Account a = (Account) request.getSession().getAttribute("acc");
            if (a != null) {
                int type = a.getRoleId();
                if (type == 1) {
                    request.setAttribute("current", java.time.LocalDate.now().plusDays(1));
                    request.setAttribute("department", DepartmentDB.getAllDepartmentNoManager());
                    request.setAttribute("manager", EmployeeDB.getAllManager());
                    request.getRequestDispatcher("CreateEmployee.jsp").include(request, response);
                } else {
                    response.sendRedirect("home");
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (IOException | ServletException e) {
            response.sendRedirect("login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String tel = Validation.CheckTel(request.getParameter("tel"));
        int positionId = Integer.parseInt(request.getParameter("positionId"));
        int managerId = Integer.parseInt(request.getParameter("managerId"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
        long basicSalary = Long.parseLong(request.getParameter("basicSalary"));
        String tDate = request.getParameter("tDate");
        String note = request.getParameter("note");
        Employee e = new Employee(fullName, email, address, tel, positionId, managerId, departmentId, sex).create(new Contract(Date.valueOf(tDate), basicSalary, note));

        
        response.sendRedirect("list-employee");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
