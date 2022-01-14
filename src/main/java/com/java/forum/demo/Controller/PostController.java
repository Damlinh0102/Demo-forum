package com.java.forum.demo.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.catalina.startup.HomesUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.forum.demo.Model.Post;
import com.java.forum.demo.Model.User;
import com.java.forum.demo.Repository.UserRepository;
import com.java.forum.demo.Service.PostService;
import com.java.forum.demo.Service.UserService;

@Controller
public class PostController {

	@Autowired
	private PostService service;
	
	private UserService uService;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	@RequestMapping("/display/{pageNum}")
	public String display(@PathVariable int pageNum, Model model) { 
		Page<Post> page = service.display(pageNum);
		List<Post> list=page.getContent();
		model.addAttribute("totalpages", page.getTotalPages());
		model.addAttribute("posts", list);
		return "index";
	}
	
	@RequestMapping("/seemore/{id}")
	public String seeMore(@PathVariable int id, Model model) {
		Post post=service.getById(id);
		model.addAttribute("principal", service.getUserPrincipal().getId());
		model.addAttribute("post", post);
		return "detailsPost";
	}
	
	@RequestMapping("/add")
	public String addPost(Model model) {
		Post post=new Post();
		model.addAttribute("post", post);
		return "form";
	}
	
	@RequestMapping("/update/{id}")
	public String updatePost(@PathVariable int id, Model model) {
		model.addAttribute("post", service.getById(id));
		return "form";
	}
	
	@RequestMapping("/save")
	public String save(Post post) {
		Date date=new Date();
		post.setDay(date);
		post.setUser(service.getUserPrincipal());
		service.save(post);
		return "redirect:/display/1";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		service.delete(id);
		return "redirect:/display/1";
	}
	@RequestMapping("/signup")
	public String signup() {
		return "register";
	}
	@RequestMapping("/register")
	public String register(User user) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		uService.save(user);
		return "redirect:/home";
	}
}
