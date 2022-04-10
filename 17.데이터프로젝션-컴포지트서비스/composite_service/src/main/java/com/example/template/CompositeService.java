package com.example.template;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CompositeService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.url.order:http://orders:8080}")
    private String orderURL;

    @Value("${api.url.product:http://products:8080}")
    private String productURL;

    @Value("${api.url.delivery:http://delivery:8080}")
    private String deliveryURL;


    public List<OrderInfo> getOrderByCustomerId(String customerId) throws ExecutionException, InterruptedException {

        // Call OrderService API
        CompletableFuture<List<OrderInfo>> orderListCF = CompletableFuture.supplyAsync(() -> {
            String orderUri = orderURL + "/orders/search/findByCustomerId?customerId="+ customerId;
            String orderListObject = restTemplate.getForObject(orderUri, String.class);
            return orderListObject;
        }).thenCompose(orderListObject -> CompletableFuture.supplyAsync(() -> {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(orderListObject).getAsJsonObject();
            JsonArray orderListJsonArray = ((JsonObject)jsonObject.get("_embedded")).getAsJsonArray("orders");
            Type listType = new TypeToken<List<Order>>() {}.getType();
            List<Order> orderListTemp = new Gson().fromJson(orderListJsonArray, listType);

            List<OrderInfo> orderInfoList = new ArrayList<>();
            for( Order order : orderListTemp ){
                // Call product Service API
                CompletableFuture<Product> productInfoCF = CompletableFuture.supplyAsync(() -> {
                    String productUri = productURL + "product/"+ order.getProductId();
                    Product productInformation = restTemplate.getForObject(productUri, Product.class);
                    return productInformation;
                });

                // Call delivery Service API ( HATEOAS API 호출 )
                CompletableFuture<Delivery> deliveryInfoCF = CompletableFuture.supplyAsync(() -> {
                    String deliveryUrlDetail = order.get_links().get("delivery").getAsJsonObject().get("href").getAsString();
                    String deliveryUrl = deliveryURL + deliveryUrlDetail;
                    String deliveryListObject = restTemplate.getForObject(deliveryUrl, String.class);
                    JsonObject deliveryJsonObject = parser.parse(deliveryListObject).getAsJsonObject();
                    JsonArray deliveryListJsonArray = ((JsonObject)deliveryJsonObject.get("_embedded")).getAsJsonArray("deliveries");
                    Delivery deliveryInformation = new Gson().fromJson(deliveryListJsonArray.get(0), Delivery.class);

                    return deliveryInformation;
                });

                // 모든 작업이 끝나기를 기다린다.
                CompletableFuture.allOf(productInfoCF, deliveryInfoCF).join();
                try {
                    // 데이터를 조합한다
                    OrderInfo orderInfo = new OrderInfo(order, productInfoCF.get(), deliveryInfoCF.get());
                    orderInfoList.add(orderInfo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            return orderInfoList;
        }));

        return orderListCF.get();
    }
}
