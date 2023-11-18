/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BooksDAO;
import dal.CategoryDAO;
import dal.ChapterDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Books;
import model.Category;
import model.Chapter;

/**
 *
 * @author Admin
 */
@WebServlet(name="Edittor", urlPatterns={"/edittor"})
public class Edittor extends HttpServlet {
   
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
            out.println("<title>Servlet Edittor</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Edittor at " + request.getContextPath () + "</h1>");
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
        int bid = Integer.parseInt(request.getParameter("bid"));
        BooksDAO bDAO = new BooksDAO();
        Books b = bDAO.loadBooksByBid(bid);
        CategoryDAO cDAO = new CategoryDAO();
        ArrayList<Category> clist = cDAO.loadCategory();
        String oldc = cDAO.getCategoryNameByBookId(bid);
        String summary = bDAO.getSummaryContent(bid);
        ChapterDAO chDAO = new ChapterDAO();
        ArrayList<Chapter> chlist = chDAO.getChaplistByBookId(bid);
        request.setAttribute("chlist", chlist);
        request.setAttribute("summary", summary);
        request.setAttribute("oldc", oldc);
        request.setAttribute("clist", clist);
        request.setAttribute("book", b);
        request.getRequestDispatcher("editor.jsp").forward(request, response);
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
        ChapterDAO chDAO = new ChapterDAO();
        BooksDAO bDAO = new BooksDAO();
        String button = request.getParameter("button");
        int bid;
        if(button.equals("appendchapter")){
            bid = Integer.parseInt(request.getParameter("bid"));
            chDAO.appendChapter(bid);
        }else if(button.equals("deletechapter")){
            bid = Integer.parseInt(request.getParameter("bid"));
            int chid = Integer.parseInt(request.getParameter("chid"));
            chDAO.deleteChapter(chid);
        }
        else{
            bid = Integer.parseInt(button);
            String avata = request.getParameter("avatanew");
            String nname = request.getParameter("nname");
            String status = request.getParameter("status");
            String nsummary = request.getParameter("nsummary");
            String[] type = request.getParameterValues("myCheckbox");
            PrintWriter out = response.getWriter();
            if(!avata.trim().equals("")){
                bDAO.changeBookAvata(bid, avata);
            }
            if(!nname.trim().equals("")){
                bDAO.changeBookName(bid, nname);
            }
            if(!status.equals("")){
                bDAO.changeBookStatus(bid, Integer.parseInt(status));
            }
            if(!nsummary.trim().equals("")){
                bDAO.changeBookSummaryContent(bid, nsummary);
            }
            if(type != null){
                bDAO.deleteBookCategory(bid);
                for(String c:type){
                    bDAO.changeBookCategory(bid, Integer.parseInt(c));
                }
            }
            response.setCharacterEncoding("UTF-8");
            request.setAttribute("notify", "Change successfully");
            
        }
        //
        
        Books b = bDAO.loadBooksByBid(bid);
        CategoryDAO cDAO = new CategoryDAO();
        ArrayList<Category> clist = cDAO.loadCategory();
        String oldc = cDAO.getCategoryNameByBookId(bid);
        String summary = bDAO.getSummaryContent(bid);
        
        ArrayList<Chapter> chlist = chDAO.getChaplistByBookId(bid);
        request.setAttribute("chlist", chlist);
        request.setAttribute("summary", summary);
        request.setAttribute("oldc", oldc);
        request.setAttribute("clist", clist);
        request.setAttribute("book", b);
        request.getRequestDispatcher("editor.jsp").forward(request, response);


        
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
