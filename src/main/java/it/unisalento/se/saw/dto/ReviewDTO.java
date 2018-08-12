package it.unisalento.se.saw.dto;

public class ReviewDTO {

    private int id;
    private String note;
    private int rate;
    private int idStudent;
    private int idLesson;
    private int idMaterial;
    private int idReviewType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getIdReviewType() {
        return idReviewType;
    }

    public void setIdReviewType(int idReviewType) {
        this.idReviewType = idReviewType;
    }
}
