package com.example.kbrah.Service;

import com.example.kbrah.ApiExiption.ApiExcipstion;
import com.example.kbrah.Model.Cirtificate;
import com.example.kbrah.Model.Courses;
import com.example.kbrah.Model.Teachers;
import com.example.kbrah.Model.User;
import com.example.kbrah.Repositry.CertificateRepositry;
import com.example.kbrah.Repositry.CourseRepositry;
import com.example.kbrah.Repositry.TeacherRepositry;
import com.example.kbrah.Repositry.UserRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CirtificateService {
    private final CertificateRepositry certificateRepositry;
    public final CourseRepositry courseRepositry;
    public  final TeacherRepositry teacherRepositry;
    public final UserRepositry userRepositry;

    public boolean add(Cirtificate cirtificate){
        Courses courses=courseRepositry.findCoursesById(cirtificate.getCourseId());
        Teachers teachers=teacherRepositry.findTeachersById(cirtificate.getTeacherId());
        User user=userRepositry.findUserById(cirtificate.getUserId());
        if(teachers!=null){
            if(user!=null){
                if(courses!=null){
                    certificateRepositry.save(cirtificate);
                    return true;
                }return false;
        }}return false;

    }

    public List<Cirtificate>get(){
        List<Cirtificate>cirtificates=certificateRepositry.findAll();
        return cirtificates;
    }

    public boolean updat(int id,Cirtificate cirtificate){
        Cirtificate cirtificate1=certificateRepositry.findCirtificateById(id);
        if(cirtificate1==null){
            return false;
        }
        cirtificate1.setName(cirtificate.getName());
        certificateRepositry.save(cirtificate1);
        return true;
    }
    public boolean delete(int id) {
        Cirtificate cirtificate=certificateRepositry.getById(id);
        if(cirtificate==null) {
            return false;
        }
        certificateRepositry.delete(cirtificate);
        return true;
    }


    public List<Cirtificate> userCertificat(int userID){
        List<Cirtificate>cirtificates=certificateRepositry.findCirtificateByUserId(userID);
        return cirtificates;
    }


    public Integer byCourses(int courseID){
        List<Cirtificate>cirtificates=certificateRepositry.findCirtificateByCourseId(courseID);

        return cirtificates.size();
    }




}
