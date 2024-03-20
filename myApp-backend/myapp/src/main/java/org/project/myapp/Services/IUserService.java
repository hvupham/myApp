package org.project.myapp.Services;

import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.dtos.UserDTO;

public interface IUserService {
    void createUser(UserDTO userDTO) throws DataNotFoundException;

    String login(String phoneNumber, String password);

}
