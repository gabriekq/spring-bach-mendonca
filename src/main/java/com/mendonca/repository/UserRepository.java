package com.mendonca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mendonca.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
}
