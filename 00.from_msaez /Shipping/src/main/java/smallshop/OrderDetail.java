package smallshop;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;



@Entity
public class OrderDetail  {

    
    private Integer price;
    
    private Long productid;
    
    private Integer qty;


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }




}
