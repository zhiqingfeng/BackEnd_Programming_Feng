package com.example.HelloThymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.HelloThymeleaf.domain.*;
import java.util.ArrayList;

@Controller
public class StudentController {

	//Homework2:
	@RequestMapping("/studentlist")
	public String studentList(Model model) {
		ArrayList<Student> students = new ArrayList<>();
		
		students.add(new Student("Kate", "Cole"));
		students.add(new Student("Dan", "Brown"));
		students.add(new Student("Mike", "Mars"));
		
		model.addAttribute("students", students);
		return "studentlist";
	}
}
