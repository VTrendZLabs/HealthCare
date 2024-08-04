package com.vtrendz.app.healthcare.provider;


import com.vtrendz.app.healthcare.department.Department;
import com.vtrendz.app.healthcare.department.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/provider")
public class ProviderController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);

  @Autowired
  ProviderRepository repository;

  @Autowired
  DepartmentRepository departmentRepository;


  @PostMapping
  public Provider add(@RequestBody Provider provider) {

//        Optional<Department> department = departmentRepository.findById(provider.getDepartment().getId());
//        department.ifPresent(provider::setDepartment);

    LOGGER.info("Provider add: {}", provider);
    return repository.saveAndFlush(provider);
  }

  @GetMapping
  public List<Provider> findAll() {
    LOGGER.info("Provider find");
    return repository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Provider> findById(@PathVariable("id") Long id) {
    LOGGER.info("Provider find: id={}", id);
    return repository.findById(id);
  }

  @GetMapping("/department/{departmentId}")
  public Set<Provider> findByDepartment(@PathVariable("departmentId") Long departmentId) {
    LOGGER.info("Provider find: departmentId={}", departmentId);
    Optional<Department> department = departmentRepository.findById(departmentId);
    return department.orElseThrow().getProviders();
  }

}
