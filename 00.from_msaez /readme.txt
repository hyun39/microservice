




# one:multi 

    구조에서 multi 클래스에도 반드시 pk가 지정이 되어야 한다.


# one 에 해당하는 클래스에서 

    @OneToMany(cascade = CascadeType.ALL) 를 해주어야 한다.


# 호출하기

    http post localhost:8081/orders customerid=1 paymenttype=0 orderDetail:='[{"productid":1, "qty":10}]'