package com.example.kbrah.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name must not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @NotEmpty(message = "username must be not empty")
    @Column(columnDefinition = "varchar(20) unique")
    private String username;
    @NotEmpty(message = "password must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotEmpty(message = "email must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String email;
    @Pattern(regexp ="^(admin|student)$",message = "role must be admin or student")
    @Column(columnDefinition = "varchar(10) check(role ='admin' or role='student' )")
    private String role;
    @NotNull(message = "balance id must be not empty")
    @Column(columnDefinition = "double not null")
    @Positive
    private Double balance;
    @Pattern(regexp ="^(on|off)$",message = "status must be on or off")
    @Column(columnDefinition = "varchar(10) check(statue ='on' or statue='off' )")
    private String statue ="off";

}
