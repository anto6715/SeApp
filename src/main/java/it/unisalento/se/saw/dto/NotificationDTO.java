package it.unisalento.se.saw.dto;

public class NotificationDTO {

    private String title;
    private String body;
    private String token_topic;
    private int idUser;
    private String data;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToken_topic() {
        return token_topic;
    }

    public void setToken_topic(String token_topic) {
        this.token_topic = token_topic;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
