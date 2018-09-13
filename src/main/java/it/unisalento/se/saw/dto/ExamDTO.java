package it.unisalento.se.saw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExamDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Europe/Rome")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm",timezone = "Europe/Rome")
    private Date time;
    private int idRoom;
    private int idTeaching;
    private int id;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdTeaching() {
        return idTeaching;
    }

    public void setIdTeaching(int idTeaching) {
        this.idTeaching = idTeaching;
    }
}
