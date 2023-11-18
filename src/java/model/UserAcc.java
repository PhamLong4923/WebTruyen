/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class UserAcc {
    private int userId;
    private String userName;
    private String userPass;
    private int userRole;
    private String userAvataImgSrc;
    private String userGmail;

    

    public UserAcc() {
    }

    public UserAcc(int userId, String userName, String userPass, int userRole, String userAvataImgSrc, String userGmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPass = userPass;
        this.userRole = userRole;
        this.userAvataImgSrc = userAvataImgSrc;
        this.userGmail =  userGmail;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public int getUserRole() {
        return userRole;
    }

    public String getUserAvataImgSrc() {
        return userAvataImgSrc;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public void setUserAvataImgSrc(String userAvataImgSrc) {
        this.userAvataImgSrc = userAvataImgSrc;
    }

    public String getUserGmail() {
        return userGmail;
    }

    public void setUserGmail(String userGmail) {
        this.userGmail = userGmail;
    }
    
    
    

    
    
}
