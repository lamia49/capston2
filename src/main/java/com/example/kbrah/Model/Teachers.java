package com.example.kbrah.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotEmpty(message = "Experince must be not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String experince;
    @NotEmpty
    @Pattern(regexp ="^(free|busy)$",message = "status must be busy or free")
    @Column(columnDefinition = "varchar(10) check(statue ='busy' or statue='free' )")
    private String statue;
}
