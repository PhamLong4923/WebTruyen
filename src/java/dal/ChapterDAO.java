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
import model.Chapter;

/**
 *
 * @author Admin
 */
public class ChapterDAO extends DBContext {

    public ArrayList<Chapter> getChaplistByBookId(int bid) {
        String sql = "SELECT *\n"
                + "FROM Chapter\n"
                + "WHERE bookId = " + bid + "\n"
                + "ORDER BY chapterNumber DESC;";
        ArrayList<Chapter> chlist = new ArrayList<>();
        Chapter ch = new Chapter();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                ch = new Chapter(rs.getInt("chapterId"), rs.getInt("bookId"), rs.getInt("chapterNumber"), rs.getString("chapterName"), rs.getString("chapterDob"));
                chlist.add(ch);
            }
            return chlist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getBidByChid(int chid) {
        String sql = "SELECT c.bookId\n"
                + "FROM Chapter c\n"
                + "WHERE chapterId = " + chid;

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("bookId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public String getChapNameByChid(int chid) {
        String sql = "SELECT c.chapterNumber, c.chapterName\n"
                + "FROM Chapter c\n"
                + "WHERE chapterId = " + chid;

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return "Chapter " + rs.getString(1) + ":" + rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String getTitleByChid(int chid) {
        String sql = "SELECT b.bookName, c.chapterNumber, c.chapterName\n"
                + "FROM Books b\n"
                + "JOIN Chapter c ON b.bookId = c.bookId\n"
                + "WHERE c.chapterId = " + chid;

        String title = "";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                title += rs.getString("bookName") + " - Chapter " + rs.getString("chapterNumber") + " " + rs.getString("chapterName");
            }
            return title;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int changeChapterNameByChid(int chid, String newcontent) {
        String sql = "UPDATE Chapter\n"
                + "SET chapterName = ?\n"
                + "WHERE chapterId = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, newcontent);
            pr.setInt(2, chid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

    public int triggerAddChapter(int bid) {
        String sql = "UPDATE Books\n"
                + "SET chapterNumbers = chapterNumbers + 1\n"
                + "WHERE bookId = "+bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);

            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int triggerDelChapter(int bid) {
        String sql = "UPDATE Books\n"
                + "SET chapterNumbers = chapterNumbers - 1\n"
                + "WHERE bookId = "+bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);

            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int appendChapter(int bid) {
        String sql = "INSERT INTO Chapter (bookId, chapterNumber, chapterName, chapterDob)\n"
                + "VALUES (?, ?, '', GETDATE())";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, bid);
            pr.setInt(2, getSumChapByBookId(bid) + 1);
            triggerAddChapter(bid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int deleteChapter(int chid) {
        String sql = "DELETE FROM ChapterImg\n"
                + "WHERE chapterId = " + chid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.executeUpdate();
            sql = "DELETE FROM Chapter\n"
                    + "WHERE chapterId = " + chid;
            pr = connection.prepareStatement(sql);
            triggerDelChapter(getBidByChid(chid));
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getSumChapByBookId(int bid) {
        String sql = "SELECT *\n"
                + "FROM Chapter\n"
                + "WHERE bookId = " + bid + "\n"
                + "ORDER BY chapterNumber DESC;";
        int sum = 0;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                sum++;
            }
            return sum;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
