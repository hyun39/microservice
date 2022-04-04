package smallshop;


public class OrderPlaced extends AbstractEvent {

    private Long id;
    private Long orderid;

    public OrderPlaced(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long Orderid) {
        this.orderid = orderid;
    }
}
