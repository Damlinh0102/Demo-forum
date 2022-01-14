package com.java.forum.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.forum.demo.Model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
