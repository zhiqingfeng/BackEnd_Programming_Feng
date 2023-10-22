package com.example.Bookstore;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.AppUser;
import com.example.Bookstore.domain.AppUserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	@Bean //i forgot to add this, that's why the booklist do not display
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, AppUserRepository urepository) {
		return(args) -> {
		
			crepository.save(new Category("Humous"));
			crepository.save(new Category("Animal"));
			
			repository.save(new Book("Ernest Hemingway", "A Farewell to Armns", "1232323-21", 1929, crepository.findByName("Animal").get(0)));
			repository.save(new Book("George Orwell", "Animal Farm", "2212343-5", 1945, crepository.findByName("Humous").get(0)));
			
			
			//create users: admin/admin, user/user
			AppUser user1 = new AppUser("user","$2a$12$pCiZA6N.pei7TOjTp8a4.ezWkPTdFsdUMZi7DUJZLgG2IyuL8P80S", "user@bookstore.com","USER");
			AppUser user2 = new AppUser("admin", "$2a$12$XiKfsUER3vJO3zQCC95U7unG6acm/E.w0eH93u9S9JDgUJZnxBa9m", "admin@bookstore.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			System.out.println("IN COMMAND LINE RUNNER");
			
			for(Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};
	}
}
