package com.nithin.springsecuritypractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nithin.springsecuritypractice.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
