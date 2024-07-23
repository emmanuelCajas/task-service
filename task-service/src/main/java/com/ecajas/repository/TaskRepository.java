package com.ecajas.repository;

import com.ecajas.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    @Query("SELECT t FROM Task t JOIN t.project p WHERE p.name LIKE %:projectName%")
    List<Task> findTasksByProjectName(String projectName);
}
