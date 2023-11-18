/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserAcc;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    public UserAcc accByUid(int uid) {
        UserAcc acc = new UserAcc();
        String sql = "SELECT * FROM UserAcc WHERE userId=" + uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                acc = new UserAcc(rs.getInt("userId"), rs.getString("userName"), rs.getString("userPass"), rs.getInt("userRole"), rs.getString("userAvataImgSrc"), rs.getString("userGmail"));
            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int checkUserName(String uname) {
        String sql = "SELECT * FROM UserAcc WHERE userName = '" + uname + "'";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int login(String uname, String upass) {
        String sql = "SELECT * FROM UserAcc WHERE userName = ? AND userPass = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, uname);
            pr.setString(2, upass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("userId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int signin(String uname, String upass) {
        String sql = "INSERT INTO UserAcc (userName, userPass, userRole)\n"
                + "VALUES (?, ?, ?);";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, uname);
            pr.setString(2, upass);
            pr.setInt(3, 1);
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int changepass(int uid, String newpass) {
        String sql = "UPDATE UserAcc\n"
                + "SET userPass = ?\n"
                + "WHERE userId = ?;";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, newpass);
            pr.setInt(2, uid);
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public String userNameByUid(int uid) {
        String sql = "SELECT * FROM UserAcc WHERE userId = " + uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString("userName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String userGmailByUid(int uid) {
        String sql = "SELECT * FROM UserAcc WHERE userId = " + uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString("userGmail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int changeRoleByUid(int uid) {
        String sql = "UPDATE UserAcc\n"
                + "SET userRole = 2\n"
                + "WHERE userId = "+uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);;
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int disinheritAuthor(int uid) {
        String sql = "UPDATE UserAcc\n"
                + "SET userRole = 3\n"
                + "WHERE userId = "+uid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);;
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
