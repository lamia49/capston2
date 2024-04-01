package com.example.kbrah.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer teacherId;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer catogaryId;
    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;
    @NotNull
    @Column(columnDefinition = "int not null")
    @Min(value = 0)
    private Integer numberOfsets;
    @Pattern(regexp ="^(close|open)$")
    @Column(columnDefinition = "varchar(10) check(statue ='open' or statue='close' )")
    private String statue;
    @NotNull
    @Column(columnDefinition = "double not null")
    private Double price;
    @Column(columnDefinition = "int default 0")
    @Min(value = 0)
    private  Integer numberOfsubscribe = 0 ;

}
