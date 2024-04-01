package com.example.kbrah.Service;

import com.example.kbrah.ApiExiption.ApiExcipstion;
import com.example.kbrah.Model.Cirtificate;
import com.example.kbrah.Model.Courses;
import com.example.kbrah.Model.User;
import com.example.kbrah.Repositry.CertificateRepositry;
import com.example.kbrah.Repositry.CourseRepositry;
import com.example.kbrah.Repositry.UserRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepositry repositryUser;
    private final CourseRepositry courseRepositry;
    private final CourseService courseService;
    private  final CertificateRepositry certificateRepositry;

    public void add(User user){
        repositryUser.save(user);
    }
    public List get() {

        return repositryUser.findAll();
    }

    public boolean update(int id, User user) {
        User user1=repositryUser.getById(id);
        if(user1==null){
            return false;
        }
        user1.setUsername(user.getUsername());
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setBalance(user.getBalance());
        repositryUser.save(user1);
        return true;
    }

    public boolean delete(int id) {
        User user=repositryUser.getById(id);
        if(user==null) {
            return false;
        }
        repositryUser.delete(user);
        return true;
    }


    public boolean subscribe(int courseID,int userId){
        Courses course=courseRepositry.findCoursesById(courseID);
        User user=repositryUser.findUserById(userId);
        if(user==null){
            throw new ApiExcipstion("user id not found");
        }else if(course==null){
            throw new ApiExcipstion("course id not found");
        }

        if (user.getBalance() >= course.getPrice()) {
                user.setBalance(user.getBalance() - course.getPrice());
                user.setStatue("on");
                courseService.checkStatus(course);
                courseService.numberOfsubscribe(course);
                repositryUser.save(user);
                courseRepositry.save(course);
                return true;
            }
         return false;
    }

    public boolean checkSubscribe(int userId){
        User user=repositryUser.findUserById(userId);
   if(user.getStatue().equalsIgnoreCase("on")){
       return false;
   }
   return true;
    }

    public boolean discoundSpecial(int userID,int courseID){
        List<Cirtificate> cirtificates= certificateRepositry.findCirtificateByUserId(userID);
        Courses course=courseRepositry.findCoursesById(courseID);
        User user=repositryUser.findUserById(userID);
        if(user==null){
            throw new ApiExcipstion("user id not found");
        }else if(course==null){
            throw new ApiExcipstion("course id not found");
        }
        if(cirtificates.size()>=5){
            if (user.getBalance() >= course.getPrice()*0.5) {
                user.setBalance((user.getBalance() - course.getPrice()*0.5));
            courseService.checkStatus(course);
            courseService.numberOfsubscribe(course);
            user.setStatue("on");
            repositryUser.save(user);
            courseRepositry.save(course);
            return true;
        }}

        return false;
    }


    public boolean discount(int userID, int courseID,double discount) {
        User user=repositryUser.getById(userID);
        Courses courses=courseRepositry.findCoursesById(courseID);
            if(user==null){
                throw new ApiExcipstion("user id not found");
            }else if(courses==null){
                throw new ApiExcipstion("course id not found");
            }

        if (user.getRole().equalsIgnoreCase("admin")){
                double offers =courses.getPrice() - courses.getPrice() * discount;
                courses.setPrice(offers);
              courseRepositry.save(courses);
                return true;
            }
        return false;
        }



}
