package it.unisalento.se.saw.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExamDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "Europe/Rome")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss",timezone = "Europe/Rome")
    private Date time;
    private int idRoom;
    private int idTeaching;

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
