package com.example.n_iso;

public class Main_TabFragment1_DataRecycle {

    public String category;
    public String title;
    public String subtitle;
    public String image;
    public String script;
    public String url;

    public Main_TabFragment1_DataRecycle(String category, String title, String subtitle, String image, String url) {
        this.category = category;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.script = script;
        this.url = url;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getImage() { return image; }

    public void setImage(String image) {
        this.image = image;
    }

    public String getScript() {
        return script;
    }

    public void getScript(String script) {
        this.script = script;
    }

    public String getUrl() { return  url; }

    public void setUrl(String url) { this.url = url; }
}