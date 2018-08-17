package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Course;
import it.unisalento.se.saw.domain.Student;
import it.unisalento.se.saw.dto.CourseDTO;
import it.unisalento.se.saw.dto.StudentDTO;

public class StudentDTOModel implements DTO<Student, StudentDTO> {
    @Override
    public StudentDTO create(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId().getIdStudent());
        studentDTO.setYearStart(student.getYearStart());
        studentDTO.setYear(student.getYear());
        studentDTO.setMatricola(student.getMatricola());
        studentDTO.setAge(student.getUser().getAge());
        studentDTO.setEmail(student.getUser().getEmail());
        studentDTO.setName(student.getUser().getName());
        studentDTO.setSurname(student.getUser().getSurname());
        studentDTO.setUid(student.getUser().getUid());
        studentDTO.setUserType(student.getUser().getUserType());
        studentDTO.setIdCourse(student.getCourse().getIdCourse());
        studentDTO.setIdUser(student.getId().getUserIdUser());
        studentDTO.setToken(student.getUser().getToken());


        DtoFactory dtoFactory = new DtoFactory();
        DTO<Course,CourseDTO> dto = dtoFactory.getDTO("COURSE");
        CourseDTO courseDTO = dto.create(student.getCourse());
        studentDTO.setCourseDTO(courseDTO);
        return studentDTO;
    }
}
