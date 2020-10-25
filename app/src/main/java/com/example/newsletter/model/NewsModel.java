package com.example.newsletter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("news")
    @Expose
    private List<List<News>> news = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<List<News>> getNews() {
        return news;
    }

    public void setNews(List<List<News>> news) {
        this.news = news;
    }

    public class News {

        @SerializedName("headline")
        @Expose
        private String headline;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("desc")
        @Expose
        private String desc;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("link")
        @Expose
        private String link;

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

    }
}
