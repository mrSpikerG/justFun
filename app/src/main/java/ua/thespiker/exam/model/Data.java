package ua.thespiker.exam.model;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("describtion")
    private String description;
    @SerializedName("iconName")
    private String icon;


    public Data(String name, int id, String description, String type, String ico) {
        this.title = name;
        this.description = description;
        this.icon = ico;
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
