package com.vtrendz.app.healthcare.department;

import com.vtrendz.app.healthcare.provider.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @ManyToMany(mappedBy = "departments")
  private Set<Provider> providers;
}
