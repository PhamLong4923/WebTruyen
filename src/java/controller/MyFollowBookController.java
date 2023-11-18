/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BooksDAO;
import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Books;
import model.UserAcc;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MyFollowBookController", urlPatterns = {"/myfollow"})
public class MyFollowBookController extends HttpServlet {

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
            out.println("<title>Servlet MyFollowBookController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyFollowBookController at " + request.getContextPath() + "</h1>");
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
        BooksDAO bDAO = new BooksDAO();
        CategoryDAO cDAO = new CategoryDAO();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        UserAcc uacc = (UserAcc) session.getAttribute("loginacc");

        if (uacc != null) {
            int uid = uacc.getUserId();
            ArrayList<Books> blist = bDAO.myFollowBooks(uid);
            out.print("<div class=\"relative relative-content\">\n"
                    + "                            <span class=\"page-title\">Truyen dang theo doi</span>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"items\">");
            for (Books b : blist) {
                out.print("<div class=\"item item1\">\n"
                        + "                                        <div class=\"ct-image ct-top\"><a href=\"#\" onclick=\"readBook('" + b.getBookId() + "')\"><img src=\"" + b.getBookImg() + "\" width=\"100%\" height=\"100%\" alt=\"alt\"/><a></div>\n"
                        + "                                                    <div class=\"ct-info ct-foot\">\n"
                        + "                                                        <span class=\"nt-name\">" + b.getBookName() + "</span>\n"
                        + "\n"
                        + "                                                        <div class=\"new-chapter\">\n"
                        + "                                                            <span class=\"chapter-latest\"><i class=\"fa-solid fa-thumbs-up\"></i>:" + b.getLike() + "</span>\n"
                        + "                                                            <span class=\"chapter-latest-time\"><i class=\"fa-solid fa-eye\"></i>:" + b.getView() + "</span>\n"
                        + "                                                        </div>\n"
                        + "\n"
                        + "\n"
                        + "                                                    </div>\n"
                        + "                                                    </div>");
            }
            out.print("</div>");
        } else {
            out.print("<div class=\"relative relative-content\">\n"
                    + "                            <span class=\"page-title\">Hãy đăng nhập để xem theo dõi của bạn</span>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"items\">");
            out.print("</div>");
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
