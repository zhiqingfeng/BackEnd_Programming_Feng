package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean //i forgot to add this, that's why the booklist do not display
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return(args) -> {
		
			crepository.save(new Category("Humous"));
			crepository.save(new Category("Animal"));
			
			repository.save(new Book("A Farewell to Armns", "Ernest Hemingway", "1232323-21", 1929, crepository.findByName("Animal").get(0)));
			repository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, crepository.findByName("Humous").get(0)));
			
			System.out.println("IN COMMAND LINE RUNNER");
			
			for(Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};
	}
}
