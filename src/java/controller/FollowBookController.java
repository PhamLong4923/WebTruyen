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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserAcc;

/**
 *
 * @author Admin
 */
@WebServlet(name="FollowBookController", urlPatterns={"/follow"})
public class FollowBookController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet FollowBookController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FollowBookController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        BooksDAO bDAO = new BooksDAO();
        PrintWriter out = response.getWriter();
        int bid = Integer.parseInt(request.getParameter("bid"));
        HttpSession session = request.getSession();
        if(session.getAttribute("loginacc") != null){
            UserAcc uacc = (UserAcc)session.getAttribute("loginacc");
            int uid = uacc.getUserId();
            if(bDAO.followBookBid(bid, uid) != 0){
                bDAO.upfollow(bid);
                out.print("<button class=\"action-button\" style=\"background-color: #e24040\"><i class=\"fa-sharp fa-solid fa-heart-circle-minus\"></i></i>Bo Theo doi</button>");
            }else{
                bDAO.unfollowBookBid(bid, uid);
                bDAO.downfollow(bid);
                out.print("<button class=\"action-button\" style=\"background-color: #e24040\"><i class=\"fa-solid fa-heart\"></i> Theo doi</button>");
            }
        }
       
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
