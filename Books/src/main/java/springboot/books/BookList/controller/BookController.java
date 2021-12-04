package springboot.books.BookList.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import springboot.books.BookList.model.Book;
import springboot.books.BookList.service.BookService;

@AllArgsConstructor
@Controller
public class BookController {

	private BookService bookService;

	@GetMapping("/listOfBooks")
	public ModelAndView getListOfBooks() {
		ModelAndView mav = new ModelAndView("listBooks");
		List<Book> books = bookService.getListOfBooks();
		mav.addObject("books", books);
		return mav;
	}

	@GetMapping("/addBook")
	public ModelAndView addBookForm() {
		ModelAndView mav = new ModelAndView("addBook");
		Book book = new Book();
		mav.addObject("book", book);
		return mav;
	}

	@PostMapping("/saveBook")
	public String submitForm(@ModelAttribute Book book) {
		bookService.addBook(book);
		return "redirect:/home";
	}

	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		bookService.deleteBook(book);
		return "redirect:/listBooks";
	}

	@RequestMapping("/search")
	public ModelAndView searchBooks(String keyword) {
		ModelAndView mav = new ModelAndView("listBooks");
		if (keyword != null) {
			mav.addObject("users", bookService.getByKeyword(keyword));
		} else {
			mav.addObject("users", bookService.getListOfBooks());
		}
		return mav;
	}
}
