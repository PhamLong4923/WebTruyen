/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ChapterImg {
    private int contentId;
    private int chapterId;
    private String chapterImgSrc;
    private int imgPage;

    public ChapterImg() {
    }

    public ChapterImg(int contentId, int chapterId, String chapterImgSrc, int imgPage) {
        this.contentId = contentId;
        this.chapterId = chapterId;
        this.chapterImgSrc = chapterImgSrc;
        this.imgPage = imgPage;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public void setChapterImgSrc(String chapterImgSrc) {
        this.chapterImgSrc = chapterImgSrc;
    }

    public void setImgPage(int imgPage) {
        this.imgPage = imgPage;
    }

    public int getContentId() {
        return contentId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public String getChapterImgSrc() {
        return chapterImgSrc;
    }

    public int getImgPage() {
        return imgPage;
    }
    
    
}
