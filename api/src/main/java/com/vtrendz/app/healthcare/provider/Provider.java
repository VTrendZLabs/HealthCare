package com.vtrendz.app.healthcare.provider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtrendz.app.healthcare.department.Department;
import com.vtrendz.app.healthcare.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Provider {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @OneToOne(mappedBy = "provider")
  private User user;

  @Column(name = "position")
  private String position;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JoinTable(name = "PROVIDER_DEPARTMENT_MAPPING", joinColumns = @JoinColumn(name = "provider_id"),
    inverseJoinColumns = @JoinColumn(name = "department_id"))
  @JsonIgnore
  private Set<Department> departments;


}
