package com.java.forum.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.forum.demo.Model.User;
import com.java.forum.demo.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepository;
	
	public void save(User user) {
		uRepository.save(user);
	}
}
