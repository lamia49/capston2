package com.example.kbrah.Service;

import com.example.kbrah.ApiExiption.ApiExcipstion;
import com.example.kbrah.Model.Courses;
import com.example.kbrah.Model.PlatForms;
import com.example.kbrah.Model.Teachers;
import com.example.kbrah.Model.User;
import com.example.kbrah.Repositry.CourseRepositry;
import com.example.kbrah.Repositry.PlatFormRepositry;
import com.example.kbrah.Repositry.TeacherRepositry;
import com.example.kbrah.Repositry.UserRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PlatFormService {

    private final PlatFormRepositry platFormRepositry;
    private final  TeacherRepositry teacherRepositry;
    private final CourseRepositry courseRepositry;
    public void add(PlatForms platForms){
        Teachers teachers=teacherRepositry.findTeachersById(platForms.getTeacherId());
        if(teachers==null){
            throw new ApiExcipstion("techear id not found");
        }
        platFormRepositry.save(platForms);
    }

    public List<PlatForms> get(){
        List<PlatForms>platForms =platFormRepositry.findAll();
        return platForms;
    }
    public boolean update(int id,PlatForms platForms) {
        PlatForms platForms1=platFormRepositry.getById(id);
        if(platForms1==null){
            return false;
        }
        platForms1.setName(platForms.getName());
        platForms1.setStartDate(platForms.getStartDate());
        platForms1.setEndDate(platForms.getEndDate());
        platFormRepositry.save(platForms1);
        return true;
    }

    public boolean delete(int id) {
        PlatForms platForms=platFormRepositry.getById(id);
        if(platForms==null) {
            return false;
        }
        platFormRepositry.delete(platForms);
        return true;
    }


    public void openSetion(int platFormID,int teacherID,int courseId){
    PlatForms platForms=platFormRepositry.findPlatFormsById(platFormID);
    Teachers teacher=teacherRepositry.findTeachersById(teacherID);
        Courses courses=courseRepositry.findCoursesById(courseId);
    if(teacher==null){
        throw new ApiExcipstion("teacher id not found");
    } else if (platForms==null) {
        throw new ApiExcipstion("platform id not found");
    } else if (courses==null) {
        throw new ApiExcipstion("coures id not found");
    }
        platForms.setStatue("Start");
        platFormRepositry.save(platForms);
    }




    public void closeSestion(int platformID,int teacherID){
        PlatForms platForms=platFormRepositry.findPlatFormsById(platformID);
        Teachers teacher=teacherRepositry.findTeachersById(teacherID);
        if(teacher==null){
            throw new ApiExcipstion("teacher id not found");
        } else if (platForms==null) {
            throw new ApiExcipstion("platform id not found");
        }
            platForms.setStatue("notStart");
            platFormRepositry.save(platForms);
    }


    public String byOpenSestion(){
        List<PlatForms>platFormsList= platFormRepositry.findPlatFormsByStatue("NotStart");
        if(platFormsList.isEmpty()){
            return "all platforms busy";
        }
         return platFormsList.toString();
    }

    }

