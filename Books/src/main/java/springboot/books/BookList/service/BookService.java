package springboot.books.BookList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import springboot.books.BookList.model.Book;
import springboot.books.BookList.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> getListOfBooks() {
		return bookRepository.findAll();
	}

	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}

	public List<Book> getByKeyword(String keyword) {
		return bookRepository.findByKeyword(keyword);
	}

	public Book getBook(long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("User with id=" + id + "not found."));
	}
}
