package com.example.android.newsfeeds;

public class Language_model {


    private String title;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public String getTitle() {
        return title;
    }

    public Language_model(String title, String code, int language_image) {
        this.title = title;
        this.code = code;
        this.language_image = language_image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLanguage_image() {
        return language_image;
    }

    public void setLanguage_image(int language_image) {
        this.language_image = language_image;
    }

    private int language_image;
}
