/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatnq.controller;

import nhatnq.registration.RegistrationDAO;
import nhatnq.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {
    
//    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String ERROR_PAGE = "invalid.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //1. lay toan bo thong tin nguoi dung

        //
        String button = request.getParameter("btAction");
        String url = ERROR_PAGE;
        try {
            if (button.equals("Login")) {
                String username = request.getParameter("txtUsername");
                String password = request.getParameter("txtPassword");
                //2. Call method cua model
                //2.1 NEW DAO
                RegistrationDAO dao = new RegistrationDAO();
                //2.2 call method of DAO
//                boolean result = dao.checkLogin(username, password);
               RegistrationDTO result = dao.checkLogin(username, password);
                //3. process request
                if (result != null) {
                    url = SEARCH_PAGE;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", result);
                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(60*3);
                    response.addCookie(cookie);
                }//user name and password are exist in database
            }//end if user clicked login
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();;
        } finally {
            response.sendRedirect(url);
            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
