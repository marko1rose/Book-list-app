package springboot.books.BookList.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import springboot.books.BookList.model.Author;
import springboot.books.BookList.repository.AuthorRepository;

@AllArgsConstructor
@Service
public class AuthorService {

	private AuthorRepository authorRepository;

	public List<Author> getListOfAuthors() {
		return authorRepository.findAll();
	}

}
