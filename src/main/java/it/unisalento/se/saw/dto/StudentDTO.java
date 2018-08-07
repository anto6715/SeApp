package it.unisalento.se.saw.dto;

public class StudentDTO  extends UserDTO{

    private String matricola;
    private int year;
    private int yearStart;
    private int idCourse;
    private CourseDTO courseDTO;

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public String getMatricola() {
        return matricola;
    }

    public int getYear() {
        return year;
    }

    public int getYearStart() {
        return yearStart;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setYearStart(int yearStart) {
        this.yearStart = yearStart;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
}
