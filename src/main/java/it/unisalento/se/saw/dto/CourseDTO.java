package it.unisalento.se.saw.dto;

public class CourseDTO {

    private int id;
    private String name;
    private String type;
    private int lenght;
    private int credits;
    private String location;
    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLenght() {
        return lenght;
    }

    public int getCredits() {
        return credits;
    }

    public String getLocation() {
        return location;
    }

    public String getLanguage() {
        return language;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
