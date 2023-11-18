/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */

public class Books {
    private int bookId;
    private int authorId;
    private String bookName;
    private String bookImg;
    private int bookStatus;
    private int chapterNumbers;
    private int like;
    private int view;
    private int follows;

    public Books() {
    }

    public Books(int bookId, int authorId, String bookName, String bookImg, int bookStatus, int chapterNumbers, int like, int view, int follows) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.bookName = bookName;
        this.bookImg = bookImg;
        this.bookStatus = bookStatus;
        this.chapterNumbers = chapterNumbers;
        this.like = like;
        this.view = view;
        this.follows = follows;
        
    }

    public int getBookId() {
        return bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookImg() {
        return bookImg;
    }

    public int getBookStatus() {
        return bookStatus;
    }

    public int getChapterNumbers() {
        return chapterNumbers;
    }

    public int getLike() {
        return like;
    }

    public int getView() {
        return view;
    }

    public int getFollows() {
        return follows;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setChapterNumbers(int chapterNumbers) {
        this.chapterNumbers = chapterNumbers;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setView(int view) {
        this.view = view;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }
    
    
}
