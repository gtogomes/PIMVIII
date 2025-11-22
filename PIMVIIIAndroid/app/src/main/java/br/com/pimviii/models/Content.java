package br.com.pimviii.models;

public class Content {
    private int id;
    private String title;
    private String type;
    private String description;

    public Content(int id, String title, String type, String description) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public String getDescription() { return description; }
}
