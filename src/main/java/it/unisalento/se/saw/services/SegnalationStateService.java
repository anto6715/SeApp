package it.unisalento.se.saw.services;


import it.unisalento.se.saw.Iservices.ISegnalationStateServices;
import it.unisalento.se.saw.domain.SegnalationState;
import it.unisalento.se.saw.dto.SegnalationStateDTO;
import it.unisalento.se.saw.exceptions.SegnalationStateNotFoundException;
import it.unisalento.se.saw.repositories.SegnalationStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SegnalationStateService implements ISegnalationStateServices {

    @Autowired
    SegnalationStateRepository segnalationStateRepository;

    @Transactional
    public List<SegnalationState> getAll() {
        return segnalationStateRepository.findAll();
    }
    @Transactional
    public SegnalationState getById(int id) throws SegnalationStateNotFoundException {
        try {
            return segnalationStateRepository.getOne(id);
        } catch (Exception e) {
            throw new SegnalationStateNotFoundException();
        }
    }
    @Transactional
    public SegnalationState save(SegnalationStateDTO segnalationStateDTO) {
        SegnalationState segnalationState = new SegnalationState();
        segnalationState.setState(segnalationStateDTO.getState());
        return segnalationStateRepository.save(segnalationState);
    }
    @Transactional
    public void remove(int id) throws SegnalationStateNotFoundException {
        try {
            SegnalationState segnalationState =  segnalationStateRepository.getOne(id);
            segnalationStateRepository.delete(segnalationState);
        } catch (Exception e) {
            throw new SegnalationStateNotFoundException();
        }
    }
}
