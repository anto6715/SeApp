package it.unisalento.se.saw.dto;

import it.unisalento.se.saw.domain.Course;

import java.util.HashSet;
import java.util.Set;

public class ProfessorDTO extends UserDTO {

    private int course;
    private int id;
    private Set<Course> courses = new HashSet<Course>(0);

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

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
