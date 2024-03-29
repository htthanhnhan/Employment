/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.EmployeeDB;
import model.entity.Account;
import model.entity.Employee;

/**
 *
 * @author ACER
 */
public class ListEmployee extends HttpServlet {

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
            out.println("<title>Servlet ListAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListAccount at " + request.getContextPath() + "</h1>");
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
                switch (type) {
                    case 1: {
                        request.setAttribute("list", EmployeeDB.getAllEmployee());
                        request.getRequestDispatcher("ListEmployee.jsp").include(request, response);
                        break;
                    }
                    case 2: {
                        switch (a.getPositionId()) {
                            case 1:
                                request.setAttribute("list", EmployeeDB.getAllEmployee());
                                break;
                            case 2:
                                request.setAttribute("list", EmployeeDB.getAllEmployeeByDepartmentId(new Employee(a.getEmpId()).getDepartmentId()));
                                break;
                            default:
                                request.setAttribute("list", EmployeeDB.getAllEmployeeByManagerId(a.getEmpId()));
                                break;
                        }
                        request.getRequestDispatcher("ListEmployee.jsp").include(request, response);
                        break;
                    }
                    case 3: {
                        response.sendRedirect("home");
                        break;
                    }
                    default:
                        response.sendRedirect("home");
                        break;
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
        Account a = (Account) request.getSession().getAttribute("acc");
        if (a.getRoleId() == 1) {
            int id = Integer.parseInt(request.getParameter("id"));
            new Employee(id).block();
        } else {
            response.sendRedirect("home");
        }
        
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
