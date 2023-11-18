/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */

public class Author {
    private int authorId;
    private int userId;
    private String authorName;
    private String introduction;
    private int isActive;

    public Author() {
    }

    public Author(int authorId, int userId, String authorName, String introduction, int isActive) {
        this.authorId = authorId;
        this.userId = userId;
        this.authorName = authorName;
        this.introduction = introduction;
        this.isActive = isActive;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    
    

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    
    
}
