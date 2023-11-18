/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author Admin
 */
@WebServlet(name = "TypeManager", urlPatterns = {"/categorymanager"})
public class TypeManager extends HttpServlet {

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
            out.println("<title>Servlet TypeManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TypeManager at " + request.getContextPath() + "</h1>");
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
        CategoryDAO cDAO = new CategoryDAO();
        ArrayList<Category> clist = cDAO.loadCategory();

        out.print("<div class=\"categorymanager\" style=\"width: 98%; height: auto; min-height: 600px; background-color: whitesmoke; margin: auto\">\n"
                + "                                        <div class=\"\" id=\"categoryajax\" style=\"width: 98%; display: grid; grid-template-columns: repeat(4, 1fr);gap: 5px; background-color: gainsboro; margin: auto; height:auto\">");
        for (Category c : clist) {
            out.print("<input type=\"radio\" name=\"managercheckbox\" value=\"" + c.getCategoryId() + "\" ><label for=\"radio\">" + c.getCategoryName() + "</label>");

        }
        out.print("</div>\n"
                + "                                        <div class=\"categoryedit\" style=\"padding: 15px; width: 98%; margin: auto; height: 170px; margin-top: 10px; background-color: gainsboro; display: flex; justify-content: space-around\">\n"
                + "                                            <div class=\"addnewC\" style=\"display: flex; flex-direction: column\">\n"
                + "                                                <input type=\"text\" name=\"cname\" id=\"cname\" placeholder=\"Tên thể loại mới\">\n"
                + "                                                <input type=\"text\" name=\"ctitile\" id=\"ctitile\" placeholder=\"Mô tả nội dung thể loại\">\n"
                + "                                                <a href=\"#\" onclick=\"addnewcategory()\"><i class=\"fa-solid fa-plus\" style=\"width: 190px; height: 29px; border: 1px #000 solid; display: flex; justify-content: center;align-items: center\"></i></a>\n"
                + "                                            </div>\n"
                + "                                            <div class=\"delC\">\n"
                + "                                                <a href=\"#\" onclick=\"removecategory()\"><i class=\"fa-solid fa-trash fa-lg\"></i></a>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                            <p style=\"word-break: break-word; width: 98%; background-color: gainsboro; height: 20px; margin-top: 10px; margin: auto; text-align: center\">Note:hãy tích vào thể loại bạn muốn để sửa hoặc xóa</p>\n"
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
