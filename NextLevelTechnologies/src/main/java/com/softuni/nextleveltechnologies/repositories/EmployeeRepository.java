package com.softuni.nextleveltechnologies.repositories;

import com.softuni.nextleveltechnologies.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findAllByAgeAfter(int age);
}
