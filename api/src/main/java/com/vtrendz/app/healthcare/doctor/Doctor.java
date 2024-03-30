package com.vtrendz.app.healthcare.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vtrendz.app.healthcare.department.Department;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="position")
    private String position;

    @Column(name = "department_id")
    @JsonProperty("departmentId")
    private Long departmentId;

    @ManyToOne(targetEntity= Department.class, fetch= FetchType.LAZY)
    @JoinColumn(name = "department_id", insertable=false, updatable=false)
    @JsonIgnore
    private Department department;
}
