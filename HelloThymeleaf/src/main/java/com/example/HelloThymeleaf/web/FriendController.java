package com.example.HelloThymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class FriendController {
	
	//Homework3:
	
	private ArrayList<String> friends = new ArrayList<>();
	
	@RequestMapping("/friend")
	public String friendList(@RequestParam(name="Name", required = false) String name, Model model) {
		
		if(name != null) {
			friends.add(name);
		}
		model.addAttribute("friends", friends);
		return "friendlist";
		
	}
}
