package cart_service.service;

import java.util.List;
import cart_service.entity.Cart;
import cart_service.entity.CartItem;
import cart_service.dto.AddToCartRequest;

public interface CartService {
    Cart createCart(Cart cart);
    List<Cart> getAllCart();
    Cart getCartIdByUserId(Long userId);
    CartItem addBookToCart(AddToCartRequest request);
}
