package com.example.kbrah.Service;

import com.example.kbrah.ApiExiption.ApiExcipstion;
import com.example.kbrah.Model.*;
import com.example.kbrah.Repositry.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    public final CourseRepositry courseRepositry;
    public  final TeacherRepositry teacherRepositry;
    public final UserRepositry userRepositry;
    public final CatogaryRepositry catogaryRepositry;
    public final CertificateRepositry certificateRepositry;
    public void add(Courses courses){
        Teachers teachers=teacherRepositry.findTeachersById(courses.getTeacherId());
        Catogary catogary=catogaryRepositry.findCatogariesById(courses.getCatogaryId());
        if(teachers==null){
            throw new ApiExcipstion("techear id not found");
        }else if(catogary==null){
            throw new ApiExcipstion("catogary id not found");
        }
        courseRepositry.save(courses);
    }
    public List<Courses> get(){
        List<Courses>coursesList =courseRepositry.findAll();
        return coursesList;
    }

    public boolean update(Integer id,Courses courses){
        Courses courses1=courseRepositry.findCoursesById(id);
        if(courses1==null){
            return false;
        }
        courses1.setName(courses.getName());
        courses1.setNumberOfsets(courses.getNumberOfsets());
        courses1.setStatue(courses.getStatue());
     courseRepositry.save(courses1);
     return true;
    }
    public boolean delete(int id) {
        Courses courses=courseRepositry.getById(id);
        if(courses==null) {
            return false;
        }
        courseRepositry.delete(courses);
        return true;
    }

 public void checkStatus(Courses course){
     course.setNumberOfsets(course.getNumberOfsets() - 1);
     if(course.getNumberOfsets()==0){
         course.setStatue("close");
     }
 }

 public void numberOfsubscribe(Courses courses){
        courses.setNumberOfsubscribe(courses.getNumberOfsubscribe()+1);
 }


 public Courses TopCourse(){
return courseRepositry.findTopByOrderByNumberOfsubscribeDesc();
 }

 public String openCourses(){
        List<Courses>coursesList=courseRepositry.findCoursesByStatue("open");
        if (coursesList.isEmpty()){
            return "all courses is closed";
        }
        return coursesList.toString();
 }

    public boolean addSeats(int userId,int courseId, int amount) {
        Courses courses = courseRepositry.findCoursesById(courseId);
        User user = userRepositry.findUserById(userId);
        if (courses == null) {
            throw new ApiExcipstion("id course not found");
        } else if (user == null) {
            throw new ApiExcipstion("id user not found");
        } else if (user.getRole().equalsIgnoreCase("admin")) {
            courses.setStatue("open");
            courses.setNumberOfsets(courses.getNumberOfsets() + amount);
            courseRepositry.save(courses);
            return true;
        }
        return false;
    }


        }





