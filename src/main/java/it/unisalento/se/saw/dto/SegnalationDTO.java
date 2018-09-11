package it.unisalento.se.saw.dto;

public class SegnalationDTO {

    private int id;
    private String note;
    private String description;
    private int idState;
    private int idProfessor;
    private int idRoom;
    private ProfessorDTO professorDTO;
    private RoomDTO roomDTO;
    private SegnalationStateDTO segnalationStateDTO;

    public SegnalationStateDTO getSegnalationStateDTO() {
        return segnalationStateDTO;
    }

    public void setSegnalationStateDTO(SegnalationStateDTO segnalationStateDTO) {
        this.segnalationStateDTO = segnalationStateDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public ProfessorDTO getProfessorDTO() {
        return professorDTO;
    }

    public void setProfessorDTO(ProfessorDTO professorDTO) {
        this.professorDTO = professorDTO;
    }

    public RoomDTO getRoomDTO() {
        return roomDTO;
    }

    public void setRoomDTO(RoomDTO roomDTO) {
        this.roomDTO = roomDTO;
    }


}
