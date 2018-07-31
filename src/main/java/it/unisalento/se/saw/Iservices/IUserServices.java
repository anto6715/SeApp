package it.unisalento.se.saw.Iservices;

import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserServices {

    public List<User> getAll();
    public User getByName(String name) throws UserNotFoundException;
    public User save(User user);
    public User getById(int id) throws UserNotFoundException;
    public void removeUserById(int id) throws UserNotFoundException;
}
