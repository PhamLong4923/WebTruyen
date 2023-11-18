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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Books;
import model.Category;
import model.UserAcc;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

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
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
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
        CategoryDAO cDAO = new CategoryDAO();
        BooksDAO bDAO = new BooksDAO();
        //blist
        ArrayList<Books> blist = bDAO.loadBooks();
        //clist
        ArrayList<Category> clist = cDAO.loadCategory();
        //cookie history
        boolean checkhbid = false;
        boolean checkhchid = false;
        boolean checkliked = false;
        Cookie[] cookies = request.getCookies(); 
        if (cookies != null) {
            for (Cookie c : cookies) {
                if(c.getName().equals("hbid")) {
                    checkhbid = true;
                }
                if(c.getName().equals("hchid")) {
                    checkhchid = true;
                }
                if(c.getName().equals("liked")) {
                    checkliked = true;
                }
            }
        }
        if(checkhbid==false){
            Cookie hbid = new Cookie("hbid", "");
            hbid.setMaxAge(60*60*24*7);
            response.addCookie(hbid);
        }
        if(checkhchid==false){
            Cookie hchid = new Cookie("hchid", "");
            hchid.setMaxAge(60*60*24*31*365*100);
            response.addCookie(hchid);
        }
        if(checkliked==false){
            Cookie liked = new Cookie("liked", "");
            liked.setMaxAge(60*60*24*31*365*100);
            response.addCookie(liked);
        }
        
        request.setAttribute("clist", clist);
        request.setAttribute("blist", blist);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
        System.out.println("hehe");
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
