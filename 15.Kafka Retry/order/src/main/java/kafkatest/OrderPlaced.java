package kafkatest;


public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String message;

    public OrderPlaced() {
        super();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
