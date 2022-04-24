package springboot.books.BookList.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "title")
	private String title;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;

	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private Category category;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "author_id")
	@NotNull
	private Author author;

	@Column(nullable = true)
	private Integer rating;

	private String imgUrl;

	@Override
	public String toString() {
		return "Book [title=" + title + ", releaseDate=" + releaseDate + ", category=" + category + ", author=" + author
				+ "]";
	}
}
