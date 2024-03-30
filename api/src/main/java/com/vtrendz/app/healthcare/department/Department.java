package com.vtrendz.app.healthcare.department;

import com.vtrendz.app.healthcare.doctor.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name", unique=true)
    private String name;

    @OneToMany(targetEntity= Doctor.class, cascade=ALL, mappedBy="department", fetch= FetchType.LAZY)
    private List<Doctor> doctors;
}
