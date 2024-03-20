package org.project.myapp.Repositoris;

import org.project.myapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber (String phoneNumber);


    Optional<User> findByPhoneNumber(String phoneNumber);

}
