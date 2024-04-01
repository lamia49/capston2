package com.example.kbrah.Repositry;

import com.example.kbrah.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositry extends JpaRepository<Courses,Integer> {
    Courses findCoursesById(Integer id);
    Courses findTopByOrderByNumberOfsubscribeDesc();
    List<Courses> findCoursesByStatue(String state);
}
