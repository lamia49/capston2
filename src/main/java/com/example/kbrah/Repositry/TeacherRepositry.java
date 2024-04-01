package com.example.kbrah.Repositry;



import com.example.kbrah.Model.Teachers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepositry extends JpaRepository<Teachers,Integer> {

    Teachers findTeachersById(Integer id);
    List<Teachers> findTeachersByStatue(String statue);
}
