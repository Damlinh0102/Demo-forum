package com.java.forum.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.java.forum.demo.Model.Post;
import com.java.forum.demo.Model.User;
import com.java.forum.demo.Repository.PostRepository;
import com.java.forum.demo.Repository.UserRepository;
@Service
public class PostService {
	
	@Autowired
	private PostRepository prepository;
	@Autowired
	private UserRepository uRepository;
	
	public Page<Post> display(int pageNum) {
		Pageable page=PageRequest.of(pageNum-1, 5);
		return prepository.findAll(page);
	}
	
	public void save(Post post) {
		prepository.save(post);
	}
	
	public void delete(int id) {
		prepository.deleteById(id);
	}
	
	public Post getById(int id) {
		return prepository.getById(id);
	}
	
	public User getUserPrincipal() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		return uRepository.getByEmail(email);
	}

}
