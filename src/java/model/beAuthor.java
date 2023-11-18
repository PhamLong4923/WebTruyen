/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class beAuthor {
    private int authorid;
    private int userId;
    private String authorName;
    private String introduction;
    private int isActive;

    public beAuthor() {
    }

    public beAuthor(int authorid, int userId, String authorName, String introduction, int isActive) {
        this.authorid = authorid;
        this.userId = userId;
        this.authorName = authorName;
        this.introduction = introduction;
        this.isActive = isActive;
    }

    public int getAuthorid() {
        return authorid;
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

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
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

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    
    
}
