package com.example.kbrah.Repositry;

import com.example.kbrah.Model.Catogary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatogaryRepositry extends JpaRepository<Catogary,Integer> {
    Catogary findCatogariesById(int id);
   List<Catogary> findCatogariesByName(String name);
}
