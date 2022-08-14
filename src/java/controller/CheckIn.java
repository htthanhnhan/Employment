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
import model.dao.TimeKeepingDB;
import model.entity.Account;
import model.entity.Employee;
import model.entity.TimeKeeping;

/**
 *
 * @author ACER
 */
public class CheckIn extends HttpServlet {

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
            out.println("<title>Servlet CheckIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckIn at " + request.getContextPath() + "</h1>");
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
                        response.sendRedirect("home");
                        break;
                    }
                    case 2:
                    case 3: {
                        if (a.getPositionId() == 1) {
                            response.sendRedirect("home");
                            break;
                        } else {
                            TimeKeeping current = TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(a.getEmpId()));
                            String checkIn = "disabled";
                            String checkOut = "disabled";
                            String checkInOverTime = "disabled";
                            String checkOutOverTime = "disabled";
                            if (current == null) {
                                checkIn = "";
                            } else if (current.getStartTime() != null && current.getEndTime() == null) {
                                checkOut = "";
                            } else if (current.getStartTime() != null && current.getEndTime() != null && current.getStartOverTime() == null) {
                                checkInOverTime = "";
                            } else if (current.getStartTime() != null && current.getEndTime() != null && current.getStartOverTime() != null && current.getEndOverTime() == null) {
                                checkOutOverTime = "";
                            }
                            request.setAttribute("checkIn", checkIn);
                            request.setAttribute("checkOut", checkOut);
                            request.setAttribute("checkInOverTime", checkInOverTime);
                            request.setAttribute("checkOutOverTime", checkOutOverTime);
                            request.setAttribute("current", current);
                            request.setAttribute("list", TimeKeepingDB.getTimeKeepingByEmployee(new Employee(a.getEmpId())));
                            request.getRequestDispatcher("CheckIn.jsp").include(request, response);
                            break;
                        }
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
        PrintWriter out = response.getWriter();
        Account a = (Account) request.getSession().getAttribute("acc");
        int id = a.getEmpId();
        int type = Integer.parseInt(request.getParameter("type"));
        switch (type) {
            case 1:
                if(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)) == null){
                    new Employee(id).startTime();
                } else {
                    throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
                }
                
                break;
            case 2:
                if(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getEndTime() == null) {
                    new TimeKeeping(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getId()).endTime();
                } else {
                    throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
                }
                break;
            case 3:
                if(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)) != null && TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getEndTime() != null && TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getStartOverTime() == null) {
                    new TimeKeeping(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getId()).startOverTime();    
                } else {
                    throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
                }
                
                break;
            case 4:
                if(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getEndOverTime() == null) {
                    new TimeKeeping(TimeKeepingDB.getTimeKeepingByEmployeeAndCurrentDate(new Employee(id)).getId()).endOverTime(); 
                } else {
                    throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
                }
                break;
        }
        for (TimeKeeping t : TimeKeepingDB.getTimeKeepingByEmployee(new Employee(a.getEmpId()))) {
            out.println("<tr>");
            out.println("<td>" + t.getCurrentDate() + "</td>");
            out.println("<td>" + t.getStartTime() + "</td>");
            out.println("<td>" + (t.getEndTime() != null ? t.getEndTime() : "") + "</td>");
            out.println("<td>" + t.getWorkingHours() + "</td>");
            out.println("<td>" + t.getPunish() + "</td>");
            out.println("<td>" + (t.getStartOverTime() != null ? t.getStartOverTime() : "") + "</td>");
            out.println("<td>" + (t.getEndOverTime() != null ? t.getEndOverTime() : "") + "</td>");
            out.println("<td>" + t.getOverTimeHours() + "</td>");
            out.println("</tr>");
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
