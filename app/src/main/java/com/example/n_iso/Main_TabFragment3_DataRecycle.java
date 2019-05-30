package com.example.n_iso;

public class Main_TabFragment3_DataRecycle {

    public String title;
    public String subtitle;
    public String image;
    public String category;
    public String synopsis;
    public String url;

    public Main_TabFragment3_DataRecycle(String title, String subtitle, String image, String category, String synopsis, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.category = category;
        this.synopsis = synopsis;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSynopsis() { return synopsis; }

    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }

    public String getUrl() { return  url; }

    public void setUrl(String url) { this.url = url; }
}
