package book_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import book_service.entity.Book;

@Service
public interface BookService {
    Book createBook(Book book);
    List<Book> getAllBook();
    Book getBookById(Long id);
    List<Book> getBooksByCategoryID(Long id);
    List<Book> searchBooks(String query);
    Book patchBookQuantity(Long id, Book book);
    
}
