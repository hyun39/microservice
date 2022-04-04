package smallshop;


public class Shipped extends AbstractEvent {

    private Long id;
    private Long orderid;
    private Long productid;

    public Shipped(){
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
    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long Productid) {
        this.productid = productid;
    }
}
