package com.centrohospitalar.grupog.services;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

 /*   private List<Task> tasks;

    @Override
    public List<Task> getTasks() {
        if(tasks == null) {
            initializeTasks();
        }

        return tasks;
    }

    @Override
    public void updateTask(Long id) {
        for(Task task : tasks) {
            if(task.getId() == id) {
                task.setComplete(true);
                return;
            }
        }
    }

    private void initializeTasks() {
        tasks = new ArrayList<>();
        tasks.add(new Task("Aprender japonês", LocalDate.parse("2022-09-17")));
        tasks.add(new Task("Aprender japonês", LocalDate.of(2022, 9, 17)));
        tasks.add(new Task("Aprender espanhol", LocalDate.of(2022, 9, 15)));
    }*/
}
