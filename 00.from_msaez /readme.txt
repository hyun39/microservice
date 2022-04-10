


# one:multi 

    구조에서 multi 클래스에도 반드시 pk가 지정이 되어야 한다.


# one 에 해당하는 클래스에서 

    @OneToMany(cascade = CascadeType.ALL) 를 해주어야 한다.


# 호출하기

    http post localhost:8081/orders customerid=1 paymenttype=0 orderDetail:='[{"productid":1, "qty":10}]'


# event에서는 Trigger를 지정을 할수가 있다.

    pre/post persist, update, remove 이다



#
https://brunch.co.kr/@purpledev/30

https://blog.woniper.net/346


등록하기 ( GET, POST 둘다 된다)
- http localhost:8081/orders productId=1 productName="TV" qty=3

주문된 상품을 조회한다.
- http localhost:8081/orders

주문된 상품을 수정한다. ( PATCH는 대문자로 써주어야 한다 )
- http PATCH  localhost:8081/orders/1  productId=2  productName="TV" qty=5

포스트로 (CHILD 포함) 넘기기
- http post localhost:8081/orders customerid=1 paymenttype=0 orderDetail:='[{"productid":1, "qty":10}]'

삭제하기
- http DELETE  localhost:8081/orders/1 