package com.vtrendz.app.healthcare.doctor;


import com.vtrendz.app.healthcare.department.Department;
import com.vtrendz.app.healthcare.department.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    DoctorRepository repository;

    @Autowired
    DepartmentRepository departmentRepository;


    @PostMapping
    public Doctor add(@RequestBody Doctor doctor) {

//        Optional<Department> department = departmentRepository.findById(doctor.getDepartment().getId());
//        department.ifPresent(doctor::setDepartment);

        LOGGER.info("Doctor add: {}", doctor);
        return repository.saveAndFlush(doctor);
    }

    @GetMapping
    public List<Doctor> findAll() {
        LOGGER.info("Doctor find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Doctor> findById(@PathVariable("id") Long id) {
        LOGGER.info("Doctor find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Doctor> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Doctor find: departmentId={}", departmentId);
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.orElseThrow().getDoctors();
    }

}
