/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BooksDAO;
import dal.ChapterDAO;
import dal.ChapterImgDAO;
import dal.CommentsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Chapter;
import model.ChapterImg;
import model.Comments;
import org.apache.catalina.connector.CoyoteWriter;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChapterSelectController", urlPatterns = {"/readchapter"})
public class ChapterSelectController extends HttpServlet {

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
            out.println("<title>Servlet ChapterSelectController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChapterSelectController at " + request.getContextPath() + "</h1>");
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
        BooksDAO bDAO = new BooksDAO();
        ChapterDAO chDAO = new ChapterDAO();
        ChapterImgDAO imgDAO = new ChapterImgDAO();
        String title = chDAO.getTitleByChid(chid);
        int bid = chDAO.getBidByChid(chid);
        ArrayList<Chapter> chlist = chDAO.getChaplistByBookId(bid);
        ArrayList<ChapterImg> imglist = imgDAO.getAllImgByChapId(chid);
        
        //history
        Cookie[] cookies = request.getCookies();
        boolean isReadedB = false;
        boolean isReadedCh = false;
        if (cookies != null) {
            // Kiểm tra tên cookie cần lấy
            for (Cookie cookie : cookies) {
                System.out.println("1");
                //log bookHistory
                if (cookie.getName().equals("hbid")) {
                    
                    String bed = cookie.getValue();
                    for (String i : bed.split(":")) {
                        System.out.println("2");
                        if (i.equals(String.valueOf(bid))) {
                            isReadedB = true;
                        }
                    }
                    if (isReadedB == false) {
                        int flag = bDAO.upview(bid);
                        String nvalue = cookie.getValue() + bid + ":";
                        cookie.setValue(nvalue);                                             
                        response.addCookie(cookie);
                        
                    }
                }
                 //log chapHistory
                if (cookie.getName().equals("hchid")) {
                    
                    String ched = cookie.getValue();
                    for (String i : ched.split(":")) {
                        System.out.println("3");
                        if (i.equals(String.valueOf(chid))) {
                            isReadedCh = true;
                            
                        }
                    }
                    if (isReadedCh == false) {
                        String nvalue = cookie.getValue() + chid + ":";
                        cookie.setValue(nvalue);                                             
                        response.addCookie(cookie);
                        
                    }
                   
                }
                
            }
           
            
        }
        
        PrintWriter out = response.getWriter();

        out.print("<div class=\"chaptercontent\">\n"
                + "                    <div class=\"controll-banner\">\n"
                + "                        <div class=\"bookselectname\">" + title + "</div>\n"
                + "                        <div class=\"errorbutton\"><a href=\"\"><i class=\"fa-solid fa-triangle-exclamation\" style=\"color: #fbd309;\"></i>Báo Lỗi Chương</a></div>\n"
                + "                        <div class=\"notifycation\"><i class=\"fa-solid fa-circle-info\" style=\"color: #eee044;\"></i>Sử dụng mũi tên trái (←) hoặc phải (→) để chuyển chapter</div>\n"
                + "                        <div class=\"transitionbutton\">\n"
                + "                            <a class=\"prev control-button link-prev-chap\" href=\"\" ><i class=\"fa fa-chevron-left\"></i></a>\n"
                + "                            <a class=\"next disable control-button\" href=\"\"><i class=\"fa fa-chevron-right\"></i></a>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    \n");
        for(ChapterImg img:imglist){
            out.print("                    <div class=\"chapter-img\">\n"
                + "                        <img src=\""+img.getChapterImgSrc()+"\" width=\"100%\" height=\"100%\" alt=\"alt\"/>                       \n"
                + "                    </div>\n");
        }
                
                out.print("                    \n"
                + "                </div>\n"
                + "                <div class=\"chapter-transition\">\n"
                + "                    <div class=\"transition\">\n"
                + "                        <a href=\"/WebTruyen/home\" class=\"home\"><i class=\"fa fa-home\" aria-hidden=\"true\"></i></a>\n"
                + "                        <a class=\"prev control-button link-prev-chap\" href=\"#\" onclick=\"changeSelectValue(1)\" ><i class=\"fa fa-chevron-left\"></i></a>\n"
                + "                        <select id=\"mySelect\" class=\"form-select\" aria-label=\"Default select example\" onchange=\"readselectchapter()\">");
        for (Chapter c : chlist) {
            if (c.getChapterId() != chid) {
                out.print("<option value=\""+c.getChapterId()+"\">Chapter" + c.getChapterNumber() + ":" + c.getChapterName() + "</option>");
            } else {
                out.print("<option selected value=\""+c.getChapterId()+"\">Chapter" + c.getChapterNumber() + ":" + c.getChapterName() + "</option>");
            }
        }
        out.print("</select>\n"
                + "                        <a class=\"next disable control-button\" href=\"#\" onclick=\"changeSelectValue(-1)\"><i class=\"fa fa-chevron-right\"></i></a>\n"
                + "                        <a href=\"#\" class=\"button is-danger is-rounded btn-subscribe subscribeBook\" ><i class=\"fa fa-heart\"></i> <span>Theo dõi</span></a>\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "                <div class=\"chaptercomment\">\n"
                + "                    <div class=\"book-comments\">\n"
                + "                        <div class=\"cm-relative\">\n"
                + "                            <i class=\"fa-solid fa-comments\"></i>Binh Luan(478)\n"
                + "                            <p>hãy donate để có nhiều truyện mới hơn nhé</p>\n"
                + "                        </div>\n"
                + "                        <div class=\"cm-main\">\n"
                + "                            <div class=\"yourcm\">\n"
                + "                                <textarea id=\"comment-input\" class=\"comment-input\" placeholder=\"Hãy bình luận văn minh không là ban acc\"></textarea>\n"
                + "                                <a onclick=\"comment('"+bid+"')\"><button type=\"button\" class=\"btn btn-success\" >Ðang</button></a>"
                + "                            </div>\n"
                + "                            <div class=\"othercm\" id=\"commentsajax\">");
        CommentsDAO coDAO = new CommentsDAO();
        ArrayList<Comments> colist = coDAO.getCommentByBookId(bid);
        for (Comments co : colist) {
            out.print("<div class=\"comment\">\n"
                    + "                                    <div class=\"avataruser\"><img src=\"" + co.getUserAvata() + "\" alt=\"useravata\" style=\"width: 60px; height: 60px; border-radius: 50%\"></div>\n"
                    + "                                    <div class=\"commentcontent\"><p class=\"username\">" + co.getUserName() + "</p><p>" + co.getComment() + "</p></div>\n"
                    + "                                </div>");

        }
        out.print("</div>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "            </div>");

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
