package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Lesson;
import it.unisalento.se.saw.domain.Room;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.dto.LessonDTO;
import it.unisalento.se.saw.dto.RoomDTO;
import it.unisalento.se.saw.dto.TeachingDTO;

public class LessonDTOModel implements DTO<Lesson, LessonDTO> {
    @Override
    public LessonDTO create(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        AbstractFactory abstractFactory = FactoryProducer.getFactory("DTO");
        DTO<Room, RoomDTO> dtoRoom = abstractFactory.getDTO("ROOM");
        DTO<Teaching, TeachingDTO> dtoTeaching = abstractFactory.getDTO("Teaching");

        RoomDTO roomDTO = dtoRoom.create(lesson.getRoom());
        TeachingDTO teachingDTO = dtoTeaching.create(lesson.getTeaching());

        lessonDTO.setId(lesson.getId().getIdLesson());
        lessonDTO.setIdRoom(lesson.getId().getRoomIdRoom());
        lessonDTO.setIdTeaching(lesson.getId().getTeachingIdTeaching());
        lessonDTO.setDate(lesson.getDate());
        lessonDTO.setStart(lesson.getStart());
        lessonDTO.setEnd(lesson.getEnd());
        lessonDTO.setRoomDTO(roomDTO);
        lessonDTO.setTeachingDTO(teachingDTO);
        return lessonDTO;
    }
}
