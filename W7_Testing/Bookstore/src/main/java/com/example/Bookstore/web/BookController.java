package com.example.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;
	
	//List all the books
	@RequestMapping(value= "/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//RESTful service to get all books 
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) repository.findAll();
	}
	
	//RESTful service to get book by id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
		return repository.findById(bookId);
	}
	
	//Add a new book
	@RequestMapping(value="/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categorys", crepository.findAll());
		return "addbook";
	}
	
	//Save the new book
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	//Delete the book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id")Long bookId, Model model ) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	//Edit the book
	@GetMapping("/editbook/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	    model.addAttribute("book", repository.findById(id).get());
	    model.addAttribute("categorys", crepository.findAll());
	    return "editbook";
	  }
	  
	@PostMapping("/update/{id}")
	public String updateUser( Book book) {	          	   
		repository.save(book);
	    return "redirect:/booklist";
	}
}
