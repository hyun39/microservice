
# https://labs.msaez.io/
- shop.json 



# 여기서는 Spring Applicatin으로 Rest 서비스로 뛰워보는 것이다.



cd order
mvn spring-boot:run


기동된 order 서비스를 호출하여 주문 1건을 요청한다.
- http localhost:8081/orders productId=1 productName="TV" qty=3


- 주문된 상품을 조회한다.
http localhost:8081/orders

주문된 상품을 수정한다.
- http PATCH localhost:8081/orders


실행중 프로세스 확인 및 삭제
netstat -lntp | grep :808 
kill -9 <process id>



# 설정관련
- 개별서비스



Order
    Spring
        JPA
        cloud   
            Stream
                Kafka
                Bindings
    Logging

Delivery
    상동

Gateway 
    Spring
        cloud   
            routes
            globalcors








