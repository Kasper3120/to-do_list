package com.java101_edycja1.todo_list.service;

import com.java101_edycja1.todo_list.model.Task;
import com.java101_edycja1.todo_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public void delete(Task task) {
       taskRepository.delete(task);
    }

    public void save(Task task) {
        taskRepository.save(task);
    }
}
