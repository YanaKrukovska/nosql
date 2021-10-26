package com.krukovska.mongodb;

import com.krukovska.mongodb.model.Subtask;
import com.krukovska.mongodb.model.Task;
import com.krukovska.mongodb.service.SubtaskService;
import com.krukovska.mongodb.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

@SpringBootApplication
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Bean
    CommandLineRunner init(TaskService taskService, SubtaskService subtaskService) {

            /*
    + Display on console all tasks.
    + Display overdue tasks.
    + Display all tasks with the specific category (query parameter).
    ? Display all subtasks related to tasks with the specific category (query parameter).
    + Insert task
    + Update task
    + Delete task
    Perform insert/update/delete all subtasks of the given task (query parameter).
    Support full-text search by word in task description.
    Support full-text search by sub-task name.
     */

        return args -> {

            // Clean up
            taskService.deleteAll();
            subtaskService.deleteAll();

            populateTestData(taskService, subtaskService);

            printTaskResult("Display on console all tasks", taskService.findAllTasks());
            printTaskResult("Display overdue tasks",  taskService.findAllOverdueTasks());
            printTaskResult("Display all tasks with the specific category",  taskService.findAllTasksByCategory("Mentoring"));

            System.out.println("Display all subtasks related to tasks with the specific category");
            taskService.findAllSubtasksByCategory("Mentoring").forEach(System.out::println);
            System.out.println();

            System.out.println("Delete task by id");
            // Create task
            Task taskToDelete = taskService.save(Task.builder().name("Delete").description("Task to delete")
                    .creationDate(now())
                    .deadline(of(2021, 10, 24, 23, 59, 59))
                    .build());
            System.out.println("Tasks amount: " + taskService.findAllTasks().size());
            taskService.delete(taskToDelete.getId());
            System.out.println("Tasks amount: " + taskService.findAllTasks().size());
            System.out.println();

            printTaskResult("Support full-text search by word in task description", taskService.findAllByDescription("tasks"));

            printTaskResult("Support full-text search by sub-task name", taskService.findAllBySubtaskName("Task"));

        };

    }

    private void printTaskResult(String taskName, List<Task> tasks) {
        System.out.println(taskName);
        tasks.forEach(System.out::println);
        System.out.println();
    }

    private void populateTestData(TaskService taskService, SubtaskService subtaskService) {
        Subtask nosqlSubtask1 = subtaskService.save(new Subtask("NoSql Task 1", "Move project to MongoDb"));
        Subtask nosqlSubtask2 = subtaskService.save(new Subtask("NoSql Task 2", "Create MongoDb project"));
        Subtask nosqlSubtask3 = subtaskService.save(new Subtask("NoSql Task 3", "Create CassandraDb project"));

        // Create task
        Task task1 = taskService.save(Task.builder().name("MongoDb").description("Complete NoSQL tasks")
                .creationDate(now())
                .deadline(of(2021, 10, 24, 23, 59, 59))
                .category("Mentoring")
                .subtasks(new ArrayList<>(Arrays.asList(nosqlSubtask1, nosqlSubtask2, nosqlSubtask3)))
                .build());

        // Update task
        task1.setName("NoSQL");
        Task updatedTask1 = taskService.save(task1);
        System.out.println(updatedTask1);

        taskService.save(Task.builder()
                .name("Update Windows")
                .description("Update Windows to version 11")
                .category("Upgrades")
                .creationDate(of(2021, 10, 16, 12, 33, 41))
                .deadline(of(2021, 10, 20, 10, 0, 0))
                .build());


        Subtask kafkaSubtask1 = subtaskService.save(new Subtask("Kafka Task 1", "Start Kafka Zookeeper"));
        Subtask kafkaSubtask2 = subtaskService.save(new Subtask("Kafka Task 2", "Create Kafka project"));

        taskService.save(Task.builder().name("Kafka").description("Complete Kafka tasks")
                .creationDate(of(2021, 9, 1, 9, 12, 55))
                .deadline(of(2021, 10, 10, 23, 59, 59))
                .category("Mentoring")
                .subtasks(new ArrayList<>(Arrays.asList(kafkaSubtask1, kafkaSubtask2)))
                .build());
    }

}
