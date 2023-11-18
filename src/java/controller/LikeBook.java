/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BooksDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LikeBook", urlPatterns = {"/like"})
public class LikeBook extends HttpServlet {

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
            out.println("<title>Servlet LikeBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LikeBook at " + request.getContextPath() + "</h1>");
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
        BooksDAO bDAO = new BooksDAO();
        Cookie[] cookies = request.getCookies();
        String bid = request.getParameter("bid");

        boolean islike = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Kiểm tra tên cookie cần lấy
                if (cookie.getName().equals("liked")) {
                    
                    String liked = cookie.getValue();
                    for (String i : liked.split(":")) {
                        if (i.equals(bid)) {
                            out.print("Bạn đã like truyện rồi");
                            islike = true;
                            response.setContentType("text/plain"); // Đặt kiểu dữ liệu phản hồi thành text/plain
                            response.setCharacterEncoding("UTF-8"); // Đặt encoding cho phản hồi
                            return; // Kết thúc xử lý servlet
                        }
                    }
                    if (islike == false) {
                        int flag = bDAO.likeBookBid(Integer.parseInt(bid));
                        String nvalue = cookie.getValue() + bid + ":";
                        cookie.setValue(nvalue);
                        out.print("Thích truyện");
                        response.setContentType("text/plain"); // Đặt kiểu dữ liệu phản hồi thành text/plain
                        response.setCharacterEncoding("UTF-8"); // Đặt encoding cho phản hồi
                        response.addCookie(cookie);
                        
                    }
                    break;
                }
            }
            
        } else {

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
