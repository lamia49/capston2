package com.example.kbrah.Repositry;

import com.example.kbrah.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<User,Integer> {
    User findUserById(Integer id);

}
