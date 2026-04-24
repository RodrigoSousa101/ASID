package book_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import book_service.entity.Book;
import book_service.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
	}

	@Override
	public List<Book> getBooksByCategoryID(Long id) {
		return bookRepository.findByCategoryId(id);
	}

	@Override
	public List<Book> searchBooks(String query) {
		return bookRepository.searchBooks(query);
	}

	@Override
	public Book patchBookQuantity(Long id, Book book) {
		Book existingBook = bookRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

		existingBook.setQuantity(book.getQuantity());
		return bookRepository.save(existingBook);
	}
}
