package com.example.kbrah.Service;

import com.example.kbrah.Model.Catogary;
import com.example.kbrah.Model.Courses;
import com.example.kbrah.Repositry.CatogaryRepositry;
import com.example.kbrah.Repositry.CourseRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatogaryService {
    private final CatogaryRepositry rebositryCatogary;
    private final CourseRepositry courseRepositry;
    public void adding(Catogary catogary){
        rebositryCatogary.save(catogary);
    }
    public List<Catogary> getting(){
        return rebositryCatogary.findAll() ;
    }
    public boolean update(int id , Catogary catogary){
        Catogary catogary1 = rebositryCatogary.getById(id);
        if(catogary1==null){
            return false;
        }
        catogary1.setName(catogary.getName());
        rebositryCatogary.save(catogary1);
        return true;
    }

    public boolean delete(int id){
        Catogary catogary=rebositryCatogary.getById(id);
        if(catogary==null){
            return false;
        }
        rebositryCatogary.deleteById(id);
        return true;
    }

    public List<Catogary> byName(String catogary){
        List<Catogary> catogaris=rebositryCatogary.findCatogariesByName(catogary);
        return catogaris;   
    }


}
