package order_service.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import order_service.dto.CartItemResponse;
import order_service.dto.CartResponse;

@Component
public class CartClient {

	private final RestTemplate restTemplate;
	private final String cartServiceUrl;

	public CartClient(RestTemplate restTemplate,
					  @Value("${cart.service.url}") String cartServiceUrl) {
		this.restTemplate = restTemplate;
		this.cartServiceUrl = cartServiceUrl;
	}

	public CartResponse getCartByUserId(Long userId) {
		String url = cartServiceUrl + "/cart/" + userId;
		return restTemplate.getForObject(url, CartResponse.class);
	}

	public List<CartItemResponse> getAllCartItems() {
		String url = cartServiceUrl + "/cartitem";
		CartItemResponse[] response = restTemplate.getForObject(url, CartItemResponse[].class);
		if (response == null) {
			return Collections.emptyList();
		}
		return Arrays.asList(response);
	}
}
