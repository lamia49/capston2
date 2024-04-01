package com.example.kbrah.Controller;

import com.example.kbrah.Model.User;
import com.example.kbrah.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kbrah/users")
@AllArgsConstructor
public class UserCntroller {
    private final UserService userService;



    @PostMapping("add")
    public ResponseEntity add(@RequestBody @Valid User user , Errors error){
        if(error.hasErrors()){
            String massege= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }

        userService.add(user);
        return ResponseEntity.status(200).body("added");}

    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.status(200).body(userService.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id , @RequestBody @Valid User user, Errors error){
        if(error.hasErrors()){
            String massege= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        boolean isFound= userService.update(id,user);
        if(isFound){
            return ResponseEntity.status(200).body("updated");
        }
        return ResponseEntity.status(400).body("not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        boolean isFound= userService.delete(id);
        if(isFound){
            return ResponseEntity.status(200).body("deleted");
        }
        return ResponseEntity.status(400).body("notfound");
    }

    @PutMapping("/buy/{userId}/{courseId}")
    public ResponseEntity byu(@PathVariable int userId,@PathVariable int courseId){
  boolean buy=userService.subscribe(courseId,userId);
  if (buy){
      return ResponseEntity.status(200).body("successfully subscribed");
  }
        return ResponseEntity.status(200).body("bad request");
    }


    @GetMapping("/check_subscribe/{userId}")
    public ResponseEntity checkUserSebscribe(@PathVariable int userId){
        boolean check=userService.checkSubscribe(userId);
        if(check){
            return ResponseEntity.status(200).body("user does not have course");
        }
        return ResponseEntity.status(200).body("user have course");
    }


    @PutMapping("/specialDiscount/{userId}/{courseID}")
    public ResponseEntity speicilDiscount(@PathVariable int userId,@PathVariable int courseID){
        boolean buy=userService.discoundSpecial(courseID,userId);
        if (buy){
            return ResponseEntity.status(200).body("successfully subscribed");
        }
        return ResponseEntity.status(200).body("you should have at least 5 Certificate");
    }



    @PutMapping("/discount/{userId}/{courseID}/{amount}")
    public ResponseEntity discount(@PathVariable int userId,@PathVariable int courseID,@PathVariable double amount){
        boolean buy=userService.discount(userId,courseID,amount);
        if (buy){
            return ResponseEntity.status(200).body("successfully discount");
        }
        return ResponseEntity.status(200).body("you are not allowed");
    }


}
