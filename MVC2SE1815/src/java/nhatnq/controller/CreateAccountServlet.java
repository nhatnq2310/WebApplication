/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatnq.controller;

import nhatnq.registration.RegistrationCreateError;
import nhatnq.registration.RegistrationDAO;
import nhatnq.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "CreateAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        //1. get all user information
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullname");
        String confirm = request.getParameter("txtConfirm");
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        String url = ERROR_PAGE;
        try {
            //2. check user's errors
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthErr("Username is required 6-30 character");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setPasswordLengthErr("Password is required 6-30 character");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatchedErr("Confirm must be matched password");
            }
            if (fullname.trim().length() < 6 || fullname.trim().length() > 30) {
                foundErr = true;
                errors.setFullNameLengthErr("Fullname is required 6-30 character");
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                //3. call method of model
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO account = new RegistrationDTO(
                        username, password, fullname, foundErr);
                boolean result = dao.createAccount(account);
                //4. process result
                if (result) {
                    url = LOGIN_PAGE;
                }
            }

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL" + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameisExisted("is Existed!");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (ClassNotFoundException ex) {

            log("CreateAccountServlet _ ClassNotFound" + ex.getMessage());

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url); //vi dang luu loi o attribute
            rd.forward(request, response);
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