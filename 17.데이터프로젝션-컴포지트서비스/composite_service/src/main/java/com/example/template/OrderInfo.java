package com.example.template;

public class OrderInfo {

    // from order
    private Long orderId;
    private Long productId;
    private int quantity;
    private String productName;

    // from product
    int price;
    String imageUrl;

    // from delivery
    private String customerId;
    private String deliveryState;


    public OrderInfo(Order order, Product product, Delivery delivery){
        this.setOrderId(delivery.getOrderId());
        this.setProductId(order.getProductId());
        this.setQuantity(order.getQuantity());
        this.setProductName(product.getName());
        this.setPrice(product.getPrice());
        this.setImageUrl(product.getImageUrl());
        this.setCustomerId(delivery.getCustomerId());
        this.setDeliveryState(delivery.getDeliveryState());
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }
}
