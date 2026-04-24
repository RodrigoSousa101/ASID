package book_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book_service.entity.Book;
import book_service.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String query) {
        if (query != null && !query.isBlank()) {
            return ResponseEntity.ok(bookService.searchBooks(query));
        }

        return ResponseEntity.ok(bookService.getAllBook());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PatchMapping("/books/{id}/quantity")
    public ResponseEntity<Book> patchBookQuantity(@PathVariable Long id, @RequestBody Book book) {
        Book patchedBook = bookService.patchBookQuantity(id, book);
        return ResponseEntity.ok(patchedBook);
    }
}
