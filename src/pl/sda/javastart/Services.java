package pl.sda.javastart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Services {
     Long id;
     BigDecimal price;
     String name;


     private String view(){
         return id + " " + name + " " + price;
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
