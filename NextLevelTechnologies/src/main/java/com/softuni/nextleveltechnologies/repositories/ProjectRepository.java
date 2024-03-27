package com.softuni.nextleveltechnologies.repositories;

import com.softuni.nextleveltechnologies.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);

    @Query("select p from Project p where p.isFinished = :isFinished")
    List<Project> findAllByIsFinished(Boolean isFinished);
}
