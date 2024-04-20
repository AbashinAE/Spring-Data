package ru.gb.Spring.Data.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.gb.Spring.Data.model.Task;
import ru.gb.Spring.Data.model.TaskStatus;
import ru.gb.Spring.Data.services.TaskService;

@RestController
public class TaskController {
    private final TaskService dataService;

    public TaskController(TaskService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public List<Task> getAllTasks(){
        return dataService.getAllTasks();
    }

    @PutMapping("/")
    public Task addTask(@RequestBody Task task) {

        var currentTime = LocalDateTime.now();
        task.setCreateTimeTask(currentTime);
        task.setLastUpdateTimeTask(currentTime);

        task.setStatusTask(TaskStatus.NOT_STARTED);
        return dataService.addTask(task);
    }
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status){
        return dataService.getListTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return dataService.updateTaskStatus(id, status);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        dataService.deleteTaskWithId(id);
    }
}
