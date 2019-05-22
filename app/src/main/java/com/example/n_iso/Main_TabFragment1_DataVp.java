package com.example.n_iso;

public class Main_TabFragment1_DataVp {

    public String title;
    public String description;
    public String image;
    public String link;

    public Main_TabFragment1_DataVp(String title, String description, String link, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String category) {
        this.description = description;
    }

    public String getImage() { return image; }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}