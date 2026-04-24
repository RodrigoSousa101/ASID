package cart_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cart_service.client.BookClient;
import cart_service.dto.AddToCartRequest;
import cart_service.dto.BookResponse;
import cart_service.entity.Cart;
import cart_service.entity.CartItem;
import cart_service.repository.CartItemRepository;
import cart_service.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookClient bookClient;

    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           BookClient bookClient) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookClient = bookClient;
    }

    @Override
    public Cart createCart(Cart createCart) {
        if (createCart.getCreatedDate() == null) {
            createCart.setCreatedDate(LocalDate.now());
        }

        return cartRepository.save(createCart);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartIdByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public CartItem addBookToCart(AddToCartRequest request) {
        BookResponse book = bookClient.getBookById(request.getBookId());

        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado.");
        }

        if (book.getQuantity() == null || book.getQuantity() < request.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente.");
        }

        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(request.getUserId());
                    newCart.setCreatedDate(LocalDate.now());
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndBookId(cart.getId(), request.getBookId());

        CartItem cartItem;

        if (existingItem.isPresent()) {
            cartItem = existingItem.get();
            int newQuantity = cartItem.getQuantity() + request.getQuantity();
            cartItem.setQuantity(newQuantity);
            cartItem.setSubTotal(newQuantity * cartItem.getUnitPrice());
        } else {
            cartItem = new CartItem();
            cartItem.setCartId(cart.getId());
            cartItem.setBookId(request.getBookId());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUnitPrice(book.getPrice());
            cartItem.setSubTotal(book.getPrice() * request.getQuantity());
        }

        return cartItemRepository.save(cartItem);
    }
}