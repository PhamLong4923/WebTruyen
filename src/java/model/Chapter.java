/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Chapter {
    int chapterId;
    int bookId;
    int chapterNumber;
    String chapterName;
    String chapterDob;

    public Chapter() {
    }

    public Chapter(int chapterId,int bookId, int chapterNumber, String chapterName, String chapterDob) {
        this.chapterId = chapterId;
        this.bookId = bookId;
        this.chapterNumber = chapterNumber;
        this.chapterName = chapterName;
        this.chapterDob = chapterDob;
    }

    public int getChapterId() {
        return chapterId;
    }
        

    public int getBookId() {
        return bookId;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getChapterDob() {
        return chapterDob;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }
       

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setChapterDob(String chapterDob) {
        this.chapterDob = chapterDob;
    }
    
    
}
