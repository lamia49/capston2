package com.example.kbrah.Controller;

import com.example.kbrah.Model.Catogary;
import com.example.kbrah.Model.Teachers;
import com.example.kbrah.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kbrah/teachers")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Teachers teachers , Errors error){
        if(error.hasErrors()){
            String massege= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        teacherService.add(teachers);
        return ResponseEntity.status(200).body("added");
    }


    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(teacherService.get());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id , @RequestBody @Valid Teachers teachers, Errors error) {
        if (error.hasErrors()) {
            String massege = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        boolean isFound = teacherService.update(id, teachers);
        if (isFound) {
            return ResponseEntity.status(200).body("updated");
        }
        return ResponseEntity.status(400).body("Not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity update(@PathVariable int id) {

        boolean isFound = teacherService.delete(id);
        if (isFound) {
            return ResponseEntity.status(200).body("deleted");
        }
        return ResponseEntity.status(400).body("Not found");
    }


    @PutMapping("/chang-state/{teacherId}")
    public ResponseEntity changeState(@PathVariable int teacherId){
      boolean check =teacherService.changeStatus(teacherId);
      if(check) {
          return ResponseEntity.status(200).body("change successfully");
      }
        return ResponseEntity.status(200).body("your not allowed");
    }




    @GetMapping("/free_teachers/{statue}")
    public ResponseEntity freeTeachers(@PathVariable String statue){
        return ResponseEntity.status(200).body(teacherService.freeTeachers(statue));
    }

}
