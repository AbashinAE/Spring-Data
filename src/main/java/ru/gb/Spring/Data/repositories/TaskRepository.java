package ru.gb.Spring.Data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.gb.Spring.Data.model.Task;
import ru.gb.Spring.Data.model.TaskStatus;

public interface TaskRepository  extends JpaRepository<Task, Long>{
    List<Task> findByStatus(TaskStatus status);
}