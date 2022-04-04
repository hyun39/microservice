order 마이크로 서비스를 8081 포트로 실행한다.
order 서비스를 찾아서 삭제해야 한다면

# netstat -lntp | grep :808 
tcp        0      0 0.0.0.0:8080            0.0.0.0:*               LISTEN      3752/java           
tcp        0      0 0.0.0.0:8081            0.0.0.0:*               LISTEN      3109/java           
# kill -9 3109  <-- 해당 pid


gateway 마이크로 서비스를 8080 포트로 실행한다.
 cd gateway
 mvn spring-boot:run


기동된 order 서비스를 호출하여 주문 1건을 요청한다.
  http localhost:8081/orders productId=1 productName="TV" qty=3
  http localhost:8081/orders


게이트웨이를 통하여 같은 url 을 port 를 변경하여 실행한다.
 http localhost:8080/orders productId=1 productName="PC" qty=1
 http localhost:8080/orders  


delivery 마이크로 서비스를 8082 포트로 실행한다.
게이트웨이서비스의 application.yaml 의 spring.cloud.gateway.routes 에 아래 설정을 추가하여 delivery 서비스로의 라우팅을 추가한다. (indent 에 주의해주세요)
    - id: delivery
        uri: http://localhost:8082
        predicates:
          - Path=/deliveries/** 


게이트웨이 서비스를 재기동 한다.
 fuser -k 8080/tcp  #서비스 종료
 mvn spring-boot:run #서비스 기동           


8082 포트로 delivery 서비스를 호출하여 보고, 8080포트로 게이트웨이를 통하여 delivery 서비스를 호출한다.
 http localhost:8082/deliveries
 http localhost:8080/deliveries


 # 설정관련
 