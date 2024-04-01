package com.example.kbrah.Controller;

import com.example.kbrah.Model.Catogary;
import com.example.kbrah.Service.CatogaryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/kbrah/catogary")
public class CatogaryController {
    private final CatogaryService catogaryService;


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Catogary catogary, Errors error){
        if(error.hasErrors()){
            String massege= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        catogaryService.adding(catogary);
        return ResponseEntity.status(200).body("added");
    }

    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(catogaryService.getting());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id , @RequestBody @Valid Catogary catogary, Errors error) {
        if (error.hasErrors()) {
            String massege = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        boolean isFound = catogaryService.update(id, catogary);
        if (isFound) {
            return ResponseEntity.status(200).body("updated");
        }
        return ResponseEntity.status(400).body("Not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity update(@PathVariable int id) {

        boolean isFound = catogaryService.delete(id);
        if (isFound) {
            return ResponseEntity.status(200).body("deleted");
        }
        return ResponseEntity.status(400).body("Not found");
    }




}
