package com.example.business.order.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.business.cart.entities.CartItem;
import com.example.business.cart.exceptions.EmptyCartException;
import com.example.business.cart.services.CartService;
import com.example.business.order.dtos.request.OrderCreateRequest;
import com.example.business.order.dtos.response.OrderItemResponse;
import com.example.business.order.dtos.response.OrderResponse;
import com.example.business.order.entities.Order;
import com.example.business.order.entities.OrderItem;
import com.example.business.order.enums.OrderStatus;
import com.example.business.order.exceptions.InsufficientStockException;
import com.example.business.order.exceptions.OrderNotFoundException;
import com.example.business.order.repositories.OrderItemRepository;
import com.example.business.order.repositories.OrderRepository;
import com.example.business.product.entities.Product;
import com.example.business.product.exceptions.ProductNotFoundException;
import com.example.business.product.repositories.ProductRepository;
import com.example.business.user.services.AddressService;
import com.example.business.user.services.UserService;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartService cartService;
    private final AddressService addressService;
    private final ProductRepository productRepository;
    private final UserService userService;

    public OrderService(
        OrderRepository orderRepository,
        OrderItemRepository orderItemRepository,
        CartService cartService,
        AddressService addressService,
        ProductRepository productRepository,
        UserService userService
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartService = cartService;
        this.addressService = addressService;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Transactional
    public OrderResponse createOrderFromCart(Long userId, OrderCreateRequest request) {
        userService.ensureUserExists(userId);
        addressService.findAddressForUser(userId, request.getShippingAddressId());

        List<CartItem> cartItems = cartService.getCartItemsForCheckout(userId);
        if (cartItems.isEmpty()) {
            throw new EmptyCartException(userId);
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(cartItem.getProductId()));

            if (product.getStockProduct() == null || product.getStockProduct() < cartItem.getQuantity()) {
                throw new InsufficientStockException(
                    product.getId(),
                    cartItem.getQuantity(),
                    product.getStockProduct() != null ? product.getStockProduct() : 0
                );
            }

            BigDecimal lineTotal = product.getPriceProduct()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(product.getPriceProduct());
            orderItems.add(orderItem);

            product.setStockProduct(product.getStockProduct() - cartItem.getQuantity());
            productRepository.save(product);
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setShippingAddressId(request.getShippingAddressId());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(totalAmount);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(savedOrder.getId());
        }
        orderItemRepository.saveAll(orderItems);

        cartService.clearCart(userId);

        return toResponse(savedOrder, orderItems);
    }

    public List<OrderResponse> getOrdersByUserId(Long userId) {
        userService.ensureUserExists(userId);
        return orderRepository.findByUserId(userId)
            .stream()
            .map(order -> toResponse(order, orderItemRepository.findByOrderId(order.getId())))
            .toList();
    }

    public OrderResponse getOrderById(Long userId, Long orderId) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));

        return toResponse(order, orderItemRepository.findByOrderId(order.getId()));
    }

    private OrderResponse toResponse(Order order, List<OrderItem> items) {
        List<OrderItemResponse> itemResponses = items.stream()
            .map(item -> {
                Product product = productRepository.findById(item.getProductId()).orElse(null);
                String productName = product != null ? product.getNameProduct() : null;
                return new OrderItemResponse(
                    item.getId(),
                    item.getProductId(),
                    productName,
                    item.getQuantity(),
                    item.getPriceAtPurchase()
                );
            })
            .toList();

        return new OrderResponse(
            order.getId(),
            order.getUserId(),
            order.getShippingAddressId(),
            order.getStatus(),
            order.getTotalAmount(),
            order.getCreatedAt(),
            itemResponses
        );
    }
}
