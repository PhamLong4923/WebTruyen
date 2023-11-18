/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class AuthorMg {
    private int userId;
    private int userRole;
    private String userName;
    private String authorName;
    private String userGmail;

    public AuthorMg() {
    }

    public AuthorMg(int userId, int userRole, String userName, String authorName, String userGmail) {
        this.userId = userId;
        this.userRole = userRole;
        this.userName = userName;
        this.authorName = authorName;
        this.userGmail = userGmail;
    }

    public int getUserId() {
        return userId;
    }

    public int getUserRole() {
        return userRole;
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getUserGmail() {
        return userGmail;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setUserGmail(String userGmail) {
        this.userGmail = userGmail;
    }
    
    
}
