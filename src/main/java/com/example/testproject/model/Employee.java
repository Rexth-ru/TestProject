package com.example.testproject.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String familyName;
    private String phone;
    private Date birthday;
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

}
