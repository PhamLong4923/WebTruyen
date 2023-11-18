/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Category> loadCategory() {
        String sql = "SELECT * FROM Category";
        ArrayList<Category> clist = new ArrayList<>();
        Category c;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                c = new Category(rs.getInt("categoryId"), rs.getString("categoryName"), rs.getString("categoryTitile"));
                clist.add(c);
            }
            return clist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getCategoryById(int cid) {
        String sql = "SELECT * FROM Category WHERE categoryId = " + cid;
        PreparedStatement pr;
        try {
            pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString("categoryName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getCategoryNameByBookId(int bid) {
        String sql = "SELECT c.categoryName "
                + "FROM (Books b JOIN BookCategory bc ON b.bookId = bc.bookId) "
                + "JOIN Category c ON c.categoryId = bc.categoryId "
                + "WHERE b.bookId = " + bid;
        PreparedStatement pr;
        String nc = "";
        try {
            pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                nc += rs.getString("categoryName") + "  ";
            }
            return nc;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int addNewCategory(String name, String title) {
        String sql = "INSERT INTO Category (categoryName, categoryTitile)\n"
                + "VALUES (?, ?);";
        PreparedStatement pr;
        try {
            pr = connection.prepareStatement(sql);
            pr.setString(1, name);
            pr.setString(2, title);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int RemoveBookCategory(int cid) {
        String sql = "DELETE FROM BookCategory WHERE categoryId = ?;";
        PreparedStatement pr;
        try {
            pr = connection.prepareStatement(sql);
            pr.setInt(1, cid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int RemoveCategory(int cid) {
        RemoveBookCategory(cid);
        String sql = "DELETE FROM Category WHERE categoryId = ?;";
        PreparedStatement pr;
        try {
            pr = connection.prepareStatement(sql);
            pr.setInt(1, cid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
