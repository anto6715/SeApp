package it.unisalento.se.saw.dto;

import it.unisalento.se.saw.domain.Course;

import java.util.HashSet;
import java.util.Set;

public class ProfessorDTO extends UserDTO {

    private int course;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
