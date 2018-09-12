package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.MaterialDTO;
import it.unisalento.se.saw.exceptions.LessonNotFoundException;
import it.unisalento.se.saw.exceptions.MaterialNotFoundException;
import it.unisalento.se.saw.repositories.MaterialRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MaterialServiceTest {

    @Mock
    MaterialRepository materialRepository;

    @Mock
    LessonService lessonService;

    @InjectMocks
    MaterialService materialService;

    @Test
    public void getAllTest() {
        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(1);
        lessonId.setTeachingCourseIdCourse(1);
        lessonId.setTeachingProfessorIdProfessor(1);
        lessonId.setTeachingCourseIdCourse(3);
        lessonId.setIdLesson(1);
        lessonId.setTeachingProfessorUserIdUser(1);

        Lesson lesson = new Lesson();
        lesson.setRoom(room);
        lesson.setId(lessonId);
        lesson.setTeaching(teaching);
        lesson.setDate(new Date());
        /******************************************************/

        /***********************Material*******************************/
        MaterialId materialId = new MaterialId();
        materialId.setLessonTeachingProfessorUserIdUser(1);
        materialId.setLessonTeachingProfessorIdProfessor(1);
        materialId.setLessonTeachingIdTeaching(1);
        materialId.setLessonTeachingCourseIdCourse(3);
        materialId.setLessonRoomIdRoom(1);
        materialId.setIdMaterial(1);

        Material material = new Material();
        material.setLink("link");
        material.setName("name");
        material.setLesson(lesson);
        material.setId(materialId);
        /******************************************************/

        List<Material> materials = new ArrayList<>();
        materials.add(material);

        when(materialRepository.findAll()).thenReturn(materials);

        List<MaterialDTO> materialDTOS = materialService.getAll();
        assertEquals(material.getLink(), materialDTOS.iterator().next().getLink());
    }

    @Test
    public void getByIdLessonTest() {
        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(1);
        lessonId.setTeachingCourseIdCourse(1);
        lessonId.setTeachingProfessorIdProfessor(1);
        lessonId.setTeachingCourseIdCourse(3);
        lessonId.setIdLesson(1);
        lessonId.setTeachingProfessorUserIdUser(1);

        Lesson lesson = new Lesson();
        lesson.setRoom(room);
        lesson.setId(lessonId);
        lesson.setTeaching(teaching);
        lesson.setDate(new Date());
        /******************************************************/

        /***********************Material*******************************/
        MaterialId materialId = new MaterialId(1,1,1,3,1,1,1);

        Material material = new Material(materialId,lesson);
        material.setLink("link");
        material.setName("name");
        /******************************************************/

        List<Material> materials = new ArrayList<>();
        materials.add(material);

        when(materialRepository.findMaterialsById_LessonIdLesson(1)).thenReturn(materials);

        List<MaterialDTO> materialDTOS = materialService.getByIdLesson(1);
        assertEquals(material.getName(), materialDTOS.iterator().next().getName());
    }

    @Test
    public void getByIdTeachingTest() {
        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(1);
        lessonId.setTeachingCourseIdCourse(1);
        lessonId.setTeachingProfessorIdProfessor(1);
        lessonId.setTeachingCourseIdCourse(3);
        lessonId.setIdLesson(1);
        lessonId.setTeachingProfessorUserIdUser(1);

        Lesson lesson = new Lesson();
        lesson.setRoom(room);
        lesson.setId(lessonId);
        lesson.setTeaching(teaching);
        lesson.setDate(new Date());
        /******************************************************/

        /***********************Material*******************************/
        MaterialId materialId = new MaterialId(1,1,1,3,1,1,1);

        Material material = new Material(materialId,lesson,"link","name",null);
        /******************************************************/

        List<Material> materials = new ArrayList<>();
        materials.add(material);

        when(materialRepository.findMaterialsById_LessonTeachingIdTeaching(1)).thenReturn(materials);

        List<MaterialDTO> materialDTOS = materialService.getByIdTeaching(1);
        assertEquals(material.getId().getLessonIdLesson(), materialDTOS.iterator().next().getIdLesson());
    }

    @Test
    public void getByIdTest() throws MaterialNotFoundException {
        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(1);
        lessonId.setTeachingCourseIdCourse(1);
        lessonId.setTeachingProfessorIdProfessor(1);
        lessonId.setTeachingCourseIdCourse(3);
        lessonId.setIdLesson(1);
        lessonId.setTeachingProfessorUserIdUser(1);

        Lesson lesson = new Lesson();
        lesson.setRoom(room);
        lesson.setId(lessonId);
        lesson.setTeaching(teaching);
        lesson.setDate(new Date());
        /******************************************************/

        /***********************Material*******************************/
        MaterialId materialId = new MaterialId();
        materialId.setLessonTeachingProfessorUserIdUser(1);
        materialId.setLessonTeachingProfessorIdProfessor(1);
        materialId.setLessonTeachingIdTeaching(1);
        materialId.setLessonTeachingCourseIdCourse(3);
        materialId.setLessonRoomIdRoom(1);
        materialId.setIdMaterial(1);

        Material material = new Material();
        material.setLink("link");
        material.setName("name");
        material.setLesson(lesson);
        material.setId(materialId);
        /******************************************************/

        when(materialRepository.findMaterialById_IdMaterial(1)).thenReturn(material);

        MaterialDTO materialDTO = materialService.getById(1);
        assertEquals(material.getId().getLessonTeachingProfessorUserIdUser(),materialDTO.getIdUserProf());
    }

    @Test
    public void getByIdErrorTest() throws MaterialNotFoundException {
        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(1);
        lessonId.setTeachingCourseIdCourse(1);
        lessonId.setTeachingProfessorIdProfessor(1);
        lessonId.setTeachingCourseIdCourse(3);
        lessonId.setIdLesson(1);
        lessonId.setTeachingProfessorUserIdUser(1);

        Lesson lesson = new Lesson();
        lesson.setRoom(room);
        lesson.setId(lessonId);
        lesson.setTeaching(teaching);
        lesson.setDate(new Date());
        /******************************************************/

        /***********************Material*******************************/
        MaterialId materialId = new MaterialId();
        materialId.setLessonTeachingProfessorUserIdUser(1);
        materialId.setLessonTeachingProfessorIdProfessor(1);
        materialId.setLessonTeachingIdTeaching(1);
        materialId.setLessonTeachingCourseIdCourse(3);
        materialId.setLessonRoomIdRoom(1);
        materialId.setIdMaterial(1);

        Material material = new Material();
        material.setLink("link");
        material.setName("name");
        material.setLesson(lesson);
        material.setId(materialId);
        /******************************************************/

        when(materialRepository.findMaterialById_IdMaterial(1)).thenReturn(material);

        try{
            MaterialDTO materialDTO = materialService.getById(2);
            assertEquals(material.getLink(),materialDTO.getLink());
        } catch (MaterialNotFoundException e) {
            assertEquals("Material not found", e.getMessage());
        }

    }

    @Test
    public void getDomainByIdTest() throws MaterialNotFoundException {
        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(1);
        lessonId.setTeachingCourseIdCourse(1);
        lessonId.setTeachingProfessorIdProfessor(1);
        lessonId.setTeachingCourseIdCourse(3);
        lessonId.setIdLesson(1);
        lessonId.setTeachingProfessorUserIdUser(1);

        Lesson lesson = new Lesson();
        lesson.setRoom(room);
        lesson.setId(lessonId);
        lesson.setTeaching(teaching);
        lesson.setDate(new Date());
        /******************************************************/

        /***********************Material*******************************/
        MaterialId materialId = new MaterialId();
        materialId.setLessonTeachingProfessorUserIdUser(1);
        materialId.setLessonTeachingProfessorIdProfessor(1);
        materialId.setLessonTeachingIdTeaching(1);
        materialId.setLessonTeachingCourseIdCourse(3);
        materialId.setLessonRoomIdRoom(1);
        materialId.setIdMaterial(1);

        Material material = new Material();
        material.setLink("link");
        material.setName("name");
        material.setLesson(lesson);
        material.setId(materialId);
        /******************************************************/

        when(materialRepository.findMaterialById_IdMaterial(1)).thenReturn(material);

        Material m = materialService.getDomainById(1);
        assertEquals(material,m);
    }

    @Test
    public void saveTest() throws MaterialNotFoundException, LessonNotFoundException {
        /***************************Course***************************/
        Course course = new Course();
        course.setCredits(1);
        course.setLanguage("language");
        course.setLenght(2);
        course.setLocation("location");
        course.setName("name");
        course.setType("type");
        course.setIdCourse(3);
        /******************************************************/

        /*****************User*************************************/
        User user = new User();
        user.setIdUser(1);
        user.setUserType(1);
        user.setToken("token");
        user.setUid("uid");
        user.setAge(2);
        user.setEmail("email");
        /******************************************************/
        /****************************Room**************************/
        Room room = new Room();
        room.setIdRoom(1);
        room.setCapacity(1);
        room.setLatitude(1.0);
        room.setLongitude(0.1);
        room.setLocation("location");
        room.setName("name");
        /******************************************************/

        /*************************Professor*****************************/

        ProfessorId professorId = new ProfessorId();
        professorId.setUserIdUser(1);
        professorId.setIdProfessor(1);

        Professor professor = new Professor();
        professor.setUser(user);
        professor.setId(professorId);

        /******************************************************/

        /*****************Teaching*************************************/
        TeachingId teachingId = new TeachingId(1,3,1,1);

        Teaching teaching = new Teaching();
        teaching.setName("Analisi");
        teaching.setProfessor(professor);
        teaching.setCourse(course);
        teaching.setId(teachingId);
        teaching.setCredits(10);
        teaching.setYear(1);
        /******************************************************/

        /**************************Lesson****************************/
        LessonId lessonId = new LessonId();
        lessonId.setRoomIdRoom(1);
        lessonId.setTeachingCourseIdCourse(1);
        lessonId.setTeachingProfessorIdProfessor(1);
        lessonId.setTeachingCourseIdCourse(3);
        lessonId.setIdLesson(1);
        lessonId.setTeachingProfessorUserIdUser(1);

        Lesson lesson = new Lesson();
        lesson.setRoom(room);
        lesson.setId(lessonId);
        lesson.setTeaching(teaching);
        lesson.setDate(new Date());
        /******************************************************/

        /***********************Material*******************************/
        MaterialId materialId = new MaterialId();
        materialId.setLessonTeachingProfessorUserIdUser(1);
        materialId.setLessonTeachingProfessorIdProfessor(1);
        materialId.setLessonTeachingIdTeaching(1);
        materialId.setLessonTeachingCourseIdCourse(3);
        materialId.setLessonRoomIdRoom(1);
        materialId.setIdMaterial(1);

        Material material = new Material();
        material.setLink("link");
        material.setName("name");
        material.setLesson(lesson);
        material.setId(materialId);
        /******************************************************/

        /***********************MaterialDTO*******************************/
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setId(1);
        materialDTO.setIdUserProf(1);
        materialDTO.setIdLesson(1);
        materialDTO.setName("name");
        materialDTO.setDate(new Date().toString());
        /******************************************************/

        when(lessonService.getDomainById(1)).thenReturn(lesson);
        when(materialRepository.save(any(Material.class))).thenReturn(material);

        MaterialDTO m = materialService.save(materialDTO);
        assertEquals(materialDTO.getName(), m.getName());
    }
}
