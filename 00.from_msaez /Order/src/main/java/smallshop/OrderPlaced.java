package smallshop;


public class OrderPlaced extends AbstractEvent {

    private Long id;

    public OrderPlaced(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
}
