package com.example.kbrah.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Cirtificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer teacherId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer courseId;
    @NotEmpty(message = "name must not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;


}
