/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AuthorDAO;
import dal.BooksDAO;
import dal.CategoryDAO;
import dal.ChapterDAO;
import dal.CommentsDAO;
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
import model.Chapter;
import model.Comments;
import model.UserAcc;

/**
 *
 * @author Admin
 */
@WebServlet(name = "BookSelectController", urlPatterns = {"/read"})
public class BookSelectController extends HttpServlet {

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
            out.println("<title>Servlet BookSelectController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookSelectController at " + request.getContextPath() + "</h1>");
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
        AuthorDAO aDAO = new AuthorDAO();
        ChapterDAO chDAO = new ChapterDAO();
        int bid = Integer.parseInt(request.getParameter("bid"));

        Books b = bDAO.loadBooksByBid(bid);
        String bc = cDAO.getCategoryNameByBookId(bid);
        String authorName = aDAO.getAuthorByAuthorId(b.getAuthorId()).getAuthorName();
        PrintWriter out = response.getWriter();
        String status;
        String bname;
        if (b.getBookStatus() == 1) {
            status = "Dang cap nhat";
        } else {
            status = "Hoan thanh";
        }
        ArrayList<Chapter> chlist = chDAO.getChaplistByBookId(bid);
        String summaryContent = bDAO.getSummaryContent(bid);
        //take comment
        CommentsDAO coDAO = new CommentsDAO();
        ArrayList<Comments> colist = coDAO.getCommentByBookId(bid);

        HttpSession session = request.getSession();

        out.print("<div class=\"bookInf\">\n"
                + "                        <div class=\"book-img-contianer\">\n"
                + "                            <div class=\"book-img\"><img src=" + b.getBookImg() + " alt=\"alt\"/></div>\n"
                + "                        </div>\n"
                + "                        <div class=\"book-inf-container\">\n"
                + "                            <h1>" + b.getBookName() + "</h1>\n"
                + "                            <div class=\"book-inf\">\n"
                + "                                <p><i class=\"fa-solid fa-user\" style=\"color: #01020e;\"></i>Tac gia:" + authorName + "</p>\n"
                + "\n"
                + "                                <p><i class=\"fa-sharp fa-solid fa-wifi fa-rotate-90\"></i>Tinh trang:" + status + "</p>\n"
                + "                                <p><i class=\"fa-solid fa-thumbs-up\"></i>Luot thich:" + b.getLike() + "</p>\n"
                + "\n"
                + "                                <p><i class=\"fa-solid fa-heart\"></i>Luot theo doi:" + b.getFollows() + "</p>\n"
                + "                                <p><i class=\"fa-solid fa-eye\"></i>Luot xem:" + b.getView() + "</p>\n"
                + "                                <p><i class=\"fa-solid fa-pen\"></i>The loai:" + bc + "</p>\n"
                + "                                <div class=\"book-action\">\n"
                + "                                    <a href=\"#\" onclick=\"readchapter('" + chlist.get(chlist.size() - 1).getChapterId() + "')\"><button class=\"action-button\" style=\"background-color: greenyellow\"><i class=\"fa-solid fa-book\"></i> Ðoc tu dau</button></a>\n");
        if (session.getAttribute("loginacc") != null) {
            UserAcc uacc = (UserAcc) session.getAttribute("loginacc");
            if (bDAO.isfollowed(bid, uacc.getUserId())) {
                out.print("<a id=\"followajax\" onclick=\"follow('" + bid + "')\"><button class=\"action-button\" style=\"background-color: #e24040\"><i class=\"fa-sharp fa-solid fa-heart-circle-minus\"></i></i>Bo Theo doi</button></a>\n");
            } else {
                out.print("<a id=\"followajax\" onclick=\"follow('" + bid + "')\"><button class=\"action-button\" style=\"background-color: #e24040\"><i class=\"fa-solid fa-heart\"></i> Theo doi</button></a>\n");
            }

        } else {
            out.print("<a onclick=\"notification()\"><button class=\"action-button\" style=\"background-color: #e24040\"><i class=\"fa-solid fa-heart\"></i> Theo doi</button></a>\n");

        }

        out.print("<a onclick=\"like('" + bid + "')\"><button class=\"action-button\" style=\"background-color: #cd23ae\"><i class=\"fa-solid fa-thumbs-up\"></i> Thich</button></a>\n");

        out.print(
                "                                </div>\n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <div class=\"introduction\">\n"
                + "                        <div class=\"intro-titile\">                            \n"
                + "                            <i class=\"fa-sharp fa-solid fa-circle-info\" style=\"color: #f0b128;\"></i>Gioi thieu\n"
                + "                        </div>\n"
                + "                        <div class=\"intro\">\n"
                + "                            <p>" + summaryContent + "</p>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <div class=\"bookChapter\">\n"
                + "                        <div class=\"scrollable-content\">\n");
        Cookie[] cook = request.getCookies();
        String hchid = "";
        for (Cookie c : cook) {
            if (c.getName().equals("hchid")) {
                hchid += c.getValue();
                break;
            }
        }
        for (Chapter ch : chlist) {
            out.print("<div class=\"inner-component\">\n");
            if (hchid.contains(":" + ch.getChapterId() + ":")) {
                out.print("                                <p class=\"chapter-name\"><a style=\"color: gainsboro\"  href=\"#\" onclick=\"readchapter('" + ch.getChapterId() + "')\">Chap " + ch.getChapterNumber() + " " + ch.getChapterName() + "</a></p>\n");
            } else {
                out.print("                                <p class=\"chapter-name\"><a  href=\"#\" onclick=\"readchapter('" + ch.getChapterId() + "')\">Chap " + ch.getChapterNumber() + " " + ch.getChapterName() + "</a></p>\n");
            }
            out.print("                                <p class=\"chapter-time\">" + ch.getChapterDob() + "</p>\n"
                    + "                            </div>");

        }
        out.print("</div>\n"
                + "\n"
                + "\n"
                + "                    </div>\n"
                + "                    <div class=\"book-comments\">\n"
                + "                        <div class=\"cm-relative\">\n"
                + "                            <i class=\"fa-solid fa-comments\"></i>Binh Luan(478)\n"
                + "                            <p>Hay donate de co nhieu truyen moi hon nhe</p>\n"
                + "                        </div>\n"
                + "                        <div class=\"cm-main\">\n"
                + "                            <div class=\"yourcm\">\n"
                + "                                <textarea id=\"comment-input\" class=\"comment-input\" placeholder=\"hay binh luan van minh khong la ban acc\"></textarea>\n"
                + "                                <a onclick=\"comment('" + bid + "')\"><button type=\"button\" class=\"btn btn-success\" >Đăng</button></a>"
                + "                            </div>\n"
                + "                            <div class=\"othercm\" id=\"commentsajax\">\n");
        for (Comments co : colist) {
            out.print("<div class=\"comment\">\n"
                    + "                                    <div class=\"avataruser\"><img src=\"" + co.getUserAvata() + "\" alt=\"useravata\" style=\"width: 60px; height: 60px; border-radius: 50%\"></div>\n"
                    + "                                    <div class=\"commentcontent\"><p class=\"username\">" + co.getUserName() + "</p><p>" + co.getComment() + "</p></div>\n"
                    + "                                </div>");

        }
        out.print("</div>\n"
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
