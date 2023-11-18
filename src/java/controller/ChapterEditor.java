/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BooksDAO;
import dal.ChapterDAO;
import dal.ChapterImgDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.ChapterImg;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChapterEditor", urlPatterns = {"/chapedittor"})
public class ChapterEditor extends HttpServlet {

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
            out.println("<title>Servlet ChapterEditor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChapterEditor at " + request.getContextPath() + "</h1>");
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
        int chid = Integer.parseInt(request.getParameter("chid"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        ChapterImgDAO chimgDAO = new ChapterImgDAO();
        ChapterDAO chDAO = new ChapterDAO();
        BooksDAO bDAO = new BooksDAO();
        ArrayList<ChapterImg> cilist = chimgDAO.getAllImgByChapId(chid);
        String bname = bDAO.loadBooksByBid(bid).getBookName();
        String chap = chDAO.getChapNameByChid(chid);
        System.out.println(chap);
        request.setAttribute("bid", bid);
        request.setAttribute("chid", chid);
        request.setAttribute("content", bname + ">" + chap);
        request.setAttribute("cilist", cilist);
        request.getRequestDispatcher("managerchapimg.jsp").forward(request, response);
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
        ChapterDAO chDAO = new ChapterDAO();
        ChapterImgDAO imgDAO = new ChapterImgDAO();
        String button = request.getParameter("button");
        int chid = Integer.parseInt(request.getParameter("chid"));

        if (button.equals("changecontent")) {
            String newcontent = request.getParameter("ncontent");
            chDAO.changeChapterNameByChid(chid, newcontent);
        } else if (button.equals("appendimg")) {
            String img = request.getParameter("img");
            if (!img.trim().equals("")) {
                imgDAO.appendChapterImg(chid, img);
            }

        } else if (button.equals("delete")) {
            int contentId = Integer.parseInt(request.getParameter("contentId"));
            imgDAO.deleteChapterImg(contentId);
        } else if (button.equals("replace")) {
            String newImg = request.getParameter("reimg");
            if (!newImg.trim().equals("")) {
                int contentId = Integer.parseInt(request.getParameter("contentId"));
                imgDAO.replaceChapterImg(contentId, newImg);
            }

        }

        int bid = Integer.parseInt(request.getParameter("bid"));
        ChapterImgDAO chimgDAO = new ChapterImgDAO();
        BooksDAO bDAO = new BooksDAO();
        ArrayList<ChapterImg> cilist = chimgDAO.getAllImgByChapId(chid);
        String bname = bDAO.loadBooksByBid(bid).getBookName();
        String chap = chDAO.getChapNameByChid(chid);
        System.out.println(chap);
        request.setAttribute("bid", bid);
        request.setAttribute("chid", chid);
        request.setAttribute("content", bname + ">" + chap);
        request.setAttribute("cilist", cilist);
        request.getRequestDispatcher("managerchapimg.jsp").forward(request, response);

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
