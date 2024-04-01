package com.example.kbrah.Controller;

import com.example.kbrah.Model.Courses;
import com.example.kbrah.Model.PlatForms;
import com.example.kbrah.Model.User;
import com.example.kbrah.Service.CourseService;
import com.example.kbrah.Service.PlatFormService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kbrah/course")
@AllArgsConstructor
public class CourcesController {
    private final CourseService courseService;


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Courses courses , Errors error){
        if(error.hasErrors()){
            String massege= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        courseService.add(courses);
        return ResponseEntity.status(200).body("added");
    }


    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(courseService.get());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id , @RequestBody @Valid Courses courses, Errors error) {
        if (error.hasErrors()) {
            String massege = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }boolean isFound = courseService.update(id, courses);
        if (isFound) {
            return ResponseEntity.status(200).body("updated");
        }
        return ResponseEntity.status(400).body("Not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity update(@PathVariable int id) {

        boolean isFound = courseService.delete(id);
        if (isFound) {
            return ResponseEntity.status(200).body("deleted");
        }
        return ResponseEntity.status(400).body("Not found");
    }


    @GetMapping("/top_course")
    public ResponseEntity topCourse(){
        return ResponseEntity.status(200).body(courseService.TopCourse());
    }



    @PutMapping("add_seats/{courseId}/{userId}/{amount}")
    public ResponseEntity chaeckNumberOfSeats(@PathVariable int courseId,@PathVariable int userId,@PathVariable int amount) {
           boolean addSeats=courseService.addSeats(userId,courseId,amount);
           if(addSeats) {
               return ResponseEntity.status(200).body("added");
           }
        return ResponseEntity.status(200).body("you not allowed");
       }



       @GetMapping("/open_courses")
       public ResponseEntity searchOpenCourse(){
        return ResponseEntity.status(200).body(courseService.openCourses());

       }
    }






