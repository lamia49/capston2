package com.example.kbrah.Controller;

import com.example.kbrah.Model.Cirtificate;
import com.example.kbrah.Model.PlatForms;
import com.example.kbrah.Service.CirtificateService;
import com.example.kbrah.Service.PlatFormService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kbrah/cirtificat")
@AllArgsConstructor
public class CirtificatController {
    private final CirtificateService cirtificateService;


    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Cirtificate cirtificate , Errors error){
        if(error.hasErrors()){
            String massege= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        boolean isFound=cirtificateService.add(cirtificate);
        if(isFound) {
            return ResponseEntity.status(200).body("added");
        }
        return ResponseEntity.status(200).body("set the information correctlly");
    }


    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(cirtificateService.get());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id , @RequestBody @Valid Cirtificate cirtificate, Errors error) {
        if (error.hasErrors()) {
            String massege = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        boolean isFound = cirtificateService.updat(id,cirtificate);
        if (isFound) {
            return ResponseEntity.status(200).body("updated");
        }
        return ResponseEntity.status(400).body("Not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity update(@PathVariable int id) {

        boolean isFound = cirtificateService.delete(id);
        if (isFound) {
            return ResponseEntity.status(200).body("deleted");
        }
        return ResponseEntity.status(400).body("Not found");
    }


    @GetMapping("/user_Certificat/{userId}")
    public List userCertificat(@PathVariable int userId){
        return cirtificateService.userCertificat(userId);
    }


    @GetMapping("/studentPass/{courseId}")
    public ResponseEntity<Integer> studentPass(@PathVariable int courseId){
        Integer numberOfPassStudent=cirtificateService.byCourses(courseId);
        return ResponseEntity.status(200).body(numberOfPassStudent);
    }



}
