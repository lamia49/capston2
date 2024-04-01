package com.example.kbrah.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlatForms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer teacherId;
    @NotEmpty(message = "name must not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @DateTimeFormat
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;
    @DateTimeFormat
    @Column(columnDefinition = "DATE")
    private LocalDate endDate;
    @Pattern(regexp ="^(notStart|Start)$")
    @Column(columnDefinition = "varchar(10) check(statue ='Start' or statue='notStart' )")
    private String statue;
}
