package com.java101_edycja1.todo_list.controller;
import com.java101_edycja1.todo_list.model.Task;
import com.java101_edycja1.todo_list.service.TaskService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class FormController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/new-task")
    public String showCreateForm(Task task) {
        return "new-task";
    }

    @PostMapping("/task")
    public String createTask(@Valid Task task, BindingResult result, Model model) {
        Task item = new Task();
        item.setDescription(task.getDescription());
        item.setIsComplete(task.getIsComplete());

        taskService.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Task with id: " + " not found!")));
        taskService.delete(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Task task = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Task with id: " + " not found!")));
        model.addAttribute("task", task);
        return "edit-task";
    }
    @PostMapping("/task/{id}")
    public String updateTask(@PathVariable("id") Long id, @Valid Task task, BindingResult bindingResult) {
        LocalDate currentDate = LocalDate.now();
        Task item = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Task with id: " + " not found!")));
        item.setIsComplete(task.getIsComplete());
        item.setTitle(task.getTitle());
        item.setDescription(task.getDescription());
        if (task.getDueDate().isBefore(currentDate)) {
            bindingResult.rejectValue("dueDate", "error.task", "Due date cannot be in the past");
            return "edit-task";
        }
        item.setDueDate(task.getDueDate());

        taskService.save(item);

        return "redirect:/";
    }
    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException(("Task with id: " + " not found!")));
        return "redirect:/";
    }
}
