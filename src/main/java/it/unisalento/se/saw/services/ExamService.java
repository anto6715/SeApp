package it.unisalento.se.saw.services;

import it.unisalento.se.saw.Iservices.IExamServices;
import it.unisalento.se.saw.Iservices.IRoomServices;
import it.unisalento.se.saw.Iservices.ITeachingServices;
import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.domain.ExamId;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.exceptions.ExamNotFoundException;
import it.unisalento.se.saw.exceptions.RoomNotFoundException;
import it.unisalento.se.saw.exceptions.TeachingNotFoundException;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.DTOFactory.DTO;
import it.unisalento.se.saw.models.FactoryProducer;
import it.unisalento.se.saw.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ExamService implements IExamServices {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    IRoomServices roomServices;

    @Autowired
    ITeachingServices teachingServices;

    AbstractFactory dtoFactory = FactoryProducer.getFactory("DTO");

    @Transactional(readOnly = true)
    public Set<ExamDTO> getAll() {
        DTO<List<Exam>, Set<ExamDTO>> dto = dtoFactory.getDTO("SetExam");
        return dto.create(examRepository.findAll());
    }

    @Transactional
    public ExamDTO getById(int id) throws ExamNotFoundException {
        try {
            DTO<Exam, ExamDTO> dto = dtoFactory.getDTO("Exam");
            return dto.create(examRepository.findExamById_IdExam(id));
        } catch (Exception e) {
            throw new ExamNotFoundException();
        }
    }

    @Transactional
    public ExamDTO save(ExamDTO examDTO) {
        DTO<Exam, ExamDTO> dto = dtoFactory.getDTO("Exam");
        Room room = null;
        try {
            room = roomServices.getDomainById(examDTO.getIdRoom());
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        }
        Teaching teaching = null;
        try {
            teaching = teachingServices.getDomainById(examDTO.getIdTeaching());
        } catch (TeachingNotFoundException e) {
            e.printStackTrace();
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
        return dto.create(examRepository.save(exam));

    }


}
