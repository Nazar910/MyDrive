package app.models;

public class Image {

    private final long id;
    private final String name;
    private final String content;

    public Image(long id, String content, String name) {
        this.id = id;
        this.content = content;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return this.name;
    }
}