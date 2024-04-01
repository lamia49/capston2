package com.example.kbrah.Repositry;

import com.example.kbrah.Model.Cirtificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepositry extends JpaRepository<Cirtificate,Integer> {
    Cirtificate findCirtificateById(Integer id);
    List<Cirtificate> findCirtificateByCourseId(Integer courseID);
    List<Cirtificate> findCirtificateByUserId(Integer userID);

}
