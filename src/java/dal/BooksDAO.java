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
import model.Books;

/**
 *
 * @author Admin
 */
public class BooksDAO extends DBContext {

    public void removeBookByBid(int bid) {
        String[] sqls = {"DELETE FROM Comments WHERE bookId = ?;",
             "DELETE FROM ChapterImg WHERE chapterId IN (SELECT chapterId FROM Chapter WHERE bookId = ?);",
             "DELETE FROM Chapter WHERE bookId = ?;",
             "DELETE FROM followBook WHERE bookId = ?;",
             "DELETE FROM BookCategory WHERE bookId = ?;",
             "DELETE FROM Books WHERE bookId = ?;"};
        try {
            for (String sql : sqls) {
                PreparedStatement pr = connection.prepareStatement(sql);
                pr.setInt(1, bid);
                pr.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int createNewBook(int aid, String bname, String bimg, String sum) {
        String sql = "INSERT INTO Books (authorId, bookName, bookImg, bookStatus, chapterNumbers, likes, [view], follows, summaryContent)\n"
                + "VALUES (?, ?, ?, 1, 0, 0, 0, 0, ?)";

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, aid);
            pr.setString(2, bname);
            pr.setString(3, bimg);
            pr.setString(4, sum);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Books> loadBooks() {
        String sql = "SELECT TOP 36 b.*\n"
                + "FROM Books b\n"
                + "JOIN (\n"
                + "    SELECT bookId, MAX(chapterDob) AS maxChapterDob\n"
                + "    FROM Chapter\n"
                + "    GROUP BY bookId\n"
                + ") latestChapter ON b.bookId = latestChapter.bookId\n"
                + "JOIN Chapter c ON latestChapter.bookId = c.bookId AND latestChapter.maxChapterDob = c.chapterDob\n"
                + "ORDER BY latestChapter.maxChapterDob DESC;";
        ArrayList<Books> blist = new ArrayList<>();
        Books b;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Books> loadAllBooks() {
        String sql = "SELECT b.*\n"
                + "FROM Books b\n"
                + "JOIN (\n"
                + "    SELECT bookId, MAX(chapterDob) AS maxChapterDob\n"
                + "    FROM Chapter\n"
                + "    GROUP BY bookId\n"
                + ") latestChapter ON b.bookId = latestChapter.bookId\n"
                + "JOIN Chapter c ON latestChapter.bookId = c.bookId AND latestChapter.maxChapterDob = c.chapterDob\n"
                + "ORDER BY latestChapter.maxChapterDob DESC;";
        ArrayList<Books> blist = new ArrayList<>();
        Books b;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Books> loadBooksWithCondition(String type, int status, int numofchap, int sort) {
        String sql = "SELECT b.*\n"
                + "FROM Books b\n"
                + "INNER JOIN BookCategory bc ON b.bookId = bc.bookId\n"
                + "INNER JOIN Chapter c ON b.bookId = c.bookId\n"
                + "WHERE 0=0 ";

        if (!type.equals("")) {
            type = "(" + type + ")";
            sql += "AND bc.categoryId IN " + type;

        }

        if (status != 2) {
            sql += " AND b.bookStatus = " + status;
        }
        sql += " GROUP BY b.bookId, b.authorId, b.bookName, b.bookImg, b.bookStatus, b.chapterNumbers, b.likes, b.[view], b.follows, b.summaryContent\n"
                + "HAVING COUNT(c.chapterId) >= " + numofchap;
        switch (sort) {
            case 0:
                sql += " ORDER BY MAX(c.chapterDob) DESC;";
                break;
            case 1:
                sql += " ORDER BY MAX(c.chapterDob) ASC;";
                break;
            case 2:
                sql += " ORDER BY b.chapterNumbers DESC;";
                break;
            case 3:
                sql += " ORDER BY b.chapterNumbers ASC;";
                break;
            case 4:
                sql += " ORDER BY b.likes DESC;";
                break;
            default:
                throw new AssertionError();
        }
        System.out.println(sql);
        ArrayList<Books> blist = new ArrayList<>();
        Books b;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Books> loadBooksByCategoryId(int cid) {
        String sql = "SELECT TOP 36 b.*\n"
                + "FROM Books b\n"
                + "JOIN (\n"
                + "    SELECT bookId, MAX(chapterDob) AS maxChapterDob\n"
                + "    FROM Chapter\n"
                + "    GROUP BY bookId\n"
                + ") latestChapter ON b.bookId = latestChapter.bookId\n"
                + "JOIN Chapter c ON latestChapter.bookId = c.bookId AND latestChapter.maxChapterDob = c.chapterDob\n"
                + "JOIN BookCategory bc ON latestChapter.bookId = bc.bookId\n"
                + "WHERE bc.categoryId = " + cid + "\n"
                + "ORDER BY latestChapter.maxChapterDob DESC;";
        ArrayList<Books> blist = new ArrayList<>();
        Books b;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Books> loadBooksByName(String name) {
        String sql = "SELECT TOP 36 * FROM Books WHERE bookName LIKE '%' + ? + '%'";

        ArrayList<Books> blist = new ArrayList<>();
        Books b;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, name.trim());
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Books> quickSearch(String text) {
        String sql = "SELECT b.*\n"
                + "FROM Books b\n"
                + "JOIN beAuthor ba ON b.authorId = ba.authorId\n"
                + "WHERE b.bookName LIKE '%' + ? + '%' \n"
                + "  OR ba.authorName LIKE '%' + ? + '%';";
        if (text.trim().equals("")) {
            sql = "SELECT b.*\n"
                    + "FROM Books b\n"
                    + "JOIN (\n"
                    + "    SELECT bookId, MAX(chapterDob) AS maxChapterDob\n"
                    + "    FROM Chapter\n"
                    + "    GROUP BY bookId\n"
                    + ") latestChapter ON b.bookId = latestChapter.bookId\n"
                    + "JOIN Chapter c ON latestChapter.bookId = c.bookId AND latestChapter.maxChapterDob = c.chapterDob\n"
                    + "ORDER BY latestChapter.maxChapterDob DESC;";
        }

        ArrayList<Books> blist = new ArrayList<>();
        Books b;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            if (!text.trim().equals("")) {
                pr.setString(1, text.trim());
                pr.setString(2, text.trim());
            }
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Books loadBooksByBid(int bid) {
        String sql = "SELECT *\n"
                + "FROM Books\n"
                + "WHERE bookId = " + bid;
        Books b = new Books();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int changeBookName(int bid, String name) {
        String sql = "UPDATE Books\n"
                + "SET bookName = ?\n"
                + "WHERE bookId = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(2, bid);
            pr.setString(1, name);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int takeBookControll(int bid) {
        String sql = "UPDATE Books\n"
                + "SET authorId = 2\n"
                + "WHERE bookId = " + bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int changeBookStatus(int bid, int status) {
        String sql = "UPDATE Books\n"
                + "SET bookStatus = ?\n"
                + "WHERE bookId = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(2, bid);
            pr.setInt(1, status);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int changeBookSummaryContent(int bid, String summary) {
        String sql = "UPDATE Books\n"
                + "SET summaryContent = ?\n"
                + "WHERE bookId = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(2, bid);
            pr.setString(1, summary);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int changeBookAvata(int bid, String avata) {
        String sql = "UPDATE Books\n"
                + "SET bookImg = ?\n"
                + "WHERE bookId = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(2, bid);
            pr.setString(1, avata);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int deleteBookCategory(int bid) {
        String sql = "DELETE FROM BookCategory WHERE bookId = " + bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int removeBook(int bid) {
        String sql = "DELETE FROM Books WHERE bookId = " + bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int changeBookCategory(int bid, int category) {

        String sql = "INSERT INTO BookCategory (bookId, categoryId)\n"
                + "VALUES (?, ?)";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, bid);
            pr.setInt(2, category);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Books> loadBooksByAuthorId(int aid) {
        String sql = "SELECT *\n"
                + "FROM Books\n"
                + "WHERE authorId = " + aid;
        Books b = new Books();
        ArrayList<Books> blist = new ArrayList<>();
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getSummaryContent(int bid) {
        String sql = "SELECT summaryContent\n"
                + "FROM Books\n"
                + "WHERE bookId = " + bid;

        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString("summaryContent");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int likeBookBid(int bid) {
        String sql = "UPDATE Books\n"
                + "SET likes = likes + 1\n"
                + "WHERE bookId = " + bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int followBookBid(int bid, int uid) {
        String sql = "INSERT INTO followBook (userId, bookId)\n"
                + "VALUES (?, ?)";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, uid);
            pr.setInt(2, bid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int unfollowBookBid(int bid, int uid) {
        String sql = "DELETE FROM followBook WHERE userId = ? AND bookId = ?;";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, uid);
            pr.setInt(2, bid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean isfollowed(int bid, int uid) {
        String sql = "SELECT * FROM followBook WHERE userId = ? AND bookId = ?;";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, uid);
            pr.setInt(2, bid);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Books> myFollowBooks(int uid) {
        String sql = "SELECT TOP 36 b.*\n"
                + "FROM Books b JOIN followBook fb ON b.bookId = fb.bookId\n"
                + "WHERE fb.userId = " + uid;
        ArrayList<Books> blist = new ArrayList<>();
        Books b;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("bookName"));
                b = new Books(rs.getInt("bookId"), rs.getInt("authorId"), rs.getString("bookName"), rs.getString("bookImg"), rs.getInt("bookStatus"), rs.getInt("chapterNumbers"), rs.getInt("likes"), rs.getInt("view"), rs.getInt("follows"));
                blist.add(b);
            }
            return blist;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int upview(int bid) {
        String sql = "UPDATE Books\n"
                + "SET [view] = [view] + 1\n"
                + "WHERE bookId = ?;";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, bid);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int upfollow(int bid) {
        String sql = "UPDATE Books\n"
                + "SET follows = follows + 1\n"
                + "WHERE bookId = " + bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int downfollow(int bid) {
        String sql = "UPDATE Books\n"
                + "SET follows = follows - 1\n"
                + "WHERE bookId = " + bid;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            return pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BooksDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
