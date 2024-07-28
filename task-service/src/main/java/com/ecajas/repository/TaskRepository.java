package com.ecajas.repository;

import com.ecajas.model.Task;
import com.ecajas.model.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Consulta derivada para encontrar tareas en un rango de fechas
    List<Task> findByDueDateBetween(LocalDate startDate, LocalDate endDate);

    // Consulta para encontrar tareas por prioridad
    List<Task> findByPriority(TaskPriority priority);

    /*
        El @Query tiene un ambiente transaccional de solo lectura, es decir para hacer selects, necesita
        llevar un @Modifying ya que  haciendo una operación que va a alterar un campo de la tabla.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.completed = true WHERE t.id = :id")
    void markTaskAsCompleted(Long id);

    List<Task> findByProjectId(Long projectId);

    @Query("SELECT t FROM Task t JOIN t.project p WHERE p.name LIKE %:projectName%")
    List<Task> findTasksByProjectName(String projectName);

    //Store procedure que hace una lista reporte de las tareas que estan finalizadas.
    @Query(value = "CALL GetTaskReport();", nativeQuery = true)
    List<Object[]> getTaskReport();


}
