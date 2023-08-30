package ru.BTLab.test.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.BTLab.test.models.Truancy;

import java.util.List;

public interface TruancyRepository extends JpaRepository<Truancy, Long> {
    List<Truancy> findAllByUserId(Long userId, PageRequest of);
}
