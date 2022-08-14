/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
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
 * @author NguyenVy
 */
public class UpdateCheckIn extends HttpServlet {

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
            out.println("<title>Servlet UpdateCheckIn</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCheckIn at " + request.getContextPath() + "</h1>");
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
                int id = -1;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (Exception e) {
                    response.sendRedirect("list-employee");
                    return;
                }

                switch (type) {
                    case 1: {
                        TimeKeeping t = new TimeKeeping(id);
                        request.setAttribute("t", t);
                        request.getRequestDispatcher("UpdateCheckIn.jsp").forward(request, response);
                        break;
                    }
                    case 2:
                    case 3: {
                        response.sendRedirect("list-employee");
                        break;
                    }
                }
            } else {
                response.sendRedirect("login");
            }
        } catch (IOException e) {
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
        int id = Integer.parseInt(request.getParameter("id"));
        TimeKeeping t = new TimeKeeping(id);
        String start = request.getParameter("startTime");
        if(start.length() < 7) {
            start = start + ":00";
        }
        String end = request.getParameter("endTime");
        if(end.length() < 7) {
            end = end + ":00";
        }
        String startOver = request.getParameter("startOverTime");
        if(startOver.length() < 7) {
            startOver = startOver + ":00";
        }
        String endOver = request.getParameter("endOverTime");
        if(endOver.length() < 7) {
            endOver = endOver + ":00";
        }
        Time startTime = null;
        Time endTime = null;
        Time startOverTime = null;
        Time endOverTime = null;
        if (start != null && !start.trim().equals(":00")) {
            try {
                startTime = Time.valueOf(start);
            } catch (Exception e) {
                throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
            }
        }
        if (end != null && !end.trim().equals(":00")) {
            try {
                endTime = Time.valueOf(end);
            } catch (Exception e) {
                throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
            }
        }
        if (startOver != null && !startOver.trim().equals(":00")) {
            try {
                startOverTime = Time.valueOf(startOver);
            } catch (Exception e) {
                throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
            }
        }
        if (endOver != null && !endOver.trim().equals(":00")) {
            try {
                endOverTime = Time.valueOf(endOver);
            } catch (Exception e) {
                throw new RuntimeException("Có lỗi xảy ra, vui lòng thử lại!");
            }
        }

        if (startTime != null && endTime != null && endTime.before(startTime)) {
            throw new RuntimeException("Thời gian kết thúc phải sau thời gian bắt đầu!");
        }
        if (startTime != null && startOverTime != null && endTime != null && startOverTime.before(endTime)) {
            throw new RuntimeException("Thời gian tăng ca phải sau thời gian kết thúc ngày làm việc!");
        }
        if (startTime != null && startOverTime != null && endTime != null && endOverTime != null && endOverTime.before(startOverTime)) {
            throw new RuntimeException("Thời gian kết thúc tăng ca phải sau thời gian bắt đầu tăng ca!");
        }
        
        t.setStartTime(startTime);
        t.setEndTime(endTime);
        t.setStartOverTime(startOverTime);
        t.setEndOverTime(endOverTime);

        t.update();
        response.sendRedirect("check-in-detail?id="+t.getEmployeeId());
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
