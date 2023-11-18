/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Comments;
import model.UserAcc;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CommentController", urlPatterns = {"/comment"})
public class CommentController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentController at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        UserAcc uacc = (UserAcc) session.getAttribute("loginacc");
        CommentsDAO coDAO = new CommentsDAO();
        int bid = Integer.parseInt(request.getParameter("bid"));
        
        String comment = request.getParameter("yourcm");
        if (uacc == null) {
            out.print("<p>Hay dang nhap de binh luan</p>");
            ArrayList<Comments> colist = coDAO.getCommentByBookId(bid);
            for (Comments co : colist) {
                out.print("<div class=\"comment\">\n"
                        + "                                    <div class=\"avataruser\"><img src=\"" + co.getUserAvata() + "\" alt=\"useravata\" style=\"width: 60px; height: 60px; border-radius: 50%\"></div>\n"
                        + "                                    <div class=\"commentcontent\"><p class=\"username\">" + co.getUserName() + "</p><p>" + co.getComment() + "</p></div>\n"
                        + "                                </div>");

            }
        } else {

            coDAO.postCommentBook(comment, uacc.getUserId(), bid);
            ArrayList<Comments> colist = coDAO.getCommentByBookId(bid);
            for (Comments co : colist) {
                out.print("<div class=\"comment\">\n"
                        + "                                    <div class=\"avataruser\"><img src=\"" + co.getUserAvata() + "\" alt=\"useravata\" style=\"width: 60px; height: 60px; border-radius: 50%\"></div>\n"
                        + "                                    <div class=\"commentcontent\"><p class=\"username\">" + co.getUserName() + "</p><p>" + co.getComment() + "</p></div>\n"
                        + "                                </div>");

            }
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
