package com.example.template.delivery;

import com.example.template.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService{

    /**
     * 배송 fallback
     */
    public void startDelivery(Delivery delivery) {
        System.out.println("@@@@@@@ 배송이 지연중 입니다. @@@@@@@@@@@@");
        System.out.println("@@@@@@@ 배송이 지연중 입니다. @@@@@@@@@@@@");
        System.out.println("@@@@@@@ 배송이 지연중 입니다. @@@@@@@@@@@@");
    }

}
