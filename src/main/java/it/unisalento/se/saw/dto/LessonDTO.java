package it.unisalento.se.saw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LessonDTO {

    private int id;
    private int idTeaching;
    private int idRoom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Europe/Rome")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "Europe/Rome")
    private Date start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "Europe/Rome")
    private Date end;
    private RoomDTO roomDTO;
    private TeachingDTO teachingDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }

    public TeachingDTO getTeachingDTO() {
        return teachingDTO;
    }

    public void setTeachingDTO(TeachingDTO teachingDTO) {
        this.teachingDTO = teachingDTO;
    }

    public int getIdTeaching() {
        return idTeaching;
    }

    public void setIdTeaching(int idTeaching) {
        this.idTeaching = idTeaching;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
}
