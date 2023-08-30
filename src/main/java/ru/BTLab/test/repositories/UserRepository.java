package ru.BTLab.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.BTLab.test.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTelephone(String telephone);
}
