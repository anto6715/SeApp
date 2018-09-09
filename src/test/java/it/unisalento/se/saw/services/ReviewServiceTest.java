package it.unisalento.se.saw.services;

import it.unisalento.se.saw.domain.*;
import it.unisalento.se.saw.dto.ReviewDTO;
import it.unisalento.se.saw.exceptions.*;
import it.unisalento.se.saw.repositories.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    LessonService lessonService;

    @Mock
    MaterialService materialService;

    @Mock
    ReviewTypeService reviewTypeService;

    @Mock
    StudentService studentService;

    @InjectMocks
    ReviewService reviewService;

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId();
        reviewId.setReviewTypeIdReviewType(1);
        reviewId.setStudentCourseIdCourse(3);
        reviewId.setStudentIdStudent(1);
        reviewId.setStudentUserIdUser(1);

        Review review = new Review();
        review.setId(reviewId);
        review.setStudent(student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        review.setReviewType(reviewType);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findAll()).thenReturn(reviews);

        Set<ReviewDTO> reviewDTOS = reviewService.getAll();
        assertEquals(review.getId().getIdReview(), reviewDTOS.iterator().next().getId());
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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId();
        reviewId.setReviewTypeIdReviewType(1);
        reviewId.setStudentCourseIdCourse(3);
        reviewId.setStudentIdStudent(1);
        reviewId.setStudentUserIdUser(1);

        Review review = new Review(reviewId,lesson,material,reviewType,student,"note",1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewsByLesson_Id_IdLesson(1)).thenReturn(reviews);

        Set<ReviewDTO> reviewDTOS = reviewService.getByIdLesson(1);
        assertEquals(review.getId().getIdReview(), reviewDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdMaterialTest() {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId();
        reviewId.setReviewTypeIdReviewType(1);
        reviewId.setStudentCourseIdCourse(3);
        reviewId.setStudentIdStudent(1);
        reviewId.setStudentUserIdUser(1);

        Review review = new Review();
        review.setId(reviewId);
        review.setStudent(student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        review.setReviewType(reviewType);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewsByMaterial_Id_IdMaterial(1)).thenReturn(reviews);

        Set<ReviewDTO> reviewDTOS = reviewService.getByIdMaterial(1);
        assertEquals(review.getId().getIdReview(), reviewDTOS.iterator().next().getId());
    }

    @Test
    public void getByType() {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId();
        reviewId.setReviewTypeIdReviewType(1);
        reviewId.setStudentCourseIdCourse(3);
        reviewId.setStudentIdStudent(1);
        reviewId.setStudentUserIdUser(1);

        Review review = new Review();
        review.setId(reviewId);
        review.setStudent(student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        review.setReviewType(reviewType);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewsById_ReviewTypeIdReviewType(1)).thenReturn(reviews);

        Set<ReviewDTO> reviewDTOS = reviewService.getByType(1);
        assertEquals(review.getId().getIdReview(), reviewDTOS.iterator().next().getId());
    }

    @Test
    public void getByIdTest() throws ReviewNotFoundException {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId(1,1,3,1,1);

        Review review = new Review(reviewId,reviewType,student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewById_IdReview(1)).thenReturn(review);

        ReviewDTO reviewDTO = reviewService.getById(1);
        assertEquals(review.getId().getIdReview(), reviewDTO.getId());
    }

    @Test
    public void getByIdErrorTest() throws ReviewNotFoundException {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId(1,1,3,1,1);

        Review review = new Review(reviewId,reviewType,student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewById_IdReview(1)).thenReturn(review);

        try {
            ReviewDTO reviewDTO = reviewService.getById(3);
            assertEquals(review.getId().getIdReview(), reviewDTO.getId());
        } catch (ReviewNotFoundException e) {
            assertEquals("Review not found", e.getMessage());
        }

    }

    @Test
    public void getByIdStudentAndIdMaterialTest() throws ReviewNotFoundException {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId(1,1,3,1,1);

        Review review = new Review(reviewId,reviewType,student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewById_StudentIdStudentAndMaterial_Id_IdMaterial(1,1)).thenReturn(review);

        ReviewDTO reviewDTO = reviewService.getByIdStudentAndIdMaterial(1,1);
        assertEquals(review.getId().getIdReview(), reviewDTO.getId());
    }

    @Test
    public void getByIdStudentAndIdMaterialErrorTest() throws ReviewNotFoundException {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId(1,1,3,1,1);

        Review review = new Review(reviewId,reviewType,student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewById_StudentIdStudentAndMaterial_Id_IdMaterial(1,1)).thenReturn(review);

        try{
            ReviewDTO reviewDTO = reviewService.getByIdStudentAndIdMaterial(1,2);
            assertEquals(review.getId().getIdReview(), reviewDTO.getId());
        } catch (ReviewNotFoundException e) {
            assertEquals("Review not found",e.getMessage());
        }

    }

    @Test
    public void getByIdStudentAndIdLessonTest() throws ReviewNotFoundException {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId(1,1,3,1,1);

        Review review = new Review(reviewId,reviewType,student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewById_StudentIdStudentAndLesson_Id_IdLesson(1,1)).thenReturn(review);

        ReviewDTO reviewDTO = reviewService.getByIdStudentAndIdLesson(1,1);
        assertEquals(review.getId().getIdReview(), reviewDTO.getId());
    }

    @Test
    public void getByIdStudentAndIdLessonErrorTest() throws ReviewNotFoundException {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId(1,1,3,1,1);

        Review review = new Review(reviewId,reviewType,student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findReviewById_StudentIdStudentAndLesson_Id_IdLesson(1,1)).thenReturn(review);

        try {
            ReviewDTO reviewDTO = reviewService.getByIdStudentAndIdLesson(1,4);
            assertEquals(review.getId().getIdReview(), reviewDTO.getId());
        } catch (ReviewNotFoundException e) {
            assertEquals("Review not found", e.getMessage());
        }

    }

    @Test
    public void saveTest() throws ReviewNotFoundException, LessonNotFoundException, MaterialNotFoundException, ReviewTypeNotFoundException, StudentNotFoundException {

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

        /************************Student******************************/
        StudentId studentId = new StudentId();
        studentId.setIdStudent(1);
        studentId.setUserIdUser(1);
        studentId.setCourseIdCourse(3);

        Student student = new Student();
        student.setId(studentId);
        student.setUser(user);
        student.setCourse(course);
        student.setMatricola("matricola");
        student.setYear(1);
        student.setYearStart(1);
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

        /**************************ReviewType****************************/
        ReviewType reviewType = new ReviewType();
        reviewType.setType("type");
        reviewType.setIdReviewType(1);
        /******************************************************/

        /********************Review**********************************/
        ReviewId reviewId = new ReviewId(1,1,3,1,1);

        Review review = new Review(reviewId,reviewType,student);
        review.setMaterial(material);
        review.setLesson(lesson);
        review.setRate(1);
        /******************************************************/

        /********************ReviewDTO**********************************/
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setIdLesson(1);
        reviewDTO.setIdMaterial(1);
        reviewDTO.setIdStudent(1);
        reviewDTO.setIdReviewType(1);
        reviewDTO.setRate(1);
        reviewDTO.setNote("note");
        reviewDTO.setId(1);
        /******************************************************/

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(lessonService.getDomainById(1)).thenReturn(lesson);
        when(materialService.getDomainById(1)).thenReturn(material);
        when(studentService.getDomainById(1)).thenReturn(student);
        when(reviewTypeService.getDomainById(1)).thenReturn(reviewType);

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewDTO r = reviewService.save(reviewDTO);
        assertEquals(reviewDTO.getId(), r.getId());
    }


}
