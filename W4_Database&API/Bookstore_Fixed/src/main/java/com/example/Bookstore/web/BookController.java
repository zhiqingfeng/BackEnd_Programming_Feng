package com.example.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.*;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	//List all the books
	@RequestMapping(value= "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//Add a new book
	@RequestMapping(value="/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	//Save the new book
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	//Delete the book
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id")Long bookId, Model model ) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	//Edit the book
	@GetMapping("/editbook/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("book", repository.findById(id).get());
	    return "editbook";
	  }
	  
	@PostMapping("/update/{id}")
	public String updateUser( Book book) {	          	   
		repository.save(book);
	    return "redirect:/booklist";
	}
}
