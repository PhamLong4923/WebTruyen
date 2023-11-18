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
import model.ChapterImg;

/**
 *
 * @author Admin
 */
public class ChapterImgDAO extends DBContext {

    public ArrayList<ChapterImg> getAllImgByChapId(int chid) {
        String sql = "SELECT *\n"
                + "FROM ChapterImg\n"
                + "WHERE chapterId = ?\n"
                + "ORDER BY imgPage ASC;";

        ArrayList<ChapterImg> cilist = new ArrayList<>();
        ChapterImg ci = new ChapterImg();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, chid);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                ci = new ChapterImg(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                cilist.add(ci);
            }
            return cilist;
        } catch (SQLException ex) {
            Logger.getLogger(ChapterImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int countpage(int chid) {
        String sql = "SELECT *\n"
                + "FROM ChapterImg\n"
                + "WHERE chapterId = ?\n"
                + "ORDER BY imgPage ASC;";

//        ArrayList<ChapterImg> cilist = new ArrayList<>();
//        ChapterImg ci = new ChapterImg();
        try {
            int n = 0;
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, chid);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                n++;
            }
            return n;
//            while(rs.next()){
//                ci = new ChapterImg(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
//                cilist.add(ci);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(ChapterImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int appendChapterImg(int chid, String img) {
        String sql = "INSERT INTO ChapterImg (chapterId, chapterImgSrc, imgPage)\n"
                + "VALUES (?, ?, ?);";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, chid);
            pr.setString(2, img);
            pr.setInt(3, countpage(chid) + 1);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int deleteChapterImg(int contentid) {
        String sql = "DELETE FROM ChapterImg WHERE contentId = ?;";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, contentid);

            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

    public int replaceChapterImg(int contentid, String img) {
        String sql = "UPDATE ChapterImg\n"
                + "SET chapterImgSrc = ?\n"
                + "WHERE contentId = ?";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, img);
            pr.setInt(2, contentid);

            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChapterImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

}
