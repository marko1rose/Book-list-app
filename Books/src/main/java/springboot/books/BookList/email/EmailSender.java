package springboot.books.BookList.email;

public interface EmailSender {
	void send(String to, String email);
}
