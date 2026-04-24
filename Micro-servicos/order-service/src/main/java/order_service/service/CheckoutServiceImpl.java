package order_service.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import order_service.client.CartClient;
import order_service.dto.CartItemResponse;
import order_service.dto.CartResponse;
import order_service.dto.CheckoutRequest;
import order_service.dto.CheckoutResponse;
import order_service.entity.Orders;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartClient cartClient;
    private final OrdersService ordersService;

    public CheckoutServiceImpl(CartClient cartClient, OrdersService ordersService) {
        this.cartClient = cartClient;
        this.ordersService = ordersService;
    }

    @Override
    public CheckoutResponse createOrderFromUserCart(CheckoutRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido de checkout invalido.");
        }

        Long userId = request.getUserId();
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId e obrigatorio.");
        }

        if (request.getAmountPaid() == null || request.getAmountPaid() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "amountPaid deve ser maior ou igual a 0.");
        }

        CartResponse cart = cartClient.getCartByUserId(userId);
        if (cart == null || cart.getId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho nao encontrado para o utilizador.");
        }

        List<CartItemResponse> allCartItems = cartClient.getAllCartItems();
        List<CartItemResponse> cartItems = allCartItems.stream()
                .filter(item -> cart.getId().equals(item.getCartId()))
                .toList();

        if (cartItems.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho vazio.");
        }

        double total = cartItems.stream()
                .map(CartItemResponse::getSubTotal)
                .filter(value -> value != null)
                .mapToDouble(Double::doubleValue)
                .sum();

        boolean paymentSuccessful = request.getAmountPaid() >= total;

        CheckoutResponse response = new CheckoutResponse();
        response.setUserId(userId);
        response.setCartId(cart.getId());
        response.setCartItems(cartItems);
        response.setCartTotal(total);
        response.setAmountPaid(request.getAmountPaid());
        response.setPaymentSuccessful(paymentSuccessful);

        if (!paymentSuccessful) {
            double missing = total - request.getAmountPaid();
            response.setPaymentMessage("Pagamento falhou. Valor em falta: " + missing);
            response.setChangeAmount(0.0);
            response.setOrderId(null);
            return response;
        }

        Orders order = new Orders();
        order.setOrderDate(new Date());
        order.setTotalPrice(total);
        order.setUserId(userId);
        Orders createdOrder = ordersService.createOrder(order);

        response.setPaymentMessage("Pagamento confirmado com sucesso.");
        response.setChangeAmount(request.getAmountPaid() - total);
        response.setOrderId(createdOrder.getId());

        return response;
    }
}