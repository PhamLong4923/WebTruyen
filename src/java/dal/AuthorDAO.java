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
import model.Author;
import model.AuthorMg;
import model.Books;
import model.UserAcc;

/**
 *
 * @author Admin
 */
public class AuthorDAO extends DBContext {
    
    public AuthorMg getAuthorMgByUid(int uid) {
        String sql = "SELECT ua.userId, ua.userRole, ua.userName, ba.authorName, ua.userGmail FROM UserAcc ua JOIN beAuthor ba ON ua.userId = ba.userId\n"
                + "WHERE ua.userId = ?";

        AuthorMg amg;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, uid);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                amg = new AuthorMg(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
                return amg;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<AuthorMg> getAuthorMg() {
        String sql = "SELECT ua.userId, ua.userRole, ua.userName, ba.authorName, ua.userGmail FROM UserAcc ua JOIN beAuthor ba ON ua.userId = ba.userId\n"
                + "WHERE ba.isActive = 1 AND ua.userRole IN (2,3)";

        AuthorMg amg;
        ArrayList<AuthorMg> amglist = new ArrayList<>();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                amg = new AuthorMg(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
                amglist.add(amg);
            }
            return amglist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Author getAuthorByAuthorId(int aid) {
        String sql = "SELECT *\n"
                + "FROM beAuthor\n"
                + "WHERE  authorId = " + aid;

        Author a = new Author();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                a = new Author(rs.getInt("authorId"), rs.getInt("userid"), rs.getString("authorName"), rs.getString("introduction"), rs.getInt("isActive"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Author getAuthorByUserId(int uid) {
        String sql = "SELECT *\n"
                + "FROM beAuthor\n"
                + "WHERE  userId = " + uid;

        Author a = new Author();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                a = new Author(rs.getInt("authorId"), rs.getInt("userid"), rs.getString("authorName"), rs.getString("introduction"), rs.getInt("isActive"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int beAuthor(int uid, String intro, String email, String sign) {

        String sql = "INSERT INTO beAuthor (userId, authorName, introduction, isActive)\n"
                + "VALUES (?, ?, ?, 0);";
        if (isRequested(uid) == 1) {
            return 0;
        }
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, uid);
            pr.setString(2, sign);
            pr.setString(3, intro);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int isRequested(int uid) {
        String sql = "SELECT * FROM beAuthor WHERE userId = " + uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int updateEmail(String email, int uid) {
        String sql = "UPDATE UserAcc\n"
                + "SET userGmail = ?\n"
                + "WHERE userId = ?;";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, email);
            pr.setInt(2, uid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
}
