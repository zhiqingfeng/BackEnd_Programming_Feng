package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test //search
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("Animal Farm");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
	}

	@Test //create
	public void createNewBook() {
		Category category = new Category("IT");
		crepository.save(category);
		Book book = new Book("Jussi Mikkola", "Mobile programming", "1232323-22", 2023, category);
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test //delete
	public void deleteNewBook() {
		List<Book> books = repository.findByTitle("A Farewell to Armns");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("A Farewell to Armns");
		assertThat(newBooks).hasSize(0);
	}
	
}
