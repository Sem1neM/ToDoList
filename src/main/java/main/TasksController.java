package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/tasks/")
    public int addTask(Task task){
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/tasks/")
    public List<Task> getTasks(){
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable){
            tasks.add(task);
        }
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable int id){
        taskRepository.deleteById(id);
    }

    @DeleteMapping("/tasks/")
    public void deleteAllTask(){
        taskRepository.deleteAll();
    }

    @PutMapping("/tasks/{id}")
    public void updateTask(@PathVariable int id, Task newTask){
        Task task = taskRepository.findById(id).get();
        if (newTask.getDate() != null) task.setDate(newTask.getDate());
        if (newTask.getName() != null) task.setName(newTask.getName());
        taskRepository.save(task);
    }
}
