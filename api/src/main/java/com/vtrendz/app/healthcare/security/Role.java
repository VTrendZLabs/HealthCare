package com.vtrendz.app.healthcare.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vtrendz.app.healthcare.security.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
  ADMIN(
    Set.of(
      ADMIN_READ,
      ADMIN_UPDATE,
      ADMIN_DELETE,
      ADMIN_CREATE,
      MANAGER_READ,
      MANAGER_UPDATE,
      MANAGER_DELETE,
      MANAGER_CREATE
    )
  ),
  MANAGER(
    Set.of(
      MANAGER_READ,
      MANAGER_UPDATE,
      MANAGER_DELETE,
      MANAGER_CREATE
    )
  ),
  PROVIDER(
    Set.of(
      PROVIDER_READ,
      PROVIDER_UPDATE,
      PROVIDER_DELETE,
      PROVIDER_CREATE
    )
  ),
  USER(
    Set.of(
      USER_READ,
      USER_UPDATE,
      USER_DELETE,
      USER_CREATE
    )
  );

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
      .stream()
      .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
      .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
