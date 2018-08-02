package it.unisalento.se.saw.dto;

public class ProfessorDTO extends UserDTO {

    private int course;

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
