/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Comments {
    private int commentId;
    private String userAvata;
    private String userName;
    private String comment;
    private int userId;
    private int chapterId;
    private int bookId;
    private String commentDob;

    public Comments() {
    }

    public Comments(int commentId, String userAvata, String userName, String comment, int userId, int chapterId, int bookId, String commentDob) {
        this.commentId = commentId;
        this.userAvata = userAvata;
        this.userName = userName;
        this.comment = comment;
        this.userId = userId;
        this.chapterId = chapterId;
        this.bookId = bookId;
        this.commentDob = commentDob;
    }

    

    

    public int getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public int getUserId() {
        return userId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getUserAvata() {
        return userAvata;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommentDob() {
        return commentDob;
    }
    
    
    
    

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setUserAvata(String userAvata) {
        this.userAvata = userAvata;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCommentDob(String commentDob) {
        this.commentDob = commentDob;
    }
    
    
    
}
