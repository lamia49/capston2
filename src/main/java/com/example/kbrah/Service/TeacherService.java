package com.example.kbrah.Service;

import com.example.kbrah.ApiExiption.ApiExcipstion;
import com.example.kbrah.Model.Courses;
import com.example.kbrah.Model.Teachers;
import com.example.kbrah.Model.User;
import com.example.kbrah.Repositry.TeacherRepositry;
import com.example.kbrah.Repositry.UserRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepositry teacherRepositry;
    private final UserRepositry userRepositry;

    public void add(Teachers teachers){
        teacherRepositry.save(teachers);
    }

    public List<Teachers> get(){
        List<Teachers>teachers =teacherRepositry.findAll();
        return teachers;
    }
    public boolean update(int id,Teachers teacher) {
        Teachers teacher1=teacherRepositry.getById(id);
        if(teacher1==null){
            return false;
        }
        teacher1.setName(teacher.getName());
        teacher1.setExperince(teacher.getExperince());
        teacherRepositry.save(teacher1);
        return true;
    }

    public boolean delete(int id) {
        Teachers teacher=teacherRepositry.getById(id);
        if(teacher==null) {
            return false;
        }
        teacherRepositry.delete(teacher);
        return true;
    }

    public boolean changeStatus( int teacherID) {
        Teachers teacher = teacherRepositry.findTeachersById(teacherID);
        if (teacher == null) {
            throw new ApiExcipstion("id teacher not found");
        }
            if(teacher.getStatue().equalsIgnoreCase("free")) {
                teacher.setStatue("busy");
                teacherRepositry.save(teacher);
            }else if(teacher.getStatue().equalsIgnoreCase("busy"))
            teacher.setStatue("free");
        teacherRepositry.save(teacher);
            return true;

    }

    public List<Teachers> freeTeachers(String statue){
        List<Teachers>teachersList=teacherRepositry.findTeachersByStatue(statue);
        return teachersList;
    }
    }


