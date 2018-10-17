package indi.aljet.mydanmudemo_master.model;

public class Danmu {
    public long id;
    public int userId;
    public String type;
    public int avatarUrl;
    public String content;

    public Danmu() {
    }

    public Danmu(long id, int userId, String type, int avatarUrl, String content) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.content = content;
    }
}
