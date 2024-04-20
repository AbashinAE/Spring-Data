package ru.gb.Spring.Data.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ru.gb.Spring.Data.model.Task;
import ru.gb.Spring.Data.model.TaskStatus;
import ru.gb.Spring.Data.repositories.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getListTasksByStatus( TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task updatedTask = taskRepository.getReferenceById(id);
        updatedTask.setStatusTask(status);
        updatedTask.setLastUpdateTimeTask(LocalDateTime.now());
        return taskRepository.saveAndFlush(updatedTask);
    }

    public void deleteTaskWithId(Long id) {
        taskRepository.delete(taskRepository.getReferenceById(id));
    }
}