package com.example.android.newsfeeds;

public class NewsModel {

    String title;

    public NewsModel(String url) {
        this.url = url;
    }



    public String getUrl() {
        return url;
    }

    String url;


    public String getUrlToImage() {
        return urlToImage;
    }

    String urlToImage;

    public String getTitle() {
        return title;
    }


    public NewsModel(String title,String urlToImage,String url){
        this.title=title;
        this.urlToImage=urlToImage;
        this.url = url;
    }
}
