package springboot.books.BookList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.books.BookList.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
