/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.AuthorDAO;
import dal.BooksDAO;
import dal.beAuthorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.AuthorMg;
import model.Books;
import model.UserAcc;
import model.beAuthor;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DashBoardController", urlPatterns = {"/dashboard"})
public class DashBoardController extends HttpServlet {

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
            out.println("<title>Servlet DashBoardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoardController at " + request.getContextPath() + "</h1>");
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
        UserAcc sesionacc = (UserAcc) session.getAttribute("loginacc");
        int role = sesionacc.getUserRole();
        String email = "";
        if (sesionacc.getUserGmail() != null) {
            email = sesionacc.getUserGmail();
        }
        String urole = role == 0 ? "Admin" : role == 1 ? "Doc gia" : role == 2 ? "Tac gia" : "";

        out.print("<div class=\"dashboard row\">\n"
                + "                <div class=\"right-controll col-md-4\">\n"
                + "                    <div class=\"summaryinfo\"><a href=\"#\" onclick=\"summaryinfo()\">Thông tin chung</a></div>\n");
        if (role == 0) {
            out.print(
                    "                    <div class=\"authorasign\"><a href=\"#\" onclick=\"manage()\">Quản lí trang</a></div>\n");
        } else {
            out.print(
                    "                    <div class=\"authorasign\"><a href=\"#\" onclick=\"uploaded()\">Truyện đã đăng</a></div>\n");

        }
        out.print(
                "                    <div class=\"changeinfo\"><a href=\"#\" onclick=\"changeinfo()\">Thay đổi mật khẩu</a></div>\n"
                + "                </div>\n"
                + "                <div class=\"left-content col-md-8\">\n"
                + "                    <div class=\"left-inner-content summary\" style=\"display: block\">\n"
                + "                        <div class=\"avata\"><img src=\"" + sesionacc.getUserAvataImgSrc() + "\" width=\"85px\" height=\"85px\" alt=\"alt\"/><p>" + sesionacc.getUserName() + "</p></div>\n"
                + "                        <div class=\"otherinfo\">\n"
                + "                            <p style=\"margin-left: 10px;\">Email</p>\n"
                + "                            <textarea disabled=\"\" style=\"width: 300px; height: 30px; margin-left: 10px;\">" + email + "</textarea>\n"
                + "                            <p style=\"margin-left: 10px;\">Role</p>\n"
                + "                            <textarea disabled=\"\" style=\"width: 100px; height: 30px; margin-left: 10px;\">" + urole + "</textarea>\n"
                + "                        </div>\n"
                + "\n"
                + "                    </div>\n"
                + "\n");
        if (role == 0) {
            out.print("<div class=\"left-inner-content author\" style=\"display: none\">\n"
                    + "                        <div class=\"managerscreen\">\n"
                    + "                            <div class=\"managerbox\">\n"
                    + "                                <div class=\"box\"><a href=\"#\" onclick=\"request()\"><buttoon>Yêu cầu</buttoon></a></div>\n"
                    + "                                <div class=\"box\"><a href=\"#\" onclick=\"uploadedbook()\"><buttoon>Truyện đang đăng</buttoon></a></div>\n"
                    + "                                <div class=\"box\"><a href=\"#\" onclick=\"typemanager()\"><buttoon>The loai</buttoon></a></div>"
                    + "                                <div class=\"box\"><a href=\"#\" onclick=\"allauthor()\"><buttoon>Dịch giả</buttoon></a></div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"manageritem\">\n"
                    + "                                <div class=\"managerajax\" id=\"managerajax\" >\n"
                    + "                                    <div class=\"request\">\n"
                    + "                                        <div class=\"requestitem\">\n"
                    + "                                            <table class=\"table table-striped requesttable\">\n"
                    + "                                                <tr>\n"
                    + "                                                    <th>Tên tài khoản</th>\n"
                    + "                                                    <th>Giới thiệu</th>\n"
                    + "                                                    <th>Email</th>\n"
                    + "                                                    <th>Action</th>\n"
                    + "                                                </tr>");
            beAuthorDAO beaDAO = new beAuthorDAO();
            AccountDAO aDAO = new AccountDAO();
            ArrayList<beAuthor> bealist = beaDAO.getAllNonActiveReq();
            for (beAuthor bea : bealist) {
                out.print("<tr>\n"
                        + "                                                    <td class=\"break-word\" style=\"width: 20%\">" + aDAO.userNameByUid(bea.getUserId()) + "</td>\n"
                        + "                                                    <td class=\"break-word\" style=\"width: 40%\">" + bea.getIntroduction() + "</td>\n"
                        + "                                                    <td class=\"break-word\" style=\"width: 25%\">" + aDAO.userGmailByUid(bea.getUserId()) + "</td>\n"
                        + "                                                    <td style=\"width: 15%\"><p><a href=\"#\" onclick=\"accept('" + bea.getUserId() + "')\">Accept</a>/<a href=\"#\" onclick=\"denife('" + bea.getUserId() + "')\">Denife</a></p></td>\n"
                        + "                                                </tr>");

            }
            out.print("</table>\n"
                    + "</div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "\n"
                    + "                    </div>");

        } else if (role == 1) {
            out.print(
                    "<div class=\"left-inner-content author\" style=\"display: none\">\n"
                    + "                        <div class=\"intro-author\">\n"
                    + "                            <textarea style=\"width: 90%; height: 150px;\" placeholder=\"Giới thiệu về team dịch của bạn\" id=\"intro\"></textarea>\n"
                    + "                            <div class=\"updateinfo\" style=\"display: flex; flex-direction: column;\">\n"
                    + "                                <label>Email</label>\n"
                    + "                                <input type=\"text\" name=\"email\" id=\"email\">\n"
                    + "                                <label>Tên dịch giả (ký danh)</label>\n"
                    + "                                <input type=\"text\" name=\"authorsign\" id=\"authorsign\">\n"
                    + "                            </div>\n"
                    + "                            <button onclick=\"beAuthor()\">Đăng ký tác giả</button>\n"
                    + "                        </div>\n"
                    + "\n"
                    + "                    </div>");

        } else if (role == 2) {
            BooksDAO bDAO = new BooksDAO();
            AuthorDAO aDAO = new AuthorDAO();
            ArrayList<Books> blist = bDAO.loadBooksByAuthorId(aDAO.getAuthorByUserId(sesionacc.getUserId()).getAuthorId());
            AuthorMg amg = aDAO.getAuthorMgByUid(sesionacc.getUserId());
            out.print("<div class=\"left-inner-content author\" style=\"display: none\">\n"
                    + "                        <div class=\"managerscreen\">\n"
                    + "\n"
                    + "                            <div class=\"manageritem\">\n"
                    + "                                <div class=\"managerajax\">\n"
                    + "                                    <div class=\"authorinf\">\n"
                    + "                                        <p class=\"pseudonym\">Truyện của bạn</p>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"sign\">\n"
                    + "                                        <p>Tên đăng kí: "+amg.getAuthorName()+"</p>\n"
                    + "                                        <p>Tài khoản đăng ký: " + sesionacc.getUserGmail() + "</p>\n"
                    + "<form action=\"createbook\">\n"
                    + "                            <button type=\"submit\" value=\"\"><i class=\"fa-solid fa-square-plus\"></i></button>\n"
                    + "                        </form>"
                    + "                                    </div>\n"
                    + "                                    <div class=\"uploaded\">\n"
                    + "                                        <table border=\"1\" class=\"table table-striped\">\n"
                    + "                                            <tr>\n"
                    + "                                                <th>Bìa</th>\n"
                    + "                                                <th>Tên</th>\n"
                    + "                                                <th>Edit</th>\n"
                    + "                                            </tr>");
            for (Books b : blist) {
                out.print("<tr>\n"
                        + "                                                    <td><img src=\"" + b.getBookImg() + "\" alt=\"alt\" width=\"100px\" height=\"120px\"/></td>\n"
                        + "                                                    <td>" + b.getBookName() + "</td>\n"
                        + "                                                    <td><a href=\"edittor?bid=" + b.getBookId() + "\"><i class=\"fa-solid fa-pen\"></i></a><i class=\"fa-solid fa-grip-lines-vertical\"></i><a href=\"#\" onclick=\"deletebook('" + b.getBookId() + "')\"><i class=\"fa-solid fa-trash\"></i></a></td>\n"
                        + "                                                </tr>");

            }
            out.print("</table>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "\n"
                    + "                    </div>");

        } else {
            out.print(
                    "<div class=\"left-inner-content author\" style=\"display: none\">\n"
                    + "<div class=\"disinherit\" style=\"display: flex; flex-direction: column; margin: auto; width: 95%; height: 350px;justify-content: center; align-items: center\">\n"
                    + "                                                <i class=\"fa-solid fa-triangle-exclamation fa-2xl\" style=\"color: #f92424; font-size: 100px\"></i>\n"
                    + "                                                <p style=\"font-size: 17px; margin-top: 40px\">!Bạn đã bị hủy quyền đăng truyện!</p>\n"
                    + "                                                <p style=\"font-size: 12px\"><i class=\"fa-regular fa-hand-point-up fa-rotate-90\" style=\"color: #2c6ae8;\"></i>hãy liên hệ với admin để biết chi tiết</p>\n"
                    + "                                            </div>"
                    + "                    </div>");

        }
        out.print(
                "                    \n"
                + "                    <div class=\"left-inner-content changepass\" style=\"display: none\">\n"
                + "                        <div class=\"authorinf\">\n"
                + "                            <p class=\"pseudonym\">change your password</p>\n"
                + "                        </div>\n"
                + "\n"
                + "                        <div class=\"form-row\">\n"
                + "                            <label class=\"form-label\">Old pass:</label>\n"
                + "                            <input type=\"text\" name=\"oldpass\" id=\"oldpass\" class=\"form-input\">\n"
                + "                        </div>\n"
                + "\n"
                + "                        <div class=\"form-row\">\n"
                + "                            <label class=\"form-label\">New pass:</label>\n"
                + "                            <input type=\"text\" name=\"newpass\" id=\"newpass\" class=\"form-input\">\n"
                + "                        </div>\n"
                + "\n"
                + "                        <div class=\"form-row\">\n"
                + "                            <label class=\"form-label\">Confirm new pass:</label>\n"
                + "                            <input type=\"text\" name=\"cfnewpass\" id=\"cfnewpass\" class=\"form-input\">\n"
                + "                        </div>\n"
                + "                        <button onclick=\"changepass()\">Change</button>\n"
                + "\n"
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
