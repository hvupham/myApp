package org.project.myapp.Services;

import lombok.RequiredArgsConstructor;
import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.Repositoris.RoleRepository;
import org.project.myapp.Repositoris.UserRepository;
import org.project.myapp.dtos.UserDTO;
import org.project.myapp.models.Role;
import org.project.myapp.models.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void createUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));

        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .active(true)
                .roleId(role)
                .build();

        // Kiem tra neu co accountId thi khong yeu cau mat khau
        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            String password = userDTO.getPassword();
            // String encodedPassword = passwordEncoder.encode(password);
            // newUser.setPassword(encodedPassword);
        }

        userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        // Implement your login logic here
        return null;
    }
}
