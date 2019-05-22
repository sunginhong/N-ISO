package com.example.n_iso;

public class Main_TabFragment1_DataRecycle {

    public String category;
    public String title;
    public String image;
    public String script;

    public Main_TabFragment1_DataRecycle(String category, String title, String image, String script) {
        this.category = category;
        this.title = title;
        this.image = image;
        this.script = script;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getScript() {
        return script;
    }

    public void getScript(String script) {
        this.script = script;
    }
}