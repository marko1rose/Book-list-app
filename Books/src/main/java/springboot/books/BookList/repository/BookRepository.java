package springboot.books.BookList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springboot.books.BookList.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "select b.* from book b join author a on a.id = b.author_id where b.title like %:keyword% or a.first_name like %:keyword% or a.last_name like %:keyword%", nativeQuery = true)
	List<Book> findByKeyword(@Param("keyword") String keyword);
}
