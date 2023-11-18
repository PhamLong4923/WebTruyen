/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.*;

/**
 *
 * @author Admin
 */
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryTitle;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String categoryTitle) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
    
    
}
