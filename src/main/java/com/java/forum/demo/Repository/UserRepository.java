package com.java.forum.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.forum.demo.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User getByEmail(String email);

}
