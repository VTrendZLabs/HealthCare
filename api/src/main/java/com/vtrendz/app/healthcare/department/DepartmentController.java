package com.vtrendz.app.healthcare.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

  @Autowired
  private DepartmentRepository repository;

  @PostMapping
  public Department add(@RequestBody Department department) {
    LOGGER.info("Department add: {}", department);
    return repository.saveAndFlush(department);
  }

  @GetMapping
  public List<Department> findAll() {
    LOGGER.info("Department find");
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Department> findById(@PathVariable Long id) {
    LOGGER.info("Department find: id={}", id);
    return repository.findById(id);
  }

  @GetMapping("/with-providers")
  public List<Department> findAllWithProviders() {
    LOGGER.info("Department providers");
    // departments.forEach(department -> department.setProviders(providerRepository.findByDepartment(department.getId())));
    return repository.findAll();
  }

}
