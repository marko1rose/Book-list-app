package springboot.books.BookList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springboot.books.BookList.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "select * from book b where b.title like %:keyword% or b.author like %:keyword%", nativeQuery = true)
	List<Book> findByKeyword(@Param("keyword") String keyword);
}
