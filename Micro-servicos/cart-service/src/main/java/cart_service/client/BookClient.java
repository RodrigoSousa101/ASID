package cart_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import cart_service.dto.BookResponse;

@Component
public class BookClient {
    @Value("${book.service.url}")
    private String bookServiceUrl;

    private final RestTemplate restTemplate;

    public BookClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BookResponse getBookById(Long bookId) {
        String url = bookServiceUrl + "/books/" + bookId;
        try {
            ResponseEntity<BookResponse> response = restTemplate.getForEntity(url, BookResponse.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound ex) {
            return null;
        }
    }
}