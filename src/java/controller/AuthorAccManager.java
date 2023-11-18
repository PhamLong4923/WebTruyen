/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AuthorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.AuthorMg;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AuthorAccManager", urlPatterns = {"/authoraccmg"})
public class AuthorAccManager extends HttpServlet {

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
            out.println("<title>Servlet AuthorAccManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthorAccManager at " + request.getContextPath() + "</h1>");
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
        AuthorDAO aDAO = new AuthorDAO();
        ArrayList<AuthorMg> amglist = aDAO.getAuthorMg();

        out.print("<div class=\"authormanager\" style=\"width: 98%; height: auto; min-height: 395px; margin: auto; background-color: white\">\n"
                + "                                        <div id=\"authortableajax\" style=\"width: 95%; height: auto; min-height: 395px; margin: auto; background-color: white; border: 1px #000 solid; overflow-y: scroll\">");
        for (AuthorMg amg : amglist) {
            out.print("<div class=\"authoritem\" style=\"width: 95%; height: 30px; border-bottom: 1px #000 solid; margin: auto; display: flex; justify-content: space-between\">\n"
                    + "                                                <p>" + amg.getUserName() + "</p>\n"
                    + "                                                <p>" + amg.getAuthorName() + "</p>\n"
                    + "                                                <p>" + amg.getUserGmail() + "</p>\n");
            if (amg.getUserRole() == 2) {
                out.print(
                        "                                                <a href=\"#\" onclick=\"disinherit('" + amg.getUserId() + "','"+amg.getUserRole()+"')\"><i class=\"fa-solid fa-triangle-exclamation\" style=\"color: #24f964;\"></i></a>\n"
                        + "                                                \n");
            }else{
                out.print(
                        "                                                <a href=\"#\" onclick=\"disinherit('" + amg.getUserId() + "','"+amg.getUserRole()+"')\"><i class=\"fa-solid fa-triangle-exclamation\" style=\"color: #f92424;\"></i></a>\n"
                        + "                                                \n");
            }

            out.print("                                            </div>");

        }
        out.print("</div>\n"
                + "                                    </div>");

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
