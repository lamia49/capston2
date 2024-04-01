package com.example.kbrah.Controller;

import com.example.kbrah.Model.PlatForms;

import com.example.kbrah.Service.PlatFormService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/kbrah/platforms")
@AllArgsConstructor
public class PlatFormsController {
    private final PlatFormService platFormService;


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid PlatForms platForms , Errors error){
        if(error.hasErrors()){
            String massege= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        platFormService.add(platForms);
        return ResponseEntity.status(200).body("added");
    }


    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(platFormService.get());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id , @RequestBody @Valid PlatForms platForms, Errors error) {
        if (error.hasErrors()) {
            String massege = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        boolean isFound = platFormService.update(id, platForms);
        if (isFound) {
            return ResponseEntity.status(200).body("updated");
        }
        return ResponseEntity.status(400).body("Not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity update(@PathVariable int id) {

        boolean isFound = platFormService.delete(id);
        if (isFound) {
            return ResponseEntity.status(200).body("deleted");
        }
        return ResponseEntity.status(400).body("Not found");
    }

    @PutMapping("/openSetion/{platFormID}/{teacherId}/{courseId}")
public ResponseEntity openSetion(@PathVariable int platFormID,@PathVariable int teacherId,@PathVariable int courseId){
   platFormService.openSetion(platFormID,teacherId,courseId);

       return ResponseEntity.status(200).body("sesstion is opend");



}

    @PutMapping("/closeSetion/{platFormID}/{teacherId}")
    public ResponseEntity closeSetion(@PathVariable int platFormID,@PathVariable int teacherId){
       platFormService.closeSestion(platFormID,teacherId);
       return ResponseEntity.status(200).body("sestion is closed");

    }

@GetMapping("/search_NotStarSestion")
public ResponseEntity searchOpenPlatForms(){
        return ResponseEntity.status(200).body(platFormService.byOpenSestion());
}









}
