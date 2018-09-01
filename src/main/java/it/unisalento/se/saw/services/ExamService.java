package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IExamServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.domain.ExamId;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.exceptions.ExamNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DomainFactory.Domain;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ExamService implements IExamServices {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    IRoomServices roomServices;

    @Autowired
    ITeachingServices teachingServices;

    @Transactional(readOnly = true)
    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    @Transactional
    public Exam getById(int id) throws ExamNotFoundException {
        try {
            return examRepository.findExamById_IdExam(id);
        } catch (Exception e) {
            throw new ExamNotFoundException();
        }
    }

    @Transactional
    public Exam save(ExamDTO examDTO) throws RoomNotFoundException, TeachingNotFoundException {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DOMAIN");
        Domain<TeachingDTO, Teaching> domainTeaching = abstractFactory.getDomain("Teaching");
        Room room;
        Teaching teaching;
        try {
            room = roomServices.getById(examDTO.getIdRoom());
        } catch (Exception e) {
            throw new RoomNotFoundException();
        }

        try{
            teaching = domainTeaching.create(teachingServices.getById(examDTO.getIdTeaching()));
        } catch (Exception e) {
            throw new TeachingNotFoundException();
        }


        ExamId examId = new ExamId();
        examId.setRoomIdRoom(room.getIdRoom());
        examId.setTeachingIdTeaching(teaching.getId().getIdTeaching());
        examId.setTeachingCourseIdCourse(teaching.getId().getCourseIdCourse());
        examId.setTeachingProfessorIdProfessor(teaching.getId().getProfessorIdProfessor());
        examId.setTeachingProfessorUserIdUser(teaching.getId().getProfessorUserIdUser());



        Exam exam = new Exam();
        exam.setTeaching(teaching);
        exam.setRoom(room);
        exam.setId(examId);
        exam.setData(examDTO.getDate());
        exam.setTime(examDTO.getTime());
        return examRepository.save(exam);

    }


    @Transactional
    public void remove(int id) throws ExamNotFoundException {

    }
}
