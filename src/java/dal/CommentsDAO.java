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
import model.Chapter;
import model.Comments;

/**
 *
 * @author Admin
 */
public class CommentsDAO extends DBContext {

    public ArrayList<Comments> getCommentByBookId(int bid) {
        String sql = "SELECT TOP 5 co.*, us.userName, us.userAvataImgSrc\n"
                + "FROM Comments co JOIN UserAcc us\n"
                + "ON co.userId = us.userId\n"
                + "WHERE co.bookId = " + bid + "\n"
                + " ORDER BY co.commentDob DESC";
        ArrayList<Comments> colist = new ArrayList<>();
        Comments co = new Comments();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                co = new Comments(rs.getInt("commentId"), rs.getString("userAvataImgSrc"), rs.getString("userName"), rs.getString("comment"), rs.getInt("userId"), rs.getInt("chapterId"), rs.getInt("bookId"), rs.getString("commentDob"));
                colist.add(co);
            }
            return colist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Comments> getCommentByChapterId(int chid) {
        String sql = "SELECT TOP 5 co.*, us.userName, us.userAvataImgSrc\n"
                + "FROM Comments co JOIN UserAcc us\n"
                + "ON co.userId = us.userId\n"
                + "WHERE co.chapterId = " + chid + "\n"
                + " ORDER BY co.commentDob DESC";
        ArrayList<Comments> colist = new ArrayList<>();
        Comments co = new Comments();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                co = new Comments(rs.getInt("commentId"), rs.getString("userAvataImgSrc"), rs.getString("userName"), rs.getString("comment"), rs.getInt("userId"), rs.getInt("chapterId"), rs.getInt("bookId"), rs.getString("commentDob"));
                colist.add(co);
            }
            return colist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int postCommentBook(String cm, int uid, int bid) {
        String sql = "INSERT INTO Comments (comment, userId, chapterId, bookId, commentDob)\n"
                + "VALUES (?, ?, NULL, ?, GETDATE());";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, cm);
            pr.setInt(2, uid);
            pr.setInt(3, bid);
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int postCommentChapter(String cm,int chid, int uid, int bid) {
        String sql = "INSERT INTO Comments (comment, userId, chapterId, bookId, commentDob)\n"
                + "VALUES (?, ?, ?, ?, GETDATE());";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, cm);
            pr.setInt(2, uid);
            pr.setInt(3,chid);
            pr.setInt(4,bid);
            return pr.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
