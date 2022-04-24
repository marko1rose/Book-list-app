package springboot.books.BookList.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import springboot.books.BookList.exporter.BookListExcelExporter;
import springboot.books.BookList.model.Author;
import springboot.books.BookList.model.Book;
import springboot.books.BookList.service.AuthorService;
import springboot.books.BookList.service.BookService;

@AllArgsConstructor
@Controller
public class BookController {

	private BookService bookService;
	private AuthorService authorService;

	@GetMapping("/list")
	public ModelAndView getListOfBooks() {
		ModelAndView mav = new ModelAndView("listBooks");
		List<Book> books = bookService.getListOfBooks();
		mav.addObject("books", books);
		return mav;
	}

	@GetMapping("/addBook")
	public ModelAndView addBookForm() {
		ModelAndView mav = new ModelAndView("addBookForm");
		Book book = new Book();
		mav.addObject("book", book);
		mav.addObject("authors", authorService.getListOfAuthors());
		return mav;
	}

	@PostMapping("/saveBook")
	public String submitForm(@ModelAttribute Book book, @ModelAttribute Author author) {
		book.setAuthor(author);
		bookService.addBook(book);
		return "redirect:/list";
	}

	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		Book book = bookService.getBook(id);
		bookService.deleteBook(book);
		return "redirect:/list";
	}

	@RequestMapping("/search")
	public ModelAndView searchBooks(String keyword) {
		ModelAndView mav = new ModelAndView("listBooks");
		if (keyword != null) {
			mav.addObject("books", bookService.getByKeyword(keyword));
		} else {
			mav.addObject("books", bookService.getListOfBooks());
		}
		return mav;
	}

	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long id) {
		ModelAndView mav = new ModelAndView("addBookForm");
		Book book = bookService.getBook(id);
		mav.addObject("book", book);
		mav.addObject("authors", authorService.getListOfAuthors());
		return mav;
	}

	@GetMapping("/exportExcel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=books_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Book> listBooks = bookService.getListOfBooks();

		BookListExcelExporter excelExporter = new BookListExcelExporter(listBooks);

		excelExporter.export(response);
	}
}
