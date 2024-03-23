package org.project.myapp.Services;

import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.dtos.UserDTO;
import org.project.myapp.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;

    String login(String phoneNumber, String password) throws Exception;

}
