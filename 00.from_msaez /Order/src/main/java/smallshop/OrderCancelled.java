package smallshop;


public class OrderCancelled extends AbstractEvent {

    private Long id;
    private Long orderid;

    public OrderCancelled(){
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
