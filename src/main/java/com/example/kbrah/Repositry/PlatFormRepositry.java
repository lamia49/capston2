package com.example.kbrah.Repositry;


import com.example.kbrah.Model.PlatForms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlatFormRepositry extends JpaRepository<PlatForms,Integer> {
    PlatForms findPlatFormsById(Integer id);
    List<PlatForms> findPlatFormsByStatue(String statue);
}
