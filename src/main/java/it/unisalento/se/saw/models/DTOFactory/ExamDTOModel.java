package it.unisalento.se.saw.models.DTOFactory;

import it.unisalento.se.saw.domain.Exam;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.ExamDTO;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.dto.TeachingDTO;
import it.unisalento.se.saw.models.AbstractFactory;
import it.unisalento.se.saw.models.FactoryProducer;

public class ExamDTOModel implements DTO<Exam, ExamDTO> {
    @Override
    public ExamDTO create(Exam exam) {
        ExamDTO examDTO = new ExamDTO();

        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Room, RoomDTO> dtoRoom = abstractFactory.getDTO("ROOM");
        DTO<Teaching, TeachingDTO> dtoTeaching = abstractFactory.getDTO("Teaching");

        RoomDTO roomDTO = dtoRoom.create(exam.getRoom());
        TeachingDTO teachingDTO = dtoTeaching.create(exam.getTeaching());

        examDTO.setDate(exam.getData());
        examDTO.setId(exam.getId().getIdExam());
        examDTO.setIdRoom(exam.getId().getRoomIdRoom());
        examDTO.setIdTeaching(exam.getId().getTeachingIdTeaching());
        examDTO.setTime(exam.getTime());
        examDTO.setRoomDTO(roomDTO);
        examDTO.setTeachingDTO(teachingDTO);
        return examDTO;
    }
}
